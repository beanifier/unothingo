public abstract class PlayerAI {
    public Player player;
    
    public PlayerAI() {}

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void turnEvent(GameMove move) {
        return;
    }

    public abstract GameMove playTurn(GameMove lastmove) throws Exception;

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
