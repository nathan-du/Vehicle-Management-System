package com.nathan.ui;

import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/11.
 */
public class DriverProfile extends JFrame{
    private String username;
    private String idDriver;
    private String name;
    private String gender;
    private String birthDate;
    private String licenseDate;
    private String fileNum;
    private String type;
    private String phoneNum;

    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 244;

    public DriverProfile(String username)
    {
        this.username = username;

        getProfile();

        Container container = getContentPane();
        container.setBackground(Color.white);
        container.setLayout(null);

        JLabel nameJLabel = new JLabel("Name: " + name);//name
        nameJLabel.setFont(new Font("Times", 1, 15));
        nameJLabel.setSize(200, 20);
        nameJLabel.setLocation(40, 20);
        container.add(nameJLabel);

        JLabel genderJLabel = new JLabel("Gender: " + gender);//gender
        genderJLabel.setFont(new Font("Times", 1, 15));
        genderJLabel.setSize(200, 20);
        genderJLabel.setLocation(40, 50);
        container.add(genderJLabel);

        JLabel typeJLabel = new JLabel("Class: " + type);//Class
        typeJLabel.setFont(new Font("Times", 1, 15));
        typeJLabel.setSize(200, 20);
        typeJLabel.setLocation(40, 80);
        container.add(typeJLabel);

        JLabel phoneNumJLabel = new JLabel("Phone Number: " + phoneNum);//phone number
        phoneNumJLabel.setFont(new Font("Times", 1, 15));
        phoneNumJLabel.setSize(200, 20);
        phoneNumJLabel.setLocation(40, 110);
        container.add(phoneNumJLabel);

        JLabel idJLabel = new JLabel("ID: " + idDriver);//id number
        idJLabel.setFont(new Font("Times", 1, 15));
        idJLabel.setSize(200, 20);
        idJLabel.setLocation(340, 20);
        container.add(idJLabel);

        JLabel birthDateJLabel = new JLabel("Date of Birth: " + birthDate);//birth of date
        birthDateJLabel.setFont(new Font("Times", 1, 15));
        birthDateJLabel.setSize(200, 20);
        birthDateJLabel.setLocation(340, 50);
        container.add(birthDateJLabel);

        JLabel licenseDateJLabel = new JLabel("Date of First Issue: " + licenseDate);//date of first issue
        licenseDateJLabel.setFont(new Font("Times", 1, 15));
        licenseDateJLabel.setSize(260, 20);
        licenseDateJLabel.setLocation(340, 80);
        container.add(licenseDateJLabel);

        JLabel fileNumJLabel = new JLabel("File Number: " + fileNum);//file number
        fileNumJLabel.setFont(new Font("Times", 1, 15));
        fileNumJLabel.setSize(200, 20);
        fileNumJLabel.setLocation(340, 109);
        container.add(fileNumJLabel);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Dilog", 1, 15));
        backButton.setBounds(40, 150, 150, 50);
        container.add(backButton);

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        setTitle("Driver's Profile");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    /**
     * get profile
     */
    public void getProfile()
    {
        Client client = new Client();
        String sendContent = "RequestDriverProfile:" + username;
        String returnContent = client.sendSearch(sendContent);
        String[] temp = returnContent.split("\\*");
        this.idDriver = temp[0];
        this.name = temp[1];
        this.gender = temp[2];
        this.birthDate = temp[3];
        this.licenseDate = temp[4];
        this.fileNum = temp[5];
        this.type = temp[6];
        this.phoneNum = temp[7];
    }

    public static void main(String[] args)
    {
        new DriverProfile("673650096");
    }
}
