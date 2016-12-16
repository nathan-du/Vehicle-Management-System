package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/15.
 */
public class ResetPassword extends JFrame {
    public static final int DEFAULT_WIDTH = 220;
    public static final int DEFAULT_HEIGHT = 100;

    public ResetPassword()
    {
        Container container = getContentPane();

        Client client = new Client();
        String[] tempUserName = client.sendSearch("SelectAllUser:").split("\\*");

        JPanel resetPasswordJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(resetPasswordJPanel);
        resetPasswordJPanel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JLabel usernameJLabel = new JLabel("User Name:");
        usernameJLabel.setFont(new Font("Times", 1, 15));
        resetPasswordJPanel.add(usernameJLabel);

        JComboBox usernameJComboBox = new JComboBox();
        for(int i = 0; i < tempUserName.length; i++)
        {
            usernameJComboBox.addItem(tempUserName[i]);
        }
        resetPasswordJPanel.add(usernameJComboBox);

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        resetPasswordJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        resetPasswordJPanel.add(submitButton);


        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(usernameJLabel).addComponent(usernameJComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(usernameJLabel)
                                .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(usernameJComboBox)
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
                        String username = usernameJComboBox.getSelectedItem().toString();
                        boolean judge = client.send("ResetPassword:" + username);
                        if(judge == true)
                        {
                            JOptionPane.showMessageDialog(null, "The password has been changed to 000000!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                        }
                    }
                }
        );

        container.add(resetPasswordJPanel);
        setTitle("Reset Password");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ResetPassword();
    }
}
