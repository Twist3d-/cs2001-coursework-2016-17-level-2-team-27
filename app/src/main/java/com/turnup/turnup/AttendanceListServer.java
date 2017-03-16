package com.turnup.turnup;

import java.util.Date;

/**
 * Created by SHREYAS on 27-Feb-17.
 */

// This Java class is used to pull and push data from AttendanceList Page from Server

public class AttendanceListServer
{
    private String name, level, studentid, email, cardid, coursename, room, moduleNo;
    private Date start, end, attendedTime;


    public Date getStart() {
    return start;
}
    public void setStart( String startString ) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }
    public void setEnd( String endString ) {
        this.end = end;
    }

    public Date getAttendedTime() {
        return attendedTime;
    }
    public void setAttendedTime( String attendedTimeString ) {
        this.attendedTime = attendedTime;
    }

    public String getRoom() {
        return room;
    }
    public void setRoom( String roomString ) {
        this.room = room;
    }

    public String getModuleNo() {
        return moduleNo;
    }
    public void setModuleNo( String moduleNoString ) {
        this.moduleNo = moduleNo;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail( String studentEmailIdString ) {
        this.email = email;
    }


    public String getStudentID(){
        return studentid;
    }
    public void setStudentID(String studentIdString){
        this.studentid = studentid;
    }

    public String getCardID(){
        return cardid;
    }
    public void setCardID(String studentCardIdString){
        this.cardid = cardid;
    }

    public String getCourseName(){
        return coursename;
    }
    public void setCourseName(String courseNameString){
        this.coursename = coursename;
    }

    public String getLevel(){
        return level;
    }
    public void setLevel(String courseLevelString){
        this.level = level;
    }

    public String getName(){
        return name;
    }
    public void setName(String studentNameString){
        this.name = name;
    }
}
