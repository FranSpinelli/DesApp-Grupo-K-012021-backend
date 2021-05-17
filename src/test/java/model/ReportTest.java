package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Report;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    public void ReportConstructorGettersAndSettersTest(){

        Report aReport = new Report("aCause", "netflix", "n12", "fran");

        assertEquals("aCause", aReport.getCause());
        assertEquals("netflix", aReport.getSourcePlatform());
        assertEquals("n12", aReport.getPlatformReporterID());
        assertEquals("fran", aReport.getReporterNickName());
        assertNull(aReport.getId());

        aReport.setCause("cause");
        aReport.setId(1);
        aReport.setNickName("grace");
        aReport.setPlatformReporterID("n1");
        aReport.setSourcePlatform("amazon prime");

        assertEquals("cause", aReport.getCause());
        assertEquals("amazon prime", aReport.getSourcePlatform());
        assertEquals("n1", aReport.getPlatformReporterID());
        assertEquals("grace", aReport.getReporterNickName());
        assertEquals(1, aReport.getId());
    }

}