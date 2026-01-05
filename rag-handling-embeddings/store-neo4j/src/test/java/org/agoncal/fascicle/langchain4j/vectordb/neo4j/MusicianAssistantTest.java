package org.agoncal.fascicle.langchain4j.vectordb.neo4j;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Requires local Neo4j server running on localhost:7474")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseNeo4jToStoreEmbeddings() {
    EmbeddingMatch<TextSegment> match = assistant.useNeo4jToStoreEmbeddings();
    assertNotNull(match);
    assertNotNull(match.embedded());
    assertTrue(match.score() > 0);
  }
}
