package com.company.Commands;


import com.company.Command;
import com.company.Comparators.Comparator;
import com.company.Main;
import com.company.Models.Ticket;
import com.company.Printer;

import java.util.ArrayList;

public class Print_field_descending_type extends Command {
    @Override
    public void Execute(boolean is) {
        ArrayList<Ticket> a = new ArrayList<>();
        Main.tickets.iterator().forEachRemaining(a::add);
        a.sort(new Comparator());
        for (Ticket ticket : a){
            Main.writer.getResponces().add(ticket.getType().toString());
            if(is){
                Printer.getInstance().WriteLine(ticket.getType().toString());
            }
            else {
                Main.writer.getResponces().add(ticket.getType().toString());
            }
        }
    }
}
