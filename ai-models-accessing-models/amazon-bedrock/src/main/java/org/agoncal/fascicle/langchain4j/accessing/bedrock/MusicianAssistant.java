package org.agoncal.fascicle.langchain4j.accessing.bedrock;

import dev.langchain4j.model.bedrock.BedrockTitanChatModel;
import static dev.langchain4j.model.bedrock.BedrockTitanChatModel.Types.TitanTextExpressV1;
import software.amazon.awssdk.regions.Region;

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

    musicianAssistant.useBedrockTitanChatModel();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ##############################
  // ### BEDROCK LANGUAGE MODEL ###
  // ##############################
  public void useBedrockLanguageModelBuilder() {
    System.out.println("### useBedrockLanguageModelBuilder");

    // tag::adocSnippet[]
    BedrockTitanChatModel model = BedrockTitanChatModel.builder()
      .temperature(0.9)
      .model(TitanTextExpressV1.getValue())
      .build();
    // end::adocSnippet[]

    String completion = model.generate(PROMPT);

    System.out.println(completion);
  }

  // ##########################
  // ### BEDROCK CHAT MODEL ###
  // ##########################
  public void useBedrockTitanChatModel() {
    System.out.println("### useBedrockTitanChatModel");

    // tag::adocRequest[]
    BedrockTitanChatModel model = BedrockTitanChatModel.builder()
      .temperature(0.9)
      .model(TitanTextExpressV1.getValue())
      .region(Region.US_EAST_1)
      .build();
    // end::adocRequest[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

}
