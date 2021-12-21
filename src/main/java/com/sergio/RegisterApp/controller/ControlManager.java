package com.sergio.RegisterApp.controller;

import com.sergio.RegisterApp.exceptions.CustomerIDAlreadyExistException;
import com.sergio.RegisterApp.model.RegisterManager;
import com.sergio.RegisterApp.views.PrincipalWindow;

import java.awt.event.*;
import java.io.IOException;

/**
 * ControlManager
 */
public class ControlManager implements KeyListener, MouseListener {

  private RegisterManager registerManager;
  private PrincipalWindow principalWindow;

  public ControlManager() throws IOException, CustomerIDAlreadyExistException {
    this.principalWindow = new PrincipalWindow(this);
    this.registerManager = new RegisterManager();
    // provisional
    loadCustomers();

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
