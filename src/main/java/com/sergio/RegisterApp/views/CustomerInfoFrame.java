package com.sergio.RegisterApp.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;

public class CustomerInfoFrame extends JFrame {

  private GridBagConstraints gbc = new GridBagConstraints();
  private Customer customer;

  public CustomerInfoFrame(Customer customer) {
    this.customer = customer;
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
    gbc.insets = new Insets(2, 2, 2, 2);
    JLabel infoTitle = new JLabel("Datos del cliente", SwingConstants.CENTER);
    gbc.gridx = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridy = 0;
    add(infoTitle, gbc);
    JLabel firstNames = new JLabel(customer.getFirstNames(), SwingConstants.CENTER);
    JLabel infoFirstName = new JLabel("Nombres Cliente", SwingConstants.CENTER);
    firstNames.setFont(new Font("Verdana", Font.PLAIN, 14));
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(firstNames, gbc);
    gbc.gridy = 3;
    add(infoFirstName, gbc);

    JLabel lastNames = new JLabel(customer.getLastNames(), SwingConstants.CENTER);
    JLabel infoLastName = new JLabel("Apellidos Cliente", SwingConstants.CENTER);
    lastNames.setFont(new Font("Verdana", Font.PLAIN, 14));
    gbc.gridy = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(lastNames, gbc);
    gbc.gridy = 6;
    add(infoLastName, gbc);

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

    JButton updateButton = new JButton("Acualizar");
    JButton deleteButton = new JButton("Eliminar");
    gbc.gridy = 12;
    add(updateButton, gbc);
    gbc.gridy = 13;
    add(deleteButton, gbc);
  }

  public static void main(String[] args) {
    new CustomerInfoFrame(
        new Customer("Sergio", "Perez", DocType.citizenshipCard, "12345678", LocalDate.parse("1996-01-01")));
  }
}
