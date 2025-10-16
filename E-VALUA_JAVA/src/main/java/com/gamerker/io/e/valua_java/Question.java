/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.e.valua_java;

/**
 *
 * @author hp
 */
public abstract class Question {
    private String text, type, description;
    private int points;

    public Question(String text, String type, String description, int points) {
        this.text = text;
        this.type = type;
        this.description = description;
        this.points = points;
    }
    
    public abstract boolean verifyAnswer(String userAnswer);
    public abstract String renderQuestion();
}
