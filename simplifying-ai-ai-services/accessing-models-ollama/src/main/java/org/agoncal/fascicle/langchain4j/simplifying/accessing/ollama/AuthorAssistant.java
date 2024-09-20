package org.agoncal.fascicle.langchain4j.simplifying.accessing.ollama;

// tag::adocSnippet[]

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface AuthorAssistant {

  @SystemMessage("""
    You are an expert in Science Fiction books.
    Keep your answers short and to the point.
    If you don't know the answer, say 'I don't know'.
    """)
  @UserMessage("Write a short biography about this Sci-Fi author.")
  String generateAuthorBiography(String author);

  // tag::adocTemplate[]
  @UserMessage("Write a short biography about {{author}}.")
  String generateAuthorBio(@V("author") String authorName);

  @UserMessage("Write a short biography about {{it}}?")
  String generateAuthorBioIt(String author);
  // end::adocSkip[]
}
// end::adocSnippet[]
