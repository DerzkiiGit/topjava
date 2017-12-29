package ru.javawebinar.topjava.web.meal;

import javafx.application.Application;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_MEAL_URL)
public class MealRestController extends AbstractMealController {

    static final String REST_MEAL_URL = "/rest/meals";

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getMeals(){
        return super.getAll();
    }


    @RequestMapping(value = "/filter",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(@RequestParam("fromDate") @DateTimeFormat(pattern = "'\"'yyyy-MM-dd'T'HH:mm:ss'\"'")
                                                       LocalDateTime fromDate,
                                           @RequestParam("toDate") @DateTimeFormat(pattern = "'\"'yyyy-MM-dd'T'HH:mm:ss'\"'")
                                                   LocalDateTime toDate){

    return super.getBetween(fromDate.toLocalDate(),fromDate.toLocalTime(),toDate.toLocalDate(),toDate.toLocalTime());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> deleteMeal(@PathVariable("id")int id){
        super.delete(id);
        return getMeals();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal getMeal(@PathVariable("id") int id){
        return super.get(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMeal(@RequestBody Meal meal, @PathVariable("id")int id){
         super.update(meal,id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> saveMeal(@RequestBody Meal meal){
        Meal createdMeal = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_MEAL_URL + "/{id}")
                .buildAndExpand(createdMeal.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(createdMeal);
    }

}