package org.venutolo.texttablebuilder;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.venutolo.texttablebuilder.Alignment.LEFT;
import static org.venutolo.texttablebuilder.Alignment.RIGHT;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderToStringTest {

    private TextTableBuilder textTableBuilder;

    @Before
    public void setUp() {
        textTableBuilder = new TextTableBuilder()
                .setBoxDrawingCharacters(BoxDrawingCharacters.forCharacter('#'));
    }

    private static String joinForTable(final String... strings) {
        return StringUtils.join(strings, "\n");
    }

    /*========================================================================
     * HEADER ALIGNMENT - TOSTRING TESTS
     *========================================================================*/

    @Test
    public void testHeaderAlignmentsWhenNotSetToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("aaa", "bbb", "ccc");
        // headers should be left-aligned when alignment has not been set
        final String expected = joinForTable(
                "###################",
                "# h   # h   # h   #",
                "###################",
                "# aaa # bbb # ccc #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                expected,
                textTableBuilder.toString()
        );
    }

    @Test
    public void testHeaderAlignmentsWhenSetToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("aaa", "bbb", "ccc");
        textTableBuilder.setHeaderAlignments(LEFT, LEFT, LEFT);
        final String leftExpected = joinForTable(
                "###################",
                "# h   # h   # h   #",
                "###################",
                "# aaa # bbb # ccc #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                leftExpected,
                textTableBuilder.toString()
        );
        textTableBuilder.setHeaderAlignments(RIGHT, RIGHT, RIGHT);
        final String rightExpected = joinForTable(
                "###################",
                "#   h #   h #   h #",
                "###################",
                "# aaa # bbb # ccc #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                rightExpected,
                textTableBuilder.toString()
        );
    }

    @Test
    public void testClearHeaderAlignmentsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("aaa", "bbb", "ccc");
        textTableBuilder.setHeaderAlignments(RIGHT, RIGHT, RIGHT);
        textTableBuilder.clearHeaderAlignments();
        // headers should be left-aligned when alignment has not been set
        final String expected = joinForTable(
                "###################",
                "# h   # h   # h   #",
                "###################",
                "# aaa # bbb # ccc #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                expected,
                textTableBuilder.toString()
        );
    }

    /*========================================================================
     * COLUMN ALIGNMENT - TOSTRING TESTS
     *========================================================================*/

    @Test
    public void testColumnAlignmentsWhenNotSetToString() {
        textTableBuilder.addRow("a", "bbb", "cc");
        textTableBuilder.addRow("dddd", "ee", "f");
        // columns should be left-aligned when alignment has not been set
        final String expected = joinForTable(
                "###################",
                "# a    # bbb # cc #",
                "# dddd # ee  # f  #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                expected,
                textTableBuilder.toString()
        );
    }

    @Test
    public void testColumnAlignmentsWhenSetToString() {
        textTableBuilder.addRow("a", "bbb", "cc");
        textTableBuilder.addRow("dddd", "ee", "f");
        textTableBuilder.setColumnAlignments(LEFT, LEFT, LEFT);
        final String leftExpected = joinForTable(
                "###################",
                "# a    # bbb # cc #",
                "# dddd # ee  # f  #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                leftExpected,
                textTableBuilder.toString()
        );
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT);
        final String rightExpected = joinForTable(
                "###################",
                "#    a # bbb # cc #",
                "# dddd #  ee #  f #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                rightExpected,
                textTableBuilder.toString()
        );
    }

    @Test
    public void testClearColumnAlignmentsToString() {
        textTableBuilder.addRow("a", "bbb", "cc");
        textTableBuilder.addRow("dddd", "ee", "f");
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT);
        textTableBuilder.clearColumnAlignments();
        // columns should be left-aligned when alignment has not been set
        final String expected = joinForTable(
                "###################",
                "# a    # bbb # cc #",
                "# dddd # ee  # f  #",
                "###################"
        );
        assertEquals(
                "toString() did not produced expected value",
                expected,
                textTableBuilder.toString()
        );
    }

}
