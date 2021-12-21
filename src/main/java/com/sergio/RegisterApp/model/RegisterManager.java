package com.sergio.RegisterApp.model;

import java.util.ArrayList;

/**
 * 
 * @author Sergio Suárez
 *
 */
public class RegisterManager {

  private ArrayList<Customer> listCustomers;

  public RegisterManager() {
    listCustomers = new ArrayList<>();
  }

  public ArrayList<Customer> getListCustomers() {
    return listCustomers;
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
  public void addCustomer(Customer customer)/* throws CostumerNotFoundException, CostumerIDAlreadyExistException */ {
    if (customer != null) {
      String docNumber = customer.getDocNumber();
      DocType docType = customer.getDocType();
      String iD = validateID(docNumber, docType);
      if (iD == "Inexistente")
        listCustomers.add(customer);
      else {
        /* throw new CostumerIDAlreadyExistException(); */
      }
    } else {
      /* throw new CostumerNotFoundException(); */
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
  private String validateID(String docNumber, DocType docType) {
    String validID = "Inexistente";
    if (listCustomers != null) {
      for (int i = 0; i < listCustomers.size(); i++) {
        if ((docNumber.equalsIgnoreCase(listCustomers.get(i).getDocNumber())
            && (docType == (listCustomers.get(i).getDocType()))))
          validID = "Existente";
      }
    }
    return validID;
  }

  /**
   * Buscar indice de Cliente
   * 
   * @apiNote
   *          Busca el indice de un cliente en la lista de clientes, si existe y
   *          si la lista no es null;
   *          a partir del nombre o apellido completo y exacto (como se encuentra
   *          en este registro).
   * @param firstOrLastName
   *                        Nombre o Apellido Completo del cliente.
   * @return
   *         Retorna (-1) si la lista de cliente es null.
   *         Retorna (-2) si el cliente no existe y lanza la excepcion
   *         CostumerNotFoundException.
   *         Retorna el indice del cliente si es encontrado.
   */
  public int searchIndexCustomer(String firstOrLastName) /* throws CostumerNotFoundException */ {
    int j = -1;
    if (listCustomers != null) {
      for (int i = 0; i < listCustomers.size(); i++) {
        if ((firstOrLastName.equalsIgnoreCase(listCustomers.get(i).getFirstNames())
            || (firstOrLastName.equalsIgnoreCase(listCustomers.get(i).getLastNames()))))
          j = i;
      }
      /* throw new CostumerNotFoundException(); */ // tengo dudas aqui
      j = -2;
    }
    return j;
  }

  /**
   * Buscar Cliente
   * 
   * @apiNote
   *          Busca un cliente exacto y lo devuelve.
   *          Primero comprueba si el indice del cliente existe con el metodo
   *          "Buscar Indice del Cliente"
   *          a partir del nombre o apellido completo y exacto.
   *          Analiza los casos de retorno del metodo "Buscar Indice del Cliente"
   *          y si el cliente existe retorna dicho cliente,
   *          de lo contrario lanza la excepcion CostumerNotFoundException o la
   *          excepcion ListCostumersNotFoundException.
   * @param firstOrLastName
   *                        Nombre o Apellido Completo del cliente.
   * @return
   *         Un cliente de tipo de objeto Costumer.
   * 
   */
  public Customer searchCustomer(String firstOrLastName)
  /*
   * throws CostumerNotFoundException,
   * ListCostumersNotFoundException
   */ { // Tengo dudas aqui
    int x = searchIndexCustomer(firstOrLastName);
    Customer customer = null;
    switch (x) {
      case -2:
        /* throw new CostumerNotFoundException(); */
        break;
      case -1:
        /* throw new ListCostumersNotFoundException(); */
        break;
      default:
        customer = listCustomers.get(x);
    }
    return customer;
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
    String iD = validateID(docNumber, docType);
    if (iD == "Existente") {
      for (int i = 0; i < listCustomers.size(); i++) {
        if ((docNumber.equalsIgnoreCase(listCustomers.get(i).getDocNumber())
            && (docType == (listCustomers.get(i).getDocType()))))
          listCustomers.remove(i);
      }
    } else {
      /* throw new CostumerNotFoundException(), ListCostumersNotFoundException(); */
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
      String birthDate)/*
                        * throws CostumerNotFoundException,
                        * ListCostumersNotFoundException
                        */ {
    costumer.setFirstNames(firstNames);
    costumer.setLastNames(lastNames);
    costumer.setDocNumber(docNumber);
    costumer.setDocType(docType);
    costumer.setBirthDate();
  }

  /**
   * Filtrar Lista
   * 
   * @param keywords
   *                 Palabras clave
   * @return
   *         Una lista filtrada de tipo ArrayList
   * @apiNote
   *          Este metodo solo se usa para filtrar la lista de clientes con
   *          palabras clave, para tener como resultado una lista mas
   *          peque¿a que solo posea los resultados que contienen plabras clave
   *          (como el inicio de un nombre o apellido, o el inicio
   *          de un numero de documento; respecto a dicho cliente).
   */
  public ArrayList<Customer> filterList(String keywords) {
    ArrayList<Customer> filteredList = new ArrayList<Customer>();
    for (Customer costumer : listCustomers) {
      if (costumer.getFirstNames().contains(keywords) || costumer.getLastNames().contains(keywords))
        filteredList.add(costumer);
      else if (costumer.getDocNumber().contains(keywords))
        filteredList.add(costumer);
    }
    return filteredList;
  }
}
