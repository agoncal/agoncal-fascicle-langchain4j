package org.agoncal.fascicle.langchain4j.accessing.openai;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.moderation.ModerationModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiLanguageModel;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;
import static dev.langchain4j.model.LambdaStreamingResponseHandler.onNext;
import static dev.langchain4j.model.LambdaStreamingResponseHandler.onNextAndError;

import java.time.Duration;

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

//    musicianAssistant.useLangChain4jInsteadSDK();
//    musicianAssistant.useOpenAiLanguageTypeOfModel();
//    musicianAssistant.useOpenAiLanguageModel();
//    musicianAssistant.useOpenAiLanguageModelPrompt();
//    musicianAssistant.useOpenAiLanguageModelBuilder();

//    musicianAssistant.useOpenAiChatTypeOfModel();
//    musicianAssistant.useOpenAiChatModelTemperatureOne();
//    musicianAssistant.useOpenAiChatModelTemperatureZero();
//    musicianAssistant.useTypedUntypedResponseString();
//    musicianAssistant.useTypedUntypedResponseUserMessage();
//    musicianAssistant.useTypedUntypedResponseImage();
//    musicianAssistant.useTypedUntypedResponseEmbedding();
//    musicianAssistant.useOpenAiStreaming();
//    musicianAssistant.useOpenAiLambdaStreaming();
//    musicianAssistant.useOpenAiLambdaStreamingError();
//    musicianAssistant.useOpenAiChatModelBuilder();
//    musicianAssistant.useOpenAiStreamingChatTypeOfModel();

//    musicianAssistant.useOpenAiModerationTypeOfModel();
    musicianAssistant.useOpenAiImageTypeOfModel();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
  private static final String OPENAI_ORGANIZATION = System.getenv("OPENAI_ORGANIZATION");
  private static final String PROMPT = "When was the first Beatles album released?";

  // #############################
  // ### OPENAI LANGUAGE MODEL ###
  // #############################
  public void useLangChain4jInsteadSDK() {
    System.out.println("### useLangChain4jInsteadSDK");

    // tag::adocUseLangChain4jInsteadSDK[]
    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    String answer = model.generate("When was the first Beatles album released?");

    System.out.println(answer);
    // end::adocUseLangChain4jInsteadSDK[]
  }

  public void useOpenAiLanguageTypeOfModel() {
    System.out.println("### useOpenAiLanguageTypeOfModel");

    // tag::adocLanguageTypeOfModel[]
    LanguageModel model = OpenAiLanguageModel.withApiKey(OPENAI_API_KEY);

    Response<String> response = model.generate("What is the history of jazz music?");

    System.out.println(response.content());
    // end::adocLanguageTypeOfModel[]
    System.out.println(response.finishReason());
    System.out.println(response.tokenUsage());
  }

  public void useOpenAiLanguageModel() {
    System.out.println("### useOpenAiLanguageModel");

    OpenAiLanguageModel model = OpenAiLanguageModel.withApiKey(OPENAI_API_KEY);

    Response<String> response = model.generate("When was the first Beatles album released?");

    String content = response.content();
    FinishReason finishReason = response.finishReason();
    TokenUsage tokenUsage = response.tokenUsage();

    System.out.println(content);
    System.out.println(finishReason.name());
    System.out.println(tokenUsage.inputTokenCount());
    System.out.println(tokenUsage.outputTokenCount());
    System.out.println(tokenUsage.totalTokenCount());
  }

  public void useOpenAiLanguageModelPrompt() {
    System.out.println("### useOpenAiLanguageModelPrompt");

    OpenAiLanguageModel model = OpenAiLanguageModel.withApiKey(OPENAI_API_KEY);

    Prompt prompt = new Prompt("When was the first Beatles album released?");
    Response<String> response = model.generate(prompt);

    String content = response.content();
    FinishReason finishReason = response.finishReason();
    TokenUsage tokenUsage = response.tokenUsage();

    System.out.println(content);
    System.out.println(finishReason.name());
    System.out.println(tokenUsage.inputTokenCount());
    System.out.println(tokenUsage.outputTokenCount());
    System.out.println(tokenUsage.totalTokenCount());
  }

  public void useOpenAiLanguageModelBuilder() {
    System.out.println("### useOpenAiLanguageModelBuilder");

    // tag::adocSnippet[]
    OpenAiLanguageModel model = OpenAiLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .temperature(0.3)
      .logRequests(true)
      .logResponses(true)
      .build();
    // end::adocSnippet[]

    Response<String> response = model.generate(PROMPT);

    System.out.println(response.content());
    System.out.println(response.finishReason());
    System.out.println(response.tokenUsage());
  }

  // #########################
  // ### OPENAI CHAT MODEL ###
  // #########################
  public void useOpenAiChatTypeOfModel() {
    System.out.println("### useOpenAiChatTypeOfModel");

    // tag::adocChatTypeOfModel[]
    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    UserMessage userMessage = new UserMessage("Who composed the Moonlight Sonata?");
    Response<AiMessage> response = model.generate(userMessage);

    System.out.println(response.content());
    // end::adocChatTypeOfModel[]
  }

  public void useOpenAiSimpleConf() {
    System.out.println("### useOpenAiSimpleConf");

    // tag::adocSimpleConf[]
    OpenAiChatModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);
    // end::adocSimpleConf[]

    String content = model.generate("What inspired the author to start writing?");

    System.out.println(content);
  }

  public void useOpenAiSimpleConf2() {
    System.out.println("### useOpenAiSimpleConf2");

    // tag::adocSimpleConf2[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();
    // end::adocSimpleConf2[]

    String content = model.generate("What inspired the author to start writing?");

    System.out.println(content);
  }

  public void useOpenAiChatModelBuilder() {
    System.out.println("### useOpenAiChatModelBuilder");

    // tag::adocRichConf[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .organizationId(OPENAI_ORGANIZATION)
      .modelName(GPT_4_O)
      .frequencyPenalty(0.5)
      .temperature(0.9)
      .maxRetries(3)
      .maxTokens(150)
      .topP(1.0)
      .seed(42)
      .timeout(Duration.ofSeconds(30))
      .logRequests(true)
      .logResponses(true)
      .build();
    // end::adocRichConf[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useOpenAiChatModelTemperatureOne() {
    System.out.println("### useOpenAiChatModelTemperatureOne");

    // tag::adocOpenAiChatModelTemperatureOne[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(1.0)
      .build();

    String completion = model.generate("In one single sentence, tell me what is the significance of the Beatles in music history?");
    // end::adocOpenAiChatModelTemperatureOne[]

    System.out.println(completion);
  }

  public void useOpenAiChatModelTemperatureZero() {
    System.out.println("### useOpenAiChatModelTemperatureZero");

    // tag::adocOpenAiChatModelTemperatureZero[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.0)
      .build();

    String completion = model.generate("In one single sentence, tell me what is the significance of the Beatles in music history?");
    // end::adocOpenAiChatModelTemperatureZero[]

    System.out.println(completion);
  }

  public void useOpenAiChatModelAiMessage() {
    System.out.println("### useOpenAiChatModelAiMessage");

    OpenAiChatModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    SystemMessage sysMsg = new SystemMessage("You are a music expert.");
    UserMessage userMsg = new UserMessage("When was the first Rolling Stones album released?");
    Response<AiMessage> response = model.generate(sysMsg, userMsg);

    System.out.println(response);
  }

  // ###################################
  // ### OPENAI STREAMING CHAT MODEL ###
  // ###################################
  public void useOpenAiStreamingChatTypeOfModel() {
    System.out.println("### useOpenAiStreamingChatTypeOfModel");

    // tag::adocStreamingChatTypeOfModel[]
    StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    model.generate("What are some common formats and sizes of video tapes?",
      new StreamingResponseHandler<>() {

        @Override
        public void onNext(String token) {
          System.out.print(token);
        }

        @Override
        public void onComplete(Response<AiMessage> response) {
          System.out.println("Streaming completed: " + response);
        }

        @Override
        public void onError(Throwable error) {
          error.printStackTrace();
        }
      });
    // end::adocStreamingChatTypeOfModel[]
  }

  public void useOpenAiStreaming() {
    System.out.println("### useOpenAiStreaming");

    // tag::adocStreaming[]
    StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    model.generate("Who are some influential female musicians?",
      new StreamingResponseHandler<>() {

        @Override
        public void onNext(String token) {
          System.out.print(token);
        }

        @Override
        public void onComplete(Response<AiMessage> response) {
          System.out.println("Streaming completed: " + response);
        }

        @Override
        public void onError(Throwable error) {
          error.printStackTrace();
        }
      });
    // end::adocStreaming[]
  }

  public void useOpenAiLambdaStreaming() {
    System.out.println("### useOpenAiLambdaStreaming");

    StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    // tag::adocLambdaStreaming[]
    model.generate("Who are some influential female musicians?",
      onNext(System.out::print));
    // end::adocLambdaStreaming[]
  }

  public void useOpenAiLambdaStreamingError() {
    System.out.println("### useOpenAiLambdaStreamingError");

    StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    // tag::adocLambdaStreamingError[]
    model.generate("Who are some influential female musicians?",
      onNextAndError(System.out::print, Throwable::printStackTrace));
    // end::adocLambdaStreamingError[]
  }

  // ###############################
  // ### OPENAI MODERATION MODEL ###
  // ###############################
  public void useOpenAiModerationTypeOfModel() {
    System.out.println("### useOpenAiModerationTypeOfModel");

    // tag::adocModerationTypeOfModel[]
    ModerationModel model = OpenAiModerationModel.withApiKey(OPENAI_API_KEY);

    Response<Moderation> response = model.moderate("I want to kill all bass players.");

    System.out.println(response.content());
    // end::adocModerationTypeOfModel[]
  }

  // ##########################
  // ### OPENAI IMAGE MODEL ###
  // ##########################
  public void useOpenAiImageTypeOfModel() {
    System.out.println("### useOpenAiImageTypeOfModel");

    // tag::adocImageTypeOfModel[]
    ImageModel model = OpenAiImageModel.withApiKey(OPENAI_API_KEY);

    Response<Image> response = model.generate("Colourful CD album cover showing all main Jazz artists");

    Image image = response.content();

    System.out.println(image.url());
    System.out.println(image.mimeType());
    // end::adocImageTypeOfModel[]

    response = model.edit(image, "Make it more blue");

    System.out.println(response.content().url());
  }

  // ##################################
  // ### TYPED AND UNTYPED RESPONSE ###
  // ##################################
  public void useTypedUntypedResponseString() {
    System.out.println("### useTypedUntypedResponseString");

    OpenAiChatModel chatModel = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocTypedUntypedResponseString[]
    String response = chatModel.generate("Who is the author of 1984?");
    System.out.println(response);
    // end::adocTypedUntypedResponseString[]
  }

  public void useTypedUntypedResponseUserMessage() {
    System.out.println("### useTypedUntypedResponseUserMessage");

    OpenAiChatModel chatModel = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocTypedUntypedResponseUserMessage[]
    UserMessage message = new UserMessage("Who are the main characters in Moby Dick?");
    Response<AiMessage> response = chatModel.generate(message);
    System.out.println(response.content().text());
    System.out.println(response.tokenUsage());
    System.out.println(response.finishReason());
    // end::adocTypedUntypedResponseUserMessage[]
  }

  public void useTypedUntypedResponseImage() {
    System.out.println("### useTypedUntypedResponseImage");

    OpenAiImageModel imageModel = OpenAiImageModel.withApiKey(OPENAI_API_KEY);

    // tag::adocTypedUntypedResponseImage[]
    Response<Image> response = imageModel.generate("Draw Moby Dick");
    System.out.println(response.content().url());
    System.out.println(response.content().base64Data());
    // end::adocTypedUntypedResponseImage[]
  }

  public void useTypedUntypedResponseEmbedding() {
    System.out.println("### useTypedUntypedResponseEmbedding");

    OpenAiEmbeddingModel embeddingModel = OpenAiEmbeddingModel.withApiKey(OPENAI_API_KEY);

    // tag::adocTypedUntypedResponseEmbedding[]
    Response<Embedding> response = embeddingModel.embed("Moby Dick is a novel by Herman Melville about Captain Ahab's quest to hunt a giant white whale");
    System.out.println(response.content().dimension());
    System.out.println(response.content().vectorAsList());
    // end::adocTypedUntypedResponseEmbedding[]
  }

  public void dontKnow() {
    // tag::adocDontKnow[]
    SystemMessage systemMsg = new SystemMessage("""
      You are a Vintage Store assistant.
      You can answer customers' requests on any of these vintage items.
      If you don't know the answer, say 'I don't know'.
      """);
    // end::adocDontKnow[]
  }
}
