/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class ChallengeMode {
    private NequiAccount userAccount;
    private Scanner scanner;
    private int points;
    private List<Challenge> activeChallenges;
    private List<Challenge> completedChallenges;
    
    public ChallengeMode(NequiAccount userAccount) {
        this.userAccount = userAccount;
        this.scanner = new Scanner(System.in);
        this.points = 0;
        this.activeChallenges = new ArrayList<>();
        this.completedChallenges = new ArrayList<>();
        initializeChallenges();
    }
    
    private void initializeChallenges() {
        activeChallenges.add(new Challenge("Primera Recarga", "Realiza tu primera recarga", 50, "es"));
        activeChallenges.add(new Challenge("Ahorrador", "Mantén un saldo mayor a $100,000", 100, "es"));
        activeChallenges.add(new Challenge("Pagador Responsable", "Paga 3 servicios", 75, "es"));
        activeChallenges.add(new Challenge("Generoso", "Envía dinero a 5 personas diferentes", 150, "es"));
    }
    
    public void showMenu(String language) {
        while (true) {
            if (language.equals("es")) {
                System.out.println("\n=== MODO RETOS ===");
                System.out.println("Puntos acumulados: " + points);
                System.out.println("\n1. Ver retos disponibles");
                System.out.println("2. Ver progreso");
                System.out.println("3. Canjear puntos");
                System.out.println("4. Volver al Menú Principal");
                System.out.print("Seleccione: ");
            } else {
                System.out.println("\n=== CHALLENGE MODE ===");
                System.out.println("Accumulated points: " + points);
                System.out.println("\n1. View available challenges");
                System.out.println("2. View progress");
                System.out.println("3. Redeem points");
                System.out.println("4. Back to Main Menu");
                System.out.print("Select: ");
            }
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    showAvailableChallenges(language);
                    break;
                case 2:
                    viewProgress(language);
                    break;
                case 3:
                    exchangePoints(language);
                    break;
                case 4:
                    return;
                default:
                    if (language.equals("es")) {
                        System.out.println("Opción inválida");
                    } else {
                        System.out.println("Invalid option");
                    }
            }
        }
    }
    
    private void showAvailableChallenges(String language) {
        if (language.equals("es")) {
            System.out.println("\n=== RETOS DISPONIBLES ===");
            if (activeChallenges.isEmpty()) {
                System.out.println("¡Has completado todos los retos disponibles!");
                return;
            }
            
            for (int i = 0; i < activeChallenges.size(); i++) {
                Challenge c = activeChallenges.get(i);
                System.out.println((i + 1) + ". " + c.getName());
                System.out.println("\t" + c.getDescription());
                System.out.println("\tRecompensa: " + c.getPoints() + " puntos");
                System.out.println();
            }
        } else {
            System.out.println("\n=== AVAILABLE CHALLENGES ===");
            if (activeChallenges.isEmpty()) {
                System.out.println("You've completed all available challenges!");
                return;
            }
            
            for (int i = 0; i < activeChallenges.size(); i++) {
                Challenge c = activeChallenges.get(i);
                System.out.println((i + 1) + ". " + c.getNameEN());
                System.out.println("\t" + c.getDescriptionEN());
                System.out.println("\tReward: " + c.getPoints() + " points");
                System.out.println();
            }
        }
    }
    
    private void viewProgress(String language) {
        if (language.equals("es")) {
            System.out.println("\n=== TU PROGRESO ===");
            System.out.println("Puntos totales: " + points);
            System.out.println("Retos completados: " + completedChallenges.size());
            System.out.println("Retos activos: " + activeChallenges.size());
            
            if (!completedChallenges.isEmpty()) {
                System.out.println("\nRetos completados:");
                for (Challenge c : completedChallenges) {
                    System.out.println("Completado: " + c.getName() + " (+" + c.getPoints() + " puntos)");
                }
            }
        } else {
            System.out.println("\n=== YOUR PROGRESS ===");
            System.out.println("Total points: " + points);
            System.out.println("Completed challenges: " + completedChallenges.size());
            System.out.println("Active challenges: " + activeChallenges.size());
            
            if (!completedChallenges.isEmpty()) {
                System.out.println("\nCompleted challenges:");
                for (Challenge c : completedChallenges) {
                    System.out.println("Completed: " + c.getNameEN() + " (+" + c.getPoints() + " points)");
                }
            }
        }
    }
    
    private void exchangePoints(String language) {
        if (language.equals("es")) {
            System.out.println("\n=== CANJEAR PUNTOS ===");
            System.out.println("Puntos disponibles: " + points);
            System.out.println("\nRecompensas:");
            System.out.println("1. $5,000 en tu cuenta (100 puntos)");
            System.out.println("2. $10,000 en tu cuenta (180 puntos)");
            System.out.println("3. $20,000 en tu cuenta (350 puntos)");
            System.out.println("4. Volver");
            System.out.print("Seleccione: ");
        } else {
            System.out.println("\n=== REDEEM POINTS ===");
            System.out.println("Available points: " + points);
            System.out.println("\nRewards:");
            System.out.println("1. $5,000 to your account (100 points)");
            System.out.println("2. $10,000 to your account (180 points)");
            System.out.println("3. $20,000 to your account (350 points)");
            System.out.println("4. Back");
            System.out.print("Select: ");
        }
        
        int option = scanner.nextInt();
        scanner.nextLine();
        
        int[] requiredPoints = {100, 180, 350};
        double[] rewards = {5000, 10000, 20000};
        
        if (option >= 1 && option <= 3) {
            if (points >= requiredPoints[option - 1]) {
                points -= requiredPoints[option - 1];
                userAccount.setAvailableAmount(userAccount.getAvailableAmount() + rewards[option - 1]);
                
                if (language.equals("es")) {
                    System.out.println("¡Canje exitoso! Se han agregado $" + rewards[option - 1] + " a tu cuenta");
                    System.out.println("Puntos restantes: " + points);
                } else {
                    System.out.println("Successful redemption! $" + rewards[option - 1] + " added to your account");
                    System.out.println("Remaining points: " + points);
                }
            } else {
                if (language.equals("es")) {
                    System.out.println("Puntos insuficientes");
                } else {
                    System.out.println("Insufficient points");
                }
            }
        }
    }
    
    public void checkChallenges() {
        List<Challenge> toComplete = new ArrayList<>();
        
        for (Challenge c : activeChallenges) {
            if (c.checkCompletion(userAccount)) {
                toComplete.add(c);
                points += c.getPoints();
            }
        }
        
        for (Challenge c : toComplete) {
            activeChallenges.remove(c);
            completedChallenges.add(c);
        }
    }
}

