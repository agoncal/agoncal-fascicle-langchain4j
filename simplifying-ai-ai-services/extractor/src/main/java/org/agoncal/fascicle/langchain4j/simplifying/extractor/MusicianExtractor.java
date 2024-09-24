package org.agoncal.fascicle.langchain4j.simplifying.extractor;

import dev.langchain4j.service.UserMessage;

// tag::adocSnippet[]
public interface MusicianExtractor {

  @UserMessage("""
    Extract the information about the musician described below.
    Here is the document describing the musician:
    ---
    {{it}}
    ---
    """)
  Musician extractMusician(String text);

}
// end::adocSnippet[]
