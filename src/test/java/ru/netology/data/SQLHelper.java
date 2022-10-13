package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConnect() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode() {
        var querySQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (var conn = getConnect()) {
            var result = runner.query(conn, querySQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(result);
        }
    }

    @SneakyThrows
    public static void cleanDB() {
        var connection = getConnect();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }

    @SneakyThrows
    public static String userStatus(String login) {
        var connection = getConnect();
        String data = "SELECT status FROM users WHERE login = ?;";
        return runner.query(connection, data, new ScalarHandler<>(), login);
    }

}