package pkg8.puzzle;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
class minumum_entry implements Comparable<minumum_entry> {
     String state;
     Integer cost;
     state parentState;
     public minumum_entry(String state,state parentState ,Integer cost) {
        this.state = state;
        this.cost = cost;
        this.parentState = parentState;
    }
    @Override
    public int compareTo(minumum_entry other) {
        return this.getCost().compareTo(other.getCost());
    }
    public String getState() {
        return state;
    }
    public Integer getCost() {
        return cost;
    }
    public state getParentState() {
        return parentState;
    }
}
 class HashPriorityQueue<Type> extends PriorityQueue<Type> {
     Set<String> set = new HashSet<>();
}
public class Astar {
    public static search Astar(state initialState, state goalState, Heuristic h) {
        HashPriorityQueue<minumum_entry> pq = new HashPriorityQueue<>();
        Set<String> explored = new HashSet<>(); 
        state currentState = initialState;                                                     
        search result = new search();  
        boolean hasNeighbors=false;
        int cost = h.distance(initialState.getStateString());
        minumum_entry entry = new minumum_entry(initialState.getStateString(),null, cost);
        pq.add(entry);
            while (!pq.isEmpty()) {
                entry = pq.remove();
                currentState = new state(entry.getState(), entry.getParentState());
                explored.add(currentState.getStateString());
                if (goalState.getStateString().equals(currentState.getStateString())) {
                    result.updateExpandedNodes();
                    result.setFound(true);
                    break;
                }
                result.updateExpandedNodes();
                currentState.findNeighbors();
                for (String neighbor :currentState.getNeighbors()) {
                    if (neighbor != null){
                    cost = h.distance(neighbor);
                    entry = new minumum_entry(neighbor,currentState, cost);
                    if (!pq.contains(entry)&&!explored.contains(neighbor)) {
                        pq.add(entry);
                        hasNeighbors = true;
                    } else if (pq.contains(entry)) {
                        pq.add(entry);
                        hasNeighbors = true;
                    }
                    }
                }
                if (hasNeighbors)
                    result.updateMaxDepth(currentState.getDepth() + 1);
            }
             result.setSearchAlgorithm("Astar search (" + h.getClass().getSimpleName() + ")");
         result.findPathToGoal(currentState);
        result.updateDepth();
        result.updateCost();
        return result;
    }
}
