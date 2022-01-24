/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import facade.ClientFacade;
import facade.HistoryFacade;
import facade.ProductFacade;
import gui.components.BootsPanelComponent;
import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.ClientsPanelComponent;
import gui.components.InfoComponent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import shopboots.classes.Client;
import shopboots.classes.History;
import shopboots.classes.Product;
import gui.components.EditComponent;
import gui.components.GuestPanelComponent;
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
    private final ProductFacade productFacade = new ProductFacade(Product.class);
    private final ClientFacade clientFacade = new ClientFacade(Client.class);
    private final HistoryFacade historyFacade = new HistoryFacade(History.class);
    private JTabbedPane managerTabbed = new JTabbedPane();
    private JTabbedPane managerTabbedmain = new JTabbedPane();
    private final JPanel buyPanel = new JPanel();
    private final JPanel changebPanel = new JPanel();
    private final JPanel changecPanel = new JPanel();
    public guiApp() {
        initComponents();
    }                     
    private void initComponents() {
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(600,450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ShopBoots");
        setResizable(false);
        
        managerTabbedmain = new JTabbedPane();
        managerTabbedmain.setPreferredSize(new Dimension(600,30));
        managerTabbedmain.setMinimumSize(managerTabbedmain.getPreferredSize());
        managerTabbedmain.setMaximumSize(managerTabbedmain.getPreferredSize());
        GuestPanelComponent guestPanel = new GuestPanelComponent();
        JPanel enteringPanel = new JPanel();
        managerTabbedmain.addTab("Обувь", guestPanel);
        managerTabbedmain.addTab("Вход", enteringPanel);
        this.add(managerTabbedmain);
        enteringPanel.setLayout(new BoxLayout(enteringPanel, BoxLayout.Y_AXIS));
        enteringPanel.add(Box.createRigidArea(new Dimension(600, 30)));
        captionComponent = new CaptionComponent("Вход", this.getWidth(), 50);
        InfoComponent infoFirst = new InfoComponent("", this.getWidth(), 50);
        enteringPanel.add(captionComponent);
        enteringPanel.add(infoFirst);
        EditComponent loginfirst = new EditComponent("Логин:", 200, 50, 200);
        enteringPanel.add(loginfirst);
        EditComponent passwordfirst = new EditComponent("Пароль:", 200, 50, 200);
        enteringPanel.add(passwordfirst);
        ButtonComponent enter = new ButtonComponent("Войти", 80, 175, 250);
        enteringPanel.add(enter);
        List<Client> clients = clientFacade.findAll();
        enter.getButton().addActionListener((ActionEvent evt) -> {
            try{
                if( !loginfirst.getEditor().getText().isEmpty() &&
                    !passwordfirst.getEditor().getText().isEmpty()){
                        if(!"maksim".equals(loginfirst.getEditor().getText()) ||
                            !"12345".equals(passwordfirst.getEditor().getText())){
                            if(!clients.isEmpty()){
                                for(Client client : clients){
                                    if(client.getLogin().equals(loginfirst.getEditor().getText()) &&
                                            client.getPassword().equals(passwordfirst.getEditor().getText()) &&
                                            client.getLevel().equals("client")){
                                        managerTabbed.removeTabAt(0);
                                        managerTabbed.removeTabAt(0);
                                        managerTabbed.removeTabAt(1);
                                        this.remove(managerTabbedmain);
                                        this.add(managerTabbed);
                                        this.revalidate();
                                        this.repaint();
                                        break;
                                    }
                                    else if(client.getLogin().equals(loginfirst.getEditor().getText()) &&
                                            client.getPassword().equals(passwordfirst.getEditor().getText()) &&
                                            client.getLevel().equals("manager")){
                                        managerTabbed.removeTabAt(2);
                                        this.remove(managerTabbedmain);
                                        this.add(managerTabbed);
                                        this.revalidate();
                                        this.repaint();
                                        break;
                                    }
                                    else{
                                        infoFirst.getInfo().setForeground(Color.red);
                                        infoFirst.getInfo().setText("Ошибка ввода данных. Обратитесь к админу(проверьте лог и пароль)");
                                        break;
                                    }    
                                }
                            }
                            else{
                                    infoFirst.getInfo().setForeground(Color.red);
                                    infoFirst.getInfo().setText("Нет таких пользователей");                            
                            }
                        }
                        else{
                            this.remove(managerTabbedmain);
                            managerTabbedmain.setEnabled(false);
                            managerTabbedmain.setVisible(false);
                            this.add(managerTabbed);
                            this.revalidate(); // вход sysadmin
                            this.repaint();  
                        }
                }
                else{
                    infoFirst.getInfo().setForeground(Color.red);
                    infoFirst.getInfo().setText("Все поля обязательны");
                }
            }catch(Exception ex){
                infoFirst.getInfo().setForeground(Color.red);
                infoFirst.getInfo().setText("Неправильный тип данных");
            }
        });
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        jPanel1 = new javax.swing.JPanel();
        managerTabbed = new JTabbedPane();
        managerTabbed.setPreferredSize(new Dimension(600,10));
        managerTabbed.setMinimumSize(managerTabbed.getPreferredSize());
        managerTabbed.setMaximumSize(managerTabbed.getPreferredSize());
        BootsPanelComponent bootsPanel = new BootsPanelComponent();
        ClientsPanelComponent clientPanel = new ClientsPanelComponent();
        managerTabbed.addTab("Добавить обувь", bootsPanel);
        managerTabbed.addTab("Регистрация клиента", clientPanel);
        managerTabbed.addTab("Выполнить покупку", buyPanel);
        managerTabbed.addTab("Изменить обувь", changebPanel);
        managerTabbed.addTab("изменить клиента", changecPanel);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        captionComponent = new CaptionComponent("Создание покупки", this.getWidth(), 50);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        buyPanel.add(captionComponent);
        InfoComponent infoComponent2 = new InfoComponent("", this.getWidth(), 25);
        buyPanel.add(infoComponent2);
        ListClientsComponent listClientsComponent = new ListClientsComponent("Клиенты:", 400, 145, 200);
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
                jPanel.revalidate();
                jPanel.repaint();
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
        
        buttonComponent3.getButton().addActionListener((ActionEvent evt) -> {
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
                    changebPanel.revalidate();
                    changebPanel.repaint();
                    
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
        jRadioButton9.setText("Логин;");
        JRadioButton jRadioButton10 = new JRadioButton();
        jRadioButton10.setText("Пароль;");
        JRadioButton jRadioButton11 = new JRadioButton();
        jRadioButton11.setText("Деньги;");
        JRadioButton jRadioButton12 = new JRadioButton();
        jRadioButton12.setText("Уровень;");
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(jRadioButton6);
        buttonGroup1.add(jRadioButton7);
        buttonGroup1.add(jRadioButton8);
        buttonGroup1.add(jRadioButton9);
        buttonGroup1.add(jRadioButton10);
        buttonGroup1.add(jRadioButton11);
        buttonGroup1.add(jRadioButton12);
        jPanel3.add(jRadioButton6);
        jPanel3.add(jRadioButton7);
        jPanel3.add(jRadioButton8);
        jPanel3.add(jRadioButton9);
        jPanel3.add(jRadioButton10);
        jPanel3.add(jRadioButton11);
        jPanel3.add(jRadioButton12);
        changecPanel.add(jPanel3);
        EditComponent editComponent10 = new EditComponent("Введите на что менять:", 250, 50, 120);
        changecPanel.add(editComponent10);
        ButtonComponent buttonComponent4 = new ButtonComponent("Изменить обувь", 25, 175, 250);
        changecPanel.add(buttonComponent4);
        
        buttonComponent4.getButton().addActionListener((ActionEvent evt) -> {
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
                        clients1.get(0).setClientNumber(editComponent10.getEditor().getText());
                    }
                    else if(jRadioButton9.isSelected()){
                        clients1.get(0).setLogin(editComponent10.getEditor().getText());
                    }
                    else if(jRadioButton10.isSelected()){
                        clients1.get(0).setPassword(editComponent10.getEditor().getText());
                    }
                    else if(jRadioButton11.isSelected()){
                        clients1.get(0).setClientMoney(Double.parseDouble(editComponent10.getEditor().getText()));
                    }
                    else if(jRadioButton12.isSelected()){
                        clients1.get(0).setLevel(editComponent10.getEditor().getText());
                    }
                    else{
                        infoComponent5.getInfo().setForeground(Color.red);
                        infoComponent5.getInfo().setText("Выберите что изменить");
                    }
                    infoComponent5.getInfo().setForeground(Color.BLUE);
                    infoComponent5.getInfo().setText("Успех!");
                    clientFacade.edit(clients1.get(0));
                    changecPanel.revalidate();
                    changecPanel.repaint();
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
        });
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
        pack();                    
    }
            
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
        java.awt.EventQueue.invokeLater(() -> {
            new guiApp().setVisible(true);
        });
    }
    // Variables declaration - do not modify      
    private JPanel jPanel1;
    private CaptionComponent captionComponent;
    // End of variables declaration                   
}
