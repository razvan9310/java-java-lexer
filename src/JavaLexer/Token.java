package JavaLexer;

/**
 * Created by razvan on 4/26/15.
 */
public class Token {
  private int mType;
  private int mValue;

  public Token(int type, int value) {
    mType = type;
    mValue = value;
  }

  public int getType() {
    return mType;
  }

  public int getValue() {
    return mValue;
  }
}
