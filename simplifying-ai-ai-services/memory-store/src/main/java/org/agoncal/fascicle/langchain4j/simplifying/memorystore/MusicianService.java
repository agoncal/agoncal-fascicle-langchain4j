package org.agoncal.fascicle.langchain4j.simplifying.memorystore;

// tag::adocSnippet[]

import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianService {

  public static void main(String[] args) throws InterruptedException {
//    useChatMemoryStore();
    useChatMemoryStoreLastQuestion();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // #########################
  // ### CHAT MEMORY STORE ###
  // #########################
  private static void useChatMemoryStore() throws InterruptedException {
    System.out.println("### useChatMemoryStore");

    // tag::adocChatMemoryStore[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      .temperature(0.3)
      .build();

    ChatMemoryStore memoryStore = RedisChatMemoryStore.builder()
      .host("localhost")
      .port(6379)
      .build();

    ChatMemory chatMemory = MessageWindowChatMemory.builder()
      .maxMessages(20)
      .chatMemoryStore(memoryStore)
      .build();

    MusicianAssistant assistant = AiServices.builder(MusicianAssistant.class)
      .chatModel(model)
      .chatMemory(chatMemory)
      .build();

    System.out.println(assistant.chat("My name is Antonio")); // Nice to meet you, Antonio!
    // end::adocChatMemoryStore[]
  }

  private static void useChatMemoryStoreLastQuestion() throws InterruptedException {
    System.out.println("### useChatMemoryStoreLastQuestion");

    // tag::adocChatMemoryStoreLastQuestion[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      .temperature(0.3)
      .build();

    ChatMemoryStore memoryStore = RedisChatMemoryStore.builder()
      .host("localhost")
      .port(6379)
      .build();

    ChatMemory chatMemory = MessageWindowChatMemory.builder()
      .maxMessages(20)
      .chatMemoryStore(memoryStore)
      .build();

    MusicianAssistant assistant = AiServices.builder(MusicianAssistant.class)
      .chatModel(model)
      .chatMemory(chatMemory)
      .build();

    System.out.println(assistant.chat("What's my name?")); // Nice to meet you, Antonio!
    // end::adocChatMemoryStoreLastQuestion[]
  }
}
