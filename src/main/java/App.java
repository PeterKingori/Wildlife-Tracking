import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: show all sightings
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Sighting> sightings = Sighting.getAll();
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
            String category = request.queryParams("category");
            String species = request.queryParams("species");
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String ranger = request.queryParams("ranger");
            Sighting newSighting = new Sighting(category, species, location, health, age, ranger);
            Animal newAnimal = new Animal(species);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a sighting
        get("/sightings/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfSightingToEdit = Integer.parseInt(request.params("id"));
            Sighting editSighting = Sighting.findById(idOfSightingToEdit);
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
            Sighting editSighting = Sighting.findById(idOfSightingToEdit);
            editSighting.update(newCategory, newSpecies, newLocation, newHealth, newAge, newRanger);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual sighting

    }
}
