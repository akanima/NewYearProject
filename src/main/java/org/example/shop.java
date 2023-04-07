package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class shop {
    private String shopName="digikala";
    private String webAddress;
    Registration registration=new Registration();
    product product=new product();
    ArrayList<product> allTheAvailableProducts=product.allTheAvailableProducts;
    Order order=new Order();
    ArrayList<String> allCompletedOrders;
    ArrayList<User> users=registration.users;
    ArrayList<Admin> admins=registration.admins;
    ArrayList<String> approvedSellerInString=new ArrayList<>();
    private double shopBalance;
    Wallet wallet=new Wallet(shopBalance);
    public shop(){}
    public shop(String shopName,String webAddress,ArrayList<User> users,ArrayList<Admin> admins,double shopBalance,ArrayList<String> approvedSellerInString,ArrayList<product> allTheAvailableProducts,ArrayList<String> allCompletedOrders)throws IOException{

        this.shopName=shopName;
        this.webAddress=webAddress;
        this.users=users;
        this.admins=admins;
        this.shopBalance=shopBalance;
        this.allTheAvailableProducts=allTheAvailableProducts;
        setAllCompletedOrders(allCompletedOrders);
        setApprovedSellersInString(approvedSellerInString);
    }
    public void setAllCompletedOrders(ArrayList<String> allCompletedOrders)throws IOException {
        Order order=new Order();
        User user=new User();
        user.loadingAllCompletedOrders();
        this.allCompletedOrders=order.allCompletedOrdersInString;
        allCompletedOrders=this.allCompletedOrders;
    }
    public void setApprovedSellersInString(ArrayList<String> approvedSellersInString)throws IOException{
        Registration registration=new Registration();
        registration.loadingApprovedSellers();
        this.approvedSellerInString=registration.approvedSellersInString;
        approvedSellersInString=this.approvedSellerInString;
    }

    private void setShopName(String shopName){
        this.shopName="digikala";
    }
    public String getShopName(){
        return this.shopName;
    }
    public void setProductName(String webAddress){
        this.webAddress=webAddress;
    }
    public String getWebAddress(){
        return this.webAddress;
    }
    public void setShopBalance(double shopBalance){
        this.shopBalance=shopBalance;
    }
    public double getShopBalance(){
        return this.shopBalance;
    }

}
