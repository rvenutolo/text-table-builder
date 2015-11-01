package org.venutolo.texttablebuilder;

/**
 * @author Rick Venutolo
 */
final class TestStrings {

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

    static final String CANNOT_BE_NULL =
            "cannot be null";

    static final String EXPECTED_NPE_FOR_NULL_LIST =
            "expected NPE for null list";

    static final String EXPECTED_NPE_FOR_NULL_ALIGNMENT =
            "expected NPE for null alignment";

    static final String ALIGNMENTS_NOT_NULL =
            "alignments should not be null";

    static final String ALIGNMENTS_EMPTY =
            "alignments should be empty";

    static final String NOT_EQUAL_TO_NULL =
            "must not be equal to null";

    static final String NOT_EQUAL_RANDOM_OBJECT =
            "must not be equal to some random object";

    static final String EQUAL_ITSELF =
            "must be equal to itself";

    static final String NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES =
            "must not be equal to instance with different values";

    static final String EQUAL_TO_INSTANCE_WITH_SAME_VALUES =
            "must be equal to instance with same values";

    static final String HASH_CODE_MUST_BE_DISTINCT =
            "hash code must be distinct for non-equal instances";

    static final String HASH_CODE_MUST_BE_SAME =
            "hash code must be the same for equal instances";

    static final String TO_STRING_MUST_BE_DISTINCT =
            "toString() must be distinct for non-equal instances";

    static final String TO_STRING_MUST_BE_SAME =
            "toString() must be the same for equal instances";

    private TestStrings() {
    }

}
