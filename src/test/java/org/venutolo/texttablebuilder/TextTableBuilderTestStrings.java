package org.venutolo.texttablebuilder;

/**
 * @author Rick Venutolo
 */
final class TextTableBuilderTestStrings {

    static final String GETTER_SETTER_VALUE_NOT_EQUAL =
            "value returned from getter is not equal to that given to setter";

    static final String SETTER_NO_DEFENSIVE_COPY =
            "setter did not create a defensive copy";

    static final String GETTER_NO_DEFENSIVE_COPY =
            "getter did not create a defensive copy";

    static final String BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING =
            "expected 2 columns";

    static final String EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH =
            "expected IAE for wrong column length";

    static final String NOT_EMPTY_AFTER_CLEAR =
            "not empty after clearing";

    private TextTableBuilderTestStrings() {
    }
}
