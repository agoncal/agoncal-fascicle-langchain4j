package org.agoncal.fascicle.langchain4j.enriching.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LegalDocumentToolsTest {

  private LegalDocumentTools tools;

  @BeforeEach
  void setUp() {
    tools = new LegalDocumentTools();
  }

  @Test
  void shouldReturnPrivacyLastUpdate() {
    LocalDate date = tools.lastUpdatePrivacy();
    assertNotNull(date);
    assertEquals(LocalDate.of(2013, 3, 9), date);
  }

  @Test
  void shouldReturnTermsLastUpdate() {
    LocalDate date = tools.lastUpdateTerms();
    assertNotNull(date);
    assertEquals(LocalDate.of(2014, 6, 19), date);
  }

  @Test
  void shouldReturnEndUserLicenseAgreementLastUpdate() {
    LocalDate date = tools.lastEndUserLicenseAgreement();
    assertNotNull(date);
    assertEquals(LocalDate.of(2021, 11, 23), date);
  }
}
