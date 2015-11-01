package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.venutolo.texttablebuilder.TestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_NPE_FOR_NULL_LIST;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EMPTY_AFTER_CLEAR;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderRowsTest {

    private static final String APPENDER_NO_DEFENSIVE_COPY =
            "appender did not create a defensive copy";

    private static final String GETTER_APPENDER_VALUE_NOT_EQUAL =
            "value returned from getter is not equal to that given to appender";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private String[] row0Array;

    private List<String> row0List;

    private String[] row1Array;

    private List<String> row1List;

    private List<List<String>> allRows;

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        row0Array = new String[]{"r0c0", "r0c1"};
        row0List = Arrays.asList(row0Array);
        row1Array = new String[]{"r1c0", "r1c1"};
        row1List = Arrays.asList(row1Array);
        allRows = new ArrayList<List<String>>();
        allRows.add(row0List);
        allRows.add(row1List);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .addRow((Object[]) row0Array)
                .addRow((Object[]) row1Array);
    }

    /*========================================================================
     * TESTS FOR getRows()
     *========================================================================*/

    @Test
    public void testGetRowsWhenNotSet() {
        final List<List<Object>> emptyRows = emptyTextTableBuilder.getRows();
        assertNotNull(
                "rows should not be null",
                emptyRows
        );
        assertTrue(
                "rows should be empty",
                emptyRows.isEmpty()
        );
    }

    @Test
    public void testGetRowsForDefensiveCopying() {
        final List<String> rowExpected = allRows.get(0);
        populatedTextTableBuilder.getRows().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                rowExpected,
                populatedTextTableBuilder.getRows().get(0)
        );
        final String columnExpected = rowExpected.get(0);
        populatedTextTableBuilder.getRows().get(0).set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                columnExpected,
                populatedTextTableBuilder.getRows().get(0).get(0)
        );
    }

    /*========================================================================
     * TESTS FOR addRow(Collection<?>)
     *========================================================================*/

    @Test
    public void testAddRow() {
        emptyTextTableBuilder.addRow(row0List);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0List,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowMultiple() {
        emptyTextTableBuilder.addRow(row0List);
        emptyTextTableBuilder.addRow(row1List);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRow((Collection<?>) null);
    }

    @Test
    public void testAddRowForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRow(Collections.<String>emptyList());
    }

    @Test
    public void testAddRowForDefensiveCopy() {
        final String expected = row0List.get(0);
        emptyTextTableBuilder.addRow(row0List);
        row0List.set(0, null);
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0).get(0)
        );
    }

    /*========================================================================
     * TESTS FOR addRow(Object...)
     *========================================================================*/

    @Test
    public void testAddRowArray() {
        emptyTextTableBuilder.addRow((Object[]) row0Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0List,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowArrayMultiple() {
        emptyTextTableBuilder.addRow((Object[]) row0Array);
        emptyTextTableBuilder.addRow((Object[]) row1Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowArrayForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRow((Object[]) null);
    }

    @Test
    public void testAddRowArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRow();
    }

    @Test
    public void testAddRowArrayForDefensiveCopy() {
        final String expected = row0Array[0];
        emptyTextTableBuilder.addRow((Object[]) row0Array);
        row0Array[0] = null;
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0).get(0)
        );
    }

    /*========================================================================
     * TESTS FOR clearRows()
     *========================================================================*/

    @Test
    public void testClearRows() {
        populatedTextTableBuilder.clearRows();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getRows().isEmpty()
        );
    }

}
