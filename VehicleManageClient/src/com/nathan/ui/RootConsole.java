package com.nathan.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.nathan.uiRoot.RootManage;

/**
 * Created by Nathan on 16/4/14.
 */
public class RootConsole extends JFrame {
    private String name;
    private String username;
    private String driver;

    public static final int DEFAULT_WIDTH = 240;
    public static final int DEFAULT_HEIGHT = 330;

    public RootConsole(String name, String username, String driver)
    {
        this.name = name;
        this.username = username;
        this.driver = driver;

        Container container = getContentPane();
        container.setBackground(Color.WHITE);
        container.setLayout(null);

        JButton profileButton = new JButton("Profile");//Profile Button
        profileButton.setFont(new Font("Dilog", 1, 15));
        profileButton.setBounds(45, 20, 150, 50);
        container.add(profileButton);

        JButton autoButton = new JButton("Auto");//Auto Button
        autoButton.setFont(new Font("Dilog", 1, 15));
        autoButton.setBounds(45, 70, 150, 50);
        container.add(autoButton);

        JButton oilCardButton = new JButton("Oil Card");//Oil Card Button
        oilCardButton.setFont(new Font("Dilog", 1, 15));
        oilCardButton.setBounds(45, 140, 150, 50);
        container.add(oilCardButton);

        JButton repairButton = new JButton("Repair");// Repair Button
        repairButton.setFont(new Font("Dilog", 1, 15));
        repairButton.setBounds(45, 190, 150, 50);
        container.add(repairButton);

        JButton rootButton = new JButton("Root");//Root Button
        rootButton.setFont(new Font("Dilog", 1, 15));
        rootButton.setBounds(45, 250, 150, 50);
        container.add(rootButton);

        profileButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Profile profile = new Profile(name, username, driver);
                    }
                }
        );

        autoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Auto auto = new Auto(name, username);
                    }
                }
        );

        oilCardButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        OilCard oilCard = new OilCard();
                    }
                }
        );

        repairButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Repair repair = new Repair(name);
                    }
                }
        );

        rootButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { RootManage rootManage = new RootManage();}
                }
        );
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent arg0) {
                // TODO Auto-generated method stub
                dispose();
            }

        });

        setTitle("User Console");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        new RootConsole("Nathan", "673650096", "1");
    }
}
