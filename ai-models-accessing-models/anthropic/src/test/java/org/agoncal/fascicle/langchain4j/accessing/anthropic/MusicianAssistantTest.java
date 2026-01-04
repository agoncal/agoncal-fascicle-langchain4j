package org.agoncal.fascicle.langchain4j.accessing.anthropic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "ANTHROPIC_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseAnthropicChatModel() {
    String response = assistant.useAnthropicChatModel();
    assertNotNull(response);
  }
}
