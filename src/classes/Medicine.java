package classes;

/**
 * Clasa reprezentând un medicament.
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class Medicine {
    private String name;
    private int year;
    private String dosage;
    private String prescriptionFor;
    private String duration;

    /**
     * Constructor pentru obiectul Medicine.
     *
     * @param name            Numele medicamentului
     * @param year            Anul
     * @param dosage          Doza
     * @param prescriptionFor Prescripția pentru
     * @param duration        Durata administrării
     */
    public Medicine(String name, int year, String dosage, String prescriptionFor, String duration) {
        this.name = name;
        this.year = year;
        this.dosage = dosage;
        this.prescriptionFor = prescriptionFor;
        this.duration = duration;
    }

    /**
     * Returnează numele medicamentului.
     *
     * @return Numele medicamentului
     */
    public String getName() {
        return name;
    }

    /**
     * Setează numele medicamentului.
     *
     * @param name Numele medicamentului
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returnează anul.
     *
     * @return Anul
     */
    public int getYear() {
        return year;
    }

    /**
     * Setează anul.
     *
     * @param year Anul
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returnează doza.
     *
     * @return Doza
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Setează doza.
     *
     * @param dosage Doza
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Returnează prescripția pentru.
     *
     * @return Prescripția pentru
     */
    public String getPrescriptionFor() {
        return prescriptionFor;
    }

    /**
     * Setează prescripția pentru tratament.
     *
     * @param prescriptionFor Prescripția pentru
     */
    public void setPrescriptionFor(String prescriptionFor) {
        this.prescriptionFor = prescriptionFor;
    }

    /**
     * Returnează durata administrării.
     *
     * @return Durata administrării
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Setează durata administrării.
     *
     * @param duration Durata administrării
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
