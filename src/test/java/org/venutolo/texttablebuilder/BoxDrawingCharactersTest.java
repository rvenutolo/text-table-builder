package org.venutolo.texttablebuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Venutolo
 */
public class BoxDrawingCharactersTest {

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
        assertTrue("must not be equal to null", !testCharacters.equals(null));
        assertTrue("must not be equal to some random object", !testCharacters.equals(new Object()));
        assertTrue("must be equal to itself", testCharacters.equals(testCharacters));
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setHorizontal('h').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setVertical('v').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setTopLeftCorner('1').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setTopIntersect('2').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setTopRightCorner('3').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setLeftIntersect('4').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setMiddleIntersect('5').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setRightIntersect('6').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setBottomLeftCorner('7').build())
        );
        assertTrue(
                "must not be equal to instance with different values",
                !testCharacters.equals(builder.setBottomIntersect('8').build())
        );
        assertTrue(
                "must be equal to instance with same values",
                testCharacters.equals(builder.setBottomRightCorner('9').build())
        );
    }

    @Test
    public void testHashCode() {
        assertEquals(
                "hash code must be consistent",
                testCharacters.hashCode(),
                testCharacters.hashCode()
        );
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setHorizontal('h');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setVertical('v');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setTopLeftCorner('1');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setTopIntersect('2');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setTopRightCorner('3');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setLeftIntersect('4');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setMiddleIntersect('5');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setRightIntersect('6');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setBottomLeftCorner('7');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setBottomIntersect('8');
        assertNotEquals(
                "hash code must be distinct for non-equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
        builder.setBottomRightCorner('9');
        assertEquals(
                "hash code must be the same for equal instances",
                testCharacters.hashCode(),
                builder.build().hashCode()
        );
    }

    @Test
    public void testToString() {
        // this may be pointless, but is the only thing preventing me from 100% code coverage
        // and I just want to see that green
        assertEquals(
                "toString should be consistent",
                testCharacters.toString(),
                testCharacters.toString()
        );
        assertNotEquals(
                "toString should be distinct for non-equal instances",
                testCharacters.toString(),
                builder.build().toString()
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
        assertEquals("horizontal should be \'h\'", 'h', built.getHorizontal());
        assertEquals("vertical should be \'v\'", 'v', built.getVertical());
        assertEquals("topLeftCorner should be \'1\'", '1', built.getTopLeftCorner());
        assertEquals("topIntersect should be \'2\'", '2', built.getTopIntersect());
        assertEquals("topRightCorner should be \'3\'", '3', built.getTopRightCorner());
        assertEquals("leftIntersect should be \'4\'", '4', built.getLeftIntersect());
        assertEquals("middleIntersect should be \'5\'", '5', built.getMiddleIntersect());
        assertEquals("rightIntersect should be \'6\'", '6', built.getRightIntersect());
        assertEquals("bottomLeftCorner should be \'7\'", '7', built.getBottomLeftCorner());
        assertEquals("bottomIntersect should be \'8\'", '8', built.getBottomIntersect());
        assertEquals("bottomRightCorner should be \'9\'", '9', built.getBottomRightCorner());
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
        expectedException.expectMessage("long");
        expectedException.reportMissingExceptionWithMessage("Expected IAE for incorrect length");
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
        expectedException.expectMessage("long");
        expectedException.reportMissingExceptionWithMessage("Expected IAE for incorrect length");
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
        expectedException.expectMessage("horizontal");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent horizontal usage"
        );
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
        expectedException.expectMessage("horizontal");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent horizontal usage"
        );
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
        expectedException.expectMessage("horizontal");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent horizontal usage"
        );
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
        expectedException.expectMessage("horizontal");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent horizontal usage"
        );
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
        expectedException.expectMessage("horizontal");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent horizontal usage"
        );
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
        expectedException.expectMessage("vertical");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent vertical usage"
        );
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
        expectedException.expectMessage("vertical");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent vertical usage"
        );
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
        expectedException.expectMessage("vertical");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent vertical usage"
        );
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
        expectedException.expectMessage("vertical");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent vertical usage"
        );
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
        expectedException.expectMessage("vertical");
        expectedException.reportMissingExceptionWithMessage(
                "Expected IAE for inconsistent vertical usage"
        );
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
