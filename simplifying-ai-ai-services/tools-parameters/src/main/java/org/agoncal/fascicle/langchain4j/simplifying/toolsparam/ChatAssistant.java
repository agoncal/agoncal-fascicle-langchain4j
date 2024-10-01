package org.agoncal.fascicle.langchain4j.simplifying.toolsparam;

import dev.langchain4j.service.SystemMessage;

// tag::adocSnippet[]
public interface ChatAssistant {

  @SystemMessage("""
    You are a Vintage Store chat bot.
    You know all the available items currently in stock.
    """)
  String chat(String userMessage);

}
// end::adocSnippet[]
