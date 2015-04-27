package JavaLexer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by razvan on 4/26/15.
 */
public class Lexer {
  private static int START_STATE = 0;

  private int mCurrentPosition = 0;
  private Integer mCurrentState = START_STATE;
  private byte[] mInput;
  private LexerAutomaton mLexerAutomaton;
  private ArrayList<String> mTokenValues;
  private HashMap<Integer, ArrayList<Integer>> mTokenPositionsMap;

  public String getTokenValue(int index) {
    if (index < 0 || index >= mTokenValues.size()) {
      return null;
    }
    return mTokenValues.get(index);
  }

  public int getCurrentPosition() {
    return mCurrentPosition;
  }

  public void setLexerAutomaton(LexerAutomaton lexerAutomaton) {
    mLexerAutomaton = lexerAutomaton;
    mCurrentState = START_STATE;
    mTokenValues = new ArrayList<String>();
    mTokenPositionsMap = new HashMap<Integer, ArrayList<Integer>>();
  }

  public void setInput(byte[] input) {
    mInput = input;
    mCurrentPosition = 0;
  }

  public Token getToken() {
    String tokenValue = "";
    int offset = 0;
    int lastFinalOffset = 0;
    Integer lastFinalState = null;
    String lastFinalTokenValue = "";
    char currentCharacter;

    while (mCurrentPosition + offset < mInput.length && (mCurrentState = mLexerAutomaton.transition(
            mCurrentState, currentCharacter = ((char) (mInput[mCurrentPosition + offset] & 0xFF))))
            != null) {
      tokenValue += currentCharacter;
      if (mLexerAutomaton.tokenTypeForFinalState(mCurrentState) != null) {
        if (mCurrentState == START_STATE) {
          tokenValue = "";
        }
        lastFinalOffset = offset + 1;
        lastFinalState = mCurrentState;
        lastFinalTokenValue = tokenValue;
      }
      ++offset;
    }

    if (lastFinalState == null) {
      return null;
    } else {
      mCurrentState = START_STATE;
      if (!mTokenValues.contains(lastFinalTokenValue)) {
        mTokenValues.add(lastFinalTokenValue);
      }
      int positionInTokensList = mTokenValues.indexOf(lastFinalTokenValue);
      ArrayList tokenPositions = mTokenPositionsMap.get(positionInTokensList);
      if (tokenPositions == null) {
        tokenPositions = new ArrayList<Integer>();
      }
      tokenPositions.add(mCurrentPosition);
      mTokenPositionsMap.put(positionInTokensList, tokenPositions);

      mCurrentPosition += lastFinalOffset;

      return new Token(mLexerAutomaton.tokenTypeForFinalState(lastFinalState), positionInTokensList);
    }
  }

  public String[] getFormattedTableDescription() {
    ArrayList<String> tableDescription = new ArrayList<String>();
    for (int i = 0; i < mTokenValues.size(); ++i) {
      String row = String.format("Token no: %d | value: %s | positions:", i, mTokenValues.get(i));
      ArrayList<Integer> positions = mTokenPositionsMap.get(i);
      for (Integer position : positions) {
        row += " " + String.valueOf(position);
      }
      tableDescription.add(row);
    }
    return tableDescription.toArray(new String[tableDescription.size()]);
  }
}
