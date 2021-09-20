package perevertishdz;

import java.util.Scanner;

public class PerevertishDZ {

public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
String firstString = scanner.nextLine();

char[] charFirstString = firstString.toCharArray();
char[] charSecondString = new char[charFirstString.length];
for (int i = 0; i < charFirstString.length; i++) {
charSecondString[(charSecondString.length-1)-i] = charFirstString[i];
}
System.out.println(charSecondString);
}
}