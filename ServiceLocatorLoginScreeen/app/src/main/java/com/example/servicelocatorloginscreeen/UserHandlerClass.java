package com.example.servicelocatorloginscreeen;

public class UserHandlerClass {

    String name, usern, useremail, phone, physicaladdress, userpassword, idenNumber;

    public UserHandlerClass() {
    }

    public UserHandlerClass(String name, String usern, String useremail, String phone, String physicaladdress, String userpassword, String idenNumber) {
        this.name = name;
        this.usern = usern;
        this.useremail = useremail;
        this.phone = phone;
        this.physicaladdress = physicaladdress;
        this.userpassword = userpassword;
        this.idenNumber = idenNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhysicaladdress() {
        return physicaladdress;
    }

    public void setPhysicaladdress(String physicaladdress) {
        this.physicaladdress = physicaladdress;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getIdenNumber() {
        return idenNumber;
    }

    public void setIdenNumber(String idenNumber) {
        this.idenNumber = idenNumber;
    }
}
