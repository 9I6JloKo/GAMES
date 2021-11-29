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
import interfaces.Keeping;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import tools.SaverToFile;
import shopboots.classes.AntiHistory;
/*import shopboots.classes.Wages;*/
import tools.SaverToBase;
import java.lang.String;
/**
 *
 * @author anana
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private ClientFacade clientFacade;
    private HistoryFacade historyFacade;
    private AntiHistoryFacade antiHistoryFacade;
    private ProductFacade productFacade;
    /*private Keeping keeping = new SaverToFile();*/
//    private Keeping keeping = new SaverToBase();
   /* private Wages wages = new Wages();*/
    double easyMoney = 0;
//    
//    public App() {
//        products = keeping.loadProducts();
//        clients = keeping.loadClients();
//        histories = keeping.loadHistories();
//        antihistories = keeping.loadAntiHistories();
//       /* wages = keeping.loadWages();*/
//    }
    public App() {
        init();
    }
    private void init(){
        antiHistoryFacade = new AntiHistoryFacade(AntiHistory.class);
        productFacade = new ProductFacade(Product.class);
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
            task = inputInt();
            switch (task) {
                case 2:
                    productFacade.create(addProduct());
                    break;
                case 3:
                    for (int i = 0; i< productFacade.findAll().size();i++){
                        if (productFacade.findAll().get(i) != null){
                            System.out.println(productFacade.findAll().get(i).toString());
                        }
                    }   break;
                case 4:
                    clientFacade.create(addClient());
                    break;
                case 5:
                    for (int i = 0; i< clientFacade.findAll().size();i++){
                        if (clientFacade.findAll().get(i) != null){
                            System.out.println(clientFacade.findAll().get(i).toString());
                        }
                    }   break;
                case 6:
                    historyFacade.create(addHistory());
                    break;
                case 7:
                    for (int i = 0; i< historyFacade.findAll().size();i++){
                        if (historyFacade.findAll().get(i) != null){
                            System.out.println(historyFacade.findAll().get(i).toString());
                        }
                    }   break;
                case 8:
                    antiHistoryFacade.create(addAntiHistory());
                    break;
                case 9:
                    for (int i = 0; i< antiHistoryFacade.findAll().size();i++){
                        if (antiHistoryFacade.findAll().get(i) != null){
                            System.out.println(antiHistoryFacade.findAll().get(i).toString());
                        }
                    }   break;
                case 10:
                    for(int i = 0; i < (historyFacade.findAll().size()); i++){
                        for (int j = 0; j < productFacade.findAll().size(); j++) {
                            if(historyFacade.findAll().get(i).getProduct() == productFacade.findAll().get(j).getModell()){
                                easyMoney += productFacade.findAll().get(j).getPrice();
                                break;
                            }
                        }
                    }
                        for (int i = 0; i < antiHistoryFacade.findAll().size(); i++){
                            for (int j = 0; j < productFacade.findAll().size(); j++){
                                if(antiHistoryFacade.findAll().get(i).getProduct() == productFacade.findAll().get(j).getModell()){
                                    easyMoney -= productFacade.findAll().get(j).getPrice();
                                    break;
                                }
                            }   
                        }
                    monthWages();
                    /*     if(wages.getWages() != 0){
                    System.out.println("Доход - " + wages.getWages());
                    }*/ break;

                case 11:
                    System.out.println("Нажмите 1 для продолжения, 2 для отмены");
                    byte otvet = scanner.nextByte();
                    if(otvet == 1){
                        productUpdate();
                    }
                    break;
                case 12:
                    System.out.println("Нажмите 1 для продолжения, 2 для отмены");
                    byte otvet1 = scanner.nextByte();
                    if(otvet1 == 1){
                        clientUpdate();
                    }
                    break;
                default:
                    repeat = false;
                    break;
            }
        }
    }
    private void monthWages(){
        System.out.println("За какой месяц хотите счет?\n1) Январь\n2) Февраль\n3) Март\n4) Апрель\n5) Май\n6) Июнь\n7) Июль\n8) Август\n9) Сентябрь\n10) Октябрь\n11) Ноябрь\n12) Декабрь\n-->");    
        byte month = scanner.nextByte();
        for (int i = 0; i< historyFacade.findAll().size();i++){
            if (historyFacade.findAll().get(i) != null && month-1 == historyFacade.findAll().get(i).getDateOfBuying().getMonth()){
                System.out.println(historyFacade.findAll().get(i).toString());
                for (int j = 0; j< antiHistoryFacade.findAll().size();j++){
                    if(antiHistoryFacade.findAll().get(j) != null && month-1 == antiHistoryFacade.findAll().get(j).getDateOfVozvrata().getMonth()){
                        System.out.println(antiHistoryFacade.findAll().get(j).toString());
                    }
                }
            } else {
                System.out.println("Error");
            }
        }
        if(easyMoney >= 0){
            System.out.println("Общий доход по всем месяцам - " + easyMoney);
        }
    }
    private void productUpdate(){
        if (productFacade.findAll().get(0) != null){
            for(int i = 0; i < productFacade.findAll().size(); i++){
                if(productFacade.findAll().get(0) != null){
                    System.out.println((i+1) + ")" + productFacade.findAll().get(i).toString());
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
                    productFacade.findAll().get(otvetproduct-1).setBywho(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Модель - ");
                    productFacade.findAll().get(otvetproduct-1).setModell(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Цена - "); 
                    productFacade.findAll().get(otvetproduct-1).setPrice(inputDouble());
                    break;
                case 4: 
                    System.out.println("Размер - "); 
                    productFacade.findAll().get(otvetproduct-1).setSize(inputDouble());
                    break;
                case 5:
                    System.out.println("Кол-во - "); 
                    productFacade.findAll().get(otvetproduct-1).setPiece(inputInt());
                    productFacade.findAll().get(otvetproduct-1).setMaxPiece(productFacade.findAll().get(otvetproduct-1).getPiece());
                    break;
                default:
                    System.out.println("Error");
            }
            productFacade.edit(productFacade.findAll().get(otvetproduct-1));
        }
        else{
            System.out.println("Массив продуктов пуст");
        }
    }
    private void clientUpdate(){
        if (clientFacade.findAll().get(0) != null){
            for(int i = 0; i < clientFacade.findAll().size(); i++){
                if(clientFacade.findAll().get(0) != null){
                    System.out.println((i+1) + ")" + clientFacade.findAll().get(i).toString());
                }
            }   
            System.out.println("Какого клиента хотите изменить?");
            byte otvetclient = scanner.nextByte();
            scanner.nextLine();
            System.out.println("Какую часть хотите изменить?\nИмя - \"1\", \nФамилия - \"2\", \nНомер телефона - \"3\", \nДеньги клиента - \"4\"");
            byte otvetclient1 = scanner.nextByte();
            scanner.nextLine();
            switch(otvetclient1){
                case 1:
                    System.out.println("Имя - ");
                    clientFacade.findAll().get(otvetclient-1).setClientName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Фамилия - ");
                    clientFacade.findAll().get(otvetclient-1).setClientSurname(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Номер телефона - ");
                    clientFacade.findAll().get(otvetclient-1).setClientNumber(inputInt());
                    break;
                case 4:
                    System.out.println("Деньги клиента - ");
                    clientFacade.findAll().get(otvetclient-1).setClientMoney(inputDouble());
                    break;
                default:
                    System.out.println("Error");
            }
            clientFacade.edit(clientFacade.findAll().get(otvetclient-1));
        }
        else{
            System.out.println("Массив клиентов пуст");
        }
    }
    private Client addClient(){
        Client client = new Client();
        System.out.print("Имя - ");
        client.setClientName(scanner.nextLine());
        System.out.print("Фамилия - ");
        client.setClientSurname(scanner.nextLine());
        System.out.print("Номер телефона - ");
        client.setClientNumber(inputInt());
        System.out.print("Деньги - ");
        client.setClientMoney(inputDouble());
        return client;
    }

    private Product addProduct() {
        Product product = new Product();
        System.out.print("Модель - ");
        product.setModell(scanner.nextLine());
        System.out.print("Размер - ");
        product.setSize(inputDouble());
        System.out.print("Фирма - ");
        product.setBywho(scanner.nextLine());
        System.out.print("Цена - ");
        product.setPrice(inputDouble());
        System.out.print("Количество пар обуви - ");
        product.setPiece(inputInt());
        product.setMaxPiece(product.getPiece());
        if(product.getPiece() < 1){
            System.out.println("Нету обуви");
            product = null;
        }
        return product;
    }
    private History addHistory(){
        History history = null;
        if(clientFacade.findAll().get(0) != null){
            history = new History();
            System.out.println("Список покупателей - ");
            for(int i = 0; i < clientFacade.findAll().size(); i++){
                if(clientFacade.findAll().get(0) != null){
                    System.out.println((i+1) + ")" + clientFacade.findAll().get(i).toString());
                }
            }
            System.out.println("Номер покупателя - ");
            int clientNumb = inputInt();
            history.setClientNumber(clientFacade.findAll().get(clientNumb-1).getClientNumber());
            history.setClientName(clientFacade.findAll().get(clientNumb-1).getClientName());
            System.out.println("Список обуви - ");
            for(int i = 0; i < productFacade.findAll().size(); i++){
                if(productFacade.findAll().get(0) != null){
                    System.out.println((i+1) + ")" + productFacade.findAll().get(i).toString());
                }
            }
            System.out.println("Номер обуви - ");
            int productNumb = inputInt();
            history.setProduct(productFacade.findAll().get(productNumb-1).getModell());
            Calendar c = new GregorianCalendar();
            history.setSize(productFacade.findAll().get(productNumb-1).getSize());
            history.setDateOfBuying(c.getTime());
            if(clientFacade.findAll().get(clientNumb-1).getClientMoney() >= productFacade.findAll().get(productNumb-1).getPrice() && productFacade.findAll().get(productNumb-1).getPiece() > 0){
                clientFacade.findAll().get(clientNumb-1).setClientMoney(clientFacade.findAll().get(clientNumb-1).getClientMoney() - productFacade.findAll().get(productNumb-1).getPrice());
                /*wages.setWages((double) (wages.getWages()+productFacade.findAll().get(productNumb-1).getPrice()));*/
                productFacade.findAll().get(productNumb-1).setPiece(productFacade.findAll().get(productNumb-1).getPiece() - 1);
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
            for (int i = 0; i< historyFacade.findAll().size();i++){
                if (historyFacade.findAll().get(i) != null){
                    System.out.println((i+1) + ")" + historyFacade.findAll().get(i).toString());
                }
            }
            int otvet = inputInt();
            if(historyFacade.findAll().get(0) != null){
                for (int i = 0; i< clientFacade.findAll().size();i++){
                    if(clientFacade.findAll().get(i) != null){
                        if(clientFacade.findAll().get(i).getClientNumber() == historyFacade.findAll().get(otvet-1).getClientNumber()){
                            for (int b = 0; b< productFacade.findAll().size();b++){
                                if(productFacade.findAll().get(b) != null && productFacade.findAll().get(b).getModell() == historyFacade.findAll().get(otvet-1).getProduct() && productFacade.findAll().get(b).getSize() == historyFacade.findAll().get(otvet-1).getSize()){
                                    System.out.println("sdsd");
                                    if(productFacade.findAll().get(b).getMaxPiece() > productFacade.findAll().get(b).getPiece()){
                                        clientFacade.findAll().get(i).setClientMoney(clientFacade.findAll().get(i).getClientMoney() + productFacade.findAll().get(b).getPrice());
                                        productFacade.findAll().get(b).setPiece(productFacade.findAll().get(b).getPiece()+1);
                                        /*wages.setWages((double) (wages.getWages()-productFacade.findAll().get(b).getPiece()));*/
                                        antihistory.setClientNumber(clientFacade.findAll().get(i).getClientNumber());
                                        Calendar c = new GregorianCalendar();
                                        antihistory.setDateOfVozvrata(c.getTime());
                                        antihistory.setProduct(productFacade.findAll().get(i).getModell());
                                        antihistory.setClient(clientFacade.findAll().get(i).getClientName());
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
    private int inputInt(){
        do {
            try {
                String inputedNumber = scanner.nextLine();
                return Integer.parseInt(inputedNumber);
            } catch(Exception e) {
                System.out.println("Введены неверные данные.");
                System.out.print("Попробуйте еще раз-->");
            }
        } while(true);
    }
    private double inputDouble() {
	do {
            try {
                String inputedNumber = scanner.nextLine();
                return Double.parseDouble(inputedNumber);
            } catch(Exception e) {
                System.out.println("Введены неверные данные.");
                System.out.print("Попробуйте еще раз -->");
            }
	} while(true);
    }
}
