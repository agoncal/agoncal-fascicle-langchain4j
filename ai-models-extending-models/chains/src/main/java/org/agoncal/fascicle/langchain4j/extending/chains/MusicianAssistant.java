package org.agoncal.fascicle.langchain4j.extending.chains;

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class MusicianAssistant {

  public static void main(String[] args) throws InterruptedException {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

    musicianAssistant.useConversationalChain();
    musicianAssistant.useConversationalChainWithMemory();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // ################################
  // ### USE CONVERSATIONAL CHAIN ###
  // ################################
  public void useConversationalChain() throws InterruptedException {
    System.out.println("### useConversationalChain");

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    // tag::adocChain[]
    ConversationalChain chain = ConversationalChain.builder()
      .chatLanguageModel(model)
      .build();

    System.out.println(chain.execute("My name is Antonio"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("My favourite Rock band is the Rolling Stones"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("When was their first album released?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("What's the name of the singer?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("What's my name?"));
    // end::adocChain[]
  }


  public void useConversationalChainWithMemory() throws InterruptedException {
    System.out.println("### useConversationalChainWithMemory");

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    // tag::adocChainMemory[]
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    ConversationalChain chain = ConversationalChain.builder()
      .chatLanguageModel(model)
      .chatMemory(chatMemory)
      .build();
    // end::adocChainMemory[]

    System.out.println(chain.execute("My name is Antonio"));
    Thread.sleep(5000);
    System.out.println(chain.execute("My favourite Rock band is the Rolling Stones"));
    Thread.sleep(5000);
    System.out.println(chain.execute("When was their first album released?"));
    Thread.sleep(5000);
    System.out.println(chain.execute("What's the name of the singer?"));
    Thread.sleep(5000);
    System.out.println(chain.execute("What's my name?"));
  }
}
