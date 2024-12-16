package org.agoncal.fascicle.langchain4j.simplifying.memory;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

// tag::adocSnippet[]
public interface MusicianAssistant {

  @SystemMessage("You are an expert in music")
  @UserMessage("{{userMessage}}")
  String chat(@V("userMessage") String userMessage);

}
// end::adocSnippet[]
