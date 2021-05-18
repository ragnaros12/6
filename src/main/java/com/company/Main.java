package com.company;

import com.company.Commands.*;
import com.company.Helpers.Converter;
import com.company.Helpers.Create;
import com.company.Writers.Logger;
import com.company.Models.Tickets;
import com.company.Models.Writer;
import com.company.Writers.Printer;
import org.reflections.Reflections;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Main {

    public static DatagramPacket recieve = new DatagramPacket(new byte[2048], 2048);
    public static DatagramPacket send;
    public static DatagramSocket server;
    public static ArrayList<Command> commands = new ArrayList<>();
    public static Writer writer = new Writer();
    public static Tickets tickets = new Tickets();
    public static int ids = 0;
    public static LocalDateTime start;
    public static String ip = "127.0.0.1";
    public static int port = 1112;
    public static Thread console;
    public static Logger logger;
    public static String path = "C:\\file.txt";

    public static void console_thread(){
        while (true){
            try {
                Printer.getInstance().WriteLine("введите команду");
                String next = Printer.getInstance().ReadLine().trim();
                boolean isCommand = false;
                for (Command command : commands) {
                    if (next.startsWith(command.getName()) || next.toLowerCase(Locale.ROOT).startsWith(command.getName().toLowerCase(Locale.ROOT))) {
                        command.args.addAll(Arrays.asList(next.split(",")));
                        command.args.remove(0);
                        if(command.getClass() == Insert.class || command.getClass() == Replace_if_greater.class || command.getClass() == Replace_if_lower.class || command.getClass() == Update.class){
                            command.args.add(Converter.getInstance().Write(Create.Set_Fields()));
                        }
                        command.Execute(true);
                        isCommand = true;
                        command.args.clear();
                    }
                }
                if (!isCommand) {
                    Printer.getInstance().WriteLine("нет такой команды");
                }
            }
            catch (Exception e){
                Printer.getInstance().WriteLine("вы каким то образом поломали программу, что ж вы за человек");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Printer.Init(System.in, new PrintStream(System.out, true, Charset.forName("windows-1251")));
        InetAddress address;
        try{
            address = InetAddress.getByName(System.getenv("address"));
        }
        catch (UnknownHostException e){
            address = InetAddress.getByName(ip);
        }
        try{
             logger = new Logger(new FileOutputStream("C:\\log.txt"));
        }
        catch (Exception ignored){

        }
        Printer.getInstance().WriteLine(address);
        console = new Thread(Main::console_thread);
        console.start();

        start = LocalDateTime.now();
        logger.WriteLine("старт программы: " + start.toString());

        try {
            for (Class<? extends Command> class1 : (new Reflections("com.company.Commands")).getSubTypesOf(Command.class)) {// получаем все классы наследуемые от command
                if(class1 != Exist.class) {
                    commands.add(class1.getConstructor().newInstance());// добавляем
                }
            }
        }
        catch (Exception ignored){

        }
        logger.WriteLine("установленны все команды");

        if(args.length == 0){
            path = "C:\\file.txt";
        }
        else{
            path = args[0];
        }
        try{
            tickets = Converter.getInstance().Read_file(Tickets.class, path);
            if(tickets == null){
                tickets = new Tickets();
            }
        }
        catch (Exception ignored){
            tickets = new Tickets();
        }
        server = new DatagramSocket(port, address);
        logger.WriteLine("сервер запущен");
        try {
            while (true) {
                recieve = new DatagramPacket(new byte[2048], 2048);
                server.receive(recieve);
                logger.WriteLine("получено сообщение от клиента c ip: " + recieve.getAddress());
                String out = new String(recieve.getData(), StandardCharsets.UTF_16);
                Command command = Converter.getInstance().Read(Exist.class, out);
                logger.WriteLine("текст сообщения: " + out);
                boolean is = false;
                for (Command command1 : commands) {
                    if (command.getName().startsWith(command1.getName()) || command.getName().toLowerCase(Locale.ROOT).startsWith(command1.getName().toLowerCase(Locale.ROOT))) {
                        command1.args = command.args;
                        command1.Execute(false);
                        is = true;
                    }
                }
                if (!is) {
                    writer.getResponces().add("такой команды не существует");
                }
                writer.getResponces().add("введите команду");

                byte[] response = Converter.getInstance().Write(writer).getBytes(StandardCharsets.UTF_16);
                /*for (byte r : responce){
                    System.out.print(r + "  ");
                }*/
                writer.getResponces().clear();
                send = new DatagramPacket(response, response.length, recieve.getAddress(), port-1);
                logger.WriteLine("отправлен ответ на: " + recieve.getAddress() + ".Текст: " + Converter.getInstance().Write(writer));
                server.send(send);
            }
        }
        catch (Exception e){

        }
        try {
            Converter.getInstance().write_to_file(tickets, args[0]);
            Printer.getInstance().Close();
        }
        catch (Exception ignored){

        }
        logger.WriteLine("сервер закончил работу");
        logger.log_steam.close();
    }
}
