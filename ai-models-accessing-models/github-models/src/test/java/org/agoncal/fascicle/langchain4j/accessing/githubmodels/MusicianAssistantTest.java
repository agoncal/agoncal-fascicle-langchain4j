package org.agoncal.fascicle.langchain4j.accessing.githubmodels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledIfEnvironmentVariable(named = "GITHUB_TOKEN", matches = ".+")
class MusicianAssistantTest {

  private MusicianAssistant assistant;

  @BeforeEach
  void setUp() {
    assistant = new MusicianAssistant();
  }

  @Test
  void shouldUseGitHubModelsChatModel() {
    String response = assistant.useGitHubModelsChatModel();
    assertNotNull(response);
  }
}
