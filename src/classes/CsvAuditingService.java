package classes;

import enumerations.Paths;
import interfaces.AuditingService;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clasa CsvAuditingService implementează interfața AuditingService și este responsabilă pentru stocarea acțiunilor de audit într-un fișier CSV.
 * Implementează interfața {@link AuditingService}.</p>
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class CsvAuditingService implements AuditingService {
    private static CsvAuditingService instance = null;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructorul clasei CsvAuditingService.
     * Inițializează fișierul CSV cu un rând antet.
     */
    private CsvAuditingService() {
        try {
            FileWriter writer = new FileWriter(String.valueOf(Paths.AUDIT_CSV), true);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error initializing CSV file: " + e.getMessage());
        }
    }

    /**
     * Returnează o singură instanță a clasei CsvAuditingService utilizând modelul Singleton.
     *
     * @return instanța unică a clasei CsvAuditingService
     */
    public static CsvAuditingService getInstance() {
        if (instance == null) {
            instance = new CsvAuditingService();
        }
        return instance;
    }

    /**
     * Înregistrează o acțiune de inserare a unui doctor în fișierul de audit.
     *
     * @param ID           ID-ul doctorului
     * @param name         numele doctorului
     * @param contactInfo  informațiile de contact ale doctorului
     * @param specialties  lista de specializări ale doctorului
     */
    @Override
    public void logInsertDoctor(String ID, String name, String contactInfo, List<String> specialties) {
        String action = "Inserted Doctor: { " + ID + ", " + name + ", " + contactInfo + ", " + specialties + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de inserare a unui doctor în fișierul de audit.
     *
     * @param ID          ID-ul doctorului
     * @param name        numele doctorului
     * @param contactInfo informațiile de contact ale doctorului
     */
    public void logInsertDoctor(String ID, String name, String contactInfo) {
        String action = "Inserted Doctor: { " + ID + ", " + name + ", " + contactInfo + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de inserare a unei asistente medicale în fișierul de audit.
     *
     * @param ID          ID-ul asistentei medicale
     * @param name        numele asistentei medicale
     * @param contactInfo informațiile de contact ale asistentei medicale
     * @param exp         ani de experiență ai asistentei medicale
     */
    @Override
    public void logInsertNurse(String ID, String name, String contactInfo, int exp) {
        String action = "Inserted Nurse: { " + ID + ", " + name + ", " + contactInfo + ", " + exp + " years of experience }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de inserare a unui client în fișierul de audit.
     *
     * @param ID        ID-ul clientului
     * @param name      numele clientului
     * @param age       vârsta clientului
     * @param diagnosis diagnosticul clientului
     */
    @Override
    public void logInsertClient(String ID, String name, int age, String diagnosis) {
        String action = "Inserted Client: { " + ID + ", " + name + ", " + age + " years old, " + diagnosis + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de salvare a datelor în fișierul de audit.
     */
    @Override
    public void logSaveDataAction() {
        String timestamp = dateFormat.format(new Date());
        writeLineToFile("Saved data | " + timestamp + "\n");
    }

    /**
     * Înregistrează o acțiune de încărcare a datelor la pornirea aplicației în fișierul de audit.
     */
    @Override
    public void logLoadDataActionAtStart() {
        String timestamp = dateFormat.format(new Date());
        writeLineToFile("Loaded data | " + timestamp + "\n");
    }

    /**
     * Înregistrează o acțiune de reîncărcare a datelor în fișierul de audit.
     */
    @Override
    public void logLoadDataAction() {
        String timestamp = dateFormat.format(new Date());
        writeLineToFile("Reloaded data | " + timestamp + "\n");
    }

    /**
     * Înregistrează o acțiune de inserare a unei programări în fișierul de audit.
     *
     * @param app         programarea
     * @param staff       personalul medical
     * @param client      clientul
     * @param date        data programării
     * @param description descrierea programării
     */
    @Override
    public void logInsertAppointment(Appointment app, MedicalStaff staff, Client client, String date, String description) {
        String action = "Inserted Appointment: { " + app.getID() + ", " + staff.getName() + ", " + client.getName() + ", " + date + ", " + description + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de ștergere a unui client din fișierul de audit.
     *
     * @param client clientul
     */
    @Override
    public void logDeleteClient(Client client) {
        String action = "Deleted Client: { " + client.getID() + ", " + client.getName() + ", " + client.getAge() + " years old, " + client.getDiagnosis() + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de ștergere a unui membru al personalului medical din fișierul de audit.
     *
     * @param staff membrul personalului medical
     */
    @Override
    public void logDeleteStaff(MedicalStaff staff) {
        String action = "Deleted Staff: { " + staff.getID() + ", " + staff.getName() + ", " + staff.getSpeciality() + ", " + staff.getContactInfo() + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de ștergere a unei programări din fișierul de audit.
     *
     * @param app programarea
     */
    @Override
    public void logDeleteAppointment(Appointment app) {
        String action = "Deleted Appointment: { " + app.getID() + ", " + app.getMedicalStaff().getName() + ", " + app.getClient().getName() + ", " + app.getDate() + ", " + app.getDescription() + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de finalizare a unei programări în fișierul de audit.
     *
     * @param app            programarea
     * @param appointmentCost costul programării
     */
    @Override
    public void logCompletedAppointment(Appointment app, double appointmentCost) {
        String action = "Completed Appointment: { " + app.getID() + ", " + app.getMedicalStaff().getName() + ", " + app.getClient().getName() + ", " + app.getDate() + ", " + app.getDescription() + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | Cost: $" + appointmentCost + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de plată în fișierul de audit.
     *
     * @param client        clientul
     * @param paymentAmount suma plătită
     */
    public void logPayment(Client client, double paymentAmount) {
        String action = "Payment: { Client: " + client.getName() + ", ID: " + client.getID() + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | Payment Amount: $" + paymentAmount + " | " + timestamp + "\n";
        writeLineToFile(line);
    }

    /**
     * Înregistrează o acțiune de actualizare a înregistrării unui client în fișierul de audit.
     *
     * @param client clientul
     */
    @Override
    public void logClientRecordUpdate(Client client) {
        String action = "Updated Client Record: { Client: " + client.getName() + ", ID: " + client.getID() + " }";
        String timestamp = dateFormat.format(new Date());
        String line = action + " | Medical Records updated! | " + timestamp + "\n";
        writeLineToFile(line);
    }

    private void writeLineToFile(String line) {
        try {
            FileWriter writer = new FileWriter(String.valueOf(Paths.AUDIT_CSV), true);
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
