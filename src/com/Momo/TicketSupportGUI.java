package com.Momo;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Momo Johnson on 4/7/2016.
 */
public class TicketSupportGUI extends JFrame {
    //Declaring of various variable for the JFrame
    private JTextField problemTicketField;
    private JTextField reportedNameField;
    private JComboBox priorityLevel;//The Jcombox Variable
    private JPanel rootPanel;
    private JButton addButton;
    private JList<Ticket> allOpenTickets;//The JList Variable
    private JButton deleteTicketsButton;
    private JList ResolvedTickets;
    private JButton resolvedSelectedButton;
    protected static LinkedList<Ticket> resolvedTickets;
    DefaultListModel resolvedTicketsModel;


    DefaultListModel<Ticket> ticketsListModel;//Default JList Variable


    //Constructor of the TicketSupportGUI
    public TicketSupportGUI() {
        super("Ticket Support Program");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configurepriority();
        configurepriorityButton();
        setPreferredSize(new Dimension(600, 300));
        rootPanel.setBackground(Color.gray);

        problemTicketField.setBackground(Color.yellow);
        allOpenTickets.setBackground(Color.white);
        pack();
        setVisible(true);
        ticketsListModel = new DefaultListModel<>();
        resolvedTickets = new LinkedList<>();
        resolvedTicketsModel = new DefaultListModel();
        deleteTickets();
        resolvedTickets();

    }

    //Configuration of the JCombox to contain five options
    private void configurepriority() {
        for (int i = 1; i <= 5; i++) {
            priorityLevel.addItem(i);

        }
    }

    //Configuration of the cof the priority button
    private void configurepriorityButton() {
        //Add Listener for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            //Action to peformed by the add button
            public void actionPerformed(ActionEvent e) {
                String problem = problemTicketField.getText();
                String reporter = reportedNameField.getText();
                String priority = priorityLevel.getSelectedItem().toString();
                int priority1 = Integer.parseInt(priority);
                //The Ticket Object being instantiated
                Ticket myTest = new Ticket(problem, priority1, reporter, new Date());
                ticketsListModel.addElement(myTest);//Adding the ticket elements to the default  JList

                allOpenTickets.setModel(ticketsListModel);// Adding the default JList to the JList


            }

        });


    }

    public void deleteTickets() {
        deleteTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ticket toDelete;
                for (int i = 0; i < ticketsListModel.size(); i++) {
                    toDelete = allOpenTickets.getSelectedValue();
                    if (toDelete == null) {
                        JOptionPane.showMessageDialog(TicketSupportGUI.this, "None selected");
                        return;
                    }
                    if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(TicketSupportGUI.this, "Are you sure you want to delete and exit?", "Delete?", JOptionPane.OK_CANCEL_OPTION)) {
                        allOpenTickets.clearSelection();
                        ticketsListModel.removeElement(toDelete);

                        // toDelete = addTicketsJList.clearSelection();
                        allOpenTickets.clearSelection();
                    }
                    break;
                }
                allOpenTickets.clearSelection();

            }

        });


    }

    public void resolvedTickets() {
        resolvedSelectedButton.addActionListener(new ActionListener() {
            @Override


            public void actionPerformed(ActionEvent e) {
                String enterSolution = null;
                Ticket toResolve = null;
                FileWriter writer = null;
                for (int i = 0; i < ticketsListModel.size(); i++) {
                    toResolve = allOpenTickets.getSelectedValue();
//                toDelete = addTicketsJList.clearSelection();
                    if (toResolve == null) {
                        JOptionPane.showMessageDialog(TicketSupportGUI.this, "None selected");
                        return;
                    }
                    enterSolution = JOptionPane.showInputDialog("Enter solution");

                    if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(TicketSupportGUI.this, "Do you want to delete ticket?", "Exit?", JOptionPane.OK_CANCEL_OPTION)) {
                        allOpenTickets.clearSelection();
                        //Save all of the data...
                        resolvedTicketsModel.addElement(toResolve);
                        resolvedTicketsModel.addElement("Solution: " + enterSolution);

                        ticketsListModel.removeElement(toResolve);
                        allOpenTickets.clearSelection();
                        Ticket.saveResolvedTickets(toResolve);
                    }
                    break;
                }
               allOpenTickets.clearSelection();


//                String toResolve = String.valueOf(addTicketsJList.getSelectedValue());
//                String solution = JOptionPane.showInputDialog("Enter solution");


            }
        });

    }




}
