package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {

        if(repository.get(id).getUserId()==userId){
           return repository.remove(id)!=null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal =repository.get(id);
        if(meal.getUserId()==userId)return meal;
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.values().stream().filter(meal->meal.getUserId()==userId).sorted((m1,m2)->m1.getDateTime().compareTo(m2.getDateTime())).collect(Collectors.toCollection(ArrayList::new));
    }
}

