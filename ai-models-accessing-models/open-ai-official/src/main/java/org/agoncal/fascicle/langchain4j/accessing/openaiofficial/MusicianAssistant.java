package org.agoncal.fascicle.langchain4j.accessing.openaiofficial;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openaiofficial.OpenAiOfficialChatModel;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class MusicianAssistant {

  public static void main(String[] args) throws JsonProcessingException {

    useOpenAIOfficial();

  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  private static void useOpenAIOfficial() {
    System.out.println("### useOpenAIOfficial");

    // tag::adocSnippet[]
    ChatModel model = OpenAiOfficialChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(com.openai.models.ChatModel.GPT_4_1)
      .build();

    String answer = model.chat("When was the first Beatles album released?");

    System.out.println(answer);
    // end::adocSnippet[]
  }
}
