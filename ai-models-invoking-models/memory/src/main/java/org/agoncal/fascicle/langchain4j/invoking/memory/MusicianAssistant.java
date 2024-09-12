package org.agoncal.fascicle.langchain4j.invoking.memory;

// tag::adocSnippet[]

import dev.langchain4j.data.message.AiMessage;
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

//    musicianAssistant.useNoMemory();
//    musicianAssistant.sendingMultipleMessages();
    musicianAssistant.useChatMemory();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // #################
  // ### NO MEMORY ###
  // #################
  public void useNoMemory() throws InterruptedException {
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
  public void sendingMultipleMessages() throws InterruptedException {
    System.out.println("### sendingMultipleMessages");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocMultipleMessages[]
    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    UserMessage thirdMsg = UserMessage.from("When was their first album released?");
    UserMessage forthMsg = UserMessage.from("What's the name of the singer?");
    UserMessage fifthMsg = UserMessage.from("What's my name?");

    System.out.println(model.generate(firstMsg, secondMsg, thirdMsg, forthMsg, fifthMsg));
    // Your name is Antonio
    // end::adocMultipleMessages[]
  }

  // ###################
  // ### CHAT MEMORY ###
  // ####################
  public void useChatMemory() throws InterruptedException {
    System.out.println("### useChatMemory");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocChatMemory[]
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    UserMessage firstMsg = UserMessage.from("My name is Antonio");
    chatMemory.add(firstMsg);
    AiMessage firstAnswer = model.generate(chatMemory.messages()).content();
    System.out.println(firstAnswer.text());
    chatMemory.add(firstAnswer);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage secondMsg = UserMessage.from("My favourite Rock band is the Beatles");
    chatMemory.add(secondMsg);
    AiMessage secondAnswer = model.generate(chatMemory.messages()).content();
    System.out.println(secondAnswer.text());
    chatMemory.add(secondAnswer);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage thirdMsg = UserMessage.from("When was their first album released?");
    chatMemory.add(thirdMsg);
    AiMessage thirdAnswer = model.generate(chatMemory.messages()).content();
    System.out.println(thirdAnswer.text());
    chatMemory.add(thirdAnswer);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage forthMsg = UserMessage.from("What's the name of the singer?");
    chatMemory.add(forthMsg);
    AiMessage forthAnswer = model.generate(chatMemory.messages()).content();
    System.out.println(forthAnswer.text());
    chatMemory.add(forthAnswer);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage fifthMsg = UserMessage.from("What's my name?");
    chatMemory.add(fifthMsg);
    AiMessage fifthAnswer = model.generate(chatMemory.messages()).content();
    System.out.println(fifthAnswer.text());
    chatMemory.add(fifthAnswer);
    // end::adocChatMemory[]
  }
}
