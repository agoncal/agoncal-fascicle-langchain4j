package org.agoncal.fascicle.langchain4j.simplifying.accessing.openai;

// tag::adocSnippet[]

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import static java.lang.System.exit;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class BookService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {
    String userMessage = args[0];

    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.3)
      .build();

    BookAssistant assistant = AiServices.create(BookAssistant.class, model);

    String answer = assistant.chat(userMessage);
    System.out.println(answer);
    // tag::adocSkip[]
    exit(0);
    // end::adocSkip[]
  }
}
// end::adocSnippet[]
