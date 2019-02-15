COMP 302 NullPointer Ultimate Monopoly

This project is developed by 
 * Baran Berkay Hökelek
 * Furkan Sahbaz
 * Tumay Ozdemir
 * Berkay Barlas 
 * Alihan Zorlu
 
| Video of Game |
|:---:|:---:|
| ![feedPage](https://raw.githubusercontent.com/berkaybarlas/friendYol/master/images/feedPage.gif) | ![OtherPages](https://raw.githubusercontent.com/berkaybarlas/friendYol/master/images/otherPages.gif) |

# Image of Board 

![boardImage](https://raw.githubusercontent.com/berkaybarlas/Ultimate-Monopoly-Online-Game/tree/master/assets/ultimate_monopoly.png)


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
 - Every class included in the class diagram except BotBehavior is implemented with small changes which are specified above and also 
 - **ServerProtocol** class is not deleted because it does not needed.
 - **ServerInfo** class is added to store number of connected clients and their IPs.
 - A variable added to Player class in order to check which client it is from.

 # How to Run the Game:
	When the game window pops up, we can either start a new server by clicking the Start Server button or join to an already existing server by clicking the Join Button. If you click on the Start Server button, a window pops up and asks the number of players for that game session. When the player connects to his/ her local host, the player creates a client. If you click on the Join Server button instead, the local address that you want to join into is asked. So, if the game is played between different computers, their IP addresses should be known. In the case of playing the game in one computer, the IP address that you need to join is displayed after the client is created by the founder player (the player who starts the game first). Regardless of whether you create a new game or join to an already established game, in the server screen two options are displayed: Start Game and Quit Server. 
	The game starts after the Start Game button is clicked. The board is displayed on the left side of the screen. In the middle part, we have the Button Panel. Roll Button is used to roll dice and move the player. Buy Property button is used to buy an unowned property. The button becomes unpressable if the square that the player is on is not a property square or if the property square is already owned. Draw Card button is below the Buy Property button, it is used to draw an action card, such as a Community Chest Card or a Chance Card. Lastly, Improve Property button is used to upgrade a property. Above the button panel, we have Dice Panel. The values that are obtained from the roll dice button are displayed in the Dice Panel.
	Player panel is on the right side of the Button Panel. The name of the players are displayed as buttons on a scrollable panel. When the names of the players are clicked, several information about them are displayed such as the Player name, Money, Owned properties and Owned utilities. 
	Lastly, on the bottom left part of the game window screen, we have the Message Box Panel. Messages can be written into the text field and by using the Submit button, we messages be sent. The messages are displayed on the screen below the Player Panel. When new messages are added, they are first fitted into the message box screen and after a certain number of messages, we messages are displayed as a scrollable panel.
	We also have an additional panel (Menu Window) on the top part of the screen. In that panel, there are Send Message, Menu, Game and Quit buttons. This part is currently used by the developer and its main purpose is to test the game more easily. When the Send Message button is clicked, if the server is working, �hello� message is displayed on the console. Menu and Game buttons allows the developer to quickly switch through the game and the server menu. Finally, Quit button is used to close the game window. 
	
# Tested Classes:
    - GameEngine
    - PlayerController	
    - MoneyController
    - PropertySquare
    - Square (Various Square types such as Go to Jail, Go, Bonus, Reverse Direction, Roll 3, and Luxury Tax.)