package org.venutolo.texttablebuilder;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderHeadersTest extends TextTableBuilderTest {

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
