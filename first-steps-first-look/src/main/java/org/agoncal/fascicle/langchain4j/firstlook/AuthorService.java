package org.agoncal.fascicle.langchain4j.firstlook;

import static java.lang.System.exit;

// tag::adocSnippet[]
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class AuthorService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {
    String authorName = args[0];

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1)
      .temperature(0.3)
      .build();

    AuthorAssistant assistant = AiServices.create(AuthorAssistant.class, model);

    String answer = assistant.generateAuthorBiography(authorName);
    System.out.println(answer);
    // tag::adocSkip[]
    exit(0);
    // end::adocSkip[]
  }
}
// end::adocSnippet[]
