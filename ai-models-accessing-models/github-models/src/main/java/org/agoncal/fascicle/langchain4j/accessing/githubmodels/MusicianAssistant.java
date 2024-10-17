package org.agoncal.fascicle.langchain4j.accessing.githubmodels;

import dev.langchain4j.model.github.GitHubModelsChatModel;
import static dev.langchain4j.model.github.GitHubModelsChatModelName.GPT_4_O_MINI;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) {
    useGitHubModelsChatModel();
  }

  private static final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN");

  // ################################
  // ### GITHUB MODELS CHAT MODEL ###
  // ################################
  private static void useGitHubModelsChatModel() {
    System.out.println("### useGitHubModelsChatModel");

    // tag::adocSnippet[]
    GitHubModelsChatModel model = GitHubModelsChatModel.builder()
      .gitHubToken(GITHUB_TOKEN)
      .modelName(GPT_4_O_MINI)
      // tag::adocSkip[]
      .logRequestsAndResponses(true)
      // end::adocSkip[]
      .build();

    System.out.println(model.generate("List some influential Jazz musicians"));
    // end::adocSnippet[]
  }
}
