package com.sergio.RegisterApp.views;


import com.github.lgooddatepicker.components.DatePicker;
import com.sergio.RegisterApp.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PrincipalWindow extends JFrame {
    private JTextField txtSearch;
    private JButton btnSearch;

    private JTable table;
    private JScrollPane scrollPane;
    private TableModel tableModel;

    private JButton btnAdd;
    private DatePicker datePicker;
    private ArrayList<String> columnNames;


    public PrincipalWindow(KeyListener kListener) {
        setTitle("Registro de usuarios");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setLayout(new GridBagLayout());
        initComponents(kListener);
        setVisible(true);
    }

    private void initComponents(KeyListener kListener) {
        txtSearch = new JTextField();
        txtSearch.addKeyListener(kListener);
        btnSearch = new JButton("Buscar");

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        btnAdd = new JButton("Agregar");
        datePicker = new DatePicker();

        initTable();
        posicionateComponents();
    }

    private void initTable() {
        columnNames = new ArrayList<>(
                List.of(new String[]{"Nombre", "Apellido", "Cedula"}));
        tableModel = new DefaultTableModel(columnNames.toArray(), 1);
        table.setModel(tableModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(null, "Hola");
                }
            }
        });
        table.setDefaultEditor(Object.class, null);
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.weightx = .8;
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
        gbc.gridx = 0;
        gbc.gridy++;
        add(datePicker, gbc);

    }

    public static void main(String[] args) {
        new PrincipalWindow(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar());
            }
        });
    }

    public String getKeywords() {
        return txtSearch.getText();
    }

    public void setCustomers(ArrayList<Customer> filterList) {
        System.out.println(filterList);
        Object[][] data = new Object[filterList.size()][columnNames.size()];
        for (int i = 0; i < filterList.size(); i++) {
            data[i][0] = filterList.get(i).getFirstNames();
            data[i][1] = filterList.get(i).getLastNames();
            data[i][2] = filterList.get(i).getDocNumber();
        }
        tableModel = new DefaultTableModel(data, columnNames.toArray());
        table.setModel(tableModel);
    }
}