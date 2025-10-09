/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;

/**
 *
 * @author hp
 */
public abstract class NequiOperation {
    private String description;
    private double amount;
    private String date;

    public NequiOperation(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }
    
    public abstract void doOperation(NequiAccount account);

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

