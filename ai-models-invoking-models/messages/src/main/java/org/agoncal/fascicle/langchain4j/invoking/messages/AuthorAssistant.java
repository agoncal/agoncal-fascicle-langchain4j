package org.agoncal.fascicle.langchain4j.invoking.messages;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.PdfFileContent;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1_MINI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class AuthorAssistant {

  private final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public String useUserMessage() {
    System.out.println("### useUserMessage");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocUserMessage[]
    UserMessage userMessage = new UserMessage("What genre is Brave New World?");
    ChatResponse response = model.chat(userMessage);
    // end::adocUserMessage[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }

  public String useUserMessageFrom() {
    System.out.println("### useUserMessageFrom");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocUserMessageFrom[]
    UserMessage userMessage = UserMessage.from("What genre is Brave New World?");
    ChatResponse response = model.chat(userMessage);
    // end::adocUserMessageFrom[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }

  public String useSystemMessage() {
    System.out.println("### useSystemMessage");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocSystemMessage[]
    SystemMessage systemMessage = new SystemMessage("You are a library assistant, and you can answer customers' request on any book.");
    UserMessage userMessage = new UserMessage("What genre is Brave New World?");
    ChatResponse response = model.chat(systemMessage, userMessage);
    // end::adocSystemMessage[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }

  public String useUserMessageContent() {
    System.out.println("### useUserMessageContent");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocUserMessageContent[]
    TextContent textContent = TextContent.from("What genre is Brave New World?");
    UserMessage userMessage = UserMessage.from(textContent);
    ChatResponse response = model.chat(userMessage);
    // end::adocUserMessageContent[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }

  public String useUserMessagesPdfContent() throws URISyntaxException, IOException {
    System.out.println("### useUserMessagesPdfContent");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocUserMessagesPdfContent[]
    Path pdfPath = Path.of(Paths.get("src/main/resources/brave_new_world_chapter_I.pdf").toUri());
    String pdfBase64 = Base64.getEncoder().encodeToString(Files.readAllBytes(pdfPath));

    UserMessage userMessage = UserMessage.builder()
      .addContent(TextContent.from("Summarize the following PDF file"))
      .addContent(PdfFileContent.from(pdfBase64, "application/pdf"))
      .build();

    ChatResponse response = model.chat(userMessage);
    // end::adocUserMessagesPdfContent[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }

  public String useUserMessagePdfContent() {
    System.out.println("### useUserMessagePdfContent");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .logRequests(true)
      .logResponses(true)
      .build();

    // tag::adocUserMessagePdfContent[]
    UserMessage userMessage = UserMessage.from(
      TextContent.from("Summarize the following PDF file"),
      PdfFileContent.from(Paths.get("src/main/resources/brave_new_world_chapter_I.pdf").toUri())
    );
    ChatResponse response = model.chat(userMessage);
    // end::adocUserMessagePdfContent[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }

  public String useUserMessagesImageContent() {
    System.out.println("### useUserMessagesImageContent");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocUserMessagesImageContent[]
    Image image = Image.builder()
      .url("src/main/resources/brave_new_world_chapter_I.pdf")
      .build();
    ImageContent imageContent = new ImageContent(image);

    UserMessage userMessage = UserMessage.from(
      TextContent.from("Tell me more about this book cover"),
      imageContent
    );
    ChatResponse response = model.chat(userMessage);
    // end::adocUserMessagesImageContent[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }

  public String useUserMessageImageContent() {
    System.out.println("### useUserMessageImageContent");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocUserMessageImageContent[]
    UserMessage userMessage = UserMessage.from(
      TextContent.from("Tell me more about this book cover"),
      ImageContent.from(Paths.get("src/main/resources/brave_new_world.jpg").toUri())
    );
    ChatResponse response = model.chat(userMessage);
    // end::adocUserMessageImageContent[]

    System.out.println(response.aiMessage().text());
    return response.aiMessage().text();
  }
}
