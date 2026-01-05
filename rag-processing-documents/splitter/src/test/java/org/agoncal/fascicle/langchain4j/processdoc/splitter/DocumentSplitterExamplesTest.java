package org.agoncal.fascicle.langchain4j.processdoc.splitter;

import dev.langchain4j.data.segment.TextSegment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DocumentSplitterExamplesTest {

  private DocumentSplitterExamples examples;

  @BeforeEach
  void setUp() {
    examples = new DocumentSplitterExamples();
  }

  @Test
  void shouldSplitWithDocumentBySentenceSplitterLoad() {
    List<TextSegment> segments = examples.splitWithDocumentBySentenceSplitterLoad();
    assertNotNull(segments);
    assertFalse(segments.isEmpty());
  }

  @Test
  void shouldSplitWithDocumentBySentenceSplitter() {
    List<TextSegment> segments = examples.splitWithDocumentBySentenceSplitter();
    assertNotNull(segments);
    assertFalse(segments.isEmpty());
  }

  @Test
  void shouldSplitWithDocumentByLineSplitter() {
    List<TextSegment> segments = examples.splitWithDocumentByLineSplitter();
    assertNotNull(segments);
    assertFalse(segments.isEmpty());
  }

  @Test
  void shouldSplitWithDocumentByWordSplitter() {
    List<TextSegment> segments = examples.splitWithDocumentByWordSplitter();
    assertNotNull(segments);
    assertFalse(segments.isEmpty());
  }

  @Test
  void shouldSplitWithDocumentSplitters() {
    List<TextSegment> segments = examples.splitWithDocumentSplitters();
    assertNotNull(segments);
    assertFalse(segments.isEmpty());
  }
}
