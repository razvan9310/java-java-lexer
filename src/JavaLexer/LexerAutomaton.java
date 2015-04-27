package JavaLexer;

import java.util.HashMap;

/**
 * Deterministic finite automaton the lexer is conceptually designed upon.
 */
public class LexerAutomaton {
  /**
   * Hash-able state-symbol pair class used in defining the automaton's transitions.
   */
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

  /**
   * Transitions map, i.e. <current state, current symbol> -> new state
   */
  private HashMap<StateSymbolPair, Integer> mTransitionsMap;
  /**
   * Mapping of final states to corresponding tokens type. See {@link Token#getType()} for a list of
   * possible types.
   */
  private HashMap<Integer, Integer> mFinalStatesToTokenTypesMap;

  /**
   * Creates a new LexerAutomaton object.
   * @param transitionsMap Transitions map for this automaton to use. Keys represent pairs of type
   *     <current state, current symbol>. Values represent states corresponding to transactions from
   *     the current state, via the current symbol.
   * @param finalStatesToTokenTypesMap Map containing pairs of type <final state, corresponding
   *     token's type>. See {@link Token#getType()} for a list of possible types.
   */
  LexerAutomaton(HashMap<StateSymbolPair, Integer> transitionsMap, HashMap<Integer, Integer>
      finalStatesToTokenTypesMap) {
    mTransitionsMap = transitionsMap;
    mFinalStatesToTokenTypesMap = finalStatesToTokenTypesMap;
  }

  /**
   * Performs an automaton transition.
   * @param state Current state.
   * @param symbol Current symbol.
   * @return Integer representing the new state that <state, symbol> transitions into.
   */
  public Integer transition(int state, char symbol) {
    return mTransitionsMap.get(new StateSymbolPair(state, symbol));
  }

  /**
   * Returns a given state's token type.
   * @param state {@code int} representing some state.
   * @return null if the given state is not final in the automaton, corresponding token type
   *     otherwise. See {@link Token#getType()} for a list of possible types.
   */
  public Integer tokenTypeForFinalState(int state) {
    return mFinalStatesToTokenTypesMap.get(state);
  }
}
