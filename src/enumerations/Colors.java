package enumerations;

/**
 * Aceasta enumerare reprezinta culorile posibile pentru formatarea textului in consola, utilizand codurile ANSI.
 *
 * <p>
 * @author Andrei-Stefanel Murariu {@iamxorum}
 * @version 1.0
 * </p>
 */
public enum Colors {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    LIGHT_BLACK("\u001B[90m"),
    LIGHT_RED("\u001B[91m"),
    LIGHT_GREEN("\u001B[92m"),
    LIGHT_YELLOW("\u001B[93m"),
    LIGHT_BLUE("\u001B[94m"),
    LIGHT_MAGENTA("\u001B[95m"),
    LIGHT_CYAN("\u001B[96m"),
    LIGHT_WHITE("\u001B[97m");

    private final String code;

    /**
     * Construieste un obiect Colors cu codul ANSI corespunzator culorii.
     *
     * @param code codul ANSI al culorii
     */
    Colors(String code) {
        this.code = code;
    }

    /**
     * Returneaza codul ANSI al culorii.
     *
     * @return codul ANSI al culorii
     */
    @Override
    public String toString() {
        return code;
    }
}
