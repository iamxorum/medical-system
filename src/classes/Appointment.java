package classes;

import enumerations.Colors;

import java.util.Objects;

/**
 * Clasa Appointment reprezintă o programare în cadrul unui sistem medical.
 * Aceasta conține informații despre ID-ul programării, data, ora, descrierea, clientul și personalul medical implicat.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class Appointment {
    private String ID;
    private final String date;
    private final String time;
    private final String description;
    private final Client client;
    private final MedicalStaff medicalStaff;

    /**
     * Constructorul clasei Appointment.
     *
     * @param ID             ID-ul programării.
     * @param medicalStaff   Personalul medical implicat în programare.
     * @param client         Clientul care a făcut programarea.
     * @param date           Data programării.
     * @param time           Ora programării.
     * @param description    Descrierea programării.
     */
    public Appointment(String ID, MedicalStaff medicalStaff, Client client, String date, String time, String description) {
        this.ID = ID;
        this.date = date;
        this.time = time;
        this.client = client;
        this.description = description;
        this.medicalStaff = medicalStaff;
    }

    /**
     * Returnează data programării.
     *
     * @return Data programării.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returnează ora programării.
     *
     * @return Ora programării.
     */
    public String getTime() {
        return time;
    }

    /**
     * Returnează descrierea programării.
     *
     * @return Descrierea programării.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returnează clientul care a făcut programarea.
     *
     * @return Clientul care a făcut programarea.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returnează personalul medical implicat în programare.
     *
     * @return Personalul medical implicat în programare.
     */
    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    /**
     * Returnează ID-ul programării.
     *
     * @return ID-ul programării.
     */
    public String getID() {
        return ID;
    }

    /**
     * Setează ID-ul programării.
     *
     * @param ID ID-ul programării.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Verifică dacă o altă obiect de tip Appointment este egal cu obiectul curent.
     *
     * @param o Obiectul de comparat.
     * @return true dacă obiectul este egal cu obiectul curent, false în caz contrar.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(ID, that.ID) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(description, that.description) && Objects.equals(client, that.client) && Objects.equals(medicalStaff, that.medicalStaff);
    }

    /**
     * Returnează valoarea hash a obiectului.
     *
     * @return Valoarea hash a obiectului.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID, date, time, description, client, medicalStaff);
    }

    /**
     * Afișează informațiile despre programare în consolă.
     */
    public void printInfo() {
        System.out.println(Colors.YELLOW + "* ID: " + Colors.RESET + getID());
        System.out.println(Colors.YELLOW + "* Data: " + Colors.RESET + getDate());
        System.out.println(Colors.YELLOW + "* Ora: " + Colors.RESET + getTime());
        System.out.println(Colors.GREEN + "* Client: " + Colors.RESET + client);
        System.out.println(Colors.LIGHT_BLUE + "* Personal: " + Colors.RESET + medicalStaff);
        System.out.println(Colors.YELLOW + "* Descriere: " + Colors.RESET + description);
        System.out.println(Colors.LIGHT_BLUE + "*********************************" + Colors.RESET);
    }

    /**
     * Returnează o reprezentare textuală a obiectului.
     *
     * @return Reprezentarea textuală a obiectului.
     */
    @Override
    public String toString() {
        return getID() + "\n" + getDate() + "\n" + getTime() + "\n" + medicalStaff + "\n" + client + "\n" + description;
    }
}
