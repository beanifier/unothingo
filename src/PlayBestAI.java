public class PlayBestAI extends PlayerAI {
    public PlayBestAI() {
        super();
    }

    public int cardValue(Card card) {
        int value = 0;
        switch (card.type) {
            case WILD_DRAW4:
                value = 500;
                break;
            case WILD:
                value = 450;
                break;
            case SKIP:
                value = 350;
                break;
            case REVERSE:
                value = 300;
                break;
            case DRAW2:
                value = 250;
                break;
            default:
                value = 100;
                break;
        }
        Card topCard = player.game.discardPile.getTopCard();
        if (card.color == topCard.color) {
            value -= 20;
        }
        return value;
    }

    public void sort() {
        // Bubble sort
        for (int i = 0; i < player.hand.size() - 1; i++) {
            for (int j = 0; j < player.hand.size() - i - 1; j++) {
                if (cardValue(player.hand.getCard(j)) > cardValue(player.hand.getCard(j + 1))) {
                    Card temp = player.hand.getCard(j);
                    player.hand.cards.set(j, player.hand.getCard(j + 1));
                    player.hand.cards.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public GameMove playTurn(GameMove lastMove) throws Exception {
        sort();
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
            chooseBestWild(card);
        }
        return move;
    }
}