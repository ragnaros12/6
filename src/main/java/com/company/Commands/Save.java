package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Parser;
import com.company.Printer;

public class Save extends Command {
    @Override
    public void Execute(boolean is_thread) throws Exception {
        if(is_thread) {
            Parser.getInstance().Save(Main.path);
            Printer.getInstance().WriteLine("успех");
        }
        else{
            Main.writer.getResponces().add("недостаточно прав на вызов комнады");
        }
    }
}
