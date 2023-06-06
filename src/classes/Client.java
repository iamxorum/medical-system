package classes;

import interfaces.Patient;

import java.util.Objects;

/**
 * Clasa Client reprezintă un pacient în sistemul medical.
 * Implementează interfața {@link Patient}.</p>
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class Client implements Patient {
    private String ID;
    private final String name;
    private final String diagnosis;
    private final int age;
    private double debt;

    /**
     * Constructorul fără argumente al clasei Client.
     * Inițializează toate câmpurile cu valori implicite.
     */
    public Client(){
        this.ID = "####";
        this.name = "####";
        this.age = 0;
        this.diagnosis = "####";
        this.debt = 0.0;
    }

    /**
     * Constructorul cu argumente al clasei Client.
     * Inițializează câmpurile cu valorile specificate.
     *
     * @param ID        ID-ul pacientului
     * @param name      numele pacientului
     * @param age       vârsta pacientului
     * @param diagnosis diagnosticul pacientului
     * @param debt      datoria pacientului
     */
    public Client(String ID, String name, int age, String diagnosis, double debt){
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.debt = debt;
    }

    /**
     * Returnează ID-ul pacientului.
     *
     * @return ID-ul pacientului
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * Setează ID-ul pacientului.
     *
     * @param ID ID-ul pacientului
     */
    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Returnează numele pacientului.
     *
     * @return numele pacientului
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returnează vârsta pacientului.
     *
     * @return vârsta pacientului
     */
    @Override
    public int getAge() {
        return this.age;
    }

    /**
     * Returnează diagnosticul pacientului.
     *
     * @return diagnosticul pacientului
     */
    @Override
    public String getDiagnosis() {
        return this.diagnosis;
    }

    /**
     * Returnează datoria pacientului.
     *
     * @return datoria pacientului
     */
    @Override
    public double getDebt() {
        return debt;
    }

    /**
     * Setează datoria pacientului.
     *
     * @param debt datoria pacientului
     */
    public void setDebt(double debt) {
        this.debt = debt;
    }

    /**
     * Verifică dacă obiectul Client este egal cu un alt obiect dat.
     *
     * @param o obiectul de comparat
     * @return true dacă obiectele sunt egale, false în caz contrar
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age && Double.compare(client.debt, debt) == 0 && Objects.equals(ID, client.ID) && Objects.equals(name, client.name) && Objects.equals(diagnosis, client.diagnosis);
    }

    /**
     * Returnează valoarea hash a obiectului Client.
     *
     * @return valoarea hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID, name, diagnosis, age, debt);
    }

    /**
     * Returnează o reprezentare textuală a obiectului Client.
     *
     * @return reprezentarea textuală a obiectului
     */
    @Override
    public String toString() {
        return ID + " | " + name + " | " + age + " | " + diagnosis + " | $" + debt + ";";
    }
}
