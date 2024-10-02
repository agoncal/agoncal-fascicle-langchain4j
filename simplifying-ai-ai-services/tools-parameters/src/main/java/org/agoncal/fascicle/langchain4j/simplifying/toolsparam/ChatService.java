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

    // tag::adocQ0[]
    String answer = assistant.chat("Hi, how are you?");
    System.out.println(answer); // Hello! I'm just a bot, but I'm here to help
    // end::adocQ0[]
    Thread.sleep(5000);

    // tag::adocQ1[]
    answer = assistant.chat("Do you have any CD album in stock entitled HELP?");
    System.out.println(answer); // We have the following CD albums titled "Help" in stock: (...)
    // end::adocQ1[]
    Thread.sleep(5000);

    // tag::adocQ2[]
    answer = assistant.chat("Anything from the Beatles released in the 60's, like in 65?");
    System.out.println(answer); // We currently have the following Beatles albums from the 1960s in stock: (...)
    // end::adocQ2[]
    Thread.sleep(5000);

    // tag::adocQ3[]
    answer = assistant.chat("Any item written by Douglas Adams?");
    System.out.println(answer); // We have the following books by Douglas Adams in stock: (...)
    // end::adocQ3[]
    Thread.sleep(5000);
  }
}
