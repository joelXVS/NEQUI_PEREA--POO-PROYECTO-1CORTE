/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.e.valua_java;
import java.util.List;

/**
 *
 * @author hp
 */
public class Test {
    private String name, description;
    private List<Question> questions;
    private double testTime, pointsCorrect, pointsIncorrect;

    public Test(String name, String description, List<Question> questions, double testTime, double pointsCorrect, double pointsIncorrect) {
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.testTime = testTime;
        this.pointsCorrect = pointsCorrect;
        this.pointsIncorrect = pointsIncorrect;
    }
    
    public void addQuestion(Question question){
        this.questions.add(question);
    }
}
