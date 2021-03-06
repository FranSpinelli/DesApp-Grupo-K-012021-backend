package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Report;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.ReportRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.InexistentElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.RepeatedElementException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.webservice.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ReportService extends AbstractService {

    @Autowired
    private ReportRepository reportRepository;

    @Transactional
    public ResponseEntity addNewReport(ReportDTO reportDTO) throws InexistentElementException, RepeatedElementException {

        Review reviewWithID = findReviewByID(reportDTO.getReviewID());

        checkForRepeatedReportInReview(reviewWithID, reportDTO.getPlatformReporterID(), reportDTO.getReporterNickName(), reportDTO.getSourcePlatform());

        Report aReport = new Report(reportDTO.getReportCause(), reportDTO.getSourcePlatform(), reportDTO.getPlatformReporterID(),
                    reportDTO.getReporterNickName());

        reviewWithID.addReport(aReport);
        Report savedReport = reportRepository.save(aReport);
        reviewRepository.save(reviewWithID);

        return ResponseEntity.ok().body(savedReport);
    }

    private void checkForRepeatedReportInReview(Review aReview, String aPlatformReporterID, String aReporterNickName, String aSourcePlatform) throws RepeatedElementException {
        Collection<Report> reports = aReview.getReports();

        if(reports.stream().anyMatch( report -> report.getPlatformReporterID().equals(aPlatformReporterID) &&
                        report.getReporterNickName().equals(aReporterNickName) &&
                        report.getSourcePlatform().equals(aSourcePlatform))){
            throw new RepeatedElementException("There is already a report in that Review of a writer with that id from that platform");
        }
    }
}
