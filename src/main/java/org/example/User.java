package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;

public class User implements Serializable{
    Scanner sc = new Scanner(System.in);
    private String username;
    private String password;
    private String userEmail;
    private String userPhoneNo;
    private String userAddress;
    private int userBalance;
    ArrayList<Order> shoppingCart = new ArrayList<>();
    ArrayList<Order> orderSubmission = new ArrayList<>();// list of the user` orders
    ArrayList<String> orderSubmissionInString=new ArrayList<>();
    ArrayList<String> adminSubmission = new ArrayList<>();
    ArrayList<Order> purchasedProducts = new ArrayList<>();
    ArrayList<String> fundRequest = new ArrayList<>();
    ArrayList<String> adminApprovalForFund = new ArrayList<>();
    Wallet wallet = new Wallet(userBalance);
    private UUID uuidOfUser = UUID.randomUUID();

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String userEmail, String userPhoneNo, String userAddress, int userBalance, UUID uuidOfUser) {
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.userAddress = userAddress;
        this.userBalance = userBalance;
        this.uuidOfUser = uuidOfUser;
    }

    public void setUuid(UUID uuidOfUser) {
        this.uuidOfUser = uuidOfUser;
    }

    public UUID getUuidOfUser() {
        return uuidOfUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserPhoneNo() {
        return this.userPhoneNo;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    public int getUserBalance() {
        return this.userBalance;
    }
    public void makingAFundRequest() throws IOException{
        loadingFundRequests();
        System.out.println("your username has been sent to an admin please remember your tracing code.");
        fundRequest.add(getUsername());
        savingFundRequest(getUsername());
        int tracingCode = fundRequest.size() - 1;
        System.out.println("your tracing code is:" + tracingCode);
        adminApprovalForFund.add("2");
        savingAdminApprovalForFund();
    }

    public boolean gettingConfirmationForAddingFund(Admin admin, int tracingCode)throws IOException {
        admin.loadingConfirmationForAddingFund();
        boolean isConfirmed=false ;
        if (admin.confirmationForAddingFund.get(tracingCode).equals("1")) {
            admin.confirmationForAddingFund.remove(tracingCode);
            fundRequest.remove(tracingCode);
            adminApprovalForFund.remove(tracingCode);
            System.out.println("you have been confirmed!");
            isConfirmed = true;
        } else if (admin.confirmationForAddingFund.get(tracingCode).equals("0")) {
            isConfirmed = false;
            admin.confirmationForAddingFund.remove(tracingCode);
            fundRequest.remove(tracingCode);
            adminApprovalForFund.remove(tracingCode);
            System.out.println("you have not been confirmed please try again");
        } else if (admin.confirmationForAddingFund.get(tracingCode).equals("2")) {
            System.out.println("you still have to wait");
        }

        return isConfirmed;
    }

    public void AddingFund(Admin admin)throws IOException{
        System.out.println("please enter your tracing code");
        int tracingCode = sc.nextInt();
        if (admin.confirmationForAddingFund.get(tracingCode).equals("2")) {
            System.out.println("please wait your request is still in processing level");
            Main.showUserMenu();
        } else {
            if (gettingConfirmationForAddingFund(admin, tracingCode)) {
                System.out.println("your request has been accepted please enter the amount of your deposit");
                int deposit = sc.nextInt();
                int newUserBalance = getUserBalance() + deposit;
                setUserBalance(newUserBalance);
                Main.showUserMenu();
            } else {
                System.out.println("your request has been rejected");
                Main.showUserMenu();
            }

        }

    }

    int totalPrice = 0;
    int totalQuantity=0;

    public void searchingProduct(String productName)throws IOException{
        product product = new product(productName);
        for (int i = 0; i < product.allTheAvailableProducts.size(); i++) {
            if (productName.equals(product.allTheAvailableProducts.get(i).getProductName())) {
                System.out.println(product.allTheAvailableProducts.get(i).getProductName() + product.allTheAvailableProducts.get(i).getProductPrice() + product.allTheAvailableProducts.get(i).getProductQuantity() + product.allTheAvailableProducts.get(i).getProductComments() + '\n');
                System.out.println("Do you want to add it to your shopping cart?");
                System.out.println("1 means yes and 0 means no");
                int choice = getInput();
                if (choice == 1) {
                    System.out.println("please enter how many of this product you want");
                    int quantity = sc.nextInt();
                    if (quantity <= product.allTheAvailableProducts.get(i).getProductQuantity()) {
                        String orderDate = "this day";
                        totalPrice = totalPrice + product.allTheAvailableProducts.get(i).getProductPrice() * quantity;
                        totalQuantity=totalQuantity+quantity;
                        Order order = new Order(getUsername(), product.allTheAvailableProducts.get(i).getSellerCompany(), productName, orderDate, totalQuantity,totalPrice, product.allTheAvailableProducts.get(i).getUuidOfProduct());
                        shoppingCart.add(order);
                        System.out.println("order successfully got added to your shopping cart!");
                        Main.showUserMenu();
                    } else {
                        System.out.println("sorry we don`t have that many!");
                        Main.showUserMenu();
                    }
                } else {
                    Main.showUserMenu();
                }
            } else {
                System.out.println("Not found!");
                Main.showUserMenu();
            }
        }
    }

    public int getInput() {
        int choice = -1;
        while (choice < 0 || choice > 6) {
            try {
                System.out.println("enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("invalid selection, try again");
            }
        }
        return choice;
    }

    public void submittingShoppingCart()throws IOException {
        loadingOrderSubmission();
        System.out.println("do you want to submit your shopping cart?");
        System.out.println("1 means yes and 0 means no");
        int choice = getInput();
        if (choice == 1) {
            for (int i = 0; i < shoppingCart.size(); i++) {
                totalPrice = totalPrice + shoppingCart.get(i).getProductPrice();
                if (userBalance >= totalPrice) {
                    System.out.println("your order has been sent to admin for approval!");
                    String orderInfo=shoppingCart.get(i).getBuyerUsername()+';'+shoppingCart.get(i).getSellerCompany()+';'+shoppingCart.get(i).getProductName()+';'+shoppingCart.get(i).getOrderDate()+';'+shoppingCart.get(i).getQuantityOfOrder()+';'+shoppingCart.get(i).getTotalPrice()+';'+shoppingCart.get(i).getUuidOfOrder();
                    savingOrderSubmission(orderInfo);
                    orderSubmission.add(shoppingCart.get(i));
                    System.out.println("please remember your tracing code!");
                    int tracingCode = orderSubmission.size() - 1;
                    System.out.println("your tracing code is:"+tracingCode);
                    adminSubmission.add("2");
                    savingAdminSubmission();
                    Main.showUserMenu();
                } else {
                    System.out.println("your wallet don`t have enough resources");
                    Main.showUserMenu();
                }
            }
        }
    }

    public void completingSale(shop shop) throws IOException{
        for (int i = 0; i < shoppingCart.size(); i++) {
            int newUserBalance = getUserBalance() - totalPrice;
            setUserBalance(newUserBalance);
            int newProductQuantity = shoppingCart.get(i).getProductQuantity() - shoppingCart.get(i).getQuantityOfOrder();
            product product = new product(shoppingCart.get(i).getSellerCompany(), shoppingCart.get(i).getProductName(), shoppingCart.get(i).getProductPrice(), shoppingCart.get(i).getProductQuantity());
            product.setProductQuantity(newProductQuantity);
            double newShopBalance = shop.getShopBalance() + totalPrice * 0.1;
            shop.setShopBalance(newShopBalance);
            Seller seller = new Seller(shoppingCart.get(i).getSellerCompany());
            double newSellerBalance = seller.getSellerBalance() + shoppingCart.get(i).getProductPrice() * 0.9;
            seller.setSellerBalance(newSellerBalance);
            purchasedProducts.add(shoppingCart.get(i));
            Order order = new Order(shoppingCart.get(i).getBuyerUsername(), shoppingCart.get(i).getSellerCompany(), shoppingCart.get(i).getProductName(), shoppingCart.get(i).getOrderDate(), shoppingCart.get(i).getQuantityOfOrder(), shoppingCart.get(i).getTotalPrice(), shoppingCart.get(i).getUuidOfOrder());
            order.allOrders.add(order);
            String orderInfo=shoppingCart.get(i).getBuyerUsername()+';'+shoppingCart.get(i).getSellerCompany()+';'+shoppingCart.get(i).getProductName()+';'+shoppingCart.get(i).getOrderDate()+';'+shoppingCart.get(i).getQuantityOfOrder()+';'+shoppingCart.get(i).getTotalPrice()+';'+shoppingCart.get(i).getUuidOfOrder()+'\n';
            savingAllCompletedOrders(orderInfo);
        }
        Main.showUserMenu();
    }

    public void checkIfOrderIsCompleted(Admin admin, shop shop) throws IOException{
        admin.loadingConfirmationForSubmittingOrders();
        System.out.println("please enter your tracing code");
        int tracingCode = sc.nextInt();
        if (admin.confirmationForSale.get(tracingCode).equals("1")) {
            System.out.println("your order has been successfully completed");
            completingSale(shop);
            //going back system
            Main.showUserMenu();
        } else if (admin.confirmationForSale.get(tracingCode).equals("0")) {
            System.out.println("you order was disapproved please try again!");
            //going back system
            Main.showUserMenu();
        } else if (admin.confirmationForSale.get(tracingCode).equals("2")) {
            System.out.println("please wait your order is still waiting for admin approval!");
            //going back system
            Main.showUserMenu();
        }
    }

    public void printingEditingMenu() {
        System.out.println("if you want to change your username enter 1");
        System.out.println("if you want to change your password enter 2");
        System.out.println("if you want to change your E-mail enter 3");
        System.out.println("if you want to change your phone number enter 4");
        System.out.println("if you want to change your address enter 5");
    }

    public void editingProfile() throws IOException{
        printingEditingMenu();
        System.out.println("please enter your choice!");
        switch (getInput()) {
            case 1:
                System.out.println("enter your new username");
                String newUsername = sc.nextLine();
                setUsername(newUsername);
                Main.showUserMenu();
                break;
            case 2:
                System.out.println("enter your new password");
                String newPassword = sc.nextLine();
                setPassword(newPassword);
                Main.showUserMenu();
                break;
            case 3:
                System.out.println("enter your new E-mail");
                String newEmail = sc.nextLine();
                setUserEmail(newEmail);
                Main.showUserMenu();
                break;
            case 4:
                System.out.println("enter your new phone number");
                String newPhoneNo = sc.nextLine();
                setUserPhoneNo(newPhoneNo);
                Main.showUserMenu();
                break;
            case 5:
                System.out.println("enter your new address");
                String newAddress = sc.nextLine();
                setUserAddress(newAddress);
                Main.showUserMenu();
                break;
        }
    }

    public void viewShoppingCart()throws IOException {
        for (int i = 0; i < shoppingCart.size(); i++) {
            System.out.println(shoppingCart.get(i).getProductName() + shoppingCart.get(i).getProductPrice() + shoppingCart.get(i).getQuantityOfOrder() + '\n');
            if (i == shoppingCart.size() - 1) {
                System.out.println("your total price of your order is:" + shoppingCart.get(i).getTotalPrice());
            }
        }
        Main.showUserMenu();
    }

    public void removingAnItem(String toBeRemovedItemName)throws IOException {
        for (int i = 0; i < shoppingCart.size(); i++) {
            if (toBeRemovedItemName.equals(shoppingCart.get(i).getProductName())) {
                shoppingCart.remove(i);
            }
        }
        Main.showUserMenu();
    }
    public void savingFundRequest(String username)throws IOException{
        FileWriter fundrequests=new FileWriter("fundRequest.txt");
        fundrequests.write(username+'\n');
        fundrequests.close();
    }
    public void savingAdminApprovalForFund()throws IOException{
        FileWriter adminApprovalForFunds=new FileWriter("adminApprovalForFunds.txt");
        adminApprovalForFunds.write("2"+'\n');
        adminApprovalForFunds.close();
    }
    public void savingOrderSubmission(String orderInfo)throws IOException{
        FileWriter orderSubmissions=new FileWriter("orderSubmissions");
        orderSubmissions.write(orderInfo);
        orderSubmissions.close();
    }
    public void savingAdminSubmission()throws IOException{
        FileWriter adminSubmissions=new FileWriter("adminSubmissionsForSale.txt");
        adminSubmissions.write("2"+'\n');
        adminSubmissions.close();
    }
    public void savingAllCompletedOrders(String orderInfo)throws IOException{
        FileWriter allCompletedOrders=new FileWriter("allCompletedOrders.txt");
        allCompletedOrders.write(orderInfo);
        allCompletedOrders.close();
    }
    public void loadingFundRequests()throws IOException{
        FileReader file=new FileReader("fundRequest.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String username=tokens.nextToken();
            fundRequest.add(username);
        }
        bf.close();
    }
    public void loadingAdminApprovalForFund()throws IOException{
        FileReader file=new FileReader("adminApprovalForFunds.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String a=tokens.nextToken();
            adminApprovalForFund.add(a);
        }
        bf.close();
    }
    public void loadingOrderSubmission()throws IOException{
        FileReader file=new FileReader("adminSubmissionsForSale.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String orderInfo=tokens.nextToken();
            orderSubmissionInString.add(orderInfo);
        }
        bf.close();
    }
    public void loadingAdminSubmission()throws IOException{
        FileReader file=new FileReader("adminSubmissionsForSale.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String a=tokens.nextToken();
            adminSubmission.add(a);
        }
        bf.close();
    }
    public void loadingAllCompletedOrders()throws IOException{
        FileReader file=new FileReader("allCompletedOrders.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String CompletedOrdersInfo=tokens.nextToken();
            Order order=new Order();
            order.allCompletedOrdersInString.add(CompletedOrdersInfo);
        }
        bf.close();
    }
}
