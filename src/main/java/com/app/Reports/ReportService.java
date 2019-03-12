package com.app.Reports;

import com.app.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportService {


    @Autowired
    private ReportsRepository reportsRepository;

    public  List<TotalBDWithinDateRange> getTotalBDInDateRange( ReportForm report,String name){

        List output= new ArrayList<>();
        switch(report.getReportName()){
            case "TotalBDInDateRange":
              reportsRepository.findByUserCenterLangNameandType(report.getStartDate(),report.getEndDate(),name)
                .ifPresent(objects -> objects
                        .forEach(e -> {
                            output.add(new TotalBDWithinDateRange((Integer) e[0], (String) e[1], (String) e[2], (String) e[3], (Long) e[4], (Long) e[5]));
                        }));
                break;
            case "TotalBDOfUserListInDateRange":
                List<User> userList =new ArrayList<>();
                Arrays.stream(report.getUsers()).forEach(e->userList.add(new User(e)));
                reportsRepository.findByUserListLangNameandType(report.getStartDate(),report.getEndDate(),userList)
                        .ifPresent(objects -> objects
                        .forEach(e -> {
                            output.add(new TotalBDWithinDateRange((Integer) e[0], (String) e[1], (String) e[2], (String) e[3], (Long) e[4], (Long) e[5],(Integer) e[6], (String) e[7]));
                        }));
                break;
            default :output.clear();
                break;
        }


        return output;
    }

}
