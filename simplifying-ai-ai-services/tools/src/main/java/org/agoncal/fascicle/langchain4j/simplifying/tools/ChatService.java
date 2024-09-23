package org.agoncal.fascicle.langchain4j.simplifying.tools;

// tag::adocHeader[]

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.DefaultToolExecutor;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.service.tool.ToolExecutor;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import static java.time.Duration.ofSeconds;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatService {

  public static void main(String[] args) throws Exception {

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName(GPT_4_O)
      .build();

    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    ChatAssistant assistant = AiServices.builder(ChatAssistant.class)
      .chatLanguageModel(model)
      .chatMemory(chatMemory)
      .tools(new LegalDocumentTools())
      .build();

    String answer = assistant.chat("When was the PRIVACY document updated?");
    System.out.println(answer); // It was last updated on March 9, 2013
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    answer = assistant.chat("And what about the TERMS AND CONDITIONS");
    System.out.println(answer); // Last updated on June 19, 2014
    // tag::adocSkip[]
  }
}
