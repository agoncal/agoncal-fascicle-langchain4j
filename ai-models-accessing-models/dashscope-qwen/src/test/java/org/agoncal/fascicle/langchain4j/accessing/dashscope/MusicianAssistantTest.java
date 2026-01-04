package org.agoncal.fascicle.langchain4j.accessing.dashscope;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "DASHSCOPE_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseQwenChatModelBuilder() {
    String completion = assistant.useQwenChatModelBuilder();
    assertNotNull(completion);
  }
}
