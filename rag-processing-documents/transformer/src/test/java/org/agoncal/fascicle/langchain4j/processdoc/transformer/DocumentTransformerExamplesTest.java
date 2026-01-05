package org.agoncal.fascicle.langchain4j.processdoc.transformer;

import dev.langchain4j.data.document.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DocumentTransformerExamplesTest {

  private DocumentTransformerExamples examples;

  @BeforeEach
  void setUp() {
    examples = new DocumentTransformerExamples();
  }

  @Test
  void shouldTransformWithTextDocumentParser() {
    Document document = examples.transformWithTextDocumentParser();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  void shouldTransformWithCSSSelector() {
    Document document = examples.transformWithCSSSelector();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  void shouldTransformWithTikaDocumentParser() {
    Document document = examples.transformWithTikaDocumentParser();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }
}
