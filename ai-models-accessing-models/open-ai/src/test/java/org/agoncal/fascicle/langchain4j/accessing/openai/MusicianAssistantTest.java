package org.agoncal.fascicle.langchain4j.accessing.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  // #############################
  // ### OPENAI LANGUAGE MODEL ###
  // #############################
  @Test
  void shouldUseLangChain4jInsteadSDK() {
    String answer = assistant.useLangChain4jInsteadSDK();

    assertNotNull(answer);
    assertTrue(answer.toLowerCase().contains("beatles") || answer.contains("1963") || answer.contains("Please"));
  }

  @Test
  void shouldUseOpenAiLanguageTypeOfModel() {
    Response<String> response = assistant.useOpenAiLanguageTypeOfModel();

    assertNotNull(response);
    assertNotNull(response.content());
    assertNotNull(response.finishReason());
    assertNotNull(response.tokenUsage());
  }

  @Test
  void shouldUseOpenAiLanguageModel() {
    Response<String> response = assistant.useOpenAiLanguageModel();

    assertNotNull(response);
    assertNotNull(response.content());
    assertNotNull(response.finishReason());
    assertNotNull(response.tokenUsage());
    assertTrue(response.tokenUsage().totalTokenCount() > 0);
  }

  @Test
  void shouldUseOpenAiLanguageModelPrompt() {
    Response<String> response = assistant.useOpenAiLanguageModelPrompt();

    assertNotNull(response);
    assertNotNull(response.content());
  }

  @Test
  void shouldUseOpenAiLanguageModelBuilder() {
    Response<String> response = assistant.useOpenAiLanguageModelBuilder();

    assertNotNull(response);
    assertNotNull(response.content());
  }

  // #########################
  // ### OPENAI CHAT MODEL ###
  // #########################
  @Test
  void shouldUseOpenAiChatTypeOfModel() {
    ChatResponse response = assistant.useOpenAiChatTypeOfModel();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
    assertTrue(response.aiMessage().text().toLowerCase().contains("beethoven"));
  }

  @Test
  void shouldUseOpenAiSimpleConf() {
    String content = assistant.useOpenAiSimpleConf();

    assertNotNull(content);
    assertTrue(content.length() > 0);
  }

  @Test
  void shouldUseOpenAiSimpleConf2() {
    String content = assistant.useOpenAiSimpleConf2();

    assertNotNull(content);
    assertTrue(content.length() > 0);
  }

  @Test
  void shouldUseOpenAiRichConf() {
    String completion = assistant.useOpenAiRichConf();

    assertNotNull(completion);
    assertTrue(completion.toLowerCase().contains("rolling stones") || completion.contains("1964"));
  }

  @Test
  void shouldUseOpenAiChatRequest() {
    ChatResponse response = assistant.useOpenAiChatRequest();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
  }

  @Test
  void shouldUseOpenAiChatRequestDefault() {
    ChatResponse response = assistant.useOpenAiChatRequestDefault();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
  }

  @Test
  void shouldUseOpenAiResponseString() {
    String response = assistant.useOpenAiResponseString();

    assertNotNull(response);
    assertTrue(response.length() > 0);
  }

  @Test
  void shouldUseOpenAiChatResponse() {
    ChatResponse response = assistant.useOpenAiChatResponse();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
    assertNotNull(response.id());
    assertNotNull(response.finishReason());
    assertNotNull(response.modelName());
  }

  @Test
  void shouldUseOpenAiChatModelTemperatureOne() {
    String completion = assistant.useOpenAiChatModelTemperatureOne();

    assertNotNull(completion);
    assertTrue(completion.toLowerCase().contains("beatles"));
  }

  @Test
  void shouldUseOpenAiChatModelTemperatureZero() {
    String completion = assistant.useOpenAiChatModelTemperatureZero();

    assertNotNull(completion);
    assertTrue(completion.toLowerCase().contains("beatles"));
  }

  @Test
  void shouldUseOpenAiChatModelAiMessage() {
    ChatResponse response = assistant.useOpenAiChatModelAiMessage();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
  }

  // ###################################
  // ### OPENAI STREAMING CHAT MODEL ###
  // ###################################
  @Test
  void shouldUseOpenAiStreamingChatTypeOfModel() {
    ChatResponse response = assistant.useOpenAiStreamingChatTypeOfModel();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
  }

  @Test
  void shouldUseOpenAiStreaming() {
    ChatResponse response = assistant.useOpenAiStreaming();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
  }

  // ###############################
  // ### OPENAI MODERATION MODEL ###
  // ###############################
  @Test
  void shouldUseOpenAiModerationTypeOfModel() {
    Response<Moderation> response = assistant.useOpenAiModerationTypeOfModel();

    assertNotNull(response);
    assertNotNull(response.content());
    assertTrue(response.content().flagged());
  }

  // ##################################
  // ### TYPED AND UNTYPED RESPONSE ###
  // ##################################
  @Test
  void shouldUseTypedUntypedResponseString() {
    String response = assistant.useTypedUntypedResponseString();

    assertNotNull(response);
    assertTrue(response.toLowerCase().contains("orwell") || response.toLowerCase().contains("george"));
  }

  @Test
  void shouldUseTypedUntypedResponseStringOneLine() {
    String response = assistant.useTypedUntypedResponseStringOneLine();

    assertNotNull(response);
  }

  @Test
  void shouldUseTypedUntypedResponseUserMessage() {
    ChatResponse response = assistant.useTypedUntypedResponseUserMessage();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
    assertNotNull(response.tokenUsage());
    assertNotNull(response.finishReason());
  }

  @Test
  void shouldUseTypedUntypedResponseUserMessageOneLine() {
    String response = assistant.useTypedUntypedResponseUserMessageOneLine();

    assertNotNull(response);
  }

  @Test
  void shouldUseTypedUntypedResponseEmbedding() {
    Response<Embedding> response = assistant.useTypedUntypedResponseEmbedding();

    assertNotNull(response);
    assertNotNull(response.content());
    assertTrue(response.content().dimension() > 0);
    assertNotNull(response.content().vectorAsList());
  }

  @Test
  void shouldUseJSONResponseFormat() throws JsonProcessingException {
    ChatResponse response = assistant.useJSONResponseFormat();

    assertNotNull(response);
    assertNotNull(response.aiMessage());
    String text = response.aiMessage().text();
    assertTrue(text.contains("name"));
  }

  @Test
  void shouldDontKnow() {
    SystemMessage message = assistant.dontKnow();

    assertNotNull(message);
    assertTrue(message.text().contains("Vintage Store"));
  }

  // ###################################
  // ### EXPENSIVE TESTS (DISABLED) ###
  // ###################################
  // These tests are disabled by default as they incur costs
  // Uncomment to run them manually

  // @Test
  // void shouldUseOpenAiImageTypeOfModel() {
  //   Response<Image> response = assistant.useOpenAiImageTypeOfModel();
  //   assertNotNull(response);
  //   assertNotNull(response.content());
  //   assertNotNull(response.content().url());
  // }

  // @Test
  // void shouldUseTypedUntypedResponseImage() {
  //   Response<Image> response = assistant.useTypedUntypedResponseImage();
  //   assertNotNull(response);
  //   assertNotNull(response.content());
  // }

  // @Test
  // @EnabledIfEnvironmentVariable(named = "DEEPSEEK_API_KEY", matches = ".+")
  // void shouldUseOpenAIForDeepSeek() {
  //   String answer = assistant.useOpenAIForDeepSeek();
  //   assertNotNull(answer);
  // }
}
