package com.Momo;

/**
 * Created by Momo Johnson on 4/13/2016.
 */
import java.io.FileWriter;
import java.io.IOException;

public class ResolvedTickets  {

    public  void saveResolvedTickets(Ticket ticketToSave) {

        FileWriter writer;
        try {
            writer = new FileWriter("File_Resolved_Tickets.txt");
            writer.write(String.valueOf(ticketToSave)+ "\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("not found");
        }
    }


}

