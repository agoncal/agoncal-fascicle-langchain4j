package org.agoncal.fascicle.langchain4j.simplifying.toolsparam;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import dev.langchain4j.service.AiServices;

public class ChatService {

  public static void main(String[] args) throws Exception {

    // tag::adocTools[]
    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName(GPT_4_O)
      // tag::adocSkip[]
      .logRequests(true)
      .logResponses(true)
      // end::adocSkip[]
      .build();

    ChatAssistant assistant = AiServices.builder(ChatAssistant.class)
      .chatLanguageModel(model)
      .tools(new CurrentStockTools())
      .build();

    String answer = assistant.chat("When was the PRIVACY document updated?");
    System.out.println(answer); // It was last updated on March 9, 2013
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    answer = assistant.chat("Do you have any CD album entitled HELP?");
    System.out.println(answer); // Last updated on June 19, 2014
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    answer = assistant.chat("Anything from the Beatles released in the 60's, like in 65");
    System.out.println(answer); // Last updated on June 19, 2014
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    // end::adocTools[]
  }
}
