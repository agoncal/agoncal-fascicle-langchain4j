package org.agoncal.fascicle.langchain4j.embedding.azureopenai;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariables;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnabledIfEnvironmentVariables({
  @EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+"),
  @EnabledIfEnvironmentVariable(named = "AZURE_OPENAI_ENDPOINT", matches = ".+")
})
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldTextToEmbeddingAzureOpenAI() {
    Response<Embedding> embedding = assistant.textToEmbeddingAzureOpenAI();
    assertNotNull(embedding);
    assertNotNull(embedding.content());
    assertTrue(embedding.content().dimension() > 0);
  }
}
