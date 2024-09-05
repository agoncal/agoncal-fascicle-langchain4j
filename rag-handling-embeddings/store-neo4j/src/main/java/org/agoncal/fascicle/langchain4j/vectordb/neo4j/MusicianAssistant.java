package org.agoncal.fascicle.langchain4j.vectordb.neo4j;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.neo4j.Neo4jEmbeddingStore;

import java.util.List;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static final String USERNAME = "neo4j";
  public static final String ADMIN_PASSWORD = "adminPass";


  public static void main(String[] args) {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

    musicianAssistant.useNeo4jToStoreEmbeddings();
  }

  public void useNeo4jToStoreEmbeddings() {
    System.out.println("### useNeo4jToStoreEmbeddings");

    // tag::adocNeo4jToStoreEmbeddingsConnect[]
    EmbeddingStore<TextSegment> embeddingStore = Neo4jEmbeddingStore.builder()
      .withBasicAuth("http://localhost:7474", USERNAME, ADMIN_PASSWORD)
      .dimension(384)
      .metadataPrefix("metadata.")
      .label("vintagestore_label")
      .indexName("vintagestore_idx")
      .build();
    // end::adocNeo4jToStoreEmbeddingsConnect[]

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("I've been to France twice.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("New Delhi is the capital of India.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    Embedding queryEmbedding = embeddingModel.embed("Did you ever travel abroad?").content();
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }
}
