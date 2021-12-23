package com.sergio.RegisterApp.views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.sergio.RegisterApp.model.Customer;

public class CustomerInfoFrame extends JFrame {

    private GridBagConstraints gbc = new GridBagConstraints();
    private Customer customer;
    private JButton updateButton;
    private JButton deleteButton;
    private ActionListener aListener;

    public CustomerInfoFrame(Customer customer, ActionListener aListener) {
        this.customer = customer;
        setTitle("Informacion");
        this.aListener = aListener;
        setSize(314, 340);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setLayout(new GridBagLayout());
        initComponents();
        setVisible(true);
    }

    public void initComponents() {
        gbc.ipadx = 30;
        gbc.ipady = 6;
        gbc.insets = new Insets(1, 3, 9, 1);
        JLabel infoTitle = new JLabel("Datos del cliente", SwingConstants.LEFT);
        infoTitle.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(infoTitle, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel firstNames = new JLabel(customer.getFirstNames(), SwingConstants.CENTER);
        JLabel infoFirstName = new JLabel("Nombres", SwingConstants.CENTER);
        firstNames.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoFirstName.setFont(new Font("Verdana", Font.BOLD, 12));
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(firstNames, gbc);
        gbc.gridy = 3;
        add(infoFirstName, gbc);

        JLabel lastNames = new JLabel(customer.getLastNames(), SwingConstants.CENTER);
        JLabel infoLastName = new JLabel("Apellidos", SwingConstants.CENTER);
        lastNames.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoLastName.setFont(new Font("Verdana", Font.BOLD, 12));
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(lastNames, gbc);
        gbc.gridy = 6;
        add(infoLastName, gbc);
        gbc.gridwidth = 1;

        JLabel documentType = new JLabel(customer.getDocType().getDocType(), SwingConstants.CENTER);
        JLabel infoDocumentType = new JLabel("Tipo ID", SwingConstants.CENTER);
        documentType.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoDocumentType.setFont(new Font("Verdana", Font.BOLD, 12));
        gbc.gridy = 8;
        add(documentType, gbc);
        gbc.gridy = 9;
        add(infoDocumentType, gbc);

        JLabel documentNumber = new JLabel(customer.getDocNumber(), SwingConstants.CENTER);
        JLabel infoDocumentNumber = new JLabel("No ID", SwingConstants.CENTER);
        documentNumber.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoDocumentNumber.setFont(new Font("Verdana", Font.BOLD, 12));
        gbc.gridy = 8;
        gbc.gridx = 1;
        add(documentNumber, gbc);
        gbc.gridy = 9;
        add(infoDocumentNumber, gbc);

        JLabel birthDate = new JLabel(customer.getBirthDate(), SwingConstants.CENTER);
        JLabel infoBirdDate = new JLabel("Fecha Nac.", SwingConstants.CENTER);
        birthDate.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoBirdDate.setFont(new Font("Verdana", Font.BOLD, 12));
        gbc.gridy = 10;
        gbc.gridx = 0;
        add(birthDate, gbc);
        gbc.gridy = 11;
        add(infoBirdDate, gbc);

        JLabel age = new JLabel(String.valueOf(customer.getAge(customer.getBirthDate()) + " Años"), SwingConstants.CENTER);
        JLabel infoAge = new JLabel("Edad", SwingConstants.CENTER);
        age.setFont(new Font("Verdana", Font.PLAIN, 16));
        infoAge.setFont(new Font("Verdana", Font.BOLD, 12));
        gbc.gridy = 10;
        gbc.gridx = 1;
        add(age, gbc);
        gbc.gridy = 11;
        add(infoAge, gbc);

        gbc.insets = new Insets(10, 8, 7, 7);
        updateButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");

        gbc.gridx = 0;
        gbc.gridy = 16;

        updateButton.addActionListener(aListener);
        updateButton.setActionCommand("update");
        add(updateButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 16;

        deleteButton.addActionListener(aListener);
        deleteButton.setActionCommand("remove");
        add(deleteButton, gbc);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("register_Icon.ico")));
    }
}