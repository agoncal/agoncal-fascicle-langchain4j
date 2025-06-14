package org.agoncal.fascicle.langchain4j.embedding.openai;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.openai.OpenAiEmbeddingModelName.TEXT_EMBEDDING_3_LARGE;
import static dev.langchain4j.model.openai.OpenAiEmbeddingModelName.TEXT_EMBEDDING_3_SMALL;

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
    textToEmbeddingOpenAI();
    textToEmbeddingOpenAILarge();
    textToEmbeddingOpenAISegment();
  }

  static void textToEmbeddingOpenAI() {
    System.out.println("### textToEmbeddingOpenAI");

    // tag::adocTextToEmbeddingOpenAI[]
    EmbeddingModel model = OpenAiEmbeddingModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(TEXT_EMBEDDING_3_SMALL)
      .build();

    Response<Embedding> embedding = model.embed("Isaac Asimov is a writer and a biochemist");

    System.out.println(embedding.content());
    // end::adocTextToEmbeddingOpenAI[]
  }

  static void textToEmbeddingOpenAILarge() {
    System.out.println("### textToEmbeddingOpenAILarge");

    // tag::adocTextToEmbeddingOpenAILarge[]
    EmbeddingModel model = OpenAiEmbeddingModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(TEXT_EMBEDDING_3_LARGE)
      .build();

    Response<Embedding> embedding = model.embed("Isaac Asimov is a writer and a biochemist");

    System.out.println(embedding.content());
    // end::adocTextToEmbeddingOpenAILarge[]
  }

  static void textToEmbeddingOpenAISegment() {
    System.out.println("### textToEmbeddingOpenAISegment");

    EmbeddingModel model = OpenAiEmbeddingModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(TEXT_EMBEDDING_3_SMALL)
      .build();

    // tag::adocTextToEmbeddingOpenAISegment[]
    TextSegment segment = TextSegment.from("Isaac Asimov is a writer and a biochemist");
    Response<Embedding> embedding = model.embed(segment);
    // end::adocTextToEmbeddingOpenAISegment[]

    System.out.println(embedding.content());
  }
}
