package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealServiceImpl implements MealService {

    @Autowired
    private  MealRepository repository;

    @Override
    public Meal create(Meal meal) {
        return  repository.save(meal);
    }

    @Override
    public void delete(int id, int userID) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id,userID),id);
    }

    @Override
    public Meal get(int id, int userID) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id,userID),id);
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }
}