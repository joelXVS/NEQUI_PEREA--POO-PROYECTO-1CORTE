/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.gamerker.io.nequi_perea;
import java.io.File;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author hp
 */
public class NEQUI_PEREA {
    private static Scanner scanner = new Scanner(System.in);
    private static String language;
    private static NequiAccount currentAccount;
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public static void main(String[] args) {
        selectLanguage();
        registerUser();
        mainMenu();        
    }
    
    private static void selectLanguage() {
        System.out.println("=== NEQUI ===");
        System.out.println("Please select a language / Por favor selecciona un idioma:");
        System.out.println("1. Español (ES)");
        System.out.println("2. English (EN)");
        System.out.print("Selección / Selection: ");
        
        int option = scanner.nextInt();
        scanner.nextLine();
        
        language = (option == 1) ? "es" : "en";
    }
    
    private static void registerUser() {
        if (language.equals("es")) {
            System.out.println("\n=== REGISTRO DE USUARIO ===");
            System.out.print("Nombre completo: ");
            String fullname = scanner.nextLine();
            System.out.print("Documento de identidad: ");
            String document = scanner.nextLine();
            System.out.print("Número de celular: ");
            String cellphone = scanner.nextLine();
            System.out.print("Correo electrónico: ");
            String email = scanner.nextLine();
            System.out.print("Código de acceso (4 dígitos): ");
            int accessCode = scanner.nextInt();
            scanner.nextLine();
            
            User user = new User(fullname, document, cellphone, email);
            currentAccount = new NequiAccount(user, 0.0, accessCode, generateCardNumber(), generateCVV(), generateExpirationDate());
            
            System.out.println("\nCuenta creada exitosamente");
            System.out.println("Tu número de tarjeta es: " + currentAccount.getUserCard().getNumber());
        } else {
            System.out.println("\n=== USER REGISTRATION ===");
            System.out.print("Full name: ");
            String fullname = scanner.nextLine();
            System.out.print("ID document: ");
            String document = scanner.nextLine();
            System.out.print("Cell phone number: ");
            String cellphone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Access code (4 digits): ");
            int accessCode = scanner.nextInt();
            scanner.nextLine();
            
            User user = new User(fullname, document, cellphone, email);
            currentAccount = new NequiAccount(user, 0.0, accessCode, generateCardNumber(), generateCVV(), generateExpirationDate());
            
            System.out.println("\nAccount created successfully");
            System.out.println("Your card number is: " + currentAccount.getUserCard().getNumber());
        }
    }
    
    private static void mainMenu() {
        while (true) {
            if (language.equals("es")) {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Consultar saldo");
                System.out.println("2. Recargar dinero");
                System.out.println("3. Enviar dinero");
                System.out.println("4. Retirar dinero");
                System.out.println("5. Pagar servicios");
                System.out.println("6. Ver historial");
                System.out.println("7. Datos de tarjeta");
                System.out.println("8. Nequi Coach");
                System.out.println("9. Modo Retos");
                System.out.println("10. Salir");
                System.out.print("Seleccione una opción: ");
            } else {
                System.out.println("\n=== MAIN MENU ===");
                System.out.println("1. Check balance");
                System.out.println("2. Add money");
                System.out.println("3. Send money");
                System.out.println("4. Withdraw money");
                System.out.println("5. Pay services");
                System.out.println("6. View history");
                System.out.println("7. Card details");
                System.out.println("8. Nequi Coach");
                System.out.println("9. Challenge Mode");
                System.out.println("10. Exit");
                System.out.print("Select an option: ");
            }
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    currentAccount.showAvailableAmount(language);
                    break;
                case 2:
                    addMoney();
                    break;
                case 3:
                    sendMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    payServices();
                    break;
                case 6:
                    currentAccount.showHistory(language);
                    break;
                case 7:
                    System.out.println(currentAccount.getUserCard().showData(language));
                    break;
                case 8:
                    NequiCoach coach = new NequiCoach(currentAccount);
                    coach.showMenu(language);
                    break;
                case 9:
                    ChallengeMode challenges = new ChallengeMode(currentAccount);
                    challenges.showMenu(language);
                    break;
                case 10:
                    if (language.equals("es")) {
                        System.out.println("Gracias por usar Nequi");
                    } else {
                        System.out.println("Thank you for using Nequi");
                    }
                    System.exit(0);
                    break;
                default:
                    if (language.equals("es")) {
                        System.out.println("Opción no válida");
                    } else {
                        System.out.println("Invalid option");
                    }
            }
        }
    }

    private static void genInvoice(int operationType) {
        try {
            if (!currentAccount.getAccountHistory().isEmpty()) {
                HistoryItem last = currentAccount.getAccountHistory().get(currentAccount.getAccountHistory().size() - 1);
                Invoice inv = new Invoice(currentAccount, last);
                String path = inv.generatePdf();
                
                // Crear directorio si no existe
                File dir = new File("invoices");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                if (path != null) {
                    if (language.equals("es")) {
                        System.out.println("Comprobante generado en: " + path);
                    } else {
                        System.out.println("Receipt generated at: " + path);
                    }
                }
            }
        } catch (Exception e) {
            if (language.equals("es")) {
                System.out.println("No se pudo generar el comprobante");
            } else {
                System.out.println("Could not generate receipt");
            }
        }
    }

    private static void addMoney() {
        if (language.equals("es")) {
            System.out.print("Ingrese el monto a recargar: $");
        } else {
            System.out.print("Enter amount to add: $");
        }
        
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (amount <= 0) {
            if (language.equals("es")) {
                System.out.println("Monto no válido");
            } else {
                System.out.println("Invalid amount");
            }
            return;
        }
        
        String date = LocalDateTime.now().format(dateFormatter);
        Deposit deposit = new Deposit("Recarga", amount, date);
        currentAccount.doNequiOperation(deposit, "Recarga de saldo", date);
        
        if (language.equals("es")) {
            System.out.println("Recarga exitosa. Nuevo saldo: $" + currentAccount.getAvailableAmount());
        } else {
            System.out.println("Successful deposit. New balance: $" + currentAccount.getAvailableAmount());
        }
        
        genInvoice(2);
    }
    
    private static void sendMoney() {
        if (currentAccount.getAvailableAmount() <= 0) {
            if (language.equals("es")) {
                System.out.println("Saldo insuficiente para realizar envíos");
            } else {
                System.out.println("Insufficient balance to send money");
            }
            return;
        }
        
        if (language.equals("es")) {
            System.out.print("Ingrese el número de celular del destinatario: ");
        } else {
            System.out.print("Enter recipient's phone number: ");
        }
        String recipient = scanner.nextLine();
        
        if (language.equals("es")) {
            System.out.print("Ingrese el monto a enviar: $");
        } else {
            System.out.print("Enter amount to send: $");
        }
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (amount <= 0) {
            if (language.equals("es")) {
                System.out.println("Monto no válido");
            } else {
                System.out.println("Invalid amount");
            }
            return;
        }
        
        if (amount > currentAccount.getAvailableAmount()) {
            if (language.equals("es")) {
                System.out.println("Saldo insuficiente");
            } else {
                System.out.println("Insufficient balance");
            }
            return;
        }
        
        if (language.equals("es")) {
            System.out.print("Ingrese su código de acceso: ");
        } else {
            System.out.print("Enter your access code: ");
        }
        int code = scanner.nextInt();
        scanner.nextLine();
        
        if (!currentAccount.verifyAccessCode(code)) {
            if (language.equals("es")) {
                System.out.println("Código incorrecto");
            } else {
                System.out.println("Incorrect code");
            }
            return;
        }
        
        String date = LocalDateTime.now().format(dateFormatter);
        Transfer transfer = new Transfer("Envío a " + recipient, amount, date);
        currentAccount.doNequiOperation(transfer, "Envío a " + recipient, date);
        
        if (language.equals("es")) {
            System.out.println("Envío exitoso. Nuevo saldo: $" + currentAccount.getAvailableAmount());
        } else {
            System.out.println("Transfer successful. New balance: $" + currentAccount.getAvailableAmount());
        }
        
        genInvoice(3);
    }
    
    private static void withdrawMoney() {
        if (currentAccount.getAvailableAmount() <= 0) {
            if (language.equals("es")) {
                System.out.println("Saldo insuficiente para realizar retiros");
            } else {
                System.out.println("Insufficient balance to withdraw");
            }
            return;
        }
        
        if (language.equals("es")) {
            System.out.print("Ingrese el monto a retirar: $");
        } else {
            System.out.print("Enter amount to withdraw: $");
        }
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (amount <= 0) {
            if (language.equals("es")) {
                System.out.println("Monto no válido");
            } else {
                System.out.println("Invalid amount");
            }
            return;
        }
        
        if (amount > currentAccount.getAvailableAmount()) {
            if (language.equals("es")) {
                System.out.println("Saldo insuficiente");
            } else {
                System.out.println("Insufficient balance");
            }
            return;
        }
        
        if (language.equals("es")) {
            System.out.print("Ingrese su código de acceso: ");
        } else {
            System.out.print("Enter your access code: ");
        }
        int code = scanner.nextInt();
        scanner.nextLine();
        
        if (!currentAccount.verifyAccessCode(code)) {
            if (language.equals("es")) {
                System.out.println("Código incorrecto");
            } else {
                System.out.println("Incorrect code");
            }
            return;
        }
        
        String date = LocalDateTime.now().format(dateFormatter);
        Withdrawal withdrawal = new Withdrawal("Retiro en cajero", amount, date);
        currentAccount.doNequiOperation(withdrawal, "Retiro en cajero", date);
        
        if (language.equals("es")) {
            System.out.println("Retiro exitoso. Nuevo saldo: $" + currentAccount.getAvailableAmount());
        } else {
            System.out.println("Withdrawal successful. New balance: $" + currentAccount.getAvailableAmount());
        }
        
        genInvoice(4);
    }
    
    private static void payServices() {
        if (currentAccount.getAvailableAmount() <= 0) {
            if (language.equals("es")) {
                System.out.println("Saldo insuficiente para pagar servicios");
            } else {
                System.out.println("Insufficient balance to pay services");
            }
            return;
        }
        
        if (language.equals("es")) {
            System.out.println("\n=== PAGO DE SERVICIOS ===");
            System.out.println("1. Energía");
            System.out.println("2. Agua");
            System.out.println("3. Gas");
            System.out.println("4. Internet");
            System.out.println("5. Teléfono");
            System.out.print("Seleccione el servicio: ");
        } else {
            System.out.println("\n=== PAY SERVICES ===");
            System.out.println("1. Electricity");
            System.out.println("2. Water");
            System.out.println("3. Gas");
            System.out.println("4. Internet");
            System.out.println("5. Phone");
            System.out.print("Select service: ");
        }
        
        int serviceOption = scanner.nextInt();
        scanner.nextLine();
        
        if (serviceOption < 1 || serviceOption > 5) {
            if (language.equals("es")) {
                System.out.println("Servicio no válido");
            } else {
                System.out.println("Invalid service");
            }
            return;
        }
        
        String[] servicesES = {"Energía", "Agua", "Gas", "Internet", "Teléfono"};
        String[] servicesEN = {"Electricity", "Water", "Gas", "Internet", "Phone"};
        String serviceName = language.equals("es") ? servicesES[serviceOption - 1] : servicesEN[serviceOption - 1];
        
        if (language.equals("es")) {
            System.out.print("Ingrese el monto a pagar: $");
        } else {
            System.out.print("Enter amount to pay: $");
        }
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (amount <= 0) {
            if (language.equals("es")) {
                System.out.println("Monto no válido");
            } else {
                System.out.println("Invalid amount");
            }
            return;
        }
        
        if (amount > currentAccount.getAvailableAmount()) {
            if (language.equals("es")) {
                System.out.println("Saldo insuficiente");
            } else {
                System.out.println("Insufficient balance");
            }
            return;
        }
        
        String date = LocalDateTime.now().format(dateFormatter);
        Payment payment = new Payment("Pago de " + serviceName, amount, date, serviceName);
        currentAccount.doNequiOperation(payment, "Pago de " + serviceName, date);
        
        if (language.equals("es")) {
            System.out.println("Pago exitoso. Nuevo saldo: $" + currentAccount.getAvailableAmount());
        } else {
            System.out.println("Payment successful. New balance: $" + currentAccount.getAvailableAmount());
        }
        
        genInvoice(5);
    }
    
    private static String generateCardNumber() {
        return "5399" + String.format("%012d", (long)(Math.random() * 1000000000000L));
    }
    
    private static String generateCVV() {
        return String.format("%03d", (int)(Math.random() * 1000));
    }
    
    private static String generateExpirationDate() {
        int year = LocalDateTime.now().getYear() + 5;
        int month = (int)(Math.random() * 12) + 1;
        return String.format("%02d/%d", month, year);
    }
}
