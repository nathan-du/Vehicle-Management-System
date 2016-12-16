package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/15.
 */
public class DeleteAuto extends JFrame {
    public static final int DEFAULT_WIDTH = 220;
    public static final int DEFAULT_HEIGHT = 100;

    public DeleteAuto()
    {
        Container container = getContentPane();

        Client client = new Client();
        String[] tempAutoNum = client.sendSearch("SelectAllAuto:").split("\\*");

        JPanel deleteAutoJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(deleteAutoJPanel);
        deleteAutoJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel autoNumJLabel = new JLabel("Auto Num:");
        autoNumJLabel.setFont(new Font("Times", 1, 15));
        deleteAutoJPanel.add(autoNumJLabel);

        JComboBox autoNumJComboBox = new JComboBox();
        for(int i = 0; i < tempAutoNum.length; i++)
        {
            autoNumJComboBox.addItem(tempAutoNum[i]);
        }
        deleteAutoJPanel.add(autoNumJComboBox);

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        deleteAutoJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        deleteAutoJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(autoNumJLabel).addComponent(autoNumJComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(autoNumJLabel)
                        .addComponent(backButton)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(autoNumJComboBox)
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
                        String autoNum = autoNumJComboBox.getSelectedItem().toString();
                        boolean judge = client.send("DeleteAuto:" + autoNum);
                        if(judge == true)
                        {
                            dispose();
                            JOptionPane.showMessageDialog(null, "The auto has been deleted!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                        }
                    }
                }
        );

        container.add(deleteAutoJPanel);
        setTitle("Delete Auto");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new DeleteAuto();
    }
}
