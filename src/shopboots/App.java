/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopboots;
import facade.AntiHistoryFacade;
import facade.ClientFacade;
import facade.HistoryFacade;
import facade.ProductFacade;
import java.util.Scanner;
import shopboots.classes.Product;
import shopboots.classes.Client;
import shopboots.classes.History;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import tools.SaverToFile;
import shopboots.classes.AntiHistory;
/*import shopboots.classes.Wages;*/
/**
 *
 * @author anana
 */
public class App {
    
    private Scanner scanner = new Scanner(System.in);
    private ProductFacade productFacade;
    private AntiHistoryFacade antiHistoryFacade;
    private ClientFacade clientFacade;
    private HistoryFacade historyFacade;
    double easyMoney = 0;
    
    public App() {
        init();
    }
    
    private void init(){
        productFacade = new ProductFacade(Product.class);
        antiHistoryFacade = new AntiHistoryFacade(AntiHistory.class);
        clientFacade = new ClientFacade(Client.class);
        historyFacade = new HistoryFacade(History.class);
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;
        while(repeat == true){
        
            int task;
            System.out.println("-----------------------\nСделайте выбор:");
            System.out.print("\n1) Выход из приложения"
                    + "\n2) Добавить обувь"
                    + "\n3) Вывести список обуви"
                    + "\n4) Добавить покупателя"
                    + "\n5) Вывести список покупателей"
                    + "\n6) Купить обувь определенным покупателем"
                    + "\n7) Вывести историю покупок"
                    + "\n8) Вернуть товар по истории покупок"
                    + "\n9) Показать историю возврата"
                    + "\n10) Доход с продаж"
                    + "\n11) Изменить продукт"
                    + "\n12) Изменить пользователя"
                    + "\n--> ");
            task = scanner.nextInt();
            switch (task) {
                case 2:
                    productFacade.create(addProduct()); 
                    break;
                case 3:
                    printListBoots();
                    break;
                case 4:
                    clientFacade.create(addClient());
                    break;
                case 5:
                    printListClients();
                    break;
                case 6:
                    historyFacade.create(addHistory());
                    break;
                case 7:
                    printListHistories();
                    break;
                case 8:
                    antiHistoryFacade.create(addAntiHistory());
                    break;
                case 9:
                    List<AntiHistory> antihistories = antiHistoryFacade.findAll();
                    for (int i = 0; i< antihistories.size();i++){
                        if (antihistories.get(i) != null){
                            System.out.println(antihistories.get(i).toString());
                        }
                    }   break;
                case 10:
                    List<History> histories = historyFacade.findAll();
                    antihistories = antiHistoryFacade.findAll();
                    List<Product> products = productFacade.findAll();
                    for(int i = 0; i < (histories.size()); i++){
                        for (int j = 0; j < products.size(); j++) {
                            if(histories.get(i).getProduct() == products.get(j).getModell()){
                                easyMoney += products.get(j).getPrice();
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < antihistories.size(); i++){
                        for (int j = 0; j < products.size(); j++){
                            if(antihistories.get(i).getProduct() == products.get(j).getModell()){
                                easyMoney -= products.get(j).getPrice();
                                break;
                            }
                        }   
                    }
                    monthWages();
                    break;

                case 11:
                    System.out.println("Нажмите 1 для продолжения, 2 для отмены");
                    byte otvet = scanner.nextByte();
                    if(otvet == 1){
                        productFacade.edit(productUpdate());
                    }
                    break;
                case 12:
                    System.out.println("Нажмите 1 для продолжения, 2 для отмены");
                    byte otvet1 = scanner.nextByte();
                    if(otvet1 == 1){
                        clientFacade.edit(clientUpdate());
                        
                    }
                    break;
                default:
                    System.out.println("Пока! =)");
                    repeat = false;
                    break;
            }
        }
    }
    private void printListHistories(){
        List<History> histories = historyFacade.findAll();
        for(History history : histories){
            System.out.println(history.toString());
        }
    }
    private void printListClients() {
        System.out.println("Список покупателей: ");
        List<Client> clients = clientFacade.findAll();
        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }
    private void printListBoots() {
        System.out.println("Список обуви: ");
        List<Product> boots = productFacade.findAll();
        for (Product boot : boots) {
            System.out.println(boot);
        }
    }
    private void monthWages(){
        List<History> histories = historyFacade.findAll();
        List<AntiHistory> antihistories = antiHistoryFacade.findAll();
        System.out.println("За какой месяц хотите счет?\n1) Январь\n2) Февраль\n3) Март\n4) Апрель\n5) Май\n6) Июнь\n7) Июль\n8) Август\n9) Сентябрь\n10) Октябрь\n11) Ноябрь\n12) Декабрь\n-->");    
        byte month = scanner.nextByte();
        for (int i = 0; i< histories.size();i++){
            if (histories.get(i) != null && month-1 == histories.get(i).getDateOfBuying().getMonth()){
                System.out.println(histories.get(i).toString());
                for (int j = 0; j< antihistories.size();j++){
                    if(antihistories.get(j) != null && month-1 == antihistories.get(j).getDateOfVozvrata().getMonth()){
                        System.out.println(antihistories.get(j).toString());
                    }
                }
            } else {
                System.out.println("Товар устарел");
            }
        }
        if(easyMoney >= 0){
            System.out.println("Общий доход по всем месяцам - " + easyMoney);
        }
    }
    private Product productUpdate(){
        List<Product> products = productFacade.findAll();
        if (products.get(0) != null){
            for(int i = 0; i < products.size(); i++){
                if(products.get(0) != null){
                    System.out.println((i+1) + ")" + products.get(i).toString());
                }
            }   
            System.out.println("Какой продукт хотите изменить?");
            byte otvetproduct = scanner.nextByte();
            scanner.nextLine();
            System.out.println("Какую часть хотите изменить?\nПродавец - \"1\", \nМодель - \"2\", \nЦена - \"3\", \nРазмер - \"4\", \nКол-во - \"5\" ");
            byte otvetproduct1 = scanner.nextByte();
            scanner.nextLine();
            switch(otvetproduct1){
                case 1:
                    System.out.println("Продавец - ");
                    products.get(otvetproduct-1).setBywho(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Модель - ");
                    products.get(otvetproduct-1).setModell(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Цена - "); 
                    products.get(otvetproduct-1).setPrice(scanner.nextDouble());scanner.nextLine();
                    break;
                case 4: 
                    System.out.println("Размер - "); 
                    products.get(otvetproduct-1).setSize(scanner.nextDouble());scanner.nextLine();
                    break;
                case 5:
                    System.out.println("Кол-во - "); 
                    products.get(otvetproduct-1).setPiece(scanner.nextInt());scanner.nextLine();
                    products.get(otvetproduct-1).setMaxPiece(products.get(otvetproduct-1).getPiece());
                    break;
                default:
                    System.out.println("Error");
            }
            return products.get(otvetproduct-1);
        }
        else{
            System.out.println("Массив продуктов пуст");
            return null;
        }
    }
    private Client clientUpdate(){
        List<Client> clients = clientFacade.findAll();
        byte otvetclient11 = 0;
        if (clients.get(0) != null){
            for(int i = 0; i < clients.size(); i++){
                if(clients.get(0) != null){
                    System.out.println((i+1) + ")" + clients.get(i).toString());
                }
            }   
            System.out.println("Какого клиента хотите изменить?");
            byte otvetclient = scanner.nextByte();
            otvetclient11 = otvetclient;
            scanner.nextLine();
            System.out.println("Какую часть хотите изменить?\nИмя - \"1\", \nФамилия - \"2\", \nНомер телефона - \"3\", \nДеньги клиента - \"4\"");
            byte otvetclient1 = scanner.nextByte();
            scanner.nextLine();
            switch(otvetclient1){
                case 1:
                    System.out.println("Имя - ");
                    clients.get(otvetclient-1).setClientName( scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Фамилия - ");
                    clients.get(otvetclient-1).setClientSurname(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Номер телефона - ");
                    clients.get(otvetclient-1).setClientNumber(scanner.nextInt());scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Деньги клиента - ");
                    clients.get(otvetclient-1).setClientMoney(scanner.nextDouble());scanner.nextLine();
                    break;
                default:
                    System.out.println("Error");
            }
            return clients.get(otvetclient11-1);
        }
        else{
            System.out.println("Массив клиентов пуст");
            return null;
        }
    }
    private Client addClient(){
        Client client = new Client();
        System.out.print("Имя - ");
        client.setClientName(scanner.next());
        System.out.print("Фамилия - ");
        client.setClientSurname(scanner.next());
        System.out.print("Номер телефона - ");
        client.setClientNumber(scanner.nextInt());
        System.out.print("Деньги - ");
        client.setClientMoney(scanner.nextDouble());
        return client;
    }
    private Product addProduct() {
        Product product = new Product();
        System.out.print("Модель - ");
        product.setModell(scanner.nextLine());
        System.out.print("Размер - ");
        product.setSize(scanner.nextDouble());
        System.out.print("Фирма - ");
        product.setBywho(scanner.next());
        System.out.print("Цена - ");
        product.setPrice(scanner.nextDouble());
        System.out.print("Количество пар обуви - ");
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
        List<Client> clients = clientFacade.findAll();
        List<Product> products = productFacade.findAll();
        if(clients != null){
            history = new History();
            System.out.println("Список покупателей - ");
            for(int i = 0; i < clients.size(); i++){
                if(clients.get(0) != null){
                    System.out.println((i+1) + ")" + clients.get(i).toString());
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
                /*wages.setWages((double) (wages.getWages()+products.get(productNumb-1).getPrice()));*/
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
        List<History> histories = historyFacade.findAll();
        List<Client> clients = clientFacade.findAll();
        List<Product> products = productFacade.findAll();
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
                                        /*wages.setWages((double) (wages.getWages()-products.get(b).getPiece()));*/
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
