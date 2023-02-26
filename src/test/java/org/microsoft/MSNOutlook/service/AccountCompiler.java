package org.microsoft.MSNOutlook.service;

import org.microsoft.MSNOutlook.model.MSAccount;

public class AccountCompiler {

    public static final String TESTDATA_FIRST_NAME = "testdata.msaccount.firstName";
    public static final String TESTDATA_SURNAME = "testdata.msaccount.surname";
    public static final String TESTDATA_EMAIL_NAME = "testdata.msaccount.emailName";
    public static final String TESTDATA_SKYPE_NAME = "testdata.msaccount.skypeName";
    public static final String TESTDATA_PHONE = "testdata.msaccount.phoneNumber";
    public static final String TESTDATA_DOMAIN = "testdata.msaccount.domain";
    public static final String TESTDATA_PASSWORD = "testdata.msaccount.password";
    public static final String TESTDATA_COUNTRY = "testdata.msaccount.country";
    public static final String TESTDATA_DAYOFBIRTH = "testdata.msaccount.dayOfBirth";
    public static final String TESTDATA_MONTHOFBIRTH = "testdata.msaccount.monthOfBirth";
    public static final String TESTDATA_YEAROFBIRTH = "testdata.msaccount.yearOfBirth";

    public static MSAccount withCredentialFromProperty() {
        return new MSAccount(TestDataReader.getTestData(TESTDATA_FIRST_NAME),
                TestDataReader.getTestData(TESTDATA_SURNAME),
                TestDataReader.getTestData(TESTDATA_EMAIL_NAME),
                TestDataReader.getTestData(TESTDATA_SKYPE_NAME),
                TestDataReader.getTestData(TESTDATA_PHONE),
                TestDataReader.getTestData(TESTDATA_DOMAIN),
                TestDataReader.getTestData(TESTDATA_PASSWORD),
                TestDataReader.getTestData(TESTDATA_COUNTRY),
                TestDataReader.getTestData(TESTDATA_DAYOFBIRTH),
                TestDataReader.getTestData(TESTDATA_MONTHOFBIRTH),
                TestDataReader.getTestData(TESTDATA_YEAROFBIRTH));
    }
}
