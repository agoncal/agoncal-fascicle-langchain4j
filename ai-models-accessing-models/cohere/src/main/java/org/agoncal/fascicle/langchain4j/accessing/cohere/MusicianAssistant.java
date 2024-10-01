package org.agoncal.fascicle.langchain4j.accessing.cohere;

import dev.langchain4j.model.cohere.CohereScoringModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.scoring.ScoringModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

    useCohereScoringModel();
  }

  private static final String COHERE_API_KEY = System.getenv("COHERE_API_KEY");

  public static void useCohereScoringModel() {
    System.out.println("### useCohereScoringModel");

    // tag::adocCohereScoringModel[]
    String bio = """
      Brooklyn native, she is a groundbreaking figure in speculative fiction.
      Her debut novel, "The Hundred Thousand Kingdoms," launched her into literary stardom
      with its intricate world-building and exploration of power dynamics.
      Her Broken Earth trilogy, starting with "The Fifth Season," further solidified
      her reputation, winning consecutive Hugo Awards for Best Novel—a first in the genre.
      """;
    ScoringModel model = CohereScoringModel.builder()
      .apiKey(COHERE_API_KEY)
      .build();

    Response<Double> score = model.score(bio, "Is this Nora Jemisin?");
    System.out.println(score.content()); // 0.8625833

    score = model.score(bio, "Is this John Lennon?");
    System.out.println(score.content()); // 0.06198946
    // end::adocCohereScoringModel[]
  }
}
