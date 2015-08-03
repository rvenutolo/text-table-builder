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
import static org.junit.Assert.assertTrue;
import static org.venutolo.texttablebuilder.TestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TestStrings.SETTER_NO_DEFENSIVE_COPY;

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

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    private String[] row0Array;

    private List<String> row0;

    private String[] row1Array;

    private List<String> row1;

    private List<?>[] allRowsArray;

    private ArrayList<List<String>> allRows;

    @Before
    public void setUp() {
        row0Array = new String[]{"r0c0", "r0c1"};
        row0 = Arrays.asList(row0Array);
        row1Array = new String[]{"r1c0", "r1c1"};
        row1 = Arrays.asList(row1Array);
        allRowsArray = new List<?>[2];
        allRows = new ArrayList<List<String>>();
        allRows.add(row0);
        allRows.add(row1);
        allRowsArray = allRows.toArray(new List<?>[allRows.size()]);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setRows(allRows);
    }

    @Test
    public void testAddRow() {
        emptyTextTableBuilder.addRow(row0);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowMultiple() {
        emptyTextTableBuilder.addRow(row0);
        emptyTextTableBuilder.addRow(row1);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowArray() {
        emptyTextTableBuilder.addRowFromArray((Object[]) row0Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowArrayMultiple() {
        emptyTextTableBuilder.addRowFromArray((Object[]) row0Array);
        emptyTextTableBuilder.addRowFromArray((Object[]) row1Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowForDefensiveCopy() {
        final String expected = row0.get(0);
        emptyTextTableBuilder.addRow(row0);
        row0.set(0, null);
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0).get(0)
        );
    }

    @Test
    public void testAddRowArrayForDefensiveCopy() {
        final String expected = row0Array[0];
        emptyTextTableBuilder.addRowFromArray((Object[]) row0Array);
        row0Array[0] = null;
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0).get(0)
        );
    }

    @Test
    public void testAddRowForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRow(Collections.<String>emptyList());
    }

    @Test
    public void testAddRowArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRowFromArray();
    }

    @Test
    public void testAddRows() {
        emptyTextTableBuilder.addRows(allRows);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsArray() {
        emptyTextTableBuilder.addRowsFromArray(allRows.toArray(new List<?>[allRows.size()]));
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsMultiple() {
        final Collection<Collection<String>> expected = new ArrayList<Collection<String>>();
        expected.addAll(allRows);
        expected.addAll(allRows);
        emptyTextTableBuilder.addRows(allRows);
        emptyTextTableBuilder.addRows(allRows);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                expected,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsArrayMultiple() {
        final Collection<Collection<String>> expected = new ArrayList<Collection<String>>();
        expected.addAll(allRows);
        expected.addAll(allRows);
        emptyTextTableBuilder.addRowsFromArray(allRowsArray);
        emptyTextTableBuilder.addRowsFromArray(allRowsArray);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                expected,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsForDefensiveCopy() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.addRows(allRows);
        allRows.set(0, Arrays.<String>asList(null, null));
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowsArrayForDefensiveCopy() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.addRowsFromArray(allRowsArray);
        allRowsArray[0] = Arrays.<String>asList(null, null);
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRows(
                Collections.<Collection<String>>singletonList(
                        Collections.<String>emptyList()
                )
        );
    }

    @Test
    public void testAddRowsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRowsFromArray(
                Collections.<String>emptyList()
        );
    }

    @Test
    public void testSetAndGetRows() {
        emptyTextTableBuilder.setRows(allRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testSetArrayAndGetRows() {
        emptyTextTableBuilder.setRowsFromArray(allRowsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testSetRowsForDefensiveCopying() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.setRows(allRows);
        allRows.set(0, Arrays.<String>asList(null, null));
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testSetRowsArrayForDefensiveCopying() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.setRowsFromArray(allRowsArray);
        allRowsArray[0] = Arrays.<String>asList(null, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testGetRowsForDefensiveCopying() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.setRows(allRows);
        emptyTextTableBuilder.getRows().set(0, Arrays.asList(null, null));
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testSetRowsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        allRows.add(Collections.<String>emptyList());
        populatedTextTableBuilder.setRows(allRows);
    }

    @Test
    public void testSetRowsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        allRowsArray[1] = Collections.<String>emptyList();
        populatedTextTableBuilder.setRowsFromArray(allRowsArray);
    }

    @Test
    public void testClearRows() {
        populatedTextTableBuilder.clearRows();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getRows().isEmpty()
        );
    }

    @Test
    public void testRemoveRow() {
        populatedTextTableBuilder.removeRow(0);
        assertEquals(
                "After removing first row, previous second row should now be first now",
                row1,
                populatedTextTableBuilder.getRows().get(0)
        );
    }

}
