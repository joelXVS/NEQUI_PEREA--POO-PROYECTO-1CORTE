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
public class User {
    private String fullname;
    private List<Result> results;

    public User(String fullname, List<Result> results) {
        this.fullname = fullname;
        this.results = results;
    }

    public Result getResult(String resultId) {
        return resultId;
    }
    
    public List<Result> getAllResults() {
        return results;
    }
}
