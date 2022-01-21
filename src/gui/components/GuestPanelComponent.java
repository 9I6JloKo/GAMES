/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import gui.guiApp;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author anana
 */
public class GuestPanelComponent extends JPanel {
    public GuestPanelComponent() {
        initComponents();
    }

    private void initComponents() {
        this.add(Box.createRigidArea(new Dimension(0,0)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        CaptionComponent captionComponent = new CaptionComponent("Просмотр обуви", guiApp.WIDTH, 100);
        this.add(captionComponent);
        ListBootsComponent boots = new ListBootsComponent("", 80, 200, 430);
        this.add(boots);
    }
}
