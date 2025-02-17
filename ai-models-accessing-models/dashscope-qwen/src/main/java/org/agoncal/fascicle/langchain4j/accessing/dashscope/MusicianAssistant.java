package org.agoncal.fascicle.langchain4j.accessing.dashscope;

import dev.langchain4j.community.model.dashscope.QwenChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) {
    useQwenChatModelBuilder();
  }

  private static final String AWS_ACCESS_KEY_ID = System.getenv("AWS_ACCESS_KEY_ID");
  private static final String AWS_SECRET_ACCESS_KEY = System.getenv("AWS_SECRET_ACCESS_KEY");

  // ##############################
  // ### BEDROCK LANGUAGE MODEL ###
  // ##############################
  private static void useQwenChatModelBuilder() {
    System.out.println("### useQwenChatModelBuilder");

    // tag::adocSnippet[]
    QwenChatModel model = QwenChatModel.builder()
      .temperature(0.9f)
      .build();
    // end::adocSnippet[]

    String completion = model.chat("When was the first Beatles album released?");

    System.out.println(completion);
  }
}
