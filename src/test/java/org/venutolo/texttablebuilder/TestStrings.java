package org.venutolo.texttablebuilder;

/**
 * @author Rick Venutolo
 */
final class TestStrings {

    public static final String GETTER_SETTER_VALUE_NOT_EQUAL =
            "value returned from getter is not equal to that given to setter";

    public static final String SETTER_NO_DEFENSIVE_COPY =
            "setter did not create a defensive copy";

    public static final String GETTER_NO_DEFENSIVE_COPY =
            "getter did not create a defensive copy";

    public static final String BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING =
            "expected 2 columns";

    public static final String EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH =
            "expected IAE for wrong column length";

    public static final String NOT_EMPTY_AFTER_CLEAR =
            "not empty after clearing";

    public static final String CANNOT_BE_NULL =
            "cannot be null";

    public static final String EXPECTED_IAE_FOR_NULL_LIST =
            "expected IAE for null list";

    public static final String EXPECTED_IAE_FOR_NULL_ALIGNMENT =
            "expected IAE for null alignment";

    public static final String ALIGNMENTS_NOT_NULL_AND_EMPTY =
            "alignments should be non-null and empty";

    public static final String NOT_EQUAL_TO_NULL =
            "must not be equal to null";

    public static final String NOT_EQUAL_RANDOM_OBJECT =
            "must not be equal to some random object";

    public static final String EQUAL_ITSELF =
            "must be equal to itself";

    public static final String NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES =
            "must not be equal to instance with different values";

    public static final String EQUAL_TO_INSTANCE_WITH_SAME_VALUES =
            "must be equal to instance with same values";

    public static final String HASH_CODE_MUST_BE_DISTINCT =
            "hash code must be distinct for non-equal instances";

    public static final String HASH_CODE_MUST_BE_SAME =
            "hash code must be the same for equal instances";

    public static final String TO_STRING_MUST_BE_DISTINCT =
            "toString() must be distinct for non-equal instances";

    public static final String TO_STRING_MUST_BE_SAME =
            "toString() must be the same for equal instances";

    public static final String SHOULD_NOT_REACH_THIS_POINT =
            "test should never reach this point";

    private TestStrings() {
    }

}
