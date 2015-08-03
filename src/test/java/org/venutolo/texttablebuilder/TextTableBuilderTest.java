package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderTest {

    protected static final String GETTER_SETTER_VALUE_NOT_EQUAL =
            "value returned from getter is not equal to that given to setter";

    protected static final String SETTER_NO_DEFENSIVE_COPY =
            "setter did not create a defensive copy";

    protected static final String GETTER_NO_DEFENSIVE_COPY =
            "getter did not create a defensive copy";

    protected static final String BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING =
            "expected 2 columns";

    protected static final String EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH =
            "expected IAE for wrong column length";

    protected static final String NOT_EMPTY_AFTER_CLEAR =
            "not empty after clearing";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    protected TextTableBuilder emptyTextTableBuilder;

    protected TextTableBuilder populatedTextTableBuilder;

    protected Alignment[] headerAlignmentsArray;

    protected List<Alignment> headerAlignments;

    protected String[] headersArray;

    protected List<String> headers;

    protected Alignment[] rowAlignmentsArray;

    protected List<Alignment> rowAlignments;

    protected String[] row0Array;

    protected List<String> row0;

    protected String[] row1Array;

    protected List<String> row1;

    protected List<?>[] allRowsArray;

    protected ArrayList<List<String>> allRows;

    protected String prepender = "PREPENDER";

    protected String appender = "APPENDER";

    @Before
    public void setUp() {
        headerAlignmentsArray = new Alignment[]{Alignment.LEFT, Alignment.LEFT};
        headerAlignments = Arrays.asList(headerAlignmentsArray);
        headersArray = new String[]{"H0", "H1"};
        headers = Arrays.asList(headersArray);
        rowAlignmentsArray = new Alignment[]{Alignment.RIGHT, Alignment.RIGHT};
        rowAlignments = Arrays.asList(rowAlignmentsArray);
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
                .setHeaderAlignments(headerAlignments)
                .setHeaders(headers)
                .setRowAlignments(rowAlignments)
                .setRows(allRows)
                .setPrepender(prepender)
                .setAppender(appender);
    }

}
