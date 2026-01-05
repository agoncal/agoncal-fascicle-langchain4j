package org.agoncal.fascicle.langchain4j.vectordb.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.mongodb.IndexMapping;
import dev.langchain4j.store.embedding.mongodb.MongoDbEmbeddingStore;
import org.apache.commons.compress.utils.Sets;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private MongoClient client;

  private IndexMapping indexMapping = IndexMapping.builder()
    .dimension(384)
    .metadataFieldNames(Sets.newHashSet("test-key"))
    .build();

  public EmbeddingMatch<TextSegment> useMongoToStoreEmbeddings() {
    System.out.println("### useMongoToStoreEmbeddings");

    // tag::adocMongoToStoreEmbeddings[]
    EmbeddingStore<TextSegment> embeddingStore = MongoDbEmbeddingStore.builder()
      .fromClient(client)
      .databaseName("vintagestore_db")
      .collectionName("vintagestore_coll")
      .indexName("vintagestore_index")
      .filter(Filters.and(Filters.eq("metadata.key", "miles")))
      .indexMapping(indexMapping)
      .createIndex(true)
      .build();
    // end::adocMongoToStoreEmbeddings[]

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("I've been to France twice.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("New Delhi is the capital of India.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    Embedding queryEmbedding = embeddingModel.embed("Did you ever travel abroad?").content();
    EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
      .queryEmbedding(queryEmbedding)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(searchRequest);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
    return embeddingMatch;
  }
}
