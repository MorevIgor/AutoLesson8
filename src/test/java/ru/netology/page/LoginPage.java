package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement login = $x("//*[@data-test-id=\"login\"]//self::input");
    private SelenideElement password = $x("//*[@data-test-id=\"password\"]//self::input");
    private SelenideElement button = $x("//*[@data-test-id=\"action-login\"]");
    private SelenideElement error = $x("//*[@data-test-id=\"error-notification\"]");

    public VerificationPage loginValid(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue((info.getPassword()));
        button.click();
        return new VerificationPage();
    }

    public void cleanStrings() {
        login.doubleClick().sendKeys(Keys.DELETE);
        password.doubleClick().sendKeys(Keys.DELETE);
    }

    public void getError() {
        error.shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    public void getBlockError() {
        error.shouldHave(Condition.text("Вы ввели неверный пароль 3 раза. Учётная запись заблокирована.")).shouldBe(Condition.visible, Duration.ofSeconds(5));
    }
}


