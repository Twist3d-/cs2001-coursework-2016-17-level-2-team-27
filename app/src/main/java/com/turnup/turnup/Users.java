package com.turnup.turnup;

/**
 * Created by SHREYAS on 23-Feb-17.
 */

// This class is for Backendless Server, to store or get data when user registers

public class Users
{
    private String name, level, studentid, email,
            cardid, password, coursename, modulecodes;

    //------------------------------------
    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    //------------------------------------

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //------------------------------------

    public String getStudentID(){
        return studentid;
    }

    public void setStudentID(String studentid){
        this.studentid = studentid;
    }
    //------------------------------------

    public String getCardID(){
        return cardid;
    }

    public void setCardID(String cardid){
        this.cardid = cardid;
    }
    //------------------------------------

    public String getCourseName(){
        return coursename;
    }

    public void setCourseName(String coursename){
        this.coursename = coursename;
    }
    //------------------------------------

    public String getLevel(){
        return level;
    }

    public void setLevel(String level){
        this.level = level;
    }
    //------------------------------------

    public String getModuleCodes(){
        return modulecodes;
    }

    public void setModuleCodes(String modulecodes){
        this.modulecodes = modulecodes;
    }
    //------------------------------------

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


}
