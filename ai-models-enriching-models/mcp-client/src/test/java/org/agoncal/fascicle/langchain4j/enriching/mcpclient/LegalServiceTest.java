package org.agoncal.fascicle.langchain4j.enriching.mcpclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("Requires MCP server JAR to be built and available")
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class LegalServiceTest {

  private LegalService service;

  @BeforeEach
  void setUp() {
    service = new LegalService();
  }

  @Test
  void shouldAskAboutPrivacyDocument() {
    String mcpServerPath = "~/legal-documents-mcp-server.jar";
    String response = service.askLegalQuestion(mcpServerPath, "When was the PRIVACY document updated?");
    assertNotNull(response);
  }
}
