package blackjack;

/**
 * Un objeto de tipo Deck representa un mazo de cartas. El mazo es un
 * poker mazo regular que contiene 52 cartas regulares y que también puede
 * opcionalmente incluye dos Jokers.
 */
public class Mazo {

    /**
     * Una matriz de 52 o 54 cartas. Un mazo de 54 cartas contiene dos Jokers, en
     * Además de las 52 cartas de un mazo de poker regular.
     */
    private Carta[] mazo;

    /**
     * Realiza un seguimiento de la cantidad de cartas que se han repartido desde el mazo para
     * lejos.
     */
    private int cartasUsadas;

    /**
     * Construye un mazo de poker regular de 52 cartas. Inicialmente, las cartas están en una
     * orden ordenado Se puede llamar al método barajar () para aleatorizar el orden.
     * (Tenga en cuenta que "nuevo Deck ()" es equivalente a "nuevo Deck (falso)".)
     */
    public Mazo() {
        this(false);  // Simplemente llame al otro constructor en esta clase.
    }

    /**
     * Construye un póquer mazo de naipes, El mazo contiene los usuales 52
     * tarjetas y opcionalmente puede contener dos Comodines además, para un total de
     * 54 cartas. Inicialmente las cartas están ordenadas. El método barajar ()
     * se puede llamar para aleatorizar el orden.
     *
     * @param incluyeJokers si true, dos comodines están incluidos en el mazo; Si
     * false, no hay Jokers en el mazo.
     */
    public Mazo(boolean incluyeJokers) {
        if (incluyeJokers) {
            mazo = new Carta[54];
        } else {
            mazo = new Carta[52];
        }
        int contCartas = 0; // Cuántas cartas se han creado hasta ahora.
        for (int palo = 0; palo <= 3; palo++) {
            for (int value = 1; value <= 13; value++) {
                mazo[contCartas] = new Carta(value, palo);
                contCartas++;
            }
        }
        if (incluyeJokers) {
            mazo[52] = new Carta(1, Carta.JOKER);
            mazo[53] = new Carta(2, Carta.JOKER);
        }
        cartasUsadas = 0;
    }

    /**
     * Pon todas las cartas usadas nuevamente en el mazo (si hay), y barajar el mazo
     * en un orden aleatorio.
     */
    public void barajar() {
        for (int i = mazo.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Carta temp = mazo[i];
            mazo[i] = mazo[rand];
            mazo[rand] = temp;
        }
        cartasUsadas = 0;
    }

    /**
     * A medida que las cartas se reparten desde el mazo, disminuye el número de cartas que quedan.
     * Esta función devuelve la cantidad de tarjetas que aún quedan en el
     * mazo El valor de retorno sería 52 o 54 (dependiendo de si el mazo
     * incluye comodines) cuando se crea el mazo por primera vez o después de que el mazo tiene
     * sido barajard Disminuye en 1 cada vez que se utiliza el método cartasReparto ()
     * llamado.
     */
    public int cartasIzquierdas() {
        return mazo.length - cartasUsadas;
    }

    /**
     * Quita la siguiente carta del mazo y la devuelve. Es ilegal llamar
     * este método si no hay más cartas en el mazo. Usted puede verificar
     * número de tarjetas restantes llamando a la función cartasIzquierdas ().
     *
     * @return la carta que se elimina del mazo.
     * @throws IllegalStateException si no quedan cartas en el mazo
     */
    public Carta cartasReparto() {
        if (cartasUsadas == mazo.length) {
            throw new IllegalStateException("No quedan cartas en el mazo.");
        }
        cartasUsadas++;
        return mazo[cartasUsadas - 1];
        // Nota de programación: las tarjetas no se eliminan literalmente de la matriz
        // eso representa el mazo. Simplemente hacemos un seguimiento de cuántas tarjetas
        // ha sido usado.
    }

    /**
     * Prueba si el mazo contiene Jokers.
     *
     * @return true, si este es un mazo de 54 cartas que contiene dos comodines o false
     * si este es un mazo de 52 cartas que no contiene comodines.
     */
    public boolean tieneJokers() {
        return (mazo.length == 54);
    }

}
