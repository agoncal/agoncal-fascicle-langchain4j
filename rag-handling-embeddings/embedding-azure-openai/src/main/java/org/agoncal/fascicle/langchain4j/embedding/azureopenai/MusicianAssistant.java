package org.agoncal.fascicle.langchain4j.embedding.azureopenai;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private final String AZURE_OPENAI_KEY = System.getenv("OPENAI_API_KEY");
  private final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");

  public Response<Embedding> textToEmbeddingAzureOpenAI() {
    System.out.println("### textToEmbeddingAzureOpenAI");

    // tag::adocTextToEmbeddingOpenAI[]
    EmbeddingModel model = AzureOpenAiEmbeddingModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName("text-embedding-ada-002")
      .logRequestsAndResponses(false)
      .build();

    Response<Embedding> response = model.embed(TextSegment.from("Hi"));
    System.out.println(response.toString());

    // end::adocTextToEmbeddingOpenAI[]
    return response;
  }
}
