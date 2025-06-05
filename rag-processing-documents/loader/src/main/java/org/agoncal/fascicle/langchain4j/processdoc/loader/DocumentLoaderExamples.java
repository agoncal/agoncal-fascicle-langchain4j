package org.agoncal.fascicle.langchain4j.processdoc.loader;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.loader.UrlDocumentLoader;
import dev.langchain4j.data.document.loader.azure.storage.blob.AzureBlobStorageDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.source.FileSystemSource;
import dev.langchain4j.data.document.source.UrlSource;
import dev.langchain4j.data.document.source.azure.storage.blob.AzureBlobStorageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentLoaderExamples {

  private static final Logger log = LoggerFactory.getLogger(DocumentLoaderExamples.class);

  public static void main(String[] args) throws MalformedURLException {
//    loadFromDocumentLoaderFile();
    loadFromDocumentLoaderFileNoParser();
//    loadFromDocumentLoaderURL();
//    loadFromDocumentLoaderAzure();
//    loadFromFileSystemDocumentLoader();
//    loadFromUrlDocumentLoader();
//    loadFromAzureDocumentLoader();
  }

  private static void loadFromDocumentLoaderFile() {
    System.out.println("\n### loadFromDocumentLoaderFile");
    // tag::adocLoadFromDocumentLoaderFile[]
    Path documentPath = toPath("data/bio-ella-fitzgerald.txt");

    Document document = DocumentLoader.load(new FileSystemSource(documentPath), new TextDocumentParser());

    log.info(document.text());
    log.info(document.metadata().getString(Document.FILE_NAME));
    log.info(document.metadata().getString(Document.ABSOLUTE_DIRECTORY_PATH));
    // end::adocLoadFromDocumentLoaderFile[]
  }

  private static void loadFromDocumentLoaderFileNoParser() {
    System.out.println("\n### loadFromDocumentLoaderFileNoParser");
    // tag::adocLoadFromDocumentLoaderFileNoParser[]
    Path documentPath = toPath("data/bio-ella-fitzgerald.txt");

    Document document = FileSystemDocumentLoader.loadDocument(documentPath);
    // end::adocLoadFromDocumentLoaderFileNoParser[]

    log.info(document.text().trim().substring(0, 50));
    log.info(document.metadata().getString(Document.FILE_NAME));
    log.info(document.metadata().getString(Document.ABSOLUTE_DIRECTORY_PATH));
  }

  private static void loadFromFileSystemDocumentLoader() {
    System.out.println("\n### loadFromFileSystemDocumentLoader");
    // tag::adocLoadFromFileSystemDocumentLoader[]
    Path documentPath = toPath("data/bio-ella-fitzgerald.txt");

    Document document = FileSystemDocumentLoader.loadDocument(documentPath, new TextDocumentParser());
    // end::adocLoadFromFileSystemDocumentLoader[]

    log.info(document.metadata().getString(Document.FILE_NAME));
    log.info(document.metadata().getString(Document.ABSOLUTE_DIRECTORY_PATH));
    log.info(document.text().trim().substring(0, 50));
  }

  private static void loadFromDocumentLoaderURL() throws Exception {
    System.out.println("\n### loadFromDocumentLoaderURL");
    // tag::adocLoadFromDocumentLoaderURL[]
    URL documentUrl = new URI("https://raw.githubusercontent.com/agoncal/agoncal-sample-langchain4j/main/rag-processing-documents/loader/src/main/resources/data/bio-duke-ellington.txt").toURL();

    Document document = DocumentLoader.load(new UrlSource(documentUrl), new TextDocumentParser());

    log.info(document.metadata().getString(Document.URL));
    // end::adocLoadFromDocumentLoaderURL[]
    log.info(document.text().trim().substring(0, 50));
  }

  private static void loadFromDocumentLoaderAzure() {
    System.out.println("\n### loadFromDocumentLoaderAzure");
    // tag::adocLoadFromDocumentLoaderAzure[]
    Document document = DocumentLoader.load(new AzureBlobStorageSource(null, null, null, null, null), new TextDocumentParser());

    log.info(document.metadata().getString("source"));
    log.info(document.metadata().getString("azure_storage_blob_creation_time"));
    log.info(document.metadata().getString("azure_storage_blob_last_modified"));
    log.info(document.metadata().getString("azure_storage_blob_content_length"));
    log.info(document.text().trim().substring(0, 50));
    // end::adocLoadFromDocumentLoaderAzure[]
  }

  private static void loadFromUrlDocumentLoader() throws Exception {
    System.out.println("\n### loadFromUrlDocumentLoader");
    // tag::loadFromUrlDocumentLoader[]
    URL documentUrl = new URI("https://raw.githubusercontent.com/agoncal/agoncal-sample-langchain4j/main/rag-processing-documents/loader/src/main/resources/data/bio-duke-ellington.txt").toURL();

    Document document = UrlDocumentLoader.load(documentUrl, new TextDocumentParser());
    // end::loadFromUrlDocumentLoader[]

    log.info(document.metadata().getString(Document.URL));
    log.info(document.text().trim().substring(0, 50));
  }

  private static void loadFromAzureDocumentLoader() throws Exception {
    System.out.println("\n### loadFromAzureDocumentLoader");
    // tag::loadFromAzureDocumentLoader[]
    URL documentUrl = new URI("https://en.wikipedia.org/wiki/Ella_Fitzgerald").toURL();

    Document document = new AzureBlobStorageDocumentLoader(null).loadDocument(null, null, new TextDocumentParser());

    log.info(document.metadata().getString("source"));
    log.info(document.metadata().getString("azure_storage_blob_creation_time"));
    log.info(document.metadata().getString("azure_storage_blob_last_modified"));
    log.info(document.metadata().getString("azure_storage_blob_content_length"));
    log.info(document.text().trim().substring(0, 50));
    // end::loadFromAzureDocumentLoader[]
  }

  private static Path toPath(String fileName) {
    try {
      URL fileUrl = DocumentLoaderExamples.class.getClassLoader().getResource(fileName);
      return Paths.get(fileUrl.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
