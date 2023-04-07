package org.example;

import java.util.ArrayList;
import java.util.UUID;

class product extends Seller{
    private String productName;
    private int productPrice;
    private int productQuantity;
    private String productComments;
    ArrayList<String> productCategories=new ArrayList<>();
    ArrayList<product> allTheAvailableProducts=new ArrayList<>();
    private UUID uuidOfProduct=UUID.randomUUID();
    public product(){}
    public product(String sellerCompany,String productName ){
        super(sellerCompany);
        this.productName=productName;
    }
    public product(String productName){
        this.productName=productName;
    }
    public product(String sellerCompany,String productName,int productPrice){
        super(sellerCompany);
        this.productName=productName;
        this.productPrice=productPrice;
    }
    public product(String sellerCompany,String productName, int productPrice,int productQuantity){
        super(sellerCompany);
        this.productName=productName;
        this.productPrice=productPrice;
        this.productQuantity=productQuantity;
    }
    public product(String sellerCompany,String productName, int productPrice , int productQuantity,String productComments,UUID uuidOfProduct,ArrayList<String> productCategories){
        super(sellerCompany);
        this.productName=productName;
        this.productPrice=productPrice;
        this.productQuantity=productQuantity;
        this.productComments=productComments;
        this.uuidOfProduct=uuidOfProduct;
        this.productCategories=productCategories;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }
    public String getProductName(){
        return this.productName;
    }
    public void setProductPrice(int productPrice){
        this.productPrice=productPrice;
    }
    public int  getProductPrice(){
        return this.productPrice;
    }
    public void setProductQuantity(int productQuantity){
        this.productQuantity=productQuantity;
    }
    public int getProductQuantity(){
        return this.productQuantity;
    }
    public void setProductComments(String productComments){
        this.productComments=productComments;
    }
    public String getProductComments(){
        return this.productComments;
    }
    public void newQuantity(int numberOfBoughtProduct){
        productQuantity=productQuantity-numberOfBoughtProduct;
    }
    public void setUuidOfProduct(UUID uuidOfProduct){
        this.uuidOfProduct=uuidOfProduct;
    }
    public UUID getUuidOfProduct(){
        return uuidOfProduct;
    }
    public ArrayList getProductCategories(){
        return productCategories;
    }
}
