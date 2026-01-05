package org.agoncal.fascicle.langchain4j.embedding.allmini;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.output.Response;
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
  void shouldTextToEmbeddingAllMiniLmL6V2() {
    Response<Embedding> embedding = assistant.textToEmbeddingAllMiniLmL6V2();
    assertNotNull(embedding);
    assertNotNull(embedding.content());
    assertTrue(embedding.content().dimension() > 0);
  }

  @Test
  void shouldTextToEmbeddingBgeSmall() {
    Response<Embedding> embedding = assistant.textToEmbeddingBgeSmall();
    assertNotNull(embedding);
    assertNotNull(embedding.content());
    assertTrue(embedding.content().dimension() > 0);
  }

  @Test
  void shouldCosineSimilarityBetween() {
    double similarity = assistant.cosineSimilarityBetween();
    assertTrue(similarity > 0);
    assertTrue(similarity <= 1);
  }
}
