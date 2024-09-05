package org.agoncal.fascicle.langchain4j.extending.tools;

import dev.langchain4j.agent.tool.Tool;

import java.time.LocalDate;

// tag::adocSnippet[]
public class LegalDocumentTools {

  @Tool("The last time the PRIVACY document was updated")
  public LocalDate lastUpdatePrivacy() {
    return LocalDate.of(2013, 3, 9);
  }

  @Tool("The last time the TERMS AND CONDITIONS document was updated")
  public LocalDate lastUpdateTerms() {
    return LocalDate.of(2014, 6, 19);
  }

  @Tool("The last time the END USER LICENSE AGREEMENT document was updated")
  public LocalDate lastEndUserLicenseAgreement() {
    return LocalDate.of(2021, 11, 23);
  }
}
// end::adocSnippet[]
