package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;



@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {



    @Transactional
    @Query("Select m from Meal m WHERE m.id=:id and m.user.id=:userId")
     Meal findMealByIdAndUser_id(@Param("id")int id,@Param("userId") int userId);

    @Transactional
    @Query(Meal.ALL_SORTED)
    List<Meal> findByUser_idOrderByDateTimeDesc(@Param("userId")int userId);

    @Transactional
    default Meal save(Meal meal, int userId){
        User ref = getUserByID(userId);
        meal.setUser(ref);
       return save(meal);
    }
    @Transactional
    @Query("SELECT u From User u Where u.id=:id")
    User getUserByID(@Param("id") int userId);

    @Transactional
    @Override
    Meal save(Meal meal);

    @Transactional
    @Query(Meal.GET_BETWEEN)
    List<Meal> getBetween(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("userId") int userId);

    @Transactional
    @Query(Meal.DELETE)
    int delete(@Param("id")int id,@Param("userId")int userId);
}
