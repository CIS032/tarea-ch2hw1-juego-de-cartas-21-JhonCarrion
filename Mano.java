package blackjack;

/**
 * Un objeto de tipo Mano representa una mano de cartas. Las cartas pertenecen a
 * clase Card. Una mano está vacía cuando se crea, y cualquier número de cartas puede
 * ser agregado a esto.
 */
import java.util.ArrayList;

public class Mano {

    private ArrayList<Carta> mano;   // lista de cartas en la mano

    /**
     * Crea una mano que esté inicialmente vacía.
     */
    public Mano() {
        mano = new ArrayList<Carta>();
    }

    /**
     * Retire todas las cartas de la mano, dejándolas vacías.
     */
    public void vaciar() {
        mano.clear();
    }

    /**
     * Agrega una carta a la mano. Se agrega al final de la mano actual.
     *
     * @param c la tarjeta no nula que se agregará.
     * @throws NullPointerException si el parámetro c es nulo.
     */
    public void agregarCarta(Carta c) {
        if (c == null) {
            throw new NullPointerException("No se puede agregar una carta nula a una mano.");
        }
        mano.add(c);
    }

    /**
     * Retire una tarjeta de la mano, si está presente.
     *
     * @param c la tarjeta que se eliminará Si c es nulo o si la tarjeta no está en
     * la mano, entonces nada está hecho.
     */
    public void removerCarta(Carta c) {
        mano.remove(c);
    }

    /**
     * Retire la tarjeta en una posición específica de la mano.
     *
     * @param posicionar la posición de la tarjeta que se va a eliminar, donde
     * las posiciones comienzan desde cero.
     * @throws IllegalArgumentException si la posición no existe en el
     * mano, eso es si la posición es menor que 0 o mayor o igual que
     * la cantidad de cartas en la mano.
     */
    public void removerCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("La posición no existe en la mano: "
                    + posicion);
        }
        mano.remove(posicion);
    }

    /**
     * Devuelve la cantidad de cartas en la mano.
     */
    public int getContCartas() {
        return mano.size();
    }

    /**
     * Obtiene la carta en una posición especificada en la mano. (Tenga en cuenta que esta tarjeta
     * no se elimina de la mano!)
     *
     * @param posicionar la posición de la tarjeta que se va a devolver
     * @throws IllegalArgumentException si la posición no existe en la mano
     */
    public Carta getCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("La posición no existe en la mano: "
                    + posicion);
        }
        return mano.get(posicion);
    }

    /**
     * Ordena las cartas en la mano para agrupar las cartas del mismo palo
     * juntos, y dentro de un palo, las tarjetas están ordenadas por valor. Tenga en cuenta que ases
     * se considera que tienen el valor más bajo, 1.
     */
    public void ordenarXPalo() {
        ArrayList<Carta> newHand = new ArrayList<Carta>();
        while (mano.size() > 0) {
            int pos = 0;  // Posición de la carta mínima.
            Carta c = mano.get(0);  // Carta Minima.
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getPalo()< c.getPalo()
                        || (c1.getPalo()== c.getPalo() && c1.getValor()< c.getValor())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            newHand.add(c);
        }
        mano = newHand;
    }

    /**
     * Ordena las cartas en la mano para agrupar las cartas del mismo valor
     * juntos. Las cartas con el mismo valor se clasifican por palo. Tenga en cuenta que ases
     * se considera que tienen el valor más bajo, 1.
     */
    public void ordenarXValor() {
        ArrayList<Carta> newHand = new ArrayList<Carta>();
        while (mano.size() > 0) {
            int pos = 0;  // Posición de la carta mínima.
            Carta c = mano.get(0);  // Carta Minima.
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getValor()< c.getValor()
                        || (c1.getValor()== c.getValor()&& c1.getPalo()< c.getPalo())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            newHand.add(c);
        }
        mano = newHand;
    }

}
