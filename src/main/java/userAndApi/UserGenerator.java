package userAndApi;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator extends User {
    public User getRandomUser() {
        String email = RandomStringUtils.randomAlphabetic(8) + "@mail.ru";
        String password = RandomStringUtils.randomAlphabetic(9);
        String name = RandomStringUtils.randomAlphabetic(7);
        return new User(email, password, name);
    }
}