package classes;

import enumerations.Colors;
import enumerations.MedicalStaffType;

/**
 * Clasa Nurse reprezintă o subclasă a personalului medical de tip asistentă.
 * Această clasă extinde clasa MedicalStaff.
 *
 * <p>
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 * </p>
 */
public class Nurse extends MedicalStaff {
    private final int experienceYears;

    /**
     * Creează o instanță nouă a clasei Nurse cu valorile implicite.
     * Experiența este inițializată cu 0 ani.
     */
    public Nurse() {
        super("####", "####", MedicalStaffType.NURSE, "####");
        this.experienceYears = 0;
    }

    /**
     * Creează o instanță nouă a clasei Nurse cu valorile specificate.
     *
     * @param ID             ID-ul asistentei
     * @param name           numele asistentei
     * @param contactInfo    informațiile de contact ale asistentei
     * @param experienceYears ani de experiență ai asistentei
     */
    public Nurse(String ID, String name, String contactInfo, int experienceYears) {
        super(ID, name, MedicalStaffType.NURSE, contactInfo);
        this.experienceYears = experienceYears;
    }

    /**
     * Returnează ani de experiență ai asistentei.
     *
     * @return ani de experiență
     */
    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public String toString() {
        return getID() + " | " + getName() + " | " + getContactInfo() + " | " + experienceYears + " experience years;";
    }

    @Override
    public void printInfo() {
        System.out.println(Colors.YELLOW + "* ID: " + Colors.RESET + getID());
        System.out.println(Colors.YELLOW + "* Name: " + Colors.RESET + getName());
        System.out.println(Colors.YELLOW + "* Contact Info: " + Colors.RESET + getContactInfo());
        System.out.println(Colors.YELLOW + "* Speciality: " + Colors.RESET + getSpeciality());
        System.out.println(Colors.YELLOW + "* Years of Experience: " + Colors.RESET + getExperienceYears());
        System.out.println(Colors.LIGHT_BLUE + "*********************************" + Colors.RESET);
    }
}
