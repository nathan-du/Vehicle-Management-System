package com.nathan.uiRoot;


import com.nathan.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;


/**
 * Created by Nathan on 16/4/16.
 */
public class AddDriver extends JFrame implements ItemListener {
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 350;

    JComboBox birthYear = new JComboBox();
    JComboBox birthMonth = new JComboBox();
    JComboBox birthDay = new JComboBox();
    JComboBox licenseYear = new JComboBox();
    JComboBox licenseMonth = new JComboBox();
    JComboBox licenseDay = new JComboBox();

    private Calendar startDate = Calendar.getInstance();

    public AddDriver()
    {
        Container container = getContentPane();

        JPanel addDriverJPanel = new JPanel();
        GroupLayout layout = new GroupLayout(addDriverJPanel);
        addDriverJPanel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JPanel birthDateJPanel = new JPanel();
        FlowLayout birthDateLayout = new FlowLayout();
        birthDateJPanel.setLayout(birthDateLayout);

        JPanel licenseDateJPanel = new JPanel();
        FlowLayout licenseDateLayout = new FlowLayout();
        licenseDateJPanel.setLayout(licenseDateLayout);


        // get the data
        Client client = new Client();
        String[] tempUserName = client.sendSearch("SelectAllNoDriver:").split("\\*");


        //initialize the JLabel
        JLabel userNameJLabel = new JLabel("User Name:");
        userNameJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(userNameJLabel);

        JLabel genderJLabel = new JLabel("Gender:");
        genderJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(genderJLabel);

        JLabel birthDateJLabel = new JLabel("Date of Birth:");
        birthDateJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(birthDateJLabel);

        JLabel licenseDateJLabel = new JLabel("Date of First Issue:");
        licenseDateJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(licenseDateJLabel);

        JLabel fileNumJLabel = new JLabel("File Number:");
        fileNumJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(fileNumJLabel);

        JLabel idDriverJLabel = new JLabel("Id Number:");
        idDriverJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(idDriverJLabel);

        JLabel typeOfOilJLabel = new JLabel("Type:");
        typeOfOilJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(typeOfOilJLabel);

        JLabel phoneNumJLabel = new JLabel("Phone Number:");
        phoneNumJLabel.setFont(new Font("Times", 1, 15));
        addDriverJPanel.add(phoneNumJLabel);


        //initialize the JTextField and JComboBox
        JComboBox userNameJComboBox = new JComboBox();
        for(int i = 0; i < tempUserName.length; i++)
        {
            userNameJComboBox.addItem(tempUserName[i]);
        }
        addDriverJPanel.add(userNameJComboBox);

        JComboBox genderJComboBox = new JComboBox();
        genderJComboBox.addItem("Female");
        genderJComboBox.addItem("Male");
        addDriverJPanel.add(genderJComboBox);

        JTextField fileNumJTextField = new JTextField(12);
        addDriverJPanel.add(fileNumJTextField);

        JTextField idDriverJTextField = new JTextField(18);
        addDriverJPanel.add(idDriverJTextField);

        JComboBox typeJComboBox = new JComboBox();
        typeJComboBox.addItem("A");
        typeJComboBox.addItem("B");
        typeJComboBox.addItem("C");
        typeJComboBox.addItem("D");
        typeJComboBox.addItem("E");
        addDriverJPanel.add(typeJComboBox);

        JTextField phoneNumJTextField = new JTextField(15);
        addDriverJPanel.add(phoneNumJTextField);


        //Day, Month and Year
        buildYearsList(birthYear);
        birthYear.setSelectedIndex(70);
        birthYear.addItemListener(this);
        birthDateJPanel.add(birthYear);

        buildMonthsList(birthMonth);
        birthMonth.setSelectedIndex(startDate.get(Calendar.MONTH));
        birthMonth.addItemListener(this);
        birthDateJPanel.add(birthMonth);

        buildDaysList(startDate, birthDay, birthMonth);
        birthDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
        birthDay.addItemListener(this);
        birthDateJPanel.add(birthDay);

        buildYearsList(licenseYear);
        licenseYear.setSelectedIndex(70);
        licenseYear.addItemListener(this);
        licenseDateJPanel.add(licenseYear);

        buildMonthsList(licenseMonth);
        licenseMonth.setSelectedIndex(startDate.get(Calendar.MONTH));
        licenseMonth.addItemListener(this);
        licenseDateJPanel.add(licenseMonth);

        buildDaysList(startDate, licenseDay, licenseMonth);
        licenseDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
        licenseDay.addItemListener(this);
        licenseDateJPanel.add(licenseDay);


        //JButton
        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        addDriverJPanel.add(backButton);

        JButton submitButton = new JButton("Submit");// submit button
        submitButton.setFont(new Font("Dilog", 1, 15));
        addDriverJPanel.add(submitButton);


        //layout setting

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(userNameJLabel).addComponent(userNameJComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(genderJLabel).addComponent(genderJComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(birthDateJLabel).addComponent(birthDateJPanel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(licenseDateJLabel).addComponent(licenseDateJPanel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(fileNumJLabel).addComponent(fileNumJTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(idDriverJLabel).addComponent(idDriverJTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(typeOfOilJLabel).addComponent(typeJComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(phoneNumJLabel).addComponent(phoneNumJTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(backButton).addComponent(submitButton))

        );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userNameJLabel)
                                .addComponent(genderJLabel)
                                .addComponent(birthDateJLabel)
                                .addComponent(licenseDateJLabel)
                                .addComponent(fileNumJLabel)
                                .addComponent(idDriverJLabel)
                                .addComponent(typeOfOilJLabel)
                                .addComponent(phoneNumJLabel)
                                .addComponent(backButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userNameJComboBox)
                                .addComponent(genderJComboBox)
                                .addComponent(birthDateJPanel)
                                .addComponent(licenseDateJPanel)
                                .addComponent(fileNumJTextField)
                                .addComponent(idDriverJTextField)
                                .addComponent(typeJComboBox)
                                .addComponent(phoneNumJTextField)
                                .addComponent(submitButton)
                        )
        );


        //ActionListener
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
                        String username = userNameJComboBox.getSelectedItem().toString();
                        String gender = genderJComboBox.getSelectedItem().toString();
                        String birthDate = birthYear.getSelectedItem().toString() + "." + birthMonth.getSelectedItem().toString() + "." + birthDay.getSelectedItem().toString();
                        String licenseDate = licenseYear.getSelectedItem().toString() + "." + licenseMonth.getSelectedItem().toString() + "." + licenseDay.getSelectedItem().toString();
                        String fileNum = fileNumJTextField.getText();
                        String idDriver = idDriverJTextField.getText();
                        String typeOfOil = typeJComboBox.getSelectedItem().toString();
                        String phoneNum = phoneNumJTextField.getText();
                        if(fileNum.equals("") || idDriver.equals("") || phoneNum.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Don't submit blank text field!");
                        }
                        else
                        {
                            String sendContent = "AddDriver:" + idDriver + "*" + gender + "*" + birthDate + "*" + licenseDate + "*" + fileNum + "*" + typeOfOil + "*" + phoneNum + "*" + username;
                            boolean judge = client.send(sendContent);
                            if(judge == true)
                            {
                                dispose();
                                JOptionPane.showMessageDialog(null, "The driver has been added!");
                            }
                            else if(judge == false)
                            {
                                JOptionPane.showMessageDialog(null, "You haven't add the driver! Please Contact The Root.");
                            }
                        }
                    }
                }
        );

        container.add(addDriverJPanel);
        setTitle("Add Driver");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    /**
     * This method builds the list of years for the start
     * date and end date of the semester
     * @param yearsList The combo box containing the years
     */
    private void buildYearsList(JComboBox yearsList) {

        int currentYear = startDate.get(Calendar.YEAR);

        for (int yearCount = currentYear - 70; yearCount <= currentYear; yearCount++)
            yearsList.addItem(Integer.toString(yearCount));
    }

    /**
     * This method builds the list of months for the start
     * date and end date of the semester
     * @param monthsList The combo box containing the months
     */
    private void buildMonthsList(JComboBox monthsList) {

        monthsList.removeAllItems();
        for (int monthCount = 0; monthCount < 12; monthCount++)
            monthsList.addItem(monthCount + 1);
    }

    /**
     * This method builds the list of years for the start
     * date and end date of the semester
     * @param dateIn The current date, which will be used for
     * the initial date of the lists
     * @param daysList The combo box that will contain the days
     * @param monthsList The combo box that will contain the months
     */
    private void buildDaysList(Calendar dateIn, JComboBox daysList, JComboBox monthsList) {

        daysList.removeAllItems();
        dateIn.set(Calendar.MONTH, monthsList.getSelectedIndex());
        int lastDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int dayCount = 1; dayCount <= lastDay; dayCount++)
            daysList.addItem(Integer.toString(dayCount));
    }

    public void itemStateChanged(ItemEvent event) {

        if (event.getSource() == birthYear &&
                event.getStateChange() == ItemEvent.SELECTED) {

            int year = Integer.parseInt((String) birthYear.getSelectedItem());
            startDate.set(Calendar.YEAR, year);
            birthMonth.setSelectedIndex(0);
            startDate.set(Calendar.MONTH, 0);
            buildDaysList(startDate, birthDay, birthMonth);
            startDate.set(Calendar.DATE, 1);
        } else if (event.getSource() == birthMonth &&
                event.getStateChange() == ItemEvent.SELECTED) {

            startDate.set(Calendar.MONTH, birthMonth.getSelectedIndex());
            buildDaysList(startDate, birthDay, birthMonth);
            startDate.set(Calendar.DATE, 1);
        } else if (event.getSource() == birthDay &&
                event.getStateChange() == ItemEvent.SELECTED) {

            int day = Integer.parseInt((String) birthDay.getSelectedItem());
            startDate.set(Calendar.DATE, day);
        } else if (event.getSource() == licenseYear &&
                event.getStateChange() == ItemEvent.SELECTED) {

            int year = Integer.parseInt((String) licenseYear.getSelectedItem());
            startDate.set(Calendar.YEAR, year);
            licenseMonth.setSelectedIndex(0);
            startDate.set(Calendar.MONTH, 0);
            buildDaysList(startDate, licenseDay, licenseMonth);
            startDate.set(Calendar.DATE, 1);
        } else if (event.getSource() == licenseMonth &&
                event.getStateChange() == ItemEvent.SELECTED) {

            startDate.set(Calendar.MONTH, licenseMonth.getSelectedIndex());
            buildDaysList(startDate, licenseDay, licenseMonth);
            startDate.set(Calendar.DATE, 1);
        } else if (event.getSource() == licenseDay &&
                event.getStateChange() == ItemEvent.SELECTED) {

            int day = Integer.parseInt((String) licenseDay.getSelectedItem());
            startDate.set(Calendar.DATE, day);
        }
    }

    public static void main(String[] args)
    {
        new AddDriver();
    }
}
