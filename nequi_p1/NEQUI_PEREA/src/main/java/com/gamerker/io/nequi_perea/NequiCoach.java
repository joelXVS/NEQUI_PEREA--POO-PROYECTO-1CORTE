/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;
import java.util.Scanner;
import java.util.Map;

/**
 *
 * @author hp
 */
public class NequiCoach {
    private NequiAccount userAccount;
    private Scanner scanner;
    private String[] categoriesES = {"Comida", "Transporte", "Entretenimiento", "Compras", "Servicios", "Otros"};
    private String[] categoriesEN = {"Food", "Transport", "Entertainment", "Shopping", "Services", "Others"};
    
    public NequiCoach(NequiAccount userAccount) {
        this.userAccount = userAccount;
        this.scanner = new Scanner(System.in);
    }
    
    public void showMenu(String language) {
        while (true) {
            if (language.equals("es")) {
                System.out.println("\n=== NEQUI COACH ===");
                System.out.println("1. Análisis de Gastos");
                System.out.println("2. Alertas Inteligentes");
                System.out.println("3. Recomendaciones de Ahorro");
                System.out.println("4. Volver al Menú Principal");
                System.out.print("Seleccione: ");
            } else {
                System.out.println("\n=== NEQUI COACH ===");
                System.out.println("1. Expense Analysis");
                System.out.println("2. Smart Alerts");
                System.out.println("3. Savings Recommendations");
                System.out.println("4. Back to Main Menu");
                System.out.print("Select: ");
            }
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    analyzeExpenses(language);
                    break;
                case 2:
                    createAlerts(language);
                    break;
                case 3:
                    suggestRecommendations(language);
                    break;
                case 4:
                    return;
                default:
                    if (language.equals("es")) {
                        System.out.println("Opción no válida");
                    } else {
                        System.out.println("Invalid option");
                    }
            }
        }
    }
    
    private void analyzeExpenses(String language) {
        Map<String, Double> expenses = userAccount.getCategoryExpenses();
        double total = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        
        if (total == 0) {
            if (language.equals("es")) {
                System.out.println("\nNo hay gastos registrados para analizar");
            } else {
                System.out.println("\nNo expenses recorded to analyze");
            }
            return;
        }
        
        if (language.equals("es")) {
            System.out.println("\n=== ANÁLISIS DE GASTOS ===");
            System.out.println("Total gastado: $" + String.format("%.2f", total));
            System.out.println("\nDesglose por categoría:");
            
            for (int i = 0; i < categoriesES.length; i++) {
                String category = categoriesES[i];
                double amount = expenses.get(category);
                double percentage = (amount / total) * 100;
                System.out.println(category + ": $" + String.format("%.2f", amount) + 
                                 " (" + String.format("%.1f", percentage) + "%)");
            }
        } else {
            System.out.println("\n=== EXPENSE ANALYSIS ===");
            System.out.println("Total spent: $" + String.format("%.2f", total));
            System.out.println("\nBreakdown by category:");
            
            String[] categoriesMap = {"Comida", "Transporte", "Entretenimiento", "Compras", "Servicios", "Otros"};
            for (int i = 0; i < categoriesEN.length; i++) {
                String categoryKey = categoriesMap[i];
                double amount = expenses.get(categoryKey);
                double percentage = (amount / total) * 100;
                System.out.println(categoriesEN[i] + ": $" + String.format("%.2f", amount) + 
                                 " (" + String.format("%.1f", percentage) + "%)");
            }
        }
    }
    
    private void createAlerts(String language) {
        double balance = userAccount.getAvailableAmount();
        
        if (language.equals("es")) {
            System.out.println("\n=== ALERTAS INTELIGENTES ===");
            
            if (balance < 50000) {
                System.out.println("ALERTA: Su saldo está bajo ($" + String.format("%.2f", balance) + ")");
                System.out.println("Considere hacer una recarga pronto");
            } else if (balance < 100000) {
                System.out.println("AVISO: Su saldo es de $" + String.format("%.2f", balance));
                System.out.println("Se encuentra en un nivel moderado");
            } else {
                System.out.println("Su saldo está en buen estado: $" + String.format("%.2f", balance));
            }
            
            Map<String, Double> expenses = userAccount.getCategoryExpenses();
            double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
            
            if (totalExpenses > balance * 0.7) {
                System.out.println("\nADVERTENCIA: Ha gastado el " + String.format("%.1f", (totalExpenses/balance)*100) + 
                                 "% de su saldo inicial");
                System.out.println("Considere reducir gastos no esenciales");
            }
        } else {
            System.out.println("\n=== SMART ALERTS ===");
            
            if (balance < 50000) {
                System.out.println("ALERT: Your balance is low ($" + String.format("%.2f", balance) + ")");
                System.out.println("Consider adding money soon");
            } else if (balance < 100000) {
                System.out.println("NOTICE: Your balance is $" + String.format("%.2f", balance));
                System.out.println("You're at a moderate level");
            } else {
                System.out.println("Your balance is in good shape: $" + String.format("%.2f", balance));
            }
            
            Map<String, Double> expenses = userAccount.getCategoryExpenses();
            double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
            
            if (totalExpenses > balance * 0.7) {
                System.out.println("\nWARNING: You've spent " + String.format("%.1f", (totalExpenses/balance)*100) + 
                                 "% of your initial balance");
                System.out.println("Consider reducing non-essential expenses");
            }
        }
    }
    
    private void suggestRecommendations(String language) {
        Map<String, Double> expenses = userAccount.getCategoryExpenses();
        double total = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        double balance = userAccount.getAvailableAmount();
        
        if (language.equals("es")) {
            System.out.println("\n=== RECOMENDACIONES DE AHORRO ===");
            
            if (total == 0) {
                System.out.println("Aún no tiene gastos registrados");
                System.out.println("Consejo: Use Nequi para todas sus transacciones y obtenga mejor análisis");
                return;
            }
            
            String maxCategory = "";
            double maxAmount = 0;
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                if (entry.getValue() > maxAmount) {
                    maxAmount = entry.getValue();
                    maxCategory = entry.getKey();
                }
            }
            
            System.out.println("Su mayor gasto es en: " + maxCategory + " ($" + String.format("%.2f", maxAmount) + ")");
            System.out.println("\nRecomendaciones:");
            System.out.println("1. Intente reducir gastos en " + maxCategory + " un 10%");
            System.out.println("2. Establezca un presupuesto mensual de $" + String.format("%.2f", total * 0.9));
            System.out.println("3. Ahorre al menos el 10% de sus ingresos");
            
            if (balance > 0) {
                double suggestedSavings = balance * 0.1;
                System.out.println("4. Considere ahorrar $" + String.format("%.2f", suggestedSavings) + 
                                 " de su saldo actual");
            }
        } else {
            System.out.println("\n=== SAVINGS RECOMMENDATIONS ===");
            
            if (total == 0) {
                System.out.println("You don't have recorded expenses yet");
                System.out.println("Tip: Use Nequi for all your transactions to get better analysis");
                return;
            }
            
            String maxCategory = "";
            double maxAmount = 0;
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                if (entry.getValue() > maxAmount) {
                    maxAmount = entry.getValue();
                    maxCategory = entry.getKey();
                }
            }
            
            System.out.println("Your biggest expense is: " + maxCategory + " ($" + String.format("%.2f", maxAmount) + ")");
            System.out.println("\nRecommendations:");
            System.out.println("1. Try to reduce " + maxCategory + " expenses by 10%");
            System.out.println("2. Set a monthly budget of $" + String.format("%.2f", total * 0.9));
            System.out.println("3. Save at least 10% of your income");
            
            if (balance > 0) {
                double suggestedSavings = balance * 0.1;
                System.out.println("4. Consider saving $" + String.format("%.2f", suggestedSavings) + 
                                 " from your current balance");
            }
        }
    }
}