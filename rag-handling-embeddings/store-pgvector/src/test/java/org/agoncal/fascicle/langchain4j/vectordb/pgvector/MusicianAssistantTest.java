package org.agoncal.fascicle.langchain4j.vectordb.pgvector;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Requires local PostgreSQL server with pgvector extension running on localhost:5432")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUsePGVectorToStoreEmbeddings() {
    EmbeddingMatch<TextSegment> match = assistant.usePGVectorToStoreEmbeddings();
    assertNotNull(match);
    assertNotNull(match.embedded());
    assertTrue(match.score() > 0);
  }
}
