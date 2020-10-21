package com.example.sonaraapp.commonclasses;

public class Customer {
    String name,mobile,serialno,problem,workdone,estimate,remarks,paid,callnumber;

    public Customer(String name, String mobile, String serialno, String problem, String workdone, String estimate, String remarks, String paid, String callnumber) {
        this.name = name;
        this.mobile = mobile;
        this.serialno = serialno;
        this.problem = problem;
        this.workdone = workdone;
        this.estimate = estimate;
        this.remarks = remarks;
        this.paid = paid;
        this.callnumber = callnumber;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getWorkdone() {
        return workdone;
    }

    public void setWorkdone(String workdone) {
        this.workdone = workdone;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getCallnumber() {
        return callnumber;
    }

    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }



}
