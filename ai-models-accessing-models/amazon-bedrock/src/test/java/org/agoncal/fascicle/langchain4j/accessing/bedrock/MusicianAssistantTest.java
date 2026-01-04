package org.agoncal.fascicle.langchain4j.accessing.bedrock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "AWS_ACCESS_KEY_ID", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseBedrockLanguageModelBuilder() {
    String completion = assistant.useBedrockLanguageModelBuilder();
    assertNotNull(completion);
  }

  @Test
  void shouldUseBedrockTitanChatModel() {
    String completion = assistant.useBedrockTitanChatModel();
    assertNotNull(completion);
  }
}
