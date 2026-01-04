package org.agoncal.fascicle.langchain4j.accessing.vertexai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "GCP_PROJECT_ID", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseVertexAiGeminiChatModel() {
    String completion = assistant.useVertexAiGeminiChatModel();
    assertNotNull(completion);
  }
}
