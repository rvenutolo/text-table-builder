package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderOptionsTest {

    private static final boolean[] trueFalseArray = {true, false};

    private TextTableBuilder emptyTextTableBuilder;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
    }

    @Test
    public void testSetAndGetShowRowNums() {
        for (final boolean showRowNums : trueFalseArray) {
            emptyTextTableBuilder.setShowRowNums(showRowNums);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    showRowNums,
                    emptyTextTableBuilder.getShowRowNums()
            );
        }
    }

    @Test
    public void testShowRowNums() {
        emptyTextTableBuilder.showRowNums();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getShowRowNums()
        );
    }

    @Test
    public void testSetAndGetRepeatHeadersEveryXRows() {
        final int numRows = 10;
        emptyTextTableBuilder.setRepeatHeadersEveryXRows(numRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                numRows,
                emptyTextTableBuilder.getRepeatHeadersEveryXRows()
        );
    }

    @Test
    public void testSetAndGetRepeatHeadersAtBottom() {
        for (final boolean repeatHeadersAtBottom : trueFalseArray) {
            emptyTextTableBuilder.setRepeatHeadersAtBottom(repeatHeadersAtBottom);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    repeatHeadersAtBottom,
                    emptyTextTableBuilder.getRepeatHeadersAtBottom()
            );
        }
    }

    @Test
    public void testRepeatHeadersAtBottom() {
        emptyTextTableBuilder.repeatHeadersAtBottom();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getRepeatHeadersAtBottom()
        );
    }

    @Test
    public void testSetAndGetLinePrepender() {
        final String prepender = "prepender";
        emptyTextTableBuilder.setLinePrepender(prepender);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                prepender,
                emptyTextTableBuilder.getLinePrepender()
        );
    }

    @Test
    public void testSetAndGetLineAppender() {
        final String appender = "appender";
        emptyTextTableBuilder.setLineAppender(appender);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                appender,
                emptyTextTableBuilder.getLineAppender()
        );
    }

    @Test
    public void testSetAndGetNullColumnReplacement() {
        final String replacement = "replacement";
        emptyTextTableBuilder.setNullColumnReplacement(replacement);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                replacement,
                emptyTextTableBuilder.getNullColumnReplacement()
        );
    }

}
