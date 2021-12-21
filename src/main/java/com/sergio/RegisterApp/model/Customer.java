/**
 * 
 */
package com.sergio.RegisterApp.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
//import javax.swing.DatePicker;

/**
 * @author Sergio Suárez
 *
 */
public class Customer {

  private String firstNames;
  private String lastNames;
  private DocType docType;
  private String docNumber;
  private String birthDate;
  // private DatePicker datepicker;

  public Customer(String firstNames, String lastNames, DocType docType, String docNumber, LocalDate birthDate) {
    super();
    this.firstNames = firstNames;
    this.lastNames = lastNames;
    this.docType = docType;
    this.docNumber = docNumber;
    this.birthDate = birthDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
  }

  public String getFirstNames() {
    return firstNames;
  }

  public void setFirstNames(String firstNames) {
    this.firstNames = firstNames;
  }

  public String getLastNames() {
    return lastNames;
  }

  public void setLastNames(String lastNames) {
    this.lastNames = lastNames;
  }

  public DocType getDocType() {
    return docType;
  }

  public void setDocType(DocType docType) {
    this.docType = docType;
  }

  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    // Usar la App de calendario LGoodDatePicker para escojer la fecha y usarla aqui
    // para extraer la fecha
    String birth = birthDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    /*
     * Con metodos propios de la libreria se extraen los espacios de la fecha, se
     * guardan en enteros
     * y posteriormente en un string que se guarda en el set de fecha de nacimiento
     * del cliente
     * int day = birthDate.getDayOfMonth();
     * int month = birthDate.getMonth();
     * int year = birthDate.getYear();
     * birth = year + "-" + month + "-" + day;
     */
    this.birthDate = birth;
  }

  /**
   * Validar Nombre Completo
   * 
   * @param firstOrLastName
   *                        Cadena que puede ser nombre o apellido (completos).
   * @apiNote
   *          Aqui se comprueba si el nombre o apellido son nulos (solo uno
   *          de los dos ya que este metodo se usa para validar ambos campos
   *          en la lista), exceden o no superan el tamaño maximo o minimo
   *          de caracteres respectivamente ("Error1"). Y si ademas la cadena
   *          de caracteres a comprobar posee alguna clase de caracter que
   *          no sea una letra ("Error2"). Lo anterior invalidara el espacio de
   *          entrada del usuario lanzando
   *          el tipo de Error. De lo contrario lanzara un String que dice
   *          "Valido".
   * @return
   *         Valido, Error 1 o Error 2. Dependiendo de la validacion resultante de
   *         la cadena.
   **/
  public String validateFullName(String firstOrLastName) {
    int maxChars = 30, minChars = 3;
    String text, name = "Valido";
    char c = 0;
    if ((firstOrLastName != null) || (firstOrLastName.length() <= maxChars)
        || firstOrLastName.length() >= minChars) {
      text = firstOrLastName.toUpperCase();
      for (int i = 0; i < text.length(); i++) {
        c = text.charAt(i);
        if (!((c >= 'A' && c <= 'Z') || c == ' ')) {
          name = "Error 2";
        }
      }
    } else {
      name = "Error 1";
    }
    return name;
  }

  /**
   * Validar Numero de Documento
   * 
   * @param docTypeNumber, docType
   *                       docTypeNumber = numero de documento
   *                       docType = tipo de documento
   * @apiNote
   *          Aqui se comprueba si numero de documento es valido:
   *          Si no excede o no supera el tamaño maximo o minimo
   *          de caracteres respectivamente ("Error1").
   *          Si solo posee numeros y no letras, a menos que sea tipo
   *          de documento "pasaporte"("Error3").
   *          Lo anterior invalidara el espacio de entrada del usuario
   *          si las condiciones no se cumplen, lanzando el tipo de Error.
   *          De lo contrario lanzara un String que dice "Valido".
   * @return
   *         Valido, Error 1 o Error 3. Dependiendo de la validacion resultante de
   *         la cadena.
   **/
  public String validateDocNumber(String docTypeNumber, DocType docType) {
    int maxChars = 20, minChars = 8;
    String number = "Valido";
    char c = 0;
    if ((docTypeNumber != null) || (docTypeNumber.length() <= maxChars)
        || docTypeNumber.length() >= minChars) {
      if (!docType.equals("PAP")) {
        for (int i = 0; i < docTypeNumber.length(); i++) {
          c = docTypeNumber.charAt(i);
          if (!((c >= '0' && c <= '9'))) {
            number = "Error 3";
          }
        }
      }
    } else {
      number = "Error 1";
    }
    return number;
  }

  /**
   * Obtener Edad
   * 
   * @apiNote
   *          Este metodo toma el objeto fecha dado por la libreria
   *          LGoodDatePicker
   *          lo convierte y formatea en un objeto LocalDate y posteriormente
   *          calcula la cantidad de años (edad) entre la fecha ingresada y la
   *          fecha
   *          actual
   * 
   **/
  private int age(String birthDateIn) {
    String date = "1999-10-03"; // aqui seria String date = this.getBirthDay();
    LocalDate today = LocalDate.now();
    LocalDate birth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault()));
    Period age = birth.until(today);// Aqui se calcularia la edad
    return age.getYears();
    /*
     * System.out.printf("%s %d %s %d %s %d %s\n",
     * "Tengo",
     * age.getYears(),"años,",age.getMonths(),"meses,",age.getDays(),"días");
     */
  }

  public String validateLegalAge() {
    String legalAge = "Valida";
    if (this.age(getBirthDate()) < 18) {
      legalAge = "Invalida";
    }
    return legalAge;
  }

}
