package org.agoncal.fascicle.langchain4j.accessing.vertexaigemini;

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

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public void useVertexAiGeminiChatModel() {
    System.out.println("### useAzureOpenAiChatModel");

    // tag::adocSnippet[]
    VertexAiGeminiChatModel model = VertexAiGeminiChatModel.builder()
      .temperature(0.3f)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
