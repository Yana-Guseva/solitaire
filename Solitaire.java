package solitaire;

/*
 Simple Solitaire Card Game in Java
 Written by Tim Budd, Oregon State University, 1996
 */

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;

public class Solitaire extends Applet {
	static CardPile allPiles[];
	static DeckPile deckPile;
	static DiscardPile discardPile;
	static SuitPile suitPile[];
	static TablePile tableau[];
	Card firstCard = null;
	int firstIndex;

	@Override
	public void init() {
		// first allocate the arrays
		allPiles = new CardPile[13];
		suitPile = new SuitPile[4];
		tableau = new TablePile[7];
		// then fill them in
		allPiles[0] = deckPile = new DeckPile(335, 5);
		allPiles[1] = discardPile = new DiscardPile(268, 5);
		for (int i = 0; i < 4; i++) {
			allPiles[2 + i] = suitPile[i] = new SuitPile(15 + 60 * i, 5);
		}
		for (int i = 0; i < 7; i++) {
			allPiles[6 + i] = tableau[i] = new TablePile(5 + 55 * i, 80, i + 1);
		}
	}

	@Override
	public boolean mouseDown(final Event evt, final int x, final int y) {
		for (int i = 0; i < 13; i++) {
			if (allPiles[i].includes(x, y)) {
				// allPiles[i].select(x, y);
				Card secondCard = allPiles[i].select(x, y);
				// не первый проход
				if (firstCard != null) {
					if (firstCard == secondCard) {
						firstCard.setSelect(firstCard.isSelect());
					} else {
						firstCard.setSelect(false);
					}
					if (allPiles[i].canTake(firstCard)) {
						allPiles[firstIndex].removeCard(firstCard);
						allPiles[i].addCard(firstCard);
						if (secondCard != null) {
							secondCard.setSelect(false);
						}
					}
				}
				firstIndex = i;
				firstCard = secondCard;
				repaint();
				return true;
			} else {
				Card c = allPiles[i].getCard(x, y);
				if (c != null) {
					if (firstCard == c) {
						c.setSelect(true);
					} else {
						c.setSelect(false);
					}
					firstCard = c;
					firstIndex = i;
					
					repaint();
					return true;
				}

			}
		}
		return true;
	}

	@Override
	public void paint(final Graphics g) {
		for (int i = 0; i < 13; i++) {
			allPiles[i].display(g);
		}
	}
}