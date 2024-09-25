package org.agoncal.fascicle.langchain4j.simplifying.accessing.openai;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static java.lang.System.exit;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class MusicianService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {

    // tag::adocSnippet[]
    StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    MusicianAssistant assistant = AiServices.create(MusicianAssistant.class, model);

    TokenStream tokenStream = assistant.generateMusicianBiography("John Coltrane");

    tokenStream.onNext(System.out::print)
      .onComplete(System.out::println)
      .onError(Throwable::printStackTrace)
      .start();
    // end::adocSnippet[]
  }
}
