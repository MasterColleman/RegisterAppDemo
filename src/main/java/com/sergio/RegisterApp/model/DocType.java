package com.sergio.RegisterApp.model;

public enum DocType {

  citizenshipCard("CC"),
  foreignerID("CE"),
  numberOfPersonalIdentification("NIP"),
  taxIdentificationNumber("NIT"),
  identityCard("TI"),
  passport("PAP"),
  ;

  private final String documentType;

  DocType(String documentType) {
    this.documentType = documentType;
  }

  public String getDocType() {
    return documentType;
  }

}
