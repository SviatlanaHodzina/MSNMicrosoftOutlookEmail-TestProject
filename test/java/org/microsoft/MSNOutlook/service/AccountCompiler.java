package org.microsoft.MSNOutlook.service;

import org.microsoft.MSNOutlook.model.MSAccount;

public class AccountCompiler {

    public static final String TESTDATA_EMAIL_NAME = "testdata.msaccount.emailName";
    public static final String TESTDATA_SKYPE_NAME = "testdata.msaccount.skypeName";
    public static final String TESTDATA_PHONE = "testdata.msaccount.phoneNumber";
    public static final String TESTDATA_DOMAIN = "testdata.msaccount.domain";
    public static final String TESTDATA_PASSWORD = "testdata.msaccount.password";

    public static MSAccount withCredentialFromProperty() {
        return new MSAccount(TestDataReader.getTestData(TESTDATA_EMAIL_NAME),
                TestDataReader.getTestData(TESTDATA_SKYPE_NAME),
                TestDataReader.getTestData(TESTDATA_PHONE),
                TestDataReader.getTestData(TESTDATA_DOMAIN),
                TestDataReader.getTestData(TESTDATA_PASSWORD));
    }
}