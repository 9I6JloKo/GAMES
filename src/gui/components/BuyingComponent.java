/*
 * To change panel license header, choose License Headers in Project Properties.
 * To change panel template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import facade.ClientFacade;
import facade.HistoryFacade;
import facade.ProductFacade;
import gui.guiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import shopboots.classes.Client;
import shopboots.classes.History;
import shopboots.classes.Product;

/**
 *
 * @author anana
 */
public class BuyingComponent extends JPanel{
    private ProductFacade productFacade;
    private ClientFacade clientFacade;
    private HistoryFacade historyFacade;
    public BuyingComponent() {
        initComponents();
    }

    private JPanel initComponents() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,450));
        panel.setMinimumSize(this.getPreferredSize());
        panel.setMaximumSize(this.getPreferredSize());
        this.add(panel);
        productFacade = new ProductFacade(Product.class);
        clientFacade = new ClientFacade(Client.class);
        historyFacade = new HistoryFacade(History.class);
        CaptionComponent captionComponent = new CaptionComponent("Создание покупки", guiApp.WIDTH, 50);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(captionComponent);
        InfoComponent infoComponent = new InfoComponent("", guiApp.WIDTH, 25);
        panel.add(infoComponent);
        ListClientsComponent listClientsComponent = new ListClientsComponent("Клиенты:", 400, 145, 200);
        List<Client> clients = clientFacade.findAll();
        ListBootsComponent listBootsComponent = new ListBootsComponent("Обувь:", 70, 145, 200);
        List<Product> products = productFacade.findAll();
        if(!clients.isEmpty() && !products.isEmpty()){
            panel.add(listClientsComponent);
            panel.add(listBootsComponent);
        }else{
            infoComponent.getInfo().setForeground(Color.red);
            infoComponent.getInfo().setText("Добавьте клиентов и/или обувь и нажмите на кнопку для перезапуска");
        }
        panel.add(listClientsComponent);
        panel.add(listBootsComponent);
        ButtonComponent buttonComponent = new ButtonComponent("Сделать покупку", 100, 175, 250);
        panel.add(buttonComponent);
        buttonComponent.getButton().addActionListener((ActionEvent evt) -> {
            if(!clients.isEmpty() && !products.isEmpty()){
                panel.add(listClientsComponent);
                panel.add(listBootsComponent);
            }else{
                infoComponent.getInfo().setForeground(Color.red);
                infoComponent.getInfo().setText("Вы всё ещё не добавили клиента/обувь");
            }
            panel.revalidate();
            panel.repaint();
            List<Product> products1 = listBootsComponent.getList().getSelectedValuesList();
            List<Client> clients1 = listClientsComponent.getList().getSelectedValuesList();
            if (!products1.isEmpty() && !clients1.isEmpty() && clients1.get(0).getClientMoney() >= products1.get(0).getPrice() && products1.get(0).getPiece() >= 1) {
                try {
                    products1.get(0).setPiece(products1.get(0).getPiece() - 1);
                    clients1.get(0).setClientMoney(clients1.get(0).getClientMoney() - products1.get(0).getPrice());
                    LocalDate localDate = LocalDate.now();
                    localDate = localDate.plusWeeks(2);
                    History history = new History();
                    history.setClientNumber(clients1.get(0).getClientNumber());
                    history.setClientName(clients1.get(0).getClientName());
                    history.setProduct(products1.get(0).getModell());
                    history.setSize(products1.get(0).getSize());
                    history.setLocalDate(localDate);
                    Calendar c = new GregorianCalendar();
                    history.setDateOfBuying(c.getTime());
                    historyFacade.create(history);
                    clientFacade.edit(clients1.get(0));
                    productFacade.edit(products1.get(0));
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Успех!");
                }catch(Exception e){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Непредвиденная ошибка");
                }
            } else {
                infoComponent.getInfo().setForeground(Color.red);
                infoComponent.getInfo().setText("Выберите правильно клиента и обувь");
            }
        });
        return this;
    }
}
