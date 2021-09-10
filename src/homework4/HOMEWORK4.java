/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework4;
import java.util.Scanner;
/**
 *
 * @author anana
 */
public class HOMEWORK4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] colors = {"Green","Red","Yellow","White","Black"};
        String[] pets = {"rat","cow","tiger","bunny","dragon", "snake", "horse", "sheep", "monkey", "chicken", "dog", "pig"};
        int i = 0, cyclov = 0, i2 = 0;
        int fullcycle = 60;
        int podcycle = 12;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wtite something - ");
        i = scanner.nextInt();
        if (i >= 1984){
            i -= 1984;
            cyclov = i / fullcycle;
            i2 = i - cyclov * fullcycle;
            i = ((i - cyclov * fullcycle) % podcycle);
            i2 /= podcycle;
            System.out.println("Name of this year: " + colors[i2] + " " + pets[i]);
        }
        else if (i <= 1984){
            i = 1984 - i;
            cyclov = i / fullcycle;
            i2 = fullcycle - (i - cyclov * fullcycle);
            i = (fullcycle - ((i - cyclov * fullcycle) % podcycle)) % podcycle;
            i2 /= podcycle;
            System.out.println("Name of this year: " + colors[i2] + " " + pets[i]);
        }
    }
    
}
