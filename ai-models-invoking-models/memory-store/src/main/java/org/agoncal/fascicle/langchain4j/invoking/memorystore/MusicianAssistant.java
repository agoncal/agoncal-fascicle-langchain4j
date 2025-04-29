package org.agoncal.fascicle.langchain4j.invoking.memorystore;

// tag::adocSnippet[]

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) throws InterruptedException {
//    useRedisChatMemoryStore();
    useRedisChatMemoryStoreQuestion();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // ###############################
  // ### REDIS CHAT MEMORY STORE ###
  // ###############################
  private static void useRedisChatMemoryStore() throws InterruptedException {
    System.out.println("### useRedisChatMemoryStore");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .logRequests(true)
      .logResponses(true)
      .build();

    // tag::adocChatMemoryStore[]
    ChatMemoryStore memoryStore = RedisChatMemoryStore.builder()
      .host("localhost")
      .port(6379)
      .build();

    ChatMemory chatMemory = MessageWindowChatMemory.builder()
      .maxMessages(20)
      .chatMemoryStore(memoryStore)
      .build();

    chatMemory.add(new UserMessage("My name is Antonio"));
    AiMessage answer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(answer);
    System.out.println(answer.text());
    // end::adocChatMemoryStore[]
  }

  private static void useRedisChatMemoryStoreQuestion() throws InterruptedException {
    System.out.println("### useRedisChatMemoryStoreLastQuestion");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .build();

    // tag::adocChatMemoryStoreQuestion[]
    ChatMemoryStore memoryStore = RedisChatMemoryStore.builder()
      .host("localhost")
      .port(6379)
      .build();

    ChatMemory chatMemory = MessageWindowChatMemory.builder()
      .maxMessages(20)
      .chatMemoryStore(memoryStore)
      .build();

    chatMemory.add(new UserMessage("What's my name?"));
    AiMessage answer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(answer);
    System.out.println(answer.text());
    // end::adocChatMemoryStoreQuestion[]
  }
}
