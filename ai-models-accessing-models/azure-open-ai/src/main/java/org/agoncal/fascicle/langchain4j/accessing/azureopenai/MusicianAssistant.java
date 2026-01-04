package org.agoncal.fascicle.langchain4j.accessing.azureopenai;

import com.azure.ai.openai.models.AzureChatEnhancementConfiguration;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.azure.AzureOpenAiLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;

import java.time.Duration;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private final String PROMPT = "When was the first Beatles album released?";

  // ###################################
  // ### AZURE OPENAI LANGUAGE MODEL ###
  // ###################################
  public Response<String> useAzureOpenAiLanguageModelBuilder() {
    System.out.println("### useAzureOpenAiLanguageModelBuilder");

    AzureOpenAiLanguageModel model = AzureOpenAiLanguageModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.3)
      .logRequestsAndResponses(true)
      .build();

    Response<String> completion = model.generate(PROMPT);

    System.out.println(completion.content());
    System.out.println(completion.finishReason());
    System.out.println(completion.tokenUsage());
    return completion;
  }

  public Response<String> useAzureOpenAiLanguageModelPrompt() {
    System.out.println("### useAzureOpenAiLanguageModelPrompt");

    AzureOpenAiLanguageModel model = AzureOpenAiLanguageModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.3)
      .logRequestsAndResponses(true)
      .build();

    Prompt prompt = new Prompt("When was the first Beatles album released?");
    Response<String> completion = model.generate(prompt);

    String content = completion.content();
    FinishReason finishReason = completion.finishReason();
    TokenUsage tokenUsage = completion.tokenUsage();

    System.out.println(content);
    System.out.println(finishReason.name());
    System.out.println(tokenUsage.inputTokenCount());
    System.out.println(tokenUsage.outputTokenCount());
    System.out.println(tokenUsage.totalTokenCount());
    return completion;
  }

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public String useAzureOpenAiChatModel() {
    System.out.println("### useAzureOpenAiChatModel");

    // tag::adocSnippet[]
    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.3)
      .logRequestsAndResponses(true)
      .build();
    // end::adocSnippet[]

    String completion = model.chat("When was the first Rolling Stones album released?");

    System.out.println(completion);
    return completion;
  }

  public String useAzureOpenAiChatModelBuilder() {
    System.out.println("### useAzureOpenAiChatModelBuilder");

    AzureChatEnhancementConfiguration enhancementConfiguration = new AzureChatEnhancementConfiguration();

    // tag::adocRequest[]
    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .serviceVersion("2020-09-03")
      .maxRetries(3)
      .frequencyPenalty(0.0d)
      .presencePenalty(0.0d)
      .enhancements(enhancementConfiguration)
      .seed(42L)
      .timeout(Duration.ofSeconds(30))
      .maxTokens(150)
      .topP(1.0d)
      .temperature(0.9)
      .logRequestsAndResponses(true)
      .build();
    // end::adocRequest[]

    String completion = model.chat("When was the first Rolling Stones album released?");

    System.out.println(completion);
    return completion;
  }

  public String useAzureOpenAiChatModelSimple() {
    System.out.println("### useAzureOpenAiChatModelSimple");

    // tag::adocSimple[]
    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .build();
    // end::adocSimple[]

    String completion = model.chat("When was the first Rolling Stones album released?");

    System.out.println(completion);
    return completion;
  }

  public ChatResponse useAzureOpenAiChatModelAiMessage() {
    System.out.println("### useAzureOpenAiChatModelAiMessage");

    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.9)
      .logRequestsAndResponses(true)
      .build();

    SystemMessage sysMsg = new SystemMessage("You are a music expert.");
    UserMessage userMsg = new UserMessage("When was the first Rolling Stones album released?");
    ChatResponse completion = model.chat(sysMsg, userMsg);

    System.out.println(completion);
    return completion;
  }

  // ####################
  // ### OLLAMA MODEL ###
  // ####################
  public String useAzureOpenAiOllamaModel() {
    System.out.println("### useAzureOpenAiOllamaModel");

    // tag::adocLlama[]
    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName("llama-3-2-1b-instruct-1")
      .logRequestsAndResponses(true)
      .build();

    System.out.println(model.chat("When was the first Rolling Stones album released?"));
    // end::adocLlama[]
    return model.chat("When was the first Rolling Stones album released?");
  }
}
