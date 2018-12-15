package com.nullPointer.Domain.Controller;

import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Controller.PlayerController;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// about loading:
//Previous ServerInfo
//PlayerController
//Board
// HAS TO CHANGE PAWN INITIALIZING METHOD

public class SaveLoadController {

    private static SaveLoadController _instance;
  
    static final Player player = new Player("Testplayer ");
   // private PlayerController playerController = PlayerController.getInstance();
    
    private SaveLoadController() {

    }

    public static SaveLoadController getInstance() {
        if (_instance == null) {
            _instance = new SaveLoadController();
        }
        return _instance;
    }

    public void saveGame() throws IOException {

        ObjectOutputStream out = null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_dd_MM_YY");
        String saveFile = "savefile";//sdf.format(cal.getTime());
        try {
            out = new ObjectOutputStream(new
                    BufferedOutputStream(new FileOutputStream(saveFile)));
//            for(Player player : playerController.getPlayers()) {
//            	out.writeObject(player);
//            }
            out.writeObject(player);
            out.flush();
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


