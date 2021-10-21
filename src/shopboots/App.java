/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots;
import java.util.Scanner;
import shopboots.classes.Product;
import shopboots.classes.Client;
import shopboots.classes.History;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 *
 * @author anana
 */
public class App {
    private Product[] products = new Product[200];
    private Client[] clients = new Client[20];
    private History[] histories = new History[100];
    Double easyMoney = 0.00;
    Scanner scanner = new Scanner(System.in);
    public void run() {
        Scanner scanner = new Scanner(System.in);
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
                    + "\n7) Вывести историю покупок"
                    + "\n8) Доход с продаж");
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
                for(int i = 0; i < histories.length; i++){
                    if(histories[i] == null){
                        histories[i] = addHistory();
                        break;
                    }
                }
            }
            else if(task == 7){
                for (int i = 0; i< histories.length;i++){
                    if (histories[i] != null){
                        System.out.println(histories[i].toString());
                    }
                }
            }
            else if(task == 8){
                System.out.println("Доход - " + easyMoney);
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
        product.setSize(scanner.nextInt());
        System.out.println("Фирма - ");
        product.setBywho(scanner.next());
        System.out.println("Цена - ");
        product.setPrice(scanner.nextDouble());
        System.out.println("Количество пар обуви - ");
        product.setPiece(scanner.nextInt());
        if(product.getPiece() < 1){
            System.out.println("Нету обуви");
            product = null;
        }
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
        History history = null;
        if(clients[0] != null){
            history = new History();
            System.out.println("Список покупателей - ");
            for(int i = 0; i < clients.length; i++){
                if(clients[i] != null){
                    System.out.println((i+1) + ")" + clients[i].toString());
                }
            }
            System.out.println("Номер покупателя - ");
            int clientNumb = scanner.nextInt();
            history.setClient(clients[clientNumb-1].getClientName());
            System.out.println("Список обуви - ");
            for(int i = 0; i < products.length; i++){
                if(products[i] != null){
                    System.out.println((i+1) + ")" + products[i].toString());
                }
            }
            System.out.println("Номер обуви - ");
            int productNumb = scanner.nextInt();
            history.setProduct(products[productNumb-1].getModell());
            Calendar c = new GregorianCalendar();
            history.setDateOfBuying(c.getTime());
            if(clients[clientNumb-1].getClientMoney() >= products[productNumb-1].getPrice() && products[productNumb-1].getPiece() > 0){
                clients[clientNumb-1].setClientMoney(clients[clientNumb-1].getClientMoney() - products[productNumb-1].getPrice());
                easyMoney += products[productNumb-1].getPrice();
                products[productNumb-1].setPiece(products[productNumb-1].getPiece() - 1);
            }
            else{
                System.out.println("Не хватает денег или товара");
                history = null;
            }
            return history;
        }
        else{
            System.out.println("Нету покупателей или обуви");
        }
        return history;
    }
}
