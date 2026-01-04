package org.agoncal.fascicle.langchain4j.accessing.googlegeminiai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "GOOGLE_AI_GEMINI_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseGoogleAiGeminiChatModel() {
    String response = assistant.useGoogleAiGeminiChatModel();
    assertNotNull(response);
  }
}
