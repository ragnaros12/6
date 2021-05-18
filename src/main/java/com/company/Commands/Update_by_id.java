package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Ticket;
import com.company.Parser;
import com.company.Printer;

import java.util.ArrayList;

public class Update_by_id extends Command {
    @Override
    public void Execute(boolean is)  {
        if(args.size() > 1) {
            Long id = Long.parseLong(args.get(0));
            ArrayList<Ticket> tickets = new ArrayList<>(Main.tickets);

            Ticket ticket = Parser.Instance.SetTicket(args.get(1));
            boolean isb = false;

            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i).getId().equals(id)) {
                    tickets.set(i, ticket);
                }
            }

            Main.tickets.clear();
            Main.tickets.addAll(tickets);
            if (isb) {
                Main.writer.getResponces().add("Успешно");
            } else {
                Main.writer.getResponces().add("ID не найден");
            }
        }
        else{
            if(is){
                Printer.getInstance().WriteLine("Не все аргументы");
            }
            else {
                Main.writer.getResponces().add("Не все аргументы");
            }
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
