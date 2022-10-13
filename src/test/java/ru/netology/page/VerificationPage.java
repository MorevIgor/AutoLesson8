package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {

    private SelenideElement verifyCode = $x("//span[@data-test-id=\"code\"]//self::input");
    private SelenideElement buttonVerify = $x("//*[@data-test-id=\"action-verify\"]");
    private SelenideElement errorVerify = $x("//*[@data-test-id=\"error-notification\"]");

    public void VerificationPage() {
        verifyCode.shouldBe(visible);
    }

    public void getError() {
        errorVerify.shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    public void verifyPage(String verificationCode) {
        verifyCode.setValue(verificationCode);
        buttonVerify.click();
    }

    public DashboardPage validVerify(String verificationCode) {
        verifyPage(verificationCode);
        return new DashboardPage();
    }

}
