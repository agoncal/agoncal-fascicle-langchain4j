package org.agoncal.fascicle.langchain4j.enriching.mcpserver;

import io.quarkiverse.mcp.server.Tool;

import java.time.LocalDate;

public class LegalDocumentMCPServer {

  @Tool(description = "Returns the last time the PRIVACY document was updated")
  LocalDate lastUpdatePrivacy() {
    return LocalDate.of(2013, 3, 9);
  }

  @Tool(description = "Returns the last time the TERMS AND CONDITIONS document was updated")
  LocalDate lastUpdateTerms() {
    return LocalDate.of(2014, 6, 19);
  }

  @Tool(description = "Returns the last time the END USER LICENSE AGREEMENT document was updated")
  LocalDate lastEndUserLicenseAgreement() {
    return LocalDate.of(2021, 11, 23);
  }
}
// end::adocSnippet[]
