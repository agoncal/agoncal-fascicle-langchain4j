package org.agoncal.fascicle.langchain4j.accessing.vertexai;

import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;

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

    musicianAssistant.useVertexAiGeminiChatModel();
  }

  private static final String GCP_LOCATION = System.getenv("GCP_LOCATION");
  private static final String GCP_PROJECT_ID = System.getenv("GCP_PROJECT_ID");
  public static final String GEMINI_1_5_PRO = "gemini-1.5-pro-001";

  // ###################################
  // ### VERTEX AI GEMINI CHAT MODEL ###
  // ###################################
  public void useVertexAiGeminiChatModel() {
    System.out.println("### useVertexAiChatModelBuilder");

    // tag::adocSnippet[]
    VertexAiGeminiChatModel model = VertexAiGeminiChatModel.builder()
      .project(GCP_PROJECT_ID)
      .location(GCP_LOCATION)
      .modelName(GEMINI_1_5_PRO)
      .temperature(0.3f)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
