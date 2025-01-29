package org.agoncal.fascicle.langchain4j.vectordb.pgvector;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static final String USERNAME = "neo4j";
  public static final String PASSWORD = "adminPass";

  public static void main(String[] args) {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

    musicianAssistant.usePGVectorToStoreEmbeddings();
  }

  public void usePGVectorToStoreEmbeddings() {
    System.out.println("### usePGVectorToStoreEmbeddings");

    // tag::adocPGVectorToStoreEmbeddings[]
    EmbeddingStore<TextSegment> embeddingStore = PgVectorEmbeddingStore.builder()
        .host("localhost")
        .port(5432)
        .createTable(true)
        .dropTableFirst(true)
        .dimension(384)
        .table("vintagestore_collection")
        .user(USERNAME)
        .password(PASSWORD)
        .database("vintagestore_db")
        .build();
    // end::adocPGVectorToStoreEmbeddings[]

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("I've been to France twice.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("New Delhi is the capital of India.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    Embedding queryEmbedding = embeddingModel.embed("Did you ever travel abroad?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(queryEmbedding)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }
}
