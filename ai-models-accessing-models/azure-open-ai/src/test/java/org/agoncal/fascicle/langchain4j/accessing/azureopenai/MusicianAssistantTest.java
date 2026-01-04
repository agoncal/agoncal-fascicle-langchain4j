package org.agoncal.fascicle.langchain4j.accessing.azureopenai;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "AZURE_OPENAI_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseAzureOpenAiLanguageModelBuilder() {
    Response<String> completion = assistant.useAzureOpenAiLanguageModelBuilder();
    assertNotNull(completion);
    assertNotNull(completion.content());
  }

  @Test
  void shouldUseAzureOpenAiLanguageModelPrompt() {
    Response<String> completion = assistant.useAzureOpenAiLanguageModelPrompt();
    assertNotNull(completion);
    assertNotNull(completion.content());
  }

  @Test
  void shouldUseAzureOpenAiChatModel() {
    String completion = assistant.useAzureOpenAiChatModel();
    assertNotNull(completion);
  }

  @Test
  void shouldUseAzureOpenAiChatModelBuilder() {
    String completion = assistant.useAzureOpenAiChatModelBuilder();
    assertNotNull(completion);
  }

  @Test
  void shouldUseAzureOpenAiChatModelSimple() {
    String completion = assistant.useAzureOpenAiChatModelSimple();
    assertNotNull(completion);
  }

  @Test
  void shouldUseAzureOpenAiChatModelAiMessage() {
    ChatResponse completion = assistant.useAzureOpenAiChatModelAiMessage();
    assertNotNull(completion);
    assertNotNull(completion.aiMessage());
  }

  @Test
  void shouldUseAzureOpenAiOllamaModel() {
    String response = assistant.useAzureOpenAiOllamaModel();
    assertNotNull(response);
  }
}
