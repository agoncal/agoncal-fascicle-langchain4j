package org.agoncal.fascicle.langchain4j.embedding.ollama;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Requires local Ollama server running on localhost:11434")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldTextToEmbeddingOllama() {
    Response<Embedding> embedding = assistant.textToEmbeddingOllama();
    assertNotNull(embedding);
    assertNotNull(embedding.content());
    assertTrue(embedding.content().dimension() > 0);
  }
}
