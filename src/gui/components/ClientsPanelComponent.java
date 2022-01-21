/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import facade.ClientFacade;
import gui.guiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import shopboots.classes.Client;

/**
 *
 * @author anana
 */
public class ClientsPanelComponent extends JPanel {
    private ClientFacade clientFacade;
    public ClientsPanelComponent() {
        initComponents();
    }

    private void initComponents() {
        clientFacade = new ClientFacade(Client.class);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        CaptionComponent captionComponent = new CaptionComponent("Добавление клиента в базу клиентов", guiApp.WIDTH, 30);
        this.add(captionComponent);
        InfoComponent infoComponent = new InfoComponent("", guiApp.WIDTH, 50);
        this.add(infoComponent);
        EditComponent name = new EditComponent("Имя:", 250, 30, 120);
        this.add(name);
        EditComponent surName = new EditComponent("Фамилия:", 250, 30, 120);
        this.add(surName);
        EditComponent phone = new EditComponent("Номер телефона:", 250, 30, 120);
        this.add(phone);
        EditComponent money = new EditComponent("Деньги:", 250, 30, 120);
        this.add(money);
        ButtonComponent buttonComponent = new ButtonComponent("Добавить клиента", 100, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener((ActionEvent evt) -> {
            if(        !name.getEditor().getText().isEmpty()
                    && !surName.getEditor().getText().isEmpty()
                    && !phone.getEditor().getText().isEmpty()
                    && !money.getEditor().getText().isEmpty()){
                try{
                    Client client = new Client();
                    client.setClientName(name.getEditor().getText());
                    client.setClientSurname(surName.getEditor().getText());
                    client.setClientNumber(Integer.parseInt(phone.getEditor().getText()));
                    client.setClientMoney(Double.parseDouble(money.getEditor().getText()));
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Успех!");
                    clientFacade.create(client);
                    name.getEditor().setText("");
                    surName.getEditor().setText("");
                    phone.getEditor().setText("");
                    money.getEditor().setText("");
                }catch(NumberFormatException e){
                    Client client = new Client();
                    infoComponent.getInfo().setForeground(Color.red);
                    try{
                        client.setClientName(name.getEditor().getText());
                    }
                    catch(Exception ex){
                        infoComponent.getInfo().setText("Введите имя КОРРЕКТНО");
                    }
                    try{
                        client.setClientSurname(surName.getEditor().getText());
                    }
                    catch(NumberFormatException ex){
                        infoComponent.getInfo().setText("Введите фамилию КОРРЕКТНО");
                    }
                    try{
                        client.setClientNumber(Integer.parseInt(phone.getEditor().getText()));
                    }
                    catch(NumberFormatException ex){
                        infoComponent.getInfo().setText("Введите номер КОРРЕКТНО");
                    }
                    try{
                        client.setClientMoney(Double.parseDouble(money.getEditor().getText()));
                    }
                    catch(NumberFormatException ex){
                        infoComponent.getInfo().setText("Введите деньги КОРРЕКТНО");
                    }
                    
                }
            }
            else{
                infoComponent.getInfo().setForeground(Color.red);
                infoComponent.getInfo().setText("ВСЕ поля обязательны");
            }
        });
    }
}
