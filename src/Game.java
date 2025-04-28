public class Game {
    public CardStack drawPile;
    public CardStack discardPile;
    private Player[] players;
    public int playerCount;
    public int currentPlayerIndex;
    public boolean isForward;
    public GameMove lastMove;
    public int drawCount;
    public boolean isGameOver;
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
        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < 7; j++) {
                players[i].addCard(drawPile.drawCard());
            }
        }
        lastMove = new GameMove();
        lastMove.play(players[0]);
        isGameOver = false;
    }
    public Player[] getPlayers() {
        return players;
    }
    public void checkDrawPile() {
        if (drawPile.size() == 0) {
            Card topCard = discardPile.getTopCard();
            discardPile.shuffle();
            drawPile = discardPile;
            discardPile = new CardStack();
            discardPile.addCard(topCard);
        }
    }
    public boolean isMoveLegal(GameMove move) throws Exception {
        if (move == null) {
            throw new Exception("Move is null");
        }
        if (lastMove == null || lastMove.nextPlayer != move.player) {
            throw new Exception("Move is not for the current player");
        }
        if (move.isDraw) {
            return true;
        }
        if (drawCount > 0) {
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
    public void playMove() throws Exception {
        GameMove move = players[currentPlayerIndex].playCard(lastMove);
        int nextPlayerIndex = 0;
        boolean isSkip = false;
        if (isGameOver) {
            throw new Exception("Game is over");
        }
        if (isMoveLegal(move)) {
            if (drawCount > 0) {
                if (move.isDraw) {
                    for (int i = 0; i <= drawCount; i++) {
                        Card card = drawPile.drawCard();
                        players[currentPlayerIndex].addCard(card);
                        checkDrawPile();
                    }
                    drawCount = 0;
                } else {
                    if (move.playCard.type == CardType.DRAW2) {
                        drawCount += 2;
                    } else if (move.playCard.type == CardType.WILD_DRAW4) {
                        drawCount += 4;
                    } else {
                        throw new Exception("Player must draw cards or play a draw card");
                    }
                }
            } else {
                if (move.isDraw) {
                    Card card = drawPile.drawCard();
                    players[currentPlayerIndex].addCard(card);
                } else {
                    drawCount = 0;
                    if (move.playCard.type == CardType.REVERSE) {
                        isForward = !isForward;
                    }
                    if (move.playCard.type == CardType.SKIP) {
                        isSkip = true;
                    }
                    if (move.playCard.type == CardType.WILD || move.playCard.type == CardType.WILD_DRAW4) {
                        CardColor color = move.playCard.color;
                        if (color == CardColor.WILD) {
                            throw new Exception("Wild card must have a color other than wild");
                        }
                    }
                    if (move.playCard.type == CardType.DRAW2) {
                        drawCount += 2;
                    }
                    if (move.playCard.type == CardType.WILD_DRAW4) {
                        drawCount += 4;
                    }
                }
            }
            nextPlayerIndex = isForward ? (currentPlayerIndex + (isSkip ? 2 : 1)) % playerCount : (currentPlayerIndex - (isSkip ? 2 : 1) + playerCount) % playerCount;
            move.play(players[nextPlayerIndex]);
        } else {
            throw new Exception("Move is not legal" + move);
        }
        for (int i = 0; i < players.length; i++) {
            if (players[i].hand.size() == 0) {
                System.out.println("Player " + (i + 1) + " wins!");
                isGameOver = true;
                return;
            }
        }
        if (move.didPlay) {
            if (move.playCard != null) {
                discardPile.addCard(players[currentPlayerIndex].hand.takeCard(move.playCardIndex));
            } 
        }
        lastMove = move;
        currentPlayerIndex = nextPlayerIndex;
        for (int i = 0; i < players.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Player ").append(i + 1).append(" hand: ");
            for (int j = 0; j < players[i].hand.size(); j++) {
                sb.append(players[i].hand.getCard(j));
            }
            System.out.println(sb.toString());
        }
        System.out.println("Discard pile: " + discardPile.getTopCard());
        System.out.println("Next Player: " + (currentPlayerIndex+1));
        System.out.println("Draw Count: " + drawCount);
        System.out.println("-");
        checkDrawPile();
    }
}
