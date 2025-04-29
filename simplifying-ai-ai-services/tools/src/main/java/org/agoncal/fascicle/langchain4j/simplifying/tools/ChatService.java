package org.agoncal.fascicle.langchain4j.simplifying.tools;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import dev.langchain4j.service.AiServices;

public class ChatService {

  public static void main(String[] args) throws Exception {

    // tag::adocTools[]
    ChatModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName(GPT_4_O)
      // tag::adocSkip[]
      .logRequests(true)
      .logResponses(true)
      // end::adocSkip[]
      .build();

    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    ChatAssistant assistant = AiServices.builder(ChatAssistant.class)
      .chatModel(model)
      .chatMemory(chatMemory)
      .tools(new LegalDocumentTools())
      .build();

    String answer = assistant.chat("When was the PRIVACY document updated?");
    System.out.println(answer); // It was last updated on March 9, 2013
    // tag::adocSkip[]
    Thread.sleep(5000);

    answer = assistant.chat("And what about the TERMS AND CONDITIONS");
    System.out.println(answer); // Last updated on June 19, 2014
    // end::adocSkip[]
    // end::adocTools[]
  }
}
