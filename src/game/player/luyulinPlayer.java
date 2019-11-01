package game.player;

import game.Manager;
import game.Player;
import game.Poker;

import java.util.Collections;
import java.util.List;

public class luyulinPlayer implements Player {

    @Override
    public String getName() {
        return "卢昱霖";
    }

    @Override
    public String getStuNum() {
        return "2019210098";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (isSameColor(pokers) )
            return (int) ((2.5 - round/5) * moneyYouNeedToPayLeast) < 2.5 * moneyOnDesk ? (int) ((2.5 - round/5) * moneyYouNeedToPayLeast)   :
                    ((2 - round/5) * moneyYouNeedToPayLeast)    ;
        if ( (isSameColorStraight(pokers) || isSamePoint(pokers))  )
            return  3*moneyYouNeedToPayLeast;
        if (isPair(pokers))
            return  round <= 4 ? (int) ((2.8-lastPerson/5))*moneyYouNeedToPayLeast : (int)1.2*moneyYouNeedToPayLeast;

        if (isStraight(pokers))
            return round <= 4 ?   ((3-lastPerson/5))*moneyYouNeedToPayLeast : moneyYouNeedToPayLeast;
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 12)
                return lastPerson <= 4 ? moneyYouNeedToPayLeast : (int) ((1.6-lastPerson/10))*moneyYouNeedToPayLeast;
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}