package com.example.expandablerecyclerview.model;

import java.util.List;

public class Reports {

    private int facilityId ;
    private int facilityDepartmentId ;
    private  int auditId ;

    private List<SkuReports> skuReports;

    public List<SkuReports> getSkuReports() {
        return skuReports;
    }

    public void setSkuReports(List<SkuReports> skuReports) {
        this.skuReports = skuReports;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getFacilityDepartmentId() {
        return facilityDepartmentId;
    }

    public void setFacilityDepartmentId(int facilityDepartmentId) {
        this.facilityDepartmentId = facilityDepartmentId;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }
}
