# AdaShip: Assignment 2
Essentially, AdaShip is a clone of the classic ‘Battleship’ game – as a default, AdaShip is a two-player, turn based game of sea warfare. You and an opponent each place a number of ships on your own board, and you then alternate turns "firing" torpedoes at each other’s ships. The game is won when one player has destroyed/sunk all of the other player’s ships.

##Repl.it: 
To run AdaShip on Repl.it you first need to import the GitHub Repo. Then please set the run configuration to the following:  
* Select Language: `Java`
* Run Command: `mvn clean compile exec:java`
  <a href="https://imgbb.com/"><img src="https://i.ibb.co/yNhY7Xg/Repl-it.png" alt="Repl-it" border="0"></a>
## Challenge Outline:
The Ada Battleships assignment is to create a console-style based game of "battleships". The code should be written in an advanced way, following the standards of Object Orientated Programming whilst using polymorphism and inheritance. And making sure that my code is both reusable and efficient.
### Problem Summary:
I will be creating a system that replicates the game of Battleships. The system will be created in multiple releases, each adding different aspects to the game so that it eventually supports: 
* Config File: Containing all data regarding the board and boats
* Ship Placements: Automatic, Manual
* Display the current players' gameboard, and a target board of the opponents.
* Validation: All user input, The location and direction of the boat being placed on the gameboard, The location of mines and torpedos.
* One player v computer game
* Two player game
* One player v computer (salvo) game
* Two player game (salvo) game
* One player v computer (hidden mines) game
* Two player game (hidden mines) game
* Computer v computer (hidden mines)
### UML Diagram:
<a href="https://ibb.co/j5pSZQG"><img src="https://i.ibb.co/3sJnYjW/Ada-Ship-UML.png" alt="Ada-Ship-UML" border="0"></a>
### Initial working plan, overall approach, development strategy and approach to quality:
My working plan is approach the problem by breaking down the overall problem down into smaller tasks. Each task will be organised on a Kanban board and worked on individually therefore taking an Agile approach to development. I will continuously check the quality of the code that I have written through regular code reviews with my partner, Morgan McNeil. This will help me to highlight code smells that I may not be necessarily able to see myself. I will try to reduce code smells as much as possible by making my code both efficient, and easy to read.
### Analysis and decomposition of the overall problem into key ‘epic’ style tasks:
My approach to creating this system will be to break down the epic, into smaller stories that can be worked on individually. I have created a Kanban board using GitHub to complete this process.
Kanban Board Link: `https://github.com/jamesspencer19/AdaShip/projects/1?fullscreen=true`
### Initial object-oriented design ideas and planned phased breakdown into smaller tasks:
I will break my system down into classes that will benefit my code re-usability, scalability and efficiency. I will work by implementing my solution in versions, by doing this I will be able to create a basic working game of battleships. It will then be possible to reuse code when implementing more complex gameplay types, such as Salvo and hidden mines.
## Development:
### Adoption and use of ‘good’ standards:
I would like to create a program that adopts the following "good" standards for the following reasons. By thoroughly commenting my code, it will not only help myself to remember to role of each part of the application such as different functions when troubleshooting issues. But it will also help any other developer that wishes to work on my project, as they will have to spend less time asking questions such as "What is this method used for?" as the appropriate documentation will be there to explain this. The next standard that I would like to adopt in my AdaShip Assignment is to reduce hard coding as much as possible, as stated in the assignment brief. We would like the application to be scaleable, allowing the user to change a config file letting them place as many ships as they like on a gameboard however large they would like it to be. The third "good" standard that I would like to use in my project is to use reduce duplicate code as much as possible, this will coincide with making my code both readable and efficient.
### Phase 1 development: tasks, code review and changes (repeated for each development phase.):
Tasks: 
* Create Config File
* Read Config File
* Create Basic Menu for Player vs Computer and Quit
* Create GameBoards and Target Boards
* Allow user to place ships
* Computer auto place ships
* Implement standard game logic

Code Review and Changes:
When first reading the config file I used a Maven dependency GSON, this enabled me to quickly read the config file when it was in a JSON format, however after a code review with my partner I came to the conclusion that it was best to revert the config file format back to .ini and read it manually using regex to separate the values.
### Phase 2 development: tasks, code review and changes:
Tasks:
* One player v computer (salvo) game
* Two player game (salvo) game
The salvo implementation updates the basic game play by allowing the current ‘player’ (player or  computer) to ‘fire’ one torpedo per their remaining ships. For example, if the ‘player’ has three  ‘non-destroyed’ ships instead of a single valid coordinate (e.g., F2) they could enter F2 E2 G2 (one  coordinate per ship) – once entered, details on any ‘hits’ and/or ‘misses’ are clearly provided, and all appropriate boards are updated to reflect this salvo

Code Review and Changes:
In this code review I realised that I was unable to keep track of the user/computers hits or misses using my current method of scoring as it would reset to zero after each shot. I then decided to create a salvo method (which could be reduced to one) for both the computer and the 
### Phase 3 development: tasks, code review and changes:
Tasks:
* One player v computer (hidden mines) game
* Two player game (hidden mines) game
* Computer v computer (hidden mines)
The hidden mines implementation updates the basic game play with five randomly dropped mines.  The hidden mines are essentially added to each ‘players’ shipboard and remain ‘hidden’ during the  set-up phase (optionally they could be added on completion of each set-up). However, they should  be clearly displayed during game turns as part of each ‘players’ shipboard.

Code Review and Changes:
In this code review I realised that I can use my initial Player vs Computer and Player vs Player methods by implementing a check for mine function within them. This then allowed me to reduce code smell by reusing a method so that my code was efficient and there was less duplication. This worked as the mine checker would be redundant in the standard methods and only come to life when and check for mines when I used my place mines method and there are mines on the gameboard.
### Ensuring quality through testing and resolving bugs:
After each new feature had been implemented, I would run through the small portion of code and thoroughly test the feature. I would both test for logical errors that could occur within the application and edge cases such as the user entering a character instead of an integer for the row coordinates. This process of testing small portions of code, regularly was repeated throughout the development process until the task was complete. If an error occurred during the tests that I was undertaking, I would go back and read through the code that I had just written to try and solve the root cause of the problem in comparison to implementing a "quick fix" that could cause greater problems in the future. 
### Reflection on key design challenges, innovations and how they were solved (with examples):
A key design challenge that I faced was due to the way in which I set up my gameboard, as a 2D Character Array. Initially I was only storing the Character values of ships on my gameboard, this meant that all ships occured to be the same and I was unable to differentiate between then to check which ship had been sunk. I then thought about my solution to this problem and I changed my approach to use an Integer 2D array. This allowed be to store data within the gameboard on which ship had been placed where, therefore I could then search the gameboard to see if any ships had sunk and if so which ship. Another design challenge that I faced was through the use of using a config file there could be the possibility that the user would add too many ships for the size of the board. By getting the sum of all ships and comparing this to the area of the board I am able to validate that the amount of ships that the user has placed in the config file are able to fit on the gameboard, and if not the error that this problem has occurred is diplayed to the user and the application is quit allowing the user to make their desired changes.
## Evaluation:
### Analysis with embedded examples of key code refactoring, reuse, smells:
### Implementation and effective use of ‘advanced’ programming principles (with examples):
### Features showcase and embedded innovations (with examples) - opportunity to ‘highlight’ best bits:
* Coloured Gameboard:
  * Green Ships
  * Red Hit
  * Cyan Mine Before Hit
  * Red Mine Once Hit
* Game Modes:
  * One player v computer game
  * Two player game
  * One player v computer (salvo) game
  * Two player game (salvo) game
  * One player v computer (hidden mines) game
  * Two player game (hidden mines) game
  * Computer v computer (hidden mines)
* Configureable:
  * Any number of ships until board limit (Validated)
  * Unlimited Board Size
  * Customizable ship lengths
  * Ability to change characters that represent icons on the gameboard
* Validation
  * All user input is validated
  * Config file is validated
  * Boat position and direction is validated
  * Torpedo Positions and Mine Positions are validated
* Auto-Place/AI:
  * Opportunity for user to autoplace all/remaining ships
  * Opportunity for the user to Auto Fire torpedo
* Ship Placement: 
  * User can place ships manually.
  * User can re-place ships
  * User can clear the board and replace all ships
  *  User can place some ships, then autoplace the rest
### Improved algorithms – research, design, implementation, and tested confirmation (with examples):
Unfortunately I was unable to implement the Improved ‘computer player’ targeting algorithm due to time constraints, however I can showcase the short and efficient way that I read the config .ini file. Through my research I was able to conclude that the use of Regex was best suited to the porblem of reading the config file,
### Reflective review, opportunities to improve and continued professional development:
Overall the next opportunity to improve would be to add the Improved ‘computer player’ targeting problem where I will design and implement a separate, optimised search and/or targeting algorithm, it is likely that your solution has implemented a generally random or basic algorithm to ‘pick’ targets; research and implement a better  than random solution to this problem. As I can now see after implementing the hidden mines logic to how I can taget the ship effectively, such as storing the location if the computer makes a hit, and the targeting the coordinates that are up, down, left and right of the previous hit and so on. Also after completing my code I can now see various areas that have duplicate code such as the validation of torpedo's and mines, and the switching of turns in the gamelogic class. Whilst working on this Advanced Programming Project I have realised that commenting my code effectively is extremely useful for both other users and myself especially when trying to troubleshoot problems.

