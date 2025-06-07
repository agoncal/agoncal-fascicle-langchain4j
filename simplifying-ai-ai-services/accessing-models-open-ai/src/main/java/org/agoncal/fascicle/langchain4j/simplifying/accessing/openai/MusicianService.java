package org.agoncal.fascicle.langchain4j.simplifying.accessing.openai;

import dev.langchain4j.model.chat.StreamingChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static java.util.concurrent.TimeUnit.SECONDS;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class MusicianService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

    // tag::adocSnippet[]
    StreamingChatModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    MusicianAssistant assistant = AiServices.create(MusicianAssistant.class, model);

    TokenStream tokenStream = assistant.generateMusicianBiography("John Coltrane");

    CompletableFuture<ChatResponse> futureResponse = new CompletableFuture<>();

    tokenStream.onPartialResponse(System.out::print)
      .onCompleteResponse(futureResponse::complete)
      .onError(futureResponse::completeExceptionally)
      .start();

    futureResponse.join();
    // end::adocSnippet[]
  }
}
