package org.agoncal.fascicle.langchain4j.accessing.huggingface;

import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import static dev.langchain4j.model.huggingface.HuggingFaceModelName.TII_UAE_FALCON_7B_INSTRUCT;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private final String HF_API_KEY = System.getenv("HF_API_KEY");

  private final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public String useHuggingFaceChatModel() {
    System.out.println("### useHuggingFaceChatModel");

    // tag::adocSnippet[]
    HuggingFaceChatModel model = HuggingFaceChatModel.builder()
      .accessToken(HF_API_KEY)
      .modelId(TII_UAE_FALCON_7B_INSTRUCT)
      .temperature(0.3)
      .build();
    // end::adocSnippet[]

    String completion = model.chat("When was the first Rolling Stones album released?");

    System.out.println(completion);
    return completion;
  }
}
