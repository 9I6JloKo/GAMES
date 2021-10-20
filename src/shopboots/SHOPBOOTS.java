/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots;
import java.util.Scanner;
import shopboots.classes.Product;
/**
 *
 * @author anana
 */
public class SHOPBOOTS {
    public static Product add_Product(){
        Scanner scanner = new Scanner(System.in);
        Product product = new Product();
        System.out.println("Модель - ");
        product.setModell(scanner.next());
        System.out.println("Размер - ");
        product.setSize(scanner.next());
        System.out.println("Фирма - ");
        product.setBywho(scanner.next());
        System.out.println("Цена - ");
        product.setPrice(scanner.nextDouble());
        return product;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int a = 0;
        int b = 0;
        while(i < 10000){
            int valik;
            //Clients client = new Clients();
            Product[] products = new Product[20];
            System.out.println("-----------------------\nСделайте выбор:");
            System.out.println("1) Выход из приложения"
                    + "\n2) Добавить обувь"
                    + "\n3) Вывести список обуви"
                    + "\n4) Добавить покупателя"
                    + "\n5) Вывести список покупателей"
                    + "\n6) Купить обувь определенным покупателем"
                    + "\n7) Доход с продаж");
            valik = scanner.nextInt();
            if(valik == 2){
                if(b < 20){
                    products[b] = add_Product();
                    b++;
                }
                else{
                    System.out.println("Слишком много обуви!");
                }
            }
            if(valik == 3){
                for(a = 0;a<20;a++){
                    if(a < b){
                        System.out.println(products[a].toString());
                    }
                    else{
                        break;
                    }
                }
            }
        }
    }
}
