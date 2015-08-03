package org.venutolo.texttablebuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Rick Venutolo
 */
public class BoxDrawingCharactersTest {

    private static final String NOT_EQUAL_TO_NULL =
            "must not be equal to null";

    private static final String NOT_EQUAL_RANDOM_OBJECT =
            "must not be equal to some random object";

    private static final String EQUAL_ITSELF =
            "must be equal to itself";

    private static final String NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES =
            "must not be equal to instance with different values";

    private static final String EQUAL_TO_INSTANCE_WITH_SAME_VALUES =
            "must be equal to instance with same values";

    private static final String HASH_CODE_MUST_BE_CONSISTENT =
            "hash code must be consistent";

    private static final String HASH_CODE_MUST_BE_DISTINCT =
            "hash code must be distinct for non-equal instances";

    private static final String HASH_CODE_MUST_BE_SAME =
            "hash code must be the same for equal instances";

    private static final String TO_STRING_SHOULD_BE_CONSISTENT =
            "toString should be consistent";

    private static final String TO_STRING_SHOULD_BE_DISTINCT =
            "toString should be distinct for non-equal instances";

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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BoxDrawingCharacters testCharacters;

    private BoxDrawingCharacters.Builder builder;

    @Before
    public void setUp() {
        testCharacters = new BoxDrawingCharacters.Builder()
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
        builder = new BoxDrawingCharacters.Builder();
    }

    @After
    public void tearDown() {
        testCharacters = null;
        builder = null;
    }

    @Test
    public void testEquals() {
        assertNotEquals(
                NOT_EQUAL_TO_NULL,
                null,
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_RANDOM_OBJECT,
                new Object(),
                testCharacters
        );
        assertEquals(
                EQUAL_ITSELF,
                testCharacters,
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setHorizontal('h').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setVertical('v').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setTopLeftCorner('1').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setTopIntersect('2').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setTopRightCorner('3').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setLeftIntersect('4').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setMiddleIntersect('5').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setRightIntersect('6').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setBottomLeftCorner('7').build(),
                testCharacters
        );
        assertNotEquals(
                NOT_EQUAL_TO_INSTANCE_WITH_DIFFERENT_VALUES,
                builder.setBottomIntersect('8').build(),
                testCharacters
        );
        assertEquals(
                EQUAL_TO_INSTANCE_WITH_SAME_VALUES,
                builder.setBottomRightCorner('9').build(),
                testCharacters
        );
    }

    @Test
    public void testHashCode() {
        assertEquals(
                HASH_CODE_MUST_BE_CONSISTENT,
                testCharacters.hashCode(),
                testCharacters.hashCode()
        );
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setHorizontal('h');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setVertical('v');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setTopLeftCorner('1');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setTopIntersect('2');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setTopRightCorner('3');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setLeftIntersect('4');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setMiddleIntersect('5');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setRightIntersect('6');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setBottomLeftCorner('7');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setBottomIntersect('8');
        assertNotEquals(
                HASH_CODE_MUST_BE_DISTINCT,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
        builder.setBottomRightCorner('9');
        assertEquals(
                HASH_CODE_MUST_BE_SAME,
                builder.build().hashCode(),
                testCharacters.hashCode()
        );
    }

    @Test
    public void testToString() {
        // this may be pointless, but is the only thing preventing me from 100% code coverage
        // and I just want to see that green
        assertEquals(
                TO_STRING_SHOULD_BE_CONSISTENT,
                testCharacters.toString(),
                testCharacters.toString()
        );
        assertNotEquals(
                TO_STRING_SHOULD_BE_DISTINCT,
                builder.build().toString(),
                testCharacters.toString()
        );
    }

    @Test
    public void testBuilder() {
        final BoxDrawingCharacters built = new BoxDrawingCharacters.Builder()
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
        assertEquals(
                "horizontal should be \'h\'",
                'h',
                built.getHorizontal()
        );
        assertEquals(
                "vertical should be \'v\'",
                'v',
                built.getVertical()
        );
        assertEquals(
                "topLeftCorner should be \'1\'",
                '1',
                built.getTopLeftCorner()
        );
        assertEquals(
                "topIntersect should be \'2\'",
                '2',
                built.getTopIntersect()
        );
        assertEquals(
                "topRightCorner should be \'3\'",
                '3',
                built.getTopRightCorner()
        );
        assertEquals(
                "leftIntersect should be \'4\'",
                '4',
                built.getLeftIntersect()
        );
        assertEquals(
                "middleIntersect should be \'5\'",
                '5',
                built.getMiddleIntersect()
        );
        assertEquals(
                "rightIntersect should be \'6\'",
                '6',
                built.getRightIntersect()
        );
        assertEquals(
                "bottomLeftCorner should be \'7\'",
                '7',
                built.getBottomLeftCorner()
        );
        assertEquals(
                "bottomIntersect should be \'8\'",
                '8',
                built.getBottomIntersect()
        );
        assertEquals(
                "bottomRightCorner should be \'9\'",
                '9',
                built.getBottomRightCorner()
        );
    }

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
                testCharacters,
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

}
