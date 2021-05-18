package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Printer;


public class Remove_by_id extends Command {
    @Override
    public void Execute(boolean is)  {
        if(args.size() > 0) {
            if (Main.tickets.removeIf(u -> u.getId() == Long.parseLong(args.get(0)))) {
                if (is) {
                    Printer.getInstance().WriteLine("Успешно");
                } else {
                    Main.writer.getResponces().add("Успешно");
                }
            } else {
                if (is) {
                    Printer.getInstance().WriteLine("ID не найден");
                } else {
                    Main.writer.getResponces().add("ID не найден");
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
