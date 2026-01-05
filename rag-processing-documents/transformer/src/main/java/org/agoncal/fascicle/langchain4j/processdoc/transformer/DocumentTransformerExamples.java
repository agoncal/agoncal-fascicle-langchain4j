package org.agoncal.fascicle.langchain4j.processdoc.transformer;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.transformer.jsoup.HtmlToTextDocumentTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.MalformedURLException;

public class DocumentTransformerExamples {

  private final Logger log = LoggerFactory.getLogger(DocumentTransformerExamples.class);

  public Document transformWithTextDocumentParser() {
    // tag::adocTransformWithTextDocumentParser[]
    InputStream documentStream = toStream("data/history-of-cds.html");

    Document document = new TextDocumentParser().parse(documentStream);

    log.info(document.text().trim().substring(0, 50));

    HtmlToTextDocumentTransformer transformer = new HtmlToTextDocumentTransformer();

    Document transformedDocument = transformer.transform(document);

    log.info(transformedDocument.text().trim().substring(0, 50));
    // end::adocTransformWithTextDocumentParser[]
    return transformedDocument;
  }

  public Document transformWithCSSSelector() {
    // tag::adocTransformWithCSSSelector[]
    InputStream documentStream = toStream("data/history-of-cds.html");

    Document document = new TextDocumentParser().parse(documentStream);

    // tag::adocSkip[]
    log.info(document.text().trim().substring(0, 50));
    // end::adocSkip[]
    HtmlToTextDocumentTransformer transformer = new HtmlToTextDocumentTransformer("#early-dev");

    Document transformedDocument = transformer.transform(document);
    // end::adocTransformWithCSSSelector[]

    log.info(transformedDocument.text().trim().substring(0, 50));
    return transformedDocument;
  }

  public Document transformWithTikaDocumentParser() {
    // tag::adocParseWithTikaDocumentParser[]
    InputStream documentStream = toStream("data/history-of-cds.html");

    Document document = new ApacheTikaDocumentParser().parse(documentStream);

    log.info(document.text().trim().substring(0, 50));

    HtmlToTextDocumentTransformer transformer = new HtmlToTextDocumentTransformer();

    Document transformedDocument = transformer.transform(document);

    log.info(transformedDocument.text().trim().substring(0, 50));
    // end::adocParseWithTikaDocumentParser[]
    return transformedDocument;
  }

  private InputStream toStream(String fileName) {
    InputStream fileStream = DocumentTransformerExamples.class.getClassLoader().getResourceAsStream(fileName);
    return fileStream;
  }
}
