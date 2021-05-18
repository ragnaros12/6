package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Printer;

public class Info extends Command {
    @Override
    public void Execute(boolean is)  {
        if(is){
            Printer.getInstance().WriteLine("Начало старта: " + Main.start.toString() + "\r\nКол-во элементов: " + Main.tickets.size());
        }
        else {
            Main.writer.getResponces().add("Начало старта: " + Main.start.toString() + "\r\nКол-во элементов: " + Main.tickets.size());
        }
    }
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
