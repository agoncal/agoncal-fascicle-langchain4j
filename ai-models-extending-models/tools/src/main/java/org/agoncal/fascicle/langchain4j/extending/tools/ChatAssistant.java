package org.agoncal.fascicle.langchain4j.extending.tools;

// tag::adocHeader[]

import dev.langchain4j.service.tool.DefaultToolExecutor;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.service.tool.ToolExecutor;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import static java.time.Duration.ofSeconds;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatAssistant {

  public static void main(String[] args) throws Exception {

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName(GPT_4_O)
      .temperature(0.7)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();


    // tag::adocSkip[]
    // STEP 1: User specify tools and query
    System.out.println("####################################");
    System.out.println("STEP 1: User specify tools and query");
    // end::adocSkip[]
    // tag::adocStepOne[]
    // Tools
    LegalDocumentTools tools = new LegalDocumentTools();
    List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(tools.getClass());
    // User query
    List<ChatMessage> chatMessages = new ArrayList<>();
    UserMessage userQuery = UserMessage.from("When was the PRIVACY document updated?");
    chatMessages.add(userQuery);
    // end::adocStepOne[]

    // STEP 2: Model generate function arguments
    System.out.println("#########################################");
    System.out.println("STEP 2: Model generate function arguments");
    // tag::adocStepTwo[]
    AiMessage aiMessage = model.generate(singletonList(userQuery), toolSpecifications).content();
    List<ToolExecutionRequest> toolExecutionRequests = aiMessage.toolExecutionRequests();
    chatMessages.add(aiMessage);
    // end::adocStepTwo[]
    toolExecutionRequests.forEach(toolExecutionRequest -> {
      System.out.println("Function name: " + toolExecutionRequest.name());
      System.out.println("Function args:" + toolExecutionRequest.arguments());
    });

    // STEP 3: User execute function to obtain tool results
    System.out.println("####################################################");
    System.out.println("STEP 3: User execute function to obtain tool results");
    // tag::adocStepThree[]
    toolExecutionRequests.forEach(toolExecutionRequest -> {
      ToolExecutor toolExecutor = new DefaultToolExecutor(tools, toolExecutionRequest);
      String result = toolExecutor.execute(toolExecutionRequest, UUID.randomUUID().toString());
      ToolExecutionResultMessage toolExecutionResultMessages = ToolExecutionResultMessage.from(toolExecutionRequest, result);
      chatMessages.add(toolExecutionResultMessages);
    });
    // end::adocStepThree[]

    // STEP 4: Model generate final response
    System.out.println("#####################################");
    System.out.println("STEP 4: Model generate final response");
    // tag::adocStepFour[]
    AiMessage finalResponse = model.generate(chatMessages).content();
    System.out.println(finalResponse.text());
    // end::adocStepFour[]
  }
  // end::adocMethod[]

  private static List<ToolExecutionResultMessage> toolExecutor(
    Object objectWithTools,
    List<ToolExecutionRequest> toolExecutionRequests) throws Exception {
    String memoryId = UUID.randomUUID().toString();
    return toolExecutionRequests.stream()
      .map(request -> {
        try {
          ToolExecutor toolExecutor = new DefaultToolExecutor(objectWithTools,
            objectWithTools.getClass().getDeclaredMethod(request.name(),
              String.class));
          return ToolExecutionResultMessage.from(request, toolExecutor.execute(request, memoryId));
        } catch (NoSuchMethodException e) {
          System.err.println("No such tool found: " + request.name());
        }
        return null;
      })
      .collect(toList());
  }

}
