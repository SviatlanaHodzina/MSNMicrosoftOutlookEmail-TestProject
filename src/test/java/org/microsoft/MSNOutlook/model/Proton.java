package org.microsoft.MSNOutlook.model;

import java.util.Objects;

public class Proton {
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

    public Proton(String firstName, String surname, String emailName, String skypeName, String phoneNumber,
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
        return "Proton{" +
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
        Proton protonAccount = (Proton) o;
        return Objects.equals(firstName, protonAccount.firstName) && Objects.equals(surname, protonAccount.surname)
                && Objects.equals(emailName, protonAccount.emailName) && Objects.equals(skypeName, protonAccount.skypeName)
                && Objects.equals(phoneNumber, protonAccount.phoneNumber) && Objects.equals(domain, protonAccount.domain)
                && Objects.equals(password, protonAccount.password) && Objects.equals(country, protonAccount.country)
                && Objects.equals(dayOfBirth, protonAccount.dayOfBirth) && Objects.equals(monthOfBirth, protonAccount.monthOfBirth)
                && Objects.equals(yearOfBirth, protonAccount.yearOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, emailName, skypeName, phoneNumber, domain, password,
                country, dayOfBirth, monthOfBirth, yearOfBirth);
    }
}