package solitaire;

class DiscardPile extends SelectClass {

	DiscardPile(final int x, final int y) {
		super(x, y);
	}

	@Override
	public void addCard(final Card aCard) { //���������� ������ ��������
		if (!aCard.isFaceUp()) {
			aCard.flip();
		}
		super.addCard(aCard);
	}
}