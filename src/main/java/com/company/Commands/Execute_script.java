package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;


public class Execute_script extends Command {
    @Override
    public void Execute(boolean is) throws Exception {
        try{
            if(args.size() > 0) {
                Scanner scanner = new Scanner(new File(args.get(0)));

                while (scanner.hasNext()) {
                    String next = scanner.nextLine().trim();
                    for (Command Commands : Main.commands) {
                        if (next.startsWith(Commands.getName()) || next.startsWith(Commands.getName().toLowerCase(Locale.ROOT))) {
                            Commands.Execute(is);
                        }
                    }
                }
            }
            else{
                if(is){
                    Printer.getInstance().WriteLine("Не все аргументы");
                }
                else {
                    Main.writer.getResponces().add("Не все аргументы");
                }
            }
        }
        catch (FileNotFoundException e){
            if(is){
                Printer.getInstance().WriteLine("Файла нет или доступ к нему запрещен");
            }
            else {
                Main.writer.getResponces().add("Файла нет или доступ к нему запрещен");
            }
        }
        catch (Exception e){
            if(is){
                Printer.getInstance().WriteLine("Команда работает неверно");
            }
            else {
                Main.writer.getResponces().add("Команда работает неверно");
            }
        }
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
