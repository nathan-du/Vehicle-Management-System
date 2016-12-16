package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/17.
 */
public class AddOilCard extends JFrame {
    public static final int DEFAULT_WIDTH = 320;
    public static final int DEFAULT_HEIGHT = 180;

    public AddOilCard()
    {
        Container container = getContentPane();


        //initialize the JPanel and Layout
        JPanel addOilCardJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(addOilCardJPanel);
        addOilCardJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        //initialize the JLabel and JTextField
        JLabel idOilCardJLabel = new JLabel("OC Number:");
        idOilCardJLabel.setFont(new Font("Times", 1, 15));
        addOilCardJPanel.add(idOilCardJLabel);

        JLabel passwordJLabel = new JLabel("Password(Null):");
        passwordJLabel.setFont(new Font("Times", 1, 15));
        addOilCardJPanel.add(passwordJLabel);

        JLabel balanceJLabel = new JLabel("Balance:");
        balanceJLabel.setFont(new Font("Times", 1, 15));
        addOilCardJPanel.add(balanceJLabel);

        JTextField idOilCardJTextField = new JTextField(20);
        addOilCardJPanel.add(idOilCardJTextField);

        JPasswordField passwordJPasswordField = new JPasswordField(6);
        addOilCardJPanel.add(passwordJPasswordField);

        JTextField balanceJTextField = new JTextField(10);
        addOilCardJPanel.add(balanceJTextField);


        //initialize the Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Dilog", 1, 15));
        addOilCardJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        addOilCardJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(idOilCardJLabel).addComponent(idOilCardJTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(passwordJLabel).addComponent(passwordJPasswordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(balanceJLabel).addComponent(balanceJTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(idOilCardJLabel)
                                        .addComponent(passwordJLabel)
                                        .addComponent(balanceJLabel)
                                        .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(idOilCardJTextField)
                                        .addComponent(passwordJPasswordField)
                                        .addComponent(balanceJTextField)
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
                        String idOilCard = idOilCardJTextField.getText();
                        String tempBalance = balanceJTextField.getText();
                        if(idOilCard.equals("") || tempBalance.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                        }
                        else {
                            try {
                                double balance = Double.parseDouble(tempBalance);
                                char[] tempPassword = passwordJPasswordField.getPassword();
                                String password = new String(tempPassword);
                                Client client = new Client();
                                String judge = client.sendSearch("AddOilCard:" + idOilCard + "*" + password + "*" + balance);
                                if(judge.equals("same"))
                                {
                                    JOptionPane.showMessageDialog(null, "The oil card existed!");
                                }
                                else if(judge.equals("true"))
                                {
                                    dispose();
                                    JOptionPane.showMessageDialog(null, "The oil card has been added!");
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                                }
                            } catch (NumberFormatException m) {
                                JOptionPane.showMessageDialog(null, "Don't submit error data in balance!");
                            }
                        }
                    }
                }
        );

        container.add(addOilCardJPanel);
        setTitle("Add Oil Card");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main (String[]args)
    {
        new AddOilCard();
    }
}
