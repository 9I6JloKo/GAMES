/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;
import java.util.Scanner;
/**
 *
 * @author anana
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cifra1,sotni,edinitsi, summa, desjatki;
        System.out.print("Cifra - ");
        Scanner scanner = new Scanner(System.in);
        String cifra = scanner.next();
        int dlina = cifra.length();
        if (dlina == 3){
            cifra1 = Integer.parseInt(cifra);
            sotni = (cifra1 - cifra1 % 100) / 100;
            desjatki = ((cifra1 % 100) - (cifra1 % 100) % 10)/ 10;
            edinitsi = cifra1 % 10;
            summa = edinitsi+ desjatki;
            System.out.println("sotni - " + sotni);
            System.out.println("desjatki - " + desjatki);
            System.out.println("edinitsi - " + edinitsi);
            System.out.println("summa - " + summa);
        }
    }
    
}
