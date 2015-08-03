package org.venutolo.texttablebuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderRowColumnNumbersTest extends TextTableBuilderTest {

    @Test
    public void testGetNumRows() {
        assertEquals(
                "Incorrect number of rows",
                emptyTextTableBuilder.getRows().size(),
                emptyTextTableBuilder.getNumRows()
        );
        assertEquals(
                "Incorrect number of rows",
                populatedTextTableBuilder.getRows().size(),
                populatedTextTableBuilder.getNumRows()
        );
    }

    @Test
    public void testGetNumColumns() {
        assertEquals(
                "Incorrect number of columns",
                emptyTextTableBuilder.getRows().size(),
                emptyTextTableBuilder.getNumColumns()
        );
        assertEquals(
                "Incorrect number of columns",
                populatedTextTableBuilder.getRows().size(),
                populatedTextTableBuilder.getNumColumns()
        );
    }

}
