package org.agoncal.fascicle.langchain4j.accessing.anthropic;

import dev.langchain4j.model.anthropic.AnthropicChatModel;

import static dev.langchain4j.model.anthropic.AnthropicChatModelName.CLAUDE_3_5_SONNET_20240620;

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
      .modelName(CLAUDE_3_5_SONNET_20240620)
      // tag::adocSkip[]
      .logRequests(true)
      .logResponses(true)
      // end::adocSkip[]
      .build();

    System.out.println(model.chat("List some influential Jazz musicians"));
    // end::adocSnippet[]
  }
}
