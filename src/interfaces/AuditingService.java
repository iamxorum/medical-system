package interfaces;

import classes.Appointment;
import classes.Client;
import classes.MedicalStaff;

import java.util.List;

/**
 * Serviciul de auditare pentru înregistrarea acțiunilor din cadrul sistemului.
 * @author Andrei-Stefanel Murariu {@iamxorum} | Petru-Serban Floristeanu
 * @version 1.0
 */
public interface AuditingService {
    /**
     * Înregistrează adăugarea unui doctor în sistem.
     *
     * @param ID           ID-ul doctorului
     * @param name         numele doctorului
     * @param contactInfo  informații de contact ale doctorului
     * @param specialties  specializările doctorului
     */
    void logInsertDoctor(String ID, String name, String contactInfo, List<String> specialties);

    /**
     * Înregistrează adăugarea unui doctor în sistem.
     *
     * @param ID           ID-ul doctorului
     * @param name         numele doctorului
     * @param contactInfo  informații de contact ale doctorului
     */
    void logInsertDoctor(String ID, String name, String contactInfo);

    /**
     * Înregistrează adăugarea unei asistente medicale în sistem.
     *
     * @param ID           ID-ul asistentei medicale
     * @param name         numele asistentei medicale
     * @param contactInfo  informații de contact ale asistentei medicale
     * @param exp          experiența asistentei medicale în ani
     */
    void logInsertNurse(String ID, String name, String contactInfo, int exp);

    /**
     * Înregistrează adăugarea unui client în sistem.
     *
     * @param ID         ID-ul clientului
     * @param name       numele clientului
     * @param age        vârsta clientului
     * @param diagnosis  diagnosticul clientului
     */
    void logInsertClient(String ID, String name, int age, String diagnosis);

    /**
     * Înregistrează acțiunea de salvare a datelor.
     */
    void logSaveDataAction();

    /**
     * Înregistrează acțiunea de încărcare a datelor.
     */
    void logLoadDataAction();

    /**
     * Înregistrează acțiunea de încărcare a datelor la pornirea sistemului.
     */
    void logLoadDataActionAtStart();

    /**
     * Înregistrează adăugarea unei programări în sistem.
     *
     * @param app         programarea adăugată
     * @param staff       personalul medical responsabil de programare
     * @param client      clientul pentru care este programată consultația
     * @param date        data programării
     * @param description descrierea programării
     */
    void logInsertAppointment(Appointment app, MedicalStaff staff, Client client, String date, String description);

    /**
     * Înregistrează ștergerea unui client din sistem.
     *
     * @param client clientul șters
     */
    void logDeleteClient(Client client);

    /**
     * Înregistrează ștergerea unui membru al personalului medical din sistem.
     *
     * @param staff membrul personalului medical șters
     */
    void logDeleteStaff(MedicalStaff staff);

    /**
     * Înregistrează ștergerea unei programări din sistem.
     *
     * @param app programarea ștearsă
     */
    void logDeleteAppointment(Appointment app);

    /**
     * Înregistrează finalizarea unei programări și costul acesteia.
     *
     * @param app            programarea finalizată
     * @param appointmentCost costul programării
     */
    void logCompletedAppointment(Appointment app, double appointmentCost);

    /**
     * Înregistrează efectuarea unui plăți pentru un client.
     *
     * @param client         clientul pentru care se efectuează plata
     * @param paymentAmount  suma plătită
     */
    void logPayment(Client client, double paymentAmount);

    /**
     * Înregistrează actualizarea înregistrării unui client.
     *
     * @param client clientul ale cărui înregistrări sunt actualizate
     */
    void logClientRecordUpdate(Client client);
}
