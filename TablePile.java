package solitaire;

import java.awt.Color;
import java.awt.Graphics;

class TablePile extends SelectClass {

	TablePile(final int x, final int y, final int c) {
		// initialize the parent class
		super(x, y);
		// then initialize our pile of cards
		for (int i = 0; i < c; i++) {
			addCard(Solitaire.deckPile.pop());
		}
		// flip topmost card face up
		top().flip();
	}

	@Override
	public boolean canTake(final Card aCard) {
		if (empty()) {
			return isKing(aCard);
		}
		Card topCard = top();
		return (aCard.color() != topCard.color())
				&& (aCard.getRank() == topCard.getRank() - 1);
	}
	
	private boolean isKing(final Card aCard) {
		return aCard.getRank() == 12;
	}

	@Override
	public void display(final Graphics g) {
		stackDisplay(g, top());
	}

	@Override
	public boolean includes(final int tx, final int ty) {
		// don't test bottom of card
		//return x <= tx && tx <= x + Card.width && y <= ty;

		int i = 0;
		Card card = top();
		while(card != null) {
			i++;
			card = card.link;
		}

		return x <= tx && tx <= x + Card.width && ty >= (y + (i-1)*Card.height/2) && ty <= (y + (i-1)*Card.height/2+Card.height);
		
	}
	
	@Override
	public Card getCard(final int tx, final int ty){
		if(!(x <= tx && tx <= x + Card.width)){
			return null;
		} else {
			Card card = top();
			int i = 0;
			while(card != null) {
				i++;			
				if( ty>=(y+i*35) && ty<=((y+i+1)*35) ){
					
					return card;
				}
				card = card.link;
			}
			return null;
			
		}
			
	}

	private int stackDisplay(final Graphics g, final Card aCard) {
		int localy;
		if (aCard == null) {
			return y;
		}
		localy = stackDisplay(g, aCard.link);
		if(aCard.isSelect()){
			aCard.draw(g, x, localy, Color.orange);
		} else {
			aCard.draw(g, x, localy);
		}
		
		return localy + 35;
	}
}