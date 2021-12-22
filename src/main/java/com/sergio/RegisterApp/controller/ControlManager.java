package com.sergio.RegisterApp.controller;

import com.sergio.RegisterApp.exceptions.CustomerIDAlreadyExistException;
import com.sergio.RegisterApp.exceptions.DoctypeInvalidException;
import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.RegisterManager;
import com.sergio.RegisterApp.views.PrincipalWindow;

import java.awt.event.*;
import java.io.IOException;

/**
 * ControlManager
 */
public class ControlManager implements KeyListener, MouseListener, ActionListener {
    private RegisterManager registerManager;
    private PrincipalWindow principalWindow;


    public ControlManager() throws IOException, CustomerIDAlreadyExistException {
        this.principalWindow = new PrincipalWindow(this, this, this);
        this.registerManager = new RegisterManager();
        // provisional
        loadCustomers();

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("add")) addCostumer();
        if (event.getActionCommand().equals("added")) addedCostumer();
        if (event.getActionCommand().equals("remove")) removeCustomer();
    }

    private void removeCustomer() {
        try {
            boolean confirm = principalWindow.showConfirmDialog();
            if (!confirm) return;
            registerManager.removeCustomer(principalWindow.getSelectedCustomerID(),
                                           principalWindow.getSelectedCustomerDocType());
            principalWindow.closeCustomerInfoFrame();
        } catch (Exception e) {
            System.err.println(e);
        }
        principalWindow.setCustomers(registerManager.getListCustomers());
    }

    private void loadCustomers() {
        try {
            registerManager.loadCustomers();
            principalWindow.setCustomers(registerManager.getListCustomers());
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void getCustomersFromKeywords() {
        String keywords = principalWindow.getKeywords();
        principalWindow.setCustomers(registerManager.filterList(keywords));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        getCustomersFromKeywords();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            try {
                sendCustomerToView(registerManager.searchCustomerByDoc(principalWindow.getSelectedCustomerID(),
                                                                       principalWindow.getSelectedCustomerDocType()));
            } catch (DoctypeInvalidException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendCustomerToView(Customer customer) {
        principalWindow.setCustomer(customer, this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    private void addedCostumer() {
        Customer customer = null;
        try {
            customer = principalWindow.getCustomer();
            registerManager.addCustomer(customer);
            principalWindow.showSuccessMessage("Customer added successfully");
            principalWindow.closeAddWindow();
            principalWindow.setCustomers(registerManager.getListCustomers());
        } catch (DoctypeInvalidException | CustomerIDAlreadyExistException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addCostumer() {
        principalWindow.openAddFrame();
    }
}
