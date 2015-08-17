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
                .setHeadersInternal(headers);
    }

    @Test
    public void testGetHeadersForDefensiveCopying() {
        final String expected = headers.get(0);
        populatedTextTableBuilder.getHeaders().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                populatedTextTableBuilder.getHeaders().get(0)
        );
    }

    @Test
    public void testSetHeadersCollection() {
        emptyTextTableBuilder.setHeadersInternal(headers);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headers,
                emptyTextTableBuilder.getHeaders()
        );
    }

    @Test
    public void testSetHeadersArray() {
        emptyTextTableBuilder.setHeaders((Object[]) headersArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headers,
                emptyTextTableBuilder.getHeaders()
        );
    }

    @Test
    public void testSetHeadersCollectionForDefensiveCopying() {
        final String expected = headers.get(0);
        emptyTextTableBuilder.setHeadersInternal(headers);
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
        emptyTextTableBuilder.setHeaders((Object[]) headersArray);
        headersArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaders().get(0)
        );
    }

    @Test
    public void testSetHeadersCollectionForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeadersInternal(Collections.<String>emptyList());
    }

    @Test
    public void testSetHeadersArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaders();
    }

    @Test
    public void testSetHeaderCollectionForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setHeadersInternal(null);
    }

    @Test
    public void testSetHeaderArrayForNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_NPE_FOR_NULL_LIST);
        populatedTextTableBuilder.setHeaders((Object[]) null);
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
