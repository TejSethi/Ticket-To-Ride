package main.java.com.tickettoride;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardDeck<T> implements Iterable<T> {

    private List<T> cardList;
    private List<T> discardCardList;

    private boolean applyReshuffle;

    public CardDeck(boolean applyShuffling) {
        this.cardList = new ArrayList<>();
        discardCardList = new ArrayList<>();
        this.applyReshuffle = applyShuffling;
    }

    public void addCard(T card) {
        this.cardList.add(card);
    }

    public T removeCard() {
        return this.cardList.remove(this.cardList.size() - 1);
    }

    public void addToDiscardedList(T card) {
        this.discardCardList.add(card);
    }

    public List<T> getCardList() {
        return this.cardList;
    }

    @Override
    public Iterator<T> iterator() {
        return new CardIterator<>(cardList, discardCardList, applyReshuffle);
    }




}
