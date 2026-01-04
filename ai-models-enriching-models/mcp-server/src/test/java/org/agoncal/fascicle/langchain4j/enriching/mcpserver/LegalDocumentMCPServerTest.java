package org.agoncal.fascicle.langchain4j.enriching.mcpserver;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class LegalDocumentMCPServerTest {

  @Inject
  LegalDocumentMCPServer mcpServer;

  @Test
  void shouldInjectMcpServer() {
    assertNotNull(mcpServer);
  }
}
