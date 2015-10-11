package org.venutolo.texttablebuilder;

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

    @Test
    public void testColumnAlignmentsWhenNotSetToString() {
        textTableBuilder.addRow("a", "bbb", "cc");
        textTableBuilder.addRow("dddd", "ee", "f");
        // columns should be left-aligned when alignment has not been set
        final String expected = ""
                                + "###################\n"
                                + "# a    # bbb # cc #\n"
                                + "# dddd # ee  # f  #\n"
                                + "###################";
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
        final String leftExpected = ""
                                    + "###################\n"
                                    + "# a    # bbb # cc #\n"
                                    + "# dddd # ee  # f  #\n"
                                    + "###################";
        assertEquals(
                "toString() did not produced expected value",
                leftExpected,
                textTableBuilder.toString()
        );
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT);
        final String rightExpected = ""
                                     + "###################\n"
                                     + "#    a # bbb # cc #\n"
                                     + "# dddd #  ee #  f #\n"
                                     + "###################";
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
        final String expected = ""
                                + "###################\n"
                                + "# a    # bbb # cc #\n"
                                + "# dddd # ee  # f  #\n"
                                + "###################";
        assertEquals(
                "toString() did not produced expected value",
                expected,
                textTableBuilder.toString()
        );
    }

}
