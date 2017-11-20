package ru.javawebinar.topjava.repository.jdbc.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ru.javawebinar.topjava.model.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Created by SpooN on 20.11.2017.
 */
public class MealRowMapper implements RowMapper<Meal> {
    @Override
    public Meal mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            LocalDateTime dateTime =resultSet.getTimestamp("date_and_time").toLocalDateTime();
            String description = resultSet.getString("description");
            int calories = resultSet.getInt("calories");
            Meal meal = new Meal(id,dateTime,description,calories);

        return meal;
    }
}
