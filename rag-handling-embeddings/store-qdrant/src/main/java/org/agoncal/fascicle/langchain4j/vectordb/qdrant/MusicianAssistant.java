package org.agoncal.fascicle.langchain4j.vectordb.qdrant;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;

import java.util.ArrayList;
import java.util.List;

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

//    musicianAssistant.useQdrantToStoreEmbeddingsSimple();
//    musicianAssistant.useQdrantToStoreEmbeddingsComplex();
//    musicianAssistant.useQdrantToStoreListEmbeddings();
//    musicianAssistant.useQdrantToStoreAllListEmbeddings();
//    musicianAssistant.useQdrantToStoreJustEmbeddings();
//    musicianAssistant.useQdrantToRemoveEmbeddings();
//    musicianAssistant.useQdrantToRemoveEmbeddings();
    musicianAssistant.useQdrantToRemoveAllEmbeddings();

  }

  public void useQdrantToStoreEmbeddingsConnect() {
    System.out.println("### useQdrantToStoreEmbeddingsConnect");

    // tag::adocQdrantToStoreEmbeddingsConnect[]
    EmbeddingStore<TextSegment> embeddingStore = QdrantEmbeddingStore.builder()
      .collectionName("vintagestore-collection")
      .build();
    // end::adocQdrantToStoreEmbeddingsConnect[]

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("I've been to France twice.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("New Delhi is the capital of India.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    Embedding embeddedQuestion = embeddingModel.embed("Did you ever travel abroad?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }

  public void useQdrantToStoreEmbeddings() {
    System.out.println("### useQdrantToStoreEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    // tag::adocQdrantToStoreEmbeddings[]
    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("Kind of Blue (1959) ...");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("Bitches Brew (1970) ...");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    TextSegment segment3 = TextSegment.from("Sketches of Spain (1960) ...");
    Embedding embedding3 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding3, segment3);
    // end::adocQdrantToStoreEmbeddings[]

    Embedding embeddedQuestion = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }

  public void useQdrantToStoreJustEmbeddings() {
    System.out.println("### useQdrantToStoreJustEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    // tag::adocQdrantToStoreJustEmbeddings[]
    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    Response<Embedding> embedding1 = embeddingModel.embed("Kind of Blue (1959) ...");
    embeddingStore.add(embedding1.content());

    Response<Embedding> embedding2 = embeddingModel.embed("Bitches Brew (1970) ...");
    embeddingStore.add(embedding2.content());

    Response<Embedding> embedding3 = embeddingModel.embed("Sketches of Spain (1960) ...");
    embeddingStore.add(embedding3.content());
    // end::adocQdrantToStoreJustEmbeddings[]

    Embedding embeddedQuestion = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedding().dimension());
  }

  public void useQdrantToStoreJustListEmbeddings() {
    System.out.println("### useQdrantToStoreJustListEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    // tag::adocQdrantToStoreJustListEmbeddings[]
    List<Embedding> embeddings = new ArrayList<>();

    Response<Embedding> embedding1 = embeddingModel.embed("Kind of Blue (1959) ...");
    embeddings.add(embedding1.content());

    Response<Embedding> embedding2 = embeddingModel.embed("Bitches Brew (1970) ...");
    embeddings.add(embedding2.content());

    Response<Embedding> embedding3 = embeddingModel.embed("Sketches of Spain (1960) ...");
    embeddings.add(embedding3.content());

    embeddingStore.addAll(embeddings);
    // end::adocQdrantToStoreJustListEmbeddings[]

    Embedding embeddedQuestion = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedding().dimension());
  }

  public void useQdrantToStoreListEmbeddings() {
    System.out.println("### useQdrantToStoreListEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    // tag::adocQdrantToStoreListEmbeddings[]
    TextSegment segment1 = TextSegment.from("Kind of Blue (1959) ...");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("Bitches Brew (1970) ...");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    TextSegment segment3 = TextSegment.from("Sketches of Spain (1960) ...");
    Embedding embedding3 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding3, segment3);
    // end::adocQdrantToStoreListEmbeddings[]

    Embedding embeddedQuestion = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }

  public void useQdrantToStoreAllListEmbeddings() {
    System.out.println("### useQdrantToStoreListEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("Kind of Blue (1959) ...");
    Embedding embedding1 = embeddingModel.embed(segment1).content();

    TextSegment segment2 = TextSegment.from("Bitches Brew (1970) ...");
    Embedding embedding2 = embeddingModel.embed(segment2).content();

    TextSegment segment3 = TextSegment.from("Sketches of Spain (1960) ...");
    Embedding embedding3 = embeddingModel.embed(segment2).content();

    // tag::adocQdrantToStoreAllListEmbeddings[]
    embeddingStore.addAll(
      List.of(embedding1, embedding2, embedding3),
      List.of(segment1, segment2, segment3)
    );
    // end::adocQdrantToStoreAllListEmbeddings[]

    Embedding embeddedQuestion = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }

  public void useInMemoryToQuery() {
    System.out.println("### useInMemoryToQuery");

    InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    // tag::adocInMemoryToQuery1[]
    // Data
    TextSegment segment1 = TextSegment.from("Kind of Blue (1959): Widely regarded as one of the greatest jazz albums of all time, featuring musicians like John Coltrane and Bill Evans.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("Bitches Brew (1970): This groundbreaking album marked a significant shift in jazz, blending rock, funk, and electronic elements.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    TextSegment segment3 = TextSegment.from("Blue Moods (1955): This lesser-known Miles Davis album is a collaboration with Charles Mingus alongside Britt Woodman on trombone and Elvin Jones on drums.");
    Embedding embedding3 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding3, segment3);
    // end::adocInMemoryToQuery1[]

    // tag::adocInMemoryToQuery2[]
    // Question to ask
    Embedding embeddedQuestion = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    // end::adocInMemoryToQuery2[]

    // tag::adocInMemoryToQuery3[]
    // Search
    EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(3)
      .minScore(0.5)
      .build();
    // end::adocInMemoryToQuery3[]

    // tag::adocInMemoryToQuery4[]
    // Results
    EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);

    searchResult.matches().forEach(match -> {
      System.out.println(match.score());
      System.out.println(match.embedded().text());
    });
    // end::adocInMemoryToQuery4[]
  }

  public void useQdrantToStoreEmbeddingsComplex() {
    System.out.println("### useQdrantToStoreEmbeddingsSimple");

    createCollection();

    // tag::adocComplex[]
    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();
    // end::adocComplex[]

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("I've been to France twice.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("New Delhi is the capital of India.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    Embedding embeddedQuestion = embeddingModel.embed("Did you ever travel abroad?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
    // end::adocSnippet[]
  }

  public void useQdrantToRemoveEmbeddings() {
    System.out.println("### useQdrantToRemoveEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    // tag::adocQdrantToRemoveEmbeddings[]
    Embedding embedding = embeddingModel.embed("Kind of Blue (1959) ...").content();

    String id = embeddingStore.add(embedding);

    embeddingStore.remove(id);
    // end::adocQdrantToRemoveEmbeddings[]

    Embedding embeddedQuestion = embeddingModel.embed("Which Nora Jemisin won an award?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedding().dimension());
  }

  public void useQdrantToRemoveAllEmbeddings() {
    System.out.println("### useQdrantToRemoveEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    Embedding embedding = embeddingModel.embed("Kind of Blue (1959) ...").content();

    // tag::adocQdrantToRemoveAllEmbeddings[]
    embeddingStore.removeAll();
    // end::adocQdrantToRemoveAllEmbeddings[]

    Embedding embeddedQuestion = embeddingModel.embed("Which Nora Jemisin won an award?").content();
    EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
      .queryEmbedding(embeddedQuestion)
      .maxResults(1)
      .build();
    EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(request);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.matches().get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedding().dimension());
  }

  private static void createCollection() {
    QdrantGrpcClient.Builder grpcClientBuilder = QdrantGrpcClient.newBuilder("localhost", 6334, false);
    QdrantClient qdrantClient = new QdrantClient(grpcClientBuilder.build());
    try {
      qdrantClient.createCollectionAsync(
        "langchain4j-collection",
        Collections.VectorParams.newBuilder()
          .setSize(384)
          .setDistance(Collections.Distance.Cosine)
          .build()
      ).get();
    } catch (Exception e) {
      System.out.println("Collection already exists, skipping creation.");
    }
  }
}
