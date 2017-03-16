package com.turnup.turnup;

import java.util.Date;

/**
 * Created by SHREYAS on 27-Feb-17.
 */

// This class is for Backendless Server, to push and pull data from Timetable page in server

public class TimetableServer
{
    private Date day;
    private String activity;
    private String description;
    private Date start;
    private Date end;
    private String room;
    private String staff;
    private String moduleNo;
    private String chipNo;

    public Date getDay() {
        return day;
    }
    public void setDay( String dayString ) {
        this.day = day;
    }

    public String getActivity() {
        return activity;
    }
    public void setActivity( String activityString ) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription( String descriptionString ) {
        this.description = description;
    }

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

    public String getRoom() {
        return room;
    }
    public void setRoom( String roomString ) {
        this.room = room;
    }

    public String getStaff() {
        return staff;
    }
    public void setStaff( String staffString ) {
        this.staff = staff;
    }

    public String getModuleNo() {
        return moduleNo;
    }
    public void setModuleNo( String moduleNoString ) {
        this.moduleNo = moduleNo;
    }

    public String getChipNo() {
        return chipNo;
    }
    public void setChipNo( String chipNoString ) {
        this.chipNo = chipNo;
    }
}
