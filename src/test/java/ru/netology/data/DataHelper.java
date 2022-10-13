package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHelper {

    private DataHelper() {
    }

    private static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    private static String login() {
        return faker.name().username();
    }

    private static String password() {
        return faker.internet().password();
    }

    public static AuthInfo user() {
        return new AuthInfo(login(), password());
    }

    public static AuthInfo getAuthInfoWithTestData() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }

    public static void assertStatus(String login) {
        String expected = "blocked";
        String actual = SQLHelper.userStatus(login);
        assertEquals(expected, actual);
    }

}

