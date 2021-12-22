package com.sergio.RegisterApp.controller;

import com.sergio.RegisterApp.exceptions.CustomerIDAlreadyExistException;
import com.sergio.RegisterApp.exceptions.CustomerNotFoundException;
import com.sergio.RegisterApp.exceptions.DoctypeInvalidException;
import com.sergio.RegisterApp.exceptions.ListCustomersNotFoundException;
import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.RegisterManager;
import com.sergio.RegisterApp.views.PrincipalWindow;

import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ControlManager
 */
public class ControlManager implements KeyListener, MouseListener, ActionListener {
    private RegisterManager registerManager;
    private PrincipalWindow principalWindow;


    public ControlManager() throws IOException, CustomerIDAlreadyExistException {
        this.principalWindow = new PrincipalWindow(this, this, this);
        this.registerManager = new RegisterManager();
        loadCustomers();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("add")) addCostumer();
        if (event.getActionCommand().equals("added")) addedCostumer();
        if (event.getActionCommand().equals("remove")) removeCustomer();
        if (event.getActionCommand().equals("update")) updateCustomer();
        if (event.getActionCommand().equals("updated")) updatedCostumer();
    }

    private void updatedCostumer() {
        Customer customer = null;
        try {
            Customer customerToUpdate = registerManager.searchCustomerByDoc(principalWindow.getSelectedCustomerID(),
                                                                            principalWindow.getSelectedCustomerDocType());
            customer = principalWindow.getUpdatedCustomer();
            registerManager.updateClient(customerToUpdate, customer.getFirstNames(), customer.getLastNames(),
                                         customer.getDocType(), customer.getDocNumber(),
                                         LocalDate.parse(customer.getBirthDate(),
                                                         DateTimeFormatter.ofPattern("yyyy/MM/dd")));

            principalWindow.showSuccessMessage("Cliente actualizado exitosamente");
            principalWindow.closeUpdateWindow();
            principalWindow.setCustomers(registerManager.getListCustomers());
        } catch (DoctypeInvalidException | ListCustomersNotFoundException | CustomerNotFoundException | IOException e) {
            principalWindow.showErrorMessage(e.getMessage());
        }
    }

    private void updateCustomer() {
        principalWindow.openUpdateFrame(this);
    }

    private void removeCustomer() {
        try {
            boolean confirm = principalWindow.showConfirmDialog();
            if (!confirm) return;
            registerManager.removeCustomer(principalWindow.getSelectedCustomerID(),
                                           principalWindow.getSelectedCustomerDocType());
            principalWindow.closeCustomerInfoFrame();
        } catch (Exception e) {
            principalWindow.showErrorMessage(e.getMessage());
        }
        principalWindow.setCustomers(registerManager.getListCustomers());
    }

    private void loadCustomers() {
        try {
            registerManager.loadCustomers();
            principalWindow.setCustomers(registerManager.getListCustomers());
        } catch (Exception e) {
            principalWindow.showErrorMessage(e.getMessage());
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
                principalWindow.showErrorMessage(ex.getMessage());
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
            principalWindow.showSuccessMessage("Cliente agregado exitosamente");
            principalWindow.closeAddWindow();
            principalWindow.setCustomers(registerManager.getListCustomers());
        } catch (DoctypeInvalidException | CustomerIDAlreadyExistException | IOException e) {
            principalWindow.showErrorMessage(e.getMessage());
        }
    }

    private void addCostumer() {
        principalWindow.openAddFrame();
    }
}
