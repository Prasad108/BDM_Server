package com.app.Reports;

import javax.validation.constraints.NotBlank;

import java.util.Date;

public class ReportForm {

    @NotBlank
    private String reportName;

    private Date startDate;
    private Date endDate;
    private int[] users;

    public ReportForm() {
    }

    public ReportForm(@NotBlank String reportName, Date startDate, Date endDate, int[] users) {
        this.reportName = reportName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int[] getUsers() {
        return users;
    }

    public void setUsers(int[] users) {
        this.users = users;
    }
}
