/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import facade.AntiHistoryFacade;
import facade.ClientFacade;
import facade.HistoryFacade;
import facade.ProductFacade;
import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.InfoComponent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import shopboots.classes.AntiHistory;
import shopboots.classes.Client;
import shopboots.classes.History;
import shopboots.classes.Product;
import gui.components.EditComponent;
import gui.components.ListBootsComponent;
import gui.components.ListClientsComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
/**
 *
 * @author anana
 */
public class guiApp extends javax.swing.JFrame {
    public static final int WIDTH = 600;
    private ProductFacade productFacade;
    private ClientFacade clientFacade;
    private HistoryFacade historyFacade;
    private AntiHistoryFacade antiHistoryFacade;
    private void init(){
        productFacade = new ProductFacade(Product.class);
        clientFacade = new ClientFacade(Client.class);
        historyFacade = new HistoryFacade(History.class);
        antiHistoryFacade = new AntiHistoryFacade(AntiHistory.class);
    }
    public guiApp() {
        init();
        initComponents();
    }                     
    private void initComponents() {
        this.setPreferredSize(new Dimension(600,450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ShopBoots");
        setResizable(false);
        
        jPanel1 = new javax.swing.JPanel();
        
        JTabbedPane managerTabbed = new JTabbedPane();
        managerTabbed.setPreferredSize(new Dimension(600,10));
        managerTabbed.setMinimumSize(managerTabbed.getPreferredSize());
        managerTabbed.setMaximumSize(managerTabbed.getPreferredSize());
        JPanel bootsPanel = new JPanel();
        this.add(bootsPanel);
        JPanel clientPanel = new JPanel();
        this.add(clientPanel);
        JPanel buyPanel = new JPanel();
        this.add(buyPanel);
        JPanel backPanel = new JPanel();
        this.add(backPanel);
        JPanel changebPanel = new JPanel();
        this.add(changebPanel);
        JPanel changecPanel = new JPanel();
        this.add(changecPanel);
        managerTabbed.addTab("Добавить обувь", bootsPanel);
        managerTabbed.addTab("Добавить клиента", clientPanel);
        managerTabbed.addTab("Выполнить покупку", buyPanel);
        managerTabbed.addTab("Выполнить возврат", backPanel);
        managerTabbed.addTab("Изменить обувь", changebPanel);
        managerTabbed.addTab("изменить клиента", changecPanel);
        this.add(managerTabbed);
        
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bootsPanel.setLayout(new BoxLayout(bootsPanel, BoxLayout.Y_AXIS));
        bootsPanel.add(Box.createRigidArea(new Dimension(0,25)));
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        captionComponent = new CaptionComponent("Добавление обуви в магазин", this.getWidth(), 30);
        bootsPanel.add(captionComponent);
        InfoComponent infoComponent = new InfoComponent("", this.getWidth(), 50);
        bootsPanel.add(infoComponent);
        EditComponent editComponent = new EditComponent("Модель:", 250, 30, 120);
        bootsPanel.add(editComponent);
        EditComponent editComponent1 = new EditComponent("Размер:", 250, 30, 40);
        bootsPanel.add(editComponent1);
        EditComponent editComponent2 = new EditComponent("Фирма:", 250, 30, 120);
        bootsPanel.add(editComponent2);
        EditComponent editComponent3 = new EditComponent("Цена:", 250, 30, 40);
        bootsPanel.add(editComponent3);
        EditComponent editComponent4 = new EditComponent("Кол-во пар:", 250, 30, 40);
        bootsPanel.add(editComponent4);
        ButtonComponent buttonComponent = new ButtonComponent("Добавить обувь", 100, 350, 150);
        bootsPanel.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try{
                    if(        !editComponent.getEditor().getText().isEmpty()
                            && !editComponent1.getEditor().getText().isEmpty()
                            && !editComponent2.getEditor().getText().isEmpty()
                            && !editComponent3.getEditor().getText().isEmpty()
                            && !editComponent4.getEditor().getText().isEmpty()
                            && Integer.parseInt(editComponent4.getEditor().getText()) >= 1){
                        try{
                            Product product = new Product();
                            product.setModell(editComponent.getEditor().getText());
                            product.setSize(Double.parseDouble(editComponent1.getEditor().getText()));
                            product.setBywho(editComponent2.getEditor().getText());
                            product.setPrice(Double.parseDouble(editComponent3.getEditor().getText()));
                            product.setMaxPiece(Integer.parseInt(editComponent4.getEditor().getText()));
                            product.setPiece(Integer.parseInt(editComponent4.getEditor().getText()));
                            infoComponent.getInfo().setForeground(Color.BLUE);
                            infoComponent.getInfo().setText("Успех!");
                            productFacade.create(product);
                            editComponent.getEditor().setText("");
                            editComponent1.getEditor().setText("");
                            editComponent2.getEditor().setText("");
                            editComponent3.getEditor().setText("");
                            editComponent4.getEditor().setText("");
                        }catch(Exception e){
                            Product product = new Product();
                            infoComponent.getInfo().setForeground(Color.red);
                            try{
                                product.setModell(editComponent.getEditor().getText());
                            }
                            catch(Exception ex){
                                infoComponent.getInfo().setText("Введите модель КОРРЕКТНО");
                            }
                            try{
                                product.setSize(Double.parseDouble(editComponent1.getEditor().getText()));
                            }
                            catch(Exception ex){
                                infoComponent.getInfo().setText("Введите размер КОРРЕКТНО");
                            }
                            try{
                                product.setBywho(editComponent2.getEditor().getText());
                            }
                            catch(Exception ex){
                                infoComponent.getInfo().setText("Введите Фирму КОРРЕКТНО");
                            }
                            try{
                                product.setPrice(Double.parseDouble(editComponent3.getEditor().getText()));
                            }
                            catch(Exception ex){
                                infoComponent.getInfo().setText("Введите цену КОРРЕКТНО");
                            }
                            try{
                                product.setMaxPiece(Integer.parseInt(editComponent4.getEditor().getText()));
                            }
                            catch(Exception ex){
                                infoComponent.getInfo().setText("Введите кол-во КОРРЕКТНО");
                            }
                        }
                    }
                    else{
                        infoComponent.getInfo().setForeground(Color.red);
                        infoComponent.getInfo().setText("ВСЕ поля обязательны");
                    }
                }catch(Exception ex){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите кол-во КОРРЕКТНО");
                }
            }
        });
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
        clientPanel.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Добавление клиента в базу клиентов", this.getWidth(), 30);
        clientPanel.add(captionComponent);
        InfoComponent infoComponent1 = new InfoComponent("", this.getWidth(), 50);
        clientPanel.add(infoComponent1);
        EditComponent editComponent5 = new EditComponent("Имя:", 250, 30, 120);
        clientPanel.add(editComponent5);
        EditComponent editComponent6 = new EditComponent("Фамилия:", 250, 30, 120);
        clientPanel.add(editComponent6);
        EditComponent editComponent7 = new EditComponent("Номер телефона:", 250, 30, 120);
        clientPanel.add(editComponent7);
        EditComponent editComponent8 = new EditComponent("Деньги:", 250, 30, 120);
        clientPanel.add(editComponent8);
        ButtonComponent buttonComponent1 = new ButtonComponent("Добавить клиента", 100, 350, 150);
        clientPanel.add(buttonComponent1);
        buttonComponent1.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(        !editComponent5.getEditor().getText().isEmpty()
                        && !editComponent6.getEditor().getText().isEmpty()
                        && !editComponent7.getEditor().getText().isEmpty()
                        && !editComponent8.getEditor().getText().isEmpty()){
                    try{
                        Client client = new Client();
                        client.setClientName(editComponent5.getEditor().getText());
                        client.setClientSurname(editComponent6.getEditor().getText());
                        client.setClientNumber(Integer.parseInt(editComponent7.getEditor().getText()));
                        client.setClientMoney(Double.parseDouble(editComponent8.getEditor().getText()));
                        infoComponent1.getInfo().setForeground(Color.BLUE);
                        infoComponent1.getInfo().setText("Успех!");
                        clientFacade.create(client);
                        ClientFacade clientFacade1 = clientFacade;
                        editComponent5.getEditor().setText("");
                        editComponent6.getEditor().setText("");
                        editComponent7.getEditor().setText("");
                        editComponent8.getEditor().setText("");
                    }catch(NumberFormatException e){
                        Client client = new Client();
                        infoComponent1.getInfo().setForeground(Color.red);
                        try{
                            client.setClientName(editComponent5.getEditor().getText());
                        }
                        catch(Exception ex){
                            infoComponent1.getInfo().setText("Введите имя КОРРЕКТНО");
                        }
                        try{
                            client.setClientSurname(editComponent6.getEditor().getText());
                        }
                        catch(NumberFormatException ex){
                            infoComponent1.getInfo().setText("Введите фамилию КОРРЕКТНО");
                        }
                        try{
                            client.setClientNumber(Integer.parseInt(editComponent7.getEditor().getText()));
                        }
                        catch(NumberFormatException ex){
                            infoComponent1.getInfo().setText("Введите номер КОРРЕКТНО");
                        }
                        try{
                            client.setClientMoney(Double.parseDouble(editComponent8.getEditor().getText()));
                        }
                        catch(NumberFormatException ex){
                            infoComponent1.getInfo().setText("Введите деньги КОРРЕКТНО");
                        }

                    }
                }
                else{
                    infoComponent1.getInfo().setForeground(Color.red);
                    infoComponent1.getInfo().setText("ВСЕ поля обязательны");
                }
            }
        });
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        captionComponent = new CaptionComponent("Создание покупки", this.getWidth(), 50);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        buyPanel.add(captionComponent);
        InfoComponent infoComponent2 = new InfoComponent("", this.getWidth(), 25);
        buyPanel.add(infoComponent2);
        ListClientsComponent listClientsComponent = new ListClientsComponent("Клиенты:", 400, 145, 200);
        List<Client> clients = clientFacade.findAll();
        ListBootsComponent listBootsComponent = new ListBootsComponent("Обувь:", 70, 145, 200);
        List<Product> products = productFacade.findAll();
        if(!clients.isEmpty() && !products.isEmpty()){
            jPanel.add(listClientsComponent);
            jPanel.add(listBootsComponent);
        }else{
            infoComponent2.getInfo().setForeground(Color.red);
            infoComponent2.getInfo().setText("Добавьте клиентов и/или обувь и нажмите на кнопку для перезапуска");
        }
        jPanel.add(listClientsComponent);
        jPanel.add(listBootsComponent);
        buyPanel.add(jPanel);
        ButtonComponent buttonComponent2 = new ButtonComponent("Сделать покупку", 100, 175, 250);
        buyPanel.add(buttonComponent2);
        buttonComponent2.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!clients.isEmpty() && !products.isEmpty()){
                    jPanel.add(listClientsComponent);
                    jPanel.add(listBootsComponent);
                }else{
                    infoComponent2.getInfo().setForeground(Color.red);
                    infoComponent2.getInfo().setText("Вы всё ещё не добавили клиента/обувь");
                }
                List<Product> products = listBootsComponent.getList().getSelectedValuesList();
                List<Client> clients = listClientsComponent.getList().getSelectedValuesList();
                if(        !products.isEmpty()
                        && !clients.isEmpty()
                        && clients.get(0).getClientMoney() >= products.get(0).getPrice()
                        && products.get(0).getPiece() >= 1){
                    try{
                        products.get(0).setPiece(products.get(0).getPiece() - 1);
                        clients.get(0).setClientMoney(clients.get(0).getClientMoney() - products.get(0).getPrice());
                        LocalDate localDate = LocalDate.now();
                        localDate = localDate.plusWeeks(2);
                        History history = new History();
                        history.setClientNumber(clients.get(0).getClientNumber());
                        history.setClientName(clients.get(0).getClientName());
                        history.setProduct(products.get(0).getModell());
                        history.setSize(products.get(0).getSize());
                        history.setLocalDate(localDate);
                        Calendar c = new GregorianCalendar();
                        history.setDateOfBuying(c.getTime());
                        historyFacade.create(history);
                        clientFacade.edit(clients.get(0));
                        productFacade.edit(products.get(0));
                        infoComponent2.getInfo().setForeground(Color.BLUE);
                        infoComponent2.getInfo().setText("Успех!");
                    }catch(Exception e){
                        infoComponent2.getInfo().setForeground(Color.red);
                        infoComponent2.getInfo().setText("Непредвиденная ошибка");
                    }
                }
                else{
                    infoComponent2.getInfo().setForeground(Color.red);
                    infoComponent2.getInfo().setText("Выберите правильно клиента и обувь");
                }
            }
        });
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//        captionComponent = new CaptionComponent("Возврат товара", this.getWidth(), 30);
//        backPanel.add(captionComponent);
//        InfoComponent infoComponent3 = new InfoComponent("", this.getWidth(), 30);
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        changebPanel.setLayout(new BoxLayout(changebPanel, BoxLayout.Y_AXIS));
        changebPanel.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Изменение обуви", this.getWidth(), 25);
        InfoComponent infoComponent4 = new InfoComponent("", this.getWidth(), 50);
        changebPanel.add(captionComponent);
        changebPanel.add(infoComponent4);
        ListBootsComponent listBootsComponent1 = new ListBootsComponent("Обувь:", 100, 145, 375);
        changebPanel.add(listBootsComponent1);
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));
        JRadioButton jRadioButton1 = new JRadioButton();
        jRadioButton1.setText("Продавец;");
        JRadioButton jRadioButton2 = new JRadioButton();
        jRadioButton2.setText("Модель;");
        JRadioButton jRadioButton3 = new JRadioButton();
        jRadioButton3.setText("Цена;");
        JRadioButton jRadioButton4 = new JRadioButton();
        jRadioButton4.setText("Размер;");
        JRadioButton jRadioButton5 = new JRadioButton();
        jRadioButton5.setText("Кол-во");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);
        buttonGroup.add(jRadioButton4);
        buttonGroup.add(jRadioButton5);
        jPanel2.add(jRadioButton1);
        jPanel2.add(jRadioButton2);
        jPanel2.add(jRadioButton3);
        jPanel2.add(jRadioButton4);
        jPanel2.add(jRadioButton5);
        changebPanel.add(jPanel2);
        EditComponent editComponent9 = new EditComponent("Введите на что менять:", 250, 50, 120);
        changebPanel.add(editComponent9);
        ButtonComponent buttonComponent3 = new ButtonComponent("Изменить обувь", 25, 175, 250);
        changebPanel.add(buttonComponent3);
        
        buttonComponent3.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                List<Product> products1 = listBootsComponent1.getList().getSelectedValuesList();
                if(!products1.isEmpty() && !editComponent9.getEditor().getText().isEmpty()){
                    try{
                        if(jRadioButton1.isSelected()){
                            products1.get(0).setBywho(editComponent9.getEditor().getText());
                        }
                        else if(jRadioButton2.isSelected()){
                            products1.get(0).setModell(editComponent9.getEditor().getText());
                        }
                        else if(jRadioButton3.isSelected()){
                            products1.get(0).setPrice(Double.parseDouble(editComponent9.getEditor().getText()));
                        }
                        else if(jRadioButton4.isSelected()){
                            products1.get(0).setSize(Double.parseDouble(editComponent9.getEditor().getText()));
                        }
                        else if(jRadioButton5.isSelected()){
                            products1.get(0).setPiece(Integer.parseInt(editComponent9.getEditor().getText()));
                            products1.get(0).setMaxPiece(Integer.parseInt(editComponent9.getEditor().getText()));
                        }
                        else{
                            infoComponent4.getInfo().setForeground(Color.red);
                            infoComponent4.getInfo().setText("Выберите что изменить");
                        }
                        infoComponent4.getInfo().setForeground(Color.BLUE);
                        infoComponent4.getInfo().setText("Успех!");
                        productFacade.edit(products1.get(0));

                    }
                    catch(Exception e){
                        infoComponent4.getInfo().setForeground(Color.red);
                        infoComponent4.getInfo().setText("Неправильный тип данных");
                    }
                }
                else{
                    infoComponent4.getInfo().setForeground(Color.red);
                    infoComponent4.getInfo().setText("Выполните все действия");
                }
                

            }
        });
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        changecPanel.setLayout(new BoxLayout(changecPanel, BoxLayout.Y_AXIS));
        changecPanel.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Изменение клиента", this.getWidth(), 25);
        InfoComponent infoComponent5 = new InfoComponent("", this.getWidth(), 50);
        changecPanel.add(captionComponent);
        changecPanel.add(infoComponent5);
        ListClientsComponent listClientsComponent1 = new ListClientsComponent("Клиенты:", 100, 145, 375);
        changecPanel.add(listClientsComponent1);
        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.X_AXIS));
        JRadioButton jRadioButton6 = new JRadioButton();
        jRadioButton6.setText("Имя;");
        JRadioButton jRadioButton7 = new JRadioButton();
        jRadioButton7.setText("Фамилия;");
        JRadioButton jRadioButton8 = new JRadioButton();
        jRadioButton8.setText("Номер телефона;");
        JRadioButton jRadioButton9 = new JRadioButton();
        jRadioButton9.setText("Деньги клиента;");
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup.add(jRadioButton6);
        buttonGroup.add(jRadioButton7);
        buttonGroup.add(jRadioButton8);
        buttonGroup.add(jRadioButton9);
        jPanel3.add(jRadioButton6);
        jPanel3.add(jRadioButton7);
        jPanel3.add(jRadioButton8);
        jPanel3.add(jRadioButton9);
        changecPanel.add(jPanel3);
        EditComponent editComponent10 = new EditComponent("Введите на что менять:", 250, 50, 120);
        changecPanel.add(editComponent10);
        ButtonComponent buttonComponent4 = new ButtonComponent("Изменить обувь", 25, 175, 250);
        changecPanel.add(buttonComponent4);
        
        buttonComponent4.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                List<Client> clients1 = listClientsComponent1.getList().getSelectedValuesList();
                if(!clients1.isEmpty() && !editComponent10.getEditor().getText().isEmpty()){
                    try{
                        if(jRadioButton6.isSelected()){
                            clients1.get(0).setClientName(editComponent10.getEditor().getText());
                        }
                        else if(jRadioButton7.isSelected()){
                            clients1.get(0).setClientSurname(editComponent10.getEditor().getText());
                        }
                        else if(jRadioButton8.isSelected()){
                            clients1.get(0).setClientNumber(Integer.parseInt(editComponent10.getEditor().getText()));
                        }
                        else if(jRadioButton9.isSelected()){
                            clients1.get(0).setClientMoney(Double.parseDouble(editComponent10.getEditor().getText()));
                        }
                        else{
                            infoComponent5.getInfo().setForeground(Color.red);
                            infoComponent5.getInfo().setText("Выберите что изменить");
                        }
                        infoComponent5.getInfo().setForeground(Color.BLUE);
                        infoComponent5.getInfo().setText("Успех!");
                        clientFacade.edit(clients1.get(0));
                    }
                    catch(Exception e){
                        infoComponent5.getInfo().setForeground(Color.red);
                        infoComponent5.getInfo().setText("Неправильный тип данных");
                    }
                }
                else{
                    infoComponent5.getInfo().setForeground(Color.red);
                    infoComponent5.getInfo().setText("Выполните все действия");
                }
            }
        });
        pack();
    }// </editor-fold>                        

            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(guiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(guiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(guiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(guiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new guiApp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify      
    private JPanel jPanel1;
    private CaptionComponent captionComponent;
    // End of variables declaration                   
}
