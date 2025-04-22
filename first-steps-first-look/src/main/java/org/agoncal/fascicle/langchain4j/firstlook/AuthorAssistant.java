package org.agoncal.fascicle.langchain4j.firstlook;

// tag::adocSnippet[]

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface AuthorAssistant {

  @SystemMessage("You are an expert in Science Fiction books.")
  @UserMessage("Write a short biography about {{author}}.")
  String generateAuthorBiography(@V("author") String authorName);

}
// end::adocSnippet[]
