package org.agoncal.fascicle.langchain4j.invoking.templates;

// tag::adocSnippet[]

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;

import static java.util.Collections.singletonMap;

import java.util.HashMap;
import java.util.Map;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) throws InterruptedException {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

//    musicianAssistant.usePromptTemplate();
    musicianAssistant.useMultiplePromptTemplate();
//    musicianAssistant.usePromptTemplateCurrentDate();
  }

  // #######################
  // ### PROMPT TEMPLATE ###
  // #######################

  public void usePromptTemplate() {
    System.out.println("### usePromptTemplate");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocPromptTemplate[]
    PromptTemplate promptTemplate = PromptTemplate.from("When was the first album of {{band}} released?");

    Map<String, Object> variables = singletonMap("band", "Miles Davis");

    Prompt prompt = promptTemplate.apply(variables);

    Response<AiMessage> response = model.generate(prompt.toUserMessage());
    System.out.println(response.content().text());
    // end::adocPromptTemplate[]
  }

  public void useMultiplePromptTemplate() {
    System.out.println("### useMultiplePromptTemplate");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocMultiplePromptTemplate[]
    PromptTemplate promptTemplate = PromptTemplate.from("Give me the titles of the album {{album}} of {{band}}");

    Map<String, Object> variables = Map.of(
      "album", "Kind of Blue",
      "band", "Miles Davis");

    Prompt prompt = promptTemplate.apply(variables);

    Response<AiMessage> response = model.generate(prompt.toUserMessage());
    System.out.println(response.content().text());
    // end::adocMultiplePromptTemplate[]
  }

  public void usePromptTemplateCurrentDate() {
    System.out.println("### usePromptTemplateCurrentDate");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocPromptTemplateCurrentDate[]
    PromptTemplate promptTemplate = PromptTemplate.from("Today is {{current_date}}. How many years since {{album}} was released?");

    Map<String, Object> variables = singletonMap("album", "Sgt. Pepper's Lonely Hearts Club Band");

    Prompt prompt = promptTemplate.apply(variables);

    Response<AiMessage> response = model.generate(prompt.toUserMessage());
    System.out.println(response.content().text());
    // end::adocPromptTemplateCurrentDate[]
  }
}
