package classes;

import enumerations.Colors;

/**
 * Clasa Bank reprezintă o implementare simplă a unei bănci.
 * Această clasă utilizează un design Singleton și stochează o valoare reprezentând suma disponibilă în bancă.
 * Aceasta oferă metode pentru a obține valoarea băncii, a seta valoarea băncii și a afișa suma actuală în consolă.
 *
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 */
public class Bank {
    private static Bank instance = null;
    private double bank;

    private Bank(){
        bank = 0.0;
    }

    /**
     * Metoda getInstance returnează instanța Singleton a clasei Bank.
     * Dacă instanța nu a fost creată încă, aceasta va fi creată în momentul apelului.
     *
     * @return Instanța Singleton a clasei Bank.
     */
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    /**
     * Metoda getBank returnează valoarea curentă a băncii.
     *
     * @return Valoarea curentă a băncii.
     */
    public double getBank() {
        return bank;
    }

    /**
     * Metoda setBank setează valoarea băncii.
     *
     * @param bank Noua valoare pentru bancă.
     */
    public void setBank(double bank) {
        this.bank = bank;
    }

    /**
     * Metoda bankAmount afișează suma actuală din bancă în consolă.
     *
     * @param bank Valoarea curentă a băncii.
     */
    public void bankAmount(double bank){
        System.out.println(Colors.LIGHT_BLUE + "--------------------------");
        System.out.println("* Suma din bancă este: " + Colors.YELLOW + bank + Colors.LIGHT_BLUE);
        System.out.println("--------------------------" + Colors.RESET);
    }
}
