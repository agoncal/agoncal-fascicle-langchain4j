package org.agoncal.fascicle.langchain4j.rag.rag;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.exit;
import java.net.URI;
import java.net.URISyntaxException;
import static java.time.Duration.ofSeconds;
import java.util.Scanner;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class ChatAssistant {

    private static final Logger log = LoggerFactory.getLogger(ChatAssistant.class);
    private static final String INDEX_NAME = "VintageStoreIndex";
    private static final String QDRANT_URL = "http://localhost:6334";
    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    private static final EmbeddingStore<TextSegment> embeddingStore = embeddingStore();
    private static final ChatModel model = model();
    private static final ChatMemory chatMemory = memory();
    private static final EmbeddingModel embeddingModel = embeddingModel();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String question;

        System.out.println("You are chatting with the Vintage Store chat bot, ask any questions.:");
        System.out.println("Type 'exit' to quit the chat bot.");

        while (true) {
            question = scanner.nextLine();

            if ("exit".equalsIgnoreCase(question)) {
                System.out.println("Exiting...");
                break;
            }

            System.out.println(ChatAssistant.chat(question));
        }

        scanner.close();
        exit(0);
    }

    private static String chat(String question) {
        // tag::adocSimilaritySearch[]
        Embedding embeddedQuestion = embeddingModel.embed(question).content();

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(embeddedQuestion)
                .maxResults(3)
                .minScore(0.5)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);

        searchResult.matches().forEach(match -> {
            chatMemory.add(UserMessage.from(match.embedded().text()));
        });
        // end::adocSimilaritySearch[]

        // tag::adocChat[]
        UserMessage userQuestion = UserMessage.from(question);

        chatMemory.add(userQuestion);
        AiMessage aiAnswer = model.chat(chatMemory.messages()).aiMessage();
        chatMemory.add(aiAnswer);

        System.out.println("Vintage Store chat bot: " + aiAnswer.text());
        // end::adocChat[]

        return aiAnswer.text();
    }

    private static ChatMemory memory() {
        // tag::adocMemory[]
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

        SystemMessage systemMsg = new SystemMessage("""
                You are a Vintage Store assistant.
                Vintage Store is a company specialising in the sale of nostalgic items, including paper books, CDs, tapes, and other cherished vintage collectibles.
                You can answer customers' requests on any of these vintage items.
                If you don't know the answer, say 'I don't know'.
                """);

        chatMemory.add(systemMsg);
        return chatMemory;
        // end::adocMemory[]
    }

    private static ChatModel model() {
        // tag::adocModel[]
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(OPENAI_API_KEY)
                .modelName(GPT_4_1)
                .temperature(0.3)
                .timeout(ofSeconds(60))
                .logRequests(true)
                .logResponses(true)
                .build();
        // end::adocModel[]
        return model;
    }

    private static EmbeddingModel embeddingModel() {
        // tag::adocEmbedModel[]
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        // end::adocEmbedModel[]
        return embeddingModel;
    }

    private static EmbeddingStore<TextSegment> embeddingStore() {
        try {
            String qdrantHostname = new URI(QDRANT_URL).getHost();
            int qdrantPort = new URI(QDRANT_URL).getPort();

            QdrantGrpcClient.Builder grpcClientBuilder = QdrantGrpcClient.newBuilder(qdrantHostname, qdrantPort, false);
            QdrantClient qdrantClient = new QdrantClient(grpcClientBuilder.build());
            return QdrantEmbeddingStore.builder()
                    .client(qdrantClient)
                    .collectionName(INDEX_NAME)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
