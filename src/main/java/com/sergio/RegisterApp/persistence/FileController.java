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

  public Customer readFile(String cutomerId) throws FileNotFoundException {
    File file = new File(PATH + cutomerId + EXTENSION);
    Scanner scanner = new Scanner(file);
    StringBuilder jsonCostumer = new StringBuilder();
    while (scanner.hasNextLine()) {
      jsonCostumer.append(scanner.nextLine());
    }
    scanner.close();
    return jsonToObject(jsonCostumer.toString());
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
