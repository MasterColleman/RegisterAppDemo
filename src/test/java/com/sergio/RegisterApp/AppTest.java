package com.sergio.RegisterApp;

import com.sergio.RegisterApp.model.Customer;
import com.sergio.RegisterApp.model.DocType;
import com.sergio.RegisterApp.persistence.FileController;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

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
        Customer customer = new Customer(
                "David", "Rodriguez", DocType.citizenshipCard, "100235482",
                LocalDate.parse("2008-10-10"));
        try {
            fileController.writeFile(customer);
            Customer anotherCustomer = fileController.readFile(customer.getDocNumber());
            assertEquals(customer.getDocNumber(), anotherCustomer.getDocNumber());
        } catch (Exception e) {
            fail();
        }
    }
}
