package interfaces;

import enumerations.MedicalStaffType;

/**
 * Interfața Medic reprezintă un contract pentru obiectele care reprezintă medici într-un sistem medical.
 * Aceasta oferă metode pentru obținerea informațiilor despre medic.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum} | Petru-Serban Floristeanu
 * @version 1.0
 */
public interface Medic {
    /**
     * Returnează ID-ul medicului.
     *
     * @return ID-ul medicului
     */
    String getID();

    /**
     * Setează ID-ul medicului.
     *
     * @param ID ID-ul medicului
     */
    void setID(String ID);

    /**
     * Returnează numele medicului.
     *
     * @return Numele medicului
     */
    String getName();

    /**
     * Returnează informațiile de contact ale medicului.
     *
     * @return Informațiile de contact ale medicului
     */
    String getContactInfo();

    /**
     * Returnează specialitatea medicului.
     *
     * @return Specialitatea medicului
     */
    MedicalStaffType getSpeciality();

    /**
     * Afișează informațiile despre medic.
     */
    default void printInfo() {
        System.out.println("ID: " + getID());
        System.out.println("Name: " + getName());
        System.out.println("Contact Info: " + getContactInfo());
        System.out.println("Speciality: " + getSpeciality());
        System.out.println("---------------------------------");
    }
}
