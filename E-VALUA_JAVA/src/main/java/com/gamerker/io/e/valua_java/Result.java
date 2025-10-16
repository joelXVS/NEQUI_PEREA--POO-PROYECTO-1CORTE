/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.e.valua_java;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author hp
 */
public class Result {
    private int totalScore;
    private LocalDateTime date;
    private Map<Question, Boolean> saveAnswers;
    private Test test;
    private User user;
    private String resultId;

    public Result(int totalScore, LocalDateTime date, Map<Question, Boolean> saveAnswers, Test test, User user) {
        this.totalScore = totalScore;
        this.date = date;
        this.saveAnswers = saveAnswers;
        this.test = test;
        this.user = user;
        this.resultId = "";
    }

    public String getResultId() {
        return resultId;
    }
}
