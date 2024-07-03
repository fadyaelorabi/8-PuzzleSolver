package pkg8.puzzle;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
 class HashStack<Type> extends Stack<Type> {
}
public class DFS {
        public static search dfs(state initialState, state goalState) {
        HashStack<frontier> st = new HashStack<>();
        Set<String> explored = new HashSet<>();
        state currentState = initialState;    
        search result = new search();
        boolean hasNeighbors=false;
        frontier entry = new frontier(initialState.getStateString(), null);
        st.push(entry);
            while (!st.isEmpty()) {
                entry = st.pop();
                currentState = new state(entry.getState(), entry.getParentState());
                explored.add(currentState.getStateString());
                if (goalState.getStateString().equals(currentState.getStateString())) {
                    result.updateExpandedNodes();
                    result.setFound(true);
                    break;
                }
                result.updateExpandedNodes();
                currentState.findNeighbors();
                for (int i = 3; i >= 0; i--) {
                    String neighbor = currentState.getNeighbors().get(i);
                    if (neighbor != null){
                    entry = new frontier(neighbor, currentState);
                    if (!st.contains(entry) && !explored.contains(neighbor)) {
                        st.push(entry);
                        hasNeighbors = true;
                    }
                    }
                }
               if (hasNeighbors)
                    result.updateMaxDepth(currentState.getDepth() + 1);
            }
             result.setSearchAlgorithm("DFS");
         result.findPathToGoal(currentState);
        result.updateDepth();
        result.updateCost();
        return result;
    }
}
