package org.agoncal.fascicle.langchain4j.gettingstarted;

import static java.lang.System.exit;
import static java.time.Duration.ofSeconds;
import java.util.List;

// tag::adocHeader[]
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;

public class MusicianAssistant {

  // tag::adocOpenAIKey[]
  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
  // end::adocOpenAIKey[]

  public static void main(String[] args) {
    String name = args[0];

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      // tag::adocLogs[]
      .logRequests(true)
      .logResponses(true)
      .build();
    // end::adocLogs[]

    Musician musician = new MusicianAssistant().generateTopThreeAlbums(model, name);

    System.out.println(musician);
    exit(0);
  }
  // end::adocHeader[]

  // tag::adocMethod[]
  Musician generateTopThreeAlbums(ChatLanguageModel model, String name) {

    SystemMessage systemMsg = SystemMessage.from("""
      You are an expert in Jazz music.
      Reply with only the names of the artists, albums, etc.
      Be very concise.
      If a list is given, separate the items with commas.
      """);
    UserMessage userMsg = UserMessage.from(
      String.format("Only list the top 3 albums of %s", name)
    );

    List<ChatMessage> messages = List.of(systemMsg, userMsg);

    ChatResponse albums = model.chat(messages);
    String topThreeAlbums = albums.aiMessage().text();

    return new Musician(name, topThreeAlbums);
  }
  // end::adocMethod[]
}
