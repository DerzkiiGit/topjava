package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by SpooN on 12.11.2017.
 */
public class AdminRestController  extends AbstractMealController{
    @Override
    public List<Meal> getAll(int userId) {
        return super.getAll(userId);
    }

    @Override
    public Meal get(int id, int userid) {
        return super.get(id, userid);
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public void delete(int id, int userID) {
        super.delete(id, userID);
    }

    @Override
    public void update(Meal meal, int id) {
        super.update(meal, id);
    }
}
