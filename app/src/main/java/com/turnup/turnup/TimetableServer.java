package com.turnup.turnup;

import java.util.Date;

/**
 * Created by SHREYAS on 27-Feb-17.
 */

// This class is for Backendless Server, to push and pull data from Timetable page in server

public class TimetableServer
{
    private String activity;
    private String description;
    private Date start;
    private Date end;
    private String room;
    private String staff;
    private String moduleNo;
    private String chipNo;


    public String getActivity() {
        return activity;
    }
    public void setActivity( String activity ) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription( String description ) {
        this.description = description;
    }

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

    public String getRoom() {
        return room;
    }
    public void setRoom( String room ) {
        this.room = room;
    }

    public String getStaff() {
        return staff;
    }
    public void setStaff( String staff ) {
        this.staff = staff;
    }

    public String getModuleNo() {
        return moduleNo;
    }
    public void setModuleNo( String moduleNo ) {
        this.moduleNo = moduleNo;
    }

    public String getChipNo() {
        return chipNo;
    }
    public void setChipNo( String chipNo ) {
        this.chipNo = chipNo;
    }
}
