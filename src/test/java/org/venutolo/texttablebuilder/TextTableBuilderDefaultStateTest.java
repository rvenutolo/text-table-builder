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
    public void testDefaultStateRepeatHeaders() {
        assertEquals(
                "Default state repeat headers is incorrect",
                0,
                emptyTextTableBuilder.getRepeatHeaders()
        );
    }

    @Test
    public void testDefaultStateRepeateHeadersAtBottom() {
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
    public void testDefaultStateNullReplacement() {
        assertTrue(
                "Default state null replacements is not empty",
                emptyTextTableBuilder.getNullReplacement().isEmpty()
        );
    }

    @Test
    public void testDefaultStatePrepender() {
        assertTrue(
                "Default state prepender is not empty",
                emptyTextTableBuilder.getPrepender().isEmpty()
        );
    }

    @Test
    public void testDefaultStateAppender() {
        assertTrue(
                "Default state appender is not empty",
                emptyTextTableBuilder.getAppender().isEmpty()
        );
    }

}
