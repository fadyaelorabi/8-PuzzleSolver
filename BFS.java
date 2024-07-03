package pkg8.puzzle;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
 class HashQueue<Type> extends LinkedList<Type> {
      Set<String> set = new HashSet<>();
}
public class BFS {
    public static search bfs(state initialState, state goalState) {
        HashQueue<frontier> queue = new HashQueue<>();
        Set<String> explored = new HashSet<>();
        state currentState = initialState;
        search result = new search(); 
        boolean hasNeighbors=false; 
        frontier entry = new frontier(initialState.getStateString(), null);
        queue.add(entry);
            while (!queue.isEmpty()) {
                entry = queue.remove();
                currentState = new state(entry.getState(), entry.getParentState());
                explored.add(currentState.getStateString());
                if (goalState.getStateString().equals(currentState.getStateString())) {
                    System.out.println("Goal state reached!");
                    result.updateExpandedNodes();
                    result.setFound(true);
                    break;
                }
                result.updateExpandedNodes();
                currentState.findNeighbors();
                for (String neighbor :currentState.getNeighbors()) {
                    if (neighbor != null){
                    entry = new frontier(neighbor, currentState);
                   if (!explored.contains(neighbor)) {
                        queue.add(entry);
                        hasNeighbors = true;
                    }
                    }
                }
                if (hasNeighbors)
                   result.updateMaxDepth(currentState.getDepth() + 1);
            }
        result.setSearchAlgorithm("BFS");
        result.findPathToGoal(currentState);
        result.updateDepth();
        result.updateCost();
        return result;
    }
}