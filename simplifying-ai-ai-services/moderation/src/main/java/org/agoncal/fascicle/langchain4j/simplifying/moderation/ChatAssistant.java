package org.agoncal.fascicle.langchain4j.simplifying.moderation;

import dev.langchain4j.service.Moderate;

// tag::adocSnippet[]
public interface ChatAssistant {

  @Moderate
  String chat(String userMessage);

}
// end::adocSnippet[]
