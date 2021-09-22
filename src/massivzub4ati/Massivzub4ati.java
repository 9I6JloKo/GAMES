/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massivzub4ati;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author anana
 */
public class Massivzub4ati {
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return (random.nextInt(max - min+1)) + min;
    }
    public static void main(String[] args) {
        int min = 0, max = 0, summa = 0, summateplo = 0, summaholod = 0, mesatsteplo = 0, mesatsholod = 0, denteplo = 0, denholod = 0; 
        int[][][] TempForThisYear = new int[12][][];
        for(int i = 0; i < 12; i++){
            if(i == 0 || i == 2 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11){
                TempForThisYear[i] = new int[31][];
                for(int g = 0; g < 31; g++){
                    TempForThisYear[i][g] = new int[24];
                }
            }
            else if(i == 3 || i == 5 || i == 8 || i == 10){
                TempForThisYear[i] = new int[30][];
                for(int g = 0; g < 30; g++){
                    TempForThisYear[i][g] = new int[24];
                }
            }
            else{
                TempForThisYear[i] = new int[28][];
                for(int g = 0; g < 28; g++){
                    TempForThisYear[i][g] = new int[24];
                }
            }
        }
        for(int i = 0; i < TempForThisYear.length; i++){
            switch(i){
                case 0:case 1: case 11:
                    min = -35;
                    max = -14;
                    break;
                case 2:case 3: case 4:
                    min = -14;
                    max = 6;
                    break;
                case 5: case 6: case 7:
                    min = 6;
                    max = 36;
                    break;
                case 8: case 9: case 10:
                    min = -8;
                    max = 25;
                    break;
            }
            for(int a = 0; a < TempForThisYear[i].length; a++){
                for(int d = 0; d < TempForThisYear[i][a].length; d++){
                    TempForThisYear[i][a][d] = getRandomNumberUsingNextInt(min,max);
                }
            } 
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which month would u want?(number) - ");
        int month = scanner.nextInt();
        System.out.print("Which day would u want?(number) - ");
        int day = scanner.nextInt();
        System.out.printf("%n|Temperature for this day|%n");
        for(int i = 0;i < TempForThisYear[month-1][day-1].length; i++){
            System.out.printf("%n|%d:00: %d|%n", i, TempForThisYear[month-1][day-1][i]);
        }
        for(int g = 0;g < 12; g++){
            for(int i = 0;i < TempForThisYear.length; i++){
                if(summaholod<summa){
                    summaholod = summa;
                    denholod = i;
                    mesatsholod = g;
                }
                if(summateplo<summa){
                    summateplo = summa;
                    denteplo = i;
                    mesatsteplo = g;
                }
                for(int a = 0;a < TempForThisYear[i].length; a++){
                    summa += TempForThisYear[g][i][a];
                }
            }
        }
        System.out.printf("Число самого холодного дня года - %d.%d, средняя температура - %d", denholod, mesatsholod, (summaholod%24));
        System.out.printf("Число самого теплого дня года - %d.%d, средняя температура - %d", denteplo, mesatsteplo, (mesatsholod%24));
    }
}
