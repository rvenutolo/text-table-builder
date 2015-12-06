package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.venutolo.texttablebuilder.TestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_NULL_LIST;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TestStrings.SETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.SHOULD_NOT_REACH_THIS_POINT;

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

    /*========================================================================
     * TESTS FOR getHeaders()
     *========================================================================*/

    @Test
    public void testGetHeadersWhenNotSet() {
        final List<Object> emptyHeaders = emptyTextTableBuilder.getHeaders();
        assertTrue(
                "headers should be non-null and empty",
                (emptyHeaders != null) && emptyHeaders.isEmpty()
        );
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

    /*========================================================================
     * TESTS FOR setHeaders(Collection<?>)
     *========================================================================*/

    @Test
    public void testSetHeaders() {
        emptyTextTableBuilder.setHeaders(headers);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headers,
                emptyTextTableBuilder.getHeaders()
        );
    }

    @Test
    public void testSetHeaderForNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_LIST);
        populatedTextTableBuilder.setHeaders((Collection<?>) null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeadersForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaders(Collections.<String>emptyList());
        fail(SHOULD_NOT_REACH_THIS_POINT);
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

    /*========================================================================
     * TESTS FOR setHeaders(Object...)
     *========================================================================*/

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
    public void testSetHeaderArrayForNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_LIST);
        populatedTextTableBuilder.setHeaders((Object[]) null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeadersArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaders();
        fail(SHOULD_NOT_REACH_THIS_POINT);
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

    /*========================================================================
     * TESTS FOR clearHeaders()
     *========================================================================*/

    @Test
    public void testClearHeaders() {
        populatedTextTableBuilder.clearHeaders();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getHeaders().isEmpty()
        );
    }

}
