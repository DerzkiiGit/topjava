package ru.javawebinar.topjava.web.meal;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.of;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;


public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_MEAL_URL + '/';

    @Test
    public void getMeals() throws Exception {
     MvcResult mvcResult= mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
            assertMatch(MEALS, getListMeals(mvcResult));
    }

    @Test
    public void getBetween() throws Exception {
        LocalDateTime fromDate = of(2015, Month.MAY, 31, 19, 0);
        LocalDateTime toDate = of(2015, Month.MAY, 31, 21, 0);


        MvcResult mvcResult= mockMvc.perform(post(REST_URL+"filter")
                .contentType(MediaType.APPLICATION_JSON)
                .param("fromDate",JsonUtil.writeValue(fromDate))
                .param("toDate",JsonUtil.writeValue(toDate)))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertMatch(getListMeals(mvcResult), MEAL6);
    }

    @Test
    public void deleteMeal() throws Exception {

        MvcResult result = mockMvc.perform(delete(REST_URL+MEAL6.getId()))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List <Meal> meals = new ArrayList<>(MEALS);

        meals.remove(MEAL6);

        assertMatch(getListMeals(result),meals);

    }

    @Test
    public void getMeal() throws Exception {
        MvcResult result = mockMvc.perform(get(REST_URL+MEAL6.getId()))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertMatch(MealTestData.getMeal(result),MEAL6);
    }

    @Test
    public void updateMeal() throws Exception {
        Meal meal = MEAL6;
        meal.setDescription("Updated Description");

        mockMvc.perform(put(REST_URL+meal.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeIgnoreProps(meal,"user")))
                .andDo(print())
                .andExpect(status().isOk());
      MvcResult result=  mockMvc.perform(get(REST_URL+meal.getId())).andReturn();
        assertMatch(MealTestData.getMeal(result),meal);
        MEAL6.setDescription("Ужин");
    }

    @Test
    public void saveMeal() throws Exception {
        Meal meal = new Meal(of(2017, Month.MAY, 31, 19, 0),"fresh supper", 1001);

        ResultActions action= mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeIgnoreProps(meal,"user")))
                .andDo(print())
                .andExpect(status().isCreated());

       Meal returned = TestUtil.readFromJson(action, Meal.class);
       meal.setId(returned.getId());

        assertMatch(returned,meal);
        assertMatch(mealService.getAll(AuthorizedUser.id()),meal,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1);
    }



    }

