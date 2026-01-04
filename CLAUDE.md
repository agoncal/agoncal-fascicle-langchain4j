# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java Maven multi-module project containing code examples for the "Understanding LangChain4j" fascicle (ebook). It demonstrates practical usage of LangChain4j, a Java library for integrating LLMs into Java applications.

- **Java version**: 21
- **LangChain4j version**: 1.9.1 (core), 1.9.1-beta17 (community)
- **Maven**: 3.0.0+

## Build Commands

```bash
# Build entire project
mvn clean install

# Build specific module
mvn clean install -pl ai-models-accessing-models/open-ai

# Run a specific example (most modules have exec-maven-plugin configured)
mvn exec:java -pl first-steps-first-look

# Run tests
mvn test

# Run single test class
mvn test -pl first-steps-getting-started -Dtest=MusicianAssistantTest

# Run integration tests
mvn verify
```

## Module Structure

The 14 main modules follow the book's learning progression:

1. **First Steps**: `first-steps-first-look`, `first-steps-getting-started`
2. **AI Models**: `ai-models-accessing-models` (16 LLM providers), `ai-models-invoking-models`, `ai-models-enriching-models`
3. **AI Services**: `simplifying-ai-ai-services`, `simplifying-ai-easy-rag`
4. **RAG**: `rag-processing-documents`, `rag-handling-embeddings` (4 embedding models + 9 vector stores), `rag-rag`
5. **Integration**: `going-further-integrating` (Spring, Quarkus), `going-further-testing-debugging`, `going-further-advanced`
6. **Final**: `wrappingup-putting-together`

## Code Patterns

### AI Service Pattern (High-level)
```java
public interface AuthorAssistant {
  @SystemMessage("You are an expert in Science Fiction...")
  @UserMessage("Write a short biography about {{author}}")
  String generateAuthorBiography(@V("author") String author);
}

AuthorAssistant assistant = AiServices.create(AuthorAssistant.class, model);
```

### Direct Model Pattern (Low-level)
```java
ChatModel model = OpenAiChatModel.builder()
  .apiKey(OPENAI_API_KEY)
  .modelName(GPT_4_1)
  .build();
String response = model.chat(prompt);
```

### Package Convention
`org.agoncal.fascicle.langchain4j.[feature]`

### AsciiDoc Snippet Tags
Code uses tags for book integration - preserve these when editing:
```java
// tag::adocSnippet[]
// code included in documentation
// end::adocSnippet[]
```

## Environment Variables

- `OPENAI_API_KEY` - Required for OpenAI examples
- Provider-specific keys for other modules (Azure, Anthropic, etc.)

## Testing

- **JUnit 5** for unit tests
- **TestContainers** for Docker-based integration tests (Ollama, databases)
- **WireMock** for HTTP API mocking

Tests requiring external services use TestContainers to spin up Docker containers automatically.

## Key Dependencies (managed in parent POM)

- `langchain4j-bom` and `langchain4j-community-bom` for version management
- TinyLog for logging
- Quarkus 3.6.5 for Quarkus integration module
