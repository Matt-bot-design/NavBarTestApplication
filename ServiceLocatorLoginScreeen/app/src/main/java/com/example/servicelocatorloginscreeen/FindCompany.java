package com.example.servicelocatorloginscreeen;

public class FindCompany {

    String profileName, profileServices;

    public FindCompany() {
    }

    public FindCompany(String profileName, String profileServices) {
        this.profileName = profileName;
        this.profileServices = profileServices;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileServices() {
        return profileServices;
    }

    public void setProfileServices(String profileServices) {
        this.profileServices = profileServices;
    }
}
