package com.sergio.RegisterApp.views;

import com.github.lgooddatepicker.components.DatePicker;
import com.sergio.RegisterApp.exceptions.DoctypeInvalidException;
import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AddFrame extends JFrame {
    private JLabel lblTitle;
    private JLabel lblNames;
    private JLabel lblLastNames;
    private JLabel typeDoc;
    private JLabel lblDocument;
    private JLabel lblBirth;
    private JLabel lblAge;
    private JLabel lblRequired;

    private JTextField txtNames;
    private JTextField txtLastNames;
    private JTextField txtDocument;
    private DatePicker datePicker;
    private JTextField txtAge;
    private JComboBox<String> cmbTypeDoc;


    private JButton btnSave;

    public AddFrame(ActionListener aListener) {
        this.setTitle("Agregar Registro");
        this.setSize(360, 400);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents(aListener);
        posicionateComponents();
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new AddFrame(null);
    }

    public Customer getCustomer() throws DoctypeInvalidException {
        if (txtNames.getText().isEmpty() || txtLastNames.getText().isEmpty() || txtDocument.getText()
                .isEmpty() || datePicker.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Faltan informacion por ingresar para poder registrar al cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            DocType docType = null;
            for (DocType d : DocType.values()) {
                if (d.getDocType().equals(cmbTypeDoc.getSelectedItem())) {
                    docType = d;
                }
            }
            Customer cust = new Customer(txtNames.getText(), txtLastNames.getText(), docType, txtDocument.getText(),
                                         datePicker.getDate());
            if (cust.validateDocNumber(cust.getDocNumber(), cust.getDocType()).equalsIgnoreCase("Valido")) {
                if (cust.validateFullName(cust.getLastNames()).equalsIgnoreCase("Valido")) {
                    if (cust.validateFullName(cust.getFirstNames()).equalsIgnoreCase("Valido")) {
                        if (cust.validateLegalAge().equalsIgnoreCase("Valida")) {
                            return cust;
                        } else JOptionPane.showMessageDialog(null, "La persona que desea ingresar no posee la edad legal para ser cliente", "Error: Cliente no mayor de Edad", JOptionPane.ERROR_MESSAGE);
                    } else if (cust.validateFullName(cust.getFirstNames()).equalsIgnoreCase("Error 2")) {
                        JOptionPane.showMessageDialog(null, "Los caracteres del nombre deben ser unicamente letras",
                                                      "Error: Caracteres no Alfabeticos",
                                                      JOptionPane.ERROR_MESSAGE);
                    } else JOptionPane.showMessageDialog(null,
                                                         "Nombre excede el limite o es inferior al minimo de " + "caracteres [3,30]",
                                                         "Error: Limite de Caracteres", JOptionPane.ERROR_MESSAGE);
                } else if (cust.validateFullName(cust.getLastNames()).equalsIgnoreCase("Error 2")) {
                    JOptionPane.showMessageDialog(null, "Los caracteres  del apellido deben ser unicamente letras",
                                                  "Error: Caracteres no Alfabeticos",
                                                  JOptionPane.ERROR_MESSAGE);
                } else JOptionPane.showMessageDialog(null,
                                                     "Apellido excede el limite o es inferior al minimo de" + "caracteres [3,30]",
                                                     "Error: Limite de Caracteres", JOptionPane.ERROR_MESSAGE);
            } else if (cust.validateDocNumber(cust.getDocNumber(), cust.getDocType()).equalsIgnoreCase("Error 3")) {
                JOptionPane.showMessageDialog(null, "El numero de documento no es valido porque contiene letras",
                                              "Error: Tipo de ID no corresponde a No. de ID", JOptionPane.ERROR_MESSAGE);
            } else JOptionPane.showMessageDialog(null, "El documento no es valido, por que es inferior o superior a la cantidad de caracteres numericos permitidos [8,20]", "Error: Limite de Caracteres", JOptionPane.ERROR_MESSAGE);
        }
        throw new DoctypeInvalidException("Tipo de Documento Invalido");
    }

    private void posicionateComponents() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 10;
        gbc.ipadx = 70;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(lblTitle, gbc);

        gbc.gridy = 1;
        this.add(txtNames, gbc);
        gbc.gridy = 2;
        this.add(lblNames, gbc);
        gbc.gridy = 3;
        this.add(txtLastNames, gbc);
        gbc.gridy = 4;
        this.add(lblLastNames, gbc);
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        this.add(cmbTypeDoc, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        this.add(txtDocument, gbc);
        gbc.gridy = 6;
        this.add(lblDocument, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        this.add(typeDoc, gbc);
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        this.add(datePicker, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        this.add(lblBirth, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        this.add(lblRequired, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        this.add(btnSave, gbc);
    }

    private void initComponents(ActionListener aListener) {

        lblTitle = new JLabel("Agregar Nuevo Cliente");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblNames = new JLabel("*Nombres");
        lblNames.setHorizontalAlignment(JLabel.CENTER);
        lblLastNames = new JLabel("*Apellidos");
        lblLastNames.setHorizontalAlignment(JLabel.CENTER);
        typeDoc = new JLabel("*Tipo de ID");
        typeDoc.setHorizontalAlignment(JLabel.CENTER);
        lblDocument = new JLabel("*No. Documento");
        lblDocument.setHorizontalAlignment(JLabel.CENTER);
        lblBirth = new JLabel("*Fecha de Nacimiento");
        lblBirth.setHorizontalAlignment(JLabel.CENTER);
        lblRequired = new JLabel("* Todos los datos se deben completar o no se agregara el cliente");
        lblRequired.setText("<html><p style='color:red;'>* Todos los datos se deben completar o no se agregara el cliente</p></html>");

        txtNames = new JTextField();
        txtLastNames = new JTextField();
        txtDocument = new JTextField();
        datePicker = new DatePicker();
        txtAge = new JTextField();
        cmbTypeDoc = new JComboBox<>();

        btnSave = new JButton("Agregar");
        btnSave.addActionListener(aListener);
        btnSave.setActionCommand("added");
        initCmbBox();
    }

    private void initCmbBox() {
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(
                Arrays.stream(DocType.values()).map(DocType::getDocType).toArray(String[]::new));
        cmbTypeDoc.setModel(model);
    }
}
