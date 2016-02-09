package com.example.renat.tetris;


import android.content.Context;

import com.example.renat.tetris.game.World;
import com.example.renat.tetris.game.WorldSize;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Static class for reading and writing data.
 * Created by Renat on 17.07.2015.
 */
public  class Data {

    private static OutputStreamWriter writer;
    private static InputStream istream;
    private static InputStreamReader reader;
    private static BufferedReader bufferedReader;

    public static int HIGHSCORE;
    public static int SECOND_HIGHSCORE;
    public static boolean MUSIC_ON;
    public static WorldSize.Size SIZE;
    public static int CURRENT_LEVEL;
    public static int POINTS;



    public static void writeData(Context con) throws Exception{
        try {
            writer = new OutputStreamWriter(con.openFileOutput("data.txt",Context.MODE_PRIVATE));
            writer.write(            HIGHSCORE
                            + "\n" + SECOND_HIGHSCORE
                            + "\n" + Boolean.toString(MUSIC_ON)
                            + "\n" + SIZE
                            + "\n" + CURRENT_LEVEL
                            + "\n" + POINTS);
            writer.flush();

        }catch(Exception e){
            throw new Exception("Highscore could not be saved");
        }finally {
            if(writer != null)
                writer.close();
        }
    }

    public static String init(Context con){
        try{
            istream = con.openFileInput("data.txt");

            if(istream != null) {
                reader = new InputStreamReader(istream);
                bufferedReader = new BufferedReader(reader);
                String h = bufferedReader.readLine();

                try {
                    HIGHSCORE = Integer.parseInt(h);
                } catch (NumberFormatException e) {
                    return "Highscore could not be read. highscore was reseted";
                }

                h = bufferedReader.readLine();
                try{
                    SECOND_HIGHSCORE = Integer.parseInt(h);
                }catch(NumberFormatException e){
                    return "Highscore could not be read. highscore was reseted";
                }

                //read options
                try {
                    h = bufferedReader.readLine();
                    MUSIC_ON = Boolean.parseBoolean(h);
                    h = bufferedReader.readLine();

                   if(WorldSize.isAValidSize(h))
                       SIZE = WorldSize.Size.valueOf(h);
                   else
                       return "Options could not be read and are reseted";

                }catch (Exception e){
                    return "Options could not be read and are reseted";
                }

                //read current Level
                h = bufferedReader.readLine();
                try {
                    CURRENT_LEVEL = Integer.parseInt(h);
                }catch(Exception e){
                    return "Level State could not be read and is reseted";
                }

                h = bufferedReader.readLine();
                try{
                    POINTS = Integer.parseInt(h);
                }catch(Exception e){
                    return "Points could not be read";
                }

            }else{
                return "InputStream is null. highscore and options were reseted";
            }
        }catch(FileNotFoundException e){
            return "data.txt does not exist, highscore and options were reseted";
        }catch(IOException e) {
            return "data.txt could not be read. highscore and options were reseted";
        }catch(NullPointerException e){
            return "Context is null";
        }finally {
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (Exception e){
                    return "Some error happened";
                }
            }
        }
        return "";
    }

    public static void setDefaultOptions(){
        MUSIC_ON = true;
        SIZE = WorldSize.Size.DEFAULT;
    }



}
