package org.agoncal.fascicle.langchain4j.invoking.templates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUsePromptTemplate() {
    String response = assistant.usePromptTemplate();
    assertNotNull(response);
  }

  @Test
  void shouldUseMultiplePromptTemplate() {
    String response = assistant.useMultiplePromptTemplate();
    assertNotNull(response);
  }

  @Test
  void shouldUsePromptTemplateCurrentDate() {
    String response = assistant.usePromptTemplateCurrentDate();
    assertNotNull(response);
  }
}
