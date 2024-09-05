package org.agoncal.fascicle.langchain4j.accessing.huggingface;

import dev.langchain4j.model.huggingface.HuggingFaceChatModel;

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

    musicianAssistant.useHuggingFaceChatModel();
  }

  private static final String HF_API_KEY = System.getenv("HF_API_KEY");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public void useHuggingFaceChatModel() {
    System.out.println("### useHuggingFaceChatModel");

    // tag::adocSnippet[]
    HuggingFaceChatModel model = HuggingFaceChatModel.builder()
      .accessToken(HF_API_KEY)
      .temperature(0.3)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
