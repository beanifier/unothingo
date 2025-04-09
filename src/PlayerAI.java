public abstract class PlayerAI {
    public Player player;
    
    public PlayerAI(Player playerObject) {
        player = playerObject;
    }

    public void turnEvent(GameMove move) {
        return;
    }

    public abstract GameMove playTurn(GameMove move);

    public void drawCardEvent(GameMove move) {
        return;
    }
    
    public void skipEvent(GameMove move) {
        return;
    }

    public void reverseEvent(GameMove move) {
        return;
    }
}
