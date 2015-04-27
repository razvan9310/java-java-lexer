package JavaLexer;

import java.util.HashMap;
import java.util.List;

/**
 * Created by razvan on 4/26/15.
 */
public class LexerAutomaton {
  public static class StateSymbolPair {

    public int mState;
    public char mSymbol;

    public StateSymbolPair(int state, char symbol) {
      mState = state;
      mSymbol = symbol;
    }

    @Override
    public int hashCode() {
      String hashString = String.valueOf(100 * mState + (int) mSymbol);
      return hashString.hashCode();
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof StateSymbolPair)) {
        return false;
      }
      StateSymbolPair oPair = (StateSymbolPair) o;
      return this.mState == oPair.mState && this.mSymbol == oPair.mSymbol;
    }
  }

  private HashMap<StateSymbolPair, Integer> mTransitionsMap;
  private HashMap<Integer, Integer> mFinalStatesToTokenTypesMap;
  private List<Integer> mWhitespaceCommentStates;

  LexerAutomaton(HashMap<StateSymbolPair, Integer> transitionsMap, HashMap<Integer, Integer>
      finalStatesToTokenTypesMap) {
    mTransitionsMap = transitionsMap;
    mFinalStatesToTokenTypesMap = finalStatesToTokenTypesMap;
  }

  public Integer transition(int state, char symbol) {
    return mTransitionsMap.get(new StateSymbolPair(state, symbol));
  }

  public Integer finalStateType(int state) {
    return mFinalStatesToTokenTypesMap.get(state);
  }
}
