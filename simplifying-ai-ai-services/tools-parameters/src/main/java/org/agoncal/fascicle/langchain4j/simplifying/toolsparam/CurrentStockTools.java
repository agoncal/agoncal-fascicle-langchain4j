package org.agoncal.fascicle.langchain4j.simplifying.toolsparam;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import java.math.BigDecimal;
import java.util.List;

// @formatter:off
// tag::adocSnippet[]
public class CurrentStockTools {

  @Tool("Returns the books that are available in stock")
  public List<Book> booksInStockByTitle(
                    @P("The title of the book") String bookTitle) {
    return selectBooksInStockByTitleFromTheDatabase(bookTitle);
  }

  @Tool("Returns the books that are available in stock")
  public List<Book> booksInStockByAuthorAndYear(
                    @P("The author of the book") String authorName,
                    @P("The year of publication of the book") int year) {
    return selectBooksInStockByAuthorAndYearFromTheDatabase(authorName, year);
  }

  @Tool("Returns the CDs that are available in stock")
  public List<CD> cdsInStockByTitle(
                    @P("The title of the CD album") String albumTitle) {
    return selectCDsInStockByTitleFromTheDatabase(albumTitle);
  }

  @Tool("Returns the CDs that are available in stock")
  public List<CD> cdsInStockByArtistAndYear(
                    @P("The artist or band of the CD album") String artistName,
                    @P("The year the album was released") int year) {
    return selectCDsInStockByArtistAndYearFromTheDatabase(artistName, year);
  }
  // tag::adocSkip[]

  private List<Book> selectBooksInStockByTitleFromTheDatabase(String bookTitle) {
    System.out.println("### selectBooksInStockByTitleFromTheDatabase: " + bookTitle);
    return List.of(
      new Book("The Lord of the Galaxy", "Douglas Adams", "English", new BigDecimal("12.5")),
      new Book("The Lord of the Rings", "J.R.R. Tolkien", "English", new BigDecimal("15.0")),
      new Book("The Lord in the Rye", "J.D. Salinger", "English", new BigDecimal("10.0"))
    );
  }

  private List<Book> selectBooksInStockByAuthorAndYearFromTheDatabase(String authorName, int year) {
    System.out.println("### selectBooksInStockByAuthorAndYearFromTheDatabase: " + authorName + " " + year);
    return List.of(
      new Book("The Lord of the Galaxy", "Douglas Adams", "English", new BigDecimal("12.5")),
      new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "English", new BigDecimal("10.0"))
    );
  }

  private List<CD> selectCDsInStockByTitleFromTheDatabase(String albumTitle) {
    System.out.println("### selectCDsInStockByTitleFromTheDatabase: " + albumTitle);
    return List.of(
      new CD("Help", "The Beatles", 1965, new BigDecimal("12.5")),
      new CD("Help", "The Beatles Revisited", 1973, new BigDecimal("10.0"))
    );
  }

  private List<CD> selectCDsInStockByArtistAndYearFromTheDatabase(String artistName, int year) {
    System.out.println("### selectCDsInStockByArtistAndYearFromTheDatabase: " + artistName + " " + year);
    return List.of(
      new CD("Help", "The Beatles", 1965, new BigDecimal("12.5")),
      new CD("Rubber Soul", "The Beatles", 1963, new BigDecimal("10.0"))
    );
  }
  // end::adocSkip[]
}
// end::adocSnippet[]
