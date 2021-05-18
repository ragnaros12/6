package com.company;

import com.company.Commands.Empty;
import com.company.Models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    public static Parser Instance;

    public static Parser getInstance() {
        if(Instance == null){
            Instance = new Parser();
        }
        return Instance;
    }

    public String GetRequest(Command command){
        StringBuilder builder = new StringBuilder(command.getName() + "\r\n");
        for (String arg : command.args){
            if(command.args.get(command.args.size()-1).equals(arg))
                builder.append(arg);
            else
                builder.append(arg).append(",");
        }
        return builder.toString();
    }



    public Command SetRequest(String req){
        String[] arr = req.split("\r\n");
        if(arr.length >= 1){
            Empty empty = new Empty();
            empty.setName(arr[0]);
            empty.args.addAll(Arrays.asList(arr));
            empty.args = empty.args.subList(1, empty.args.size());
            return empty;
        }
        else{
            return null;
        }
    }

    public Ticket SetTicket(String ticket){
        String[] split = ticket.split(",");
        return new Ticket(split[0], new Coordinates(Integer.parseInt(split[2]), Long.parseLong(split[3])), Integer.parseInt(split[1]), Integer.parseInt(split[9]), TicketType.valueOf(split[4]), new Venue(split[5], Integer.parseInt(split[7]), VenueType.valueOf(split[6])));
    }

    public String GetWriter(Writer writer){
        String re = "";
        for (int i = 0; i < writer.responces.size(); i++){
            re += writer.responces.get(i);
            if(i + 1 != writer.responces.size()){
                re += "\r\n";
            }
        }
        return re;
    }

    public Venue SetVenue(String re){
        String[] spl = re.split(",");
        return new Venue(spl[0], Integer.parseInt(spl[1]), VenueType.valueOf(spl[2].trim()));
    }
    public String GetVenue(Venue venue){
        return venue.getName() + "," + venue.getCapacity() + "," + venue.getType();
    }

    public void Save(String path) {
        try {
            OutputStream outputStream = new FileOutputStream(path);
            for (Ticket ticket : Main.tickets){
                byte[] buffer = GetTicket(ticket).getBytes(StandardCharsets.UTF_16);
                outputStream.write(buffer,0, buffer.length);
                if(!ticket.getId().equals(Main.tickets.getLast().getId())){
                    byte[] buffer1 = ("\r\n").getBytes(StandardCharsets.UTF_16);
                    outputStream.write(buffer1,0, buffer1.length);
                }
            }
            outputStream.close();
        }
        catch (FileNotFoundException e){
            Printer.getInstance().WriteLine("файл не найден");
        } catch (IOException e) {
            Printer.getInstance().WriteLine("ошибка работы с файлом");
        }

    }
    public void Read(String path){
        try {
            Scanner scanner = new Scanner(new FileInputStream(path), StandardCharsets.UTF_16);
            while (scanner.hasNext()) {
                String[] values = scanner.next().split(",");
                try {
                    Ticket ticket = new Ticket();
                    ticket.setName(values[0]);
                    ticket.setPrice(Integer.parseInt(values[1].trim()));
                    ticket.setCoordinates(new Coordinates(Integer.parseInt(values[2].trim()), Long.parseLong(values[3].trim())));
                    ticket.setType(TicketType.valueOf(values[4].replace(" ", "")));
                    ticket.setVenue(new Venue(values[5], Integer.parseInt(values[7].trim()), VenueType.valueOf(values[6].trim())));
                    ticket.setCreationDate(ZonedDateTime.parse(values[8].trim()));
                    ticket.setDiscount(Integer.parseInt(values[9].trim()));
                    Main.tickets.add(ticket);
                }
                catch (Exception e){
                    Printer.getInstance().WriteLine("значния  либо неверные, либо недопустимые");
                }
            }
        }
        catch (FileNotFoundException e){
            Printer.getInstance().WriteLine("файл не найден");
        }
    }
    public String GetTicket(Ticket ticket){
        return ticket.getName() + "," + ticket.getPrice() + "," + ticket.getCoordinates().getX() + "," +  ticket.getCoordinates().getY() + "," +
                ticket.getType() + "," + ticket.getVenue().getName() + "," + ticket.getVenue().getType() + "," + ticket.getVenue().getCapacity() + "," + ticket.getCreationDate().toString()
                + "," + ticket.getDiscount();
    }
}
