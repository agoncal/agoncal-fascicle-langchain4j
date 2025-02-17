package org.agoncal.fascicle.langchain4j.accessing.deepseek;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.time.Duration;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private static final String DEEPSEEK_API_KEY = System.getenv("DEEPSEEK_API_KEY");

  public static void main(String[] args) {
    useOpenAIForDeepSeek();
  }

  // #############################
  // ### DEEPSEEK CHAT MODEL ###
  // #############################
  static void useOpenAIForDeepSeek() {
    System.out.println("### useOpenAIForDeepSeek");

    // tag::adocUseOpenAIForDeepSeek[]
    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(DEEPSEEK_API_KEY)
      .baseUrl("https://api.deepseek.com")
      .modelName("deepseek-chat")
      .temperature(1.3)
      .timeout(Duration.ofSeconds(60))
      .topP(2d)
      .maxTokens(1000)
      .build();

    String answer = model.chat("When was the first Beatles album released?");

    System.out.println(answer);
    // end::adocUseOpenAIForDeepSeek[]
  }
}
