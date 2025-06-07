package org.agoncal.fascicle.langchain4j.simplifying.easyrag;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class DocumentIngestor {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
  private static final String PATH_TO_DOCUMENTS = "/Users/agoncal/Documents/Code/Agoncal/agoncal-fascicle-langchain4j/simplifying-ai-easy-rag/src/main/resources";

  public static void main(String[] args) throws Exception {

    // tag::adocIngest[]
    List<Document> documents = FileSystemDocumentLoader.loadDocuments(PATH_TO_DOCUMENTS);

    InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
    EmbeddingStoreIngestor.ingest(documents, embeddingStore);
    // end::adocIngest[]

    ChatModel chatModel = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O_MINI)
      .build();

    // tag::adocAssistant[]
    VintageStoreAssistant assistant = AiServices.builder(VintageStoreAssistant.class)
      .chatModel(chatModel)
      .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
      .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
      .build();

    System.out.println(assistant.chat("What are the Terms and Conditions of Vintage Store?"));
    // end::adocAssistant[]
  }
}
