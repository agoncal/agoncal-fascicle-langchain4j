package org.agoncal.fascicle.langchain4j.accessing.anthropic;

import dev.langchain4j.model.anthropic.AnthropicChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) {
    useAnthropicChatModel();
  }

  private static final String ANTHROPIC_API_KEY = System.getenv("ANTHROPIC_API_KEY");

  // ################################
  // ### ANTHROPIC LANGUAGE MODEL ###
  // ################################
  private static void useAnthropicChatModel() {
    System.out.println("### useAnthropicChatModel");

    // tag::adocSnippet[]
    AnthropicChatModel model = AnthropicChatModel.builder()
      .apiKey(ANTHROPIC_API_KEY)
      .logRequests(true)
      .logResponses(true)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("What is the capital of France?");

    System.out.println(completion);
  }
}
