/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;

/**
 *
 * @author hp
 */
public class Challenge {
    private String name;
    private String description;
    private int points;
    private String nameEN;
    private String descriptionEN;

    public Challenge(String name, String description, int points, String language) {
        this.name = name;
        this.description = description;
        this.points = points;
        translateToEnglish();
    }

    private void translateToEnglish() {
        switch (name) {
            case "Primera Recarga":
                nameEN = "First Deposit";
                descriptionEN = "Make your first deposit";
                break;
            case "Ahorrador":
                nameEN = "Saver";
                descriptionEN = "Maintain a balance over $100,000";
                break;
            case "Pagador Responsable":
                nameEN = "Responsible Payer";
                descriptionEN = "Pay 3 services";
                break;
            case "Generoso":
                nameEN = "Generous";
                descriptionEN = "Send money to 5 different people";
                break;
            default:
                nameEN = name;
                descriptionEN = description;
        }
    }

    public boolean checkCompletion(NequiAccount account) {
        switch (name) {
            case "Primera Recarga":
                return account.getAccountHistory().size() > 0;
            case "Ahorrador":
                return account.getAvailableAmount() >= 100000;
            case "Pagador Responsable":
                long payments = account.getAccountHistory().stream()
                    .filter(h -> h.getDetails().contains("Pago"))
                    .count();
                return payments >= 3;
            case "Generoso":
                long transfers = account.getAccountHistory().stream()
                    .filter(h -> h.getDetails().contains("Envío"))
                    .count();
                return transfers >= 5;
            default:
                return false;
        }
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPoints() { return points; }
    public String getNameEN() { return nameEN; }
    public String getDescriptionEN() { return descriptionEN; }
}
