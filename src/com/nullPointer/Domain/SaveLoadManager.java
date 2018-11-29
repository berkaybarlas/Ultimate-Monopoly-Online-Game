package com.nullPointer.Domain;

import com.nullPointer.Domain.Model.Player;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SaveLoadManager {

    private static SaveLoadManager _instance;
    static final String dataFile = "invoicedata";
    static final Player player = new Player("Testplayer ");

    private SaveLoadManager() {

    }

    public static SaveLoadManager getInstance() {
        if (_instance == null) {
            _instance = new SaveLoadManager();
        }
        return _instance;
    }

    public void saveGame() throws IOException {

        ObjectOutputStream out = null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("savefile_HH_mm_dd_MM_YY");
        String saveFile = "savefile";//sdf.format(cal.getTime());
        try {
            out = new ObjectOutputStream(new
                    BufferedOutputStream(new FileOutputStream(saveFile)));

            out.writeObject(player);
            System.out.println("Game state is saved to " + saveFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void loadGame(String fileName) throws IOException {
        ObjectInputStream in = null;
        Player playerTest;
        try {
            in = new ObjectInputStream(new
                    BufferedInputStream(new FileInputStream(fileName)));
            //read
            playerTest = (Player) in.readObject();
            System.out.println(playerTest);
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}


