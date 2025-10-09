/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;

/**
 *
 * @author hp
 */
public class Deposit extends NequiOperation {    
    public Deposit(String description, double amount, String date) {
        super(description, amount, date);
    }
    
    @Override
    public void doOperation(NequiAccount account) {
        account.setAvailableAmount(account.getAvailableAmount() + getAmount());
    }
}