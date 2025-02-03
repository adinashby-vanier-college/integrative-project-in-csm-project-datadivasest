# Deliverable 1 Files
## Concept: 
Describe the physical, mathematical and/or chemistry concept(s) behind the project: (tab)
This platform game will have users travel around different terrains intending to capture various elements/atoms/molecules to solve questions.

### Level 1: 
Given a certain period and family, the user must use the periodic table to determine which element it is. Then, find the protons/electrons necessary to create the atom. 

### Level 2: 
Given a chemical equation, the user must use stoichiometry to determine the missing molecule and find the atoms necessary to form that molecule/compound. Different worlds will exist and the user will use a map to travel from one world to another. Each world corresponds to a periodic family, where the scenery will show the properties of that family. This way, the user will become familiar with the properties of each family, and it will help them find the atoms they are looking for. (each world is simply a different scene).

### Level 3:
The user will face challenges and store winning molecules in their bag. Then, they will face different obstacles, like a plant that needs H2O and CO2. By doing so, the user will continue to the next stage, where an acidic pool awaits them. To neutralize the pool, the user must find a base and drop it to create water and salt. At this level, the user is practicing different reactions such as photosynthesis, neutralization and cellular respiration. 

Essentially, this game will have students practice various general chemistry notions including the periodic table, stoichiometry, neutralization, photosynthesis, neutralization and cellular respiration. 

## Concept Aspects: 
Identify and list the main aspects of the concept such as the problem it addresses, the proposed solution, and the solution category among other approaches’ categories: (sofia)

The main aspects of the concept that the problem addresses is all chemistry related. In particular, they address problems that were encountered during the General Chemistry course at Vanier College (202-NYA-05). 
This includes: **Nomenclature, Photosynthesis, Cellular Respiration, Molecular bonding, Atomic Structure, The atom, The Periodic Table, Stoichiometry, Neutralization, Molar mass and mole conversions, Thermochemistry, Periodic Trends, Energy Calculations.**

The proposed solution to how the proposed program will address these problems will be through an engaging and interactive game simulation that the user can play to test their knowledge about the aforementioned concepts. This solution uses graphical user interfaces, data structures, and the knowledge acquired from the programming courses 420-201-RE, 420-202-RE, 420-203-RE, and 420-204-RE to create a game-like program that has built in obstacles the user interacts with in order to progress in the game. The user will have an on screen avatar that goes through different and diverse obstacles that will each apply what they have learned to practice. This could be through collecting the parts of the atom, mini quizzes, solving stoichiometric problems, etc. This program will allow the user to demonstrate their understanding of concepts in a fun and creative way. This approach will be designed as an easy to use educational resource. It is intended to help students revise what they learned, evaluate their understanding and outline which subjects they may struggle with more. 

## The possible variable parameters that would control the user interface animating the concept: (laila)
  * Variable: Animation during reactions -> once users add molecules as reactants, animations will show the process and the change to products. 
  * Variable: Power ups (speed, x-ray) -> different power ups change the user interface, like x-ray vision will show the screen differently.
  * Variable: User moving/interactions -> as the user moves around WASD, their character moves in the screen and interactions with objects like rocks might generate objects (electrons, protons, atoms).
  * Variable: Volume/Pause/Reset -> buttons change the volume of the game and reset the game.
  * Variable: Revelant SFX -> special effects like sounds when there are other variables. 
  * Variable: HUD -> displays health, backpack (inventory), score in real-time

## Typical Input: 
Describe the typical input for the solution of the applied concept to work: (sofia)

The typical inputs for this solution can vary. The user will be expected to click on certain parts of the screen at times, or use the indicated arrow buttons to move their avatar (WASD). They may also be expected to input numbers or choose between options on the screen. The inputs are dependent on what obstacle the user is encountering or which part of the game they are on. For instance, when choosing the concept they would like to review, they will need to use their mouse and click on the desired concept. Or, they may be collecting atoms and need to move their arrow keys in order to collect them. The type of action expected is limited to the user’s mouse click and keys on their keyboard. 

## Expected Output: 
Describe the expected output and how the user interface would look like and what it would allow the user to do: (tab)

* First page: Enter game (maybe create login if we have time eventually)
* Second page: There will be a grid layout of the choices of characters -> the user will click on which one they want to be. 
* Third page: Background information and storyline explained -> user left-click to continue 
* Fourth page: (level I)
The user is given a period and family -> type (or choose) the correct element from the periodic element 
The user looks around the scene to find electrons/protons till they have enough to get to the door and enter (WASD or up/down/right/left) 
* Fifth page: (level II) 
The user is given a balanced chemical equation -> user types (or chooses) the missing molecule using stoichiometry
The user uses the map (top left corner -> new scene) to pick a world (each world -> a periodic family) 
The user finds the atoms through the worlds -> go through the door once it's solved
* Sixth page: (level III)
The user is given a list of reactions -> match to their name (might change to a different mini-game?)
The user is faced with different challenges -> must feed the correct molecule to pass (go to different worlds to get the molecule)
A hungry tree in front of a bridge -> find water and feed it (gets sugar) -> animation once he’s fed -> happy 
A scene full of acid -> must use base to neutralize the acid (gets water and salt)
* Seventh page 
The end of the game 
Story finishes (dialogue) -> defeated unstable molecules ->user left-clicks to continue 

## Feasibility: 
### List the JavaFX elements and implementation components you expect to use to implement the project. (eliza)

* JavaFX UI Elements:
* Canvas – For drawing game objects, backgrounds, and animations.
* Pane (StackPane, AnchorPane, or BorderPane) – To organize UI elements.
* HBox/VBox/GridPane – To structure menus, chemistry facts, and inventories.
* Scene/Stage – To manage different screens (main menu, game, settings, etc.).
* Button – For menu interactions and in-game actions.
* Label – For displaying scores, instructions, and chemistry facts.
* CheckBox - For choosing different settings (eg Dark Mode).
* ImageView – To render sprites, backgrounds, and animations.
* AudioClip - For rendering sound effects.
* ProgressBar – To show energy levels, reaction progress, or player health.
* TextField – For user input (if necessary).
* Shapes (Rectangle, Circle, Line) – For simple hitboxes or visual effects.
* Slider – To adjust game speed, volume, or other settings.
* ScrollPane – To view detailed chemistry information or the periodic table.
* ComboBox (Drop-down menu) – To select elements, compounds, or game settings.
* HyperLinK - For ressources
* Timeline/Transition - For a smooth animation
  
## Descripion

Characters. Background images, 
Pages (possibly)

Implementation Components:
Game Logic & Physics
Game Loop (AnimationTimer) – To continuously update and render the game.
Input Handling (KeyEvent, MouseEvent) – To move the player and interact with objects.
Entity System – Classes for Player, Enemies, Platforms, and Collectibles.
Level System - Classes for organizing the properties of each level/world etc
Graphics & Animations
Particle Effects (explosions, energy bursts) – To visualize chemical reactions.
Game State & Persistence
Scene Management – Switching between menus, levels, and game-over screens.
Save/Load System (File I/O or Serialization) – To track player progress.
Score(HUD) – To see the user's progress

### Justify the feasibility in terms of timeline and team tasks assignment. (laila)
The project seems feasible, taking each person’s individual responsibility into consideration. We divide the many tasks into each person’s strong suit.
Timeline:
Today (Week 3): Have project plan in order;
Week 3-5: Create all relevant classes, Basic working program, Story Board, Movement, Basic UI, inputs;
Weeks 5-9: Main implementation: database classes, art/graphics are finished, world/level classes have working methods, have all dialogue written, all graphics/art completed;
Weeks 9-11: Further polishing: working HUD, implementing power ups and how they affect interface;
Weeks 11-12: Program fully working, bug testing.

## Individual part: 
For each team member, describe their individual part and how it would integrate with the whole project with other team members' parts.

* Laila (blue)
Graphics;
Story;
UI + controllers -> dialogues;
Level class -> World class;
Backpack;
Sound effects;

* Sofia (pink)
UI + controllers - main + game over;
Database of chemical reactions/periodic table (period and family);
Classes for managing the database/theory (methods -> neutralization, photosynthesis, periodic table);
Login (save user);
Story, backpack;
Level class -> World class;

* Tab (green)
Movement, UI + controllers - games;
Story;
User manual (Ressources, help, instructions);
Timer;
Level class -> World class;

* Eliza (purple)
Game animation -> input;
Story, Settings;
Game logic -> pause/play/reset level;
lives, score (HUD);
Level class -> World class;
Power-ups class.
