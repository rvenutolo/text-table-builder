package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderDefaultStateTest {

    private TextTableBuilder emptyTextTableBuilder;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
    }

    @Test
    public void testDefaultStateNumColumns() {
        assertEquals(
                "Default state num columns is incorrect",
                0,
                emptyTextTableBuilder.getNumColumns()
        );
    }

    @Test
    public void testDefaultStateNumRows() {
        assertEquals(
                "Default state num rows is incorrect",
                0,
                emptyTextTableBuilder.getNumRows()
        );
    }

    @Test
    public void testDefaultStateBoxDrawingCharacters() {
        assertEquals(
                "Default state box drawing characters is incorrect",
                BoxDrawingCharacters.LIGHT,
                emptyTextTableBuilder.getBoxDrawingCharacters()
        );
    }

    @Test
    public void testDefaultStateHeaders() {
        assertTrue(
                "Default state headers are not empty",
                emptyTextTableBuilder.getHeaders().isEmpty()
        );
    }

    @Test
    public void testDefaultStateHeaderAlignments() {
        assertTrue(
                "Default state header alignments are not empty",
                emptyTextTableBuilder.getHeaderAlignments().isEmpty()
        );
    }

    @Test
    public void testDefaultStateRows() {
        assertTrue(
                "Default state rows are not empty",
                emptyTextTableBuilder.getRows().isEmpty()
        );
    }

    @Test
    public void testDefaultStateRowAlignments() {
        assertTrue(
                "Default state row alignments are not empty",
                emptyTextTableBuilder.getRowAlignments().isEmpty()
        );
    }

    @Test
    public void testDefaultStateRepeatHeadersEveryXRows() {
        assertEquals(
                "Default state repeat headers every x rows is incorrect",
                0,
                emptyTextTableBuilder.getRepeatHeadersEveryXRows()
        );
    }

    @Test
    public void testDefaultStateRepeatHeadersAtBottom() {
        assertFalse(
                "Default state repeat headers at bottom is incorrect",
                emptyTextTableBuilder.getRepeatHeadersAtBottom()
        );
    }

    @Test
    public void testDefaultStateShowRowNums() {
        assertFalse(
                "Default state show row nums is incorrect",
                emptyTextTableBuilder.getShowRowNums()
        );
    }

    @Test
    public void testDefaultStateNullColumnReplacement() {
        assertTrue(
                "Default state null column replacements is not empty",
                emptyTextTableBuilder.getNullColumnReplacement().isEmpty()
        );
    }

    @Test
    public void testDefaultStateLinePrepender() {
        assertTrue(
                "Default state line prepender is not empty",
                emptyTextTableBuilder.getLinePrepender().isEmpty()
        );
    }

    @Test
    public void testDefaultStateLineAppender() {
        assertTrue(
                "Default state line appender is not empty",
                emptyTextTableBuilder.getLineAppender().isEmpty()
        );
    }

}
