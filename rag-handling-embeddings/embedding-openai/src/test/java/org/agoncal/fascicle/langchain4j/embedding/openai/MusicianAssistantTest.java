package org.agoncal.fascicle.langchain4j.embedding.openai;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldTextToEmbeddingOpenAI() {
    Response<Embedding> embedding = assistant.textToEmbeddingOpenAI();
    assertNotNull(embedding);
    assertNotNull(embedding.content());
    assertTrue(embedding.content().dimension() > 0);
  }

  @Test
  void shouldTextToEmbeddingOpenAILarge() {
    Response<Embedding> embedding = assistant.textToEmbeddingOpenAILarge();
    assertNotNull(embedding);
    assertNotNull(embedding.content());
    assertTrue(embedding.content().dimension() > 0);
  }

  @Test
  void shouldTextToEmbeddingOpenAISegment() {
    Response<Embedding> embedding = assistant.textToEmbeddingOpenAISegment();
    assertNotNull(embedding);
    assertNotNull(embedding.content());
    assertTrue(embedding.content().dimension() > 0);
  }
}
