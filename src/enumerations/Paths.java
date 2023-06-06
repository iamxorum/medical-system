/*
 * *
 *  * *
 *  * Copyright (c) 2023 Andrei-Stefanel Murariu (@iamxorum) și Petru-Serban Floristeanu
 *  *
 *  * Permisiunea este acordată gratuit oricărei persoane care obține o copie
 *  * a acestui software și a fișierelor de documentație asociate (denumite în continuare "Software"), de a trata
 *  * acest Software fără restricții, inclusiv, dar fără a se limita la, drepturile de
 *  * utilizare, copiere, modificare, unire, publicare, distribuire, sublicențiere și/sau vânzare
 *  * a copiilor Software-ului și de a permite persoanelor cărora Software-ul le este
 *  * furnizat să facă acest lucru, cu condiția să respecte următoarele condiții:
 *  *
 *  * Nota de drepturi de autor și această notificare de permisiune trebuie incluse în toate
 *  * copiile sau porțiuni semnificative ale Software-ului.
 *  *
 *  * SOFTWARE-UL ESTE OFERIT "CA ATARE", FĂRĂ NICIUN FEL DE GARANȚIE, EXPLICITĂ SAU IMPLICITĂ,
 *  * INCLUSIV, DAR FĂRĂ A SE LIMITA LA GARANȚIILE DE COMERCIALIZARE, ADECVARE PENTRU UN ANUMIT SCOP ȘI
 *  * NEÎNCĂLCARE A DREPTURILOR DE AUTOR. ÎN NICIUN CAZ AUTORII SAU DEȚINĂTORII DREPTURILOR DE AUTOR
 *  * NU VOR FI RĂSPUNZĂTORI PENTRU ORICE PRETENȚIE, DAUNE SAU ALTE RĂSPUNDERI, INDIFERENT DACĂ
 *  * ESTE O ACȚIUNE CONTRACTUALĂ, DELICTUALĂ SAU ORICE ALTĂ FORMĂ, REZULTATĂ DIN SAU ÎN LEGĂTURĂ
 *  * CU SOFTWARE-UL SAU UTILIZAREA SAU ALTE ACTIVITĂȚI ÎN SOFTWARE.
 *
 *
 */

package enumerations;

/**
 * Aceasta enumerare reprezinta calea catre csv-uri.
 *
 * <p>
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 * </p>
 */
public enum Paths {
    BASE_PATH("src/csv_input/medicalRecords"),
    MEDICAL_STAFF_CSV("src/csv_input/medicalStaffInput.csv"),
    DOCTOR_CSV("src/csv_input/doctor.csv"),
    NURSE_CSV("src/csv_input/nurse.csv"),
    CLIENT_CSV("src/csv_input/clientInput.csv"),
    APPOINTMENT_CSV("src/csv_input/appointments.csv"),
    BANK_CSV("src/csv_input/bank.csv"),
    AUDIT_CSV("src/csv_input/audit_log.csv");
    private final String csv_paths;
    /**
     * Construieste un obiect Paths cu calea csv-urilor.
     *
     * @param csv_paths calea csv-urilor
     */
    Paths(String csv_paths) {
        this.csv_paths = csv_paths;
    }

    /**
     * Returneaza calea csv-urilor.
     *
     * @return calea csv-urilor
     */
    @Override
    public String toString() {
        return csv_paths;
    }
}
