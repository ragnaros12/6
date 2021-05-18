package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Printer;


public class Clear extends Command {
    @Override
    public void Execute(boolean is) throws Exception {
        Main.tickets.clear();
        if(is){
            Printer.getInstance().WriteLine("Удача");
        }
        else {
            Main.writer.getResponces().add("Удача");
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
