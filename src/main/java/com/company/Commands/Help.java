package com.company.Commands;


import com.company.Command;
import com.company.Main;
import com.company.Printer;

public class Help extends Command {
    @Override
    public void Execute(boolean is) {
        for (Command a: Main.commands) {
            if(is){
                Printer.getInstance().WriteLine("Команда: " + a.getName());
            }
            else {
                Main.writer.getResponces().add("Команда: " + a.getName());
            }
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
