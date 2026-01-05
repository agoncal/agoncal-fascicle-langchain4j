package org.agoncal.fascicle.langchain4j.vectordb.qdrant;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Requires local Qdrant server running on localhost:6334")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseQdrantToStoreEmbeddingsConnect() {
    EmbeddingMatch<TextSegment> match = assistant.useQdrantToStoreEmbeddingsConnect();
    assertNotNull(match);
    assertNotNull(match.embedded());
    assertTrue(match.score() > 0);
  }

  @Test
  void shouldUseQdrantToStoreEmbeddings() {
    EmbeddingMatch<TextSegment> match = assistant.useQdrantToStoreEmbeddings();
    assertNotNull(match);
    assertNotNull(match.embedded());
    assertTrue(match.score() > 0);
  }

  @Test
  void shouldUseInMemoryToQuery() {
    EmbeddingSearchResult<TextSegment> result = assistant.useInMemoryToQuery();
    assertNotNull(result);
    assertFalse(result.matches().isEmpty());
  }
}
