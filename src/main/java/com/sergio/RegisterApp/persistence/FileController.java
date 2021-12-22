package com.sergio.RegisterApp.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.sergio.RegisterApp.model.Customer;

public class FileController {

  private static final String PATH = "customers/";
  private static final String EXTENSION = ".customer";

  public Customer readFile(String fileName) throws FileNotFoundException {
    File file = new File(PATH + fileName + EXTENSION);
    Scanner scanner = new Scanner(file);
    StringBuilder jsonCustomer = new StringBuilder();
    while (scanner.hasNextLine()) {
      jsonCustomer.append(scanner.nextLine());
    }
    scanner.close();
    return jsonToObject(jsonCustomer.toString());
  }

  public void removeFile(String customerId, String customerDocType) {
    new File(PATH + customerId + customerDocType + EXTENSION).delete();
  }

  public void writeFile(Customer customer) throws IOException {
    FileWriter writer = new FileWriter(PATH + customer.getDocNumber() + customer.getDocType().toString() + EXTENSION);
    String jsonCostumer = objectToJson(customer);
    writer.write(jsonCostumer);
    writer.close();
  }

  public ArrayList<Customer> getAllCustomersSaved() throws FileNotFoundException {
    ArrayList<Customer> savedCustomers = new ArrayList<Customer>();
    String[] fileList = new File(PATH).list();
    for (String fullFileName : fileList) {
      if (!fullFileName.contains(EXTENSION))
        continue;
      String fileName = fullFileName.replace(EXTENSION, "");
      savedCustomers.add(readFile(fileName));
    }
    return savedCustomers;
  }

  public String objectToJson(Customer customer) {
    return new Gson().toJson(customer);
  }

  public Customer jsonToObject(String json) {
    return new Gson().fromJson(json, Customer.class);
  }

}
