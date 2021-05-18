package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Printer;

public class Remove_first extends Command {
    @Override
    public void Execute(boolean is)  {
        if(Main.tickets.size() != 0) {
            Main.tickets.removeFirst();
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
