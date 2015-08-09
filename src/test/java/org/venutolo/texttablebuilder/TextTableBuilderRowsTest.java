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
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.EXPECTED_NPE_FOR_NULL_LIST;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.SETTER_NO_DEFENSIVE_COPY;

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

    private List<String> row0;

    private String[] row1Array;

    private List<String> row1;

    private List<?>[] allRowsArray;

    private List<List<String>> allRows;

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

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
                .setRowsList(allRows);
    }

    @Test
    public void testAddRowList() {
        emptyTextTableBuilder.addRowList(row0);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowArray() {
        emptyTextTableBuilder.addRow((Object[]) row0Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowListMultiple() {
        emptyTextTableBuilder.addRowList(row0);
        emptyTextTableBuilder.addRowList(row1);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
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
    public void testAddRowListForDefensiveCopy() {
        final String expected = row0.get(0);
        emptyTextTableBuilder.addRowList(row0);
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
        emptyTextTableBuilder.addRow((Object[]) row0Array);
        row0Array[0] = null;
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0).get(0)
        );
    }

    @Test
    public void testAddRowListForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRowList(Collections.<String>emptyList());
    }

    @Test
    public void testAddRowArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRow();
    }

    @Test
    public void testAddRowListListForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRowList(null);
    }

    @Test
    public void testAddRowArrayForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRow((Object[]) null);
    }

    @Test
    public void testAddRowsList() {
        emptyTextTableBuilder.addRowsList(allRows);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsArray() {
        emptyTextTableBuilder.addRows(allRows.toArray(new List<?>[allRows.size()]));
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsListMultiple() {
        final Collection<Collection<String>> expected = new ArrayList<Collection<String>>();
        expected.addAll(allRows);
        expected.addAll(allRows);
        emptyTextTableBuilder.addRowsList(allRows);
        emptyTextTableBuilder.addRowsList(allRows);
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
        emptyTextTableBuilder.addRows(allRowsArray);
        emptyTextTableBuilder.addRows(allRowsArray);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                expected,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsListForDefensiveCopy() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.addRowsList(allRows);
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
        emptyTextTableBuilder.addRows(allRowsArray);
        allRowsArray[0] = Arrays.<String>asList(null, null);
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowsListForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.addRowsList(
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
        populatedTextTableBuilder.addRows(
                Collections.<String>emptyList()
        );
    }

    @Test
    public void testAddRowsListListForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRowsList(null);
    }

    @Test
    public void testAddRowsArrayForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRows((Collection<?>) null);
    }

    @Test
    public void testAddRowsListListForNullRow() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRowsList(Collections.<Collection<?>>singletonList(null));
    }

    @Test
    public void testAddRowsArrayForNullRow() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.addRows(new Collection<?>[]{null});
    }

    @Test
    public void testSetAndGetRowsList() {
        emptyTextTableBuilder.setRowsList(allRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testSetAndGetRowsArray() {
        emptyTextTableBuilder.setRows(allRowsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                allRows,
                emptyTextTableBuilder.getRows()
        );
    }

    @Test
    public void testSetRowsListForDefensiveCopying() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.setRowsList(allRows);
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
        emptyTextTableBuilder.setRows(allRowsArray);
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
        emptyTextTableBuilder.setRowsList(allRows);
        emptyTextTableBuilder.getRows().set(0, Arrays.asList(null, null));
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testSetRowsListForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        allRows.add(Collections.<String>emptyList());
        populatedTextTableBuilder.setRowsList(allRows);
    }

    @Test
    public void testSetRowsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        allRowsArray[1] = Collections.<String>emptyList();
        populatedTextTableBuilder.setRows(allRowsArray);
    }

    @Test
    public void testSetRowsListListForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setRowsList(null);
    }

    @Test
    public void testSetRowsArrayForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setRows((Collection<?>) null);
    }

    @Test
    public void testSetRowsListListForNullRow() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setRowsList(Collections.<Collection<?>>singletonList(null));
    }

    @Test
    public void testSetRowsArrayForNullRow() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setRows(new Collection<?>[]{null});
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
