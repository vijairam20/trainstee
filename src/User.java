import java.util.*;
public class User {
    String name ;
    String username ;
    String password ;
    String email_id ;
    int id ;

    User(String name , String username , String password , String email_id , int id)
    {
        this.name = name ;
        this.username = username ;
        this.password = password ;
        this.email_id = email_id;
        this.id = id ;
    }

    public void print()
    {
        System.out.println("Name: "+name);
        System.out.println("Username : "+username);
        System.out.println("Password :" +password);
        System.out.println("Email-id : "+email_id);
    }

}
