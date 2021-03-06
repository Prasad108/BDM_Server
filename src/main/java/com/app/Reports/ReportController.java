package com.app.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value="/getTotalBDInDateRange",method= RequestMethod.POST, produces="application/json")
    public List<TotalBDWithinDateRange> getTotalBDInDateRange(@Valid @RequestBody ReportForm report, Principal principal){
        return reportService.getTotalBDInDateRange(report, principal.getName());
    }



}
