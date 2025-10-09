/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;

/**
 *
 * @author hp
 */
public class HistoryItem {
    private NequiOperation operationItem;
    private String details;
    private String date;

    public HistoryItem(NequiOperation operationItem, String details, String date) {
        this.operationItem = operationItem;
        this.details = details;
        this.date = date;
    }

    public NequiOperation getOperationItem() {
        return operationItem;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }
}

