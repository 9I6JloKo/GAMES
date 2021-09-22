package java2;
import java.util.Scanner;
public class Java2 {
    
    public static void main(String[] args){
        System.out.print("Celsii - ");
        Scanner scanner = new Scanner(System.in);
        double C = scanner.nextDouble();
        double F = (9*C/5)+32; 
        System.out.printf("Fahrenheit - %.2f%n", F);
    }
}