package org.agoncal.fascicle.langchain4j.vectordb.duckdb;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseDuckDBToStoreEmbeddings() {
    EmbeddingMatch<TextSegment> match = assistant.useDuckDBToStoreEmbeddings();
    assertNotNull(match);
    assertNotNull(match.embedded());
    assertTrue(match.score() > 0);
  }
}
