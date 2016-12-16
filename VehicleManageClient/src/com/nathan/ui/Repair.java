package com.nathan.ui;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/14.
 */
public class Repair extends JFrame {
    private  String name;

    public static final int DEFAULT_WIDTH = 350;
    public static final int DEFAULT_HEIGHT = 150;

    public Repair(String name)
    {
        this.name = name;

        Container container = getContentPane();

        //initialize JPanel and set layout
        JPanel repairJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(repairJPanel);
        repairJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        //get the auto numbers from server
        String getAutoNum = "SelectAllAuto:";
        Client client = new Client();
        String[] tempAutoNum = client.sendSearch(getAutoNum).split("\\*");

        //initialize the text
        JLabel autoNumJLabel = new JLabel("Auto Number");
        autoNumJLabel.setFont(new Font("Times", 1, 15));
        repairJPanel.add(autoNumJLabel);

        JLabel repairPortionJLabel = new JLabel("Repair Portion");
        repairPortionJLabel.setFont(new Font("Times", 1, 15));
        repairJPanel.add(repairPortionJLabel);

        JLabel amountJLabel = new JLabel("Amount");
        amountJLabel.setFont(new Font("Times", 1, 15));
        repairJPanel.add(amountJLabel);

        //initialize the box
        JComboBox autoNumJComboBox = new JComboBox();
        for(int i = 0; i < tempAutoNum.length; i++)
        {
            autoNumJComboBox.addItem(tempAutoNum[i]);
        }
        repairJPanel.add(autoNumJComboBox);

        JTextField repairPortionJTextField = new JTextField(20);
        repairJPanel.add(repairPortionJTextField);

        JTextField amountJTextField = new JTextField(10);
        repairJPanel.add(amountJTextField);

        //back button and submit button
        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        repairJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");// submit button
        submitButton.setFont(new Font("Dilog", 1, 15));
        repairJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(autoNumJLabel).addComponent(repairPortionJLabel).addComponent(amountJLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(autoNumJComboBox).addComponent(repairPortionJTextField).addComponent(amountJTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(autoNumJLabel)
                        .addComponent(autoNumJComboBox)
                        .addComponent(backButton)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(repairPortionJLabel)
                        .addComponent(repairPortionJTextField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(amountJLabel)
                        .addComponent(amountJTextField)
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
                        String repairPortion = repairPortionJTextField.getText();
                        String amount = amountJTextField.getText();
                        if(repairPortion.equals("") || amount.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                        }
                        else {
                            String sendContent = "AddRepair:" + autoNum + "*" + name + "*" + repairPortion + "*" + amount;
                            boolean judge = client.send(sendContent);
                            if(judge == true)
                            {
                                dispose();
                                JOptionPane.showMessageDialog(null, "Your record has been added!");
                            }
                            else
                                JOptionPane.showMessageDialog(null, "You haven't add the record! Please Contact The Root.");
                        }

                    }
                }
        );
        container.add(repairJPanel);
        setTitle("Repair");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Repair("Sherrie");
    }
}
