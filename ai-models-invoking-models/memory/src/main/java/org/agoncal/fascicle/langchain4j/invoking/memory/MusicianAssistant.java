package org.agoncal.fascicle.langchain4j.invoking.memory;

// tag::adocSnippet[]

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) throws InterruptedException {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

//    useNoMemory();
//    sendingOneMessage();
//    sendingTwoMessages();
//    sendingThreeMessages();
//    useChatMemory();
    useChatMemoryWithSystemMessage();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // #################
  // ### NO MEMORY ###
  // #################
  private static void useNoMemory() throws InterruptedException {
    System.out.println("### useNoMemory");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);
    // tag::adocNoMemory[]
    System.out.println(model.generate("My name is Antonio"));
    // Nice to meet you, Antonio! How can I assist you today?
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("My favourite Rock band is the Beatles"));
    // That's a great choice! The Beatles are a legendary rock band.
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("When was their first album released?"));
    // Their first album, "Parachutes," was released on July 10, 2000.
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("What's the name of the singer?"));
    // I'm not sure, could you please provide more context or details?
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("What's my name?"));
    // I'm sorry, I don't know your name. Can you please tell me?
    // end::adocNoMemory[]
  }

  // #################################
  // ### SENDING MULTIPLE MESSAGES ###
  // #################################
  private static void sendingOneMessage() {
    System.out.println("### sendingOneMessage");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocOneMessage[]
    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    AiMessage firstAnswer = model.generate(firstMsg).content();

    System.out.println(firstAnswer.text());
    // Nice to meet you Antonio! How can I assist you today?
    // end::adocOneMessage[]
  }

  private static void sendingTwoMessages() throws InterruptedException {
    System.out.println("### sendingTwoMessages");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocTwoMessages[]
    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    AiMessage firstAnswer = model.generate(firstMsg).content();
    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    AiMessage secondAnswer = model.generate(firstMsg, firstAnswer, secondMsg).content();

    System.out.println(secondAnswer.text());
    // The Beatles are a legendary rock band
    // end::adocTwoMessages[]
  }

  private static void sendingThreeMessages() throws InterruptedException {
    System.out.println("### sendingThreeMessages");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocThreeMessages[]
    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    AiMessage firstAnswer = model.generate(firstMsg).content();
    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    AiMessage secondAnswer = model.generate(secondMsg).content();
    UserMessage thirdMsg = UserMessage.from("What's my name?");
    AiMessage thirdAnswer = model.generate(firstMsg, firstAnswer, secondMsg, secondAnswer, thirdMsg).content();

    System.out.println(thirdAnswer.text());
    // Your name is Antonio.
    // end::adocThreeMessages[]
  }

  // ###################
  // ### CHAT MEMORY ###
  // ####################
  private static void useChatMemory() throws InterruptedException {
    System.out.println("### useChatMemory");

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .logRequests(true)
      .logResponses(true)
      .build();

    // tag::adocChatMemory[]
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    chatMemory.add(firstMsg);
    AiMessage firstAnswer = model.generate(chatMemory.messages()).content();
    chatMemory.add(firstAnswer);
    System.out.println(firstAnswer.text()); // Nice to meet you, Antonio!
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    chatMemory.add(secondMsg);
    AiMessage secondAnswer = model.generate(chatMemory.messages()).content();
    chatMemory.add(secondAnswer);
    System.out.println(secondAnswer.text()); // That's a great choice!
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage thirdMsg = UserMessage.from("When was their first album released?");
    chatMemory.add(thirdMsg);
    AiMessage thirdAnswer = model.generate(chatMemory.messages()).content();
    chatMemory.add(thirdAnswer);
    System.out.println(thirdAnswer.text()); // "Please Please Me" released on March 22, 1963
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage forthMsg = UserMessage.from("What's the name of the singer?");
    chatMemory.add(forthMsg);
    AiMessage forthAnswer = model.generate(chatMemory.messages()).content();
    chatMemory.add(forthAnswer);
    System.out.println(forthAnswer.text()); // John Lennon and Paul McCartney
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage fifthMsg = UserMessage.from("What's my name?");
    chatMemory.add(fifthMsg);
    AiMessage fifthAnswer = model.generate(chatMemory.messages()).content();
    chatMemory.add(fifthAnswer);
    System.out.println(fifthAnswer.text()); // Your name is Antonio, as you mentioned earlier
    // end::adocChatMemory[]
  }

  private static void useChatMemoryWithSystemMessage() throws InterruptedException {
    System.out.println("### useChatMemoryWithSystemMessage");

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .logRequests(true)
      .logResponses(true)
      .build();

    // tag::adocChatMemorySystem[]
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    chatMemory.add(new SystemMessage("You are a Vintage Store chat bot."));

    chatMemory.add(new UserMessage("My name is Antonio"));
    AiMessage firstAnswer = model.generate(chatMemory.messages()).content();
    chatMemory.add(firstAnswer);
    // tag::adocSkip[]
    System.out.println(firstAnswer.text());
    Thread.sleep(5000);
    // end::adocSkip[]

    chatMemory.add(new UserMessage("What's my name?"));
    AiMessage secondAnswer = model.generate(chatMemory.messages()).content();
    chatMemory.add(secondAnswer);
    // end::adocChatMemorySystem[]
    System.out.println(secondAnswer.text());
  }
}
