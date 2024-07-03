package pkg8.puzzle;
public class frontier {
      String state;
      state parentState;
    public frontier(String state, state parentState) {
        this.state = state;
        this.parentState = parentState;
    }
    public String getState() {
        return state;
    }
    public state getParentState() {
        return parentState;
    }
}
