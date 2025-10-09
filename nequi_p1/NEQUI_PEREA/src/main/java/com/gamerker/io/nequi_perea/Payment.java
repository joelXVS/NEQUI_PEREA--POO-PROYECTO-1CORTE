/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;

/**
 *
 * @author hp
 */
public class Payment extends NequiOperation {
    private String serviceType;
    
    public Payment(String description, double amount, String date, String serviceType) {
        super(description, amount, date);
        this.serviceType = serviceType;
    }
    
    @Override
    public void doOperation(NequiAccount account) {
        account.setAvailableAmount(account.getAvailableAmount() - getAmount());
        account.addToCategory("Servicios", getAmount());
    }

    public String getServiceType() {
        return serviceType;
    }
}