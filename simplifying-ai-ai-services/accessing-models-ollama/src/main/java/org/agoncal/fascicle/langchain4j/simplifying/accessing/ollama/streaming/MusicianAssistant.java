package org.agoncal.fascicle.langchain4j.simplifying.accessing.ollama.streaming;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

// tag::adocSnippet[]
public interface MusicianAssistant {

  @SystemMessage("You are an expert in musicians biography")
  @UserMessage("Write a short biography about {{musician}}.")
  TokenStream generateMusicianBiography(@V("musician") String musicianName);

}
// end::adocSnippet[]
