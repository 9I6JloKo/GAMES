/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots;
import java.util.Scanner;
import shopboots.classes.Product;
import shopboots.classes.Client;
/**
 *
 * @author anana
 */
public class App {
    Product[] products = new Product[20];
    Client[] clients = new Client[20];
    History[] histories = new History[100];
    
    Scanner scanner = new Scanner(System.in);
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int a = 0;
        int b = 0;
        boolean repeat = true;
        while(repeat == true){
            int task;
            //Clients client = new Clients();
            
            System.out.println("-----------------------\nСделайте выбор:");
            System.out.println("1) Выход из приложения"
                    + "\n2) Добавить обувь"
                    + "\n3) Вывести список обуви"
                    + "\n4) Добавить покупателя"
                    + "\n5) Вывести список покупателей"
                    + "\n6) Купить обувь определенным покупателем"
                    + "\n7) Доход с продаж");
            task = scanner.nextInt();
            if(task == 2){
                for (int i = 0;i<products.length;i++){
                    if (products[i] == null){
                        products[i] = addProduct();
                        break;
                    }
                }
            }else if(task == 3){
                for (int i = 0; i< products.length;i++){
                    if (products[i] != null){
                        System.out.println(products[i].toString());
                    }
                }
            }
            else if(task == 4){
                for (int i = 0;i<clients.length;i++){
                    if (clients[i] == null){
                        clients[i] = addClient();
                        break;
                    }
                }
            }
            else if(task == 5){
                for (int i = 0; i< clients.length;i++){
                    if (clients[i] != null){
                        System.out.println(clients[i].toString());
                    }
                }
            }
            else if(task == 6){
                
            }
            else{
                repeat = false;
            }
        }
    }
    private Product addProduct() {
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
    private Client addClient(){
        Client client = new Client();
        System.out.println("Имя - ");
        client.setClientName(scanner.next());
        System.out.println("Фамилия - ");
        client.setClientSurname(scanner.next());
        System.out.println("Номер телефона - ");
        client.setClientNumber(scanner.nextInt());
        System.out.println("Деньги - ");
        client.setClientMoney(scanner.nextDouble());
        return client;
    }
    private History addHistory(){
        
    }
}
