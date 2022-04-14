package org.microsoft.MSNOutlook.model;

import java.util.Objects;

public class MSAccount {
    private String firstName;
    private String surname;
    private String emailName;
    private String skypeName;
    private String phoneNumber;
    private String domain;
    private String password;

    public MSAccount(String firstName, String surname, String emailName,
                     String skypeName, String phoneNumber, String domain, String password) {

        this.firstName = firstName;
        this.surname = surname;
        this.emailName = emailName;
        this.skypeName = skypeName;
        this.phoneNumber = phoneNumber;
        this.domain = domain;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmailName() {
        return emailName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getDomain() {
        return domain;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "MSAccount{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", emailName='" + emailName + '\'' +
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
        return Objects.equals(firstName, msAccount.firstName) && Objects.equals(surname, msAccount.surname)
                && Objects.equals(emailName, msAccount.emailName) && Objects.equals(skypeName, msAccount.skypeName)
                && Objects.equals(phoneNumber, msAccount.phoneNumber) && Objects.equals(domain, msAccount.domain)
                && Objects.equals(password, msAccount.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, emailName, skypeName, phoneNumber, domain, password);
    }
}
