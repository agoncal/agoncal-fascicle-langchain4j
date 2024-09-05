package org.agoncal.fascicle.langchain4j.vectordb.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.mongodb.IndexMapping;
import dev.langchain4j.store.embedding.mongodb.MongoDbEmbeddingStore;
import org.apache.commons.compress.utils.Sets;

import java.util.List;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  static MongoClient client;

  IndexMapping indexMapping = IndexMapping.builder()
    .dimension(384)
    .metadataFieldNames(Sets.newHashSet("test-key"))
    .build();


  public static void main(String[] args) {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

    musicianAssistant.useMongoToStoreEmbeddings();
  }

  public void useMongoToStoreEmbeddings() {
    System.out.println("### useMongoToStoreEmbeddings");

    // tag::adocMongoToStoreEmbeddings[]
    EmbeddingStore<TextSegment> embeddingStore = MongoDbEmbeddingStore.builder()
      .fromClient(client)
      .databaseName("vintagestore_db")
      .collectionName("vintagestore_coll")
      .indexName("vintagestore_index")
      .filter(Filters.and(Filters.eqFull("metadata.key", "miles")))
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
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }
}
