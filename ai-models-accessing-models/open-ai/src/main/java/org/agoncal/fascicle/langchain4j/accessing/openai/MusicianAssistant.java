package org.agoncal.fascicle.langchain4j.accessing.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

import static dev.langchain4j.model.LambdaStreamingResponseHandler.onPartialResponse;
import static dev.langchain4j.model.LambdaStreamingResponseHandler.onPartialResponseAndError;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.moderation.ModerationModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1_MINI;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1_NANO;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;

import dev.langchain4j.model.openai.OpenAiEmbeddingModel;

import static dev.langchain4j.model.openai.OpenAiEmbeddingModelName.TEXT_EMBEDDING_3_SMALL;

import dev.langchain4j.model.openai.OpenAiImageModel;

import static dev.langchain4j.model.openai.OpenAiImageModelName.DALL_E_3;

import dev.langchain4j.model.openai.OpenAiLanguageModel;

import static dev.langchain4j.model.openai.OpenAiLanguageModelName.GPT_3_5_TURBO_INSTRUCT;

import dev.langchain4j.model.openai.OpenAiModerationModel;

import static dev.langchain4j.model.openai.OpenAiModerationModelName.TEXT_MODERATION_STABLE;

import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) throws JsonProcessingException {

//    useLangChain4jInsteadSDK();
//    useOpenAiLanguageTypeOfModel();
//    useOpenAiLanguageModel();
//    useOpenAiLanguageModelPrompt();
//    useOpenAiLanguageModelBuilder();

//    useOpenAiChatTypeOfModel();
//    useOpenAiSimpleConf();
//    useOpenAiRichConf();
//    useOpenAiSimpleConf2();
//    useOpenAiChatRequest();
//    useOpenAiChatRequestDefault();
//    useOpenAiResponseString();
//    useOpenAiChatResponse();
//    useOpenAiChatModelTemperatureOne();
//    useOpenAiChatModelTemperatureZero();
//    useOpenAiChatModelAiMessage();

//    useOpenAiStreamingChatTypeOfModel();
//    useOpenAiStreaming();
//    useOpenAiLambdaStreaming();
    useOpenAiLambdaStreamingError();

//    useOpenAiModerationTypeOfModel();
//    useOpenAiImageTypeOfModel();

//    useTypedUntypedResponseString();
//    useTypedUntypedResponseUserMessage();
//    useTypedUntypedResponseImage();
//    useTypedUntypedResponseEmbedding();
//    useJSONResponseFormat();
//    useOpenAIForDeepSeek();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
  private static final String OPENAI_ORGANIZATION = System.getenv("OPENAI_ORGANIZATION");
  private static final String DEEPSEEK_API_KEY = System.getenv("DEEPSEEK_API_KEY");

  private static final String PROMPT = "When was the first Beatles album released?";


  // #############################
  // ### OPENAI LANGUAGE MODEL ###
  // #############################
  private static void useLangChain4jInsteadSDK() {
    System.out.println("### useLangChain4jInsteadSDK");

    // tag::adocUseLangChain4jInsteadSDK[]
    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    String answer = model.chat("When was the first Beatles album released?");

    System.out.println(answer);
    // end::adocUseLangChain4jInsteadSDK[]
  }

  private static void useOpenAiLanguageTypeOfModel() {
    System.out.println("### useOpenAiLanguageTypeOfModel");

    // tag::adocLanguageTypeOfModel[]
    LanguageModel model = OpenAiLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO_INSTRUCT)
      .build();

    Response<String> response = model.generate("What is the history of jazz music?");

    System.out.println(response.content());
    // end::adocLanguageTypeOfModel[]
    System.out.println(response.finishReason());
    System.out.println(response.tokenUsage());
  }

  private static void useOpenAiLanguageModel() {
    System.out.println("### useOpenAiLanguageModel");

    OpenAiLanguageModel model = OpenAiLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO_INSTRUCT)
      .build();

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

  private static void useOpenAiLanguageModelPrompt() {
    System.out.println("### useOpenAiLanguageModelPrompt");

    OpenAiLanguageModel model = OpenAiLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO_INSTRUCT)
      .build();

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

  private static void useOpenAiLanguageModelBuilder() {
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
  private static void useOpenAiChatTypeOfModel() {
    System.out.println("### useOpenAiChatTypeOfModel");

    // tag::adocChatTypeOfModel[]
    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_NANO)
      .build();

    UserMessage userMessage = new UserMessage("Who composed the Moonlight Sonata?");
    ChatResponse response = model.chat(userMessage);

    System.out.println(response.aiMessage().text());
    // end::adocChatTypeOfModel[]
  }

  private static void useOpenAiSimpleConf() {
    System.out.println("### useOpenAiSimpleConf");

    // tag::adocSimpleConf[]
    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();
    // end::adocSimpleConf[]

    String content = model.chat("What inspired the author to start writing?");

    System.out.println(content);
  }

  private static void useOpenAiSimpleConf2() {
    System.out.println("### useOpenAiSimpleConf2");

    // tag::adocSimpleConf2[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();
    // end::adocSimpleConf2[]

    String content = model.chat("What inspired the author to start writing?");

    System.out.println(content);
  }

  private static void useOpenAiRichConf() {
    System.out.println("### useOpenAiRichConf");

    // tag::adocRichConf[]
    ChatModel model = OpenAiChatModel.builder()
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

    String completion = model.chat("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  private static void useOpenAiChatRequest() {
    System.out.println("### useOpenAiChatRequest");

    // tag::adocChatRequest[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .build();

    ChatRequestParameters parameters = ChatRequestParameters.builder()
      .modelName(GPT_4_O.toString())
      .temperature(0.7)
      .frequencyPenalty(0.5)
      .temperature(0.9)
      .maxOutputTokens(150)
      .build();

    ChatRequest chatRequest = ChatRequest.builder()
      .messages(UserMessage.from("When was the first Rolling Stones album released?"))
      .parameters(parameters)
      .build();

    ChatResponse chatResponse = model.chat(chatRequest);
    // end::adocChatRequest[]

    System.out.println(chatResponse.aiMessage().text());
  }

  private static void useOpenAiChatRequestDefault() {
    System.out.println("### useOpenAiChatRequestDefault");

    // tag::adocChatRequestDefault[]
    ChatRequestParameters parameters = ChatRequestParameters.builder()
      .modelName(GPT_4_O.toString())
      .temperature(0.7)
      .frequencyPenalty(0.5)
      .temperature(0.9)
      .maxOutputTokens(150)
      .build();

    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .defaultRequestParameters(parameters)
      .build();

    ChatRequest chatRequest = ChatRequest.builder()
      .messages(UserMessage.from("When was the first Rolling Stones album released?"))
      .build();

    ChatResponse chatResponse = model.chat(chatRequest);
    // end::adocChatRequestDefault[]

    System.out.println(chatResponse.aiMessage().text());
  }

  private static void useOpenAiResponseString() {
    System.out.println("### useOpenAiResponseString");

    ChatRequestParameters parameters = ChatRequestParameters.builder()
      .modelName(GPT_4_O.toString())
      .temperature(0.7)
      .frequencyPenalty(0.5)
      .temperature(0.9)
      .maxOutputTokens(150)
      .build();

    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .defaultRequestParameters(parameters)
      .build();

    // tag::adocResponseString[]
    String response = model.chat("When was the first Rolling Stones album released?");

    System.out.println(response);
    // end::adocResponseString[]
  }

  private static void useOpenAiChatResponse() {
    System.out.println("### useOpenAiChatResponse");

    ChatRequestParameters parameters = ChatRequestParameters.builder()
      .modelName(GPT_4_O.toString())
      .temperature(0.7)
      .frequencyPenalty(0.5)
      .temperature(0.9)
      .maxOutputTokens(150)
      .build();

    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .defaultRequestParameters(parameters)
      .build();

    // tag::adocChatResponse[]
    ChatRequest chatRequest = ChatRequest.builder()
      .messages(UserMessage.from("When was the first Rolling Stones album released?"))
      .build();

    ChatResponse chatResponse = model.chat(chatRequest);

    System.out.println(chatResponse.aiMessage().text());
    System.out.println(chatResponse.id());
    System.out.println(chatResponse.finishReason());
    System.out.println(chatResponse.modelName());
    // end::adocChatResponse[]
  }

  private static void useOpenAiChatModelTemperatureOne() {
    System.out.println("### useOpenAiChatModelTemperatureOne");

    // tag::adocOpenAiChatModelTemperatureOne[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      .temperature(1.0)
      .build();

    String completion = model.chat("In one single sentence, tell me what is the significance of the Beatles in music history?");
    // end::adocOpenAiChatModelTemperatureOne[]

    System.out.println(completion);
  }

  private static void useOpenAiChatModelTemperatureZero() {
    System.out.println("### useOpenAiChatModelTemperatureZero");

    // tag::adocOpenAiChatModelTemperatureZero[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      .temperature(0.0)
      .build();

    String completion = model.chat("In one single sentence, tell me what is the significance of the Beatles in music history?");
    // end::adocOpenAiChatModelTemperatureZero[]

    System.out.println(completion);
  }

  private static void useOpenAiChatModelAiMessage() {
    System.out.println("### useOpenAiChatModelAiMessage");

    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    SystemMessage sysMsg = new SystemMessage("You are a music expert.");
    UserMessage userMsg = new UserMessage("When was the first Rolling Stones album released?");
    ChatResponse response = model.chat(sysMsg, userMsg);

    System.out.println(response.aiMessage().text());
  }

  // ###################################
  // ### OPENAI STREAMING CHAT MODEL ###
  // ###################################
  private static void useOpenAiStreamingChatTypeOfModel() {
    System.out.println("### useOpenAiStreamingChatTypeOfModel");

    // tag::adocStreamingChatTypeOfModel[]
    StreamingChatModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    CompletableFuture<ChatResponse> futureResponse = new CompletableFuture<>();

    model.chat("What are some common formats and sizes of video tapes?",
      new StreamingChatResponseHandler() {

        @Override
        public void onPartialResponse(String token) {
          System.out.print(token);
        }

        @Override
        public void onCompleteResponse(ChatResponse completeResponse) {
          futureResponse.complete(completeResponse);
        }

        @Override
        public void onError(Throwable error) {
          futureResponse.completeExceptionally(error);
        }
      });

    futureResponse.join();
    // end::adocStreamingChatTypeOfModel[]
  }

  private static void useOpenAiStreaming() {
    System.out.println("### useOpenAiStreaming");

    // tag::adocStreaming[]
    StreamingChatModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      .build();

    CompletableFuture<ChatResponse> futureResponse = new CompletableFuture<>();

    model.chat("Who are some influential female musicians?",
      new StreamingChatResponseHandler() {

        @Override
        public void onPartialResponse(String token) {
          System.out.print(token);
        }

        @Override
        public void onCompleteResponse(ChatResponse completeResponse) {
          futureResponse.complete(completeResponse);
        }

        @Override
        public void onError(Throwable error) {
          futureResponse.completeExceptionally(error);
        }
      });

    futureResponse.join();
    // end::adocStreaming[]
  }

  private static void useOpenAiLambdaStreaming() {
    System.out.println("### useOpenAiLambdaStreaming");

    StreamingChatModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    CompletableFuture<ChatResponse> futureResponse = new CompletableFuture<>();

    // tag::adocLambdaStreaming[]
    model.chat("Who are some influential female musicians?",
      onPartialResponse(System.out::print));
    // end::adocLambdaStreaming[]

    futureResponse.join();
  }

  private static void useOpenAiLambdaStreamingError() {
    System.out.println("### useOpenAiLambdaStreamingError");

    StreamingChatModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    CompletableFuture<ChatResponse> futureResponse = new CompletableFuture<>();

    // tag::adocLambdaStreamingError[]
    model.chat("Who are some influential female musicians?",
      onPartialResponseAndError(System.out::print, Throwable::printStackTrace));
    // end::adocLambdaStreamingError[]

    futureResponse.join();
  }

  // ###############################
  // ### OPENAI MODERATION MODEL ###
  // ###############################
  private static void useOpenAiModerationTypeOfModel() {
    System.out.println("### useOpenAiModerationTypeOfModel");

    // tag::adocModerationTypeOfModel[]
    ModerationModel model = OpenAiModerationModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(TEXT_MODERATION_STABLE)
      .build();

    Response<Moderation> response = model.moderate("I want to kill all bass players.");

    System.out.println(response.content());
    // end::adocModerationTypeOfModel[]
  }

  // ##########################
  // ### OPENAI IMAGE MODEL ###
  // ##########################
  private static void useOpenAiImageTypeOfModel() {
    System.out.println("### useOpenAiImageTypeOfModel");

    // tag::adocImageTypeOfModel[]
    ImageModel model = OpenAiImageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(DALL_E_3)
      .build();

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
  private static void useTypedUntypedResponseString() {
    System.out.println("### useTypedUntypedResponseString");

    OpenAiChatModel chatModel = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocTypedUntypedResponseString[]
    String response = chatModel.chat("Who is the author of 1984?");
    System.out.println(response);
    // end::adocTypedUntypedResponseString[]
  }

  private static void useTypedUntypedResponseStringOneLine() {
    System.out.println("### useTypedUntypedResponseStringOneLine");

    OpenAiChatModel chatModel = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocTypedUntypedResponseStringOneLine[]
    System.out.println(chatModel.chat("Who is the author of 1984?"));
    // end::adocTypedUntypedResponseStringOneLine[]
  }

  private static void useTypedUntypedResponseUserMessage() {
    System.out.println("### useTypedUntypedResponseUserMessage");

    OpenAiChatModel chatModel = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocTypedUntypedResponseUserMessage[]
    UserMessage message = new UserMessage("Who are the main characters in Moby Dick?");
    ChatResponse response = chatModel.chat(message);

    System.out.println(response.aiMessage().text()); // Captain Ahab, Captain Peleg, Flask
    System.out.println(response.tokenUsage());       // inputToken=17, outputToken=34
    System.out.println(response.finishReason());     // STOP
    // end::adocTypedUntypedResponseUserMessage[]
  }

  private static void useTypedUntypedResponseUserMessageOneLine() {
    System.out.println("### useTypedUntypedResponseUserMessageOneLine");

    OpenAiChatModel chatModel = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocTypedUntypedResponseUserMessageOneLine[]
    UserMessage message = new UserMessage("Who are the main characters in Moby Dick?");
    System.out.println(chatModel.chat(message).aiMessage().text());
    // end::adocTypedUntypedResponseUserMessageOneLine[]
  }

  private static void useTypedUntypedResponseImage() {
    System.out.println("### useTypedUntypedResponseImage");

    OpenAiImageModel imageModel = OpenAiImageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(DALL_E_3)
      .build();

    // tag::adocTypedUntypedResponseImage[]
    Response<Image> response = imageModel.generate("Draw Moby Dick");

    System.out.println(response.content().url());
    System.out.println(response.content().base64Data());
    // end::adocTypedUntypedResponseImage[]
  }

  private static void useTypedUntypedResponseEmbedding() {
    System.out.println("### useTypedUntypedResponseEmbedding");

    OpenAiEmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(TEXT_EMBEDDING_3_SMALL)
      .build();

    // tag::adocTypedUntypedResponseEmbedding[]
    Response<Embedding> response = embeddingModel.embed("Moby Dick is a novel by Herman Melville about Captain Ahab's quest to hunt a giant white whale");
    System.out.println(response.content().dimension());    // 1536
    System.out.println(response.content().vectorAsList()); // [0.0598405, 0.057074,...]
    // end::adocTypedUntypedResponseEmbedding[]
  }

  private static void useJSONResponseFormat() throws JsonProcessingException {

    // tag::adocJSONResponseFormat[]
    ResponseFormat responseFormat = ResponseFormat.builder()
      .type(ResponseFormatType.JSON)
      .jsonSchema(JsonSchema.builder()
        .name("Musician")
        .rootElement(JsonObjectSchema.builder()
          .addStringProperty("name")
          .addIntegerProperty("age")
          .addStringProperty("instrument")
          .required("name", "age")
          .build())
        .build())
      .build();

    UserMessage userMessage = UserMessage.from("Return the top 10 Jazz musicians");

    ChatRequest chatRequest = ChatRequest.builder()
      .responseFormat(responseFormat)
      .messages(userMessage)
      .build();

    ChatModel chatModel = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName("gpt-4o-mini")
      .build();

    ChatResponse chatResponse = chatModel.chat(chatRequest);
    // end::adocJSONResponseFormat[]

    String output = chatResponse.aiMessage().text();
    System.out.println(output);
  }

  private static void useOpenAIForDeepSeek() {
    System.out.println("### useOpenAIForDeepSeek");

    // tag::adocDeepSeek[]
    ChatModel model = OpenAiChatModel.builder()
      .apiKey(DEEPSEEK_API_KEY)
      .baseUrl("https://api.deepseek.com")
      .modelName("deepseek-chat")
      .build();

    String answer = model.chat("When was the first Rolling Stones album released?");

    System.out.println(answer);
    // end::adocDeepSeek[]
  }

  private static void dontKnow() {
    // tag::adocDontKnow[]
    SystemMessage systemMsg = new SystemMessage("""
      You are a Vintage Store assistant.
      You can answer customers' requests on any of these vintage items.
      If you don't know the answer, say 'I don't know'.
      """);
    // end::adocDontKnow[]
  }
}
