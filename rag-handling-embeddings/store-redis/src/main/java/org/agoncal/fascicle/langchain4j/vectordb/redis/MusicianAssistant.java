package org.agoncal.fascicle.langchain4j.vectordb.redis;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;

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

    musicianAssistant.useRedisToStoreEmbeddings();
  }

  public void useRedisToStoreEmbeddings() {
    System.out.println("### useRedisToStoreEmbeddings");

    // tag::adocRedisToStoreEmbeddingsConnect[]
    EmbeddingStore<TextSegment> embeddingStore = RedisEmbeddingStore.builder()
      .host("http://localhost")
      .port(6334)
      .indexName("vintagestore_idx")
      .dimension(384)
      .metadataKeys(createMetadata().toMap().keySet())
      .build();
    // end::adocRedisToStoreEmbeddingsConnect[]

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

  protected Metadata createMetadata() {

    Metadata metadata = new Metadata();

    metadata.put("string_empty", "");
    metadata.put("string_space", " ");
    metadata.put("string_abc", "abc");

    metadata.put("integer_min", Integer.MIN_VALUE);
    metadata.put("integer_minus_1", -1);
    metadata.put("integer_0", 0);
    metadata.put("integer_1", 1);
    metadata.put("integer_max", Integer.MAX_VALUE);

    metadata.put("long_min", Long.MIN_VALUE);
    metadata.put("long_minus_1", -1L);
    metadata.put("long_0", 0L);
    metadata.put("long_1", 1L);
    metadata.put("long_max", Long.MAX_VALUE);

    metadata.put("float_min", -Float.MAX_VALUE);
    metadata.put("float_minus_1", -1f);
    metadata.put("float_0", Float.MIN_VALUE);
    metadata.put("float_1", 1f);
    metadata.put("float_123", 1.23456789f);
    metadata.put("float_max", Float.MAX_VALUE);

    metadata.put("double_minus_1", -1d);
    metadata.put("double_0", Double.MIN_VALUE);
    metadata.put("double_1", 1d);
    metadata.put("double_123", 1.23456789d);

    return metadata;
  }

}
