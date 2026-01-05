package org.agoncal.fascicle.langchain4j.vectordb.elasticsearch;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Requires local Elasticsearch server running on localhost:9200")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseElasticsearchToStoreEmbeddings() {
    EmbeddingMatch<TextSegment> match = assistant.useElasticsearchToStoreEmbeddings();
    assertNotNull(match);
    assertNotNull(match.embedded());
    assertTrue(match.score() > 0);
  }
}
