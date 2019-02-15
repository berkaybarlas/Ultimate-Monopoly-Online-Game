package com.nullPointer.Domain.Controller;

import com.nullPointer.Domain.Model.DomainBoard;
import com.nullPointer.Domain.Model.GameEngine;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
            File dir = new File("./saveFiles/");
            dir.mkdirs();
            out = new ObjectOutputStream(new
                    BufferedOutputStream(new FileOutputStream("./saveFiles/" + saveFile)));
            PlayerController playerController = PlayerController.getInstance();
            out.writeObject(playerController);

            MoneyController moneyController = MoneyController.getInstance();
            out.writeObject(moneyController);

            DomainBoard domainBoard = GameEngine.getInstance().getDomainBoard();
            out.writeObject(domainBoard);

            out.flush();
            System.out.println("Game state is saved to " + saveFile);
            out.close();

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

        String saveFile = "savefile_" + sdf.format(cal.getTime());
        saveGame(saveFile);

    }

    public void loadGame(String fileName) throws IOException {
        ObjectInputStream in = null;
        PlayerController playerController;
        DomainBoard domainBoard;
        MoneyController moneyController;
        try {
            in = new ObjectInputStream(new
                    BufferedInputStream(new FileInputStream("./saveFiles/" + fileName)));

            playerController = (PlayerController) in.readObject();
            communicationController.sendClientMessage(playerController);

            moneyController = (MoneyController) in.readObject();
            communicationController.sendClientMessage(moneyController);

            domainBoard = (DomainBoard) in.readObject();
            communicationController.sendClientMessage(domainBoard);

        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    public ArrayList<String> getSavedFiles() {
        File dir = new File("./saveFiles/");

        File[] matches = dir.listFiles((dir1, name) -> name.startsWith("save"));

        ArrayList<String> fileList = new ArrayList<>();
        for (int i = 0; i < matches.length; i++) {
            File match = matches[i];
            fileList.add(match.getName());
        }
        return fileList;
    }
}


