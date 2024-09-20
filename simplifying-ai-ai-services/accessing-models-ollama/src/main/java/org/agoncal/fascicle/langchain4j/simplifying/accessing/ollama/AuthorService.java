package org.agoncal.fascicle.langchain4j.simplifying.accessing.ollama;

import static java.lang.System.exit;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class AuthorService {

  public static void main(String[] args) {
    String authorName = args[0];

    OllamaChatModel model = OllamaChatModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("phi3:mini")
      .build();

    AuthorAssistant assistant = AiServices.create(AuthorAssistant.class, model);

    String answer = assistant.generateAuthorBiography(authorName);
    System.out.println(answer);
    exit(0);
  }
}
