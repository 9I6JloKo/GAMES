/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dz5;
import java.util.Random;
/**
 *
 * @author anana
 */
public class Dz5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int kolvo = 20;
        Random random = new Random();
        int[] numbers = new int[kolvo];
        for (int i = 0; i < kolvo; i++){
            numbers[i] = random.nextInt(100);
            while (numbers[i] % 2 != 0){
                numbers[i] = random.nextInt(100);
            }
            System.out.println(numbers[i]);
        }
        int a = numbers[0], a1 = numbers[0]; 
        float summa = 0;
        for (int i = 0; i < kolvo; i++){
            if (a < numbers[i]){
                a = numbers[i];
            }
            else if(a1 > numbers[i]){
                a1 = numbers[i];
                
            }
            summa += numbers[i];
        }
        summa = (summa-(a+a1))/(kolvo-2);
        System.out.println("Среднее арифметическое = " + summa);
        System.out.println("Min = " + a1);
        System.out.println("Max = " + a);
        
    }
    
}
