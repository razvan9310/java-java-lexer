package JavaLexer;

/**
 * The {@link Lexer}'s atomic element.
 */
public class Token {
  /**
   * Token types, according to the Java spec.
   */
  public static final int EMPTY_TOKEN = 0;
  public static final int KEYWORD_IDENTIFIER_BOOLEAN_NULL = 1;
  public static final int NUMBER_DECIMAL_FLOATING_POINT = 2;
  public static final int NUMBER_DECIMAL_INTEGER = 3;
  public static final int NUMBER_HEXADECIMAL_FLOATING_POINT = 4;
  public static final int NUMBER_HEXADECIMAL_INTEGER = 5;
  public static final int NUMBER_OCTAL = 6;
  public static final int NUMBER_BINARY = 7;
  public static final int OPERATOR = 8;
  public static final int SEPARATOR = 9;
  public static final int CHARACTER = 10;
  public static final int STRING = 11;

  private int mType;
  private int mValue;

  public Token(int type, int value) {
    mType = type;
    mValue = value;
  }

  /**
   * Returns the current token's type.
   * @return One of the above defined static types.
   */
  public int getType() {
    return mType;
  }

  /**
   * Returns the current token's value.
   * @return String representing current token's value, i.e. "int", or "currentToken".
   */
  public int getValue() {
    return mValue;
  }
}
