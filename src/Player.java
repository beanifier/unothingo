public class Player {
    public CardStack hand;
    public Game game;
    public PlayerAI playerAI;
    public Player(PlayerAI ai) throws Exception {
        hand = new CardStack();
        playerAI = ai;
    }
    public void addCard(Card card) {
        hand.addCard(card);
    }
    public void setGame(Game gameObject) {
        game = gameObject;
    }
    private void assertGame() throws Exception {
        if (game == null) {
            throw new Exception("Game not set");
        }
    }
    public GameMove playCard(GameMove move) throws Exception {
        assertGame();
        return playerAI.playTurn(move);
    }
    public void drawEvent(GameMove move) throws Exception {
        assertGame();
        playerAI.drawCardEvent(move);
    }
    public void skipEvent(GameMove move) throws Exception {
        assertGame();
        playerAI.skipEvent(move);
    }
    public void reverseEvent(GameMove move) throws Exception {
        assertGame();
        playerAI.reverseEvent(move);
    }
    public void turnEvent(GameMove move) throws Exception {
        assertGame();
        playerAI.turnEvent(move);
    }
}
