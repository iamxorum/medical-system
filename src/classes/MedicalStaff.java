package classes;

import enumerations.MedicalStaffType;
import interfaces.Medic;

import java.util.Objects;

/**
 * Clasa abstractă pentru personalul medical.
 *
 * <p>Aceasta reprezintă o clasă de bază pentru toți membrii personalului medical.
 * Implementează interfața {@link Medic}.</p>
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public abstract class MedicalStaff implements Medic {
    private String ID;
    private final String name;
    private final String contactInfo;
    private final MedicalStaffType speciality;

    /**
     * Constructor pentru obiectele de tip MedicalStaff.
     *
     * @param ID           ID-ul personalului medical
     * @param name         numele personalului medical
     * @param speciality   specializarea personalului medical
     * @param contactInfo  informații de contact ale personalului medical
     */
    public MedicalStaff(String ID, String name, MedicalStaffType speciality, String contactInfo) {
        this.ID = ID;
        this.name = name;
        this.speciality = speciality;
        this.contactInfo = contactInfo;
    }

    /**
     * Returnează numele personalului medical.
     *
     * @return numele personalului medical
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returnează informațiile de contact ale personalului medical.
     *
     * @return informațiile de contact ale personalului medical
     */
    @Override
    public String getContactInfo() {
        return this.contactInfo;
    }

    /**
     * Returnează specializarea personalului medical.
     *
     * @return specializarea personalului medical
     */
    @Override
    public MedicalStaffType getSpeciality() {
        return this.speciality;
    }

    /**
     * Returnează ID-ul personalului medical.
     *
     * @return ID-ul personalului medical
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * Setează ID-ul personalului medical.
     *
     * @param ID ID-ul personalului medical
     */
    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Verifică dacă un obiect este egal cu personalul medical curent.
     *
     * @param o obiectul de comparat
     * @return true dacă obiectele sunt egale, false în caz contrar
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalStaff that = (MedicalStaff) o;
        return ID.equals(that.ID) && name.equals(that.name) && contactInfo.equals(that.contactInfo) && speciality == that.speciality;
    }

    /**
     * Returnează valoarea hash a obiectului personalului medical.
     *
     * @return valoarea hash a obiectului
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID, name, contactInfo, speciality);
    }

    /**
     * Returnează o reprezentare textuală a obiectului personalului medical.
     *
     * @return reprezentarea textuală a obiectului
     */
    @Override
    public String toString() {
        return "MedicalStaff{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", speciality=" + speciality +
                '}';
    }
}
