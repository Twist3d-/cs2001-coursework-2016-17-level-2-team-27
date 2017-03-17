package com.turnup.turnup;

import java.util.Date;

/**
 * Created by SHREYAS on 27-Feb-17.
 */

// This Java class is used to pull and push data from AttendanceList Page from Server

public class AttendanceListServer
{
    private String name, level, studentid, email, cardid, coursename, room, moduleNo, chipNo;
    private Date start, end, attendedTime;


    public Date getStart() {
    return start;
}
    public void setStart( Date start ) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }
    public void setEnd( Date end ) {
        this.end = end;
    }

    public Date getAttendedTime() {
        return attendedTime;
    }
    public void setAttendedTime( Date attendedTime ) {
        this.attendedTime = attendedTime;
    }

    public String getRoom() {
        return room;
    }
    public void setRoom( String room ) {
        this.room = room;
    }

    public String getModuleNo() {
        return moduleNo;
    }
    public void setModuleNo( String moduleNo ) {
        this.moduleNo = moduleNo;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail( String email ) {
        this.email = email;
    }


    public String getStudentID(){
        return studentid;
    }
    public void setStudentID(String studentid){
        this.studentid = studentid;
    }

    public String getCardID(){
        return cardid;
    }
    public void setCardID(String cardid){
        this.cardid = cardid;
    }

    public String getCourseName(){
        return coursename;
    }
    public void setCourseName(String coursename){
        this.coursename = coursename;
    }

    public String getLevel(){
        return level;
    }
    public void setLevel(String level){
        this.level = level;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getChipNo() {
        return chipNo;
    }
    public void setChipNo( String chipNo ) {
        this.chipNo = chipNo;
    }
}
