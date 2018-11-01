package com.nullPointer.Server;

public class ServerProtocol {
    private static final int WAITING = 0;
    private static final int STARTED = 1;
    private static final int END = 2;
    private static final int ANOTHER = 3;

    private int state = WAITING;

    private String[] request = { "start","rollDice", "buy", "makeAction"};
    private String[] response = { "start","roll", "bought", "act","Game Ended" };

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Waiting for start order";
            if (theInput !=null && theInput.equalsIgnoreCase(request[0])) {
                theOutput = response[0];
                state = STARTED;
            }
        } else if (state == STARTED) {
            if (theInput.equalsIgnoreCase(request[1])) {
                theOutput = response[1];
            } else if (theInput.equalsIgnoreCase(request[2])) {
                theOutput = response[2];
            } else if (theInput.equalsIgnoreCase(request[3])) {
                theOutput = response[3];
            } else {
                theOutput = "Response is not accepted: 500";
            }
        } else if (state == END) {
            theOutput = "winner";
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Money! Money! Money!";

            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}