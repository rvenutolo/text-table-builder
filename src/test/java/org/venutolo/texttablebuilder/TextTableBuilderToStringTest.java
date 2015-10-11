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
        // should just be the four corner characters
        final String expected = joinForTable(
                "##",
                "##"
        );
        assertEquals(
                "toString() did not produced expected value",
                expected,
                textTableBuilder.toString()
        );
    }

}
