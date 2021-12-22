package com.sergio.RegisterApp.views;

import com.github.lgooddatepicker.components.DatePicker;
import com.sergio.RegisterApp.exceptions.DoctypeInvalidException;
import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UpdateFrame extends JFrame {
    private JLabel lblTitle;
    private JLabel lblNames;
    private JLabel lblLastNames;
    private JLabel typeDoc;
    private JLabel lblDocument;
    private JLabel lblBirth;
    private JLabel lblRequired;

    private JTextField txtNames;
    private JTextField txtLastNames;
    private JTextField txtDocument;
    private DatePicker datePicker;
    private JComboBox<String> cmbTypeDoc;


    private JButton btnSave;

    public UpdateFrame(ActionListener aListener) {
        this.setTitle("Actualizar Cliente");
        this.setSize(400 , 400);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents(aListener);
        posicionateComponents();
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new UpdateFrame(null);
    }

    public Customer updateCustomer() throws DoctypeInvalidException {
        if (txtNames.getText().isEmpty() || txtLastNames.getText().isEmpty() || txtDocument.getText()
                .isEmpty() || datePicker.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
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
                        } else JOptionPane.showMessageDialog(null, "Edad No Legal", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (cust.validateFullName(cust.getFirstNames()).equalsIgnoreCase("Error 2")) {
                        JOptionPane.showMessageDialog(null, "Los caracteres del nombre deben ser unicamente letras",
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                    } else JOptionPane.showMessageDialog(null,
                                                         "Nombre excede el limite o es inferior al minimo de " + "caracteres",
                                                         "Error", JOptionPane.ERROR_MESSAGE);
                } else if (cust.validateFullName(cust.getLastNames()).equalsIgnoreCase("Error 2")) {
                    JOptionPane.showMessageDialog(null, "Los caracteres  del apellido deben ser unicamente letras",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                } else JOptionPane.showMessageDialog(null,
                                                     "Apellido excede el limite o es inferior al minimo " + "caracteres",
                                                     "Error", JOptionPane.ERROR_MESSAGE);
            } else if (cust.validateDocNumber(cust.getDocNumber(), cust.getDocType()).equalsIgnoreCase("Error 3")) {
                JOptionPane.showMessageDialog(null, "El numero de documento no es valido porque contiene letras",
                                              "Error", JOptionPane.ERROR_MESSAGE);
            } else JOptionPane.showMessageDialog(null, "El documento no es valido", "Error", JOptionPane.ERROR_MESSAGE);

        }
        throw new DoctypeInvalidException(" ");
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady   = 10;
        gbc.ipadx   = 60;
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
        // initialize all components
        lblTitle = new JLabel("Actualizar Cliente existente");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setHorizontalAlignment(JLabel.LEFT);
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
        lblRequired = new JLabel("* Todos los datos se deben completar o no se actualizara el cliente");
        lblRequired.setText("<html><p style='color:red;'>* Todos los datos se deben completar o no se actualizara el cliente</p></html>");


        txtNames = new JTextField();
        txtLastNames = new JTextField();
        txtDocument = new JTextField();
        datePicker = new DatePicker();
        cmbTypeDoc = new JComboBox<>();

        btnSave = new JButton("Actualizar");
        btnSave.addActionListener(aListener);
        btnSave.setActionCommand("updated");
        initCmbBox();
    }

    private void initCmbBox() {
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(
                Arrays.stream(DocType.values()).map(DocType::getDocType).toArray(String[]::new));
        cmbTypeDoc.setModel(model);
    }
}
