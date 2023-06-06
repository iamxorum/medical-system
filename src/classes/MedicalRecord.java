package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clasa care reprezinta un dosar medical.
 * Contine informatii despre istoricul medical, medicamentele si alergiile pacientului.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class MedicalRecord {
    private List<String> medicalHistory;
    private List<Medicine> medications;
    private List<String> allergies;

    /**
     * Constructorul clasei MedicalRecord.
     * Initializeaza listele pentru istoricul medical, medicamente si alergii.
     */
    public MedicalRecord() {
        medicalHistory = new ArrayList<>();
        medications = new ArrayList<>();
        allergies = new ArrayList<>();
    }

    /**
     * Getter pentru istoricul medical.
     *
     * @return Lista de string-uri reprezentand istoricul medical.
     */
    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Getter pentru medicamentele pacientului.
     *
     * @return Lista de obiecte de tip Medicine reprezentand medicamentele pacientului.
     */
    public List<Medicine> getMedications() {
        return medications;
    }

    /**
     * Getter pentru alergiile pacientului.
     *
     * @return Lista de string-uri reprezentand alergiile pacientului.
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Setter pentru istoricul medical.
     *
     * @param medicalHistory Lista de string-uri reprezentand istoricul medical.
     */
    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * Setter pentru medicamentele pacientului.
     *
     * @param medications Lista de obiecte de tip Medicine reprezentand medicamentele pacientului.
     */
    public void setMedications(List<Medicine> medications) {
        this.medications = medications;
    }

    /**
     * Setter pentru alergiile pacientului.
     *
     * @param allergies Lista de string-uri reprezentand alergiile pacientului.
     */
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    /**
     * Suprascrierea metodei equals pentru compararea a doua obiecte de tip MedicalRecord.
     *
     * @param o Obiectul de comparat cu instanta curenta.
     * @return true daca obiectele sunt egale, false in caz contrar.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord that = (MedicalRecord) o;
        return Objects.equals(medicalHistory, that.medicalHistory) && Objects.equals(medications, that.medications) && Objects.equals(allergies, that.allergies);
    }

    /**
     * Suprascrierea metodei hashCode pentru calcularea codului hash al instantei.
     *
     * @return Codul hash al instantei.
     */
    @Override
    public int hashCode() {
        return Objects.hash(medicalHistory, medications, allergies);
    }

    /**
     * Suprascrierea metodei toString pentru reprezentarea sub forma de string a instantei.
     *
     * @return String-ul care contine istoricul medical, alergiile si medicamentele sub forma de lista.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(medicalHistory).append(" | ");
        sb.append(allergies).append(" | ");

        // Iterate over the medications list and retrieve the name of each medicine
        for (Medicine medicine : medications) {
            sb.append(medicine.getName()).append(", ");
        }

        // Remove the trailing comma and space
        if (!medications.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }

        sb.append(";");
        return sb.toString();
    }

}
