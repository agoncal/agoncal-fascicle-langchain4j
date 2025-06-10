package org.agoncal.fascicle.langchain4j.enriching.mcpserver;

import java.time.LocalDate;

// tag::adocSnippet[]
import io.quarkiverse.mcp.server.McpLog;
import io.quarkiverse.mcp.server.Tool;

public class LegalDocumentMCPServer {

  @Tool(name = "last_update_privacy",
    description = "Returns the last time the PRIVACY document was updated")
  LocalDate lastUpdatePrivacy(McpLog log) {
    log.info("lastUpdatePrivacy");
    return LocalDate.of(2013, 3, 9);
  }

  @Tool(name = "last_update_terms", description = "Returns the last time the TERMS AND CONDITIONS document was updated")
  LocalDate lastUpdateTerms(McpLog log) {
    log.info("lastUpdateTerms");
    return LocalDate.of(2014, 6, 19);
  }

  @Tool(name = "last_update_license_agreement", description = "Returns the last time the END USER LICENSE AGREEMENT document was updated")
  LocalDate lastEndUserLicenseAgreement(McpLog log) {
    log.info("lastEndUserLicenseAgreement");
    return LocalDate.of(2021, 11, 23);
  }
}
// end::adocSnippet[]
