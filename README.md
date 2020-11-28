Ultimate Monopoly by NullPointer 

This project is developed by 
 * Berkay Barlas (Team leader)
 * Baran Berkay Hökelek
 * Furkan Sahbaz
 * Tumay Ozdemir
 * Alihan Zorlu

# Main Features
 - Multiplayer over same network up to 12 Players
 - Save / Load game state
 - Bot Players with 3 different level of AI
 - Network and System Failure Protection 
 - Chat with your friends within game


# Project Report
<a href="https://github.com/berkaybarlas/Ultimate-Monopoly-Online-Game/blob/master/Final_Project_Report.pdf" target="_blank">Project Report PDF</a>
# Video of Menu
![menu](https://raw.githubusercontent.com/berkaybarlas/Ultimate-Monopoly-Online-Game/master/menu.gif)

# Video of Game
![game](https://raw.githubusercontent.com/berkaybarlas/Ultimate-Monopoly-Online-Game/master/game.gif)

 # How to Play the Game:
	 You can either start a new server by clicking the Start Server button or join to an already existing server by clicking the Join Button. 
    If you click on the Join Server button instead, the address that you want to join into will be asked. So, if the game is played between different computers, their IP addresses should be known. 
    
    Regardless of whether you create a new game or join to an already established game, in the server screen two options are displayed: Start Game and Quit Server. 

	The game starts after the Start Game button is clicked. The board is displayed on the left side of the screen. In the middle part, we have the Button Panel. 
    - Roll Button is used to roll dice and move the player. Buy Property button is used to buy an unowned property. The button becomes unpressable if the square that the player on is not a property square or if the property square is already owned. 
    - Draw Card button is below the Buy Property button, it is used to draw an action card, such as a Community Chest Card or a Chance Card. 
    - Improve Property button is used to upgrade a property. Above the button panel, we have Dice Panel. 

	Player panel is on the right side of the Button Panel. The name of the players are displayed as buttons on a scrollable panel. When the names of the players are clicked, several information about them are displayed such as the Player name, Money, Owned properties and Owned utilities. 
	Lastly, on the bottom left part of the game window screen, there is a Message Box Panel. When new messages are added, they are first fitted into the message box screen and after a certain number of messages, the messages are displayed in a scrollable panel.
	
	
# Tested Classes:
    - GameEngine
    - PlayerController	
    - MoneyController
    - PropertySquare
    - Square (Various Square types such as Go to Jail, Go, Bonus, Reverse Direction, Roll 3, and Luxury Tax.)

# Changelog[18.11.2018]:
 - Several changes to methods of the Observer interface have been made, and several methods have been added to the GameEngine and UI classes in order to correctly subscribe, publish, and perform events.For example, DiceDisplay and PlayerPanel subscribed to GameEngine, and GameEngine is publishing by publisEvent(message) method and all observers's onEvent(message) methods are being called, then observers are doing something or not according to the message inside publishEvent method.
 - Constructors of various classes have been modified, depending on their needs.
Additionally, PropertySquare now has a list of rent such as rent without building anything or rent of the property in the case that there is one house built etc.
We added also a rentIndex to PropertySquare to show which rent now is the current rent in the rent list. 
 - A ServerInfo class has been added in order to maintain the connection between multiple computers.
 - We also made Board class in UI as a Thread by implementing Runnable interface because the movement and animation of the pawns take place on the Board in UI and animation is done by using Thread.sleep method.
 - **evaluateSquare(diceValue, speedDieValue, player)** has been changed to **evaluateSquare(gameEngine)**, since we need GameEngine to publish various events,    depending on which square the current player is on.
 - **buyProperty(propertySquare, player)** has been changed to **buy()**, since there needs to be some flexibility as to which type of square will be bought (Utility Square or Property Square).And, only current player can bought something 
 - The hasEnoughMoney(player, amount) method has been transferred to MoneyController class. 
 
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
 
# Credit
This is a project from Comp 302: Engineering course of Koç University instructed by Prof. Atilla Gürsoy.

# Code of Conduct
Koç University students who are currently taking this course, see Code of Conduct.


