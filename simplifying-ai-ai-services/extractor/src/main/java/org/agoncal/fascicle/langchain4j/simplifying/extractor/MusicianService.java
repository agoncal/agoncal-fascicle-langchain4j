package org.agoncal.fascicle.langchain4j.simplifying.extractor;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;

public class MusicianService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {

    // tag::adocSnippet[]
    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.3)
      // tag::adocSkip[]
      .logResponses(true)
      .logRequests(true)
      // end::adocSkip[]
      .build();

    MusicianExtractor extractor = AiServices.create(MusicianExtractor.class, model);

    Musician musician = extractor.extractMusician("""
      Ella Jane Fitzgerald (April 25, 1917 – June 15, 1996) was an American singer,
      songwriter and composer, sometimes referred to as the "First Lady of Song",
      "Queen of Jazz", and "Lady Ella". She was noted for her purity of tone,
      impeccable diction, phrasing, timing, intonation, absolute pitch,
      and a "horn-like" improvisational ability, particularly in her scat singing.
      """
    );

    System.out.println(musician.name()); // Ella Jane Fitzgerald
    System.out.println(musician.age());  // 79
    // end::adocSnippet[]
  }
}
