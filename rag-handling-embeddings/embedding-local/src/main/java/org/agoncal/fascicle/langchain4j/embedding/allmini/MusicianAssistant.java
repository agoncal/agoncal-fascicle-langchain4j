package org.agoncal.fascicle.langchain4j.embedding.allmini;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallen.BgeSmallEnEmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

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

//    musicianAssistant.textToEmbeddingAllMiniLmL6V2();
//    musicianAssistant.textToEmbeddingBgeSmall();
    musicianAssistant.cosineSimilarityBetween();
  }

  public void textToEmbeddingAllMiniLmL6V2() {
    System.out.println("### textToEmbeddingAllMiniLmL6V2");

    // tag::adocTextToEmbeddingAllMiniLmL6V2[]
    EmbeddingModel model = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment = TextSegment.from("Isaac Asimov is a writer and a biochemist");
    Response<Embedding> embedding = model.embed(segment);

    System.out.println(embedding.content());
    // end::adocTextToEmbeddingAllMiniLmL6V2[]
  }

  public void textToEmbeddingBgeSmall() {
    System.out.println("### textToEmbeddingBgeSmall");

    // tag::adocTextToEmbeddingBgeSmall[]
    EmbeddingModel model = new BgeSmallEnEmbeddingModel();

    TextSegment segment = TextSegment.from("Isaac Asimov is a writer and a biochemist");
    Response<Embedding> embedding = model.embed(segment);

    System.out.println(embedding.content());
    // end::adocTextToEmbeddingBgeSmall[]
  }

  public void cosineSimilarityBetween() {
    System.out.println("### cosineSimilarityBetween");

    EmbeddingModel model = new BgeSmallEnEmbeddingModel();

    // tag::adocCosineSimilarityBetween[]
    Embedding isaacAsimov = model.embed("Isaac Asimov").content();
    Embedding noraJemisin = model.embed("Nora Jemisin").content();
    Embedding fenderGuitar = model.embed("Fender Guitar").content();
    Embedding table = model.embed("Table").content();

    CosineSimilarity.between(isaacAsimov, noraJemisin);   // 0.7988
    CosineSimilarity.between(isaacAsimov, fenderGuitar);  // 0.7378
    CosineSimilarity.between(isaacAsimov, table);         // 0.7632
    // end::adocCosineSimilarityBetween[]

    System.out.println(CosineSimilarity.between(isaacAsimov, noraJemisin));
    System.out.println(CosineSimilarity.between(isaacAsimov, fenderGuitar));
    System.out.println(CosineSimilarity.between(isaacAsimov, table));
  }
}
