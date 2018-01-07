# ChessProject - Java

Pretend that you taking over a software project that has only just started and may be on the wrong track. The project’s long-term goals are to build a fully functional chess game and you have been hired to pick up where others have left off.  You won’t be writing it all at once and the first sprint will have a narrow focus on some basic functionality of the Chessboard and some simple movements of a Pawn.

All simulations take place on a chess board (class name: `ChessBoard`) that is a grid consisting of length X, and height Y – both of which are integers.  Chess pieces can be placed on the board at a given (x,y) coordinate pair with (0, 0) being in the lower left-hand corner of the board, and (7, 7) being in the top right hand corner of the board, as seen in the following illustration:

![alt text](http://www.chessvariants.org/d.chess/startup.gif)

Pieces are either Black or White.  Black pieces typically start at row x=7 and x=6, whereas white pieces typically start at rows x=0 and x=1.  That said, you can set up a board with many initial configurations to replay famous chess games (that last bit might be a paradox).  
Additionally, Pieces can be given two commands: move and capture (we will ignore capture for this exercise).  Each piece has unique movements, but we are going to focus on commands for pawns.  For our limited implementation, Pawns can only more forward one space (toward their opponents side of the board) and can only capture in a forward and diagonal direction as seen in the next illustration.

![alt text](http://www.chessvariants.org/d.chess/pawnmove.gif)

Your task is to get all unit tests found under the Tests folder passing. Since you plan to be on the project long term, think about how you would implement the solution, what other test coverage might be necessary and what you would do to make future features easier to implement.

Good luck, and please reach out to us if you have any questions!

_______________________________________________________________________

# Solution Implemented

The project was initially modified to support JAVA 8. Not because of any requirement but just to have the possibility to start to use some streams to work with collections easilly.

Tests have been built using old style (junit 3 extending TestCase class) and new style (junit 4 annotations @Test). So the first step was to uniform the way how the tests are executed. This because one of them built in a TestCase subclass, doesn't follow the junit3 naming convetion having test in the name of the method. Removing the superclass extension, the test was managed too. The @Test annotation is a  better way to explicity define a test in my opinion, instead have risk to lost a test or execute private metod in a test class, if there isn't any TestSuite configuration.

Another test was wrong assigned. The name of the method was not the same of the assertion (assertFALSE method while the actual assert was assertTrue). This is a typical problem during implementation of a test so it was the first thing that I saw executing the tests and fixed immediately.
Then I started the real implementation. First of all, the check if a coordinate was legal. I used a LongStream to check if a value is between a range. Additionally, I implemented the other checks like if a position was already occupied and if during the set of a board, the piece limit is reached. After, this responsability was divided between the board (the only one that has to know the pieces on it) and the piece itself (pawn knows how many pieces are allowed).

Then, the ADD method of ChessBoard was implemented. If the position is correct and empty, then the chessboard is assigned to the piece and the coordinates are set accordingly.

In the end, I worked on Pawn class in order to check if a movement is valid. First of all, the MoveType currently implemented was Move. So if a Capture is passed, then an exception is thrown. This element can be postponed in a second phase implementation but it is very helpful at this stage to concentrate on the main goal of the implementation and avoid to think on various scenario.

- Additional steps:
-- The Pawn class, contained all responsability about how a pawn can be positioned and moved over the board. The parts regarding the pawn itself, are a few ones and have been put together in the Pawn Class, while the more generic ones, have been put in the superclas ChessPiece (using design pattern Template Method). The new pieces, can be easilly implemented managing just the new max Pieces allowed in a board and the moves allowed. The rest of methods are the same. 
-- The ToString has been modified to be printed properly using MessageFormat instead of String.Format as it was.
-- Some "logs" have been added. It can be improved using SLF4J as logging framework
-- The Size was not properly configured. It was using the index instead of actual size to initialize the bidimensional array.
-- The Count of pieces, was not reffering the colors. So the max number of pieces depends on color as well.
-- cover test to check that the movement is not oblique
-- The color in the Add method of chessboard was redundant. Has been eliminated because it is an information of the piece.
-- Implement the movement of two for Pawn if it is in the start position.
-- Implement Fluent api in order to initialize and move pieces over the board

- Next steps
First of all, there are a couple of problems found in the current design. The Chess Board contains all method to check all movements and position in a board as private method. This make the ChessBoard class very heavy and hard to tested granulary. For this reason, all of these check method can be splitted in multiple components, whoich responsability is just to validate all moves. The validation of a move is the most important module of the project. So it has to be more modular and fully covered by tests.

A new fluent API has been implemented to guide a developer or the new user of the API to instantiace a new board, disposing pieces and moving them over the board. It contains also exception management that avoid to check the invalid position of a piece every time if a movement or a dispose cannot be performed due to invalid coordinate. This can be implemented further allowing only the possible movement for each piece. For example, the pawn will have just the movement of one or of two and the captures (diagonal or "en passant"). 
Afterwards, it can be implemented the Promotion move only for pawns or the castling for king and tower. This will break the Inheritance created with the template method (only pawns must have the promotion method). Due to the fact that I hate inheritance, I will be happy to found another pattern (generics and interfaces) to manage the differences between pieces.

Hopefully that I have covered all things of this exercise. 
