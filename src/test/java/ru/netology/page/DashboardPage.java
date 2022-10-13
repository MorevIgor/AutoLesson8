package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private SelenideElement heading = $x("//*[@data-test-id=\"dashboard\"]");

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }
}
