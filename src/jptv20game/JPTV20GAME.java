/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv20game;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author pupil
 */
public class JPTV20GAME {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int number = random.nextInt(10);
        System.out.println("Отгадай число !");
        for(int i = 0; i < 5; i++){
            int usernumber = scanner.nextInt();
            if(number == usernumber){
                System.out.println("URAAAAAAAA!!!!!!!! POBEDAAA");
                break;
            }
            else {
                System.out.println("NET!!!!!");
            }
        }
    }
    
}
