package org.agoncal.fascicle.langchain4j.accessing.mistralai;

import dev.langchain4j.model.mistralai.MistralAiChatModel;

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

    musicianAssistant.useMistralAiChatModel();
  }

  private static final String MISTRAL_AI_API_KEY = System.getenv("MISTRAL_AI_API_KEY");

  // ##########################
  // ### MISTRAL CHAT MODEL ###
  // ##########################
  public void useMistralAiChatModel() {
    System.out.println("### useMistralAiChatModel");

    // tag::adocSnippet[]
    MistralAiChatModel model = MistralAiChatModel.builder()
      .apiKey(MISTRAL_AI_API_KEY)
      .temperature(0.3)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
