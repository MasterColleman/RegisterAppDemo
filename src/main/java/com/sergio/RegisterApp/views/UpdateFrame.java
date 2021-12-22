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
    private JLabel lblAge;
    private JLabel lblRequired;

    private JTextField txtNames;
    private JTextField txtLastNames;
    private JTextField txtDocument;
    private DatePicker datePicker;
    private JTextField txtAge;
    private JComboBox<String> cmbTypeDoc;


    private JButton btnSave;

    public UpdateFrame(ActionListener aListener) {
        this.setTitle("Actualizar Registro");
        this.setSize(550, 550);
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

    public Customer getCustomer() throws DoctypeInvalidException {
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
            if (cust.validateDocNumber(cust.getDocNumber(), cust.getDocType()).equalsIgnoreCase("valido")) {
                if (cust.validateFullName(cust.getLastNames()).equalsIgnoreCase("valido")) {
                    if (cust.validateFullName(cust.getFirstNames()).equalsIgnoreCase("valido")) {
                        if (cust.validateLegalAge().equalsIgnoreCase("valida")) {

                            return cust;
                        } else {
                            JOptionPane.showMessageDialog(
                                    null, "Edad No Legal", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null, "Nombre excede los 30 caractere", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null, "Apellido excede los 30 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El documento no es valido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        throw new DoctypeInvalidException(" ");
    }

        private void posicionateComponents () {
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(5, 20, 50, 20);
            this.add(lblTitle, gbc);
            gbc.insets = new Insets(5, 20, 5, 20);

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
            gbc.insets = new Insets(20, 100, 5, 100);
            this.add(datePicker, gbc);
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 3;
            this.add(lblBirth, gbc);
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(20, 5, 5, 20);
            this.add(lblRequired, gbc);
            gbc.gridx = 2;
            gbc.gridwidth = 1;
            this.add(btnSave, gbc);
        }

        private void initComponents (ActionListener aListener){
            // initialize all components
            lblTitle = new JLabel("Actualizar Registro");
            lblTitle.setHorizontalAlignment(JLabel.CENTER);
            lblNames = new JLabel("*Nombres");
            lblNames.setHorizontalAlignment(JLabel.CENTER);
            lblLastNames = new JLabel("*Apellidos");
            lblLastNames.setHorizontalAlignment(JLabel.CENTER);
            typeDoc = new JLabel("*Tipo de Documento");
            typeDoc.setHorizontalAlignment(JLabel.CENTER);
            lblDocument = new JLabel("*Documento");
            lblDocument.setHorizontalAlignment(JLabel.CENTER);
            lblBirth = new JLabel("*Fecha de Nacimiento");
            lblBirth.setHorizontalAlignment(JLabel.CENTER);
            lblRequired = new JLabel("* Todos los datos se deben completar o no se actualizara el cliente");


            txtNames = new JTextField();
            txtLastNames = new JTextField();
            txtDocument = new JTextField();
            datePicker = new DatePicker();
            txtAge = new JTextField();
            cmbTypeDoc = new JComboBox<>();

            btnSave = new JButton("Actualizar");
            btnSave.addActionListener(aListener);
            btnSave.setActionCommand("updated");
            initCmbBox();
        }

        private void initCmbBox () {
            ComboBoxModel<String> model = new DefaultComboBoxModel<>(
                    Arrays.stream(DocType.values()).map(DocType::getDocType).toArray(String[]::new));
            cmbTypeDoc.setModel(model);
        }
    }
