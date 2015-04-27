package JavaLexer;

import java.util.ArrayList;

/**
 * Created by razvan on 4/26/15.
 */
public class Lexer {
  public static class CurrentTokenNextPosition {
    public Token mCurrentToken;
    public int mNextPosition;

    public CurrentTokenNextPosition(Token currentToken, int nextPosition) {
      mCurrentToken = currentToken;
      mNextPosition = nextPosition;
    }
  }

  private static int START_STATE = 0;

  private Integer mCurrentState = 0;
  private byte[] mInput;
  private LexerAutomaton mLexerAutomaton;
  private ArrayList<String> mTokenValues = new ArrayList<String>();

  public String getTokenValue(int index) {
    if (index < 0 || index >= mTokenValues.size()) {
      return null;
    }
    return mTokenValues.get(index);
  }

  public void setLexerAutomaton(LexerAutomaton lexerAutomaton) {
    mLexerAutomaton = lexerAutomaton;
  }

  public void setInput(byte[] input) {
    mInput = input;
  }

  public CurrentTokenNextPosition getTokenAndNextPosition(int pos) {
    String tokenValue = "";
    int offset = 0;
    Integer lastFinalState = null;
    String lastFinalTokenValue = "";
    int lastFinalOffset = 0;
    char currentCharacter;

    while (pos + offset < mInput.length && (mCurrentState = mLexerAutomaton.transition(
        mCurrentState, currentCharacter = ((char) (mInput[pos + offset] & 0xFF)))) != null) {
      if (mCurrentState == START_STATE) {
        tokenValue = "";
      } else {
        tokenValue += currentCharacter;
        if (mLexerAutomaton.finalStateType(mCurrentState) != null) {
          lastFinalState = mCurrentState;
          lastFinalTokenValue = tokenValue;
          lastFinalOffset = offset + 1;
        }
      }
      ++offset;
    }

    if (lastFinalState == null) {
      return new CurrentTokenNextPosition(null, pos + offset);
    } else {
      mCurrentState = START_STATE;
      if (!mTokenValues.contains(lastFinalTokenValue)) {
        mTokenValues.add(lastFinalTokenValue);
      }
      return new CurrentTokenNextPosition(new Token(mLexerAutomaton.finalStateType(lastFinalState),
              mTokenValues.indexOf(lastFinalTokenValue)), pos + lastFinalOffset);
    }
  }
}
