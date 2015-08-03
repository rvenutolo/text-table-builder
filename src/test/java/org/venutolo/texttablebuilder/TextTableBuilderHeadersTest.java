package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TextTableBuilderTestStrings.SETTER_NO_DEFENSIVE_COPY;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderHeadersTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private String[] headersArray;

    private List<String> headers;

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        headersArray = new String[]{"H0", "H1"};
        headers = Arrays.asList(headersArray);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setHeaders(headers);
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
        emptyTextTableBuilder.setHeadersFromArray((Object[]) headersArray);
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
        emptyTextTableBuilder.setHeadersFromArray((Object[]) headersArray);
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
        populatedTextTableBuilder.setHeadersFromArray();
    }

    @Test
    public void testClearHeaders() {
        populatedTextTableBuilder.clearHeaders();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getHeaders().isEmpty()
        );
    }

}