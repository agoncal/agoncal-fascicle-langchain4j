package org.agoncal.fascicle.langchain4j.testingdebugging;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;
import static java.time.Duration.ofSeconds;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class AuthorService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  private String[] scifiAuthors = {"Isaac Asimov", "Nora Jemisin", "Douglas Adams"};

  String url;
  public AuthorService(/*String url*/) {
    this.url = url;
  }

  public String getAuthorBiography(int index) {

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      //.baseUrl(this.url)
      //.proxy("http://localhost:3128")
      .modelName(GPT_3_5_TURBO)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    String prompt = "Write a short biography about " + scifiAuthors[index];

    String biography = model.generate(prompt);

    return biography;
  }

  public static void main(String[] args) {
    AuthorService authorService = new AuthorService();
    System.out.println(authorService.getAuthorBiography(0));
  }
}
// end::adocSnippet[]
