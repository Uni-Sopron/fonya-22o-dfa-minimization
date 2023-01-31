package hu.wtomee.objects;

public class Transition {

    String fromState;

    String symbol;

    String nextState;

    public Transition() {
    }

    public Transition(String fromState, String symbol, String nextState) {
        this.fromState = fromState;
        this.symbol = symbol;
        this.nextState = nextState;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "fromState='" + fromState + '\'' +
                ", symbol='" + symbol + '\'' +
                ", nextState='" + nextState + '\'' +
                '}';
    }
}
