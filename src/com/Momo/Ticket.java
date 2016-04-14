package com.Momo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Momo Johnson on 3/26/2016.
 */
public class Ticket {
    private static int staticTicketIDCounter = 1;

    private int priority;
    private String reporter;
    private String description;
    private Date dateReported;

    protected int ticketID;

    //We can autogenerate get and set methods if and when we need

    //A constructor would be useful

    public Ticket(String desc, int p, String rep,Date date)
    {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;


    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public static void saveResolvedTickets(Ticket ticketToSave) {

        FileWriter writer;
        try {
            writer = new FileWriter("File_Resolved_Tickets.txt");
            writer.write(String.valueOf(ticketToSave) + "\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("not found");
        }



    }




    //Called automatically if a Ticket object is an argument to System.out.println
    public String toString(){
        return("ID : " +this.ticketID + " Issues: "+ this.description + " Priority: " + this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported);
    }


}

