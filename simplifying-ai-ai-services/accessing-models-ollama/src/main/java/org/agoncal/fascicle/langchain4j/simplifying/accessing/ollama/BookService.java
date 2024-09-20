package org.agoncal.fascicle.langchain4j.simplifying.accessing.ollama;

// tag::adocSnippet[]

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;

// tag::adocSkip[]
import static java.lang.System.exit;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class BookService {

  public static void main(String[] args) {
    String authorName = args[0];

    ChatLanguageModel model = OllamaChatModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("phi3:mini")
      .build();

    BookAssistant assistant = AiServices.create(BookAssistant.class, model);

    String answer = assistant.chat(authorName);
    System.out.println(answer);
    // tag::adocSkip[]
    exit(0);
    // end::adocSkip[]
  }
}
// end::adocSnippet[]
