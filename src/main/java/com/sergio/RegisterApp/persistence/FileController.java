package com.sergio.RegisterApp.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.sergio.RegisterApp.model.*;

public class FileController {

	private final String path = "./assets/example.txt";

	public void readFile() throws FileNotFoundException {
		File f = new File(path);
		Scanner scanner = new Scanner(f);
	    System.out.println("====> Start File");
	    while (scanner.hasNextLine()) {
	    	String data = scanner.nextLine();
	    	System.out.println(data);
	    }
	    System.out.println("====> End File");
	    scanner.close();
	}

	public void writeFile() throws IOException {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Digite el texto:");
	    String text = scanner.nextLine();
	    FileWriter myWriter = new FileWriter(path);
	    System.out.println("texto guardado correctamente");
	    myWriter.write(text);
	    myWriter.close();
	    scanner.close();
	}
	
	public String objectToJson(Costumer person) {
        Gson gson = new Gson();
        return gson.toJson(person);
    }

    public Costumer jsonToObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Costumer.class);
    }
	
}
