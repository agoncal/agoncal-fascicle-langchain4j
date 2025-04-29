package org.agoncal.fascicle.langchain4j.gettingstarted;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

// tag::adocHeader[]
import com.github.dockerjava.api.model.Image;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class MusicianAssistantTest {

  static String MODEL_NAME = "tinyllama";
  static String IMAGE_NAME = "ollama/ollama:latest";
  // end::adocHeader[]

  // tag::adocTest[]
  @Test
  public void shouldGenerateMusicianTopThreeAlbums() throws IOException, InterruptedException {

    OllamaContainer ollamaContainer = createOllamaContainer();
    ollamaContainer.start();

    ChatModel model = OllamaChatModel.builder()
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
    System.out.println("Checking if the Ollama Docker image exists already...");
    List<Image> ollamaDockerImages = DockerClientFactory.lazyClient()
      .listImagesCmd()
      .withImageNameFilter(IMAGE_NAME)
      .exec();

    if (ollamaDockerImages.isEmpty()) {
      System.out.println("Creating a new Ollama container...");
      OllamaContainer ollama = new OllamaContainer(IMAGE_NAME);
      ollama.start();
      System.out.println("Executing an 'ollama pull' command to pull the model...");
      ollama.execInContainer("ollama", "pull", MODEL_NAME);
      ollama.commitToImage(IMAGE_NAME);
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
