package org.agoncal.fascicle.langchain4j.invoking.memory;

// tag::adocSnippet[]

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.agoncal.fascicle.langchain4j.simplifying.memory.MusicianAssistant;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianService {

  public static void main(String[] args) throws InterruptedException {
    MusicianService musicianAssistant = new MusicianService();

    musicianAssistant.useChatMemory();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // ###################
  // ### CHAT MEMORY ###
  // ####################
  public void useChatMemory() throws InterruptedException {
    System.out.println("### useChatMemory");

    // tag::adocChatMemory[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.3)
      .build();

    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    MusicianAssistant assistant = AiServices.builder(MusicianAssistant.class)
      .chatLanguageModel(model)
      .chatMemory(chatMemory)
      .build();

    String answer = assistant.chat("My name is Antonio");
    System.out.println(answer); // Nice to meet you, Antonio!
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    answer = assistant.chat("My favourite Rock band is the Beatles");
    System.out.println(answer); // That's a fantastic choice!
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    answer = assistant.chat("When was their first album released?");
    System.out.println(answer); // "Please Please Me" was released on March 22, 1963
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    answer = assistant.chat("What's the name of the singer?");
    System.out.println(answer); // Two primary lead vocalists: John Lennon and Paul McCartney
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    answer = assistant.chat("What's my name?");
    System.out.println(answer); // Your name is Antonio
    // end::adocChatMemory[]
  }
}
