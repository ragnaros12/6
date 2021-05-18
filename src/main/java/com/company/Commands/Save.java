package com.company.Commands;

import com.company.Command;
import com.company.Helpers.Converter;
import com.company.Main;

public class Save extends Command {
    @Override
    public void Execute(boolean is_thread) throws Exception {
        if(is_thread){
            Converter.getInstance().write_to_file(Main.tickets, Main.path);
        }
    }
}
