package org.agoncal.fascicle.langchain4j.simplifying.extractor;

import dev.langchain4j.service.UserMessage;

// tag::adocSnippet[]
public interface MusicianExtractor {

  @UserMessage("""
    Extract the name and age of the musician described below.
    Return a JSON document with a "name" and an "age" property,
    following this structure: {"name": "John Lennon", "age": 40}
    Return only JSON, without any markdown markup surrounding it.
    Here is the document describing the person:
    ---
    {{it}}
    ---
    JSON:
    """)
  Musician extractMusician(String text);

}
// end::adocSnippet[]
