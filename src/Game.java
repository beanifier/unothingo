public class Game {
    public CardStack drawPile;
    public CardStack discardPile;
    private Player[] players;
    public int playerCount;
    public int currentPlayerIndex;
    public boolean isForward;
    public GameMove lastMove;
    public int drawCount;
    public Game(Player[] playerList) {
        players = playerList;
        playerCount = playerList.length;
        currentPlayerIndex = 0;
        for (int i = 0; i < playerCount; i++) {
            players[i].setGame(this);
        }
        isForward = true;
        drawPile = new CardStack();
        discardPile = new CardStack();
        for (CardColor color : CardColor.values()) {
            if (color != CardColor.WILD) {
                for (CardType type : CardType.values()) {
                    if (type != CardType.WILD && type != CardType.WILD_DRAW4) {
                        drawPile.addCard(new Card(color, type));
                        drawPile.addCard(new Card(color, type));
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            drawPile.addCard(new Card(CardColor.WILD, CardType.WILD));
            drawPile.addCard(new Card(CardColor.WILD, CardType.WILD_DRAW4));
        }
        drawPile.shuffle();
        discardPile.addCard(drawPile.drawCard());
        lastMove = new GameMove();
        lastMove.play(players[0]);
    }
    public Player[] getPlayers() {
        return players;
    }
    public boolean isMoveLegal(GameMove move) throws Exception {
        if (move == null) {
            throw new Exception("Move is null");
        }
        if (lastMove.nextPlayer != move.player) {
            throw new Exception("Move is not for the current player");
        }
        Card topcard = discardPile.getTopCard();
        if (topcard.type == CardType.DRAW2  || topcard.type == CardType.WILD_DRAW4) {
            if (move.playCard.type == CardType.DRAW2  || move.playCard.type == CardType.WILD_DRAW4) {
                return true;
            } else {
                if (move.isDraw) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (Card.canGoOnto(discardPile.getTopCard(), move.playCard)) {
            return true;
        }
        return false;
    }
}
