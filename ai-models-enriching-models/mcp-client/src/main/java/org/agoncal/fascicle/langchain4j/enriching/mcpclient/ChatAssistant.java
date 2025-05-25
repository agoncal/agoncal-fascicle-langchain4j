package org.agoncal.fascicle.langchain4j.enriching.mcpclient;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;

import static java.time.Duration.ofSeconds;

public class ChatAssistant {

  public static void main(String[] args) throws Exception {

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName(GPT_4_1)
      .temperature(0.7)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

  }

}
