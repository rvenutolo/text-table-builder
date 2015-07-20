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

    private TextTableBuilder textTableBuilder;

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
        textTableBuilder = new TextTableBuilder();
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
        textTableBuilder = null;
        alignmentsArray = null;
        alignments = null;
        headersArray = null;
        headers = null;
    }

    @Test
    public void testSetAndGetBoxDrawingCharacters() {
        for (final BoxDrawingCharacters boxDrawingCharacters : boxDrawingCharactersCollection) {
            textTableBuilder.setBoxDrawingCharacters(boxDrawingCharacters);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    boxDrawingCharacters,
                    textTableBuilder.getBoxDrawingCharacters()
            );
        }
    }

    @Test
    public void testSetAndGetHeaderAlignments() {
        textTableBuilder.setHeaderAlignments(alignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                textTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetArrayAndGetHeaderAlignmentsArray() {
        textTableBuilder.setHeaderAlignments(alignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                textTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        textTableBuilder.setHeaderAlignments(alignments);
        alignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testSetHeaderAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = alignmentsArray[0];
        textTableBuilder.setHeaderAlignments(alignmentsArray);
        alignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getHeaderAlignments().get(0)
        );
    }

    @Test
    public void testGetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        textTableBuilder.setHeaderAlignments(alignments);
        textTableBuilder.getHeaderAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getHeaderAlignments().get(0)
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
        textTableBuilder.setRowAlignments(alignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                textTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetArrayAndGetRowAlignmentsArray() {
        textTableBuilder.setRowAlignments(alignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                alignments,
                textTableBuilder.getRowAlignments()
        );
    }

    @Test
    public void testSetRowAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        textTableBuilder.setRowAlignments(alignments);
        alignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testSetRowAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = alignmentsArray[0];
        textTableBuilder.setRowAlignments(alignmentsArray);
        alignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRowAlignments().get(0)
        );
    }

    @Test
    public void testGetRowAlignmentsForDefensiveCopying() {
        final Alignment expected = alignments.get(0);
        textTableBuilder.setRowAlignments(alignments);
        textTableBuilder.getRowAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRowAlignments().get(0)
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
        textTableBuilder.setHeaders(headers);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headers,
                textTableBuilder.getHeaders()
        );
    }

    @Test
    public void testSetAndGetHeadersArray() {
        textTableBuilder.setHeaders(headersArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headers,
                textTableBuilder.getHeaders()
        );
    }

    @Test
    public void testSetHeadersForDefensiveCopying() {
        final String expected = headers.get(0);
        textTableBuilder.setHeaders(headers);
        headers.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getHeaders().get(0)
        );
    }

    @Test
    public void testSetHeadersArrayForDefensiveCopying() {
        final String expected = headersArray[0];
        textTableBuilder.setHeaders(headersArray);
        headersArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getHeaders().get(0)
        );
    }

    @Test
    public void testGetHeadersForDefensiveCopying() {
        final String expected = headers.get(0);
        textTableBuilder.setHeaders(headers);
        textTableBuilder.getHeaders().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getHeaders().get(0)
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
        textTableBuilder.addRow(row0);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0,
                textTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowMultiple() {
        textTableBuilder.addRow(row0);
        textTableBuilder.addRow(row1);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                textTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowArray() {
        textTableBuilder.addRow(row0Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row0,
                textTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowArrayMultiple() {
        textTableBuilder.addRow(row0Array);
        textTableBuilder.addRow(row1Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                textTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowForDefensiveCopy() {
        final String expected = row0.get(0);
        textTableBuilder.addRow(row0);
        row0.set(0, null);
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRows().get(0).get(0)
        );
    }

    @Test
    public void testAddRowArrayForDefensiveCopy() {
        final String expected = row0Array[0];
        textTableBuilder.addRow(row0Array);
        row0Array[0] = null;
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRows().get(0).get(0)
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
        textTableBuilder.addRows(allRows);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                textTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsMultiple() {
        final Collection<Collection<String>> expected = new ArrayList<Collection<String>>();
        expected.addAll(allRows);
        expected.addAll(allRows);
        textTableBuilder.addRows(allRows);
        textTableBuilder.addRows(allRows);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                expected,
                textTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowsForDefensiveCopy() {
        final Collection<String> expected = allRows.get(0);
        textTableBuilder.addRows(allRows);
        allRows.set(0, Arrays.<String>asList(null, null));
        assertEquals(
                APPENDER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRows().get(0)
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
        textTableBuilder.setRows(allRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                allRows,
                textTableBuilder.getRows()
        );
    }

    @Test
    public void testSetRowsForDefensiveCopying() {
        final Collection<String> expected = allRows.get(0);
        textTableBuilder.setRows(allRows);
        allRows.set(0, Arrays.<String>asList(null, null));
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testGetRowsForDefensiveCopying() {
        final Collection<String> expected = allRows.get(0);
        textTableBuilder.setRows(allRows);
        textTableBuilder.getRows().set(0, Arrays.<String>asList(null, null));
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                textTableBuilder.getRows().get(0)
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

}
