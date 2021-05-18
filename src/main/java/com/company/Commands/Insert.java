package com.company.Commands;

import com.company.Command;
import com.company.Helpers.Converter;
import com.company.Helpers.Create;

public class Insert extends Command {
    @Override
    public void Execute(){
        args.add(Converter.getInstance().Write(Create.Set_Fields()));
    }
}
