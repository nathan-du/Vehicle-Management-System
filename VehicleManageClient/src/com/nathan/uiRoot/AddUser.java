package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/15.
 */
public class AddUser extends JFrame {
    public static final int DEFAULT_WIDTH = 320;
    public static final int DEFAULT_HEIGHT = 180;

    public AddUser()
    {
        Container container = getContentPane();

        //initialize the JPanel and layout
        JPanel addUserJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(addUserJPanel);
        addUserJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        //initialize the Text
        JLabel userNameJLabel = new JLabel("User Name:");
        userNameJLabel.setFont(new Font("Times", 1, 15));
        addUserJPanel.add(userNameJLabel);

        JLabel passwordJLabel = new JLabel("Password:");
        passwordJLabel.setFont(new Font("Times", 1, 15));
        addUserJPanel.add(passwordJLabel);

        JLabel nameJLabel = new JLabel("Name:");
        nameJLabel.setFont(new Font("Times", 1, 15));
        addUserJPanel.add(nameJLabel);

        //initialize the TextFiled
        JTextField userNameJTextField = new JTextField(20);
        addUserJPanel.add(userNameJTextField);

        JPasswordField passwordJPasswordField = new JPasswordField(20);
        addUserJPanel.add(passwordJPasswordField);

        JTextField nameJTextField = new JTextField(20);
        addUserJPanel.add(nameJTextField);

        //initialize the Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Dilog", 1, 15));
        addUserJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        addUserJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(userNameJLabel).addComponent(userNameJTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(passwordJLabel).addComponent(passwordJPasswordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nameJLabel).addComponent(nameJTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userNameJLabel)
                                .addComponent(passwordJLabel)
                                .addComponent(nameJLabel)
                                .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userNameJTextField)
                                .addComponent(passwordJPasswordField)
                                .addComponent(nameJTextField)
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
                        String username = userNameJTextField.getText();
                        char[] tempPassword = passwordJPasswordField.getPassword();
                        String password = new String(tempPassword);
                        String name = nameJTextField.getText();
                        if(username.equals("") || password.equals("") || name.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                        }
                        else
                        {
                            String sendContent = "SignUp:" + username + "*" + name + "*" + password;
                            Client client = new Client();
                            String judge = client.sendSearch(sendContent);
                            if(judge.equals("same"))
                            {
                                JOptionPane.showMessageDialog(null, "Your username has been used!");
                            }
                            else if(judge.equals("true"))
                            {
                                dispose();
                                JOptionPane.showMessageDialog(null, "The account has been added!");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "You haven't add account! Please Contact The Root.");
                            }
                        }
                    }
                }
        );

        container.add(addUserJPanel);
        setTitle("Add User");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);

    }

    public static void main(String[] args)
    {
        new AddUser();
    }
}
