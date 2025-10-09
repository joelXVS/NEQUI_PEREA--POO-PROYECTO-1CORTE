/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;

/**
 *
 * @author hp
 */
public class NequiCard {
    private String number;
    private String codeVerificationValue;
    private String expirationDate;

    public NequiCard(String number, String codeVerificationValue, String expirationDate) {
        this.number = number;
        this.codeVerificationValue = codeVerificationValue;
        this.expirationDate = expirationDate;
    }
    
    public String showData(String languageCode) {
        if (languageCode.equals("es")) {
            return "\n=== DATOS DE TARJETA ===\n" +
                   "Número: " + maskCardNumber(number) + "\n" +
                   "CVV: " + codeVerificationValue + "\n" +
                   "Vencimiento: " + expirationDate;
        } else {
            return "\n=== CARD DETAILS ===\n" +
                   "Number: " + maskCardNumber(number) + "\n" +
                   "CVV: " + codeVerificationValue + "\n" +
                   "Expiration: " + expirationDate;
        }
    }
    
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return cardNumber;
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    public String getNumber() {
        return number;
    }

    public String getCodeVerificationValue() {
        return codeVerificationValue;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}

