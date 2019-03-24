package com.android.dyslexia;

public class Users {
    private String username;
    private String useremail;
    private String userpassword;
    private String id;
    public Users(String username, String useremail, String userpassword, String id) {
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public String getUseremail() {
        return useremail;
    }


    public String getUserpassword() {
        return userpassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users(){

    }
}
