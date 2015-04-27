package JavaLexer;

/**
 * The {@link Lexer}'s atomic element.
 */
public class Token {
  private int mType;
  private int mValue;

  public Token(int type, int value) {
    mType = type;
    mValue = value;
  }

  /**
   * Returns the current token's type.
   * @return One of the values defined in JavaLexer.Utils:
   *     <ol>
   *       <li>EMPTY_TOKEN</li>
   *       <li>KEYWORD_IDENTIFIER_BOOLEAN_NULL</li>
   *       <li>NUMBER_DECIMAL_FLOATING_POINT</li>;
   *       <li>NUMBER_DECIMAL_INTEGER</li>
   *       <li>NUMBER_HEXADECIMAL_FLOATING_POINT</li>
   *       <li>NUMBER_HEXADECIMAL_INTEGER</li>
   *       <li>NUMBER_OCTAL</li>
   *       <li>NUMBER_BINARY</li>
   *       <li>OPERATOR</li>
   *       <li>SEPARATOR</li>
   *       <li>CHARACTER</li>
   *       <li>STRING</li>
   *     </ol>
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
