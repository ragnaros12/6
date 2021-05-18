package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Writers.Printer;

public class Clear extends Command {// ни одна команда не используется напрямую. То есть их получение происходит в момент исполнения
    @Override
    public void Execute(boolean is_thread) throws Exception {
        Main.tickets.getTickets().clear();
        if(is_thread){
            Printer.getInstance().WriteLine("список очищен");
        }
        else {
            Main.writer.getResponces().add("список очищен");
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
