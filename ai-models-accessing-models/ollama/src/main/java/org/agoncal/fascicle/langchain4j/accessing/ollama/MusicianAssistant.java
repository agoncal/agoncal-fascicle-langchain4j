package org.agoncal.fascicle.langchain4j.accessing.ollama;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

//    musicianService.useOllamaLanguageModel();
//    musicianService.useOllamaLanguageModelPrompt();
//    musicianService.useOllamaLanguageModelBuilder();
//    musicianService.useOllamaChatModel();
    musicianAssistant.useOllamaChatModelBuilder();
  }

  private static final String PROMPT = "When was the first Beatles album released?";

  // #############################
  // ### OLLAMA LANGUAGE MODEL ###
  // #############################
  public void useOllamaLanguageModel() {
    System.out.println("### useOpenAiLanguageModel");

    // tag::adocSnippet[]
    OllamaLanguageModel model = OllamaLanguageModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("orca-mini")
      .build();
    // end::adocSnippet[]

    Response<String> completion = model.generate("When was the first Beatles album released?");

    String content = completion.content();
    TokenUsage tokenUsage = completion.tokenUsage();

    System.out.println(content);
    System.out.println(tokenUsage.inputTokenCount());
    System.out.println(tokenUsage.outputTokenCount());
    System.out.println(tokenUsage.totalTokenCount());
  }

  public void useOllamaLanguageModelPrompt() {
    System.out.println("### useOpenAiLanguageModelPrompt");

    OllamaLanguageModel model = OllamaLanguageModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("orca-mini")
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
  }

  public void useOllamaLanguageModelBuilder() {
    System.out.println("### useOpenAiLanguageModelBuilder");

    OllamaLanguageModel model = OllamaLanguageModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("orca-mini")
      .temperature(0.3)
      .build();

    Response<String> completion = model.generate(PROMPT);

    System.out.println(completion.content());
    System.out.println(completion.finishReason());
    System.out.println(completion.tokenUsage());
  }

  // #########################
  // ### OLLAMA CHAT MODEL ###
  // #########################
  public void useOllamaChatModel() {
    System.out.println("### useOpenAiChatModel");

    OllamaChatModel model = OllamaChatModel.builder().build();

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useOllamaChatModelBuilder() {
    System.out.println("### useOllamaChatModelBuilder");

    // tag::adocChatModel[]
    OllamaChatModel model = OllamaChatModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("llama3.1")
      .temperature(0.9)
      .maxRetries(2)
      .build();
    // end::adocChatModel[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useOllamaChatModelAiMessage() {
    System.out.println("### useOpenAiChatModelAiMessage");

    OllamaChatModel model = OllamaChatModel.builder().build();

    SystemMessage sysMsg = new SystemMessage("You are a music expert.");
    UserMessage userMsg = new UserMessage("When was the first Rolling Stones album released?");
    Response<AiMessage> completion = model.generate(sysMsg, userMsg);

    System.out.println(completion);
  }
}
