package instances;

import classes.*;
import enumerations.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.List;
import java.util.*;


/**
 * Clasa MedicalOffice_GUI_Main reprezintă obiectul care creează interfața grafică a aplicației.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class MedicalOffice_GUI_Main {

    /**
     * Creează o bară de meniu pentru aplicație cu diferite meniuri și elemente de meniu.
     * @param officeBank Obiectul Bank care reprezintă banca biroului.
     * @param service Obiectul MedicalOfficeService care oferă acces la datele biroului medical.
     * @param doctors Vectorul de obiecte Doctor care reprezintă medicii din biroul medical.
     * @param nurses Vectorul de obiecte Nurse care reprezintă asistentele din biroul medical.
     * @param clients Vectorul de obiecte Client care reprezintă clienții din biroul medical.
     * @param appointments Vectorul de obiecte Appointment care reprezintă programările din biroul medical.
     * @param medicalRecordsMap Maparea dintre clienți și fișele medicale corespunzătoare.
     * @param io Obiectul InputOutput care furnizează funcționalitatea de intrare/ieșire.
     * @param auditingService Obiectul CsvAuditingService pentru scopuri de auditare.
     * @return Obiectul JMenuBar care reprezintă bara de meniu pentru aplicație.
     * <p>Această metodă creează o bară de meniu cu diferite meniuri și elemente de meniu pentru aplicație. Bara de meniu include meniuri pentru accesarea fișelor medicale, datelor bancare și alte funcționalități ale sistemului biroului medical.</p>
     * <p>Metoda primește mai mulți parametri care reprezintă datele și serviciile necesare pentru construirea băii de meniu. Acești parametri includ obiectul Bank care reprezintă banca biroului, obiectul MedicalOfficeService care oferă acces la datele biroului medical, vectorii de obiecte Doctor, Nurse și Client care reprezintă personalul medical și clienții, un vector de obiecte Appointment care reprezintă programările, o mapare între clienți și fișele medicale ale acestora, un obiect InputOutput pentru funcționalitatea de intrare/ieșire și un obiect CsvAuditingService pentru scopuri de auditare.</p>
     * <p>Metoda construiește bara de meniu prin crearea de meniuri și elemente de meniu, setarea proprietăților acestora și adăugarea lor în bara de meniu. Meniurile includ opțiuni pentru afișarea fișelor medicale, a sumei din bancă, a informațiilor despre personal și alte funcționalități ale sistemului biroului medical. Elementele de meniu sunt legate de acțiuni sau dialoguri corespunzătoare pentru a realiza operațiile dorite.</p>
     * @see JMenuBar
     * @see JMenu
     * @see JMenuItem
     * @see Bank
     * @see MedicalOfficeService
     * @see Doctor
     * @see Nurse
     * @see Client
     * @see Appointment
     * @see MedicalRecord
     * @see InputOutput
     * @see CsvAuditingService
     *
     * @author Andrei-Stefanel Murariu {@iamxorum} | Petru-Serban Floristeanu
     * @version 1.0
     */
    public static JMenuBar createMenuBar(Bank officeBank, MedicalOfficeService service, Vector<Doctor> doctors, Vector<Nurse> nurses, Vector<Client> clients,Vector<Appointment> appointments, Map<Client, MedicalRecord> medicalRecordsMap, InputOutput io, CsvAuditingService auditingService) {

        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rdmi;
        JCheckBoxMenuItem cbmi;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the Medical Staff Menu.
        menu = new JMenu("Medical Staff");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Accessing Staff Data");
        menuBar.add(menu);

        menuItem = new JMenuItem("Show all Medical staff", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a list of all medical staff");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MedicalStaff> allStaff = service.getAllMedicalStaff();

                StringBuilder message = new StringBuilder();
                for (MedicalStaff staff : allStaff) {
                    message.append(staff.toString() + "\n");
                }

                JOptionPane.showMessageDialog(null, message.toString(), "Medical Staff", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Show all Doctors", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a list of all doctors");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                for (Doctor doctor : doctors) {
                    message.append(doctor.toString() + "\n");
                }

                JOptionPane.showMessageDialog(null, message.toString(), "Doctors", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Show all Nurses", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a list of all nurses");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                for (Nurse nurse : nurses) {
                    message.append(nurse.toString() + "\n");
                }

                JOptionPane.showMessageDialog(null, message.toString(), "Nurses", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Show a Staff Schedule", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_4, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a staff schedule");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffName = JOptionPane.showInputDialog("Enter the name of the staff member: ");
                MedicalStaff getStaff = service.getStaffByName(staffName);

                if (getStaff == null) {
                    JOptionPane.showMessageDialog(null, "Staff member not found", "Staff Schedule", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                List<Appointment> staffSchedule = service.getScheduleForStaff(getStaff);
                StringBuilder message = new StringBuilder();
                for (Appointment appointment : staffSchedule) {
                    message.append(appointment.toString()).append("\n");
                    message.append("--------------------------------------------------\n");
                }

                JTextArea textArea = new JTextArea(message.toString());
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(1000, 700));

                JOptionPane.showMessageDialog(null, scrollPane, "Staff Schedule", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Insert new Doctor", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_5, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Inserting a new doctor");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> specs = new ArrayList<>();
                StringBuilder message = new StringBuilder();
                String spec = "";
                String name = JOptionPane.showInputDialog("Enter the name of the doctor: ");
                String contact = JOptionPane.showInputDialog("Enter the contact information of the doctor: ");
                int i = 1;
                while(!spec.equalsIgnoreCase("stop")){
                    spec = JOptionPane.showInputDialog(+ i + "} Insert a specialization or type 'stop' to finish: ");
                    if(!spec.equalsIgnoreCase("stop")){
                        specs.add(spec);
                    }
                    i++;
                }
                if(specs != null){
                    try {
                        service.insertDoctor(doctors, name, contact, specs);
                        message.append("New doctor inserted successfully.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        auditingService.logInsertDoctor(doctors.lastElement().getID(), name, contact, specs);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    try {
                        service.insertDoctor(doctors, name, contact);
                        message.append("New doctor inserted successfully.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        auditingService.logInsertDoctor(doctors.lastElement().getID(), name, contact);
                    } catch (Exception ex) {
                        message.append("New doctor insertion failed.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Insert new Nurse", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_6, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Inserting a new nurse");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String name = JOptionPane.showInputDialog("Enter the name of the nurse: ");
                String contact = JOptionPane.showInputDialog("Enter the contact information of the nurse: ");
                int expYears = Integer.parseInt(JOptionPane.showInputDialog("Enter the experience years of the nurse: "));
                try {
                    service.insertNurse(nurses, name, contact, expYears);
                    message.append("New nurse inserted successfully.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    auditingService.logInsertNurse(nurses.lastElement().getID(), name, contact, expYears);
                } catch (Exception ex) {
                    message.append("New nurse insertion failed.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Delete Staff", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_7, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Deleting a staff member");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String ID = JOptionPane.showInputDialog("Enter the ID of the staff member: ");
                MedicalStaff getStaff = service.getMedicalStaffByID(ID);
                if (getStaff == null) {
                    message.append("Staff member not found.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Staff Schedule", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    service.removeMedicalStaff(getStaff);
                    doctors.remove(getStaff);
                    message.append("Staff member deleted successfully.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    auditingService.logDeleteStaff(getStaff);
                } catch (Exception ex) {
                    message.append("Staff member deletion failed.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        menu.add(menuItem);

        //Build the Client Menu.
        menu = new JMenu("Clients");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Accessing Client Data");
        menuBar.add(menu);

        menuItem = new JMenuItem("Show all Clients", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a list of all clients");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                for (Client client : clients) {
                    message.append(client.toString() + "\n");
                }

                JOptionPane.showMessageDialog(null, message.toString(), "Clients", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Insert new Client", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Inserting a new client");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String name = JOptionPane.showInputDialog("Enter the name of the client: ");
                String diagnosis = JOptionPane.showInputDialog("Enter the diagnosis of the client: ");
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter the age of the client: "));
                try {
                    service.insertClient(clients, name, diagnosis, age);

                    Client newClient = clients.lastElement();
                    MedicalRecord newRecord = new MedicalRecord();
                    medicalRecordsMap.put(newClient, newRecord);

                    io.createRecordFolder(name);

                    message.append("New client inserted successfully.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    auditingService.logInsertClient(clients.lastElement().getID(), name, age, diagnosis);
                } catch (Exception ex) {
                    message.append("New client insertion failed.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Delete Client", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Deleting a client");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String ID = JOptionPane.showInputDialog("Enter the ID of the client: ");
                try {
                    Client deletedClient = service.getClientByID(ID);
                    if (deletedClient != null) {
                        service.removeClient(deletedClient);
                        clients.remove(deletedClient);
                        message.append("Client deleted successfully.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        auditingService.logDeleteClient(deletedClient);
                    } else {
                        message.append("Client deletion failed.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    System.out.println(Colors.RED + "Error deleting the client: " + Colors.RESET + ex.getMessage());
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Client Payment", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_4, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Client payment");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String ID = JOptionPane.showInputDialog("Enter the ID of the client: ");
                Client client2 = service.getClientByID(ID);
                if (client2 != null) {
                    double currentDebt = client2.getDebt();
                    if (currentDebt > 0) {
                        message.append("Current Debt: $" + currentDebt + "\n");
                        message.append("Enter the payment amount: ");
                        double paymentAmount = Double.parseDouble(JOptionPane.showInputDialog(message.toString()));

                        if (paymentAmount <= currentDebt) {
                            double updatedDebt = currentDebt - paymentAmount;
                            officeBank.setBank(officeBank.getBank()+paymentAmount);
                            client2.setDebt(updatedDebt);
                            message = new StringBuilder();
                            message.append("Payment successful. New debt: $" + updatedDebt);
                            JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            auditingService.logPayment(client2,paymentAmount);
                        } else {
                            message = new StringBuilder();
                            message.append("Payment failed. The payment amount exceeds the current debt.");
                            JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        System.out.println(Colors.GREEN + "The client has no outstanding debt." + Colors.RESET);
                    }
                } else {
                    System.out.println(Colors.RED + "Client not found." + Colors.RESET);
                }
            }
        });
        menu.add(menuItem);

        //Build the Appointment Menu.
        menu = new JMenu("Appointments");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Accessing Appointment Data");
        menuBar.add(menu);

        menuItem = new JMenuItem("Show all Appointments", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a list of all appointments");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                for (Appointment appointment : appointments) {
                    message.append(appointment.toString()).append("\n");
                    message.append("---------------------------------\n");
                }

                JTextArea textArea = new JTextArea(message.toString());
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                JOptionPane.showMessageDialog(null, scrollPane, "Appointments", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Create new Appointment", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Creating a new appointment");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String staffID = JOptionPane.showInputDialog("Enter the ID of the staff: ");
                MedicalStaff staff = service.getMedicalStaffByID(staffID);
                if (staff == null) {
                    message.append("No staff with the given ID was found.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String clientID = JOptionPane.showInputDialog("Enter the ID of the client: ");
                Client client = service.getClientByID(clientID);
                if (client == null) {
                    message.append("No client with the given ID was found.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String date = JOptionPane.showInputDialog("Enter the date of the appointment (yyyy/MM/dd): ");
                String timeInput = JOptionPane.showInputDialog("Enter the time of the appointment {between 08:00 and 20:00}: ");

                LocalTime time;
                try {
                    time = LocalTime.parse(timeInput);
                } catch (Exception ex) {
                    System.out.println(Colors.RED + "Invalid time format!" + Colors.RESET);
                    return;
                }

                LocalTime startTime = LocalTime.parse("08:00");
                LocalTime endTime = LocalTime.parse("20:00");
                if(time.isBefore(startTime) && time.isAfter(endTime)) {
                    if(service.isStaffAvailable(staff,date,timeInput)){
                        String description = JOptionPane.showInputDialog("Enter the description of the appointment: ");
                        Appointment appointment = service.createAppointment(appointments,staff, client, date, timeInput, description);
                        appointments.add(appointment);
                        service.addAppointment(appointment);
                        message.append("New appointment created successfully.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        auditingService.logInsertAppointment(appointment,staff,client,date,description);
                    } else {
                        message.append("Staff is not available at the given time.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Delete an Appointment", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Deleting an appointment");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String ID = JOptionPane.showInputDialog("Enter the ID of the appointment: ");
                Appointment deletedAppointment = service.getAppointmentByID(ID);
                if (deletedAppointment != null) {
                    service.removeAppointment(deletedAppointment);
                    appointments.remove(deletedAppointment);
                    message.append("Appointment deleted successfully.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    auditingService.logDeleteAppointment(deletedAppointment);
                } else {
                    message.append("Appointment deletion failed.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Complete an Appointment", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_4, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Completing an appointment");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String ID = JOptionPane.showInputDialog("Enter the ID of the appointment: ");
                try {
                    Appointment completedApp = service.getAppointmentByID(ID);
                    if (completedApp != null) {
                        service.removeAppointment(completedApp);
                        appointments.remove(completedApp);
                        double appointmentCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the cost of the appointment: "));


                        Client client2 = completedApp.getClient();
                        double currentDebt = client2.getDebt();
                        client2.setDebt(currentDebt + appointmentCost);

                        message.append("Appointment completed successfully.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        auditingService.logCompletedAppointment(completedApp, appointmentCost);
                    } else {
                        message.append("Appointment completion failed.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    System.out.println(Colors.RED + "Error deleting the appointment: " + Colors.RESET + ex.getMessage());
                }
            }
        });
        menu.add(menuItem);

        //Build the Medical Records Menu.
        menu = new JMenu("Medical Records");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Accessing Medical Records Data");
        menuBar.add(menu);

        menuItem = new JMenuItem("Show all Medical Records", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a list of all medical records");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                for (Map.Entry<Client, MedicalRecord> entry : medicalRecordsMap.entrySet()) {
                    Client client = entry.getKey();
                    MedicalRecord medicalRecord = entry.getValue();
                    message.append("Client: ").append(client.toString()).append("\n");
                    message.append("Medical Record: ").append(medicalRecord.toString()).append("\n");
                    message.append("---------------------------------\n");
                }

                JTextArea textArea = new JTextArea(message.toString());
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(1000, 700));

                JOptionPane.showMessageDialog(null, scrollPane, "Medical Records", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Show Medical Record by Client", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out a medical record by client");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String ID = JOptionPane.showInputDialog("Enter the ID of the client {Ex C0}: ");
                Client client = service.getClientByID(ID);
                if (client != null) {
                    MedicalRecord medicalRecord = medicalRecordsMap.get(client);
                    message.append("Client: ").append(client.toString()).append("\n");
                    message.append("Medical Record: ").append(medicalRecord.toString()).append("\n");
                    message.append("---------------------------------\n");
                    JOptionPane.showMessageDialog(null, message.toString(), "Medical Record", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    message.append("Client not found.");
                    JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Update Medical Record", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Updating a medical record");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                String ID = JOptionPane.showInputDialog("Enter the ID of the client {Ex. C0}: ");
                try {
                    Client client4 = service.getClientByID(ID);
                    if (client4 != null) {
                        service.printMedicalRecord(client4);
                        String medicalHistory = JOptionPane.showInputDialog("Insert the medical history you want to add to the client {N if not}: ");
                        if (!medicalHistory.equals("N")) {
                            service.insertHistoryRecord(client4, medicalHistory);
                        }
                        String allergies = JOptionPane.showInputDialog("Insert the allergies you want to add to the client {N if not}: ");
                        if (!allergies.equals("N")) {
                            service.insertAllergyRecord(client4, allergies);
                        }
                        String answer = JOptionPane.showInputDialog("Do you want to add a medicine to the client {Y/N}: ");
                        if (answer.equals("Y")) {
                            String medicineName = JOptionPane.showInputDialog("Insert the name of the medicine: ");
                            int medicineYear = Integer.parseInt(JOptionPane.showInputDialog("Insert the year of the medicine: "));
                            String medicineDosage = JOptionPane.showInputDialog("Insert the dosage of the medicine: ");
                            String prescription = JOptionPane.showInputDialog("Insert the prescription of the medicine: ");
                            String medicineDuration = JOptionPane.showInputDialog("Insert the duration of the medicine: ");
                            Medicine medicine = new Medicine(medicineName, medicineYear, medicineDosage, prescription, medicineDuration);
                            service.insertMedicationRecord(client4, medicine);
                            answer = JOptionPane.showInputDialog("Do you want to add another medicine to the client {Y/N}: ");
                        }
                        message.append("Medical Record updated successfully.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        auditingService.logClientRecordUpdate(client4);
                    } else {
                        message.append("Client not found.");
                        JOptionPane.showMessageDialog(null, message.toString(), "Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    System.out.println(Colors.RED + "Error updating the medical record: " + Colors.RESET + ex.getMessage());
                }
            }
        });
        menu.add(menuItem);

        //Build the Bank Menu.
        menu = new JMenu("Bank");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Accessing Bank Data");
        menuBar.add(menu);

        menuItem = new JMenuItem("Show Bank Amount", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Printing out the bank amount");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            StringBuilder message = new StringBuilder();
                message.append("Bank amount: ").append(officeBank.getBank()).append("\n");
                JOptionPane.showMessageDialog(null, message.toString(), "Bank Amount", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        //Build the Input Output Menu.
        menu = new JMenu("Input/Output");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Accessing Input/Output Data");
        menuBar.add(menu);

        menuItem = new JMenuItem("Save Data", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Saving data to a file");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                io.saveData(officeBank,doctors,nurses,clients,appointments,medicalRecordsMap);
                auditingService.logSaveDataAction();
                JOptionPane.showMessageDialog(null, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Re-Load Data", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Re-Loading data from a file");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctors.clear();
                nurses.clear();
                clients.clear();
                appointments.clear();
                medicalRecordsMap.clear();
                service.clearData();
                io.loadData(officeBank,service,doctors,nurses,clients,appointments,medicalRecordsMap);
                auditingService.logLoadDataAction();
                JOptionPane.showMessageDialog(null, "Data re-loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);

        //Build the Exit Menu.
        menu = new JMenu("Exit");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("Exiting the application");
        menuBar.add(menu);

        menuItem = new JMenuItem("Exit", new ImageIcon("images/newproject.png"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Exiting the application");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(100);
            }
        });
        menu.add(menuItem);

        return menuBar;
    }

    public static void main(String[] args) {
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

            final JFrame frame = new JFrame("Medical Office Application");
            frame.setJMenuBar(createMenuBar(officeBank, service, doctors, nurses, clients, appointments, medicalRecordsMap, io, auditingService));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 300);
            frame.setVisible(true);
        }
    }
}