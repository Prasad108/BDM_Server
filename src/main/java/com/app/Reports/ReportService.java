package com.app.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportService {


    @Autowired
    private ReportsRepository reportsRepository;

    public  List<TotalBDWithinDateRange> getTotalBDInDateRange( ReportForm report,String name){
        Optional<List<Object[]>> result = reportsRepository.findByUserCenterLangNameandType(report.getStartDate(),report.getEndDate(),name);
        List<TotalBDWithinDateRange> output= new ArrayList<>();
        result.ifPresent(objects -> objects
                .forEach(e -> {
                    output.add(new TotalBDWithinDateRange((Integer) e[0], (String) e[1], (String) e[2], (String) e[3], (Long) e[4], (Long) e[5]));
                }));
        return output;
    }

}
