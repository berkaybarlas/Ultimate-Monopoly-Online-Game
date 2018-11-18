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
 
 - Several changes to methods of the Observer interface have been made, and several methods have been added to the GameEngine and UI classes in order to correctly subscribe, publish, and perform events.For example, DiceDisplay and PlayerPanel subscribed to GameEngine, and GameEngine is publishing by publisEvent(message) method and all observers's onEvent(message) methods are being called, then observers are doing something or not according to the message inside publishEvent method.
 - Constructors of various classes have been modified, depending on their needs. Most importantly, Player's constructor and fields changed such as player now has layer because game has 3 layers totally and we wanted to keep the layer info of the player's position, also player now has targetPosition.
Additionally, PropertySquare now has a list of rent such as rent without building anything or rent of the property in the case that there is one house built etc.
We added also a rentIndex to PropertySquare to show which rent now is the current rent in the rent list. 
 - Unimplemented squares automatically call nextTurn() for this demo.
 - A ServerInfo class has been added in order to maintain the connection between multiple computers.
 - We also made Board class in UI as a Thread by implementing Runnable interface because the movement and animation of the pawns take place on the Board in UI and animation is done by using Thread.sleep method.
 - **evaluateSquare(diceValue, speedDieValue, player)** has been changed to **evaluateSquare(gameEngine)**, since we need GameEngine to publish various events,    depending on which square the current player is on.
 - **buyProperty(propertySquare, player)** has been changed to **buy()**, since there needs to be some flexibility as to which type of square will be bought (Utility Square or Property Square).And, only current player can bought something 
 - Also, the hasEnoughMoney(player, amount) method has been transferred to MoneyController class. 
 -  
 # Implemented Sequence Diagrams:
 - Sequence Diagram 1: Move from Current Square to Destination Square.
 - Sequence Diagram 4: Buy Property
 - Sequence Diagram 5: Pay Rent
 - Sequence Diagram 6: Start Server
 The method names and class names which include those methods written in the diagrams are the same as the ones in the code except from small changes explained above.
 # Class Diagram:
 Every class included in the class diagram except BotBehavior is implemented with small changes which are specified above and also ServerProtocol class is not added because of being unnecessary.
