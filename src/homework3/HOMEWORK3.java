/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author anana
 */
public class HOMEWORK3 {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
    
        int score = 0;
        System.out.println("|-----------ВРЕМЯ МАТЕМАТИКИ!----------|");
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            int a = random.nextInt(11);
            int b = random.nextInt(11);
            int c = a * b;
            System.out.printf("| %s * %s%n", a, b);
            System.out.print("| Ваш ответ: ");
            Scanner scanner = new Scanner(System.in);
            int slovo = scanner.nextInt();
            if (slovo == c){
                System.out.println("| " + ANSI_GREEN + "МОЛОДЧИНА!" + ANSI_RESET);
                score++;
            }
            else{
                System.out.println("| " + ANSI_RED + "НЕПРАВИЛЬНО!" + ANSI_RESET);
            }
            
        }
        if (score > 4){
            System.out.println("| ТЫ ОТЛИЧНО ЗНАЕШЬ МАТЕМАТИКУ! 5 БАЛЛОВ!");
        }
        else if(score > 2){
            System.out.printf("| НАДО БЫ ЕЩЕ ПОДУЧИТЬ! %d БАЛЛА!%n", score);
        }
        else{
            System.out.println("| СОВСЕМ ПЛОХО! ИДИ УЧИ МАТЕМАТИКУ! БАЛЛЫ: " + score);
        }
    }
    
}
