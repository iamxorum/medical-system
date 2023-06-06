package classes;

import enumerations.Paths;

import java.io.*;
import java.util.*;

/**
 * Clasa InputOutput se ocupă de gestionarea operațiilor de intrare/ieșire a datelor în/din format CSV.
 * Aceasta include salvarea și încărcarea datelor pentru personalul medical, clienți, programări, banca și fișierele medicale ale clienților.
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class InputOutput {
    private static InputOutput instance = null;
    private String line = "";
    private final String cvsSplitBy = ",";

    
    /**
     * Metodă pentru încărcarea datelor inițiale din sistem.
     *
     * @param bank         Obiectul reprezentând baza de date a băncii.
     * @param service      Obiectul reprezentând serviciul de birou medical.
     * @param doctors      Lista de obiecte Doctor reprezentând medicii disponibili.
     * @param nurses       Lista de obiecte Nurse reprezentând asistentele medicale disponibile.
     * @param clients      Lista de obiecte Client reprezentând clienții înregistrați.
     * @param appointments Lista de obiecte Appointment reprezentând programările înregistrate.
     * @param records      Mapa care asociază fiecărui client un obiect MedicalRecord.
     */
    private InputOutput(Bank bank, MedicalOfficeService service, Vector<Doctor> doctors, Vector<Nurse> nurses, Vector<Client> clients, Vector<Appointment> appointments, Map<Client, MedicalRecord> records) {
        loadData(bank,service,doctors,nurses,clients,appointments, records);
    }

    /**
     * Returnează instanța singleton a clasei InputOutput. Dacă nu există încă o instanță, aceasta va fi creată.
     *
     * @param bank          obiectul Bank
     * @param service       obiectul MedicalOfficeService
     * @param doctors       vectorul de obiecte Doctor
     * @param nurses        vectorul de obiecte Nurse
     * @param clients       vectorul de obiecte Client
     * @param appointments  vectorul de obiecte Appointment
     * @param records       harta care mapează Client la MedicalRecord
     * @return instanța singleton a clasei InputOutput
     */
    public static InputOutput getInstance(Bank bank, MedicalOfficeService service, Vector<Doctor> doctors, Vector<Nurse> nurses, Vector<Client> clients, Vector<Appointment> appointments, Map<Client, MedicalRecord> records) {
        if (instance == null) {
            instance = new InputOutput(bank, service, doctors, nurses, clients, appointments, records);
        }
        return instance;
    }
    /**
     * Salvează datele băncii, doctorilor, asistentelor, pacienților, programărilor și înregistrărilor medicale.
     *
     * @param bank         obiectul băncii
     * @param doctors      lista de doctori
     * @param nurses       lista de asistente
     * @param clients      lista de clienți
     * @param appointments lista de programări
     * @param records      harta de înregistrări medicale
     */
    public void saveData(Bank bank, Vector<Doctor> doctors, Vector<Nurse> nurses, Vector<Client> clients, Vector<Appointment> appointments, Map<Client, MedicalRecord> records) {
        saveMedicalStaffData(doctors, nurses);
        saveClientData(clients);
        saveAppointmentData(appointments);
        saveBankData(bank);
        saveMedicalRecords(records);
    }

    /**
     * Încarcă datele în serviciul MedicalOffice.
     *
     * @param bank      Obiectul de tip Bank pentru încărcarea datelor băncii.
     * @param service   Serviciul MedicalOfficeService pentru încărcarea datelor.
     * @param doctors   Vectorul de doctori pentru încărcarea datelor medicilor.
     * @param nurses    Vectorul de asistente pentru încărcarea datelor asistentelor.
     * @param clients   Vectorul de clienți pentru încărcarea datelor clienților.
     * @param appointments Mapa de programări pentru încărcarea datelor programărilor.
     * @param records   Mapa de înregistrări medicale pentru încărcarea datelor înregistrărilor.
     */
    public void loadData(Bank bank, MedicalOfficeService service, Vector<Doctor> doctors, Vector<Nurse> nurses, Vector<Client> clients, Vector<Appointment> appointments, Map<Client, MedicalRecord> records) {
        loadMedicalStaffData(service, doctors, nurses);
        loadClientData(service, clients);
        loadAppointmentData(service, doctors, clients, appointments);
        loadBankData(bank);
        loadMedicalRecords(service, records, clients);
    }

    /**
     * Metoda salvează datele personalului medical în fișierele CSV.
     *
     * @param doctors Vectorul de doctori.
     * @param nurses Vectorul de asistente.
     */
    private void saveMedicalStaffData(Vector<Doctor> doctors, Vector<Nurse> nurses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(Paths.MEDICAL_STAFF_CSV)));
             PrintWriter writer2 = new PrintWriter(new FileWriter(String.valueOf(Paths.DOCTOR_CSV)));
             PrintWriter writer3 = new PrintWriter(new FileWriter(String.valueOf(Paths.NURSE_CSV)))) {

            for (Doctor doctor : doctors) {
                if (doctor.getSpecialties() != null) {
                    writer.println("Doctor," + doctor.getID() + "," + doctor.getName() + "," + doctor.getContactInfo() + "," + String.join(",", doctor.getSpecialties()));
                    writer2.println(doctor.getID() + "," + doctor.getName() + "," + doctor.getContactInfo() + "," + String.join(",", doctor.getSpecialties()));
                } else {
                    writer.println("Doctor," + doctor.getID() + "," + doctor.getName() + "," + doctor.getContactInfo());
                    writer2.println(doctor.getID() + "," + doctor.getName() + "," + doctor.getContactInfo());
                }
            }

            for (Nurse nurse : nurses) {
                writer.println("Nurse," + nurse.getID() + "," + nurse.getName() + "," + nurse.getContactInfo() + "," + nurse.getExperienceYears());
                writer3.println(nurse.getID() + "," + nurse.getName() + "," + nurse.getContactInfo() + "," + nurse.getExperienceYears());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salvează datele despre clienți în fișierul CSV.
     *
     * @param clients Vectorul de clienți
     */
    private void saveClientData(Vector<Client> clients) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(Paths.CLIENT_CSV)))) {
            for (Client client : clients) {
                writer.println(client.getID() + "," + client.getName() + "," + client.getAge() + "," + client.getDiagnosis() + "," + client.getDebt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salvează datele despre programări într-un fișier CSV.
     *
     * @param appointments Lista de programări
     */
    private void saveAppointmentData(Vector<Appointment> appointments) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(Paths.APPOINTMENT_CSV)))) {
            for (Appointment appointment : appointments) {
                writer.println(appointment.getID() + "," + appointment.getMedicalStaff().getName() + "," + appointment.getClient().getName() + "," + appointment.getDate() + "," + appointment.getTime() + "," + appointment.getDescription());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salvează datele despre bancă într-un fișier CSV.
     *
     * @param bank Obiectul bancă
     */
    private void saveBankData(Bank bank) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(Paths.BANK_CSV)))) {
            writer.println(bank.getBank());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salvează înregistrările medicale în dosarele corespunzătoare ale clienților.
     *
     * @param medicalRecords Mapă care conține înregistrările medicale, unde cheile sunt clienți și valorile sunt înregistrările medicale asociate.
     */
    public void saveMedicalRecords(Map<Client, MedicalRecord> medicalRecords) {
        for (Map.Entry<Client, MedicalRecord> entry : medicalRecords.entrySet()) {
            Client client = entry.getKey();
            MedicalRecord medicalRecord = entry.getValue();
            createRecordFolder(client.getName(), medicalRecord);
        }
    }

    /**
     * Încarcă datele personalului medical din fișierul CSV.
     *
     * @param service Obiectul MedicalOfficeService pentru a adăuga personalul medical.
     * @param doctors Vectorul de Doctori în care se vor adăuga doctorii încărcați.
     * @param nurses Vectorul de Asistente în care se vor adăuga asistentele încărcate.
     */
    private void loadMedicalStaffData(MedicalOfficeService service, Vector<Doctor> doctors, Vector<Nurse> nurses) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(String.valueOf(Paths.MEDICAL_STAFF_CSV)));
            while ((line = br.readLine()) != null) {
                String[] staffInfo = line.split(cvsSplitBy);
                if (staffInfo.length >= 5) {
                    String ID = staffInfo[1];
                    String name = staffInfo[2];
                    String contactInfo = staffInfo[3];
                    if (staffInfo[0].equals("Doctor")) {
                        List<String> specialties = Arrays.asList(Arrays.copyOfRange(staffInfo, 4, staffInfo.length));
                        Doctor doctor = new Doctor(ID, name, contactInfo, specialties);
                        doctors.add(doctor);
                        service.addMedicalStaff(doctor);
                    } else if (staffInfo[0].equals("Nurse") && staffInfo.length == 5) {
                        int experienceYears = Integer.parseInt(staffInfo[4]);
                        Nurse nurse = new Nurse(ID, name, contactInfo, experienceYears);
                        nurses.add(nurse);
                        service.addMedicalStaff(nurse);
                    }
                } else if(staffInfo[0].equals("Doctor") && staffInfo.length == 4){
                    String ID = staffInfo[1];
                    String name = staffInfo[2];
                    String contactInfo = staffInfo[3];
                    Doctor doctor = new Doctor(ID, name, contactInfo);
                    doctors.add(doctor);
                    service.addMedicalStaff(doctor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Încarcă datele despre clienți din fișierul CSV și le adaugă în serviciul de cabinet medical.
     *
     * @param service  Serviciul de cabinet medical
     * @param clients  Vectorul în care să fie adăugați clienții încărcați
     */
    private void loadClientData(MedicalOfficeService service, Vector<Client> clients) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(String.valueOf(Paths.CLIENT_CSV)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] clientInfo = line.split(cvsSplitBy);
                String ID = clientInfo[0];
                String name = clientInfo[1];
                int age = Integer.parseInt(clientInfo[2]);
                String diagnosis = clientInfo[3];
                double debt = Double.parseDouble(clientInfo[4]);
                Client client = new Client(ID, name, age, diagnosis, debt);
                clients.add(client);
                service.addClient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Încarcă datele privind programările din fișierul CSV.
     *
     * @param service      Serviciul de management al cabinetului medical
     * @param doctors      Lista de medici disponibili
     * @param clients      Lista de clienți înregistrați
     * @param appointments Lista de programări
     */
    private void loadAppointmentData(MedicalOfficeService service, Vector<Doctor> doctors, Vector<Client> clients, Vector<Appointment> appointments) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(String.valueOf(Paths.APPOINTMENT_CSV)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] appointmentInfo = line.split(cvsSplitBy);
                if (appointmentInfo.length == 6) {
                    // Extrage informațiile despre programare
                    String ID = appointmentInfo[0];
                    String doctorName = appointmentInfo[1];
                    Doctor doctor = doctors.stream().filter(d -> d.getName().equals(doctorName)).findFirst().orElse(null);
                    if (doctor == null) {
                        System.out.println("Could not find doctor with name " + doctorName);
                        continue;
                    }
                    String clientName = appointmentInfo[2];
                    Client client = clients.stream().filter(c -> c.getName().equals(clientName)).findFirst().orElse(null);
                    if (client == null) {
                        System.out.println("Could not find client with name " + clientName);
                        continue;
                    }
                    String date = appointmentInfo[3];
                    String time = appointmentInfo[4];
                    String description = appointmentInfo[5];

                    // Creează obiectul Appointment și adaugă-l în listă
                    Appointment appointment = new Appointment(ID, doctor, client, date, time, description);
                    appointments.add(appointment);
                    service.addAppointment(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Metoda loadBankData încarcă datele băncii dintr-un fișier CSV.
     *
     * @param bank obiectul de tip Bank în care se vor încărca datele
     */
    private void loadBankData(Bank bank) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(String.valueOf(Paths.BANK_CSV)));
            String line = br.readLine();
            if (line != null) {
                double bankAmount = Double.parseDouble(line);
                bank.setBank(bankAmount);
            } else {
                System.out.println("The bank CSV file is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Metoda loadMedicalRecords încarcă înregistrările medicale pentru fiecare client dintr-un fișier CSV.
     *
     * @param service obiectul de tip MedicalOfficeService în care se vor încărca înregistrările medicale
     * @param records maparea între clienți și înregistrări medicale
     * @param clients lista de clienți
     */
    public void loadMedicalRecords(MedicalOfficeService service, Map<Client, MedicalRecord> records, Vector<Client> clients) {
        for (Client client : clients) {
            MedicalRecord medicalRecord = loadRecordFromCSV(client);
            if (medicalRecord != null) {
                records.put(client, medicalRecord);
            }
        }
        service.setMedicalRecords(records);
    }

    /**
     * Citește un fișier CSV cu medicamente și returnează o listă de obiecte Medicine.
     *
     * @param folderPath Calea către directorul în care se află fișierul CSV.
     * @param fileName Numele fișierului CSV.
     * @return Lista de obiecte Medicine.
     */
    public List<Medicine> readCSVFileMedication(String folderPath, String fileName) {
        List<Medicine> medications = new ArrayList<>();
        String filePath = folderPath + File.separator + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                String dosage = data[2].trim();
                String prescriptionFor = data[3].trim();
                int year = Integer.parseInt(data[1].trim());
                String duration = data[4].trim();

                Medicine medicine = new Medicine(name, year, dosage, prescriptionFor, duration);
                medications.add(medicine);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return medications;
    }

    /**
     * Încarcă fișierele CSV asociate unui client și creează un obiect MedicalRecord pe baza acestor informații.
     *
     * @param client Clientul pentru care se încarcă înregistrarea medicală
     * @return Obiectul MedicalRecord încărcat din fișierele CSV sau null în caz de eroare
     */
    private MedicalRecord loadRecordFromCSV(Client client) {
        String clientFolderPath = Paths.BASE_PATH + "\\" + client.getName();
        MedicalRecord medicalRecord = new MedicalRecord();

        try {
            List<String> history = readCSVFile(clientFolderPath, "history.csv");
            List<String> allergies = readCSVFile(clientFolderPath, "allergies.csv");
            List<Medicine> medications = readCSVFileMedication(clientFolderPath, "medications.csv");

            medicalRecord.setMedicalHistory(history);
            medicalRecord.setAllergies(allergies);
            medicalRecord.setMedications(medications);

            return medicalRecord;
        } catch (IOException e) {
            System.out.println("Eroare la încărcarea înregistrării medicale pentru clientul " + client.getName() + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Aceasta este o metodă pentru citirea unui fișier CSV.
     *
     * @param folderPath Calea către directorul în care se află fișierul CSV.
     * @param fileName   Numele fișierului CSV.
     * @return Lista de șiruri de caractere reprezentând datele citite din fișierul CSV.
     * @throws IOException în caz de eroare la citirea fișierului CSV.
     */
    private List<String> readCSVFile(String folderPath, String fileName) throws IOException {
        File file = new File(folderPath, fileName);
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        }

        return data;
    }

    /**
     * Aceasta este o metodă pentru crearea unui director de înregistrări pentru un client.
     *
     * @param clientName     Numele clientului.
     * @param medicalRecord  Obiectul de tip MedicalRecord care conține înregistrările medicale.
     */
    public void createRecordFolder(String clientName, MedicalRecord medicalRecord) {
        String clientFolderPath = Paths.BASE_PATH + "\\" + clientName;
        File folder = new File(clientFolderPath);

        if (!folder.exists()) {
            if (folder.mkdirs()) {
            } else {
                System.out.println("Failed to create client folder: " + clientFolderPath);
                return;
            }
        }

        createCSVFile(clientFolderPath, "history.csv", medicalRecord.getMedicalHistory());
        createCSVFile(clientFolderPath, "allergies.csv", medicalRecord.getAllergies());
        List<String> medicationData = new ArrayList<>();
        for (Medicine medicine : medicalRecord.getMedications()) {
            String medicationEntry = String.format("%s,%d,%s,%s,%s",
                    medicine.getName(), medicine.getYear(), medicine.getDosage(),
                    medicine.getPrescriptionFor(), medicine.getDuration());
            medicationData.add(medicationEntry);
        }
        createCSVFile(clientFolderPath, "medications.csv", medicationData);
    }

    /**
     * Creează un folder pentru înregistrarea unui client și creează fișierele CSV corespunzătoare.
     *
     * @param clientName Numele clientului pentru care se creează folderul de înregistrare
     */
    public void createRecordFolder(String clientName) {
        String clientFolderPath = Paths.BASE_PATH + "\\" + clientName;
        File folder = new File(clientFolderPath);

        if (!folder.exists()) {
            if (folder.mkdirs()) {
                // Folderul pentru client a fost creat cu succes
            } else {
                System.out.println("Eroare la crearea folderului pentru client: " + clientFolderPath);
                return;
            }
        }

        createCSVFile(clientFolderPath, "history.csv");
        createCSVFile(clientFolderPath, "allergies.csv");
        createCSVFile(clientFolderPath, "medications.csv");
    }


    /**
     * Creează un fișier CSV în calea specificată cu datele furnizate.
     *
     * @param folderPath Calea către directorul în care se va crea fișierul CSV
     * @param fileName   Numele fișierului CSV
     * @param data       Lista de șiruri de caractere reprezentând datele de scris în fișier
     */
    private void createCSVFile(String folderPath, String fileName, List<String> data) {
        File file = new File(folderPath, fileName);

        try (FileWriter writer = new FileWriter(file)) {
            for (String entry : data) {
                writer.write(entry + "\n");
            }
        } catch (IOException e) {
            System.out.println("Nu s-a putut crea fișierul CSV: " + file.getAbsolutePath());
        }
    }

    /**
     * Creează un fișier CSV gol în calea specificată.
     *
     * @param folderPath Calea către directorul în care se va crea fișierul CSV
     * @param fileName   Numele fișierului CSV
     */
    private void createCSVFile(String folderPath, String fileName) {
        File file = new File(folderPath, fileName);

        try (FileWriter writer = new FileWriter(file)) {
            // Nu se adaugă nicio instrucțiune pentru a crea un fișier gol
        } catch (IOException e) {
            System.out.println("Nu s-a putut crea fișierul CSV: " + file.getAbsolutePath());
        }
    }


}
