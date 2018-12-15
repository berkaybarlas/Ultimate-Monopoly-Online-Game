package com.nullPointer.Domain.Controller;

import com.nullPointer.Domain.Model.DomainBoard;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Controller.PlayerController;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

// about loading:
//Previous ServerInfo
//PlayerController
//Board
// HAS TO CHANGE PAWN INITIALIZING METHOD

public class SaveLoadController {

    private static SaveLoadController _instance;

    private CommunicationController communicationController = CommunicationController.getInstance();
    // private PlayerController playerController = PlayerController.getInstance();

    private SaveLoadController() {

    }

    public static SaveLoadController getInstance() {
        if (_instance == null) {
            _instance = new SaveLoadController();
        }
        return _instance;
    }

    public void saveGame(String saveFile) throws IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new
                    BufferedOutputStream(new FileOutputStream("/saveFiles/" + saveFile)));
            PlayerController playerController = PlayerController.getInstance();
            out.writeObject(playerController);
            //DomainBoard domainBoard = GameEngine.getInstance().getDomainBoard();
            //out.writeObject(domainBoard);
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

    public void saveGame() throws IOException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_dd_MM_YY");
        String saveFile = "savefile";//sdf.format(cal.getTime());
        saveGame(saveFile);

    }

    public void loadGame(String fileName) throws IOException {
        ObjectInputStream in = null;
        PlayerController playerController;
        try {
            in = new ObjectInputStream(new
                    BufferedInputStream(new FileInputStream(fileName)));
            //read
            playerController = (PlayerController) in.readObject();
            //System.out.println(playerTest);
            communicationController.sendClientMessage(playerController);

        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    public ArrayList<String> getSavedFiles() {
        File dir = new File("/saveFiles/");

        File[] matches = dir.listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.startsWith("temp") && name.endsWith(".txt");
            }
        });
        ArrayList<String> fileList = new ArrayList<>();
        for (int i = 0; i < matches.length; i++) {
            File match = matches[i];
            fileList.add(match.getName());
            System.out.println("[SaveLoadController]: " + match.getName());

        }
        return fileList;
    }
}


