package com.company.Writers;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Logger {

    public OutputStream log_steam;

    public void WriteLine(String str){
        try {
            byte[] bytes = (str + "\r\n").getBytes(StandardCharsets.UTF_16);
            log_steam.write(bytes);
        }
        catch (Exception ignored){

        }
    }

    public Logger(OutputStream log_steam) {
        this.log_steam = log_steam;
    }
}
