package coms309.roundTrip.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ingredientName;
    private int calsPer100Gram;

    public Ingredient() {
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getCalsPer100Gram() {
        return calsPer100Gram;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setCalsPer100Gram(int calsPer100Gram) {
        this.calsPer100Gram = calsPer100Gram;
    }
}
