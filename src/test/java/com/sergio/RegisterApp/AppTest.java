package com.sergio.RegisterApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;
import com.sergio.RegisterApp.model.RegisterManager;
import com.sergio.RegisterApp.persistence.FileController;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
  /**
   * Rigorous Test :-)
   */
  public void fileControllerTest() {
    FileController fileController = new FileController();
    Customer customer = new Customer("David", "Rodriguez", DocType.citizenshipCard, "100235482", "08/12/2002");
    try {
      fileController.writeFile(customer);
      Customer anotherCustomer = fileController.readFile(customer.getDocNumber());
      assertEquals(customer.getDocNumber(), anotherCustomer.getDocNumber());
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  public void RegisterCustomerTest() {
    Customer customer = new Customer("David", "Rodriguez", DocType.citizenshipCard, "100235482", "08/12/2002");
    RegisterManager manager = new RegisterManager();
    try {
      manager.addCustomer(customer);
      manager.getListCustomers().stream().forEach(x -> System.out.println(x.getFirstNames()));
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  @Test
  public void IsValidID() {
    RegisterManager manager = new RegisterManager();
    boolean res = manager.isValidID("123");
    assertTrue(res);
  }
}
