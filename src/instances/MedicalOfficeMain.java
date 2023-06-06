/*
 * *
 *  * *
 *  * Copyright (c) 2023 Andrei-Stefanel Murariu (@iamxorum) și Petru-Serban Floristeanu
 *  *
 *  * Permisiunea este acordată gratuit oricărei persoane care obține o copie
 *  * a acestui software și a fișierelor de documentație asociate (denumite în continuare "Software"), de a trata
 *  * acest Software fără restricții, inclusiv, dar fără a se limita la, drepturile de
 *  * utilizare, copiere, modificare, unire, publicare, distribuire, sublicențiere și/sau vânzare
 *  * a copiilor Software-ului și de a permite persoanelor cărora Software-ul le este
 *  * furnizat să facă acest lucru, cu condiția să respecte următoarele condiții:
 *  *
 *  * Nota de drepturi de autor și această notificare de permisiune trebuie incluse în toate
 *  * copiile sau porțiuni semnificative ale Software-ului.
 *  * 
 *
 *
 */

package instances;

import classes.*;
import enumerations.Colors;

import java.time.LocalTime;
import java.util.*;

/**
 * Clasa MedicalOfficeMain reprezintă aplicatia in sine.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class MedicalOfficeMain {

    public static void main(String[] args) {
        String name, contactInfo, ID;
        CsvAuditingService auditingService = CsvAuditingService.getInstance();
        MedicalOfficeService service = MedicalOfficeService.getInstance();
        Bank officeBank = Bank.getInstance();
        Map<Client, MedicalRecord> medicalRecordsMap = new HashMap<>();
        Vector<Doctor> doctors = new Vector<>();
        Vector<Nurse> nurses = new Vector<>();
        Vector<Client> clients = new Vector<>();
        Vector<Appointment> appointments = new Vector<>();
        InputOutput io = null;
        try {
            io = InputOutput.getInstance(officeBank, service, doctors, nurses, clients, appointments, medicalRecordsMap);
            if (io == null) {
                throw new MedicalException("Input inexistent");
            }
        } catch (MedicalException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1); // Exit the program with a non-zero status code indicating an error
        } catch (Exception e) {
            // Handle any other exception
            e.printStackTrace();
        }

        if(io != null) {
            auditingService.logLoadDataActionAtStart();

            Scanner scanner = new Scanner(System.in);

            int choice = -1;
            while (choice != 0) {
                System.out.println(Colors.LIGHT_MAGENTA + "                      -------------------------------------------------");
                System.out.println("                      #           1. View all medical staff           #");
                System.out.println("                      #             2. View all clients               #");
                System.out.println("                      #           3. View all appointments            #");
                System.out.println("                      #              4. View all nurses               #");
                System.out.println("                      #             5. View all doctors               #");
                System.out.println("                      #            6. View staff schedule             #");
                System.out.println("                      #             7. Insert new Doctor              #");
                System.out.println("                      #              8. Insert new Nurse              #");
                System.out.println("                      #             9. Insert new Client              #");
                System.out.println("                      #           10. Create new Appointment          #");
                System.out.println("                      #                11. Delete client              #");
                System.out.println("                      #                12. Delete staff               #");
                System.out.println("                      #             13. Delete appointment            #");
                System.out.println("                      #             14. Complete appointment          #");
                System.out.println("                      #                15. Client payment             #");
                System.out.println("                      #                 16. Bank amount               #");
                System.out.println("                      #            17. Print Medical Records          #");
                System.out.println("                      #         18. Print client Medical Records      #");
                System.out.println("                      #        19. Update client Medical Records      #");
                System.out.println("                      #               20. Reload from file            #");
                System.out.println("                      #                    21. Save                   #");
                System.out.println("                      #                    0. Exit                    #");
                System.out.println("                      -------------------------------------------------" + Colors.RESET);
                System.out.print(Colors.LIGHT_CYAN + "Enter your choice:" + Colors.RESET);
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        List<MedicalStaff> allStaff = service.getAllMedicalStaff();
                        System.out.println(Colors.LIGHT_BLUE + "#################################");
                        System.out.println("#         MEDICAL STAFF         #");
                        System.out.println("---------------------------------" + Colors.RESET);
                        for (MedicalStaff staff : allStaff) {
                            staff.printInfo();
                        }
                        break;
                    case 2:
                        List<Client> allClients = service.getAllClients();
                        System.out.println(Colors.LIGHT_BLUE + "#################################");
                        System.out.println("#            CLIENTS            #");
                        System.out.println("---------------------------------" + Colors.RESET);
                        for (Client client : allClients) {
                            client.printInfo();
                        }
                        break;
                    case 3:
                        List<Appointment> allAppointments = service.getAllAppointments();
                        System.out.println(Colors.LIGHT_BLUE + "#################################");
                        System.out.println("#         APPOINTMENTS          #");
                        System.out.println("---------------------------------" + Colors.RESET);
                        for (Appointment appointment : allAppointments) {
                            appointment.printInfo();
                        }
                        break;
                    case 4:
                        System.out.println(Colors.LIGHT_BLUE + "##################################");
                        System.out.println("#             NURSES             #");
                        System.out.println("----------------------------------" + Colors.RESET);
                        for (MedicalStaff nurse : nurses) {
                            nurse.printInfo();
                        }
                        break;
                    case 5:
                        System.out.println(Colors.LIGHT_BLUE + "#################################");
                        System.out.println("#            DOCTORS            #");
                        System.out.println("---------------------------------" + Colors.RESET);
                        for (MedicalStaff doctor : doctors) {
                            doctor.printInfo();
                        }
                        break;
                    case 6:
                        System.out.print(Colors.YELLOW + "Enter the name of the staff: " + Colors.RESET);
                        String staffName = scanner.nextLine();

                        MedicalStaff getStaff = service.getStaffByName(staffName);

                        if (getStaff != null) {
                            List<Appointment> staffSchedule = service.getScheduleForStaff(getStaff);
                            System.out.println("\nSchedule for "+ Colors.YELLOW + getStaff.getName() + Colors.RESET + ":");
                            for (Appointment appointment : staffSchedule) {
                                appointment.printInfo();
                            }
                        } else {
                            System.out.println(Colors.RED + "Staff not found!" + Colors.RESET);
                        }
                        break;
                    case 7:
                        List<String> specs = new ArrayList<>();
                        String spec = "";
                        System.out.print(Colors.YELLOW + "Insert the name of the New doctor: " + Colors.RESET);
                        name = scanner.nextLine();
                        System.out.print(Colors.YELLOW + "Insert the contact information of the New doctor: " + Colors.RESET);
                        contactInfo = scanner.nextLine();
                        int i = 1;
                        while (!spec.equalsIgnoreCase("stop")) {
                            System.out.print(Colors.YELLOW + "      ---> Insert a specialization or type 'stop' to finish: " + i + "} " + Colors.RESET);
                            spec = scanner.nextLine();
                            if (!spec.equalsIgnoreCase("stop")) {
                                specs.add(spec);
                            }
                            i++;
                        }
                        if(specs != null){
                            try {
                                service.insertDoctor(doctors, name, contactInfo, specs);
                                System.out.println(Colors.GREEN + "New doctor inserted successfully." + Colors.RESET);
                                auditingService.logInsertDoctor(doctors.lastElement().getID(), name, contactInfo, specs);
                            } catch (Exception e) {
                                System.out.println(Colors.RED + "Error inserting new doctor: " + Colors.RESET + e.getMessage());
                            }
                        } else {
                            try {
                                service.insertDoctor(doctors, name, contactInfo);
                                System.out.println(Colors.GREEN + "New doctor inserted successfully." + Colors.RESET);
                                auditingService.logInsertDoctor(doctors.lastElement().getID(), name, contactInfo);
                            } catch (Exception e) {
                                System.out.println(Colors.RED + "Error inserting new doctor: " + Colors.RESET + e.getMessage());
                            }
                        }
                        break;
                    case 8:
                        int exp;
                        System.out.print(Colors.YELLOW + "Insert the name of the New nurse: " + Colors.RESET);
                        name = scanner.nextLine();
                        System.out.print(Colors.YELLOW + "Insert the contact information of the New nurse: " + Colors.RESET);
                        contactInfo = scanner.nextLine();
                        System.out.print(Colors.YELLOW + "Insert the years of experience of the New nurse: " + Colors.RESET);
                        exp = scanner.nextInt();
                        try {
                            service.insertNurse(nurses, name, contactInfo, exp);
                            System.out.println(Colors.GREEN + "New nurse inserted successfully." + Colors.RESET);
                            auditingService.logInsertNurse(nurses.lastElement().getID(), name, contactInfo, exp);
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error inserting new nurse: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 9:
                        String diagnosis;
                        int age;
                        System.out.print(Colors.YELLOW + "Insert the name of the New client: " + Colors.RESET);
                        name = scanner.nextLine();
                        System.out.print(Colors.YELLOW + "Insert the diagnosis of the New client: " + Colors.RESET);
                        diagnosis = scanner.nextLine();
                        System.out.print(Colors.YELLOW + "Insert the age of the New client: " + Colors.RESET);
                        age = scanner.nextInt();
                        try {
                            service.insertClient(clients, name, diagnosis, age);
                            System.out.println(Colors.GREEN + "New client inserted successfully." + Colors.RESET);

                            Client newClient = clients.lastElement();
                            MedicalRecord newRecord = new MedicalRecord();
                            medicalRecordsMap.put(newClient, newRecord);

                            io.createRecordFolder(name);

                            auditingService.logInsertClient(clients.lastElement().getID(), name, age, diagnosis);
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error inserting new client: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 10:
                        List<MedicalStaff> appStaff = service.getAllMedicalStaff();
                        List<Client> appClients = service.getAllClients();
                        System.out.println(Colors.LIGHT_BLUE + "#################################");
                        System.out.println("#         MEDICAL STAFF         #");
                        System.out.println("---------------------------------" + Colors.RESET);
                        for (MedicalStaff staff : appStaff) {
                            staff.printInfo();
                        }
                        System.out.println(Colors.LIGHT_BLUE + "#################################");
                        System.out.println("#            CLIENTS            #");
                        System.out.println("---------------------------------" + Colors.RESET);
                        for (Client client : appClients) {
                            client.printInfo();
                        }
                        System.out.println(Colors.LIGHT_BLUE + "#################################");
                        System.out.println("#       CREATE APPOINTMENT      #");
                        System.out.println("---------------------------------" + Colors.RESET);
                        System.out.print(Colors.YELLOW + "Enter staff ID:" + Colors.RESET);
                        String staffId = scanner.nextLine();
                        MedicalStaff staff = service.getMedicalStaffByID(staffId);
                        if (staff == null) {
                            System.out.println(Colors.RED + "Invalid staff ID." + Colors.RESET);
                            break;
                        }
                        System.out.print(Colors.YELLOW + "Enter client ID:" + Colors.RESET);
                        String clientId = scanner.nextLine();
                        Client client = service.getClientByID(clientId);
                        if (client == null) {
                            System.out.println(Colors.RED + "Invalid client ID." + Colors.RESET);
                            break;
                        }
                        System.out.print(Colors.YELLOW + "Enter date (yyyy/MM/dd):" + Colors.RESET);
                        String date = scanner.nextLine();
                        System.out.print(Colors.YELLOW + "Enter time {between 08:00 and 20:00}:" + Colors.RESET);
                        String timeInput = scanner.nextLine();

                        LocalTime time;
                        try {
                            time = LocalTime.parse(timeInput);
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Invalid time format!" + Colors.RESET);
                            return;
                        }

                        LocalTime startTime = LocalTime.parse("08:00");
                        LocalTime endTime = LocalTime.parse("20:00");

                        if (time.isAfter(startTime) && time.isBefore(endTime)) {
                            if(service.isStaffAvailable(staff,date,timeInput)){
                                System.out.print("Enter description:");
                                String description = scanner.nextLine();
                                Appointment appointment = service.createAppointment(appointments,staff, client, date, timeInput, description);
                                appointments.add(appointment);
                                service.addAppointment(appointment);
                                System.out.println(Colors.GREEN + "Appointment created:" + Colors.RESET);
                                auditingService.logInsertAppointment(appointment,staff,client,date,description);
                            }
                        } else {
                            System.out.println(Colors.RED + "Time is outside the range." + Colors.RESET);
                        }
                        break;
                    case 11:
                        System.out.print(Colors.YELLOW + "Insert the ID of the client you want to delete {ex. C0}: " + Colors.RESET);
                        ID = scanner.nextLine();
                        try {
                            Client deletedClient = service.getClientByID(ID);
                            if (deletedClient != null) {
                                service.removeClient(deletedClient);
                                clients.remove(deletedClient);
                                System.out.println(Colors.GREEN + "Client deleted successfully." + Colors.RESET);
                                auditingService.logDeleteClient(deletedClient);
                            } else {
                                System.out.println(Colors.RED + "Client not found!" + Colors.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error deleting the client: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 12:
                        System.out.print(Colors.YELLOW + "Insert the ID of the staff you want to delete {ex. D0 or N0}: " + Colors.RESET);
                        ID = scanner.nextLine();
                        try {
                            MedicalStaff deletedStaff = service.getMedicalStaffByID(ID);
                            if (deletedStaff != null){
                                service.removeMedicalStaff(deletedStaff);
                                doctors.remove(deletedStaff);
                                System.out.println(Colors.GREEN + "Staff deleted successfully." + Colors.RESET);
                                auditingService.logDeleteStaff(deletedStaff);
                            } else {
                                System.out.println(Colors.RED + "Staff not found!" + Colors.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error deleting the staff: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 13:
                        System.out.print(Colors.YELLOW + "Insert the ID of the appointment you want to delete {ex. A0}: " + Colors.RESET);
                        ID = scanner.nextLine();
                        try {
                            Appointment deletedApp = service.getAppointmentByID(ID);
                            if (deletedApp != null){
                                service.removeAppointment(deletedApp);
                                appointments.remove(deletedApp);
                                System.out.println(Colors.GREEN + "Appointment deleted successfully." + Colors.RESET);
                                auditingService.logDeleteAppointment(deletedApp);
                            } else {
                                System.out.println(Colors.RED + "Appointment not found!" + Colors.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error deleting the appointment: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 14:
                        System.out.print(Colors.YELLOW + "Insert the ID of the appointment you want to complete {ex. A0}: " + Colors.RESET);
                        ID = scanner.nextLine();
                        try {
                            Appointment completedApp = service.getAppointmentByID(ID);
                            if (completedApp != null) {
                                service.removeAppointment(completedApp);
                                appointments.remove(completedApp);
                                System.out.print("Insert the cost of the appointment: ");
                                double appointmentCost = scanner.nextDouble();

                                Client client2 = completedApp.getClient();
                                double currentDebt = client2.getDebt();
                                client2.setDebt(currentDebt + appointmentCost);

                                System.out.println(Colors.GREEN + "Appointment completed successfully." + Colors.RESET);
                                auditingService.logCompletedAppointment(completedApp, appointmentCost);
                            } else {
                                System.out.println(Colors.RED + "Appointment not found!" + Colors.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error deleting the appointment: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 15:
                        System.out.print(Colors.YELLOW + "Enter the client's ID: " + Colors.RESET);
                        String clientID = scanner.nextLine();
                        Client client2 = service.getClientByID(clientID);
                        if (client2 != null) {
                            double currentDebt = client2.getDebt();

                            if (currentDebt > 0) {
                                System.out.println(Colors.RED + "Current Debt: $" + Colors.RESET + currentDebt);
                                System.out.print("Enter the payment amount: ");
                                double paymentAmount = scanner.nextDouble();

                                if (paymentAmount <= currentDebt) {
                                    double updatedDebt = currentDebt - paymentAmount;
                                    officeBank.setBank(officeBank.getBank()+paymentAmount);
                                    client2.setDebt(updatedDebt);
                                    System.out.println(Colors.GREEN + "Payment successfully received.");
                                    System.out.println("Updated Debt: $" + Colors.RESET + updatedDebt);
                                    auditingService.logPayment(client2,paymentAmount);
                                } else {
                                    System.out.println(Colors.RED + "Invalid payment amount. The payment cannot exceed the current debt." + Colors.RESET);
                                }
                            } else {
                                System.out.println(Colors.GREEN + "The client has no outstanding debt." + Colors.RESET);
                            }
                        } else {
                            System.out.println(Colors.RED + "Client not found." + Colors.RESET);
                        }
                        break;
                    case 16:
                        officeBank.bankAmount(officeBank.getBank());
                        break;
                    case 17:
                        System.out.println(Colors.LIGHT_BLUE + "Printing medical records..." + Colors.RESET);
                        service.printAllMedicalRecords();
                        break;
                    case 18:
                        System.out.print(Colors.YELLOW + "Insert the ID of the client you want to print the medical record for {ex. C0}: " + Colors.RESET);
                        ID = scanner.nextLine();
                        try {
                            Client client3 = service.getClientByID(ID);
                            if (client3 != null) {
                                service.printMedicalRecord(client3);
                            } else {
                                System.out.println(Colors.RED + "Client not found!" + Colors.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error printing the medical record: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 19:
                        System.out.print(Colors.YELLOW + "Insert the ID of the client you want to update the medical record for {ex. C0}: " + Colors.RESET);
                        ID = scanner.nextLine();
                        try {
                            Client client4 = service.getClientByID(ID);
                            if (client4 != null) {
                                service.printMedicalRecord(client4);
                                System.out.print(Colors.YELLOW + "Insert the medical history you want to add to the client {N if not}: " + Colors.RESET);
                                String medicalHistory = scanner.nextLine();
                                if (!medicalHistory.equals("N")) {
                                    service.insertHistoryRecord(client4, medicalHistory);
                                }
                                System.out.print(Colors.YELLOW + "Insert the allergies you want to add to the client {N if not}:" + Colors.RESET);
                                String allergies = scanner.nextLine();
                                if (!allergies.equals("N")) {
                                    service.insertAllergyRecord(client4, allergies);
                                }
                                System.out.print(Colors.YELLOW + "Do you want to add a new medicine to the client's record? (Y/N)" + Colors.RESET);
                                String answer = scanner.nextLine();
                                if (answer.equals("Y")) {
                                    System.out.print(Colors.YELLOW + "Insert the name of the medicine: " + Colors.RESET);
                                    String medicineName = scanner.nextLine();
                                    System.out.print(Colors.YELLOW + "Insert the year of the medicine: " + Colors.RESET);
                                    int medicineYear = Integer.parseInt(scanner.nextLine());
                                    System.out.print(Colors.YELLOW + "Insert the dosage of the medicine: " + Colors.RESET);
                                    String medicineDosage = scanner.nextLine();
                                    System.out.print(Colors.YELLOW + "Insert the prescription of the medicine: " + Colors.RESET);
                                    String prescription = scanner.nextLine();
                                    System.out.print(Colors.YELLOW + "Insert the duration of the medicine: " + Colors.RESET);
                                    String medicineDuration = scanner.nextLine();
                                    Medicine medicine = new Medicine(medicineName, medicineYear, medicineDosage, prescription, medicineDuration);
                                    service.insertMedicationRecord(client4, medicine);
                                    System.out.println(Colors.YELLOW + "Do you want to add another medicine to the client's record? (Y/N)" + Colors.RESET);
                                    answer = scanner.nextLine();
                                }
                                System.out.println(Colors.GREEN + "Medical record added successfully." + Colors.RESET);
                                auditingService.logClientRecordUpdate(client4);
                            } else {
                                System.out.println(Colors.RED + "Client not found!" + Colors.RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(Colors.RED + "Error updating the medical record: " + Colors.RESET + e.getMessage());
                        }
                        break;
                    case 20:
                        System.out.println(Colors.GREEN + "Reload activity..." + Colors.RESET);
                        doctors.clear();
                        nurses.clear();
                        clients.clear();
                        appointments.clear();
                        medicalRecordsMap.clear();
                        io.loadData(officeBank,service,doctors,nurses,clients,appointments,medicalRecordsMap);
                        auditingService.logLoadDataAction();
                        break;
                    case 21:
                        System.out.println(Colors.GREEN + "Saving activity..." + Colors.RESET);
                        io.saveData(officeBank,doctors,nurses,clients,appointments,medicalRecordsMap);
                        auditingService.logSaveDataAction();
                        break;
                    case 0:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println(Colors.RED + "Invalid choice." + Colors.RESET);
                        break;
                }
            }
        }
    }
}

