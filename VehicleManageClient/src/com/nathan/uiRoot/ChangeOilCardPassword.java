package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/17.
 */
public class ChangeOilCardPassword extends JFrame {
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 160;

    public ChangeOilCardPassword()
    {
        Container container = getContentPane();

        Client client = new Client();
        String[] tempOilCardNum = client.sendSearch("SelectOilCardNum:").split("\\*");

        JPanel changeOilCardPasswordJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(changeOilCardPasswordJPanel);
        changeOilCardPasswordJPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel oilCardNumJLabel = new JLabel("OC Number:");
        oilCardNumJLabel.setFont(new Font("Times", 1, 15));
        changeOilCardPasswordJPanel.add(oilCardNumJLabel);

        JLabel newPassword1Label = new JLabel("New Password1:");
        newPassword1Label.setFont(new Font("Times", 1, 15));
        changeOilCardPasswordJPanel.add(newPassword1Label);

        JLabel newPassword2Label = new JLabel("New Password2:");
        newPassword2Label.setFont(new Font("Times", 1, 15));
        changeOilCardPasswordJPanel.add(newPassword2Label);

        JComboBox oilCardNumJComboBox = new JComboBox();
        for(int i = 0; i < tempOilCardNum.length; i++)
        {
            oilCardNumJComboBox.addItem(tempOilCardNum[i]);
        }
        changeOilCardPasswordJPanel.add(oilCardNumJComboBox);

        JPasswordField newPassword1 = new JPasswordField(6);
        changeOilCardPasswordJPanel.add(newPassword1);

        JPasswordField newPassword2 = new JPasswordField(6);
        changeOilCardPasswordJPanel.add(newPassword2);

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        changeOilCardPasswordJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        changeOilCardPasswordJPanel.add(submitButton);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(oilCardNumJLabel).addComponent(oilCardNumJComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(newPassword1Label).addComponent(newPassword1))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(newPassword2Label).addComponent(newPassword2))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))
        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(oilCardNumJLabel)
                                        .addComponent(newPassword1Label)
                                        .addComponent(newPassword2Label)
                                        .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(oilCardNumJComboBox)
                                        .addComponent(newPassword1)
                                        .addComponent(newPassword2)
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
                        char[] tempPassword1 = newPassword1.getPassword();
                        char[] tempPassword2 = newPassword2.getPassword();
                        if(tempPassword1.length > 6 || tempPassword2.length > 6)
                        {
                            JOptionPane.showMessageDialog(null, "The max unit of password is 6!");
                        }
                        else {
                            String password1 = new String(tempPassword1);
                            String password2 = new String(tempPassword2);
                            if (password1.equals("") || password2.equals("")) {
                                JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                            } else if (!password1.equals(password2)) {
                                JOptionPane.showMessageDialog(null, "Please match two new passwords!");
                            } else {
                                boolean judge = client.send("ChangePasswordOilCard:" + oilCardNum + "*" + password1);
                                if (judge == true) {
                                    dispose();
                                    JOptionPane.showMessageDialog(null, "Your password has been changed!");
                                } else if (judge == false) {
                                    JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                                }
                            }
                        }
                    }
                }
        );

        container.add(changeOilCardPasswordJPanel);
        setTitle("Change Oil Card Password");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ChangeOilCardPassword();
    }
}
