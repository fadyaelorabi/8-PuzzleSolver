package pkg8.puzzle;
public abstract class Heuristic {
     abstract int distance(String state);
}
class Manhattan extends Heuristic{
public  int distance(String state) {
        state current_state;             
        Integer[][] puzzleBoard;  
        int goalX;
        int goalY;          
        int distance=0;
        current_state = new state(state, null);
        puzzleBoard = current_state.getPuzzleBoard();
        for (int row = 0 ; row < 3 ; row++) {
            for (int column = 0 ; column < 3 ; column++) {
                goalX = puzzleBoard[row][column] /3;
                goalY = puzzleBoard[row][column] %3;
                distance += Math.abs(goalX - row) + Math.abs(goalY - column);
            }
        }
        return distance;
    }
}
class Euclidean extends Heuristic{
public int distance(String state){
        state current_state;         
        Integer[][] puzzleBoard; 
        int goalX; 
        int goalY;
        int distance;              
        current_state = new state(state, null);
        puzzleBoard = current_state.getPuzzleBoard();
        distance = 0;
        for (int x = 0 ; x < 3; x++) {
            for (int y = 0 ; y < 3 ; y++) {
                goalX = puzzleBoard[x][y] / 3;
                goalY = puzzleBoard[x][y] % 3;
                distance += Math.sqrt(Math.pow((goalX - x), 2) + Math.pow((goalY - y), 2));
            }
        }
        return distance;
    }
}