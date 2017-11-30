package blackjack;

public class ManoBlackjack extends Mano {

    /**
     * Calcula y devuelve el valor de esta mano en el juego de Blackjack.
     */
    public int getBlackjackValor() {

        int val;      // El valor calculado para la mano.
        boolean as;  // Esto se establecerá en verdadero si la
        // mano contiene un as.
        int cartas;    // Número de cartas en la mano.

        val = 0;
        as = false;
        cartas = getContCartas();  // (método definido en la clase Hand)

        for (int i = 0; i < cartas; i++) {
            // Agregue el valor de la i-ésima carta en la mano.
            Carta card;    // la i-ésima carta.
            int cardVal;  // El valor de blackjack de la i-ésima carta.
            card = getCarta(i);
            cardVal = card.getValor();  // El valor normal, 1 a 13.
            if (cardVal > 10) {
                cardVal = 10;   // Para un JOTA, REINA o REY.
            }
            if (cardVal == 1) {
                as = true;     // Hay al menos un as.
            }
            val = val + cardVal;
        }

        // Ahora, val es el valor de la mano, contando cualquiera como 1.
        // Si hay un as, y si cambia su valor de 1 a
        // 11 dejaría el puntaje menor o igual a 21,
        // entonces hazlo sumando los 10 puntos adicionales a val.
        if (as == true && val + 10 <= 21) {
            val = val + 10;
        }

        return val;

    }

} 
