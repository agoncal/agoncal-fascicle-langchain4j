package org.agoncal.fascicle.langchain4j.simplifying.accessing.ollama.streaming;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import static java.lang.System.exit;

import java.util.concurrent.CompletableFuture;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class MusicianService {

  public static void main(String[] args) {
    String musicianName = args[0];

// tag::adocSnippet[]
    OllamaStreamingChatModel model = OllamaStreamingChatModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("phi3:mini")
      .build();

    MusicianAssistant assistant = AiServices.builder(MusicianAssistant.class)
      .streamingChatLanguageModel(model)
      .build();

    CompletableFuture<Response<AiMessage>> answer = new CompletableFuture<>();

    assistant.generateMusicianBiography(musicianName)
      .onPartialResponse(System.out::println)
      .onCompleteResponse(System.out::println)
      .onError(answer::completeExceptionally)
      .start();
// end::adocSnippet[]

    exit(0);
  }
}
