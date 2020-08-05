package com.OnlineMarket.AdminService;

public class ActiveParameters {
    int activeUserID;
    String activeNameAndSurname;
    String activeRole;

    protected ActiveParameters(){}

    public int getActiveUserID() {
        return activeUserID;
    }
    public void setActiveUserID(int activeUserID) {
        this.activeUserID = activeUserID;
    }

    public String getActiveNameAndSurname() {
        return activeNameAndSurname;
    }
    public void setActiveNameAndSurname(String activeKorisnikNameAndSurname) {
        this.activeNameAndSurname = activeKorisnikNameAndSurname;
    }

    public String getActiveRole() {
        return activeRole;
    }
    public void setActiveRole(String activeRole) {
        this.activeRole = activeRole;
    }
}
