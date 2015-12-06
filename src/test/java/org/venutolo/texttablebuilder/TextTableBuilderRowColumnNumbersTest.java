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
    public void testGetNumRowsOnEmptyTable() {
        assertEquals(
                "Incorrect number of rows",
                emptyTextTableBuilder.getRows().size(),
                emptyTextTableBuilder.getNumRows()
        );
    }

    @Test
    public void testGetNumRowsOnPopulatedTable() {
        assertEquals(
                "Incorrect number of rows",
                populatedTextTableBuilder.getRows().size(),
                populatedTextTableBuilder.getNumRows()
        );
    }

    /*========================================================================
     * TESTS FOR getNumColumns()
     *========================================================================*/

    @Test
    public void testGetNumColumnsOnEmptyTable() {
        assertEquals(
                "Incorrect number of columns",
                emptyTextTableBuilder.getRows().size(),
                emptyTextTableBuilder.getNumColumns()
        );
    }

    @Test
    public void testGetNumColumnsOnPopulatedTable() {
        assertEquals(
                "Incorrect number of columns",
                populatedTextTableBuilder.getRows().size(),
                populatedTextTableBuilder.getNumColumns()
        );
    }

}
