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

    private static final String GETTER_APPENDER_VALUE_NOT_EQUAL =
            "value returned from getter is not equal to that given to appender";

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

    private Alignment[] alignmentsArray;

    private List<Alignment> alignments;

    private String[] headersArray;

    private List<String> headers;

    private String[] row1Array;

    private List<String> row1;

    private String[] row2Array;

    private List<String> row2;

    private List<Collection<String>> allRows;

    @Before
    public void setUp() {
        textTableBuilder = new TextTableBuilder();
        alignmentsArray = new Alignment[]{Alignment.LEFT, Alignment.RIGHT};
        alignments = Arrays.asList(alignmentsArray);
        headersArray = new String[]{"0", "1"};
        headers = Arrays.asList(headersArray);
        row1Array = new String[] {"r1c1", "r1c2"};
        row1 = Arrays.asList(row1Array);
        row2Array = new String[] {"r2c1", "r2c2"};
        row2 = Arrays.asList(row2Array);
        allRows = new ArrayList<Collection<String>>();
        allRows.add(row1);
        allRows.add(row2);
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
    public void testAddRow() {
        textTableBuilder.addRow(row1);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row1,
                textTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowMultiple() {
        textTableBuilder.addRow(row1);
        textTableBuilder.addRow(row2);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                textTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowArray() {
        textTableBuilder.addRow(row1Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                row1,
                textTableBuilder.getRows().get(0)
        );
    }

    @Test
    public void testAddRowArrayMultiple() {
        textTableBuilder.addRow(row1Array);
        textTableBuilder.addRow(row2Array);
        assertEquals(
                GETTER_APPENDER_VALUE_NOT_EQUAL,
                allRows,
                textTableBuilder.getRows()
        );
    }

    @Test
    public void testAddRowForDefensiveCopy() {
        final String expected = row1.get(0);
        textTableBuilder.addRow(row1);
        row1.set(0, null);
        assertEquals(
                "APPENDER NO DEFENSIVE COPY",
                expected,
                textTableBuilder.getRows().get(0).get(0)
        );
    }

    @Test
    public void testAddRowArrayForDefensiveCopy() {
        final String expected = row1Array[0];
        textTableBuilder.addRow(row1Array);
        row1Array[0] = null;
        assertEquals(
                "APPENDER NO DEFENSIVE COPY",
                expected,
                textTableBuilder.getRows().get(0).get(0)
        );
    }

}
