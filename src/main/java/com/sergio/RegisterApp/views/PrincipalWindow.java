package com.sergio.RegisterApp.views;

import com.sergio.RegisterApp.exceptions.DoctypeInvalidException;
import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
    private JLabel lblRegisterList;


    public PrincipalWindow(KeyListener kListener, MouseListener mListener, ActionListener aListener) {
        setTitle("Sergio Suárez - 201912254");
        setSize(630, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("register_Icon.ico")));
        setLocationRelativeTo(null);
        setResizable(false);
        this.setLayout(new GridBagLayout());
        initComponents(kListener, mListener, aListener);

        setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // cambiar color de la ventana
        this.getContentPane().setBackground(Color.decode("#dfe6f2"));

    }


    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 0, 15);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(lblSergio, gbc);
        gbc.insets = new Insets(50, 0, 5, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(lblRegisterList, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 20, 0, 20);
        add(txtSearch, gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.weightx = .001;
        add(btnSearch, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(20, 0, 20, 20);
        add(btnAdd, gbc);
        this.setIconImage(new ImageIcon(getClass().getResource("/register_Icon.ico")).getImage());
    }

    public String getKeywords() {
        return txtSearch.getText();
    }


    private void initComponents(KeyListener kListener, MouseListener mListener, ActionListener aListener) {
        lblSergio = new JLabel("REGISTER APP");
        lblSergio.setFont(new Font("Arial", Font.BOLD, 18));
        txtSearch = new JTextField();
        txtSearch.addKeyListener(kListener);
        btnSearch = new JLabel("Filtrar");
        lblRegisterList = new JLabel("Lista de Registro");
        lblRegisterList.setFont(new Font("Verdana", Font.PLAIN, 14));
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        btnAdd = new JButton("Registrar");
        btnAdd.addActionListener(aListener);
        btnAdd.setActionCommand("add");
        addFrame = new AddFrame(aListener);
        updateFrame = new UpdateFrame(aListener);
        posicionateComponents();
        initTable(mListener);
    }

    private void initTable(MouseListener mListener) {
        columnNames = new ArrayList<>(
                List.of(new String[]{"Nombres", "Apellidos", "No. Documento", "Tipo ID", "Fecha Nac."}));
        tableModel = new DefaultTableModel(columnNames.toArray(), 0);
        table.setModel(tableModel);
        table.addMouseListener(mListener);
        table.setDefaultEditor(Object.class, null);
        table.setBackground(Color.WHITE);

        // cambiar color de la tabla
        scrollPane.getViewport().setBackground(Color.WHITE);
    }


    public void setCustomer(Customer customer, ActionListener aListener) {
        customerInfoFrame = new CustomerInfoFrame(customer, aListener);
    }

    public boolean showConfirmDialog() {
        Object[] options = {"Si", "Cancelar"};
        return (JOptionPane.showOptionDialog(null, "¿Desea eliminar este Cliente?", "Advertencia!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
                options[0]) == 0);
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
        JOptionPane.showMessageDialog(this, message, "Operacion Efectuada con Exito", JOptionPane.INFORMATION_MESSAGE);
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
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int x = 0; x < table.getColumnCount(); x++) {
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        }
        //center values in table
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < table.getColumnCount(); x++) {
            table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer2);

        }
        table.getColumnModel().getColumn(0).setMaxWidth(150);
        table.getColumnModel().getColumn(1).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setMaxWidth(132);
        table.getColumnModel().getColumn(3).setMaxWidth(60);
        table.getColumnModel().getColumn(4).setMaxWidth(80);
    }

    public void openUpdateFrame(ActionListener aListener) {
        updateFrame = new UpdateFrame(aListener);
        updateFrame.setVisible(true);
    }

    public Customer getUpdatedCustomer() throws DoctypeInvalidException {
        return updateFrame.updateCustomer();
    }

    public void closeUpdateWindow() {
        customerInfoFrame.dispose();
        updateFrame.dispose();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}