package org.agoncal.fascicle.langchain4j.accessing.openaiofficial;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.openai.azure.AzureOpenAIServiceVersion.getV2025_01_01_PREVIEW;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openaiofficial.OpenAiOfficialChatModel;
import dev.langchain4j.model.openaiofficial.OpenAiOfficialImageModel;
import dev.langchain4j.model.output.Response;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class MusicianAssistant {

  public static void main(String[] args) throws JsonProcessingException {

//    useOpenAIOfficial();
//    useOpenAIForDeepSeek();
//    useOpenAIForFoundryChat();
    useOpenAIForFoundryImage();

  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
  private static final String DEEPSEEK_API_KEY = System.getenv("DEEPSEEK_API_KEY");

  private static final String AZURE_FOUNDRY_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_FOUNDRY_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");

  private static void useOpenAIOfficial() {
    System.out.println("### useOpenAIOfficial");

    // tag::adocSnippet[]
    ChatModel model = OpenAiOfficialChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(com.openai.models.ChatModel.GPT_4_1)
      .build();

    String answer = model.chat("When was the first Beatles album released?");

    System.out.println(answer);
    // end::adocSnippet[]
  }

  private static void useOpenAIForDeepSeek() {
    System.out.println("### useOpenAIForDeepSeek");

    // tag::adocDeepSeek[]
    ChatModel model = OpenAiOfficialChatModel.builder()
      .apiKey(DEEPSEEK_API_KEY)
      .baseUrl("https://api.deepseek.com")
      .modelName("deepseek-chat")
      .build();

    String answer = model.chat("When was the first Rolling Stones album out?");

    System.out.println(answer);
    // end::adocDeepSeek[]
  }

  // ################################
  // ### AZURE FOUNDRY CHAT MODEL ###
  // ################################
  static void useOpenAIForFoundryChat() {
    System.out.println("### useOpenAIForFoundryChat");

    // tag::adocUseOpenAIForFoundryChat[]
    OpenAiOfficialChatModel model = OpenAiOfficialChatModel.builder()
      .apiKey(AZURE_FOUNDRY_KEY)
      .baseUrl(AZURE_FOUNDRY_ENDPOINT)
      .modelName("Llama-4-Scout-17B-16E-Instruct")
      .azureDeploymentName("Llama-4-Scout-Deployment")
      .azureOpenAIServiceVersion(getV2025_01_01_PREVIEW())
      .build();
    // end::adocUseOpenAIForFoundryChat[]

    String answer = model.chat("In one sentence, what is the best Pink Floyd album?");

    System.out.println(answer);
    System.out.println("##################");
  }

  // #################################
  // ### AZURE FOUNDRY IMAGE MODEL ###
  // #################################
  static void useOpenAIForFoundryImage() {
    System.out.println("### useOpenAIForFoundryImage");

    // tag::adocUseOpenAIForFoundryImage[]
    OpenAiOfficialImageModel model = OpenAiOfficialImageModel.builder()
      .apiKey(AZURE_FOUNDRY_KEY)
      .baseUrl(AZURE_FOUNDRY_ENDPOINT)
      .modelName("dall-e-3")
      .azureDeploymentName("Dalle-3-Deployment")
      .azureOpenAIServiceVersion(getV2025_01_01_PREVIEW())
      .build();
    // end::adocUseOpenAIForFoundryImage[]

    Response<Image> answer = model.generate("Draw a CD album with a Pink Flamingo");

    System.out.println(answer);
    System.out.println("##################");
  }
}
