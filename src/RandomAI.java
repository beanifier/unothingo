public class RandomAI extends PlayerAI {
    public RandomAI() {
        super();
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

    @Override
    public GameMove playTurn(GameMove lastMove) throws Exception {
        player.hand.shuffle();
        GameMove move = new GameMove();
        int index = -1;
        for (int i = 0; i < player.hand.size(); i++) {
            if (isCardIdLegal(i) && (player.game.drawCount > 0 ? Card.isDraw(card(i)) : true)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            move = new GameMove(player, true);
        } else {
            Card card = player.hand.getCard(index);
            move = new GameMove(player, index);
            chooseRandomWild(card);
        }
        return move;
    }
}