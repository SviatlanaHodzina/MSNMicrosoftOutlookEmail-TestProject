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
    private String country;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;

    public MSAccount(String firstName, String surname, String emailName, String skypeName, String phoneNumber,
                     String domain, String password, String country, String dayOfBirth, String monthOfBirth,
                     String yearOfBirth) {

        this.firstName = firstName;
        this.surname = surname;
        this.emailName = emailName;
        this.skypeName = skypeName;
        this.phoneNumber = phoneNumber;
        this.domain = domain;
        this.password = password;
        this.country = country;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
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

    public String getSkypeName() {
        return skypeName;
    }

    public String getCountry() {
        return country;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
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
                ", country='" + country + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", monthOfBirth='" + monthOfBirth + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
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
                && Objects.equals(password, msAccount.password) && Objects.equals(country, msAccount.country)
                && Objects.equals(dayOfBirth, msAccount.dayOfBirth) && Objects.equals(monthOfBirth, msAccount.monthOfBirth)
                && Objects.equals(yearOfBirth, msAccount.yearOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, emailName, skypeName, phoneNumber, domain, password,
                country, dayOfBirth, monthOfBirth, yearOfBirth);
    }
}
