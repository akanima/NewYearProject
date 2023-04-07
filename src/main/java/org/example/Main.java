package org.example;


import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable {
    public static void main(String[] args) throws IOException {
        runMenu();
    }
    public static void runMenu() throws IOException {
        startingMenu();
        int choice=getInput();
        gettingChoiceForRunningMenu(choice,new Registration());
    }

    private static void startingMenu() throws IOException {
        printHeader();
        printEntryMenu();
    }

    public static void showUserMenu() throws IOException{
        while(true){
            printUserMenu();
            int choice2=sc.nextInt();
            Admin admin=new Admin();
            User user=new User();
            shop shop=new shop();
            userMenu(choice2,user,admin,shop);
            if(logout){
                System.out.println("goodbye");
                break;
            }
        }
    }

    public static void showAdminMenu()throws IOException {
        while(true){
           printAdminMenu();
           int choice1=sc.nextInt();
           Admin admin=new Admin();
           Registration registration=new Registration();
           User user=new User();
           adminMenu(choice1, registration,admin,user);
           if(logout){
               System.out.println("goodbye");
               break;
           }
        }
    }

    public static void showSellerMenu() throws IOException{
        while(true){
            printSellerMenu();
            int choice2=getInput();
            Seller seller=new Seller();
            sellerMenu(choice2,seller);
            if(logout){
                System.out.println("goodbye");
                break;
            }
        }
    }

    public static void printHeader(){
        System.out.println("        Hi!        ");
        System.out.println("Welcome to digikala");
    }
    static Scanner sc=new Scanner(System.in);
    public static int getInput(){
        int choice=-1;
        while(choice<0 || choice>9){
            try{
                System.out.println("enter your choice: ");
                choice=Integer.parseInt(sc.nextLine());

            }
            catch(NumberFormatException e){
                System.out.println("invalid selection, try again");
            }

        }
        return choice;
    }
    public static void printEntryMenu(){
        System.out.println("if you are an admin please choose 0 to login");
        System.out.println("if you are a new seller please choose 1 to begin");
        System.out.println("if you are an approved seller please choose 2 to login");
        System.out.println("if you are a new user please choose 3 to begin");
        System.out.println("if you have an account please choose 4 to login");
        System.out.println("if you have a tracing code for being a seller please choose 5");
    }
    static boolean isDone=false;
    public static void accountsSystemForAdminAndUser(int choice,Registration registration)throws IOException{
        switch (choice){
            case 0:
                while(!isDone){
                    System.out.println("please enter your username");
                    String adminUsername=sc.nextLine();
                    System.out.println("please enter your password");
                    String adminPassword=sc.nextLine();
                    isDone=registration.AdminLogin(adminUsername,adminPassword);
                    if(isDone){
                        System.out.println("login successful!");
                        showAdminMenu();
                        break;
                    }
                    else{
                        System.out.println("login was not successful please try again!");
                    }
                }
                break;
            case 3:
                while(!isDone){
                    System.out.println("please enter your username");
                    String username=sc.nextLine();
                    isDone=registration.checkingExistanceForUsername(username);
                    if(isDone){
                        System.out.println("please complete your info");
                        registration.CompletingUserInfo(username);
                    }
                    else{
                        System.out.println("this username already exists please choose another username");
                    }
                }
                break;
            case 4:
                while(!isDone){
                    System.out.println("please enter your username");
                    String usernameForLogin=sc.nextLine();
                    System.out.println("please enter your password");
                    String passwordForLogin=sc.nextLine();
                    isDone=registration.userLogin(usernameForLogin,passwordForLogin);
                    if(isDone){
                        System.out.println("login successful!");
                        showAdminMenu();
                    }
                    else{
                        System.out.println("login was not successful please try again!");
                    }
                }
                break;
        }
    }
    public static void printAdminMenu(){
        System.out.println("please select what operation you want to do");
        System.out.println("1: registering new sellers");
        System.out.println("2: approving deposit into wallet");
        System.out.println("3: approving sales");
        System.out.println("4: view users profile");
        System.out.println("5: logout");
    }
    public static void adminMenu(int choice,Registration registration,Admin admin,User user)throws IOException{
        switch (choice){
            case 1:
               admin.gettingAdminApprovalForSeller(registration);
               break;
            case 2:
                admin.gettingAdminApprovalForFund(user);
                break;
            case 3:
                admin.gettingAdminApprovalForSubmittingOrder(user);
                break;
            case 4:
                System.out.println("please enter the username that you are looking for");
                String username=sc.nextLine();
                admin.viewUserProfile(registration,username);
                break;
            case 5:
                logout();
                break;
        }
    }
    public static boolean logout=false;
    public static void logout(){
        logout=true;
    }
    public static void printSellerMenu(){
        System.out.println("if you want to add products choose 1");
        System.out.println("if you want to see your balance choose 2");
        System.out.println("if you want to logout choose 3");
    }
    public static void sellerMenu(int choice,Seller seller)throws IOException{
        switch (choice){
            case 1:
                seller.addingProducts();
                break;
            case 2:
                System.out.println(seller.getSellerBalance());
                break;
            case 3:
                logout();
                break;
        }
    }
    public static void printUserMenu(){
        System.out.println("for making a fund request choose 1");
        System.out.println("for checking your deposit request into your wallet status choose 2");
        System.out.println("for Searching a product choose 3");
        System.out.println("for submitting your shopping cart choose 4");
        System.out.println("for checking your order status choose 5");
        System.out.println("for editing your personal info choose 6");
        System.out.println("for viewing your shopping cart choose 7");
        System.out.println("for removing an item form your shopping cart choose 8");
        System.out.println("for logging out choose 9");
    }
    public static void userMenu(int choice,User user,Admin admin,shop shop)throws IOException{
        switch(choice){
            case 1:
                user.makingAFundRequest();
                break;
            case 2:
                user.AddingFund(admin);
                break;
            case 3:
                System.out.println("please enter the name of the product you are looking for");
                String productName=sc.nextLine();
                user.searchingProduct(productName);
                break;
            case 4:
                user.submittingShoppingCart();
                break;
            case 5:
                user.checkIfOrderIsCompleted(admin,shop);
                break;
            case 6:
                user.editingProfile();
                break;
            case 7:
                user.viewShoppingCart();
                break;
            case 8:
                System.out.println("please enter the name of the item you want to remove");
                String toBeRemovedItemName=sc.nextLine();
                user.removingAnItem(toBeRemovedItemName);
                break;
            case 9:
                logout();
        }
    }
    public static void gettingChoiceForRunningMenu(int choice,Registration registration)throws IOException{
        switch (choice){
            case 0:
                accountsSystemForAdminAndUser(choice,registration);
                break;
            case 1:
                registration.makingSellerRequest();
                break;
            case 2:
                while(!isDone){
                    System.out.println("please enter the name of your company");
                    String sellerCompany=sc.nextLine();
                    System.out.println("please enter your password");
                    String sellerPassword=sc.nextLine();
                    isDone=registration.sellerLogin(sellerCompany,sellerPassword);
                    if(isDone){
                        System.out.println("login successful!");
                        showSellerMenu();
                    }
                    else{
                        System.out.println("login was not successful please try again!");
                    }
                }
                break;
            case 3:
                accountsSystemForAdminAndUser(choice,registration);
                showUserMenu();
                break;
            case 4:
                accountsSystemForAdminAndUser(choice,registration);
                break;
            case 5:
                if(registration.checkingSellerRequestStatus()){
                    registration.completingSellerInfo();
                }
                break;
        }
    }
}