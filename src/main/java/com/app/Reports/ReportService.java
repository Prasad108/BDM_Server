package com.app.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportService {


    @Autowired
    ReportsRepository reportsRepository;

    public String getTotalBDInDateRange( ReportForm report,String name){
        Optional<List<Object[]>> result = reportsRepository.findByUserCenterLangNameandType(report.getStartDate(),report.getEndDate(),name);
        return "";
    }

}
