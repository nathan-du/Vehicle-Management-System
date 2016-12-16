package com.nathan.ui;

import com.nathan.net.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/12.
 */
public class Auto extends JFrame {
    private String name;
    private String username;

    public static final int DEFAULT_WIDTH = 900;
    public static final int DEFAULT_HEIGHT = 200;

    public Auto(String name, String username)
    {
        this.name = name;
        this.username = username;

        Container container = getContentPane();

        JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);//set tabbed pane

        JPanel lendAuto = new JPanel();//lend page
        GroupLayout layout = new GroupLayout(lendAuto);
        lendAuto.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String sendContent1 = "SelectAutoNum:";
        String sendContent2 = "SelectDriverName:";
        Client client = new Client();
        String tempAutoNum[] = client.sendSearch(sendContent1).split("\\*");
        String tempDriverName[] = client.sendSearch(sendContent2).split("\\*");

        JLabel autoNumJLabel = new JLabel("Auto Num");
        autoNumJLabel.setFont(new Font("Times", 1, 15));
        lendAuto.add(autoNumJLabel);

        JLabel driverNameJLabel = new JLabel("Driver Name");
        driverNameJLabel.setFont(new Font("Times", 1, 15));
        lendAuto.add(driverNameJLabel);

        JLabel detailJLabel = new JLabel("Detail");
        detailJLabel.setFont(new Font("Times", 1, 15));
        lendAuto.add(detailJLabel);

        JComboBox autoNumJComboBox = new JComboBox();//auto num JComboBox
        for(int i = 0; i < tempAutoNum.length; i++)
        {
            autoNumJComboBox.addItem(tempAutoNum[i]);
        }
        lendAuto.add(autoNumJComboBox);

        JComboBox driverNameComboBox = new JComboBox();//driver name JCombox
        for(int i = 0; i < tempDriverName.length; i++)
        {
            driverNameComboBox.addItem(tempDriverName[i]);
        }
        lendAuto.add(driverNameComboBox);

        JTextField detailJTextField = new JTextField(50);//detail
        lendAuto.add(detailJTextField);

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));
        lendAuto.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Dilog", 1, 15));
        lendAuto.add(submitButton);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(autoNumJLabel).addComponent(autoNumJComboBox).addComponent(backButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(driverNameJLabel).addComponent(driverNameComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(detailJLabel).addComponent(detailJTextField).addComponent(submitButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(autoNumJLabel)
                        .addComponent(driverNameJLabel)
                        .addComponent(detailJLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(autoNumJComboBox)
                        .addComponent(driverNameComboBox)
                        .addComponent(detailJTextField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton)
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
                        String autonum = autoNumJComboBox.getSelectedItem().toString();//get auto num
                        String driverName = driverNameComboBox.getSelectedItem().toString();//get driver name
                        String detail = detailJTextField.getText();//get detail
                        System.out.println(detail);
                        String sendContent = "AddLendAutoRequest:" + autonum + "*" + username + "*" + driverName + "*" + detail;
                        boolean judge = client.send(sendContent);
                        if(judge == true)
                        {
                            dispose();
                            JOptionPane.showMessageDialog(null, "You have lent the auto!");
                        }
                        else if(judge == false)
                        {
                            dispose();
                            JOptionPane.showMessageDialog(null, "Your request was rejected");
                        }
                    }
                }
        );

        JPanel returnAuto = new JPanel();//return page
        returnAuto.setLayout(new BorderLayout());

        //Set the Table
        String[] titles = {"NO.", "Auto Number", "Driver", "Date of lend", "Detail"};//defaultTableModel's titles
        DefaultTableModel defaultTableModel = new DefaultTableModel(null, titles);

        String sendContent = "SelectLendAutoRequest:" + username;
        String[] temp = client.sendSearch(sendContent).split("\\*");


        if(!temp[0].equals("null")) {
            // set the default value
            for (int i = 0; i < temp.length; i = i + 5) {
                temp[0] = temp[i];
                temp[1] = temp[i+1];
                temp[2] = temp[i+2];
                temp[3] = temp[i+3];
                temp[4] = temp[i+4];
                defaultTableModel.addRow(temp);
            }
        }
        JTable returnJTable = new JTable(defaultTableModel);
        returnJTable.getTableHeader().setFont(new Font("Times", 1, 15));

        //Set the scroll pane
        JScrollPane returnJScrollPane = new JScrollPane(returnJTable);
        returnJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        returnJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton back1Button = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));

        JButton submit1Button = new JButton("Submit");// submit button
        backButton.setFont(new Font("Dilog", 1, 15));

        JPanel bottomButtonJPanel = new JPanel(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        bottomButtonJPanel.add(back1Button, c);

        c.gridx = 2;
        bottomButtonJPanel.add(submit1Button, c);

        returnAuto.add(bottomButtonJPanel, BorderLayout.PAGE_END);
        returnAuto.add(returnJScrollPane, BorderLayout.CENTER);

        //back button action
        back1Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        submit1Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selRow = returnJTable.getSelectedRow();
                        System.out.println(selRow);
                        if(selRow != -1) {
                            String no = returnJTable.getValueAt(selRow , 0).toString();
                            String autonum = returnJTable.getValueAt(selRow , 1).toString();
                            String sendContent = "ConfirmReturnAuto:" + no + "*" + autonum;
                            boolean judge = client.send(sendContent);
                            if(judge == true)
                            {
                                dispose();
                                JOptionPane.showMessageDialog(null, "You have returned the auto!");
                            }
                            else
                            {
                                dispose();
                                JOptionPane.showMessageDialog(null, "You haven't returned the auto! Please Contact The Root.");
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Please select any row!");
                    }
                }
        );

        jTabbedPane.add(lendAuto, "Lend");
        jTabbedPane.add(returnAuto, "Return");
        container.add(jTabbedPane);



        setTitle("Auto");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);

    }

    public static void main(String[] args)
    {
        new Auto("Sherrie", "user");
    }
}
