package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.venutolo.texttablebuilder.TestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TestStrings.EQUAL_ITSELF;
import static org.venutolo.texttablebuilder.TestStrings.EQUAL_TO_INSTANCE_WITH_SAME_VALUES;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_NULL_ALIGNMENT;
import static org.venutolo.texttablebuilder.TestStrings.HASH_CODE_MUST_BE_DISTINCT;
import static org.venutolo.texttablebuilder.TestStrings.HASH_CODE_MUST_BE_SAME;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EQUAL_RANDOM_OBJECT;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EQUAL_TO_NULL;
import static org.venutolo.texttablebuilder.TestStrings.TO_STRING_MUST_BE_DISTINCT;
import static org.venutolo.texttablebuilder.TestStrings.TO_STRING_MUST_BE_SAME;

/**
 * @author Rick Venutolo
 */
public class BoxDrawingCharactersTest {

    private static final String EXPECTED_IAE_FOR_INCORRECT_LENGTH =
            "Expected IAE for incorrect length";

    private static final String EXPECTED_IAE_LENGTH_SUBSTRING =
            "characters long";

    private static final String EXPECTED_IAE_HORIZ_SUBSTRING =
            "Inconsistent horizontal line character";

    private static final String EXPECTED_IAE_FOR_INCONSISTENT_HORIZ =
            "Expected IAE for inconsistent horizontal usage";

    private static final String EXPECTED_IAE_VERT_SUBSTRING =
            "Inconsistent vertical line character";

    private static final String EXPECTED_IAE_FOR_INCONSISTENT_VERT =
            "Expected IAE for inconsistent vertical usage";

    private static final String EXPECTED_IAE_BUILDER_SUBSTRING =
            "cannot be null";

    private static final String EXPECTED_IAE_FOR_NULL_BUILDER =
            "Expected IAE for null builder";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BoxDrawingCharacters built;

    private BoxDrawingCharacters.Builder builder;

    @Before
    public void setUp() {
        built = new BoxDrawingCharacters.Builder()
                .setHorizontal('h')
                .setVertical('v')
                .setTopLeftCorner('1')
                .setTopIntersect('2')
                .setTopRightCorner('3')
                .setLeftIntersect('4')
                .setMiddleIntersect('5')
                .setRightIntersect('6')
                .setBottomLeftCorner('7')
                .setBottomIntersect('8')
                .setBottomRightCorner('9')
                .build();
        builder = new BoxDrawingCharacters.Builder()
                .setHorizontal('h')
                .setVertical('v')
                .setTopLeftCorner('1')
                .setTopIntersect('2')
                .setTopRightCorner('3')
                .setLeftIntersect('4')
                .setMiddleIntersect('5')
                .setRightIntersect('6')
                .setBottomLeftCorner('7')
                .setBottomIntersect('8')
                .setBottomRightCorner('9');
    }

    @Test
    public void testWhenNullBuilder() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_BUILDER_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_BUILDER);
        new BoxDrawingCharacters(null);
    }

    /*========================================================================
     * TESTS FOR GETTERS
     *========================================================================*/

    @Test
    public void testGetHorizontal() {
        assertEquals(
                "horizontal should be \'h\'",
                'h',
                built.getHorizontal()
        );
    }

    @Test
    public void testGetVertical() {
        assertEquals(
                "vertical should be \'v\'",
                'v',
                built.getVertical()
        );
    }

    @Test
    public void testGetTopLeftCorner() {
        assertEquals(
                "topLeftCorner should be \'1\'",
                '1',
                built.getTopLeftCorner()
        );
    }

    @Test
    public void testGetTopIntersect() {
        assertEquals(
                "topIntersect should be \'2\'",
                '2',
                built.getTopIntersect()
        );
    }

    @Test
    public void testGetTopRightCorner() {
        assertEquals(
                "topRightCorner should be \'3\'",
                '3',
                built.getTopRightCorner()
        );
    }

    @Test
    public void testGetLeftIntersect() {
        assertEquals(
                "leftIntersect should be \'4\'",
                '4',
                built.getLeftIntersect()
        );
    }

    @Test
    public void testGetMiddleIntersect() {
        assertEquals(
                "middleIntersect should be \'5\'",
                '5',
                built.getMiddleIntersect()
        );
    }

    @Test
    public void testGetRightIntersect() {
        assertEquals(
                "rightIntersect should be \'6\'",
                '6',
                built.getRightIntersect()
        );
    }

    @Test
    public void testGetBottomLeftCorner() {
        assertEquals(
                "bottomLeftCorner should be \'7\'",
                '7',
                built.getBottomLeftCorner()
        );
    }

    @Test
    public void testGetBottomIntersect() {
        assertEquals(
                "bottomIntersect should be \'8\'",
                '8',
                built.getBottomIntersect()
        );
    }

    @Test
    public void testGetBottomRightCorner() {
        assertEquals(
                "bottomRightCorner should be \'9\'",
                '9',
                built.getBottomRightCorner()
        );
    }

    /*========================================================================
     * TESTS FOR forBoxDrawing(String)
     *========================================================================*/

    @Test
    public void testForCharacter() {
        final BoxDrawingCharacters expected = new BoxDrawingCharacters.Builder()
                .setHorizontal('x')
                .setVertical('x')
                .setTopLeftCorner('x')
                .setTopIntersect('x')
                .setTopRightCorner('x')
                .setLeftIntersect('x')
                .setMiddleIntersect('x')
                .setRightIntersect('x')
                .setBottomLeftCorner('x')
                .setBottomIntersect('x')
                .setBottomRightCorner('x')
                .build();
        final BoxDrawingCharacters actual = BoxDrawingCharacters.forCharacter('x');
        assertEquals(
                "forCharacter should produce equivalent instance",
                expected,
                actual
        );
    }

    /*========================================================================
     * TESTS FOR forBoxDrawing(String)
     *========================================================================*/

    @Test
    public void testFromBoxDrawingSucceeds() {
        final BoxDrawingCharacters fromBoxDrawing = BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "vxvxv"
                + "7h8h9"
                //@formatter:on
        );
        assertEquals(
                "fromBoxDrawing should produce equivalent instance",
                built,
                fromBoxDrawing
        );
    }

    @Test
    public void testFromBoxDrawingExceptionWrongLengthTooLong() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_LENGTH_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCORRECT_LENGTH);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "vxvxv"
                + "7h8h9_"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionWrongLengthTooShort() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_LENGTH_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCORRECT_LENGTH);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "vxvxv"
                + "7h8h"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentHorizontals1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_HORIZ_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_HORIZ);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2H3"
                + "vxvxv"
                + "4h5h6"
                + "vxvxv"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentHorizontals2() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_HORIZ_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_HORIZ);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4H5h6"
                + "vxvxv"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentHorizontals3() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_HORIZ_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_HORIZ);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5H6"
                + "vxvxv"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentHorizontals4() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_HORIZ_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_HORIZ);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "vxvxv"
                + "7H8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentHorizontals5() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_HORIZ_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_HORIZ);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "vxvxv"
                + "7h8H9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentVerticals1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_VERT_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_VERT);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxVxv"
                + "4h5h6"
                + "vxvxv"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentVerticals2() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_VERT_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_VERT);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxV"
                + "4h5h6"
                + "vxvxv"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentVerticals3() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_VERT_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_VERT);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "Vxvxv"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentVerticals4() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_VERT_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_VERT);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "vxVxv"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingExceptionInconsistentVerticals5() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(EXPECTED_IAE_VERT_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_INCONSISTENT_VERT);
        BoxDrawingCharacters.fromBoxDrawing(
                //@formatter:off
                  "1h2h3"
                + "vxvxv"
                + "4h5h6"
                + "vxvxV"
                + "7h8h9"
                //@formatter:on
        );
    }

    @Test
    public void testFromBoxDrawingNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_ALIGNMENT);
        BoxDrawingCharacters.fromBoxDrawing(null);
    }

    /*========================================================================
     * TESTS FOR equals(Object)
     *========================================================================*/

    @Test
    public void testNotEqualToNull() {
        assertNotEquals(
                NOT_EQUAL_TO_NULL,
                built,
                null
        );
    }

    @Test
    public void testNotEqualRandomObject() {
        assertNotEquals(
                NOT_EQUAL_RANDOM_OBJECT,
                built,
                new Object()
        );
    }

    @Test
    public void testEqualItself() {
        assertEquals(
                EQUAL_ITSELF,
                built,
                built
        );
    }

    @Test
    public void testEqualToSameProperties() {
        assertEquals(
                EQUAL_TO_INSTANCE_WITH_SAME_VALUES,
                built,
                builder.build()
        );
    }

    @Test
    public void testNotEqualToDifferentHorizontal() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setHorizontal('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentVertical() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setVertical('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentTopLeftCorner() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setTopLeftCorner('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentTopIntersect() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setTopIntersect('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentTopRightCorner() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setTopRightCorner('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentLeftIntersect() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setLeftIntersect('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentMiddleIntersect() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setMiddleIntersect('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentRightIntersect() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setRightIntersect('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentBottomLeftCorner() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setBottomLeftCorner('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentBottomIntersect() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setBottomIntersect('x').build()
        );
    }

    @Test
    public void testNotEqualToDifferentBottomRightCorner() {
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                built,
                builder.setBottomRightCorner('x').build()
        );
    }

    /*========================================================================
     * TESTS FOR hashCode()
     *========================================================================*/

    @Test
    public void testHashCodeSameForEqualInstances() {
        assertEquals(
                HASH_CODE_MUST_BE_SAME,
                built.hashCode(),
                builder.build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentHorizontal() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setHorizontal('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentVertical() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setVertical('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentTopLeftCorner() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setTopLeftCorner('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentTopIntersect() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setTopIntersect('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentTopRightCorner() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setTopRightCorner('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentLeftIntersect() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setLeftIntersect('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentMiddleIntersect() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setMiddleIntersect('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentRightIntersect() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setRightIntersect('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentBottomLeftCorner() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setBottomLeftCorner('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentBottomIntersect() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setBottomIntersect('x').build().hashCode()
        );
    }

    @Test
    public void testHashCodeDifferentBottomRightCorner() {
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                built.hashCode(),
                builder.setBottomRightCorner('x').build().hashCode()
        );
    }

    /*========================================================================
     * TESTS FOR toString()
     *========================================================================*/

    @Test
    public void testToStringSameForEqualInstances() {
        assertEquals(
                TO_STRING_MUST_BE_SAME,
                built.toString(),
                builder.build().toString()
        );
    }

    @Test
    public void testToStringDifferentHorizontal() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setHorizontal('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentVertical() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setVertical('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentTopLeftCorner() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setTopLeftCorner('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentTopIntersect() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setTopIntersect('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentTopRightCorner() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setTopRightCorner('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentLeftIntersect() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setLeftIntersect('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentMiddleIntersect() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setMiddleIntersect('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentRightIntersect() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setRightIntersect('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentBottomLeftCorner() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setBottomLeftCorner('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentBottomIntersect() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setBottomIntersect('x').build().toString()
        );
    }

    @Test
    public void testToStringDifferentBottomRightCorner() {
        assertNotEquals(
                TO_STRING_MUST_BE_DISTINCT,
                built.toString(),
                builder.setBottomRightCorner('x').build().toString()
        );
    }

}
