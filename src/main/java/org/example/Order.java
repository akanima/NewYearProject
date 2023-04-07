package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

class Order extends product{

     private String orderDate;
     private String buyerUsername;
     private int quantityOfOrder;
     private int totalPrice;
     ArrayList<Order> allOrders=new ArrayList<>();
     ArrayList<String> allCompletedOrdersInString=new ArrayList<>();
     private UUID uuidOfOrder=UUID.randomUUID();
     public Order(){}
     public Order(String buyerUsername,String sellerCompany,String productName , String orderDate,int quantityOfOrder,int totalPrice,UUID uuidOfOrder){
         super(sellerCompany,productName);
         this.orderDate=orderDate;
         this.buyerUsername=buyerUsername;
         this.quantityOfOrder=quantityOfOrder;
         this.totalPrice=totalPrice;
         this.uuidOfOrder=uuidOfOrder;
     }
     public void setOrderDate(String orderDate){
         this.orderDate=orderDate;
     }
     public String  getOrderDate(){
         return this.orderDate;
     }
     public void setBuyerUsername(String buyerUsername){
         this.buyerUsername=buyerUsername;
     }
     public String  getBuyerUsername(){
         return this.buyerUsername;
     }
     public void setQuantityOfOrder(int quantityOfOrder){
         this.quantityOfOrder=quantityOfOrder;
     }
     public int getQuantityOfOrder(){
         return this.quantityOfOrder;
     }
     public void setTotalPrice(int totalPrice){
        this.totalPrice=totalPrice;
    }
     public int getTotalPrice(){
        return this.totalPrice;
     }
     public void setUuidOfOrder(UUID uuidOfOrder){
         this.uuidOfOrder=uuidOfOrder;
     }
     public UUID getUuidOfOrder(){
         return uuidOfOrder;
     }
 }
