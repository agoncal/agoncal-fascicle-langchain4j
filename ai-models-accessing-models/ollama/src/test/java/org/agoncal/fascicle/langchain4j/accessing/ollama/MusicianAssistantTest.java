package org.agoncal.fascicle.langchain4j.accessing.ollama;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("Requires local Ollama server running on localhost:11434")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseOllamaLanguageModel() {
    Response<String> completion = assistant.useOllamaLanguageModel();
    assertNotNull(completion);
    assertNotNull(completion.content());
  }

  @Test
  void shouldUseOllamaLanguageModelPrompt() {
    Response<String> completion = assistant.useOllamaLanguageModelPrompt();
    assertNotNull(completion);
    assertNotNull(completion.content());
  }

  @Test
  void shouldUseOllamaLanguageModelBuilder() {
    Response<String> completion = assistant.useOllamaLanguageModelBuilder();
    assertNotNull(completion);
    assertNotNull(completion.content());
  }

  @Test
  void shouldUseOllamaChatModel() {
    String completion = assistant.useOllamaChatModel();
    assertNotNull(completion);
  }

  @Test
  void shouldUseOllamaChatModelBuilder() {
    String completion = assistant.useOllamaChatModelBuilder();
    assertNotNull(completion);
  }

  @Test
  void shouldUseOllamaChatModelAiMessage() {
    ChatResponse completion = assistant.useOllamaChatModelAiMessage();
    assertNotNull(completion);
    assertNotNull(completion.aiMessage());
  }
}
