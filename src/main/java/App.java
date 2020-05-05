import dao.SightingDao;
import dao.Sql2oSightingDao;
import models.Animal;
import models.Sighting;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";
        Sql2o sql2o = new Sql2o(connectionString, null, null);

        //String connectionString = "jdbc:postgresql://ec2-18-210-214-86.compute-1.amazonaws" +".com:5432/dc2222net8b3rj\n";
        //Sql2o sql2o = new Sql2o(connectionString, "aiohbpwvvhhfal","aade4b2df7a4efd53b3613515f131a0554a1ef65c2a2993583f6d244a5ac1229");
        Sql2oSightingDao sightingDao = new Sql2oSightingDao(sql2o);

        //get: show all sightings
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Sighting> sightings = sightingDao.getAll();
            model.put("sightings", sightings);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get:new form to add a sighting
        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post:process form to add new sighting
        post("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Sighting> sightings = sightingDao.getAll();
            model.put("sightings", sightings);
            String category = request.queryParams("category");
            String species = request.queryParams("species");
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String ranger = request.queryParams("ranger");
            Sighting newSighting = new Sighting(category, species, location, health, age, ranger);
            sightingDao.add(newSighting);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a sighting
        get("/sightings/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Sighting> sightings = sightingDao.getAll();
            model.put("sightings", sightings);
            int idOfSightingToEdit = Integer.parseInt(request.params("id"));
            Sighting editSighting = sightingDao.findById(idOfSightingToEdit);
            model.put("editSighting", editSighting);
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a sighting
        post("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String newCategory = request.queryParams("category");
            String newSpecies = request.queryParams("species");
            String newLocation = request.queryParams("location");
            String newHealth = request.queryParams("health");
            String newAge = request.queryParams("age");
            String newRanger = request.queryParams("ranger");
            int idOfSightingToEdit = Integer.parseInt(request.params("id"));
            sightingDao.update(idOfSightingToEdit, newCategory, newSpecies, newLocation,
                    newHealth, newAge,
                    newRanger);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual sighting
        get("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfSightingToDelete = Integer.parseInt(request.params("id"));
            sightingDao.deleteById(idOfSightingToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
