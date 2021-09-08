package java2;
import java.util.Scanner;
public class Java2 {
    
    public static void main(String[] args){
        System.out.print("Celsii - ");
        Scanner scanner = new Scanner(System.in);
        double C = scanner.nextDouble();
        double F = (double)(5*C/9)+32; 
        System.out.println("Fahrenheit - " + F);
    }
}