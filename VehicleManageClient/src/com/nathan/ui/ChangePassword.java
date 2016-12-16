package com.nathan.ui;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Nathan on 16/4/11.
 */
public class ChangePassword extends JFrame{
    private String username;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 240;

    public ChangePassword(String username)
    {
        this.username = username;

        Container container = getContentPane();
        container.setBackground(Color.white);
        container.setLayout(null);

        JLabel text = new JLabel();
        text.setText("Now, you can change your password");//text of xxx
        text.setFont(new Font("Times", 1, 15));
        text.setSize(400, 20);
        text.setLocation(20, 10);
        container.add(text);

        JLabel oldPasswordLabel = new JLabel();
        oldPasswordLabel.setText("Old Password:");//text of xxx
        oldPasswordLabel.setFont(new Font("Times", 1, 15));
        oldPasswordLabel.setSize(100, 20);
        oldPasswordLabel.setLocation(20, 40);
        container.add(oldPasswordLabel);

        JLabel newPassword1Label = new JLabel();
        newPassword1Label.setText("New Password1:");//text of xxx
        newPassword1Label.setFont(new Font("Times", 1, 15));
        newPassword1Label.setSize(150, 20);
        newPassword1Label.setLocation(20, 70);
        container.add(newPassword1Label);

        JLabel newPassword2Label = new JLabel();
        newPassword2Label.setText("New Password2:");//text of xx
        newPassword2Label.setFont(new Font("Times", 1, 15));
        newPassword2Label.setSize(150, 20);
        newPassword2Label.setLocation(20,100);
        container.add(newPassword2Label);

        JPasswordField oldPassword = new JPasswordField(30);//old password field
        oldPassword.setBounds(150, 39, 200, 20);
        container.add(oldPassword);

        JPasswordField newPassword1 = new JPasswordField(30);//new password field
        newPassword1.setBounds(150, 69, 200, 20);
        container.add(newPassword1);

        JPasswordField newPassword2 = new JPasswordField(30);//confirm password field
        newPassword2.setBounds(150, 99, 200, 20);
        container.add(newPassword2);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        submitButton.setBounds(250, 130, 100, 50);
        container.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Dilog", 1, 15));
        cancelButton.setBounds(20, 130, 100, 50);
        container.add(cancelButton);

        cancelButton.addActionListener(
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
                        char[] temp1 = oldPassword.getPassword();
                        char[] temp2 = newPassword1.getPassword();
                        char[] temp3 = newPassword2.getPassword();
                        String oldPassword = new String(temp1);//get old password
                        String newPassword1 = new String(temp2);//get new password1
                        String newPassword2 = new String(temp3);//get new password2

                        if(newPassword1.equals("") || oldPassword.equals("") || newPassword2.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                        }

                        else if(!newPassword1.equals(newPassword2))
                        {
                            JOptionPane.showMessageDialog(null, "Please match two new passwords!");
                        }

                        else
                        {
                            Client client = new Client();
                            String sendContent = "ChangePassword:" + username + "*" + oldPassword + "*" + newPassword1;
                            boolean judge = client.send(sendContent);
                            if(judge == true)
                            {
                                dispose();
                                JOptionPane.showMessageDialog(null, "Your password has been changed!");
                            }
                            else if(judge == false)
                            {
                                JOptionPane.showMessageDialog(null, "Your old password was wrong!");
                            }
                        }

                    }
                }
        );

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                // TODO Auto-generated method stub
                dispose();
            }

        });

        setTitle("Change Password");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ChangePassword("673650096");
    }
}
