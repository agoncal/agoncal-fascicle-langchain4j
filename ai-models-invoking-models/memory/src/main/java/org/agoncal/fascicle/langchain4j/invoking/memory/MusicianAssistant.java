package org.agoncal.fascicle.langchain4j.invoking.memory;

// tag::adocSnippet[]

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1_MINI;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) throws InterruptedException {
//    useNoMemory();
//    sendingOneMessage();
//    sendingTwoMessages();
//    sendingThreeMessages();
    useChatMemory();
//    useChatMemoryWithSystemMessage();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // #################
  // ### NO MEMORY ###
  // #################
  private static void useNoMemory() throws InterruptedException {
    System.out.println("### useNoMemory");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocNoMemory[]
    System.out.println(model.chat("My name is Antonio"));
    // Nice to meet you, Antonio! How can I assist you today?
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.chat("My favourite Rock band is the Beatles"));
    // That's a great choice! The Beatles are a legendary rock band.
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.chat("When was their first album released?"));
    // Their first album, "Parachutes," was released on July 10, 2000.
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.chat("What's the name of the singer?"));
    // I'm not sure, could you please provide more context or details?
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.chat("What's my name?"));
    // I'm sorry, I don't know your name. Can you please tell me?
    // end::adocNoMemory[]
  }

  // #################################
  // ### SENDING MULTIPLE MESSAGES ###
  // #################################
  private static void sendingOneMessage() {
    System.out.println("### sendingOneMessage");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocOneMessage[]
    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    ChatResponse firstAnswer = model.chat(firstMsg);

    System.out.println(firstAnswer.aiMessage().text());
    // Nice to meet you Antonio! How can I assist you today?
    // end::adocOneMessage[]
  }

  private static void sendingTwoMessages() throws InterruptedException {
    System.out.println("### sendingTwoMessages");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocTwoMessages[]
    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    AiMessage firstAnswer = model.chat(firstMsg).aiMessage();
    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    AiMessage secondAnswer = model.chat(firstMsg, firstAnswer, secondMsg).aiMessage();

    System.out.println(secondAnswer.text());
    // The Beatles are a legendary rock band
    // end::adocTwoMessages[]
  }

  private static void sendingThreeMessages() throws InterruptedException {
    System.out.println("### sendingThreeMessages");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocThreeMessages[]
    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    AiMessage firstAnswer = model.chat(firstMsg).aiMessage();
    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    AiMessage secondAnswer = model.chat(firstMsg, firstAnswer, secondMsg).aiMessage();
    UserMessage thirdMsg = UserMessage.from("What's my name and my favourite band?");
    AiMessage thirdAnswer = model.chat(firstMsg, firstAnswer, secondMsg, secondAnswer, thirdMsg).aiMessage();

    System.out.println(thirdAnswer.text());
    // Your name is Antonio, and your favorite band the Beatles.
    // end::adocThreeMessages[]
  }

  // ###################
  // ### CHAT MEMORY ###
  // ####################
  private static void useChatMemory() throws InterruptedException {
    System.out.println("### useChatMemory");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .logRequests(true)
      .logResponses(true)
      .build();

    // tag::adocChatMemory[]
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    chatMemory.add(firstMsg);
    AiMessage firstAnswer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(firstAnswer);
    System.out.println(firstAnswer.text()); // Nice to meet you, Antonio!
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    chatMemory.add(secondMsg);
    AiMessage secondAnswer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(secondAnswer);
    System.out.println(secondAnswer.text()); // That's a great choice!
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage thirdMsg = UserMessage.from("When was their first album released?");
    chatMemory.add(thirdMsg);
    AiMessage thirdAnswer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(thirdAnswer);
    System.out.println(thirdAnswer.text()); // "Please Please Me" released on 22/03/1963
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage fourthMsg = UserMessage.from("What's the name of the singer?");
    chatMemory.add(fourthMsg);
    AiMessage fourthAnswer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(fourthAnswer);
    System.out.println(fourthAnswer.text()); // John Lennon and Paul McCartney
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage fifthMsg = UserMessage.from("What's my name?");
    chatMemory.add(fifthMsg);
    AiMessage fifthAnswer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(fifthAnswer);
    System.out.println(fifthAnswer.text()); // Antonio, as you mentioned earlier
    // end::adocChatMemory[]
  }

  private static void useChatMemoryWithSystemMessage() throws InterruptedException {
    System.out.println("### useChatMemoryWithSystemMessage");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .logRequests(true)
      .logResponses(true)
      .build();

    // tag::adocChatMemorySystem[]
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    chatMemory.add(new SystemMessage("You are a Vintage Store chat bot."));

    chatMemory.add(new UserMessage("My name is Antonio"));
    AiMessage firstAnswer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(firstAnswer);
    // tag::adocSkip[]
    System.out.println(firstAnswer.text());
    Thread.sleep(5000);
    // end::adocSkip[]

    chatMemory.add(new UserMessage("What's my name?"));
    AiMessage secondAnswer = model.chat(chatMemory.messages()).aiMessage();
    chatMemory.add(secondAnswer);
    // end::adocChatMemorySystem[]
    System.out.println(secondAnswer.text());
  }
}
