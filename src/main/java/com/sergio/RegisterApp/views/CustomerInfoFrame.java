package com.sergio.RegisterApp.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.sergio.RegisterApp.controller.Commands;
import com.sergio.RegisterApp.model.Customer;

public class CustomerInfoFrame extends JFrame {

  private GridBagConstraints gbc = new GridBagConstraints();
  private Customer customer;
  private JButton deleteButton;
  private ActionListener aListener;

  public CustomerInfoFrame(Customer customer, ActionListener aListener) {
    this.customer = customer;
    setTitle("Informacion");
    this.aListener = aListener;
    setTitle("Informacion de usuario");
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    this.setLayout(new GridBagLayout());
    initComponents();
    setVisible(true);
  }

  public void initComponents() {
    gbc.ipadx = 30;
    gbc.ipady = 3;
    gbc.insets = new Insets(0, 1, 10, 1);
    JLabel infoTitle = new JLabel("Datos del cliente", SwingConstants.LEFT);
    infoTitle.setFont(new Font("Arial", Font.BOLD, 14));
  private void initComponents() {
    gbc.insets = new Insets(2, 2, 2, 2);
    JLabel infoTitle = new JLabel("Datos del cliente", SwingConstants.CENTER);
    gbc.gridx = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(infoTitle, gbc);

    gbc.insets = new Insets(0, 1, 3, 1);
    JLabel firstNames = new JLabel(customer.getFirstNames(), SwingConstants.CENTER);
    JLabel infoFirstName = new JLabel("Nombres del Cliente", SwingConstants.CENTER);
    firstNames.setFont(new Font("Verdana", Font.PLAIN, 14));
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(firstNames, gbc);
    gbc.gridy = 3;
    add(infoFirstName, gbc);

    JLabel lastNames = new JLabel(customer.getLastNames(), SwingConstants.CENTER);
    JLabel infoLastName = new JLabel("Apellidos del Cliente", SwingConstants.CENTER);
    lastNames.setFont(new Font("Verdana", Font.PLAIN, 14));
    gbc.gridy = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(lastNames, gbc);
    gbc.gridy = 6;
    add(infoLastName, gbc);
    gbc.gridwidth = 1;

    JLabel documentType = new JLabel(customer.getDocType().getDocType(), SwingConstants.CENTER);
    documentType.setFont(new Font("Verdana", Font.PLAIN, 14));
    JLabel infoDocumentType = new JLabel("Tipo Doc", SwingConstants.CENTER);
    gbc.gridy = 8;
    add(documentType, gbc);
    gbc.gridy = 9;
    add(infoDocumentType, gbc);

    JLabel documentNumber = new JLabel(customer.getDocNumber(), SwingConstants.CENTER);
    JLabel infoDocumentNumber = new JLabel("No Document", SwingConstants.CENTER);
    documentNumber.setFont(new Font("Verdana", Font.PLAIN, 14));
    gbc.gridy = 8;
    gbc.gridx = 1;
    add(documentNumber, gbc);
    gbc.gridy = 9;
    add(infoDocumentNumber, gbc);

    JLabel birthDate = new JLabel(customer.getBirthDate(), SwingConstants.CENTER);
    JLabel infoBirdDate = new JLabel("Fecha Nacimiento", SwingConstants.CENTER);
    birthDate.setFont(new Font("Verdana", Font.PLAIN, 14));
    gbc.gridy = 10;
    gbc.gridx = 0;
    add(birthDate, gbc);
    gbc.gridy = 11;
    add(infoBirdDate, gbc);

    JLabel age = new JLabel(String.valueOf(customer.getAge(customer.getBirthDate())), SwingConstants.CENTER);
    JLabel infoAge = new JLabel("Edad", SwingConstants.CENTER);
    age.setFont(new Font("Verdana", Font.PLAIN, 14));
    gbc.gridy = 10;
    gbc.gridx = 1;
    add(age, gbc);
    gbc.gridy = 11;
    add(infoAge, gbc);

    JButton updateButton = new JButton("Actualizar");
    JButton deleteButton = new JButton("Eliminar");
    gbc.ipady = 2;
    gbc.ipadx = 4;
    gbc.gridx = 0;
    gbc.gridy = 13;
    JButton updateButton = new JButton("Acualizar");
    deleteButton = new JButton("Eliminar");
    gbc.gridy = 12;
    add(updateButton, gbc);
    gbc.gridx = 1;
    gbc.gridy = 13;
    deleteButton.addActionListener(aListener);
    deleteButton.setActionCommand(Commands.C_REMOVE_CUSTOMER.toString());
    add(deleteButton, gbc);
  }
}
