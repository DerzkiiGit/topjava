package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SpooN on 12.11.2017.
 */
public class UserUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(null,"Vasya","Vasya@mail.ru","pw", Role.ROLE_USER),
            new User(null,"Petya","Petya@mail.ru","pw",Role.ROLE_ADMIN),
            new User(null,"Ilya","Ilya@mail.ru","pw",Role.ROLE_ADMIN, Role.ROLE_USER)

    );
}
