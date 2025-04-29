package org.agoncal.fascicle.langchain4j.simplifying.extractorimplicit;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import dev.langchain4j.service.AiServices;

public class MusicianService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {

    // tag::adocSnippet[]
    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      // tag::adocSkip[]
      .responseFormat("json_schema")
      .strictJsonSchema(true)
      .logResponses(true)
      .logRequests(true)
      // end::adocSkip[]
      .build();

    MusicianAssistant assistant = AiServices.create(MusicianAssistant.class, model);

    Musician musician = assistant.generateTopThreeAlbums("Nina Simon");

    musician.albums().forEach(System.out::println);
    // Little Girl Blue
    // I Put a Spell on You
    // Pastel Blues
    // end::adocSnippet[]
  }
}
