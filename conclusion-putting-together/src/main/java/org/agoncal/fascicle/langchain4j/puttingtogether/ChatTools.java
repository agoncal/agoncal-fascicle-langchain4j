package org.agoncal.fascicle.langchain4j.puttingtogether;

import dev.langchain4j.agent.tool.Tool;

import java.time.LocalDate;

// tag::adocSnippet[]
public class ChatTools {

  @Tool("The last time the ACCEPTABLE USE POLICY document was updated")
  LocalDate lastUpdateAcceptableUsePolicy() {
    return LocalDate.of(2021, 10, 1);
  }

  @Tool("The last time the DISCLAIMER document was updated")
  LocalDate lastUpdateDisclaimer() {
    return LocalDate.of(2019, 5, 29);
  }

  @Tool("The last time the END USER LICENSE AGREEMENT document was updated")
  LocalDate lastUpdateEndOfUserLicenseAgreement() {
    return LocalDate.of(2012, 3, 9);
  }

  @Tool("The last time the PRIVACY document was updated")
  LocalDate lastUpdatePrivacy() {
    return LocalDate.of(2013, 3, 9);
  }

  @Tool("The last time the TERMS AND CONDITIONS document was updated")
  LocalDate lastUpdateTerms() {
    return LocalDate.of(2014, 6, 19);
  }
}
// end::adocSnippet[]
