package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/17.
 */
public class DeleteDriver extends JFrame {
    public static final int DEFAULT_WIDTH = 220;
    public static final int DEFAULT_HEIGHT = 100;

    public DeleteDriver()
    {
        Container container = getContentPane();


        //get the user names
        Client client = new Client();
        String[] tempUserName = client.sendSearch("SelectAllDriver:").split("\\*");


        //initialize the JPanel
        JPanel deleteDriverJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(deleteDriverJPanel);
        deleteDriverJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        //initialize the JLabel and JComboBox
        JLabel userNameJLabel = new JLabel("User Name:");
        userNameJLabel.setFont(new Font("Times", 1, 15));
        deleteDriverJPanel.add(userNameJLabel);

        JComboBox userNameJComboBox = new JComboBox();
        for(int i = 0; i < tempUserName.length; i++)
        {
            userNameJComboBox.addItem(tempUserName[i]);
        }
        deleteDriverJPanel.add(userNameJComboBox);


        //initialize the JButton
        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        deleteDriverJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        deleteDriverJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(userNameJLabel).addComponent(userNameJComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userNameJLabel)
                                        .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userNameJComboBox)
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
                        String userName = userNameJComboBox.getSelectedItem().toString();
                        boolean judge = client.send("DeleteDriver:" + userName);
                        if(judge == true)
                        {
                            dispose();
                            JOptionPane.showMessageDialog(null, "The driver has been deleted!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                        }
                    }
                }
        );

        container.add(deleteDriverJPanel);
        setTitle("Delete Driver");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new DeleteDriver();
    }
}
