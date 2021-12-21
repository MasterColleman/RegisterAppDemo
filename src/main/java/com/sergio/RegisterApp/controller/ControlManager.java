package com.sergio.RegisterApp.controller;

import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;
import com.sergio.RegisterApp.model.RegisterManager;
import com.sergio.RegisterApp.views.PrincipalWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;


/**
 * ControlManager
 */
public class ControlManager implements KeyListener {
    private RegisterManager registerManager;
    private PrincipalWindow principalWindow;

    public ControlManager() {
        this.principalWindow = new PrincipalWindow(this);
        this.registerManager = new RegisterManager();
        registerManager.addCustomer(new Customer("David", "Rodriguez", DocType.citizenshipCard, "100235482",
                                                 LocalDate.parse("2008-10-10")));
        registerManager.addCustomer(new Customer("Cristian", "Sanchez", DocType.citizenshipCard, "100245645",
                                                 LocalDate.parse("2008-10-10")));
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
}
