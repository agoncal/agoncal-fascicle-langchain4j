package org.agoncal.fascicle.langchain4j.puttingtogether;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

// tag::adocSnippet[]
public interface ChatAssistant {

  @SystemMessage("""
    You are an expert of the company Vintage Store.
    Focus on the customer and answer the questions.
    Keep your answers short and to the point.
    If you don't know the answer, say 'I don't know'.
    """)
  @UserMessage("Here is my question: {{question}}")
  String chat(@V("question") String question);

}
// end::adocSnippet[]
