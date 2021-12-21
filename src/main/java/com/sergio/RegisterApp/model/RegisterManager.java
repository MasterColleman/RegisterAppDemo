package com.sergio.RegisterApp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.sergio.RegisterApp.exceptions.*;
import com.sergio.RegisterApp.persistence.FileController;

/**
 * 
 * @author Sergio Suarez
 *
 */
public class RegisterManager {

  private ArrayList<Customer> listCustomers;

  private FileController fileController;

  public RegisterManager() {
    listCustomers = new ArrayList<>();
    fileController = new FileController();
  }

  public ArrayList<Customer> getListCustomers() {
    return this.listCustomers;
  }

  public void setListCustomers(ArrayList<Customer> listCustomers) {
    this.listCustomers = listCustomers;
  }

  /**
   * Agregar Cliente
   * 
   * @param customer
   *                 Cliente tipo Costumer
   * @apiNote
   *          Se verifica si el cliente es nulo, si lo es lanza la excepcion
   *          CostumerNotFoundException.
   *          Si no es nulo, procede a la siguiente validacion a traves del metodo
   *          validarID donde: si el
   *          cliente no existe en la lista, lo agrega; si el cliente ya existe,
   *          lanza la excepcion
   *          CostumerIDAlreadyExistException
   */
  public void addCustomer(Customer customer) throws IOException, CustomerIDAlreadyExistException {
    String docNumber = customer.getDocNumber();
    DocType docType = customer.getDocType();
    if (isValidID(docNumber, docType)) {
      listCustomers.add(customer);
      fileController.writeFile(customer);
    } else {
      throw new CustomerIDAlreadyExistException(" ");           //METER MENSAJE AQUI
    }
  }

  /**
   * Validar Numero de Identificacion (ID)
   * 
   * @apiNote
   *          Se valida tanto tipo de ID como numero de ID para saber si existe o
   *          no;
   *          el cliente, que se desea agregar a la lista de clientes.
   * @param docNumber
   *                  Numero de Documento
   * @param docType
   *                  Tipo de Documento
   * @return
   *         Inexistente si el ID del cliente no existe en la lista.
   *         Existente si el ID del cliente ya existe en la lista.
   */

  public boolean isValidID(String docNumber, DocType docType) {
    return listCustomers.stream().noneMatch(customer -> docNumber.equalsIgnoreCase(customer.getDocNumber())&& customer.getDocType() == docType);
  }

  /**
   * Eliminar Cliente
   * 
   * @param docNumber
   * @param docType
   * @apiNote
   *          Elimina un cliente en la lista
   */
  public void removeCustomer(String docNumber, DocType docType) /*
                                                                 * throws CostumerNotFoundException,
                                                                 * ListCostumersNotFoundException
                                                                 */ {
    /*
     * Dado que el ID respecto al tipo de ID es el unico que no se repite en toda la
     * lista, este es el que se
     * usa para buscar y eliminar el cliente con este metodo
     */
    // String iD = isValidID(docNumber);
    // if (iD == "Existente") {
    // for (int i = 0; i < listCustomers.size(); i++) {
    // if ((docNumber.equalsIgnoreCase(listCustomers.get(i).getDocNumber())
    // && (docType == (listCustomers.get(i).getDocType()))))
    // listCustomers.remove(i);
    // }
    // } else {
    // /* throw new CostumerNotFoundException(), ListCostumersNotFoundException();
    // */
    // }
  }

  /**
   * Actualizar Cliente
   * 
   * @param costumer
   * @param firstNames
   * @param lastNames
   * @param docType
   * @param docNumber
   * @param birthDate
   * @apiNote
   *          Este metodo recibe un cliente de tipo Costumer (existente en la
   *          lista) y le setea nuevamente todos los atributos al objeto, para
   *          actualizarlo.
   */
  public void updateClient(Customer costumer, String firstNames, String lastNames, DocType docType, String docNumber,
      LocalDate birthDate)/*
                        * throws CostumerNotFoundException,
                        * ListCostumersNotFoundException
                        */ {
    costumer.setFirstNames(firstNames);
    costumer.setLastNames(lastNames);
    costumer.setDocNumber(docNumber);
    costumer.setDocType(docType);
    costumer.setBirthDate(birthDate);
  }
//
  /**
   * Filtrar Lista
   * 
   * @param keywords
   *                 Un arraylist de palabras clave
   * @return
   *         Una lista filtrada de tipo ArrayList
   * @apiNote
   *          Este metodo solo se usa para filtrar la lista de clientes con
   *          palabras clave, para tener como resultado una lista mas
   *          peque�a que solo posea los resultados que contienen plabras clave
   *          (como el inicio de un nombre o apellido, o el inicio
   *          de un numero de documento; respecto a dicho cliente).
   */
  public List<Customer> filterList(String keywords) {
    return filterCustomerByKeyword(keywords);
  }

  public List<Customer> filterCustomerByKeyword(String keyword) {
    return listCustomers.parallelStream()
        .filter(
            customer -> customer.getFirstNames().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))
                || customer.getLastNames().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))
                || customer.getDocNumber().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)))
        .collect(Collectors.toList());
  }
}
