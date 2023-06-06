package classes;

import enumerations.Colors;

import java.time.LocalDate;
import java.util.*;

/**
 * Clasa MedicalOfficeService reprezintă obiectul care efectueaza toate operatiile din cadrul Medical System.
 * Aceasta contine dataBase-ul programului.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class MedicalOfficeService {
    private static MedicalOfficeService instance = null;
    private final List<Client> clientList;
    private final List<MedicalStaff> medicalStaffList;
    private final Map<MedicalStaff, List<Appointment>> staffScheduleMap;
    private final List<Appointment> appointmentList;
    private Map<Client, MedicalRecord> medicalRecordsMap;
    private final LocalDate currentDate = LocalDate.now();

    private MedicalOfficeService() {
        clientList = new ArrayList<Client>();
        medicalStaffList = new ArrayList<MedicalStaff>();
        staffScheduleMap = new HashMap<MedicalStaff, List<Appointment>>();
        appointmentList = new ArrayList<Appointment>();
        medicalRecordsMap = new HashMap<Client, MedicalRecord>();
    }

    public static MedicalOfficeService getInstance(){
        if (instance == null){
            instance = new MedicalOfficeService();
        }
        return instance;
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public void removeClient(Client client) {
        clientList.remove(client);

        for (int i = 0; i < clientList.size(); i++) {
            Client updatedClient = clientList.get(i);
            updatedClient.setID("C" + i);
        }
    }

    public List<Client> getAllClients() {
        return clientList;
    }

    public Client getClientByID(String clientID) {
        for (Client client : clientList) {
            if (client.getID().equals(clientID)) {
                return client;
            }
        }
        return null;
    }

    public MedicalStaff getMedicalStaffByID(String staffID) {
        for (MedicalStaff staff : medicalStaffList) {
            if (staff.getID().equals(staffID)) {
                return staff;
            }
        }
        return null;
    }

    public Appointment getAppointmentByID(String appID) {
        for (Appointment apps : appointmentList) {
            if (apps.getID().equals(appID)) {
                return apps;
            }
        }
        return null;
    }

    public MedicalStaff getStaffByName(String name) {
        for (MedicalStaff staff : medicalStaffList) {
            if (staff.getName().equalsIgnoreCase(name)) {
                return staff;
            }
        }
        return null;
    }

    public void setMedicalRecords(Map<Client, MedicalRecord> medicalRecordsMap) {
        this.medicalRecordsMap = medicalRecordsMap;
    }

    public Appointment createAppointment(Vector<Appointment> appointments, MedicalStaff staff, Client client, String date, String time, String description) {
        String lastId = appointments.get(appointments.size() - 1).getID();
        int lastNum = Integer.parseInt(lastId.substring(1));
        String newId = String.format("A%d", lastNum + 1);
        return new Appointment(newId, staff, client, date, time, description);
    }

    public void addAppointment(Appointment appointment) {
        MedicalStaff staff = appointment.getMedicalStaff();
        List<Appointment> schedule = staffScheduleMap.computeIfAbsent(staff, k -> new ArrayList<Appointment>());
        schedule.add(appointment);
        appointmentList.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        MedicalStaff staff = appointment.getMedicalStaff();
        List<Appointment> schedule = staffScheduleMap.get(staff);
        if (schedule != null) {
            schedule.remove(appointment);
        }
        appointmentList.remove(appointment);

        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment updatedAppointment = appointmentList.get(i);
            updatedAppointment.setID("A" + i);
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointmentList;
    }

    public List<Appointment> getScheduleForStaff(MedicalStaff staff) {
        return staffScheduleMap.getOrDefault(staff, new ArrayList<>());
    }

    public boolean isStaffAvailable(MedicalStaff staff, String dateString, String timeString) {
        List<Appointment> schedule = getScheduleForStaff(staff);

        for (Appointment appointment : schedule) {
            if (appointment.getDate().equals(dateString) && appointment.getTime().equals(timeString)) {
                System.out.println("Staff is already occupied at the specified date and time");
                return false;
            }
        }
        return true;
    }

    public void addMedicalStaff(MedicalStaff staff) {
        medicalStaffList.add(staff);
    }

    public void removeMedicalStaff(MedicalStaff staff) {
        int indexToRemove = medicalStaffList.indexOf(staff);
        medicalStaffList.remove(staff);

        for (int i = indexToRemove; i < medicalStaffList.size(); i++) {
            MedicalStaff updatedStaff = medicalStaffList.get(i);
            if (updatedStaff instanceof Doctor) {
                updatedStaff.setID("D" + i);
            } else if (updatedStaff instanceof Nurse) {
                updatedStaff.setID("N" + i);
            }
        }
    }

    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffList;
    }

    public void insertDoctor(Vector<Doctor> doctors, String name, String contactInfo, List<String> specialities) {
        Doctor doctor = new Doctor("D"+doctors.size(), name, contactInfo, specialities);
        this.addMedicalStaff(doctor);
        doctors.add(doctor);
    }

    public void insertDoctor(Vector<Doctor> doctors, String name, String contactInfo) {
        Doctor doctor = new Doctor("D"+doctors.size(), name, contactInfo);
        this.addMedicalStaff(doctor);
        doctors.add(doctor);
    }

    public void insertNurse(Vector<Nurse> nurses, String name, String contactInfo, int experienceYears) {
        Nurse nurse = new Nurse("N"+nurses.size(), name, contactInfo, experienceYears);
        this.addMedicalStaff(nurse);
        nurses.add(nurse);
    }

    public void insertClient(Vector<Client> clients, String name, String diagnosis, int age) {
        Client client = new Client("C"+clients.size(), name, age, diagnosis, 0);
        this.addClient(client);
        clients.add(client);
    }

    public void printAllMedicalRecords() {
        for (Client client : clientList) {
            printMedicalRecord(client);
        }
    }

    public void printMedicalRecord(Client client) {
        MedicalRecord medicalRecord = medicalRecordsMap.get(client);
        if (medicalRecord != null) {
            System.out.println(Colors.LIGHT_BLUE + "Client" + Colors.RESET + ": " + client.getName());
            System.out.println(Colors.YELLOW + "Medical History" + Colors.RESET + ":");
            for (String history : medicalRecord.getMedicalHistory()) {
                System.out.println(history);
            }
            System.out.println(Colors.YELLOW + "Allergies" + Colors.RESET + ":");
            for (String allergy : medicalRecord.getAllergies()) {
                System.out.println(allergy);
            }
            System.out.println(Colors.YELLOW + "Medications" + Colors.RESET + ":");
            int counter = 1;
            for (Medicine medication : medicalRecord.getMedications()) {
                System.out.println(Colors.GREEN + "Medication " + counter++ + Colors.RESET + ": ");
                System.out.println("    - " + Colors.RED + "Name" + Colors.RESET + ": " + medication.getName());
                System.out.println("    - " + Colors.RED + "Year" + Colors.RESET + ": " + medication.getYear());
                System.out.println("    - " + Colors.RED + "Dosage" + Colors.RESET + ": " + medication.getDosage());
                System.out.println("    - " + Colors.RED + "Prescripted for" + Colors.RESET + ": " + medication.getPrescriptionFor());
                System.out.println("    - " + Colors.RED + "Duration" + Colors.RESET + ": " + medication.getDuration());
                System.out.println("--------------------");
            }
            System.out.println("####################");
        }
    }

    public void insertHistoryRecord(Client client, String history) {
        MedicalRecord medicalRecord = medicalRecordsMap.get(client);
        if (medicalRecord != null) {
            List<String> medicalHistory = medicalRecord.getMedicalHistory();
            medicalHistory.add("- " + currentDate.getYear() + ": " + history);
            medicalRecord.setMedicalHistory(medicalHistory);
        }
    }

    public void insertAllergyRecord(Client client, String allergy) {
        MedicalRecord medicalRecord = medicalRecordsMap.get(client);
        if (medicalRecord != null) {
            List<String> allergies = medicalRecord.getAllergies();
            allergies.add("- " + allergy);
            medicalRecord.setAllergies(allergies);
        }
    }

    public void insertMedicationRecord(Client client, Medicine medication) {
        MedicalRecord medicalRecord = medicalRecordsMap.get(client);
        if (medicalRecord != null) {
            List<Medicine> medications = medicalRecord.getMedications();
            medications.add(medication);
            medicalRecord.setMedications(medications);
        }
    }

    public void clearData() {
        medicalStaffList.clear();
        clientList.clear();
        appointmentList.clear();
        staffScheduleMap.clear();
        medicalRecordsMap.clear();
    }
}


