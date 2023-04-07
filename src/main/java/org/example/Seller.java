package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;

class Seller {
    Scanner sc=new Scanner(System.in);
    private String sellerCompany;
    private String sellerPassword;
    private double sellerBalance;
    private UUID uuidOfSeller=UUID.randomUUID();
    Wallet wallet=new Wallet(sellerBalance);
    ArrayList<product> availableProducts=new ArrayList<>();
    ArrayList<String> availableProductsInString=new ArrayList<>();
    public Seller(){}
    public Seller(String sellerCompany){
        this.sellerCompany=sellerCompany;
    }
   public Seller(String sellerCompany,String sellerPassword){
       this.sellerCompany=sellerCompany;
       this.sellerPassword=sellerPassword;
   }
    public Seller(String sellerCompany,String sellerPassword,UUID uuidOfSeller){
        this.sellerCompany=sellerCompany;
        this.sellerPassword=sellerPassword;
        this.sellerBalance=sellerBalance;
        this.availableProducts=availableProducts;
        this.uuidOfSeller=uuidOfSeller;
    }
    public Seller(String sellerCompany,String sellerPassword, double sellerBalance, ArrayList<product> availableProducts,Wallet wallet,UUID uuidOfSeller){
        this.sellerCompany=sellerCompany;
        this.sellerPassword=sellerPassword;
        this.sellerBalance=sellerBalance;
        this.availableProducts=availableProducts;
        this.wallet=wallet;
        this.uuidOfSeller=uuidOfSeller;
    }
    public void setSellerCompany(String sellerCompany){
        this.sellerCompany=sellerCompany;
    }
    public String getSellerCompany(){
        return this.sellerCompany;
    }
    public void setSellerPassword(String sellerPassword){
        this.sellerPassword=sellerPassword;
    }
    public String  getSellerPassword(){
        return this.sellerPassword;
    }
    public void setProductQuantity(double sellerBalance){
        this.sellerBalance=sellerBalance;
    }
    public double getSellerBalance(){
        return this.sellerBalance;
    }
    public void setSellerBalance(double sellerBalance){
        this.sellerBalance=sellerBalance;
    }
    public void setUuidOfSeller(UUID uuidOfSeller){
        this.uuidOfSeller=uuidOfSeller;
    }
    public UUID getUuidOfSeller(){
        return uuidOfSeller;
    }
    public void addingProducts()throws IOException{
        System.out.println("please enter your product name");
        String productName=sc.nextLine();
        System.out.println("please enter the price of your product");
        int productPrice=sc.nextInt();
        System.out.println("please enter the quantity of your product");
        int productQuantity=sc.nextInt();
        System.out.println("please enter your comments for this product");
        String productComments=sc.nextLine();
        UUID uuidOfProduct=UUID.randomUUID();
        System.out.println("please enter the categories of this product");
        System.out.println("if there is no categories left enter 1");
        ArrayList<String> productCategories=new ArrayList<>();
        System.out.println("press 1 if there are no categories left");
        while(true){
            String productCategory=sc.nextLine();
            productCategories.add(productCategory);
            if(productCategory.equals("1")){
                productCategories.remove("1");
                break;
            }
        }
        product product=new product(sellerCompany,productName,productPrice,productQuantity,productComments,uuidOfProduct,productCategories);
        availableProducts.add(product);
        product.allTheAvailableProducts.add(product);
        savingProducts(product);
        Main.showSellerMenu();
    }
    public void savingProducts(product product)throws IOException{
        FileWriter savingProducts=new FileWriter("availableProducts.txt");
        String productInfo=getSellerCompany()+";"+product.getProductName()+";"+product.getProductPrice()+";"+product.getProductQuantity()+";"+product.getProductComments()+";"+product.getProductCategories();
        savingProducts.write(productInfo+'\n');
    }
    public void loadingProducts()throws IOException{
        FileReader loadingProducts=new FileReader("availableProducts.txt");
        BufferedReader bf=new BufferedReader(loadingProducts);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String availableProductInfo=tokens.nextToken();
            availableProductsInString.add(availableProductInfo);
        }
        bf.close();
    }


}
