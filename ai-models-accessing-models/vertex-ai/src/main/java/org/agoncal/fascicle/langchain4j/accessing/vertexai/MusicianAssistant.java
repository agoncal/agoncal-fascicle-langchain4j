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

    musicianAssistant.useVertexAiLanguageModelBuilder();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###################################
  // ### AZURE OPENAI LANGUAGE MODEL ###
  // ###################################
  public void useVertexAiLanguageModelBuilder() {
    System.out.println("### useVertexAiLanguageModelBuilder");

    // tag::adocSnippet[]
    VertexAiGeminiChatModel model = VertexAiGeminiChatModel.builder()
      .project("langchain4j-fascicle-project")
      .location("us-central1")
      .modelName("gemini-1.0-pro")
      .temperature(0.3f)
      .build();
    // end::adocSnippet[]

    String completion = model.generate(PROMPT);
  }
}
