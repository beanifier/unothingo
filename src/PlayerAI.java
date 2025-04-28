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

    public boolean isCardIdLegal(int cardId) throws Exception {
        return player.game.isMoveLegal(new GameMove(player, cardId));
    }

    public Card card(int id) {
        return player.hand.getCard(id);
    }

    public void chooseRandomWild(Card card) {
        if (Card.isWild(card)) {
            card.color = CardColor.values()[(int) (Math.random() * 4)];
        }
    }

    public void chooseBestWild(Card card) {
        if (Card.isWild(card)) {
            CardColor bestColor = CardColor.RED;
            int maxCount = 0;
            for (CardColor color : CardColor.values()) {
                int count = 0;
                for (int i = 0; i < player.hand.size(); i++) {
                    if (player.hand.getCard(i).color == color) {
                        count++;
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                    bestColor = color;
                }
            }
            if (bestColor == CardColor.WILD) {
                bestColor = CardColor.RED;
            }
            card.color = bestColor;
        }
    }
}
