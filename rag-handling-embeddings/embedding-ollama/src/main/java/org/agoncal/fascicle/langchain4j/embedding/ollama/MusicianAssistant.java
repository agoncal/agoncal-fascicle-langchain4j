package org.agoncal.fascicle.langchain4j.embedding.ollama;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.output.Response;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public Response<Embedding> textToEmbeddingOllama() {
    System.out.println("### textToEmbeddingOllama");

    // tag::adocTextToEmbeddingOllama[]
    EmbeddingModel model = OllamaEmbeddingModel.builder()
      .baseUrl("http://localhost:11434")
      .modelName("mxbai-embed-large")
      .build();

    TextSegment segment = TextSegment.from("Isaac Asimov is a writer and a biochemist");
    Response<Embedding> embedding = model.embed(segment);

    System.out.println(embedding.content());
    // end::adocTextToEmbeddingOllama[]
    return embedding;
  }
}
