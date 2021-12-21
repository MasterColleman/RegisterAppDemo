package com.sergio.RegisterApp.controller;

import com.sergio.RegisterApp.exceptions.CustomerIDAlreadyExistException;
import com.sergio.RegisterApp.exceptions.DoctypeInvalidException;
import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;
import com.sergio.RegisterApp.model.RegisterManager;
import com.sergio.RegisterApp.views.PrincipalWindow;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.EventListener;

/**
 * ControlManager
 */
public class ControlManager implements KeyListener, MouseListener {
    private RegisterManager registerManager;
    private PrincipalWindow principalWindow;

    public ControlManager() throws IOException, CustomerIDAlreadyExistException {
        this.principalWindow = new PrincipalWindow(this, this);
        this.registerManager = new RegisterManager();
        // provisional
        registerManager.addCustomer(new Customer("David", "Rodriguez", DocType.citizenshipCard, "100235482",
                                                 LocalDate.parse("2008-10-10")));
        registerManager.addCustomer(new Customer("Cristian", "Sanchez", DocType.citizenshipCard, "100245645",
                                                 LocalDate.parse("2008-10-10")));
        loadCustomers();

    }

    private void loadCustomers() {
        principalWindow.setCustomers(registerManager.getListCustomers());
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
//            try {
//                sendCustomerToView(registerManager.searchCustomer(principalWindow.getSelectedCustomerID(),
//                                                                  principalWindow.getSelectedCustomerDocType()));
//            } catch (DoctypeInvalidException ex) {
//                ex.printStackTrace();
//            }
        }
    }
    private void sendCustomerToView(Customer customer) {
        principalWindow.setCustomer(customer);
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
}
