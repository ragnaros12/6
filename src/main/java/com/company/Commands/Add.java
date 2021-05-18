package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Parser;
import com.company.Printer;

public class Add extends Command {

    @Override
    public void Execute(boolean is)  {
        if(args.size() >= 1) {
            Main.tickets.add(Parser.getInstance().SetTicket(args.get(0)));
            if(is){
                Printer.getInstance().WriteLine("Успешно");
            }
            else {
                Main.writer.getResponces().add("Успешно");
            }
        }
        else {
            if(is){
                Printer.getInstance().WriteLine("Неудача");
            }
            else {
                Main.writer.getResponces().add("Неудача");
            }
        }
    }
}
