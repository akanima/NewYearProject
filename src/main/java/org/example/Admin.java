package org.example;




import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public  class Admin implements Serializable {
    Scanner sc=new Scanner(System.in);
    private String AdminUsername;
    private String AdminPassword;
    private String AdminEmail;
    ArrayList<String> confirmationForAddingFund=new ArrayList<>();
    ArrayList<String> confirmationForSale=new ArrayList<>();
    ArrayList<String> confirmationForNewSellers=new ArrayList<>();
    public Admin(){}
    public Admin(String AdminUsername , String AdminPassword , String AdminEmail){
        this.AdminUsername=AdminUsername;
        this.AdminPassword=AdminPassword;
        this.AdminEmail=AdminEmail;
    }
    public void setAdminUsername(String AdminUsername){
        this.AdminUsername=AdminUsername;
    }
    public String getUsername(){
        return this.AdminUsername;
    }
    public void setAdminPassword(String AdminPassword){
        this.AdminPassword=AdminPassword;
    }
    public String getAdminPassword(){
        return this.AdminPassword;
    }
    public void setAdminEmail(String AdminEmail){
        this.AdminEmail=AdminEmail;
    }
    public String getAdminEmail(){
        return this.AdminEmail;
    }
    public void gettingAdminApprovalForSeller(Registration registration)throws IOException{
        registration.loadingSellerRequests();
        registration.loadingAdminApproval();
        for(int i=0 ; i<registration.adminApproval.size();i++){
            System.out.println(registration.adminApproval.get(i)+" "+registration.newSellersCompany.get(i)+'\n');
        }
        for(int i=0; i<registration.adminApproval.size(); i++){
            System.out.println("Dear Admin these users are waiting for your confirmation and the number which is next to them show their status");
            System.out.println("you can choose:");
            System.out.println("1: for approving their request");
            System.out.println("0: for disapproving their request");
            System.out.println("2: for deciding later");
            int choice=getInput();
            registration.adminApproval.set(i,Integer.toString(choice));
            confirmationForNewSellers.add(Integer.toString(choice));
            savingConfirmationForNewSellers(Integer.toString(choice));
        }
    }

    public int getInput(){
        int choice=-1;
        while(choice<0 || choice>5){
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
    public void gettingAdminApprovalForFund(User user)throws IOException{
        user.loadingFundRequests();
        user.loadingAdminApprovalForFund();
        for(int i=0 ; i<user.adminApprovalForFund.size();i++){
            System.out.println(user.adminApprovalForFund.get(i)+" "+user.fundRequest.get(i)+'\n');
        }
        for(int i=0; i<user.adminApprovalForFund.size(); i++){
            System.out.println("Dear Admin these users are waiting for your confirmation and the number which is next to them show their status");
            System.out.println("you can choose:");
            System.out.println("1: for approving their request");
            System.out.println("0: for disapproving their request");
            System.out.println("2: for deciding later");
            int choice=getInput();
            confirmationForAddingFund.add(Integer.toString(choice));
            savingConfirmationForAddingFund(Integer.toString(choice));
        }
        Main.showAdminMenu();
    }
    public  void gettingAdminApprovalForSubmittingOrder(User user)throws IOException{
        user.loadingAdminSubmission();
        user.loadingOrderSubmission();
        for(int i=0 ; i<user.orderSubmission.size();i++){
            System.out.println(user.adminSubmission.get(i)+" "+user.orderSubmission.get(i));
        }
        for(int i=0; i<user.adminSubmission.size(); i++){
            System.out.println("Dear Admin you should choose between 0 & 1  for every username shown and the numbers that are shown are in order");
            System.out.println("related to each company situation");
            System.out.println(user.orderSubmission.get(i).getSellerCompany());
            System.out.println(user.orderSubmission.get(i).getProductName());
            System.out.println(user.orderSubmission.get(i).getBuyerUsername());
            int choice=getInput();
            confirmationForSale.add(Integer.toString(choice));
            savingConfirmationForSubmittingOrders(Integer.toString(choice));
        }
        Main.showAdminMenu();
    }
    public void viewUserProfile(Registration registration,String username)throws IOException{
        registration.loadingUsers();
        for(int i=0 ; i<registration.usersInfoInString.size();i++) {
            if (registration.usersInfoInString.get(i).contains(username)) {
                System.out.println(registration.usersInfoInString.get(i));
                Main.showAdminMenu();
            } else {
                System.out.println("this username does not exist!");
                Main.showAdminMenu();
            }
        }
    }
    public void savingConfirmationForNewSellers(String choice) throws IOException{
        FileWriter confirmationForNewSellers=new FileWriter("confirmationForNewSellers.txt");
        confirmationForNewSellers.write(choice+'\n');
        confirmationForNewSellers.close();
    }
    public void savingConfirmationForAddingFund(String choice) throws IOException{
        FileWriter confirmationsForAddingFunds=new FileWriter("confirmationsForAddingFunds.txt");
        confirmationsForAddingFunds.write(choice+'\n');
        confirmationsForAddingFunds.close();
    }
    public void savingConfirmationForSubmittingOrders(String choice) throws IOException{
        FileWriter confirmationForSubmittingOrders=new FileWriter("confirmationForSubmittingOrders.txt");
        confirmationForSubmittingOrders.write(choice+'\n');
        confirmationForSubmittingOrders.close();
    }
    public void loadingConfirmationForNewSellers()throws IOException{
        FileReader file=new FileReader("confirmationForNewSellers.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String confirmationForNewSeller=tokens.nextToken();
            confirmationForNewSellers.add(confirmationForNewSeller);
        }
        bf.close();
    }
    public void loadingConfirmationForAddingFund()throws IOException{
        FileReader file=new FileReader("confirmationsForAddingFunds.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String confirmForFund=tokens.nextToken();
            confirmationForAddingFund.add(confirmForFund);
        }
        bf.close();
    }
    public void loadingConfirmationForSubmittingOrders()throws IOException{
        FileReader file=new FileReader("confirmationForSubmittingOrders.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String confirmForSale=tokens.nextToken();
            confirmationForSale.add(confirmForSale);
        }
        bf.close();
    }
}
