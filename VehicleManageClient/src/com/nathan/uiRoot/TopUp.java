package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/17.
 */
public class TopUp extends JFrame {
    public static final int DEFAULT_WIDTH = 230;
    public static final int DEFAULT_HEIGHT = 130;

    public TopUp()
    {
        Container container = getContentPane();

        Client client = new Client();
        String[] tempOilCardNum = client.sendSearch("SelectOilCardNum:").split("\\*");

        JPanel topUpJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(topUpJPanel);
        topUpJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel oilCardNumJLabel = new JLabel("OC Number:");
        oilCardNumJLabel.setFont(new Font("Times", 1, 15));
        topUpJPanel.add(oilCardNumJLabel);

        JLabel amountJLabel = new JLabel("Amount:");
        amountJLabel.setFont(new Font("Times", 1, 15));
        topUpJPanel.add(amountJLabel);

        JComboBox oilCardNumJComboBox = new JComboBox();
        for(int i = 0; i < tempOilCardNum.length; i++)
        {
            oilCardNumJComboBox.addItem(tempOilCardNum[i]);
        }
        topUpJPanel.add(oilCardNumJComboBox);

        JTextField amountJTextField = new JTextField();
        topUpJPanel.add(amountJTextField);

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        topUpJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        topUpJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(oilCardNumJLabel).addComponent(oilCardNumJComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(amountJLabel).addComponent(amountJTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(oilCardNumJLabel)
                                        .addComponent(amountJLabel)
                                        .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(oilCardNumJComboBox)
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
                        String oilCardNum = oilCardNumJComboBox.getSelectedItem().toString();
                        String tempAmount = amountJTextField.getText();
                        if(oilCardNum.equals("") || tempAmount.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                        }
                        else
                        {
                            try
                            {
                                double amount = Double.parseDouble(tempAmount);
                                boolean judge = client.send("ChangeBalance:" + oilCardNum + "*" + amount);
                                if(judge == true)
                                {
                                    dispose();
                                    JOptionPane.showMessageDialog(null, amount + " has been topped up");
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                                }
                            }catch (NumberFormatException m) {
                                JOptionPane.showMessageDialog(null, "Don't submit error data in balance!");
                            }
                        }
                    }
                }
        );

        container.add(topUpJPanel);
        setTitle("Add Oil Card");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TopUp();
    }
}
