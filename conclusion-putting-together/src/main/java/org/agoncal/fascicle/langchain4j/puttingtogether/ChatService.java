package org.agoncal.fascicle.langchain4j.puttingtogether;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import static java.lang.System.exit;
import static java.time.Duration.ofSeconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Scanner;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocMain[]
public class ChatService {

  private static final Logger log = LoggerFactory.getLogger(ChatService.class);
  private static final String INDEX_NAME = "VintageStoreIndex";
  private static final String QDRANT_URL = "http://localhost:6334";
  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) throws Exception {

    EmbeddingStore<TextSegment> embeddingStore = embeddingStore();
    ChatLanguageModel model = model();
    ChatAssistant assistant = assistant(embeddingStore, model);

    Scanner scanner = new Scanner(System.in);
    String question;

    System.out.println("You are chatting with the Vintage Store chatbot, ask any questions.");
    System.out.println("Type 'exit' to quit the chat bot.");

    while (true) {
      question = scanner.nextLine();

      if ("exit".equalsIgnoreCase(question)) {
        System.out.println("Thanks for using the Vintage Store chatbot! We're looking forward to chatting with you again.");
        break;
      }

      System.out.println(assistant.chat(question));
    }

    scanner.close();
    exit(0);
  }
// end::adocMain[]

  // tag::adocModel[]
  private static ChatLanguageModel model() {
    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    return model;
  }
  // end::adocModel[]

  // tag::adocAssistant[]
  private static ChatAssistant assistant(EmbeddingStore<TextSegment> embeddingStore, ChatLanguageModel model) {
    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
    ContentRetriever contentRetriever = new EmbeddingStoreContentRetriever(embeddingStore, embeddingModel);

    ChatAssistant assistant = AiServices.builder(ChatAssistant.class)
      .chatLanguageModel(model)
      .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
      .contentRetriever(contentRetriever)
      .tools(new ChatTools())
      .build();

    return assistant;
  }
  // end::adocAssistant[]

  // tag::adocStore[]
  private static EmbeddingStore<TextSegment> embeddingStore() throws Exception {
    String qdrantHostname = new URI(QDRANT_URL).getHost();
    int qdrantPort = new URI(QDRANT_URL).getPort();

    QdrantGrpcClient.Builder grpcClientBuilder = QdrantGrpcClient.newBuilder(qdrantHostname, qdrantPort, false);
    QdrantClient qdrantClient = new QdrantClient(grpcClientBuilder.build());
    return QdrantEmbeddingStore.builder()
      .client(qdrantClient)
      .collectionName(INDEX_NAME)
      .build();
  }
  // end::adocStore[]
}
