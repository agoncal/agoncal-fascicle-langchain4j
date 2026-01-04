package org.agoncal.fascicle.langchain4j.invoking.tokens;

import dev.langchain4j.model.output.TokenUsage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenizationTest {

  private Tokenization tokenization;

  @BeforeEach
  void setUp() {
    tokenization = new Tokenization();
  }

  @Test
  void shouldTokenizeOpenAIIsaacAsimov() {
    int nbOfTokens = tokenization.tokenizeOpenAIIsaacAsimov();
    assertTrue(nbOfTokens > 0);
  }

  @Test
  void shouldTokenizeOpenAIIsaacAsimovMedium() {
    int nbOfTokens = tokenization.tokenizeOpenAIIsaacAsimovMedium();
    assertTrue(nbOfTokens > 0);
  }

  @Test
  void shouldTokenizeOpenAIIsaacAsimovLong() {
    int nbOfTokens = tokenization.tokenizeOpenAIIsaacAsimovLong();
    assertTrue(nbOfTokens > 0);
  }

  @Test
  void shouldTokenizeOpenAI() {
    int nbOfTokens = tokenization.tokenizeOpenAI();
    assertTrue(nbOfTokens > 0);
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
  void shouldGetTokenUsage() {
    TokenUsage tokenUsage = tokenization.tokenUsage();
    assertNotNull(tokenUsage);
    assertTrue(tokenUsage.inputTokenCount() > 0);
    assertTrue(tokenUsage.outputTokenCount() > 0);
    assertTrue(tokenUsage.totalTokenCount() > 0);
  }
}
