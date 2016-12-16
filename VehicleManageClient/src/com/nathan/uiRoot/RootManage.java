package com.nathan.uiRoot;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Nathan on 16/4/15.
 */
public class RootManage extends JFrame{
    public static final int DEFAULT_WIDTH = 435;
    public static final int DEFAULT_HEIGHT = 400;

    public RootManage()
    {
        Container container = getContentPane();
        container.setBackground(Color.WHITE);
        container.setLayout(null);

        //initialize button
        JButton signUpButton = new JButton("Add User");
        signUpButton.setFont(new Font("Dilog", 1, 15));
        signUpButton.setBounds(45, 20, 150, 50);
        container.add(signUpButton);

        JButton resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setFont(new Font("Dilog", 1, 15));
        resetPasswordButton.setBounds(45, 70, 150, 50);
        container.add(resetPasswordButton);

        JButton addAutoButton = new JButton("Add Auto");
        addAutoButton.setFont(new Font("Dilog", 1, 15));
        addAutoButton.setBounds(45, 140, 150, 50);
        container.add(addAutoButton);

        JButton deleteAutoButton = new JButton("Delete Auto");
        deleteAutoButton.setFont(new Font("Dilog", 1, 15));
        deleteAutoButton.setBounds(45, 190, 150, 50);
        container.add(deleteAutoButton);

        JButton addDriverButton = new JButton("Add Driver");
        addDriverButton.setFont(new Font("Dilog", 1, 15));
        addDriverButton.setBounds(45, 260, 150, 50);
        container.add(addDriverButton);

        JButton deleteDriverButton = new JButton("Delete Driver");
        deleteDriverButton.setFont(new Font("Dilog", 1, 15));
        deleteDriverButton.setBounds(45, 310, 150, 50);
        container.add(deleteDriverButton);

        JButton addOilCardButton = new JButton("Add Oil Card");
        addOilCardButton.setFont(new Font("Dilog", 1, 15));
        addOilCardButton.setBounds(240, 20, 150, 50);
        container.add(addOilCardButton);

        JButton deleteOilCardButton = new JButton("Delete OC");
        deleteOilCardButton.setFont(new Font("Dilog", 1, 15));
        deleteOilCardButton.setBounds(240, 70, 150, 50);
        container.add(deleteOilCardButton);

        JButton changeOilCardPasswordButton = new JButton("Alter OC PSD");
        changeOilCardPasswordButton.setFont(new Font("Dilog", 1, 15));
        changeOilCardPasswordButton.setBounds(240, 140, 150, 50);
        container.add(changeOilCardPasswordButton);

        JButton topUpButton = new JButton("Top Up OC");
        topUpButton.setFont(new Font("Dilog", 1, 15));
        topUpButton.setBounds(240, 190, 150, 50);
        container.add(topUpButton);

        JButton deleteLendAutoButton = new JButton("Del Lending Auto");
        deleteLendAutoButton.setFont(new Font("Dilog", 1, 15));
        deleteLendAutoButton.setBounds(240, 260, 150, 50);
        container.add(deleteLendAutoButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Dilog", 1, 15));
        backButton.setBounds(240, 310, 150, 50);
        container.add(backButton);

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent arg0) {
                // TODO Auto-generated method stub
                dispose();
            }

        });

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        signUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AddUser addUser = new AddUser();
                    }
                }
        );

        resetPasswordButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ResetPassword resetPassword = new ResetPassword();
                    }
                }
        );

        addAutoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AddAuto addAuto = new AddAuto();
                    }
                }
        );

        deleteAutoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DeleteAuto deleteAuto = new DeleteAuto();
                    }
                }
        );

        addDriverButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AddDriver addDriver = new AddDriver();
                    }
                }
        );

        deleteDriverButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DeleteDriver deleteDriver = new DeleteDriver();
                    }
                }
        );

        addOilCardButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AddOilCard addOilCard = new AddOilCard();
                    }
                }
        );

        deleteOilCardButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DeleteOilCard deleteOilCard = new DeleteOilCard();
                    }
                }
        );

        changeOilCardPasswordButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChangeOilCardPassword changeOilCardPassword = new ChangeOilCardPassword();
                    }
                }
        );

        topUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopUp topUp = new TopUp();
                    }
                }
        );

        deleteLendAutoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DeleteLendAuto deleteLendAuto = new DeleteLendAuto();
                    }
                }
        );

        setTitle("Root Management");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[]args)
    {
        new RootManage();
    }
}
