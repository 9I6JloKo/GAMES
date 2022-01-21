/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import shopboots.classes.Client;
import facade.ClientFacade;
import gui.components.renderers.ListClientsCellRenderer;
import gui.guiApp;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;


public class ListClientsComponent extends JPanel{
    private JLabel title;
    private JList<Client> list;
    public ListClientsComponent(String text, int left, int heightPanel, int widthEditor) {
        initComponents(text, left, heightPanel,widthEditor);
    }

    private void initComponents(String text, int left, int heightPanel,int widthEditor) {
       this.setPreferredSize(new Dimension(guiApp.WIDTH,heightPanel));
       this.setMinimumSize(this.getPreferredSize());
       this.setMaximumSize(this.getPreferredSize());
       this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
       title = new JLabel(text);
       title.setPreferredSize(new Dimension(left,27));
       title.setMinimumSize(title.getPreferredSize());
       title.setMaximumSize(title.getPreferredSize());
       title.setHorizontalAlignment(JLabel.RIGHT);
       title.setAlignmentY(TOP_ALIGNMENT);
       title.setFont(new Font("Times New Roman",0,18));
       this.add(title);
       this.add(Box.createRigidArea(new Dimension(5,0)));
       list = new JList<>();
       list.setModel(getListModel());
       list.setCellRenderer(new ListClientsCellRenderer());
       list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       list.setLayoutOrientation(JList.HEIGHT);
       JScrollPane scrollPane = new JScrollPane(list);
       scrollPane.setPreferredSize(new Dimension(widthEditor,heightPanel));
       scrollPane.setMinimumSize(scrollPane.getPreferredSize());
       scrollPane.setMaximumSize(scrollPane.getPreferredSize());
       scrollPane.setAlignmentX(LEFT_ALIGNMENT);
       scrollPane.setAlignmentY(TOP_ALIGNMENT);
       this.add(scrollPane);
    }

    private ListModel<Client> getListModel() {
        ClientFacade clientFacade = new ClientFacade(Client.class);
        List<Client> clients = clientFacade.findAll();
        DefaultListModel<Client> defaultListModel = new DefaultListModel<>();
        for (Client client : clients) {
            defaultListModel.addElement(client);
        }
        return defaultListModel;
    }

    public JList<Client> getList() {
        return list;
    }
    
}
