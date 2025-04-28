public class RandomAI extends PlayerAI {
    public RandomAI() {
        super();
    }

    @Override
    public GameMove playTurn(GameMove lastMove) throws Exception {
        player.hand.shuffle();
        GameMove move = new GameMove();
        if (player.game.drawCount > 0) {
            int index = -1;
            for (int i = 0; i < player.hand.size(); i++) {
                if (player.game.isMoveLegal(new GameMove(player, i)) && player.hand.getCard(i).type == CardType.DRAW2 || player.hand.getCard(i).type == CardType.WILD_DRAW4) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                move = new GameMove(player, true);
            } else {
                Card card = player.hand.getCard(index);
                move = new GameMove(player, index);
                if (card.type == CardType.WILD || card.type == CardType.WILD_DRAW4) {
                    card.color = CardColor.values()[(int) (Math.random() * 4)];
                }
            }
        } else {
            int index = -1;
            for (int i = 0; i < player.hand.size(); i++) {
                if (player.game.isMoveLegal(new GameMove(player, i))) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                move = new GameMove(player, true);
            } else {
                Card card = player.hand.getCard(index);
                move = new GameMove(player, index);
                if (card.type == CardType.WILD || card.type == CardType.WILD_DRAW4) {
                    card.color = CardColor.values()[(int) (Math.random() * 4)];
                }
            }
        }
        return move;
    }
}
