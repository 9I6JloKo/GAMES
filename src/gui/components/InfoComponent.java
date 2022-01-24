/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author anana
 */
public class InfoComponent extends JPanel{
    JLabel jLabel;
    public InfoComponent(String text, int widthWindow, int heightPanel) {
        initComponents(text, widthWindow, heightPanel);
    }
    private void initComponents(String text, int widthWindow, int heightPanel){
       this.setPreferredSize(new Dimension(widthWindow,heightPanel));
       this.setMinimumSize(this.getPreferredSize());
       this.setMaximumSize(this.getPreferredSize());
       jLabel = new JLabel(text);
       jLabel.setPreferredSize(new Dimension(widthWindow,heightPanel));
       jLabel.setMinimumSize(jLabel.getPreferredSize());
       jLabel.setMaximumSize(jLabel.getPreferredSize());
       jLabel.setHorizontalAlignment(JLabel.CENTER);
       jLabel.setFont(new Font("Times New Roman",1,18));
       this.add(jLabel);
    }
    public JLabel getInfo() {
        return jLabel;
    }
}
