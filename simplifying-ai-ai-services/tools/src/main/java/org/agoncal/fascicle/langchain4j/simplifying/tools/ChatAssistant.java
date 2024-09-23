package org.agoncal.fascicle.langchain4j.simplifying.tools;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

// tag::adocSnippet[]
public interface ChatAssistant {

  @SystemMessage("""
    You are an expert of the company Vintage Store.
    You know all the legal details of the company.
    If you don't know the answer, say 'I don't know'.
    """)
  @UserMessage("{{userMessage}}")
  String chat(@V("userMessage") String userMessage);

}
// end::adocSnippet[]
