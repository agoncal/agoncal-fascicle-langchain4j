package org.agoncal.fascicle.langchain4j.simplifying.extractorimplicit;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

// tag::adocSnippet[]
public interface MusicianAssistant {

  @SystemMessage("""
    You are an expert in Jazz music.
    Be very concise.
    """)
  @UserMessage("Only list the top 3 albums of {{it}}")
  Musician generateTopThreeAlbums(String musicianName);

}
// end::adocSnippet[]
