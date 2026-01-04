package org.agoncal.fascicle.langchain4j.accessing.azurefoundrylocal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("Requires local Foundry server running on localhost:5273")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseOpenAIForFoundryLocal() {
    String answer = assistant.useOpenAIForFoundryLocal();
    assertNotNull(answer);
  }
}
