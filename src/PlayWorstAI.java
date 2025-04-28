public class PlayWorstAI extends PlayerAI {
    public PlayWorstAI() {
        super();
    }

    public int cardValue(Card card) {
        int value = 0;
        switch (card.type) {
            case WILD_DRAW4:
                value = 500;
                break;
            case WILD:
                value = 400;
                break;
            case SKIP:
                value = 300;
                break;
        }
        return value;
    }

    public void sort() {
        // Bubble sort
        
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
            chooseRandomWild(card);
        }
        return move;
    }
}