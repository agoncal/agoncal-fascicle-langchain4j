package org.agoncal.fascicle.langchain4j.vectordb.azureaisearch;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariables;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnabledIfEnvironmentVariables({
  @EnabledIfEnvironmentVariable(named = "AZURE_SEARCH_ENDPOINT", matches = ".+"),
  @EnabledIfEnvironmentVariable(named = "AZURE_SEARCH_KEY", matches = ".+")
})
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseAzureAiSearchToStoreEmbeddings() {
    EmbeddingMatch<TextSegment> match = assistant.useAzureAiSearchToStoreEmbeddings();
    assertNotNull(match);
    assertNotNull(match.embedded());
    assertTrue(match.score() > 0);
  }
}
