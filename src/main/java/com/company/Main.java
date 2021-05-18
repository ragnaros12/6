package com.company;

import com.company.Commands.Empty;
import com.company.Helpes.Parser;
import com.company.Manager.TicketManager;
import com.company.Witers.Printer;
import com.company.Witers.Sender;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    public static ArrayList<String> comm_with_tickets = new ArrayList<>();
    public static TicketManager manager = new TicketManager();

    public static void main(String[] args) throws IOException {
        Printer.Init(System.in, new PrintStream(System.out, true, Charset.forName("windows-1251")));
        comm_with_tickets.add("add");
        comm_with_tickets.add("remove_lower");
        comm_with_tickets.add("update_by_id");

        String ip = "";
        int host;
        while (true){
            try {
                Printer.getInstance().WriteLine("введите ip");
                ip = Printer.getInstance().ReadLine();
                InetAddress.getByName(ip);
                Printer.getInstance().WriteLine("введите host");
                host = Integer.parseInt(Printer.getInstance().ReadLine());
                break;
            }
            catch (Exception e){
                Printer.getInstance().WriteLine("неверно ввведены значения");
            }
        }
        Sender.Init(InetAddress.getByName(ip), host);

        while (true){
            Empty empty = new Empty();
            String command = Printer.getInstance().ReadLine();
            if(command.toLowerCase(Locale.ROOT).startsWith("exit")){
                System.exit(0);
            }
            else{
                empty.args = new ArrayList<>(Arrays.asList(command.split(",")));
                empty.args = empty.args.subList(0, empty.args.size());
                if(command.toLowerCase(Locale.ROOT).equals("remove_all_by_venue")){
                    empty.args.add(Parser.getInstance().GetVenue(manager.GetVenue()));
                }
                else {
                    for (String comm : comm_with_tickets) {
                        if (command.toLowerCase(Locale.ROOT).startsWith(comm.toLowerCase(Locale.ROOT))) {
                            empty.args.add(Parser.getInstance().GetTicket(manager.get_ticket()));
                        }
                    }
                }
                Sender.getInstance().Send(Parser.getInstance().GetRequest(empty));

                Printer.getInstance().WriteLine(Sender.getInstance().Recieve());
            }
        }
    }
}
