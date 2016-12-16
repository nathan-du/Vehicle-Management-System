package com.nathan.ui;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Nathan on 16/4/13.
 */
public class OilCard extends JFrame{
    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 150;

    public OilCard()
    {
        Container container = getContentPane();

        //initialize JPanel and set layout
        JPanel oilCardJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(oilCardJPanel);
        oilCardJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        //get the auto numbers and oil card numbers from Server
        String getAutoNum = "SelectAllAuto:";
        String getOilCardNum = "SelectOilCardNum:";
        Client client = new Client();
        String[] tempAutoNum = client.sendSearch(getAutoNum).split("\\*");
        String[] tempOilCardNum = client.sendSearch(getOilCardNum).split("\\*");

        //initialize the text
        JLabel refuelNumJLabel = new JLabel("Refuel Number");
        refuelNumJLabel.setFont(new Font("Times", 1, 15));
        oilCardJPanel.add(refuelNumJLabel);

        JLabel autoNumJLabel = new JLabel("Auto Number");
        autoNumJLabel.setFont(new Font("Times", 1, 15));
        oilCardJPanel.add(autoNumJLabel);

        JLabel oilCardJLabel = new JLabel("Oil Card Number");
        oilCardJLabel.setFont(new Font("Times", 1, 15));
        oilCardJPanel.add(oilCardJLabel);

        JLabel oilTypeJLabel = new JLabel("Type of Oil");
        oilTypeJLabel.setFont(new Font("Times", 1, 15));
        oilCardJPanel.add(oilTypeJLabel);

        JLabel unitPriceJLabel = new JLabel("Unit Price");
        unitPriceJLabel.setFont(new Font("Times", 1, 15));
        oilCardJPanel.add(unitPriceJLabel);

        JLabel volumeJLabel = new JLabel("Volume");
        volumeJLabel.setFont(new Font("Times", 1, 15));
        oilCardJPanel.add(volumeJLabel);

        JLabel amountJLabel = new JLabel("Amount");
        amountJLabel.setFont(new Font("Times", 1, 15));
        oilCardJPanel.add(amountJLabel);

        //initialize the box
        JTextField refuelNumJTextField = new JTextField(10);
        oilCardJPanel.add(refuelNumJTextField);

        JComboBox autoNumJComboBox = new JComboBox();
        for(int i = 0; i < tempAutoNum.length; i++)
        {
            autoNumJComboBox.addItem(tempAutoNum[i]);
        }
        oilCardJPanel.add(autoNumJComboBox);

        JComboBox oilCardJComboBox = new JComboBox();
        for(int i = 0; i < tempOilCardNum.length; i++)
        {
            oilCardJComboBox.addItem(tempOilCardNum[i]);
        }
        oilCardJPanel.add(oilCardJComboBox);

        JComboBox oilTypeJComboBox = new JComboBox();
        oilTypeJComboBox.addItem("Regular");
        oilTypeJComboBox.addItem("Sliver");
        oilTypeJComboBox.addItem("Gold");
        oilCardJPanel.add(oilTypeJComboBox);

        JTextField unitPriceJTextField = new JTextField(5);
        oilCardJPanel.add(unitPriceJTextField);

        JTextField volumeJTextField = new JTextField(5);
        oilCardJPanel.add(volumeJTextField);

        JTextField amountJTextField = new JTextField(10);
        oilCardJPanel.add(amountJTextField);

        // Back Button and Submit Button
        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        oilCardJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");// submit button
        submitButton.setFont(new Font("Dilog", 1, 15));
        oilCardJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(refuelNumJLabel).addComponent(autoNumJLabel).addComponent(oilCardJLabel).addComponent(oilTypeJLabel).addComponent(unitPriceJLabel).addComponent(volumeJLabel).addComponent(amountJLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(refuelNumJTextField).addComponent(autoNumJComboBox).addComponent(oilCardJComboBox).addComponent(oilTypeJComboBox).addComponent(unitPriceJTextField).addComponent(volumeJTextField).addComponent(amountJTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(refuelNumJLabel)
                                .addComponent(refuelNumJTextField)
                                .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(autoNumJLabel)
                                .addComponent(autoNumJComboBox)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(oilCardJLabel)
                                .addComponent(oilCardJComboBox)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(oilTypeJLabel)
                                .addComponent(oilTypeJComboBox)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(unitPriceJLabel)
                                .addComponent(unitPriceJTextField)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(volumeJLabel)
                                .addComponent(volumeJTextField)
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
                        String refuelNum = refuelNumJTextField.getText();
                        String autoNum = autoNumJComboBox.getSelectedItem().toString();
                        String oilCardNum = oilCardJComboBox.getSelectedItem().toString();
                        String oilType = oilTypeJComboBox.getSelectedItem().toString();
                        try {
                             double unitPrice = Double.parseDouble(unitPriceJTextField.getText());
                             double volume = Double.parseDouble(volumeJTextField.getText());
                             double amount = Double.parseDouble(amountJTextField.getText());
                             if(unitPriceJTextField.getText().equals("") || volumeJTextField.getText().equals("") || amountJTextField.getText().equals(""))
                             {
                                JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                             }
                             else if(amount != unitPrice * volume)
                             {
                                JOptionPane.showMessageDialog(null, "Please submit the right data!");

                             }
                             else
                             {
                                String sendContent = "AddRefuel:" + autoNum + "*" + oilCardNum + "*" + oilType + "*" + volume + "*" + unitPrice + "*" + amount+ "*" + refuelNum ;
                                boolean judge = client.send(sendContent);
                                if(judge == true) {
                                    dispose();
                                    JOptionPane.showMessageDialog(null, "Your record has been added!");
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "You haven't add the record! Please Contact The Root.");
                             }
                        }
                        catch (NumberFormatException m)
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit error data in Unit Price, volume, or amount!");
                        }


                    }
                }
        );

        oilCardJPanel.setFocusable(true);
        oilCardJPanel.requestFocusInWindow();
        container.add(oilCardJPanel);
        setTitle("Oil Card");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        OilCard oilCard = new OilCard();
    }
}
