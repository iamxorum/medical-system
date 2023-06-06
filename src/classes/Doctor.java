package classes;

import enumerations.Colors;
import enumerations.MedicalStaffType;

import java.util.List;

/**
 * Clasa reprezentând un doctor dintr-un personal medical.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class Doctor extends MedicalStaff {
    private final List<String> specialties;

    /**
     * Constructorul implicit al clasei Doctor.
     * Initializează specialitățile cu valoarea null.
     */
    public Doctor() {
        super("####", "###", MedicalStaffType.DOCTOR, "####");
        this.specialties = null;
    }

    /**
     * Constructorul clasei Doctor cu parametri.
     *
     * @param ID           ID-ul doctorului
     * @param name         numele doctorului
     * @param contactInfo  informațiile de contact ale doctorului
     * @param specialties  lista specialităților doctorului
     */
    public Doctor(String ID, String name, String contactInfo, List<String> specialties) {
        super(ID, name, MedicalStaffType.DOCTOR, contactInfo);
        this.specialties = specialties;
    }

    /**
     * Constructorul clasei Doctor cu parametri, fără specialități.
     *
     * @param ID           ID-ul doctorului
     * @param name         numele doctorului
     * @param contactInfo  informațiile de contact ale doctorului
     */
    public Doctor(String ID, String name, String contactInfo) {
        super(ID, name, MedicalStaffType.DOCTOR, contactInfo);
        this.specialties = null;
    }

    /**
     * Returnează lista de specialități ale doctorului.
     *
     * @return lista de specialități
     */
    public List<String> getSpecialties() {
        return specialties;
    }

    /**
     * Returnează o reprezentare textuală a obiectului Doctor.
     *
     * @return reprezentarea textuală a obiectului
     */
    @Override
    public String toString() {
        return getID() + " | " + getName() + " | " + getContactInfo() + " | " + specialties + ";";
    }

    /**
     * Afisează informațiile despre doctor.
     */
    @Override
    public void printInfo() {
        System.out.println(Colors.YELLOW + "* ID: " + Colors.RESET + getID());
        System.out.println(Colors.YELLOW + "* Name: " + Colors.RESET + getName());
        System.out.println(Colors.YELLOW + "* Contact Info: " + Colors.RESET + getContactInfo());
        System.out.println(Colors.YELLOW + "* Speciality: " + Colors.RESET + getSpeciality());
        if (getSpecialties() != null && !getSpecialties().isEmpty()) {
            System.out.println(Colors.YELLOW + "* Specialties: " + Colors.RESET);
            for (String specialty : getSpecialties()) {
                System.out.println("    - " + specialty);
            }
        }
        System.out.println(Colors.LIGHT_BLUE + "*********************************" + Colors.RESET);
    }
}
