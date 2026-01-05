package org.agoncal.fascicle.langchain4j.processdoc.parser;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.MalformedURLException;

public class DocumentParserExamples {

  private final Logger log = LoggerFactory.getLogger(DocumentParserExamples.class);

  public Document parseWithTextDocumentParser() {
    System.out.println("\n### parseWithTextDocumentParser");
    // tag::adocParseWithTextDocumentParser[]
    InputStream documentStream = toStream("data/bio-ella-fitzgerald.txt");

    Document document = new TextDocumentParser().parse(documentStream);

    log.info(document.text());
    // end::adocParseWithTextDocumentParser[]
    return document;
  }

  public Document parseWithPDFBox() {
    System.out.println("\n### parseWithPDFBox");
    // tag::adocParseWithApachePdfBoxDocumentParser[]
    InputStream documentStream = toStream("data/history-of-audiotapes.pdf");

    Document document = new ApachePdfBoxDocumentParser().parse(documentStream);
    // end::adocParseWithApachePdfBoxDocumentParser[]

    log.info(document.text().trim().substring(0, 100));
    return document;
  }

  public Document parseWithTika() {
    System.out.println("\n### parseWithTika");
    // tag::adocParseWithApacheTikaDocumentParser[]
    InputStream documentStream = toStream("data/history-of-audiotapes.pdf");

    Document document = new ApacheTikaDocumentParser().parse(documentStream);
    // end::adocParseWithApacheTikaDocumentParser[]

    log.info(document.text().trim().substring(0, 100));
    return document;
  }

  public Document parseWithPoi() {
    System.out.println("\n### parseWithPoi");
    // tag::adocParseWithApachePoiDocumentParser[]
    InputStream documentStream = toStream("data/history-of-videotapes.docx");

    Document document = new ApachePoiDocumentParser().parse(documentStream);
    // end::adocParseWithApachePoiDocumentParser[]

    log.info(document.text().trim().substring(0, 100));
    return document;
  }

  // tag::adocToStream[]
  private InputStream toStream(String fileName) {
    InputStream fileStream = DocumentParserExamples.class.getClassLoader().getResourceAsStream(fileName);
    return fileStream;
  }
  // end::adocToStream[]
}
