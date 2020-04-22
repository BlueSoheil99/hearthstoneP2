package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.log;

import edu.sharif.student.bluesoheil.ap98.hearthstone.models.Player;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 */
public class Logger {
    private static Logger logger;
    private FileWriter writer;
    private PrintWriter printer;
    private ArrayList<Log> recentLogs;


    private Logger(String logPath){

        recentLogs = new ArrayList<>();
        try {
            writer = new FileWriter(logPath,true);
            printer  = new PrintWriter(writer);
        }catch (IOException e){
            logError(LogTypes.PLAYER , e);
        }
    }

    public static void initLogger(Player player){
        logger = new Logger(player.getLogPath());

    }

    public static void closeLogfile(){
        logger.printer.close();
    }

    public static void createLogFile(Player player){
        initLogger(player);
        try {
            FileWriter writer = new FileWriter(player.getLogPath());
            PrintWriter printer =new PrintWriter(writer);
            printer.println("USER:\t\t"+player.getUsername());
            printer.println("CREATED_AT:\t" + LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy/MM/dd  HH:mm:ss") ));
            printer.println("PASSWORD:\t\t"+player.getPassword());
            printer.println(" ");  // " " is a sign for finalizeLogFile method.
            printer.close();
            //or writer.close() ??????
        }catch (Exception e ) {
            System.err.println(e.getMessage());
        }
        closeLogfile();
    }

    public static void finalizeLogfile(Player player){
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(player.getLogPath()));
            StringBuffer inputBuffer = new StringBuffer();
            String line ;

            while ( (line= file.readLine()) != null) {
                if(line.equals(" ")) {
                    inputBuffer.append("DELETED_AT:\t" +
                            LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy/MM/dd  HH:mm:ss"))+"\n \r\n" );
                    // \r\n to make a distance between header and body
                }else{
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }
            file.close();
            String inputStr = inputBuffer.toString();

            for (String x:inputStr.split("\n")) logger.printer.println(x);
            closeLogfile(); //that's because when we finalize a log file we are never going to make another log.

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    public static void log(LogTypes event , String description){
        logger.printer.println(event+"\t\t"+
                LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy/MM/dd  HH:mm:ss"))+
                "\t"+description);
        logger.recentLogs.add(new Log(event , description));


    }

    public static void logError(LogTypes event , Exception exception){
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
//        log("ERROR: "+event , exceptionAsString);
        logger.printer.println("ERROR: "+event+ "\t"+
                LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy/MM/dd  HH:mm:ss"))+
                "\t"+exceptionAsString);
        logger.recentLogs.add(new Log(event , exceptionAsString));
    }

    public static ArrayList<Log> getRecentLog(){
        return logger.recentLogs;
    }

}

