package org.agoncal.fascicle.langchain4j.processdoc.loader;

import dev.langchain4j.data.document.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DocumentLoaderExamplesTest {

  private DocumentLoaderExamples examples;

  @BeforeEach
  void setUp() {
    examples = new DocumentLoaderExamples();
  }

  @Test
  void shouldLoadFromDocumentLoaderFile() {
    Document document = examples.loadFromDocumentLoaderFile();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  void shouldLoadFromDocumentLoaderFileNoParser() {
    Document document = examples.loadFromDocumentLoaderFileNoParser();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  void shouldLoadFromFileSystemDocumentLoader() {
    Document document = examples.loadFromFileSystemDocumentLoader();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  @Disabled("Requires network access")
  void shouldLoadFromDocumentLoaderURL() throws Exception {
    Document document = examples.loadFromDocumentLoaderURL();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  @Disabled("Requires network access")
  void shouldLoadFromUrlDocumentLoader() throws Exception {
    Document document = examples.loadFromUrlDocumentLoader();
    assertNotNull(document);
    assertNotNull(document.text());
    assertFalse(document.text().isEmpty());
  }

  @Test
  @Disabled("Requires Azure Blob Storage configuration")
  void shouldLoadFromDocumentLoaderAzure() {
    Document document = examples.loadFromDocumentLoaderAzure();
    assertNotNull(document);
  }

  @Test
  @Disabled("Requires Azure Blob Storage configuration")
  void shouldLoadFromAzureDocumentLoader() throws Exception {
    Document document = examples.loadFromAzureDocumentLoader();
    assertNotNull(document);
  }
}
