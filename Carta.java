package blackjack;

/**
 * Un objeto de tipo Carta representa una carta de juego de un mazo de Poker estándar,
 * incluyendo Jokers. La carta tiene un palo, que puede ser espadas, corazones, diamantes,
 * clubes, o bromista. Una pala, corazón, diamante o club tiene uno de los 13 valors:
 * as, 2, 3, 4, 5, 6, 7, 8, 9, 10, jota, reina o rey. Tenga en cuenta que "as" es
 * considerado el valor más pequeño. Un comodín también puede tener un asociado
 * valor; este valor puede ser cualquier cosa y se puede utilizar para realizar un seguimiento de varios
 * diferentes jockers.
 */
public class Carta {

    public final static int ESPADAS = 0;   // Códigos para los 4 palos, más Joker.
    public final static int CORAZONES = 1;
    public final static int DIAMANTES = 2;
    public final static int TREBOLES = 3;
    public final static int JOKER = 4;

    public final static int AS = 1;      // Códigos para las tarjetas no numéricas.
    public final static int JOTA = 11;    //   Las tarjetas 2 a 10 tienen sus 
    public final static int REINA = 12;   //  valores numéricos para sus códigos.
    public final static int REY = 13;

    /**
     * El palo de esta carta, una de las constantes ESPADAS, CORAZONES, DIAMANTES,
     * TREBOLES, o JOKER. El palo no se puede cambiar después de que la tarjeta esté
     * construido.
     */
    private final int palo;

    /**
     * El valor de la carta. Para una carta normal, este es uno de los valors 1 hasta
     * 13, con 1 representando AS. Para un JOKER, el valor puede ser cualquier cosa. los
     * el valor no se puede cambiar después de que se construye la carta.
     */
    private final int valor;

    /**
     * Crea un Joker, con 1 como valor asociado. (Tenga en cuenta que "nueva tarjeta ()"
     * es equivalente a "nueva Tarjeta (1, Tarjeta.CAJA)".)
     */
    public Carta() {
        palo = JOKER;
        valor = 1;
    }

    /**
     * Crea una carta con palo y valor especificados.
     *
     * @param elValor tel valor de la nueva carta. Para una tarjeta regular (no bromista),
     * el valor debe estar en el rango de 1 a 13, donde 1 representa un As.
     * Puede usar las constantes Card.AS, Card.JOTA, Card.REINA y Card.REY.
     * Para un Joker, el valor puede ser cualquier cosa.
     * @param elPalo el palo de la nueva carta. Este debe ser uno de los valors
     * Card.ESPADAS, Card.CORAZONES, Card.DIAMANTES, Card.TREBOLES, o
     * Card.JOKER.
     * @throws IllegalArgumentException si los valors de los parámetros no están en el
     * rangos permisibles
     */
    public Carta(int elValor, int elPalo) {
        if (elPalo != ESPADAS && elPalo != CORAZONES && elPalo != DIAMANTES
                && elPalo != TREBOLES && elPalo != JOKER) {
            throw new IllegalArgumentException("Palo de naipes ilegal.");
        }
        if (elPalo != JOKER && (elValor < 1 || elValor > 13)) {
            throw new IllegalArgumentException("Valor ilegal de naipes.");
        }
        valor = elValor;
        palo = elPalo;
    }

    /**
     * Devuelve el palo de esta carta.
     *
     * @returns el palo, que es una de las constantes Carta.ESPADAS,
     * Carta.CORAZONES, Carta.DIAMANTES, Carta.TREBOLES, o Carta.JOKER
     */
    public int getPalo() {
        return palo;
    }

    /**
     * Retorna el valor de la carta.
     *
     * @return el valor, que es uno de los números del 1 al 13, inclusive
     * para una tarjeta regular, y que puede ser cualquier valor para un Joker.
     */
    public int getValor() {
        return valor;
    }

    /**
     *Devuelve una representación de cadena del palo de la carta.
     *
     * @return una de las cadenas "Picas", "Corazones", "Diamantes", "Clubes" o
     * "Joker".
     */
    public String getPaloComoString() {
        switch (palo) {
            case ESPADAS:
                return "Espadas";
            case CORAZONES:
                return "Corazones";
            case DIAMANTES:
                return "Diamantes";
            case TREBOLES:
                return "Treboles";
            default:
                return "Joker";
        }
    }

    /**
     * Devuelve una representación en cadena del valor de la carta.
     *
     * @return para una tarjeta regular, una de las cadenas "Ace", "2", "3", ...,
     * "10", "Jack", "Queen" o "King". Para un Joker, la cuerda es siempre
     * numérico.
     */
    public String getValorComoString() {
        if (palo == JOKER) {
            return "" + valor;
        } else {
            switch (valor) {
                case 1:
                    return "As";
                case 2:
                    return "2";
                case 3:
                    return "3";
                case 4:
                    return "4";
                case 5:
                    return "5";
                case 6:
                    return "6";
                case 7:
                    return "7";
                case 8:
                    return "8";
                case 9:
                    return "9";
                case 10:
                    return "10";
                case 11:
                    return "Jota";
                case 12:
                    return "Reina";
                default:
                    return "Rey";
            }
        }
    }

    /**
     * Devuelve una representación de cadena de esta tarjeta, que incluye tanto su palo como
     * su valor (excepto que para un Joker con valor 1, el valor de retorno es solo
     * "Joker"). Los valores de muestra de retorno son: "Reina de Corazones", "10 de Diamantes",
     * "Ace of Spades", "Joker", "Joker # 2"
     */
    public String toString() {
        if (palo == JOKER) {
            if (valor == 1) {
                return "Joker";
            } else {
                return "Joker #" + valor;
            }
        } else {
            return getValorComoString() + " de " + getPaloComoString();
        }
    }

}
