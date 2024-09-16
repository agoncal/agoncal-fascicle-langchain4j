package org.agoncal.fascicle.langchain4j.simplifying.accessing.ollama;
// tag::adocSnippet[]

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import static java.lang.System.exit;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class CatalogService {

  public static void main(String[] args) {
    String authorName = args[0];

    OllamaChatModel model = OllamaChatModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("orca-mini")
      .build();

    CatalogAssistant assistant = AiServices.create(CatalogAssistant.class, model);

    String answer = assistant.getAuthorBiography(authorName);
    System.out.println(answer);
    // tag::adocSkip[]
    exit(0);
    // end::adocSkip[]
  }
}
// end::adocSnippet[]
