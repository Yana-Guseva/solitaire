package solitaire;



public class SelectClass extends CardPile{

	SelectClass(final int xl, final int yl) {
		super(xl, yl);
	}

	@Override
	public Card select(final int tx, final int ty) {
		
		if (empty()) {
			return null;
		}
		
		// if face down, then flip
		Card topCard = top();
		if (!topCard.isFaceUp()) {
			topCard.flip();
			return null;
		}
		
		topCard.setSelect(!topCard.isSelect());
		
		// else see if any suit pile can take card
		//topCard = pop();
//		for (int i = 0; i < 4; i++) {
//			if (Solitaire.suitPile[i].canTake(topCard)) {
//				Solitaire.suitPile[i].addCard(topCard);
//				return;
//			}
//		}
//		// else see if any other table pile can take card
//		for (int i = 0; i < 7; i++) {
//			if (Solitaire.tableau[i].canTake(topCard)) {
//				Solitaire.tableau[i].addCard(topCard);
//				return;
//			}
//		}
		// else put it back on our pile
//		addCard(topCard);

		return topCard;
	}
	
	
	
}
