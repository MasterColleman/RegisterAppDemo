package com.sergio.RegisterApp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    if (!existID(docNumber, docType)) {
      listCustomers.add(customer);
      fileController.writeFile(customer);
    } else {
      throw new CustomerIDAlreadyExistException("El Cliente ya existe en la Lista, por favor cambie el numero o tipo de documento");           //METER MENSAJE AQUI
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

  public boolean existID(String docNumber, DocType docType) {
    return listCustomers.stream().noneMatch(customer -> docNumber.equalsIgnoreCase(customer.getDocNumber())&& customer.getDocType() == docType);
  }

  /**
   * Buscar indice de Cliente
   * @apiNote
   * Busca el indice de un cliente en la lista de clientes, si existe y si la lista no es null;
   * a partir del nombre o apellido completo y exacto (como se encuentra en este registro).
   * @param firstOrLastName
   * Nombre o Apellido Completo del cliente.
   * @return
   * Retorna (-1) si la lista de cliente es null.
   * Lanza la excepcion CustomerNotFoundException si el cliente no es encontrado.
   * Retorna el indice del cliente si es encontrado.
   */
  public int searchIndexCustomer(String firstOrLastName) throws CustomerNotFoundException{
    int j = -1;
    if(listCustomers != null) {
      for (int i = 0; i < listCustomers.size(); i++) {
        if ((firstOrLastName.equalsIgnoreCase(listCustomers.get(i).getFirstNames())
                || (firstOrLastName.equalsIgnoreCase(listCustomers.get(i).getLastNames()))))
          j = i;
      }
      throw new CustomerNotFoundException("El cliente no fue encontrado por el nombre o apellido introducido. ");
    }
    return j;
  }

  /**
   * Buscar Cliente
   * @apiNote
   * Busca un cliente exacto y lo devuelve.
   * Primero comprueba si el indice del cliente existe con el metodo "Buscar Indice del Cliente"
   * a partir del nombre o apellido completo y exacto.
   * Analiza los casos de retorno del metodo "Buscar Indice del Cliente" y si el cliente existe retorna dicho cliente,
   * de lo contrario lanza la excepcion CustomerNotFoundException o la excepcion ListCustomersNotFoundException.
   * @param firstOrLastName
   * Nombre o Apellido Completo del cliente.
   * @return
   * Un cliente de tipo de objeto Customer.
   */
  public Customer searchCustomer(String firstOrLastName) throws CustomerNotFoundException, ListCustomersNotFoundException {                  //Tengo dudas aqui
    int x = searchIndexCustomer(firstOrLastName);
    Customer customer = null;
    switch(x) {
      case -1:
        throw new ListCustomersNotFoundException(" La lista de clientes no fue encontrada");
      default:
        customer = listCustomers.get(x);
    }
    return customer;
  }

  /**
   * Eliminar Cliente
   * @param docNumber
   * @param docType
   * @apiNote
   * Elimina un cliente de la lista
   */
  public void removeCustomer(String docNumber, DocType docType) throws CustomerNotFoundException{
    if(existID(docNumber, docType)) {
      IntStream.range(0, listCustomers.size()).filter(i -> (docNumber.equalsIgnoreCase(listCustomers.get(i).getDocNumber())
              && (docType == (listCustomers.get(i).getDocType())))).forEach(i -> listCustomers.remove(i));
    }
    else {
      throw new CustomerNotFoundException("Cliente no fue encontrado.");
    }
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
