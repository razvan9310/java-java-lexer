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
    int lastValidState = 0;
    int offset = 0;
    char currentCharacter;
    while (pos + offset < mInput.length && (mCurrentState = mLexerAutomaton.transition(
        mCurrentState, currentCharacter = ((char) (mInput[pos + offset] & 0xFF)))) != null) {
      lastValidState = mCurrentState;
//      if (!mLexerAutomaton.isWhitespaceCommentState(mCurrentState)) {
//        tokenValue = tokenValue + currentCharacter;
//      }
      if (mCurrentState == START_STATE) {
        tokenValue = "";
      } else {
        tokenValue += currentCharacter;
      }
      ++offset;
    }
    Integer finalStateType = mLexerAutomaton.finalStateType(lastValidState);
    if (finalStateType == null) {
      return new CurrentTokenNextPosition(null, pos + offset);
    }
    mCurrentState = START_STATE;
    if (mTokenValues.indexOf(tokenValue) < 0) {
      mTokenValues.add(tokenValue);
    }

    return new CurrentTokenNextPosition(new Token(mLexerAutomaton.finalStateType(lastValidState),
        mTokenValues.indexOf(tokenValue)), pos + offset);
  }
}
