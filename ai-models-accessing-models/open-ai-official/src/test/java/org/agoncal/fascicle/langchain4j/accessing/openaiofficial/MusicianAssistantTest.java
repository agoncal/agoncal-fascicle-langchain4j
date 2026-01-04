package org.agoncal.fascicle.langchain4j.accessing.openaiofficial;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
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
  void shouldUseOpenAIOfficial() {
    String answer = assistant.useOpenAIOfficial();
    assertNotNull(answer);
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "DEEPSEEK_API_KEY", matches = ".+")
  void shouldUseOpenAIForDeepSeek() {
    String answer = assistant.useOpenAIForDeepSeek();
    assertNotNull(answer);
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "AZURE_OPENAI_KEY", matches = ".+")
  void shouldUseOpenAIForFoundryChat() {
    String answer = assistant.useOpenAIForFoundryChat();
    assertNotNull(answer);
  }

  // Image generation test disabled by default (expensive)
  // @Test
  // @EnabledIfEnvironmentVariable(named = "AZURE_OPENAI_KEY", matches = ".+")
  // void shouldUseOpenAIForFoundryImage() {
  //   Response<Image> answer = assistant.useOpenAIForFoundryImage();
  //   assertNotNull(answer);
  //   assertNotNull(answer.content());
  // }
}
