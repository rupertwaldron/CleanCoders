package com.ruppyrup.episode23.dummy;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class HourlyReportInteractorTest {

  private HourlyReportInteractor interactor;

  @BeforeEach
  public void setupHourlyReportInteractor() {
    interactor = new HourlyReportInteractor();
  }

  @Test
  public void testInvalidDate() {
    Date reportDate = null;
    Session session = new DummySession(); // we aren't using this, we just want the code to compile
    Assertions.assertThrows(HourlyReportInteractor.InvalidDate.class, () -> interactor.generateReport(reportDate, session))
    ;
  }
}
