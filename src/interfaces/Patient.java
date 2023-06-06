package interfaces;

import enumerations.Colors;

/**
 * Interfața care definește comportamentul unui pacient.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum} | Petru-Serban Floristeanu
 * @version 1.0
 */
public interface Patient {
    /**
     * Returnează ID-ul pacientului.
     *
     * @return ID-ul pacientului
     */
    String getID();

    /**
     * Setează ID-ul pacientului.
     *
     * @param ID ID-ul pacientului
     */
    void setID(String ID);

    /**
     * Returnează numele pacientului.
     *
     * @return numele pacientului
     */
    String getName();

    /**
     * Returnează vârsta pacientului.
     *
     * @return vârsta pacientului
     */
    int getAge();

    /**
     * Returnează diagnosticul pacientului.
     *
     * @return diagnosticul pacientului
     */
    String getDiagnosis();

    /**
     * Returnează datoria pacientului.
     *
     * @return datoria pacientului
     */
    double getDebt();

    /**
     * Afișează informațiile pacientului.
     */
    default void printInfo() {
        System.out.println(Colors.YELLOW + "* ID: " + Colors.RESET + getID());
        System.out.println(Colors.YELLOW + "* Name: " + Colors.RESET + getName());
        System.out.println(Colors.YELLOW + "* Age: " + Colors.RESET + getAge());
        System.out.println(Colors.YELLOW + "* Diagnosis: " + Colors.RESET + getDiagnosis());
        System.out.println(Colors.RED + "* Debt: " + Colors.RESET + getDebt());
        System.out.println(Colors.LIGHT_BLUE + "*********************************" + Colors.RESET);
    }
}
