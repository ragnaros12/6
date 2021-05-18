package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Ticket;
import com.company.Printer;

public class Show extends Command {
    @Override
    public void Execute(boolean is) {
        if(Main.tickets.size() != 0) {
            for (Ticket ticket : Main.tickets) {
                if(is){
                    Printer.getInstance().WriteLine(ticket.toString());
                }
                else {
                    Main.writer.getResponces().add(ticket.toString());
                }
            }
        }
        else{
            if(is){
                Printer.getInstance().WriteLine("Ни одного элемента нет");
            }
            else {
                Main.writer.getResponces().add("Ни одного элемента нет");
            }
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
