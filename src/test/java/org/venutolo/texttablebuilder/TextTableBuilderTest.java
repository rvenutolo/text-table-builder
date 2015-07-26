package org.venutolo.texttablebuilder;

import org.junit.After;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderTest {

    private static final String GETTER_SETTER_VALUE_NOT_EQUAL =
            "value returned from getter is not equal to that given to setter";

    private static final String SETTER_NO_DEFENSIVE_COPY =
            "setter did not create a defensive copy";

    private static final String GETTER_NO_DEFENSIVE_COPY =
            "getter did not create a defensive copy";

    private static final String APPENDER_NO_DEFENSIVE_COPY =
            "appender did not create a defensive copy";

    private static final String GETTER_APPENDER_VALUE_NOT_EQUAL =
            "value returned from getter is not equal to that given to appender";

    private static final String BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING =
            "expected 2 columns";

    private static final String EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH =
            "Expected IAE for wrong column length";

    private static final String WIDTH_LESS_THAN_0 =
            "Width is less than 0";

    private static final String EXPECTED_IAE_FOR_BAD_WIDTH =
            "Expected IAE for bad width";

    private static final String CANNOT_SET_COLUMN_WIDTH =
            "Cannot set column width";

    private static final String EXPECTED_ISE_FOR_SET_BEFORE_DEFINED =
            "Expected ISE for attempting to set column width before column has been defined";

    private final Collection<BoxDrawingCharacters> boxDrawingCharactersCollection =
            Collections.unmodifiableCollection(
                    Arrays.asList(
                            BoxDrawingCharacters.ASCII,
                            BoxDrawingCharacters.CURVED,
                            BoxDrawingCharacters.DOUBLE,
                            BoxDrawingCharacters.HEAVY,
                            BoxDrawingCharacters.LIGHT,
                            BoxDrawingCharacters.NULLS,
                            BoxDrawingCharacters.SPACES
                    )
            );

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    private Alignment[] alignmentsArray;

    private List<Alignment> alignments;

    private String[] headersArray;

    private List<String> headers;

    private String[] row0Array;

    private List<String> row0;

    private String[] row1Array;

    private List<String> row1;

    private List<Collection<String>> allRows;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
        alignmentsArray = new Alignment[]{Alignment.LEFT, Alignment.RIGHT};
        alignments = Arrays.asList(alignmentsArray);
        headersArray = new String[]{"0", "1"};
        headers = Arrays.asList(headersArray);
        row0Array = new String[]{"r0c0", "r0c1"};
        row0 = Arrays.asList(row0Array);
        row1Array = new String[]{"r1c0", "r1c1"};
        row1 = Arrays.asList(row1Array);
        allRows = new ArrayList<Collection<String>>();
        allRows.add(row0);
        allRows.add(row1);
        populatedTextTableBuilder = new TextTableBuilder()
                .setHeaderAlignments(alignments)
                .setHeaders(headers)
                .setRowAlignments(alignments)
                .setRows(allRows);
    }

    @After
    public void tearDown() {
        emptyTextTableBuilder = null;
        alignmentsArray = null;
        alignments = null;
        headersArray = null;
        headers = null;
    }

    @Test
    public void testSetAndGetBoxDrawingCharacters() {
        for (final BoxDrawingCharacters boxDrawingCharacters : boxDrawingCharactersCollection) {
            emptyTextTableBuilder.setBoxDrawingCharacters(boxDrawingCharacters);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    boxDrawingCharacters,
                    emptyTextTableBuilder.getBoxDrawingCharacters()
            );
        }
    }

    @Test
    public void testSetAndGetHeaderAlignments() {
        emptyTextTableBuilder.setHeaderAlignments(alignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetArrayAndGetHeaderAlignmentsArray() {
        emptyTextTableBuilder.setHeaderAlignments(alignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        emptyTextTableBuilder.setHeaderAlignments(alignments);
        alignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testSetHeaderAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = alignmentsArray[0];
        emptyTextTableBuilder.setHeaderAlignments(alignmentsArray);
        alignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testGetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        emptyTextTableBuilder.setHeaderAlignments(alignments);
        emptyTextTableBuilder.getHeaderAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testSetHeaderAlignmentsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignments(Collections.<Alignment>emptyList());
    }

    @Test
    public void testSetHeaderAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignments();
    }

    @Test
    public void testSetAndGetRowAlignments() {
        emptyTextTableBuilder.setRowAlignments(alignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                emptyTextTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetArrayAndGetRowAlignmentsArray() {
        emptyTextTableBuilder.setRowAlignments(alignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                emptyTextTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetRowAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        emptyTextTableBuilder.setRowAlignments(alignments);
        alignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testSetRowAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = alignmentsArray[0];
        emptyTextTableBuilder.setRowAlignments(alignmentsArray);
        alignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testGetRowAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        emptyTextTableBuilder.setRowAlignments(alignments);
        emptyTextTableBuilder.getRowAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testSetRowAlignmentsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setRowAlignments(Collections.<Alignment>emptyList());
    }

    @Test
    public void testSetRowAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setRowAlignments();
    }

    @Test
    public void testSetAndGetHeaders() {
        emptyTextTableBuilder.setHeaders(headers);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headers,
                emptyTextTableBuilder.getHeaders()
        );
    }

    @Test
    public void testSetAndGetHeadersArray() {
        emptyTextTableBuilder.setHeaders(headersArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headers,
                emptyTextTableBuilder.getHeaders()
        );
    }

    @Test
    public void testSetHeadersForDefensiveCopying() {
        final String expected = headers.get(0);
        emptyTextTableBuilder.setHeaders(headers);
        headers.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaders().get(0)
        );
    }

    @Test
    public void testSetHeadersArrayForDefensiveCopying() {
        final String expected = headersArray[0];
        emptyTextTableBuilder.setHeaders(headersArray);
        headersArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaders().get(0)
        );
    }

    @Test
    public void testGetHeadersForDefensiveCopying() {
        final String expected = headers.get(0);
        emptyTextTableBuilder.setHeaders(headers);
        emptyTextTableBuilder.getHeaders().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaders().get(0)
        );
    }

    @Test
    public void testSetHeadersForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaders(Collections.<String>emptyList());
    }

    @Test
    public void testSetHeadersArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaders();
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
        emptyTextTableBuilder.addRow(row0Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0,
                emptyTextTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowArrayMultiple() {
        emptyTextTableBuilder.addRow(row0Array);
        emptyTextTableBuilder.addRow(row1Array);
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
        emptyTextTableBuilder.addRow(row0Array);
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
        populatedTextTableBuilder.addRow();
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
    public void testSetAndGetRows() {
        emptyTextTableBuilder.setRows(allRows);
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
    public void testGetRowsForDefensiveCopying() {
        final Collection<String> expected = allRows.get(0);
        emptyTextTableBuilder.setRows(allRows);
        emptyTextTableBuilder.getRows().set(0, Arrays.<String>asList(null, null));
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
    public void testSetAndGetShowRowNums() {
        for (final boolean showRowNums : new Boolean[] {true, false}) {
            emptyTextTableBuilder.setShowRowNums(showRowNums);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    showRowNums,
                    emptyTextTableBuilder.getShowRowNums()
            );
        }
    }

    @Test
    public void testClearRows() {
        populatedTextTableBuilder.clearRows();
        assertTrue(
                "Rows not empty after clearRows()",
                populatedTextTableBuilder.getRows().isEmpty()
        );
    }

    @Test
    public void testClearHeaders() {
        populatedTextTableBuilder.clearHeaders();
        assertTrue(
                "Headers not empty after clearHeaders()",
                populatedTextTableBuilder.getHeaders().isEmpty()
        );
    }

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
    public void testSetAndGetRepeatHeaders() {
        final int numRows = 10;
        emptyTextTableBuilder.repeatHeaders(numRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                numRows,
                emptyTextTableBuilder.getRepeatHeaders()
        );
    }

    @Test
    public void testSetAndGetPrependerString(){
        final String prependerString = "prepender";
        emptyTextTableBuilder.setPrependerString(prependerString);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                prependerString,
                emptyTextTableBuilder.getPrependerString()
        );
    }

    @Test
    public void testSetAndGetAppenderString() {
        final String appenderString = "appender";
        emptyTextTableBuilder.setAppenderString(appenderString);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                appenderString,
                emptyTextTableBuilder.getAppenderString()
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

    @Test
    public void testSetAndGetMinColumnWidth() {
        for (int i = 0; i < populatedTextTableBuilder.getNumColumns(); i++) {
            populatedTextTableBuilder.setColumnMinWidth(i, i);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    (Integer) i,
                    populatedTextTableBuilder.getColumnMinWidth(i)
            );
        }
    }

    @Test
    public void testSetAndGetMaxColumnWidth() {
        for (int i = 0; i < populatedTextTableBuilder.getNumColumns(); i++) {
            populatedTextTableBuilder.setColumnMaxWidth(i, i);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    (Integer) i,
                    populatedTextTableBuilder.getColumnMaxWidth(i)
            );
        }
    }

    @Test
    public void testSetColumnWidth() {
        for (int i = 0; i < populatedTextTableBuilder.getNumColumns(); i++) {
            populatedTextTableBuilder.setColumnWidth(i, i);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    (Integer) i,
                    populatedTextTableBuilder.getColumnMinWidth(i)
            );
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    (Integer) i,
                    populatedTextTableBuilder.getColumnMaxWidth(i)
            );
        }
    }

    @Test
    public void testGetColumnMinWidthNotSet() {
        assertNull(
                "Min width should be null when it has not been set",
                populatedTextTableBuilder.getColumnMinWidth(0)
        );
    }

    @Test
    public void testGetColumnMaxWidthNotSet() {
        assertNull(
                "Max width should be null when it has not been set",
                populatedTextTableBuilder.getColumnMaxWidth(0)
        );
    }

    @Test
    public void testSetColumnMinWidthBadValue() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(WIDTH_LESS_THAN_0);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_WIDTH);
        populatedTextTableBuilder.setColumnMinWidth(0, -1);
    }

    @Test
    public void testSetColumnMaxWidthBadValue() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(WIDTH_LESS_THAN_0);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_WIDTH);
        populatedTextTableBuilder.setColumnMaxWidth(0, -1);
    }

    @Test
    public void testSetColumnWidthBadValue() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(WIDTH_LESS_THAN_0);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_WIDTH);
        populatedTextTableBuilder.setColumnWidth(0, -1);
    }

    @Test
    public void testSetColumnMinWidthBeforeNumColumnsDefined() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(CANNOT_SET_COLUMN_WIDTH);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_ISE_FOR_SET_BEFORE_DEFINED);
        emptyTextTableBuilder.setColumnMinWidth(0, 0);
    }

    @Test
    public void testSetColumnMaxWidthBeforeNumColumnsDefined() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(CANNOT_SET_COLUMN_WIDTH);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_ISE_FOR_SET_BEFORE_DEFINED);
        emptyTextTableBuilder.setColumnMaxWidth(0, 0);
    }

    @Test
    public void testSetColumnWidthBeforeNumColumnsDefined() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(CANNOT_SET_COLUMN_WIDTH);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_ISE_FOR_SET_BEFORE_DEFINED);
        emptyTextTableBuilder.setColumnWidth(0, 0);
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
