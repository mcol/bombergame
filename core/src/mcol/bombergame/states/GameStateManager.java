package mcol.bombergame.states;

import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {

    /** Stack of game states. */
    private final Stack<State> states;

    /** Constructor. */
    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void set(State state) {
        pop();
        states.push(state);
    }

    private void pop() {
        states.pop().dispose();
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
