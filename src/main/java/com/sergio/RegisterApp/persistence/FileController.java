package com.sergio.RegisterApp.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.sergio.RegisterApp.model.*;

public class FileController {

  private static final String PATH = "customers/";
  private static final String EXTENSION = ".customer";

  public void readFile() throws FileNotFoundException {
    File f = new File(PATH);
    Scanner scanner = new Scanner(f);
    System.out.println("====> Start File");
    while (scanner.hasNextLine()) {
      String data = scanner.nextLine();
      System.out.println(data);
    }
    System.out.println("====> End File");
    scanner.close();
  }

  public void writeFile(Customer customer) throws IOException {
    FileWriter writer = new FileWriter(PATH + customer.getDocNumber() + EXTENSION);
    String jsonCostumer = objectToJson(customer);
    writer.write(jsonCostumer);
    writer.close();
  }

  public String objectToJson(Customer customer) {
    return new Gson().toJson(customer);
  }

  public Customer jsonToObject(String json) {
    return new Gson().fromJson(json, Customer.class);
  }

}
