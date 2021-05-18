package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Venue;
import com.company.Parser;
import com.company.Printer;

public class Remove_all_by_venue extends Command {
    @Override
    public void Execute(boolean is) throws Exception {
        if(args.size() > 0) {
            Venue v = Parser.getInstance().SetVenue(args.get(0));
            if (Main.tickets.removeIf(u -> u.getVenue().equals(v))) {
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

}
