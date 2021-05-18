package com.company.Commands;

import com.company.Command;
import com.company.Comparators.Comparator_by_coordinates;
import com.company.Main;
import com.company.Models.Ticket;
import com.company.Printer;

import java.util.ArrayList;

public class Group_counting_by_coordinates extends Command {
    @Override
    public void Execute(boolean is) {//сортируем, и прохрдим массив, ищя одинаковые, и считаем количество каждого
        try {
            ArrayList<Ticket> tickets = new ArrayList<>();
            Main.tickets.iterator().forEachRemaining(tickets::add);
            tickets.sort(new Comparator_by_coordinates());
            int sch = 1;
            for (int i = 1; i < tickets.size(); i++) {
                if (tickets.get(i - 1).getCoordinates().equals(tickets.get(i).getCoordinates())) {
                    sch++;
                } else {
                    if(is){
                        Printer.getInstance().WriteLine(tickets.get(i - 1).getCoordinates() + "\t\nколичество = " + sch);
                    }
                    else {
                        Main.writer.getResponces().add(tickets.get(i - 1).getCoordinates() + "\t\nколичество = " + sch);
                    }
                    sch = 1;
                }
            }
            if(is){
                Printer.getInstance().WriteLine(tickets.get(tickets.size() - 1).getCoordinates() + "\t\nколичество = " + sch);
            }
            else {
                Main.writer.getResponces().add(tickets.get(tickets.size() - 1).getCoordinates() + "\t\nколичество = " + sch);
            }
        }
        catch (Exception w){
            Main.writer.getResponces().add("Ошибка в команде");
        }
    }
}
