package com.nathan.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Nathan on 16/4/11.
 */
public class Profile extends JFrame {
    private String name;
    private String username;
    private String driver;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 244;

    public Profile (String name, String username, String driver)
    {
        this.name = name;
        this.username = username;
        this.driver = driver;

        Container container = getContentPane();
        container.setBackground(Color.white);
        container.setLayout(null);

        JLabel usernameJLabel = new JLabel();//username text
        usernameJLabel.setText("Username: " + username);
        usernameJLabel.setFont(new Font("Times", 1, 15));
        usernameJLabel.setSize(200, 20);
        usernameJLabel.setLocation(40, 50);
        container.add(usernameJLabel);

        JLabel nameJLabel = new JLabel();//name text
        nameJLabel.setText("Name: " + name);
        nameJLabel.setFont(new Font("Times", 1, 15));
        nameJLabel.setSize(200, 20);
        nameJLabel.setLocation(40, 80);
        container.add(nameJLabel);

        JButton changePasswordButton = new JButton("Change Passowrd");// change password button
        changePasswordButton.setFont(new Font("Dilog", 1, 11));
        changePasswordButton.setBounds(250, 48, 110, 40);
        container.add(changePasswordButton);

        JButton driverButton = new JButton("My Driver Profile");// driver profile button
        driverButton.setFont(new Font("Dilog", 1, 11));
        driverButton.setBounds(250, 136, 110, 40);
        if(driver.equals("1"))
        {
            container.add(driverButton);
        }

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        backButton.setBounds(40, 136, 110, 40);
        container.add(backButton);

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        changePasswordButton.addActionListener(//change password button action listener
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        ChangePassword changePassword = new ChangePassword(username);
                    }
                }
        );

        driverButton.addActionListener(//select driver profile
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    dispose();
                    DriverProfile driverProfile = new DriverProfile(username);
                    }
                }
        );



        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent arg0) {
                // TODO Auto-generated method stub
                dispose();
            }

        });

        setTitle(name + "'s Profile");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Profile("Nathan", "673650096", "1");
    }
}
