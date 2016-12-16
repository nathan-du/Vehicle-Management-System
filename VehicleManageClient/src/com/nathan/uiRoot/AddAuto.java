package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/15.
 */
public class AddAuto extends JFrame{
    public static final int DEFAULT_WIDTH = 320;
    public static final int DEFAULT_HEIGHT = 200;

    public AddAuto()
    {
        Container container = getContentPane();


        //initialize the JPanel and layout
        JPanel addAutoJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(addAutoJPanel);
        addAutoJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        //initialize the Text
        JLabel autoNumJLabel = new JLabel("Auto Num:");
        autoNumJLabel.setFont(new Font("Times", 1, 15));
        addAutoJPanel.add(autoNumJLabel);

        JLabel autoNameJLabel = new JLabel("Auto Name:");
        autoNameJLabel.setFont(new Font("Times", 1, 15));
        addAutoJPanel.add(autoNameJLabel);

        JLabel priceJLabel = new JLabel("Price:");
        priceJLabel.setFont(new Font("Times", 1, 15));
        addAutoJPanel.add(priceJLabel);

        JLabel typeOfOilJLabel = new JLabel("Type Of Oil:");
        typeOfOilJLabel.setFont(new Font("Times", 1, 15));
        addAutoJPanel.add(typeOfOilJLabel);


        //initialize the JTextField
        JTextField autoNumJTextField = new JTextField(10);
        addAutoJPanel.add(autoNumJTextField);

        JTextField autoNameJTextField = new JTextField(20);
        addAutoJPanel.add(autoNameJTextField);

        JTextField priceJTextField = new JTextField(10);
        addAutoJPanel.add(priceJTextField);

        JComboBox oilTypeJComboBox = new JComboBox();
        oilTypeJComboBox.addItem("Regular");
        oilTypeJComboBox.addItem("Sliver");
        oilTypeJComboBox.addItem("Gold");
        addAutoJPanel.add(oilTypeJComboBox);


        //initialize the Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Dilog", 1, 15));
        addAutoJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        addAutoJPanel.add(submitButton);


        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(autoNumJLabel).addComponent(autoNumJTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(autoNameJLabel).addComponent(autoNameJTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(priceJLabel).addComponent(priceJTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(typeOfOilJLabel).addComponent(oilTypeJComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(autoNumJLabel)
                        .addComponent(autoNameJLabel)
                        .addComponent(priceJLabel)
                        .addComponent(typeOfOilJLabel)
                        .addComponent(backButton)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(autoNumJTextField)
                        .addComponent(autoNameJTextField)
                        .addComponent(priceJTextField)
                        .addComponent(oilTypeJComboBox)
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
                        String autoNum = autoNumJTextField.getText();
                        String autoName = autoNameJTextField.getText();
                        String typeOfOil = oilTypeJComboBox.getSelectedItem().toString();
                        try {
                            double price = Double.parseDouble(priceJTextField.getText());
                            if(autoNum.equals("") || autoName.equals("") || typeOfOil.equals(""))
                            {
                                JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                            }
                            else
                            {
                                String sendContent = "AddAuto:" + autoNum + "*" + autoName + "*" + price + "*" + typeOfOil;
                                Client client = new Client();
                                boolean judge = client.send(sendContent);
                                if(judge == true) {
                                    dispose();
                                    JOptionPane.showMessageDialog(null, "You have added the auto!");
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "You haven't add the auto! Please Contact The Root.");
                            }

                        }
                        catch (NumberFormatException m)
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit error data in price!");
                        }

                    }
                }
        );

        container.add(addAutoJPanel);
        setTitle("Add Auto");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args) { new AddAuto(); }
}
