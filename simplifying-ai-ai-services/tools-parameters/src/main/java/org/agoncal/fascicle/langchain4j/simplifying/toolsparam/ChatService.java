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
    // end::adocTools[]

    // tag::adocQ1[]
    String answer = assistant.chat("Do you have any CD album in stock entitled HELP?");
    System.out.println(answer); // It was last updated on March 9, 2013
    // end::adocQ1[]
    Thread.sleep(5000);

    // tag::adocQ2[]
    answer = assistant.chat("Anything from the Beatles released in the 60's, like in 65?");
    System.out.println(answer); // Last updated on June 19, 2014
    // end::adocQ2[]
    Thread.sleep(5000);

    // tag::adocQ3[]
    answer = assistant.chat("Any item written by Douglas Adams?");
    System.out.println(answer); // Last updated on June 19, 2014
    // end::adocQ3[]
    Thread.sleep(5000);
  }
}
