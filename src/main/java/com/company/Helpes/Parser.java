package com.company.Helpes;

import com.company.Command;
import com.company.Models.Ticket;
import com.company.Models.Venue;

public class Parser {
    public static Parser Instance;

    public static Parser getInstance() {
        if(Instance == null){
            Instance = new Parser();
        }
        return Instance;
    }

    public String GetRequest(Command command){
        StringBuilder builder = new StringBuilder(command.args.get(0)).append("\r\n");
        for (int i = 1; i < command.args.size(); i++){
            if(command.args.size() - 1 == i)
                builder.append(command.args.get(i));
            else
                builder.append(command.args.get(i)).append(",");
        }
        return builder.toString();
    }

    public String GetTicket(Ticket ticket){
        return ticket.getName() + "," + ticket.getPrice() + "," + ticket.getCoordinates().getX() + "," +  ticket.getCoordinates().getY() + "," +
                ticket.getType() + "," + ticket.getVenue().getName() + "," + ticket.getVenue().getType() + "," + ticket.getVenue().getCapacity() + "," + ticket.getCreationDate().toString()
                + "," + ticket.getDiscount();
    }
    public String GetVenue(Venue venue){
        return venue.getName() + "," + venue.getCapacity() + "," + venue.getType();
    }

}
