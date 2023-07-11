package com.example.frontend.roundtrip;

public class Actors {
    private double caloriesBurned;
    private double caloriesIntake;
   // private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private int age;
    private int heightInches;
    private double weightLbs;

    public double getCalorieDifference() {
        return calorieDifference;
    }

    public void setCalorieDifference(double calorieDifference) {
        this.calorieDifference = calorieDifference;
    }

    private double calorieDifference;

    //public int getId() {
   //     return id;
  //  }

    //public void setId(int id) {
     //   this.id = id;
  //  }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public double getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs(double weightLbs) {
        this.weightLbs = weightLbs;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public double getCaloriesIntake() {
        return caloriesIntake;
    }

    public void setCaloriesIntake(int caloriesIntake) {
        this.caloriesIntake = caloriesIntake;
    }

    public String printabley(){
        return
                "\n" + this.firstName + " " + calorieDifference + "\n";
    }

    public String printablei(){
        return
                "\n" + this.firstName + " " + this.caloriesBurned + "\n";
    }

}
