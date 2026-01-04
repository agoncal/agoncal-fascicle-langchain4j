package org.agoncal.fascicle.langchain4j.enriching.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
class LegalAssistantTest {

  private LegalAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new LegalAssistant();
  }

  @Test
  void shouldAskAboutPrivacyDocument() {
    String response = assistant.askAboutDocument("When was the PRIVACY document updated?");
    assertNotNull(response);
  }

  @Test
  void shouldAskAboutTermsDocument() {
    String response = assistant.askAboutDocument("When was the TERMS AND CONDITIONS document updated?");
    assertNotNull(response);
  }
}
