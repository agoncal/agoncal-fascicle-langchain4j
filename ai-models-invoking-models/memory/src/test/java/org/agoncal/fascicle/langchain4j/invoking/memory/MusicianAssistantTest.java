package org.agoncal.fascicle.langchain4j.invoking.memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseNoMemory() throws InterruptedException {
    String response = assistant.useNoMemory();
    assertNotNull(response);
  }

  @Test
  void shouldSendOneMessage() {
    String response = assistant.sendingOneMessage();
    assertNotNull(response);
  }

  @Test
  void shouldSendTwoMessages() {
    String response = assistant.sendingTwoMessages();
    assertNotNull(response);
  }

  @Test
  void shouldSendThreeMessages() {
    String response = assistant.sendingThreeMessages();
    assertNotNull(response);
  }

  @Test
  void shouldUseChatMemory() throws InterruptedException {
    String response = assistant.useChatMemory();
    assertNotNull(response);
  }

  @Test
  void shouldUseChatMemoryWithSystemMessage() throws InterruptedException {
    String response = assistant.useChatMemoryWithSystemMessage();
    assertNotNull(response);
  }
}
