package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class,userId);
        meal.setUser(ref);
        if (meal.isNew()){
             em.persist(meal);
            return meal;
        }else{
            return em.merge(meal);
        }

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Meal meal=  em.find(Meal.class,id);
        User ref = em.getReference(User.class,userId);

        if(meal!=null&&ref!=null&&meal.getUser().equals(ref)){
            em.remove(meal);
            return true;
        }

        return false;
    }

    @Override
    public Meal get(int id, int userId) {
      Meal meal=  em.find(Meal.class,id);
      User ref = em.getReference(User.class,userId);
        if(meal!=null&&ref!=null&&meal.getUser().equals(ref)){
            return meal;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        Query q = em.createQuery("SELECT m FROM Meal m WHERE m.user.id =  :user_id");
        q.setParameter("user_id",userId);

        return q.getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query q = em.createQuery("select m from Meal m Where m.user.id =: user_id and m.dateTime > :startDate and m.dateTime > :endDate");
        q.setParameter("user_id",userId);
        q.setParameter("startDate",startDate);
        q.setParameter("endDate",endDate);


        return q.getResultList();
    }
}