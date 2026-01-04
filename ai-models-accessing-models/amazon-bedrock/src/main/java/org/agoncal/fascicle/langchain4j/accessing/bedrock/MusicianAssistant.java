package org.agoncal.fascicle.langchain4j.accessing.bedrock;

import dev.langchain4j.model.bedrock.BedrockChatModel;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import software.amazon.awssdk.regions.Region;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private final String AWS_ACCESS_KEY_ID = System.getenv("AWS_ACCESS_KEY_ID");
  private final String AWS_SECRET_ACCESS_KEY = System.getenv("AWS_SECRET_ACCESS_KEY");

  private final String PROMPT = "When was the first Beatles album released?";

  // ##############################
  // ### BEDROCK LANGUAGE MODEL ###
  // ##############################
  public String useBedrockLanguageModelBuilder() {
    System.out.println("### useBedrockLanguageModelBuilder");

    // tag::adocSnippet[]
    BedrockChatModel model = BedrockChatModel.builder()
      .defaultRequestParameters(ChatRequestParameters.builder()
        .temperature(0.9)
        .modelName("amazon.titan-text-express-v1")
        .build())
      .region(Region.US_EAST_1)
      .build();
    // end::adocSnippet[]

    String completion = model.chat(PROMPT);

    System.out.println(completion);
    return completion;
  }

  // ##########################
  // ### BEDROCK CHAT MODEL ###
  // ##########################
  public String useBedrockTitanChatModel() {
    System.out.println("### useBedrockTitanChatModel");

    // tag::adocRequest[]
    BedrockChatModel model = BedrockChatModel.builder()
      .defaultRequestParameters(ChatRequestParameters.builder()
        .temperature(0.9)
        .modelName("amazon.titan-text-express-v1")
        .build())
      .region(Region.AWS_GLOBAL)
      .build();
    // end::adocRequest[]

    String completion = model.chat("When was the first Rolling Stones album released?");

    System.out.println(completion);
    return completion;
  }

}
