package org.agoncal.fascicle.langchain4j.invoking.messages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class AuthorAssistantTest {

  private AuthorAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new AuthorAssistant();
  }

  @Test
  void shouldUseUserMessage() {
    String response = assistant.useUserMessage();
    assertNotNull(response);
  }

  @Test
  void shouldUseUserMessageFrom() {
    String response = assistant.useUserMessageFrom();
    assertNotNull(response);
  }

  @Test
  void shouldUseSystemMessage() {
    String response = assistant.useSystemMessage();
    assertNotNull(response);
  }

  @Test
  void shouldUseUserMessageContent() {
    String response = assistant.useUserMessageContent();
    assertNotNull(response);
  }

  @Test
  void shouldUseUserMessagesPdfContent() throws URISyntaxException, IOException {
    String response = assistant.useUserMessagesPdfContent();
    assertNotNull(response);
  }

  @Test
  void shouldUseUserMessagePdfContent() {
    String response = assistant.useUserMessagePdfContent();
    assertNotNull(response);
  }

  @Test
  void shouldUseUserMessagesImageContent() {
    String response = assistant.useUserMessagesImageContent();
    assertNotNull(response);
  }

  @Test
  void shouldUseUserMessageImageContent() {
    String response = assistant.useUserMessageImageContent();
    assertNotNull(response);
  }
}
