COMP 302 NullPointer Ultimate Monopoly

This project is developed by 
 * Baran Berkay Hökelek
 * Furkan Sahbaz
 * Tumay Ozdemir
 * Berkay Barlas 
 * Alihan Zorlu
 # How to Run Game
 - To run on local computer click start server. 
 - To join a server you should know server IP.
 # Changelog[18.11.2018]:
 
 - Several changes to methods of the Observer interface have been made, and several methods have been added to the GameEngine and UI classes in order to correctly subscribe, publish, and perform events.
 - Constructors of various classes have been modified, depending on their needs.
 - Unimplemented squares automatically call nextTurn() for this demo.
 - A ServerInfo class has been added in order to maintain the connection between multiple computers.
 - **evaluateSquare(diceValue, speedDieValue, player)** has been changed to **evaluateSquare(gameEngine)**, since we need GameEngine to publish various events,    depending on which square the current player is on.
 - **buyProperty(propertySquare, player)** has been changed to **buy()**, since there needs to be some flexibility as to which type of square will be bought (Utility Square or Property Square).And, only current player can bought something 
 - Also, the hasEnoughMoney(player, amount) method has been transferred to MoneyController class. 
 -  
 
 