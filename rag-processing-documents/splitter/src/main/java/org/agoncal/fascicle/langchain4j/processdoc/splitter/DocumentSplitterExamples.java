package org.agoncal.fascicle.langchain4j.processdoc.splitter;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.source.FileSystemSource;
import dev.langchain4j.data.document.splitter.DocumentByLineSplitter;
import dev.langchain4j.data.document.splitter.DocumentBySentenceSplitter;
import dev.langchain4j.data.document.splitter.DocumentByWordSplitter;
import dev.langchain4j.data.segment.TextSegment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DocumentSplitterExamples {

  private static final Logger log = LoggerFactory.getLogger(DocumentSplitterExamples.class);

  public static void main(String[] args) throws MalformedURLException {
//    splitWithDocumentBySentenceSplitter();
//    splitWithDocumentByLineSplitter();
    splitWithDocumentByWordSplitter();
  }

  private static void splitWithDocumentBySentenceSplitterLoad() {
    // tag::adocSplitWithDocumentBySentenceSplitterLoad[]
    Path documentPath = toPath("data/biography-of-isaac-asimov.txt");

    Document document = DocumentLoader.load(new FileSystemSource(documentPath), new TextDocumentParser());

    DocumentSplitter splitter = new DocumentBySentenceSplitter(1000, 50);

    List<TextSegment> segments = splitter.split(document);

    segments.forEach(segment -> System.out.println(segment.text().length()));

    // end::adocSplitWithDocumentBySentenceSplitterLoad[]
  }

  private static void splitWithDocumentBySentenceSplitter() {
    System.out.println("\n### splitWithDocumentBySentenceSplitter");
    // tag::adocSplitWithDocumentBySentenceSplitter[]
    Path documentPath = toPath("data/biography-of-isaac-asimov.txt");

    Document document = FileSystemDocumentLoader.loadDocument(documentPath);

    DocumentSplitter splitter = new DocumentBySentenceSplitter(1000, 50);

    List<TextSegment> segments = splitter.split(document);

    segments.forEach(segment -> System.out.println(segment.text()));
    // end::adocSplitWithDocumentBySentenceSplitter[]
  }

  private static void splitWithDocumentByLineSplitter() {
    System.out.println("\n### splitWithDocumentByLineSplitter");
    Path documentPath = toPath("data/biography-of-isaac-asimov.txt");

    Document document = FileSystemDocumentLoader.loadDocument(documentPath);

    // tag::adocSplitWithDocumentByLineSplitter[]
    DocumentSplitter splitter = new DocumentByLineSplitter(1000, 50);

    List<TextSegment> segments = splitter.split(document);
    // end::adocSplitWithDocumentByLineSplitter[]

    segments.forEach(segment -> System.out.println(">>"+segment.text()));
  }


  private static void splitWithDocumentByWordSplitter() {
    System.out.println("\n### splitWithDocumentByWordSplitter");
    Path documentPath = toPath("data/biography-of-isaac-asimov.txt");

    Document document = FileSystemDocumentLoader.loadDocument(documentPath);

    // tag::adocSplitWithDocumentByWordSplitter[]
    DocumentSplitter splitter = new DocumentByWordSplitter(1000, 50);

    List<TextSegment> segments = splitter.split(document);
    // end::adocSplitWithDocumentByWordSplitter[]

    segments.forEach(segment -> System.out.println(">>"+segment.text()));
  }

  private static Path toPath(String fileName) {
    try {
      URL fileUrl = DocumentSplitterExamples.class.getClassLoader().getResource(fileName);
      return Paths.get(fileUrl.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
