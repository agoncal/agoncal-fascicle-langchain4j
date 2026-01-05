package org.agoncal.fascicle.langchain4j.processdoc.parser;

import dev.langchain4j.data.document.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DocumentParserExamplesTest {

  private DocumentParserExamples examples;

  @BeforeEach
  void setUp() {
    examples = new DocumentParserExamples();
  }

  @Test
  void shouldParseWithTextDocumentParser() {
    Document document = examples.parseWithTextDocumentParser();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  void shouldParseWithPDFBox() {
    Document document = examples.parseWithPDFBox();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  @Disabled("Dependency conflict between Apache Tika and PDFBox versions")
  void shouldParseWithTika() {
    Document document = examples.parseWithTika();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  void shouldParseWithPoi() {
    Document document = examples.parseWithPoi();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }
}
