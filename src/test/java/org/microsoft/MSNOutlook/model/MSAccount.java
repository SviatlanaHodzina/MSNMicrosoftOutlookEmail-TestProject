package org.microsoft.MSNOutlook.model;

import java.util.Objects;

public class MSAccount {
    private String emailName;
    private String skypeName;
    private String phoneNumber;
    private String domain;
    private String password;

    public MSAccount(String emailName, String skypeName, String phoneNumber, String domain,
                    String password) {
        this.emailName = emailName;
        this.skypeName = skypeName;
        this.phoneNumber = phoneNumber;
        this.domain = domain;
        this.password = password;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getSkypeName() {
        return skypeName;
    }

    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MSAccount{" +
                "emailName='" + emailName + '\'' +
                ", skypeName='" + skypeName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", domain='" + domain + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MSAccount msAccount = (MSAccount) o;
        return Objects.equals(emailName, msAccount.emailName) && Objects.equals(skypeName, msAccount.skypeName) && Objects.equals(phoneNumber, msAccount.phoneNumber) && Objects.equals(domain, msAccount.domain)  && Objects.equals(password, msAccount.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailName, skypeName, phoneNumber, domain, password);
    }
}