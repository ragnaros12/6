package com.company.Commands;


import com.company.Command;
import com.company.Main;
import com.company.Printer;

public class Head extends Command {
    @Override
    public void Execute(boolean is)  {
        if(Main.tickets.size() != 0) {
            if(is){
                Printer.getInstance().WriteLine(Main.tickets.getFirst().toString());
            }
            else {
                Main.writer.getResponces().add(Main.tickets.getFirst().toString());
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
}
