package main.java.com.tickettoride;

import java.util.Iterator;
import java.util.List;

import static java.util.Collections.shuffle;

public class CardIterator<T> implements  Iterator<T> {
    private final List<T> cardList;
    private final List<T> discardList;

    private final boolean applyReshuffle;


    public CardIterator(List<T> cardList, List<T> discardList, boolean applyReshuffle){
        this.cardList = cardList;
        this.discardList = discardList;
        this.applyReshuffle = applyReshuffle;
    }

    @Override
    public boolean hasNext() {
        return cardList.size() > 0;
    }

    @Override
    public T next() {
        T card =  cardList.iterator().next();
        if (this.applyReshuffle) {
            if(cardList.size() == 0){
                reShuffleDeck();
            }
        }
        return card;
    }

    public void remove()
    {
        cardList.iterator().remove();
    }


    public void reShuffleDeck() {
        cardList.addAll(discardList);
        discardList.clear();
        shuffle(cardList);
    }
}
