package org.agoncal.fascicle.langchain4j.accessing.azurefoundrylocal;

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

  // ################################
  // ### FOUNDRY LOCAL CHAT MODEL ###
  // ################################
  public String useOpenAIForFoundryLocal() {
    System.out.println("### useOpenAIForFoundryLocal");

    // tag::adocUseOpenAIForFoundryLocal[]
    ChatModel model = OpenAiChatModel.builder()
      .baseUrl("http://localhost:5273/v1")
      .modelName("deepseek-r1-distill-qwen-14b-generic-gpu")
      .build();
    // end::adocUseOpenAIForFoundryLocal[]

    String answer = model.chat("What is the best Pink Floyd album?");

    System.out.println(answer);
    System.out.println("##################");
    return answer;
  }
}
