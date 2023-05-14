import com.mysql.cj.jdbc.interceptors.ResultSetScannerInterceptor;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/dbcclass";
    static  final String DB_USERNAME="root";
    static  final String DB_PASSWORD="";
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(DB_DRIVER);
        String sql1 = "select* from student";

        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement stat = con.createStatement();
            ResultSet result= stat.executeQuery(sql1);
            System.out.println("ID-------------------NAME-----------DEP");
            while (result.next()){
                System.out.print(result.getString(1)+"\t\t");
                System.out.print(result.getString(2)+"\t\t");
                System.out.print(result.getString(3));
                System.out.println();
            }
            Scanner input=new Scanner(System.in);
            System.out.println("register student");
            System.out.println("enter id");
            String id =input.next();
            System.out.println("name");
            String name= input.next();
            System.out.println("enter department");
            String department= input.next();
            String query3="insert into student(id,name,department) values ('"+id+"','"+name+"','"+department+"')";
            //String query2 = "insert into student(id,name,department) values ('102','john','cs')";

            if (stat.executeUpdate(query3) == 1) {
                System.out.println("Data created successfully");
            } else {
                System.out.println("Failed");
            }
            System.out.println("enter id you want to update");
            String idupdate= input.next();
            System.out.println("enter the name of student");
            String updatename=input.next();
            System.out.println("enter the department");
            String updatedept=input.next();
            String queryupdate="update student set name='"+updatename+"',department='"+updatedept+"' where id='"+idupdate+"'";
            int numrows=stat.executeUpdate(queryupdate);
            if(numrows==1){
                System.out.println("Data Updated successfully");

            } else {
                System.out.println("Faile to Update");
            }

            System.out.println("Enter id number you want to delete");
            String iddelete= input.next();
            String querydelete="delete from student  where id='"+iddelete+"'";
            if(stat.executeUpdate(querydelete)==1){
                System.out.println("Data deleted successfully");
            } else {
                System.out.println("Failed to delete");
            }


            stat.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }}