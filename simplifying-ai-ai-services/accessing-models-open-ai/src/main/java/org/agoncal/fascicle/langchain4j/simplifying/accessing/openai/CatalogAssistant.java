package org.agoncal.fascicle.langchain4j.simplifying.accessing.openai;

// tag::adocSnippet[]

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface CatalogAssistant {

  @SystemMessage("You are an expert in Science Fiction books.")
  @UserMessage("Write a short biography about {{author}}.")
  String getAuthorBiography(@V("author") String authorName);

}
// end::adocSnippet[]
