/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author alex
 */
public class Blackjack {

    static Calendar cal = Calendar.getInstance();
    static String logstr = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "\n";

    /**
     * Permita que el usuario juegue un juego de Blackjack, con la computadora como distribuidor.
     *
     * @return true si el usuario gana el juego, es false si el usuario pierde.
     */
    static boolean playBlackjack() {
        Scanner sc = new Scanner(System.in);
        Mazo mazo;                  // Una baraja de cartas. Una nueva baraja para cada juego.
        ManoBlackjack manoDistribuidor;  
        ManoBlackjack manoUsuario;    

        mazo = new Mazo();
        manoDistribuidor = new ManoBlackjack();
        manoUsuario = new ManoBlackjack();

        /*  Baraja el mazo, luego reparte dos cartas a cada jugador. */
        mazo.barajar();
        manoDistribuidor.agregarCarta(mazo.cartasReparto());
        manoDistribuidor.agregarCarta(mazo.cartasReparto());
        manoUsuario.agregarCarta(mazo.cartasReparto());
        manoUsuario.agregarCarta(mazo.cartasReparto());

        Blackjack.logstr += "\n";
        Blackjack.logstr += "\n";
        System.out.println();
        System.out.println();


        /* Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21).
         El jugador con Blackjack gana el juego. El distribuidor gana lazos.
         */
        if (manoDistribuidor.getBlackjackValor() == 21) {
            System.out.println("El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("El distribuidor tiene Blackjack. El distribuidor gana.");
            Blackjack.logstr += "El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".\n";
            Blackjack.logstr += "El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".\n";
            Blackjack.logstr += "\n";
            Blackjack.logstr += "El distribuidor tiene Blackjack. El distribuidor gana.\n";
            return false;
        }

        if (manoUsuario.getBlackjackValor() == 21) {
            System.out.println("El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("Usted tiene Blackjack.  Usted Gana.");
            Blackjack.logstr += "El distribuidor tiene el " + manoDistribuidor.getCarta(0)
                    + " y el " + manoDistribuidor.getCarta(1) + ".\n";
            Blackjack.logstr += "El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".\n";
            Blackjack.logstr += "\n";
            Blackjack.logstr += "Usted tiene Blackjack.  Usted Gana.\n";
            return true;
        }

        /*  Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero el usuario
         tiene la oportunidad de robar cartas (es decir, de "golpear"). El bucle while termina
         cuando el usuario elige "Stand". Si el usuario supera los 21,
         el usuario pierde inmediatamente.
         */
        while (true) {

            /* Muestre las cartas de usuario y deje que el usuario decida golpear o pararse. */
            Blackjack.logstr += "\n";
            Blackjack.logstr += "\n";
            System.out.println();
            System.out.println();
            System.out.println("Sus cartas son: ");
            Blackjack.logstr += "Sus cartas son: \n";
            for (int i = 0; i < manoUsuario.getContCartas(); i++) {
                System.out.println("    " + manoUsuario.getCarta(i));
                Blackjack.logstr += "    " + manoUsuario.getCarta(i) + "\n";
            }
            System.out.println("Su total es: " + manoUsuario.getBlackjackValor());
            Blackjack.logstr += "Su total es: " + manoUsuario.getBlackjackValor() + "\n";
            System.out.println();
            Blackjack.logstr += "\n";
            System.out.println("El Distribuidor muestra el  " + manoDistribuidor.getCarta(0));
            Blackjack.logstr += "El Distribuidor muestra el  " + manoDistribuidor.getCarta(0) + "\n";
            System.out.println();
            Blackjack.logstr += "\n";
            System.out.print("Hit (H) o Stand (S)? ");
            Blackjack.logstr += "Hit (H) o Stand (S)? ";
            char userAction;  // Respuesta del usuario, 'H' or 'S'.
            do {
                String entrada = sc.next();
                userAction = Character.toUpperCase(entrada.charAt(0));
                Blackjack.logstr += entrada + "\n";
                if (userAction != 'H' && userAction != 'S') {
                    System.out.print("Por favor responda H o S:  ");
                    Blackjack.logstr += "Por favor responda H o S:  ";
                }
            } while (userAction != 'H' && userAction != 'S');

            /* Si el usuario tiene éxito, el usuario obtiene una carta. Si el usuario está parado,
             el bucle termina (y es el turno del crupier de robar cartas).
             */
            if (userAction == 'S') {
                // bucle termina; el usuario ha terminado de tomar cartas.
                break;
            } else {  // userAction es 'H'. Dale una tarjeta al usuario.
                // Si el usuario supera los 21, el usuario pierde.
                Carta newCard = mazo.cartasReparto();
                manoUsuario.agregarCarta(newCard);
                System.out.println();
                System.out.println("hits del Usuario.");
                System.out.println("Su carta es " + newCard);
                System.out.println("Su total es ahora " + manoUsuario.getBlackjackValor());
                Blackjack.logstr += "\n";
                Blackjack.logstr += "hits del Usuario.\n";
                Blackjack.logstr += "Su carta es " + newCard + "\n";
                Blackjack.logstr += "Su total es ahora " + manoUsuario.getBlackjackValor() + "\n";
                if (manoUsuario.getBlackjackValor() > 21) {
                    System.out.println();
                    System.out.println("Has subido por encima de 21. Pierdes.");
                    System.out.println("La otra tarjeta del distribuidor era la "
                            + manoDistribuidor.getCarta(1));
                    Blackjack.logstr += "\n";
                    Blackjack.logstr += "Has subido por encima de 21. Pierdes.\n";
                    Blackjack.logstr += "La otra tarjeta del distribuidor era la "
                            + manoDistribuidor.getCarta(1) + "\n";
                    return false;
                }
            }

        }

        /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora es
         la oportunidad del distribuidor para dibujar. El distribuidor roba cartas hasta que el crupier
         el total es> 16. Si el crupier supera los 21, el crupier pierde.
         */
        System.out.println();
        System.out.println("stands del Usuario.");
        System.out.println("Las cartas de Distribuidor son ");
        System.out.println("    " + manoDistribuidor.getCarta(0));
        System.out.println("    " + manoDistribuidor.getCarta(1));
        Blackjack.logstr += "\n";
        Blackjack.logstr += "stands del Usuario.\n";
        Blackjack.logstr += "Las cartas de Distribuidor son \n";
        Blackjack.logstr += "    " + manoDistribuidor.getCarta(0) + "\n";
        Blackjack.logstr += "    " + manoDistribuidor.getCarta(1) + "\n";
        while (manoDistribuidor.getBlackjackValor() <= 16) {
            Carta newCard = mazo.cartasReparto();
            System.out.println("El tristribuidor hace hit y obtiene el " + newCard);
            Blackjack.logstr += "El tristribuidor hace hit y obtiene el " + newCard + "\n";
            manoDistribuidor.agregarCarta(newCard);
            if (manoDistribuidor.getBlackjackValor() > 21) {
                System.out.println();
                System.out.println("Distribuidor detenido por pasar de 21. Usted gana.");
                Blackjack.logstr += "\n";
                Blackjack.logstr += "Distribuidor detenido por pasar de 21. Usted gana.\n";
                return true;
            }
        }
        System.out.println("El total del distribuidor es " + manoDistribuidor.getBlackjackValor());
        Blackjack.logstr += "El total del distribuidor es " + manoDistribuidor.getBlackjackValor() + "\n";

        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos. */
        System.out.println();
        Blackjack.logstr += "\n";
        if (manoDistribuidor.getBlackjackValor() == manoUsuario.getBlackjackValor()) {
            System.out.println("El distribuidor gana en un empate. Tú pierdes.");
            Blackjack.logstr += "El distribuidor gana en un empate. Tú pierdes.\n";
            return false;
        } else if (manoDistribuidor.getBlackjackValor() > manoUsuario.getBlackjackValor()) {
            System.out.println("Distribuidor gana, " + manoDistribuidor.getBlackjackValor()
                    + " puntos a " + manoUsuario.getBlackjackValor() + ".");
            Blackjack.logstr += "Distribuidor gana, " + manoDistribuidor.getBlackjackValor()
                    + " puntos a " + manoUsuario.getBlackjackValor() + ".\n";
            return false;
        } else {
            System.out.println("Tu ganas, " + manoUsuario.getBlackjackValor()
                    + " puntos a " + manoDistribuidor.getBlackjackValor() + ".");
            Blackjack.logstr += "Tu ganas, " + manoUsuario.getBlackjackValor()
                    + " puntos a " + manoDistribuidor.getBlackjackValor() + ".\n";
            return true;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dolares;          // Cantidad de dolares que tiene el usuario.
        int apuesta;            // Cantidad de apuestas del usuario en un juego.
        boolean ganadas;   // ¿Ganó el usuario el juego?

        System.out.println("Bienvenido al juego de blackjack.");
        System.out.println();
        Blackjack.logstr += "Bienvenido al juego de blackjack.\n\n";

        dolares = 100;  // El usuario inicia con $100.

        while (true) {
            System.out.println("Usted tiene " + dolares + " dolares.");
            Blackjack.logstr += "Usted tiene " + dolares + " dolares.\n";
            do {
                System.out.println("¿Cuántos dólares quieres apostar? (Ingrese 0 para finalizar)");
                System.out.print("? ");
                Blackjack.logstr += "¿Cuántos dólares quieres apostar? (Ingrese 0 para finalizar)\n";
                Blackjack.logstr += "? ";
                apuesta = sc.nextInt();
                Blackjack.logstr += apuesta + "\n";
                if (apuesta < 0 || apuesta > dolares) {
                    System.out.println("Su respuesta debe estar entre 0 y" + dolares + '.');
                    Blackjack.logstr += "Su respuesta debe estar entre 0 y" + dolares + ".\n";
                }
            } while (apuesta < 0 || apuesta > dolares);
            if (apuesta == 0) {
                break;
            }
            ganadas = playBlackjack();
            if (ganadas) {
                dolares = dolares + apuesta;
            } else {
                dolares = dolares - apuesta;
            }
            System.out.println();
            Blackjack.logstr += "\n";
            if (dolares == 0) {
                System.out.println("¡Parece que te has quedado sin dolares!");
                Blackjack.logstr += "¡Parece que te has quedado sin dolares!\n";
                break;
            }
        }

        System.out.println();
        System.out.println("Te vas con $" + dolares + '.');
        Blackjack.logstr += "\nTe vas con $" + dolares + '.';
        Blackjack.logstr += "\n";
        BlackjackLogs blackjackLogs = new BlackjackLogs(logstr);
    }

}
