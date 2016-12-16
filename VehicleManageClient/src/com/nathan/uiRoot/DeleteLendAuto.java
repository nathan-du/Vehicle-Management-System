package com.nathan.uiRoot;

import com.nathan.net.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nathan on 16/4/17.
 */
public class DeleteLendAuto extends JFrame {
    public static final int DEFAULT_WIDTH = 900;
    public static final int DEFAULT_HEIGHT = 200;

    public DeleteLendAuto()
    {
        Container container = getContentPane();

        JPanel returnAuto = new JPanel();//return page
        returnAuto.setLayout(new BorderLayout());

        //Set the Table
        String[] titles = {"NO.", "Auto Number", "username", "Driver", "Date of lend", "Detail"};//defaultTableModel's titles
        DefaultTableModel defaultTableModel = new DefaultTableModel(null, titles);

        String sendContent = "SelectAllLendAutoRequest:";
        Client client = new Client();
        String[] temp = client.sendSearch(sendContent).split("\\*");


        if(!temp[0].equals("null")) {
            // set the default value
            for (int i = 0; i < temp.length; i = i + 6) {
                temp[0] = temp[i];
                temp[1] = temp[i+1];
                temp[2] = temp[i+2];
                temp[3] = temp[i+3];
                temp[4] = temp[i+4];
                temp[5] = temp[i+5];

                defaultTableModel.addRow(temp);

            }
        }
        JTable returnJTable = new JTable(defaultTableModel);
        returnJTable.getTableHeader().setFont(new Font("Times", 1, 15));

        //Set the scroll pane
        JScrollPane returnJScrollPane = new JScrollPane(returnJTable);
        returnJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        returnJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton backButton = new JButton("Back");// back button
        backButton.setFont(new Font("Dilog", 1, 15));

        JButton submitButton = new JButton("Submit");// submit button
        submitButton.setFont(new Font("Dilog", 1, 15));

        JPanel bottomButtonJPanel = new JPanel(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        bottomButtonJPanel.add(backButton, c);

        c.gridx = 2;
        bottomButtonJPanel.add(submitButton, c);

        returnAuto.add(bottomButtonJPanel, BorderLayout.PAGE_END);
        returnAuto.add(returnJScrollPane, BorderLayout.CENTER);

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
                        int selRow = returnJTable.getSelectedRow();
                        if(selRow != -1)
                        {
                            String no = returnJTable.getValueAt(selRow , 0).toString();
                            boolean judge = client.send("DeleteLendAuto:" + no);
                            if(judge == true)
                            {
                                dispose();
                                JOptionPane.showMessageDialog(null, "You have deleted the record!");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Error! Please contact the Tech!");
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Please select any row!");
                    }
                }
        );

        container.add(returnAuto);
        setTitle("Delete Lending Auto");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
    }

    public static void main(String[]args)
    {
        new DeleteLendAuto();
    }
}
