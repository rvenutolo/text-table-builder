package org.venutolo.texttablebuilder;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.venutolo.texttablebuilder.Alignment.LEFT;
import static org.venutolo.texttablebuilder.Alignment.RIGHT;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.LIGHT;

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
    public void testSetBoxDrawingCharactersToString() {
        textTableBuilder.setHeaders("h", "h");
        textTableBuilder.addRow("a", "b");
        textTableBuilder.setBoxDrawingCharacters(LIGHT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "┌───┬───┐",
                        "│ h │ h │",
                        "├───┼───┤",
                        "│ a │ b │",
                        "└───┴───┘"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testEmptyTableToString() {
        textTableBuilder.setBoxDrawingCharacters(LIGHT);
        assertEquals(
                "toString() did not produced expected value",
                // should just be the four corner characters
                joinForTable(
                        "┌┐",
                        "└┘"
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

    @Test
    public void testSetRepeatHeadersEveryXRowsNotEnoughRowsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setRepeatHeadersEveryXRows(5);
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
    public void testSetRepeatHeadersEvery1RowsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setRepeatHeadersEveryXRows(1);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# d # e # f #",
                        "#############",
                        "# h # h # h #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetRepeatHeadersEvery2RowsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setRepeatHeadersEveryXRows(2);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############",
                        "# h # h # h #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetRepeatHeadersEvery2RowsWith3RowsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.addRow("g", "h", "i");
        textTableBuilder.setRepeatHeadersEveryXRows(2);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# g # h # i #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetRepeatHeadersEveryXRowsThenClearToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.addRow("g", "h", "i");
        textTableBuilder.setRepeatHeadersEveryXRows(1);
        textTableBuilder.setRepeatHeadersEveryXRows(0);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "# g # h # i #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetRepeatHeadersAtBottomToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.repeatHeadersAtBottom();
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############",
                        "# h # h # h #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetRepeatHeadersAtBottomThenClearToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.repeatHeadersAtBottom();
        textTableBuilder.setRepeatHeadersAtBottom(false);
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
    public void testSetRepeatHeadersEveryXRowsAndRepeatAtBottomNoOverlapToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.addRow("g", "h", "i");
        textTableBuilder.setRepeatHeadersEveryXRows(2);
        textTableBuilder.repeatHeadersAtBottom();
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# g # h # i #",
                        "#############",
                        "# h # h # h #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetRepeatHeadersEveryXRowsAndRepeatAtBottomOverlapToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setRepeatHeadersEveryXRows(2);
        textTableBuilder.repeatHeadersAtBottom();
        assertEquals(
                "toString() did not produced expected value",
                // header should NOT repeat at bottom twice
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############",
                        "# h # h # h #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testShowRowNumsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.showRowNums();
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#################",
                        "#   # h # h # h #",
                        "#################",
                        "# 1 # a # b # c #",
                        "# 2 # d # e # f #",
                        "#################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testShowRowNumsRowNumAlignmentToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.showRowNums();
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "##################",
                        "#    # h # h # h #",
                        "##################",
                        "#  1 # a # b # c #",
                        "#  2 # a # b # c #",
                        "#  3 # a # b # c #",
                        "#  4 # a # b # c #",
                        "#  5 # a # b # c #",
                        "#  6 # a # b # c #",
                        "#  7 # a # b # c #",
                        "#  8 # a # b # c #",
                        "#  9 # a # b # c #",
                        "# 10 # a # b # c #",
                        "##################"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testShowRowThenClearNumsToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.showRowNums();
        textTableBuilder.setShowRowNums(false);
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
    public void testSetLinePrependerToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setLinePrepender(">");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        ">#############",
                        "># h # h # h #",
                        ">#############",
                        "># a # b # c #",
                        "># d # e # f #",
                        ">#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetLinePrependerThenClearToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setLinePrepender(">");
        textTableBuilder.setLinePrepender(null);
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
    public void testSetLineAppenderToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setLineAppender("<");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############<",
                        "# h # h # h #<",
                        "#############<",
                        "# a # b # c #<",
                        "# d # e # f #<",
                        "#############<"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetLineAppenderThenClearToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setLineAppender("<");
        textTableBuilder.setLineAppender(null);
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
    public void testDefaultNullColumnReplacementToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow(null, null, null);
        textTableBuilder.addRow("d", "e", "f");
        assertEquals(
                "toString() did not produced expected value",
                // nulls should be replaced with empty strings
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "#   #   #   #",
                        "# d # e # f #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetNullColumnReplacementToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow(null, null, null);
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setNullColumnReplacement("N");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "# N # N # N #",
                        "# d # e # f #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetNullColumnReplacementThenClearToString() {
        textTableBuilder.setHeaders("h", "h", "h");
        textTableBuilder.addRow(null, null, null);
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setNullColumnReplacement("N");
        textTableBuilder.setNullColumnReplacement(null);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# h # h # h #",
                        "#############",
                        "#   #   #   #",
                        "# d # e # f #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testSetNullColumnReplacementAppliesToHeadersToString() {
        textTableBuilder.setHeaders(null, null, null);
        textTableBuilder.addRow("a", "b", "c");
        textTableBuilder.addRow("d", "e", "f");
        textTableBuilder.setNullColumnReplacement("N");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "#############",
                        "# N # N # N #",
                        "#############",
                        "# a # b # c #",
                        "# d # e # f #",
                        "#############"
                ),
                textTableBuilder.toString()
        );
    }

    @Test
    public void testMegaToString() {
        textTableBuilder.setHeaders("h", "hh", "hhh", null);
        textTableBuilder.addRow("aaaa", null, "c", "dd");
        textTableBuilder.addRow("aaa", "b", "cccc", "dd");
        textTableBuilder.addRow("aa", "bb", "ccc", "dd");
        textTableBuilder.addRow("a", "bbb", "cc", "dd");
        textTableBuilder.addRow(null, "bbbb", "", "dd");
        textTableBuilder.addRow("a", "bbbb", "c", "dd");
        textTableBuilder.addRow("aa", "bbb", "cccc", "dd");
        textTableBuilder.addRow("aaa", "bb", "ccc", "dd");
        textTableBuilder.addRow("aaaa", "b", "cc", "dd");
        textTableBuilder.addRow(null, null, "", "dd");
        textTableBuilder.setHeaderAlignments(LEFT, LEFT, RIGHT, RIGHT);
        textTableBuilder.setColumnAlignments(LEFT, RIGHT, RIGHT, LEFT);
        textTableBuilder.setRepeatHeadersEveryXRows(3);
        textTableBuilder.setRepeatHeadersAtBottom(true);
        textTableBuilder.setShowRowNums(true);
        textTableBuilder.setLinePrepender(">");
        textTableBuilder.setLineAppender("<");
        textTableBuilder.setNullColumnReplacement("NULL");
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        ">##################################<",
                        ">#    # h    # hh   #  hhh # NULL #<",
                        ">##################################<",
                        ">#  1 # aaaa # NULL #    c # dd   #<",
                        ">#  2 # aaa  #    b # cccc # dd   #<",
                        ">#  3 # aa   #   bb #  ccc # dd   #<",
                        ">##################################<",
                        ">#    # h    # hh   #  hhh # NULL #<",
                        ">##################################<",
                        ">#  4 # a    #  bbb #   cc # dd   #<",
                        ">#  5 # NULL # bbbb #      # dd   #<",
                        ">#  6 # a    # bbbb #    c # dd   #<",
                        ">##################################<",
                        ">#    # h    # hh   #  hhh # NULL #<",
                        ">##################################<",
                        ">#  7 # aa   #  bbb # cccc # dd   #<",
                        ">#  8 # aaa  #   bb #  ccc # dd   #<",
                        ">#  9 # aaaa #    b #   cc # dd   #<",
                        ">##################################<",
                        ">#    # h    # hh   #  hhh # NULL #<",
                        ">##################################<",
                        "># 10 # NULL # NULL #      # dd   #<",
                        ">##################################<",
                        ">#    # h    # hh   #  hhh # NULL #<",
                        ">##################################<"
                ),
                textTableBuilder.toString()
        );
        textTableBuilder.clearHeaderAlignments();
        textTableBuilder.clearColumnAlignments();
        textTableBuilder.setRepeatHeadersEveryXRows(0);
        textTableBuilder.setRepeatHeadersAtBottom(false);
        textTableBuilder.setShowRowNums(false);
        textTableBuilder.setLinePrepender(null);
        textTableBuilder.setLineAppender(null);
        textTableBuilder.setNullColumnReplacement(null);
        textTableBuilder.setBoxDrawingCharacters(LIGHT);
        assertEquals(
                "toString() did not produced expected value",
                joinForTable(
                        "┌──────┬──────┬──────┬────┐",
                        "│ h    │ hh   │ hhh  │    │",
                        "├──────┼──────┼──────┼────┤",
                        "│ aaaa │      │ c    │ dd │",
                        "│ aaa  │ b    │ cccc │ dd │",
                        "│ aa   │ bb   │ ccc  │ dd │",
                        "│ a    │ bbb  │ cc   │ dd │",
                        "│      │ bbbb │      │ dd │",
                        "│ a    │ bbbb │ c    │ dd │",
                        "│ aa   │ bbb  │ cccc │ dd │",
                        "│ aaa  │ bb   │ ccc  │ dd │",
                        "│ aaaa │ b    │ cc   │ dd │",
                        "│      │      │      │ dd │",
                        "└──────┴──────┴──────┴────┘"
                ),
                textTableBuilder.toString()
        );
    }

}
