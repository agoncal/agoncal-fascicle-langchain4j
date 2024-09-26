package org.agoncal.fascicle.langchain4j.gettingstarted;

// tag::adocHeader[]

import com.github.dockerjava.api.model.Image;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Testcontainers
public class MusicianAssistantTest {

  static String MODEL_NAME = "tinyllama";
  // end::adocHeader[]

  // tag::adocTest[]
  @Test
  public void shouldGenerateMusicianTopThreeAlbums() throws IOException, InterruptedException {

    OllamaContainer ollamaContainer = createOllamaContainer();
    ollamaContainer.start();

    ChatLanguageModel model = OllamaChatModel.builder()
      .baseUrl(String.format("http://%s:%d", ollamaContainer.getHost(), ollamaContainer.getFirstMappedPort()))
      .modelName(MODEL_NAME)
      .temperature(0.0)
      .timeout(Duration.ofMinutes(5))
      .build();

    Musician musician = new MusicianAssistant().generateTopThreeAlbums(model, "Miles Davis");

    assertTrue(musician.albums().contains("Kind of Blue"));
  }
  // end::adocTest[]

  // tag::adocContainer[]
  private OllamaContainer createOllamaContainer() throws IOException, InterruptedException {
    // check if the custom Gemma Ollama image exists already
    List<Image> listImagesCmd = DockerClientFactory.lazyClient()
      .listImagesCmd()
      .withImageNameFilter(MODEL_NAME)
      .exec();

    if (listImagesCmd.isEmpty()) {
      System.out.println("Creating a new Ollama container with the model image...");
      OllamaContainer ollama = new OllamaContainer("ollama/ollama:latest");
      ollama.start();
      ollama.execInContainer("ollama", "pull", MODEL_NAME);
      ollama.commitToImage(MODEL_NAME);
      return ollama;
    } else {
      System.out.println("Using existing Ollama container with model image...");
      return new OllamaContainer(
        DockerImageName.parse(MODEL_NAME)
          .asCompatibleSubstituteFor("ollama/ollama"));
    }
  }
  // end::adocContainer[]
}
