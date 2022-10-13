package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static ru.netology.data.SQLHelper.cleanDB;
import static com.codeborne.selenide.Selenide.open;

public class DeadLineTest {

    @AfterAll
    static void tearDown() {
        cleanDB();
    }

    @Test
    public void validLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.loginValid(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    public void notValidLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.user().getLogin(), DataHelper.getAuthInfoWithTestData().getPassword());
        loginPage.loginValid(authInfo);
        loginPage.getError();
    }

    @Test
    public void notValidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.user().getPassword());
        loginPage.loginValid(authInfo);
        loginPage.getError();
    }

    @Test
    public void notValidVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.loginValid(authInfo);
        verificationPage.VerificationPage();
        var verificationCode = DataHelper.getVerificationCode().getCode();
        verificationPage.verifyPage(verificationCode);
        verificationPage.getError();
    }

    @Test
    public void notValidPasswordOverThree() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfoFirst = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.user().getPassword());
        loginPage.loginValid(authInfoFirst);
        loginPage.getError();
        loginPage.cleanStrings();
        var authInfoSecond = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.user().getPassword());
        loginPage.loginValid(authInfoSecond);
        loginPage.getError();
        loginPage.cleanStrings();
        var authInfoThird = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.user().getPassword());
        loginPage.loginValid(authInfoThird);
        loginPage.getBlockError();
    }
}
