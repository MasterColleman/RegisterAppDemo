package com.sergio.RegisterApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;
import com.sergio.RegisterApp.persistence.FileController;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
  /**
   * Rigorous Test :-)
   */
  @Test
  public void shouldAnswerWithTrue() {
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
}
