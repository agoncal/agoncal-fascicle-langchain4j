package org.agoncal.fascicle.langchain4j.enriching.mcpclient;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.List;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_1;

import static java.time.Duration.ofSeconds;

public class LegalService {

  public static void main(String[] args) throws Exception {

    ChatModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName(GPT_4_1)
      .temperature(0.7)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    //       .command(List.of("/usr/bin/java", "-jar", "/Users/agoncal/Documents/Code/Agoncal/agoncal-fascicle-langchain4j/ai-models-enriching-models/mcp-server/target/legal-documents-mcp-server-runner.jar"))
    // tag::adocMcpTransport[]
    McpTransport transport = new StdioMcpTransport.Builder()
      .command(List.of("/usr/bin/java", "-jar", "~/mcp-server/legal-documents-mcp-server.jar"))
      .logEvents(true)
      .build();
    // end::adocMcpTransport[]

    // tag::adocMcpClient[]
    McpClient mcpClient = new DefaultMcpClient.Builder()
      .transport(transport)
      .build();

    // tag::adocMcpToolProvider[]
    McpToolProvider toolProvider = McpToolProvider.builder()
      .mcpClients(List.of(mcpClient))
      // tag::adocFilter[]
      .filterToolNames("last_update_terms", "last_update_privacy")
      // end::adocFilter[]
      .build();
    // end::adocMcpToolProvider[]
    // end::adocMcpClient[]

    // tag::adocLegalAssistant[]
    LegalAssistant bot = AiServices.builder(LegalAssistant.class)
      .chatModel(model)
      .toolProvider(toolProvider)
      .build();

    String response = bot.chat("When was the PRIVACY document updated?");
    System.out.println(response); //The PRIVACY document was last updated on 09/03/2013
    // end::adocLegalAssistant[]

    System.exit(0);
  }
}
