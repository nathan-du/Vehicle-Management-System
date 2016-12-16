package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/17.
 */
public class DeleteOilCard extends JFrame{
    public static final int DEFAULT_WIDTH = 230;
    public static final int DEFAULT_HEIGHT = 100;

    public DeleteOilCard()
    {
        Container container = getContentPane();

        Client client = new Client();
        String[] tempOilCardNum = client.sendSearch("SelectOilCardNum:").split("\\*");

        JPanel deleteOilCardJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(deleteOilCardJPanel);
        deleteOilCardJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel oilCardNumJLabel = new JLabel("OC Number:");
        oilCardNumJLabel.setFont(new Font("Times", 1, 15));
        deleteOilCardJPanel.add(oilCardNumJLabel);

        JComboBox oilCardNumJComboBox = new JComboBox();
        for(int i = 0; i < tempOilCardNum.length; i++)
        {
            oilCardNumJComboBox.addItem(tempOilCardNum[i]);
        }
        deleteOilCardJPanel.add(oilCardNumJComboBox);

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        deleteOilCardJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        deleteOilCardJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(oilCardNumJLabel).addComponent(oilCardNumJComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(oilCardNumJLabel)
                                        .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(oilCardNumJComboBox)
                                        .addComponent(submitButton)
                        )
        );

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String oilCardNum = oilCardNumJComboBox.getSelectedItem().toString();
                        boolean judge = client.send("DeleteOilCard:" + oilCardNum);
                        if(judge == true)
                        {
                            dispose();
                            JOptionPane.showMessageDialog(null, "The oil card has been deleted!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                        }
                    }
                }
        );

        container.add(deleteOilCardJPanel);
        setTitle("Delete Oil Card");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new DeleteOilCard();
    }
}
