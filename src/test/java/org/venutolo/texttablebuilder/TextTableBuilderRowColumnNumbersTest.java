package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderRowColumnNumbersTest {

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .addRow("ROW");
    }

    /*========================================================================
     * TESTS FOR getNumRows()
     *========================================================================*/

    @Test
    public void testGetNumRows() {
        assertEquals(
                "Incorrect number of rows",
                emptyTextTableBuilder.getTable().size(),
                emptyTextTableBuilder.getNumRows()
        );
        assertEquals(
                "Incorrect number of rows",
                populatedTextTableBuilder.getTable().size(),
                populatedTextTableBuilder.getNumRows()
        );
    }

    /*========================================================================
     * TESTS FOR getNumColumns()
     *========================================================================*/

    @Test
    public void testGetNumColumns() {
        assertEquals(
                "Incorrect number of columns",
                emptyTextTableBuilder.getTable().size(),
                emptyTextTableBuilder.getNumColumns()
        );
        assertEquals(
                "Incorrect number of columns",
                populatedTextTableBuilder.getTable().size(),
                populatedTextTableBuilder.getNumColumns()
        );
    }

}
