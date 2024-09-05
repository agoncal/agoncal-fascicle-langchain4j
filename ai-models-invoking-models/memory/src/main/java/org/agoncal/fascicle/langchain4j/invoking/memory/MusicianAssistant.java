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
    musicianAssistant.sendingMultipleMessages();
//    musicianAssistant.useChatMemory();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // #################
  // ### NO MEMORY ###
  // #################
  public void useNoMemory() throws InterruptedException {
    System.out.println("### useNoMemory");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);
    // tag::adocNoMemory[]
    UserMessage msg = UserMessage.from("My name is Antonio");
    System.out.println(model.generate(msg));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    msg = UserMessage.from("My favourite Rock band is the Beatles");
    System.out.println(model.generate(msg));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    msg = UserMessage.from("When was their first album released?");
    System.out.println(model.generate(msg));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    msg = UserMessage.from("What's the name of the singer?");
    System.out.println(model.generate(msg));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    msg = UserMessage.from("What's my name?");
    System.out.println(model.generate(msg));
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

    UserMessage firstMessage = UserMessage.from("My name is Antonio");
    chatMemory.add(firstMessage);
    AiMessage answer1 = model.generate(chatMemory.messages()).content();
    System.out.println(answer1.text());
    chatMemory.add(answer1);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage secondMessage = UserMessage.from("My favourite Rock band is the Beatles");
    chatMemory.add(secondMessage);
    AiMessage answer2 = model.generate(chatMemory.messages()).content();
    System.out.println(answer2.text());
    chatMemory.add(answer2);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage thirdMessage = UserMessage.from("When was their first album released?");
    chatMemory.add(thirdMessage);
    AiMessage answer3 = model.generate(chatMemory.messages()).content();
    System.out.println(answer3.text());
    chatMemory.add(answer3);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage forthMessage = UserMessage.from("What's the name of the singer?");
    chatMemory.add(forthMessage);
    AiMessage answer4 = model.generate(chatMemory.messages()).content();
    System.out.println(answer4.text());
    chatMemory.add(answer4);
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]

    UserMessage fifthMessage = UserMessage.from("What's my name?");
    chatMemory.add(fifthMessage);
    AiMessage answer5 = model.generate(chatMemory.messages()).content();
    System.out.println(answer5.text());
    chatMemory.add(answer5);
    // end::adocChatMemory[]
  }
}
