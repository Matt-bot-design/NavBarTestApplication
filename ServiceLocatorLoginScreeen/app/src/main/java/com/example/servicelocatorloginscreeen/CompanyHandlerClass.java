package com.example.servicelocatorloginscreeen;

public class CompanyHandlerClass {

    String companyname, companyusern, companyemailaddress, companyphonenumber, companyphysicaladdress, companypassword, companyregistrationnumber, companyservices;

    public CompanyHandlerClass() {
    }

    public CompanyHandlerClass(String companyname, String companyusern, String companyemailaddress, String companyphonenumber, String comapanyphysicaladdress, String companypassword, String companyregistrationnumber, String companyservices) {
        this.companyname = companyname;
        this.companyusern = companyusern;
        this.companyemailaddress = companyemailaddress;
        this.companyphonenumber = companyphonenumber;
        this.companyphysicaladdress = comapanyphysicaladdress;
        this.companypassword = companypassword;
        this.companyregistrationnumber = companyregistrationnumber;
        this.companyservices = companyservices;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyusern() {
        return companyusern;
    }

    public void setCompanyusern(String companyusern) {
        this.companyusern = companyusern;
    }

    public String getCompanyemailaddress() {
        return companyemailaddress;
    }

    public void setCompanyemailaddress(String companyemailaddress) {
        this.companyemailaddress = companyemailaddress;
    }

    public String getCompanyphonenumber() {
        return companyphonenumber;
    }

    public void setCompanyphonenumber(String companyphonenumber) {
        this.companyphonenumber = companyphonenumber;
    }

    public String getCompanyphysicaladdress() {
        return companyphysicaladdress;
    }

    public void setCompanyphysicaladdress(String companyphysicaladdress) {
        this.companyphysicaladdress = companyphysicaladdress;
    }

    public String getCompanypassword() {
        return companypassword;
    }

    public void setCompanypassword(String companypassword) {
        this.companypassword = companypassword;
    }

    public String getCompanyregistrationnumber() {
        return companyregistrationnumber;
    }

    public void setCompanyregistrationnumber(String companyregistrationnumber) {
        this.companyregistrationnumber = companyregistrationnumber;
    }

    public String getCompanyservices() {
        return companyservices;
    }

    public void setCompanyservices(String companyservices) {
        this.companyservices = companyservices;
    }
}
