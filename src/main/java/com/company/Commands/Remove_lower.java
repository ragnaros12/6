package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Ticket;
import com.company.Parser;
import com.company.Printer;

public class Remove_lower extends Command {
    @Override
    public void Execute(boolean is)  {
        try {
            if(args.size() > 0) {
                Ticket ticket = Parser.getInstance().SetTicket(args.get(0));
                if (Main.tickets.removeIf(u -> u.compareTo(ticket) < 0)) {
                    if (is) {
                        Printer.getInstance().WriteLine("Успешно");
                    } else {
                        Main.writer.getResponces().add("Успешно");
                    }
                } else {
                    if (is) {
                        Printer.getInstance().WriteLine("Неудачно");
                    } else {
                        Main.writer.getResponces().add("Неудачно");
                    }
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
        catch (Exception e){
            if(is){
                Printer.getInstance().WriteLine("Ошибка команды");
            }
            else {
                Main.writer.getResponces().add("Ошибка команды");
            }
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
