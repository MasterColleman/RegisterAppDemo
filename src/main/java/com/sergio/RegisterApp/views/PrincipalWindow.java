package com.sergio.RegisterApp.views;

import com.sergio.RegisterApp.exceptions.DoctypeInvalidException;
import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class PrincipalWindow extends JFrame {
    private JTextField txtSearch;
    private JLabel btnSearch;
    private JTable table;
    private JScrollPane scrollPane;
    private TableModel tableModel;
    private JButton btnAdd;
    private ArrayList<String> columnNames;

    private CustomerInfoFrame customerInfoFrame;
    private AddFrame addFrame;
    private UpdateFrame updateFrame;

    private JLabel lblSergio;


    public PrincipalWindow(KeyListener kListener, MouseListener mListener, ActionListener aListener) {
        setTitle("Sergio Suárez - 201912254");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setLayout(new GridBagLayout());
        initComponents(kListener, mListener, aListener);
        setVisible(true);
    }


    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(lblSergio, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.weightx = .8;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(txtSearch, gbc);
        gbc.gridx++;
        gbc.weightx = .2;
        add(btnSearch, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnAdd, gbc);
    }

    public String getKeywords() {
        return txtSearch.getText();
    }


    private void initComponents(KeyListener kListener, MouseListener mListener, ActionListener aListener) {
        txtSearch = new JTextField();
        txtSearch.addKeyListener(kListener);
        btnSearch = new JLabel("Filtrar");

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        btnAdd = new JButton("Agregar");
        btnAdd.addActionListener(aListener);
        btnAdd.setActionCommand("add");
        lblSergio = new JLabel("REGISTER APP");

        addFrame = new AddFrame(aListener);
        updateFrame = new UpdateFrame(aListener);
        initTable(mListener);
        posicionateComponents();
    }


    private void initTable(MouseListener mListener) {
        columnNames = new ArrayList<>(List.of(new String[]{"Nombre", "Apellido", "Cedula", "Tipo", "Nacimiento"}));
        tableModel = new DefaultTableModel(columnNames.toArray(), 0);
        table.setModel(tableModel);
        table.addMouseListener(mListener);
        table.setDefaultEditor(Object.class, null);
    }


    public void setCustomer(Customer customer, ActionListener aListener) {
        customerInfoFrame = new CustomerInfoFrame(customer, aListener);
    }

    public boolean showConfirmDialog() {
        return (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                                              JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    public void closeCustomerInfoFrame() {
        customerInfoFrame.dispatchEvent(new WindowEvent(customerInfoFrame, WindowEvent.WINDOW_CLOSING));
    }

    public String getSelectedCustomerID() {
        return String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
    }

    public DocType getSelectedCustomerDocType() throws DoctypeInvalidException {
        String type = table.getValueAt(table.getSelectedRow(), 3).toString();
        for (DocType docType : DocType.values()) {
            if (docType.getDocType().equals(type)) return docType;
        }
        throw new DoctypeInvalidException();
    }

    public void openAddFrame() {
        addFrame.setVisible(true);
    }

    public Customer getCustomer() throws DoctypeInvalidException {
        return addFrame.getCustomer();
    }


    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void closeAddWindow() {
        addFrame.dispose();
    }

    public void setCustomers(List<Customer> filterList) {
        System.out.println(filterList);
        Object[][] data = new Object[filterList.size()][columnNames.size()];
        for (int i = 0; i < filterList.size(); i++) {
            data[i][0] = filterList.get(i).getFirstNames();
            data[i][1] = filterList.get(i).getLastNames();
            data[i][2] = filterList.get(i).getDocNumber();
            data[i][3] = filterList.get(i).getDocType().getDocType();
            data[i][4] = filterList.get(i).getBirthDate();
        }
        tableModel = new DefaultTableModel(data, columnNames.toArray());
        table.setModel(tableModel);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
    }

    public void openUpdateFrame(ActionListener aListener) {
        updateFrame = new UpdateFrame(aListener);
        updateFrame.setVisible(true);
    }

    public Customer getUpdatedCustomer() throws DoctypeInvalidException {
        return updateFrame.getCustomer();
    }

    public void closeUpdateWindow() {
        customerInfoFrame.dispose();
        updateFrame.dispose();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}