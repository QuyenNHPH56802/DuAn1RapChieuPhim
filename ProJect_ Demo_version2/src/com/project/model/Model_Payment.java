/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class Model_Payment {
    private int id;
    private int booking_id ;
    private double amount ;
    private String payment_method;
    private String payment_status;
    private String payment_date;

    public Model_Payment() {
    }

    public Model_Payment(int id, int booking_id, double amount, String payment_method, String payment_status, String payment_date) {
        this.id = id;
        this.booking_id = booking_id;
        this.amount = amount;
        this.payment_method = payment_method;
        this.payment_status = payment_status;
        this.payment_date = payment_date;
    }

    public Model_Payment(int booking_id, double amount, String payment_method, String payment_status, String payment_date) {
        this.booking_id = booking_id;
        this.amount = amount;
        this.payment_method = payment_method;
        this.payment_status = payment_status;
        this.payment_date = payment_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }
public Object[] toDataRow(){
    return new Object[]{
        this.getId(),this.getBooking_id(),this.getAmount(),this.getPayment_method(),this.getPayment_status(),this.getPayment_date()
    };
} 
    
}
