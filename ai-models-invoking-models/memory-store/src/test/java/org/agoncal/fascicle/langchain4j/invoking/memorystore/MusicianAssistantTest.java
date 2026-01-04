package org.agoncal.fascicle.langchain4j.invoking.memorystore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("Requires local Redis server running on localhost:6379")
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseRedisChatMemoryStore() {
    String response = assistant.useRedisChatMemoryStore();
    assertNotNull(response);
  }

  @Test
  void shouldUseRedisChatMemoryStoreQuestion() {
    String response = assistant.useRedisChatMemoryStoreQuestion();
    assertNotNull(response);
  }
}
