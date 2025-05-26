package org.agoncal.fascicle.langchain4j.accessing.deepseek;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) {
    useOpenAIForFoundryLocal();
  }

  // ################################
  // ### FOUNDRY LOCAL CHAT MODEL ###
  // ################################
  static void useOpenAIForFoundryLocal() {
    System.out.println("### useOpenAIForFoundryLocal");

    // tag::adocUseOpenAIForFoundryLocal[]
    ChatModel model = OpenAiChatModel.builder()
      .baseUrl("http://localhost:5273/v1")
      .modelName("deepseek-r1-distill-qwen-14b-generic-gpu")
      .logRequests(true)
      .logResponses(true)
      .build();

    String answer = model.chat("What is the best Pink Floyd album?");

    System.out.println(answer);
    System.out.println("##################");
    // end::adocUseOpenAIForFoundryLocal[]
  }
}
