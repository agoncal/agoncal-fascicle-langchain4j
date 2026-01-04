package org.agoncal.fascicle.langchain4j.accessing.googlegeminiai;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private final String GOOGLE_AI_GEMINI_API_KEY = System.getenv("GOOGLE_AI_GEMINI_API_KEY");

  // ###################################
  // ### GOOGLE AI GEMINI CHAT MODEL ###
  // ###################################
  public String useGoogleAiGeminiChatModel() {
    System.out.println("### useGoogleAiGeminiChatModel");

    // tag::adocSnippet[]
    GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
      .apiKey(GOOGLE_AI_GEMINI_API_KEY)
      .modelName("gemini-1.5-flash")
      // tag::adocSkip[]
      .logRequestsAndResponses(true)
      // end::adocSkip[]
      .build();

    System.out.println(model.chat("List some influential Jazz musicians"));
    // end::adocSnippet[]
    return model.chat("List some influential Jazz musicians");
  }
}
