/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/**
 *
 * @author pupil
 */
public class PangrammaVseLiBukvi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("write a sentence");
        String say = new String(sc.nextLine());
        char[] letters = new char[say.length()];
        /*   String say = new String("The quick brown fox jumps over the lazy dog"); */
        char[] characters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','r','s','t','u','v','w','x','y','z','q'};
        for(int i = 0; i < characters.length; i++){
            System.out.print("Не хватает буквы - ");
            for(int a = 0; a < say.length(); a++){
                if(characters[i] == say.charAt(a)){
                    count ++;
                    a = say.length(); /* or @Break@ */
                }
                else{
                    letters[a] = characters[i];
                    if(letters[letters.length-1] == characters[i]){
                        System.out.printf("%s", letters[letters.length-1]);
                    }
                }
            }
        System.out.println();
        }if(count == 26){
            System.out.println("Это панграмма");
        }else{
            System.out.println("Это не панграмма");
        }
    }
}