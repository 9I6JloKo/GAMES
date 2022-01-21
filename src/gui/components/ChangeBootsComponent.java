/*
 * To change jPanel license header, choose License Headers in Project Properties.
 * To change jPanel template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import facade.ProductFacade;
import gui.guiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import shopboots.classes.Product;

/**
 *
 * @author anana
 */
public class ChangeBootsComponent extends JPanel {
    private ProductFacade productFacade;
    public ChangeBootsComponent() {
        initComponents();
    }

    private void initComponents() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(Box.createRigidArea(new Dimension(0,25)));
        CaptionComponent captionComponent = new CaptionComponent("Изменение обуви", guiApp.WIDTH, 25);
        InfoComponent infoComponent4 = new InfoComponent("", guiApp.WIDTH, 50);
        jPanel.add(captionComponent);
        jPanel.add(infoComponent4);
        ListBootsComponent listBootsComponent = new ListBootsComponent("Обувь:", 100, 145, 375);
        jPanel.add(listBootsComponent);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        JRadioButton seller = new JRadioButton();
        seller.setText("Продавец;");
        JRadioButton Smodel = new JRadioButton();
        Smodel.setText("Модель;");
        JRadioButton Sprice = new JRadioButton();
        Sprice.setText("Цена;");
        JRadioButton Ssize = new JRadioButton();
        Ssize.setText("Размер;");
        JRadioButton Spiece = new JRadioButton();
        Spiece.setText("Кол-во");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(seller);
        buttonGroup.add(Smodel);
        buttonGroup.add(Sprice);
        buttonGroup.add(Ssize);
        buttonGroup.add(Spiece);
        jPanel.add(seller);
        jPanel.add(Smodel);
        jPanel.add(Sprice);
        jPanel.add(Ssize);
        jPanel.add(Spiece);
        jPanel.add(jPanel);
        EditComponent editComponent = new EditComponent("Введите на что менять:", 250, 50, 120);
        jPanel.add(editComponent);
        ButtonComponent buttonComponent = new ButtonComponent("Изменить обувь", 25, 175, 250);
        jPanel.add(buttonComponent);
        
        buttonComponent.getButton().addActionListener((ActionEvent evt) -> {
            List<Product> products = listBootsComponent.getList().getSelectedValuesList();
            if(!products.isEmpty() && !editComponent.getEditor().getText().isEmpty()){
                try{
                    if(seller.isSelected()){
                        products.get(0).setBywho(editComponent.getEditor().getText());
                    }
                    else if(Smodel.isSelected()){
                        products.get(0).setModell(editComponent.getEditor().getText());
                    }
                    else if(Sprice.isSelected()){
                        products.get(0).setPrice(Double.parseDouble(editComponent.getEditor().getText()));
                    }
                    else if(Ssize.isSelected()){
                        products.get(0).setSize(Double.parseDouble(editComponent.getEditor().getText()));
                    }
                    else if(Spiece.isSelected()){
                        products.get(0).setPiece(Integer.parseInt(editComponent.getEditor().getText()));
                        products.get(0).setMaxPiece(Integer.parseInt(editComponent.getEditor().getText()));
                    }
                    else{
                        infoComponent4.getInfo().setForeground(Color.red);
                        infoComponent4.getInfo().setText("Выберите что изменить");
                    }
                    infoComponent4.getInfo().setForeground(Color.BLUE);
                    infoComponent4.getInfo().setText("Успех!");
                    productFacade.edit(products.get(0));
                    jPanel.revalidate();
                    jPanel.repaint();
                    
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
    }
}
