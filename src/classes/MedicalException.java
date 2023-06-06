package classes;

/**
 * Excepție personalizată pentru probleme medicale.
 *
 * <p>
 * Această clasă extinde clasa {@code Exception} și este utilizată pentru a semnala erori specifice din domeniul medical.
 * </p>
 *
 * <p>
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 * </p>
 */
public class MedicalException extends Exception {
    /**
     * Constructor pentru crearea unei instanțe de excepție cu un mesaj specific.
     *
     * @param message mesajul care descrie problema medicală
     */
    public MedicalException(String message) {
        super(message);
    }
}
