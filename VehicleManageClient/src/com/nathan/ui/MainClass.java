package com.nathan.ui;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Nathan on 16/4/10.
 */
public class MainClass extends JFrame {
    public static final int DEFAULT_WIDTH = 420;
    public static final int DEFAULT_HEIGHT = 220;

    public MainClass()
    {
        Container logUi = getContentPane();

        logUi.setBackground(Color.WHITE);
        logUi.setLayout(null);
        //final JPasswordField password,name;
        final JTextField name;
        final JPasswordField password;

        JLabel nameJLabel = new JLabel();//Name:
        nameJLabel.setText("Username:");
        nameJLabel.setFont(new Font("Dilog", 1, 15));
        nameJLabel.setSize(100,50);
        nameJLabel.setLocation(70,20);
        logUi.add(nameJLabel);

        JLabel passwordJLabel = new JLabel();//Password:
        passwordJLabel.setText("Password:");
        passwordJLabel.setFont(new Font("Dilog", 1, 15));
        passwordJLabel.setSize(100, 50);
        passwordJLabel.setLocation(70, 50);
        logUi.add(passwordJLabel);

        name = new JTextField(20);//Name Field
        name.setBounds(150, 40, 200, 20);
        logUi.add(name);

        password = new JPasswordField(20);//Password Field
        password.setBounds(150, 70, 200, 20);
        logUi.add(password);

        JButton signIn = new JButton("Sign In");
        signIn.setFont(new Font("Dilog", 1, 15));
        signIn.setBounds(250, 100, 100, 50);
        logUi.add(signIn);

        signIn.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        String inputName;
                        String inputPassword;
                        inputName = name.getText();
                        char[] temp1 = password.getPassword();
                        inputPassword = new String(temp1);
                        Client client = new Client();
                        String sendContent = "Log:" + inputName + "*" + inputPassword;
                        String judge = client.sendSearch(sendContent);
                        String[] temp2 = judge.split("\\*");// get the authority and username
                        String authority = temp2[0];
                        String name = temp2[1];
                        String username = temp2[2];
                        String driver = temp2[3];
                        if(authority.equals("user"))
                        {
                            dispose();
                            JOptionPane.showMessageDialog(null,"Welcome! " + name);
                            UserConsole userConsole = new UserConsole(name, username, driver);
                        }
                        else if(authority.equals("root"))
                        {
                            dispose();
                            JOptionPane.showMessageDialog(null,"Welcome! " + name);
                            RootConsole rootConsole = new RootConsole(name, username, driver);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"user name/password is wrong!");
                        }

                    }
                }
        );




        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent arg0) {
                // TODO Auto-generated method stub
                System.exit(0);
            }

        });
        //password = new JPasswordField(20);



        //LogUi.add(passwordLabe1);
        //LogUi.add(password);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Logging In");
        setVisible(true);
    }

    public static void main(String[] args)
    {
        MainClass mainClass = new MainClass();
    }

}
