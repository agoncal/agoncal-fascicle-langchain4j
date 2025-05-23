package org.agoncal.fascicle.langchain4j.invoking.tokens;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1_MINI;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;
import dev.langchain4j.model.output.TokenUsage;

import java.util.List;

public class Tokenization {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // tag::adocPrompt[]
  String prompt = """
    Isaac Asimov (1920-1992), a Russian-born American author and biochemist,
    achieved renown as a prolific science fiction writer and science popularizer.
    Immigrating to the U.S. as a child, he began his literary career with the
    publication of "Marooned off Vesta" in 1939. Asimov's extensive body of work
    includes iconic novels like "Foundation" and "I, Robot." Beyond fiction,
    he became a revered science communicator, writing numerous accessible books
    and essays that demystified complex scientific concepts for a broad audience,
    leaving an enduring legacy in both literature and science education.
    """;
  // end::adocPrompt[]

  public static void main(String[] args) {
    Tokenization tokenization = new Tokenization();
    tokenization.tokenizeOpenAIIsaacAsimov();
    tokenization.tokenizeOpenAIIsaacAsimovMedium();
    tokenization.tokenizeOpenAIIsaacAsimovLong();
    tokenization.tokenizeOpenAI();
    tokenization.tokenUsage();
  }

  private void tokenizeOpenAIIsaacAsimov() {
    System.out.println("### tokenizeOpenAIIsaacAsimov");
    // tag::adocTokenize[]
    OpenAiTokenCountEstimator tokenizer = new OpenAiTokenCountEstimator(GPT_4_1);

    String prompt = "Isaac Asimov.";

    // Number of tokens: 6
    int nbOfTokens = tokenizer.estimateTokenCountInText(prompt);
    // tag::adocSkip[]
    System.out.println("Number of tokens: " + nbOfTokens + "\n");
    // end::adocSkip[]

    // Encoded tokens: 3957 65805 1666 318 869 13
    // tag::adocSkip[]
    System.out.println("Encoded tokens: " + prompt);
    // end::adocSkip[]
    List<Integer> tokens = tokenizer.encode(prompt);
    tokens.forEach(token -> System.out.print(token + " "));
    // tag::adocSkip[]
    System.out.println("\n");
    // end::adocSkip[]

    // Decoded tokens: Isaac Asimov.
    prompt = tokenizer.decode(tokens);
    // tag::adocSkip[]
    System.out.println("Decoded tokens: " + prompt);
    // end::adocSkip[]
    // end::adocTokenize[]
  }

  private void tokenizeOpenAIIsaacAsimovMedium() {
    System.out.println("### tokenizeOpenAIIsaacAsimovMedium");
    // tag::adocTokenizeMedium[]
    OpenAiTokenCountEstimator tokenizer = new OpenAiTokenCountEstimator(GPT_4_1);

    String prompt = "Isaac Asimov is a writer.";

    // Number of tokens: 9
    int nbOfTokens = tokenizer.estimateTokenCountInText(prompt);
    // tag::adocSkip[]
    System.out.println("Number of tokens: " + nbOfTokens + "\n");
    // end::adocSkip[]

    // Encoded tokens: 3957 65805 1666 318 869 374 264 7061 13
    // tag::adocSkip[]
    System.out.println("Encoded tokens: " + prompt);
    // end::adocSkip[]
    List<Integer> tokens = tokenizer.encode(prompt);
    tokens.forEach(token -> System.out.print(token + " "));
    // tag::adocSkip[]
    System.out.println("\n");
    // end::adocSkip[]

    // Decoded tokens: Isaac Asimov is a writer.
    prompt = tokenizer.decode(tokens);
    // tag::adocSkip[]
    System.out.println("Decoded tokens: " + prompt);
    // end::adocSkip[]
    // end::adocTokenizeMedium[]
  }

  private void tokenizeOpenAIIsaacAsimovLong() {
    System.out.println("### tokenizeOpenAIIsaacAsimovLong");
    // tag::adocTokenizeLong[]
    OpenAiTokenCountEstimator tokenizer = new OpenAiTokenCountEstimator(GPT_4_1);

    String prompt = "Isaac Asimov is a writer and is a biochemist.";

    // Number of tokens: 9
    int nbOfTokens = tokenizer.estimateTokenCountInText(prompt);
    // tag::adocSkip[]
    System.out.println("Number of tokens: " + nbOfTokens + "\n");
    // end::adocSkip[]

    // Encoded tokens: 3957 65805 1666 318 869 374 264 7061 13
    // tag::adocSkip[]
    System.out.println("Encoded tokens: " + prompt);
    // end::adocSkip[]
    List<Integer> tokens = tokenizer.encode(prompt);
    tokens.forEach(token -> System.out.print(token + " "));
    // tag::adocSkip[]
    System.out.println("\n");
    // end::adocSkip[]

    // Decoded tokens: Isaac Asimov is a writer.
    prompt = tokenizer.decode(tokens);
    // tag::adocSkip[]
    System.out.println("Decoded tokens: " + prompt);
    // end::adocSkip[]
    // end::adocTokenizeLong[]
  }

  private void tokenizeOpenAI() {
    System.out.println("### tokenizeOpenAI");
    OpenAiTokenCountEstimator tokenizer = new OpenAiTokenCountEstimator(GPT_3_5_TURBO);

    // Estimate
    int nbOfTokens = tokenizer.estimateTokenCountInText(prompt);
    System.out.println("Number of tokens: " + nbOfTokens + "\n");

    // Encode
    System.out.println("Encoded tokens: " + prompt);
    List<Integer> tokens = tokenizer.encode(prompt);
    tokens.forEach(token -> System.out.print(token + " "));
    System.out.println("\n");

    // Decode
    String prompt = tokenizer.decode(tokens);
    System.out.println("Decoded tokens: " + prompt);
  }

  public void tokenUsage() {
    System.out.println("### tokenUsage");

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_1_MINI)
      .build();

    // tag::adocTokenUsage[]
    ChatResponse response = model.chat(new UserMessage("In one sentence, how does Jane Eyre end?"));

    // Jane Eyre ends with Jane and Mr. Rochester reuniting and getting married.
    System.out.println(response.aiMessage().text());

    TokenUsage tokenUsage = response.tokenUsage();

    System.out.println(tokenUsage.inputTokenCount());  // 18
    System.out.println(tokenUsage.outputTokenCount()); // 17
    System.out.println(tokenUsage.totalTokenCount());  // 35
    // end::adocTokenUsage[]
  }
}
