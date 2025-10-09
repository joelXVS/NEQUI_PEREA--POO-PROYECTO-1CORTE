/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author hp
 */
public class NequiAccount {
    private User usuario;
    private double availableAmount;
    private int accessCode;
    private NequiCard userCard;
    private List<HistoryItem> accountHistory;
    private Map<String, Double> categoryExpenses;

    public NequiAccount(User usuario, double availableAmount, int accessCode, String cardNumber, String cardCode, String expirationDate) {
        this.usuario = usuario;
        this.availableAmount = availableAmount;
        this.accessCode = accessCode;
        this.userCard = new NequiCard(cardNumber, cardCode, expirationDate);
        this.accountHistory = new ArrayList<>();
        this.categoryExpenses = new HashMap<>();
        initializeCategories();
    }
    
    private void initializeCategories() {
        categoryExpenses.put("Comida", 0.0);
        categoryExpenses.put("Transporte", 0.0);
        categoryExpenses.put("Entretenimiento", 0.0);
        categoryExpenses.put("Compras", 0.0);
        categoryExpenses.put("Servicios", 0.0);
        categoryExpenses.put("Otros", 0.0);
    }
    
    public void doNequiOperation(NequiOperation operation, String details, String date) {
        operation.doOperation(this);
        accountHistory.add(new HistoryItem(operation, details, date));
    }
    
    public void showAvailableAmount(String language) {
        if (language.equals("es")) {
            System.out.println("Tu saldo disponible es: $" + String.format("%.2f", availableAmount));
        } else {
            System.out.println("Your available balance is: $" + String.format("%.2f", availableAmount));
        }
    }
    
    public void showHistory(String language) {
        if (accountHistory.isEmpty()) {
            if (language.equals("es")) {
                System.out.println("No hay transacciones registradas");
            } else {
                System.out.println("No transactions recorded");
            }
            return;
        }
        
        if (language.equals("es")) {
            System.out.println("\n=== HISTORIAL DE TRANSACCIONES ===");
        } else {
            System.out.println("\n=== TRANSACTION HISTORY ===");
        }
        
        for (int i = 0; i < accountHistory.size(); i++) {
            HistoryItem item = accountHistory.get(i);
            System.out.println((i + 1) + ". " + item.getDetails() + " - " + item.getDate());
        }
    }
    
    public boolean verifyAccessCode(int code) {
        return this.accessCode == code;
    }
    
    public void addToCategory(String category, double amount) {
        if (categoryExpenses.containsKey(category)) {
            categoryExpenses.put(category, categoryExpenses.get(category) + amount);
        } else {
            categoryExpenses.put("Otros", categoryExpenses.get("Otros") + amount);
        }
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public User getUsuario() {
        return usuario;
    }

    public NequiCard getUserCard() {
        return userCard;
    }

    public List<HistoryItem> getAccountHistory() {
        return accountHistory;
    }

    public Map<String, Double> getCategoryExpenses() {
        return categoryExpenses;
    }
}
