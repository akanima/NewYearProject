package org.example;



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;

public class Registration implements Serializable {
    Scanner sc=new Scanner(System.in);
    ArrayList<User> users=new ArrayList<>();
    ArrayList<String> usersInfoInString=new ArrayList<>();
    ArrayList<Admin> admins=new ArrayList<>();
    ArrayList<String> newSellersCompany =new ArrayList<>();
    ArrayList<String> adminApproval=new ArrayList<>();
    ArrayList<String> userUsernameInString=new ArrayList<>();
    ArrayList<String> userPasswordInString=new ArrayList<>();
    ArrayList<String> SellerCompanyInString=new ArrayList<>();
    ArrayList<String> SellerPasswordInString=new ArrayList<>();
    ArrayList<String> approvedSellersInString=new ArrayList<>();
    String predefinedAdminUsername="nima";
    String predefinedAdminPassword="nima1383";
    String predefinedAdminEmail="n.shafieinejad@gmail.com";
    public void addingPredefinedAdmin(){
        Admin admin=new Admin(predefinedAdminUsername,predefinedAdminPassword,predefinedAdminEmail);
        admins.add(admin);
    }
    public Registration(){
        addingPredefinedAdmin();
    }
    public void makingSellerRequest() throws IOException {
        loadingSellerRequests();
        System.out.println("please enter your company name for approval");
        String sellerCompanyName=sc.nextLine();
        newSellersCompany.add(sellerCompanyName);
        savingNewSellers(sellerCompanyName);
        System.out.println("please remember your tracing code!");
        int tracingCode= newSellersCompany.size()-1;
        System.out.println("your tracing code is:"+tracingCode);
        adminApproval.add("2");
        savingAdminApproval();
    }
    public void completingSellerInfo()throws IOException{
        gettingApprovedSellerCompanyName();
        UUID uuidOfSeller=UUID.randomUUID();
        String approvedSellerInfo=gettingApprovedSellerCompanyName()+";"+gettingApprovedSellerPassword()+";"+uuidOfSeller;
        savingSellers(approvedSellerInfo);
        System.out.println("you are now officially a seller you can add products in seller menu");
        Main.showSellerMenu();
    }
    public String gettingApprovedSellerCompanyName()throws IOException{
        System.out.println("please enter your company name");
        String companyName=sc.nextLine();
        SellerCompanyInString.add(companyName);
        savingApprovedSellersCompanyName(companyName);
        return companyName;
    }
    public String gettingApprovedSellerPassword()throws IOException{
        System.out.println("please enter your password");
        String sellerPassword=sc.nextLine();
        savingApprovedSellersPasswords(sellerPassword);
        SellerPasswordInString.add(sellerPassword);
        return sellerPassword;
    }
    public void CompletingUserInfo(String username) throws IOException{
        userUsernameInString.add(username);
        savingNewUsernames(username);
        System.out.println("please enter a password");
        String password=sc.nextLine();
        savingNewUsersPasswords(password);
        userPasswordInString.add(password);
        System.out.println("please enter an E-mail");
        String userEmail=sc.nextLine();
        System.out.println("please enter a phone number");
        String userPhoneNo= sc.nextLine();
        System.out.println("please enter an address");
        String userAddress=sc.nextLine();
        UUID uuidOfUser=UUID.randomUUID();
        int userBalance=0;
        User user=new User(username,password,userEmail,userPhoneNo,userAddress,userBalance,uuidOfUser);
        users.add(user);
    }
    public boolean checkingExistanceForUsername(String username){
        for(int i=0 ; i<userUsernameInString.size();i++){
            if(username.equals(userUsernameInString.get(i))){
                return false;
            }
        }
        return true;
    }
    boolean loggedIn=false;
    public boolean AdminLogin(String adminUsername,String adminPassowrd){
        for(int i=0 ;i<admins.size();i++){
            if(adminUsername.equals(admins.get(i).getUsername()) && adminPassowrd.equals(admins.get(i).getAdminPassword())){
                 return true;
            }
        }
        return false;
    }
    public boolean sellerLogin(String sellerCompany,String sellerPassword)throws IOException{
        loadingSellerCompany();
        loadingSellerspasswords();
        for(int i=0 ;i<SellerPasswordInString.size();i++){
            if(sellerCompany.equals(SellerCompanyInString.get(i)) && sellerPassword.equals(SellerPasswordInString.get(i))){
                return true;
            }
        }
        return false;
    }
    public boolean userLogin(String username,String password)throws IOException{
        loadingUsersUsernames();
        loadingUserspasswords();
        for(int i=0 ;i<userUsernameInString.size();i++){
            if(username.equals(userUsernameInString.get(i))&& password.equals(userPasswordInString.get(i)) ){
                return true;
            }
        }
        return false;
    }
    public void savingNewSellers(String newSellerCompanName) throws IOException {
        FileWriter newSellersFile=new FileWriter("newSellerInfo.txt");
        newSellersFile.write(newSellerCompanName+'\n');
        newSellersFile.close();
    }
    public void savingAdminApproval() throws IOException{
        FileWriter AdminApproval=new FileWriter("adminApproval.txt");
        AdminApproval.write("2"+'\n');

        AdminApproval.close();
    }
    public void savingSellers(String approvedSellerInfo) throws IOException{
        FileWriter approvesSellersInfo=new FileWriter("approvedSellersInfo.txt");

        approvesSellersInfo.write(approvedSellerInfo + '\n');
        approvesSellersInfo.close();
    }

    public void loadingSellerRequests()throws IOException{
        FileReader file=new FileReader("newSellerInfo.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while ((line=bf.readLine())!=null) {
            StringTokenizer tokens=new StringTokenizer(line,";");
            String sellerCompanyName=tokens.nextToken();
            newSellersCompany.add(sellerCompanyName);
        }
        bf.close();
    }
    public void loadingAdminApproval()throws IOException{
        FileReader file=new FileReader("adminApproval.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String adminApprovals=tokens.nextToken();
            adminApproval.add(adminApprovals);
        }
        bf.close();
    }
    public void loadingApprovedSellers()throws IOException{
        FileReader file=new FileReader("approvedSellersInfo.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String newSellerInfo=tokens.nextToken();
            approvedSellersInString.add(newSellerInfo);
        }
        bf.close();
    }
    public void loadingUsers()throws IOException{
        FileReader file=new FileReader("usersInfo.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String userInfo=tokens.nextToken();
            usersInfoInString.add(userInfo);
        }
        bf.close();
    }
    public boolean checkingSellerRequestStatus()throws IOException{
        boolean isConfirmed=false;
        System.out.println("please enter your tracing code to check your confirmation!");
        int tracingCode=sc.nextInt();
        Admin admin=new Admin("nima","nima1383","withnima83@gmail.com");
        admin.loadingConfirmationForNewSellers();
        if(admin.confirmationForNewSellers.get(tracingCode).equals("2")){
            System.out.println("you still have to wait");
            isConfirmed=false;
        } else if (admin.confirmationForNewSellers.get(tracingCode).equals("1")) {
            isConfirmed=true;
        } else if (admin.confirmationForNewSellers.get(tracingCode).equals("0")) {
            System.out.println("you have been rejected");
            isConfirmed=false;
        }
        return isConfirmed;
    }
    public void savingNewUsernames(String username)throws IOException{
        FileWriter file=new FileWriter("users.txt");
        file.write(username+'\n');
        file.close();
    }
    public void savingNewUsersPasswords(String passowrd)throws IOException{
        FileWriter file=new FileWriter("userspassword.txt");
        file.write(passowrd);
        file.close();
    }
    public void loadingUsersUsernames()throws IOException{
        FileReader file=new FileReader("users.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String username=tokens.nextToken();
            userUsernameInString.add(username);
        }
        bf.close();
    }
    public void loadingUserspasswords()throws IOException{
        FileReader file=new FileReader("userspassword.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String passowrd=tokens.nextToken();
            userPasswordInString.add(passowrd);
        }
        bf.close();
    }
    public void savingApprovedSellersCompanyName(String sellerCompany)throws IOException{
       FileWriter file=new FileWriter("approvedSellersCompanyName.txt");
       file.write(sellerCompany);
       file.close();
    }
    public void savingApprovedSellersPasswords(String sellerPassowrd)throws IOException{
        FileWriter file=new FileWriter("approvedSellersPassword.txt");
        file.write(sellerPassowrd);
        file.close();
    }
    public void loadingSellerCompany()throws IOException{
        FileReader file=new FileReader("approvedSellersCompanyName.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String sellerCompany=tokens.nextToken();
            SellerCompanyInString.add(sellerCompany);
        }
        bf.close();
    }
    public void loadingSellerspasswords()throws IOException{
        FileReader file=new FileReader("approvedSellersPassword.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String passowrd=tokens.nextToken();
            SellerPasswordInString.add(passowrd);
        }
        bf.close();
    }

}
