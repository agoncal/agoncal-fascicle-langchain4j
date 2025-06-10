package org.agoncal.fascicle.langchain4j.enriching.mcpclient;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

// tag::adocSnippet[]
public interface LegalAssistant {

  @SystemMessage("""
    You are a legal expert of the company Vintage Store.
    Focus on the customer and answer any legal-related questions.
    Keep your answers short and to the point.
    If you don't know the answer, say 'I don't know'.
    """)
  String chat(String question);
}
// end::adocSnippet[]
