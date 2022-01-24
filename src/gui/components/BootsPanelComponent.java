/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import facade.ProductFacade;
import gui.guiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import shopboots.classes.Product;

/**
 *
 * @author anana
 */
public class BootsPanelComponent extends JPanel {
    private ProductFacade productFacade;
    public BootsPanelComponent() {
        initComponents();
    }

    private void initComponents() {
        productFacade = new ProductFacade(Product.class);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        CaptionComponent captionComponent = new CaptionComponent("Добавление обуви в магазин", guiApp.WIDTH, 30);
        this.add(captionComponent);
        InfoComponent infoComponent = new InfoComponent("", guiApp.WIDTH, 50);
        this.add(infoComponent);
        EditComponent firma = new EditComponent("Фирма:", 250, 30, 120);
        this.add(firma);
        EditComponent model = new EditComponent("Модель:", 250, 30, 120);
        this.add(model);
        EditComponent size = new EditComponent("Размер:", 250, 30, 40);
        this.add(size);
        EditComponent price = new EditComponent("Цена:", 250, 30, 40);
        this.add(price);
        EditComponent piece = new EditComponent("Кол-во пар:", 250, 30, 40);
        this.add(piece);
        ButtonComponent buttonComponent = new ButtonComponent("Добавить обувь", 100, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener((ActionEvent evt) -> {
            try{
                if(        !model.getEditor().getText().isEmpty()
                        && !size.getEditor().getText().isEmpty()
                        && !firma.getEditor().getText().isEmpty()
                        && !price.getEditor().getText().isEmpty()
                        && !piece.getEditor().getText().isEmpty()
                        && Integer.parseInt(piece.getEditor().getText()) >= 1){
                    try{
                        Product product = new Product();
                        product.setModell(model.getEditor().getText());
                        product.setSize(Double.parseDouble(size.getEditor().getText()));
                        product.setBywho(firma.getEditor().getText());
                        product.setPrice(Double.parseDouble(price.getEditor().getText()));
                        product.setMaxPiece(Integer.parseInt(piece.getEditor().getText()));
                        product.setPiece(Integer.parseInt(piece.getEditor().getText()));
                        infoComponent.getInfo().setForeground(Color.BLUE);
                        infoComponent.getInfo().setText("Успех!");
                        productFacade.create(product);
                        model.getEditor().setText("");
                        size.getEditor().setText("");
                        firma.getEditor().setText("");
                        price.getEditor().setText("");
                        piece.getEditor().setText("");
                        this.revalidate();
                        this.repaint();
                    }catch(Exception e){
                        Product product = new Product();
                        infoComponent.getInfo().setForeground(Color.red);
                        try{
                            product.setModell(model.getEditor().getText());
                        }
                        catch(Exception ex){
                            infoComponent.getInfo().setText("Введите модель КОРРЕКТНО");
                        }
                        try{
                            product.setSize(Double.parseDouble(size.getEditor().getText()));
                        }
                        catch(Exception ex){
                            infoComponent.getInfo().setText("Введите размер КОРРЕКТНО");
                        }
                        try{
                            product.setBywho(firma.getEditor().getText());
                        }
                        catch(Exception ex){
                            infoComponent.getInfo().setText("Введите Фирму КОРРЕКТНО");
                        }
                        try{
                            product.setPrice(Double.parseDouble(price.getEditor().getText()));
                        }
                        catch(Exception ex){
                            infoComponent.getInfo().setText("Введите цену КОРРЕКТНО");
                        }
                        try{
                            product.setMaxPiece(Integer.parseInt(piece.getEditor().getText()));
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
        });
    }
}
