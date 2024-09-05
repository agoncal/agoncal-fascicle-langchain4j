package org.agoncal.fascicle.langchain4j.testingdebugging;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WireMockTest
class AuthorServiceTest {

  @BeforeAll
  static void beforeAll() {
//    wireMockServer = new WireMockServer(options().port(WIREMOCK_PORT));
//    wireMockServer.start();
  }

  @AfterAll
  static void afterAll() {

//    wireMockServer.stop();
  }

  @BeforeEach
  void setup() {
//    wireMockServer.resetAll();
//    wireMockServer.stubFor(WiremockUtils.defaultBiographyStub());
  }

  @Test
  public void shouldGetIsaacAsimovBiography(WireMockRuntimeInfo wmRuntimeInfo) {
//    stubFor(any(anyUrl()).willReturn(okJson("{}")));
//    stubFor(any(anyUrl()).willReturn(okJson("" +
//      "{" +
//      "  \"paymentId\": \"2222\"," +
//      "  \"paymentResponseStatus\": \"SUCCESS\"" +
//      "}")));
    String url = wmRuntimeInfo.getHttpBaseUrl();
    System.out.println("url = " + url);
    WireMock wireMock = wmRuntimeInfo.getWireMock();
    wireMock.startStubRecording("https://api.openai.com/v1/chat/completions");

//    String bio = new AuthorService(url).getAuthorBiography(0);
    String bio = new AuthorService().getAuthorBiography(0);
    System.out.println(bio);

    wireMock.stopStubRecording();
//      .body(is("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
  }
}
