package org.agoncal.fascicle.langchain4j.embedding.openai;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.output.Response;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

    musicianAssistant.textToEmbeddingOpenAI();
  }

  public void textToEmbeddingOpenAI() {
    System.out.println("### textToEmbeddingOpenAI");

    // tag::adocTextToEmbeddingOpenAI[]
    EmbeddingModel model = OpenAiEmbeddingModel.withApiKey(OPENAI_API_KEY);

    TextSegment segment = TextSegment.from("Isaac Asimov is a writer and a biochemist");
    Response<Embedding> embedding = model.embed(segment);

    System.out.println(embedding.content());
    // end::adocTextToEmbeddingOpenAI[]
  }
}
