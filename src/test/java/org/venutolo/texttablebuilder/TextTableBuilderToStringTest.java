package org.venutolo.texttablebuilder;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
