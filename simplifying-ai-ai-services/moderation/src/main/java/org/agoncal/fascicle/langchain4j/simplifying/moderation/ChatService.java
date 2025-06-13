package org.agoncal.fascicle.langchain4j.simplifying.moderation;

// tag::adocSnippet[]

import dev.langchain4j.model.moderation.ModerationModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import static dev.langchain4j.model.openai.OpenAiModerationModelName.TEXT_MODERATION_LATEST;
import dev.langchain4j.service.AiServices;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class ChatService {

  public static void main(String[] args) throws InterruptedException {
//    ChatService.withoutModeration();
    ChatService.withModeration();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // ###################
  // ### Moderation  ###
  // ###################
  private static void withModeration() throws InterruptedException {
    System.out.println("### withModeration");

    // tag::adocWithModeration[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      // tag::adocSkip[]
      .logRequests(true)
      .logResponses(true)
      // end::adocSkip[]
      .build();

    ModerationModel moderationModel = OpenAiModerationModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(TEXT_MODERATION_LATEST)
      // tag::adocSkip[]
      .logRequests(true)
      .logResponses(true)
      // end::adocSkip[]
      .build();

    ChatAssistant assistant = AiServices.builder(ChatAssistant.class)
      .chatModel(model)
      .moderationModel(moderationModel)
      .build();

    String answer = assistant.chat("I want to kill all bass players");
    // ModerationException is thrown
    // end::adocWithModeration[]
    System.out.println(answer);
  }

  private static void withoutModeration() throws InterruptedException {
    System.out.println("### withoutModeration");

    // tag::adocWithoutModeration[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      // tag::adocSkip[]
      .logRequests(true)
      .logResponses(true)
      // end::adocSkip[]
      .build();

    ChatAssistant assistant = AiServices.builder(ChatAssistant.class)
      .chatModel(model)
      .build();

    String answer = assistant.chat("I want to kill all bass players");
    System.out.println(answer); // I'm really sorry to hear that you're feeling this way, but I can't assist with that.
    // end::adocWithoutModeration[]
  }
}
