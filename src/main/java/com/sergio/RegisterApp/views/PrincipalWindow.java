package com.sergio.RegisterApp.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrincipalWindow extends JFrame {
    private JTextField txtSearch;
    private JButton btnSearch;

    private JTable table;
    private JScrollPane scrollPane;
    private TableModel tableModel;

    public PrincipalWindow() {
        setTitle("Registro de usuarios");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setLayout(new GridBagLayout());
        initComponents();
        setVisible(true);
    }

    public static void main(String[] args) {
        new PrincipalWindow();
    }

    private void initComponents() {
        txtSearch = new JTextField();
        btnSearch = new JButton("Buscar");

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        initTable();
        posicionateComponents();
    }

    private void initTable() {
        ArrayList<String> columnNames = new ArrayList<>(
                List.of(new String[]{"Nombre", "Apellido", "Edad", "Dirección"}));
        tableModel = new DefaultTableModel(columnNames.toArray(), 10);
        table.setModel(tableModel);
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

    }
}