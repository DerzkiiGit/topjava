package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;


public class ProfileRestController extends AbstractMealController{


    public Meal get(int id) {
        return super.get(id,AuthorizedUser.id());
    }

    public void delete(int id) {
        super.delete(id,AuthorizedUser.id());
    }

    public void update(Meal meal) {
        super.update(meal, AuthorizedUser.id());
    }

}