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
import interfaces.Keeping;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tools.SaverToFile;
import shopboots.classes.AntiHistory;
import tools.SaverToBase;
/**
 *
 * @author anana
 */
public class App {
    private List<Product> products = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private List<AntiHistory> antihistories = new ArrayList<>();
    /*private Keeping keeping = new SaverToFile();*/
    private Keeping keeping = new SaverToBase();
    Double easyMoney = 0.00;
    Scanner scanner = new Scanner(System.in);

    public App() {
//        products = keeping.loadProducts();
        clients = keeping.loadClients();
//        histories = keeping.loadHistories();
//        antihistories = keeping.loadAntiHistories();
    }
    
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;
        while(repeat == true){
        
            int task;
            System.out.println("-----------------------\nСделайте выбор:");
            System.out.println("1) Выход из приложения"
                    + "\n2) Добавить обувь"
                    + "\n3) Вывести список обуви"
                    + "\n4) Добавить покупателя"
                    + "\n5) Вывести список покупателей"
                    + "\n6) Купить обувь определенным покупателем"
                    + "\n7) Вывести историю покупок"
                    + "\n8) Вернуть товар по истории покупок"
                    + "\n9) Показать историю возврата"
                    + "\n10) Доход с продаж");
            task = scanner.nextInt();
            if(task == 2){
                products.add(addProduct());
//                keeping.saveProducts(products);
            }else if(task == 3){
                for (int i = 0; i< products.size();i++){
                    if (products.get(i) != null){
                        System.out.println(products.get(i).toString());
                    }
                }
            }
            else if(task == 4){
                clients.add(addClient());
                keeping.saveClients(clients);
                
            }else if(task == 5){
                for (int i = 0; i< clients.size();i++){
                    if (clients.get(i) != null){
                        System.out.println(clients.get(i).toString());
                    }
                }
            }else if(task == 6){
                histories.add(addHistory());
                //keeping.saveHistories(histories);
            
            }else if(task == 7){
                for (int i = 0; i< histories.size();i++){
                    if (histories.get(i) != null){
                        System.out.println(histories.get(i).toString());
                    }
                }
            }
            else if(task == 10){
                System.out.println("Доход - " + easyMoney);
            }
            else if(task == 8){
                antihistories.add(addAntiHistory());
                //keeping.saveAntiHistories(antihistories);
            
            }else if(task == 9){
                for (int i = 0; i< antihistories.size();i++){
                    if (antihistories.get(i) != null){
                        System.out.println(antihistories.get(i).toString());
                    }
                }
            }
            else{
                repeat = false;
            }
        }
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
        product.setMaxPiece(product.getPiece());
        if(product.getPiece() < 1){
            System.out.println("Нету обуви");
            product = null;
        }
        return product;
    }
    private History addHistory(){
        History history = null;
        if(clients.get(0) != null){
            history = new History();
            System.out.println("Список покупателей - ");
            for(int i = 0; i < clients.size(); i++){
                if(clients.get(0) != null){
                    System.out.println((i+1) + ")" + clients.get(0).toString());
                }
            }
            System.out.println("Номер покупателя - ");
            int clientNumb = scanner.nextInt();
            history.setClientNumber(clients.get(clientNumb-1).getClientNumber());
            history.setClientName(clients.get(clientNumb-1).getClientName());
            System.out.println("Список обуви - ");
            for(int i = 0; i < products.size(); i++){
                if(products.get(0) != null){
                    System.out.println((i+1) + ")" + products.get(i).toString());
                }
            }
            System.out.println("Номер обуви - ");
            int productNumb = scanner.nextInt();
            history.setProduct(products.get(productNumb-1).getModell());
            Calendar c = new GregorianCalendar();
            history.setSize(products.get(productNumb-1).getSize());
            history.setDateOfBuying(c.getTime());
            if(clients.get(clientNumb-1).getClientMoney() >= products.get(productNumb-1).getPrice() && products.get(productNumb-1).getPiece() > 0){
                clients.get(clientNumb-1).setClientMoney(clients.get(clientNumb-1).getClientMoney() - products.get(productNumb-1).getPrice());
                easyMoney += products.get(productNumb-1).getPrice();
                products.get(productNumb-1).setPiece(products.get(productNumb-1).getPiece() - 1);
                LocalDate localDate = LocalDate.now();
                localDate = localDate.plusWeeks(2);
                history.setLocalDate(localDate);
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
    private AntiHistory addAntiHistory(){
        AntiHistory antihistory = new AntiHistory();
        System.out.println("История покупок - ");
            for (int i = 0; i< histories.size();i++){
                if (histories.get(i) != null){
                    System.out.println((i+1) + ")" + histories.get(i).toString());
                }
            }
            int otvet = scanner.nextInt();
            if(histories.get(0) != null){
                for (int i = 0; i< clients.size();i++){
                    if(clients.get(i) != null){
                        if(clients.get(i).getClientNumber() == histories.get(otvet-1).getClientNumber()){
                            for (int b = 0; b< products.size();b++){
                                if(products.get(b) != null && products.get(b).getModell() == histories.get(otvet-1).getProduct() && products.get(b).getSize() == histories.get(otvet-1).getSize()){
                                    if(products.get(b).getMaxPiece() > products.get(b).getPiece()){
                                        clients.get(i).setClientMoney(clients.get(i).getClientMoney() + products.get(b).getPrice());
                                        products.get(b).setPiece(products.get(b).getPiece()+1);
                                        easyMoney -= products.get(b).getPiece();
                                        antihistory.setClientNumber(clients.get(i).getClientNumber());
                                        Calendar c = new GregorianCalendar();
                                        antihistory.setDateOfVozvrata(c.getTime());
                                        antihistory.setProduct(products.get(i).getModell());
                                        antihistory.setClient(clients.get(i).getClientName());
                                        break;
                                    }else{
                                        System.out.println("Товар заполнен, идите лесом!");
                                        antihistory = null;
                                    }
                                }
                                else{
                                    antihistory = null;
                                }
                            }
                            break;
                        }
                    }
                    else{
                        antihistory = null;
                    }
                }
            }else{
                System.out.println("ошибка");
                antihistory = null;
            }
        return antihistory;
    }
}
