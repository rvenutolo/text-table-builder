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

    @Test
    public void testEmptyTableToString() {
        assertEquals(
                "toString() did not produced expected value",
                // should just be the four corner characters
                joinForTable(
                        "##",
                        "##"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeadersToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeadersThenClearToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.clearHeaders();
        assertEquals(
                "toString() did not produced expected value",
                // should retain three columns
                joinForTable(
                        "##########",
                        "##########"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testAddRowToString() {
        textTableBuilder.addRow("a", "b", "c");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# a # b # c #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testAddRowsToString() {
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testAddRowsThenClearToString() {
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.clearRows();
        assertEquals(
                "toString() did not produced expected value",
                // should retain three columns
                joinForTable(
                        "##########",
                        "##########"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeaderAndAddRowsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeaderAndAddRowsThenClearHeadersToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.clearHeaders();
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeaderAndAddRowsThenClearRowsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.clearRows();
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeaderAndAddRowsThenClearBothToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.clearHeaders();
        textTableBuilder.clearRows();
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "##########",
                        "##########"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testDefaultHeaderAlignmentsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("aa", "bbb", "cccc");
        textTableBuilder.addRow("dd", "eee", "ffff");
        assertEquals(
                "toString() did not produced expected value",
                // should default to left alignment
                joinForTable(
                        "###################",
                        "# h  # h   # h    #",
                        "###################",
                        "# aa # bbb # cccc #",
                        "# dd # eee # ffff #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeaderAlignmentsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("aa", "bbb", "cccc");
        textTableBuilder.addRow("dd", "eee", "ffff");
        textTableBuilder.setHeaderAlignments(LEFT, LEFT, LEFT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###################",
                        "# h  # h   # h    #",
                        "###################",
                        "# aa # bbb # cccc #",
                        "# dd # eee # ffff #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
        textTableBuilder.setHeaderAlignments(RIGHT, RIGHT, RIGHT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###################",
                        "#  h #   h #    h #",
                        "###################",
                        "# aa # bbb # cccc #",
                        "# dd # eee # ffff #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetThenClearHeaderAlignmentsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("aa", "bbb", "cccc");
        textTableBuilder.addRow("dd", "eee", "ffff");
        textTableBuilder.setHeaderAlignments(RIGHT, RIGHT, RIGHT);
        textTableBuilder.clearHeaderAlignments();
        assertEquals(
                "toString() did not produced expected value",
                // should default to left alignment after clear
                joinForTable(
                        "###################",
                        "# h  # h   # h    #",
                        "###################",
                        "# aa # bbb # cccc #",
                        "# dd # eee # ffff #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testDefaultColumnAlignmentsToString() {
        textTableBuilder.setHeaders("hh", "hhh", "hhhh");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        assertEquals(
                "toString() did not produced expected value",
                // should default to left alignment
                joinForTable(
                        "###################",
                        "# hh # hhh # hhhh #",
                        "###################",
                        "# a  # b   # c    #",
                        "# d  # e   # f    #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetColumnAlignmentsToString() {
        textTableBuilder.setHeaders("hh", "hhh", "hhhh");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setColumnAlignments(LEFT, LEFT, LEFT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###################",
                        "# hh # hhh # hhhh #",
                        "###################",
                        "# a  # b   # c    #",
                        "# d  # e   # f    #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###################",
                        "# hh # hhh # hhhh #",
                        "###################",
                        "#  a #   b #    c #",
                        "#  d #   e #    f #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetThenClearColumnAlignmentsToString() {
        textTableBuilder.setHeaders("hh", "hhh", "hhhh");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT);
        textTableBuilder.clearColumnAlignments();
        assertEquals(
                "toString() did not produced expected value",
                // should default to left alignment after clear
                joinForTable(
                        "###################",
                        "# hh # hhh # hhhh #",
                        "###################",
                        "# a  # b   # c    #",
                        "# d  # e   # f    #",
                        "###################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testDefaultHeaderAndColumnAlignmentsToString() {
        textTableBuilder.setHeaders("h", "hh", "hhh", "hhhh");
        textTableBuilder.addRow("aaaa", "bbb", "cc", "d");
        textTableBuilder.addRow("e", "f", "g", "h");
        assertEquals(
                "toString() did not produced expected value",
                // should default to left alignment
                joinForTable(
                        "###########################",
                        "# h    # hh  # hhh # hhhh #",
                        "###########################",
                        "# aaaa # bbb # cc  # d    #",
                        "# e    # f   # g   # h    #",
                        "###########################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetHeaderAndColumnAlignmentsToString() {
        textTableBuilder.setHeaders("h", "hh", "hhh", "hhhh");
        textTableBuilder.addRow("aaaa", "bbb", "cc", "d");
        textTableBuilder.addRow("e", "f", "g", "h");
        textTableBuilder.setHeaderAlignments(LEFT, LEFT, LEFT, LEFT);
        textTableBuilder.setColumnAlignments(LEFT, LEFT, LEFT, LEFT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###########################",
                        "# h    # hh  # hhh # hhhh #",
                        "###########################",
                        "# aaaa # bbb # cc  # d    #",
                        "# e    # f   # g   # h    #",
                        "###########################"
                ),
                textTableBuilder.toString()
        );
        textTableBuilder.setHeaderAlignments(RIGHT, RIGHT, RIGHT, RIGHT);
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT, RIGHT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###########################",
                        "#    h #  hh # hhh # hhhh #",
                        "###########################",
                        "# aaaa # bbb #  cc #    d #",
                        "#    e #   f #   g #    h #",
                        "###########################"
                ),
                textTableBuilder.toString()
        );
        textTableBuilder.setHeaderAlignments(LEFT, LEFT, LEFT, LEFT);
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT, RIGHT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###########################",
                        "# h    # hh  # hhh # hhhh #",
                        "###########################",
                        "# aaaa # bbb #  cc #    d #",
                        "#    e #   f #   g #    h #",
                        "###########################"
                ),
                textTableBuilder.toString()
        );
        textTableBuilder.setHeaderAlignments(RIGHT, RIGHT, RIGHT, RIGHT);
        textTableBuilder.setColumnAlignments(LEFT, LEFT, LEFT, LEFT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "###########################",
                        "#    h #  hh # hhh # hhhh #",
                        "###########################",
                        "# aaaa # bbb # cc  # d    #",
                        "# e    # f   # g   # h    #",
                        "###########################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetThenClearHeaderAndColumnAlignmentsToString() {
        textTableBuilder.setHeaders("h", "hh", "hhh", "hhhh");
        textTableBuilder.addRow("aaaa", "bbb", "cc", "d");
        textTableBuilder.addRow("e", "f", "g", "h");
        textTableBuilder.setHeaderAlignments(RIGHT, RIGHT, RIGHT, RIGHT);
        textTableBuilder.setColumnAlignments(RIGHT, RIGHT, RIGHT, RIGHT);
        textTableBuilder.clearHeaderAlignments();
        textTableBuilder.clearColumnAlignments();
        assertEquals(
                "toString() did not produced expected value",
                // should default to left alignment after clear
                joinForTable(
                        "###########################",
                        "# h    # hh  # hhh # hhhh #",
                        "###########################",
                        "# aaaa # bbb # cc  # d    #",
                        "# e    # f   # g   # h    #",
                        "###########################"
                ),
                textTableBuilder.toString()
        );
    }

}
