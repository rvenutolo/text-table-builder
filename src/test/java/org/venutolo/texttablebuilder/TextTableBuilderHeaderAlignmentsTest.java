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
import static org.venutolo.texttablebuilder.Alignment.LEFT;
import static org.venutolo.texttablebuilder.Alignment.RIGHT;
import static org.venutolo.texttablebuilder.TestStrings.ALIGNMENTS_NOT_NULL_AND_EMPTY;
import static org.venutolo.texttablebuilder.TestStrings.BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING;
import static org.venutolo.texttablebuilder.TestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_NULL_ALIGNMENT;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_NULL_LIST;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TestStrings.NOT_EMPTY_AFTER_CLEAR;
import static org.venutolo.texttablebuilder.TestStrings.SETTER_NO_DEFENSIVE_COPY;
import static org.venutolo.texttablebuilder.TestStrings.SHOULD_NOT_REACH_THIS_POINT;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderHeaderAlignmentsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Alignment[] headerAlignmentsArray;

    private List<Alignment> headerAlignments;

    private TextTableBuilder emptyTextTableBuilder;

    private TextTableBuilder populatedTextTableBuilder;

    @Before
    public void setUp() {
        headerAlignmentsArray = new Alignment[]{LEFT, RIGHT};
        headerAlignments = Arrays.asList(headerAlignmentsArray);
        emptyTextTableBuilder = new TextTableBuilder();
        populatedTextTableBuilder = new TextTableBuilder()
                .setHeaderAlignments(headerAlignments);
    }

    /*========================================================================
     * TESTS FOR getHeaderAlignments()
     *========================================================================*/

    @Test
    public void testGetHeaderAlignmentsWhenNotSet() {
        final List<Alignment> emptyHeaderAlignments = emptyTextTableBuilder.getHeaderAlignments();
        assertTrue(
                ALIGNMENTS_NOT_NULL_AND_EMPTY,
                (emptyHeaderAlignments != null) && emptyHeaderAlignments.isEmpty()
        );
    }

    @Test
    public void testGetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = headerAlignments.get(0);
        populatedTextTableBuilder.getHeaderAlignments().set(0, null);
        assertEquals(
                GETTER_NO_DEFENSIVE_COPY,
                expected,
                populatedTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    /*========================================================================
     * TESTS FOR setHeaderAlignments(Collection<Alignment>)
     *========================================================================*/

    @Test
    public void testSetHeaderAlignments() {
        emptyTextTableBuilder.setHeaderAlignments(headerAlignments);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headerAlignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetHeaderAlignmentsForNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_LIST);
        populatedTextTableBuilder.setHeaderAlignments((Collection<Alignment>) null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeaderAlignmentsForNullAlignment() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setHeaderAlignments(Arrays.asList(null, LEFT));
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeaderAlignmentsForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignments(Collections.<Alignment>emptyList());
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeaderAlignmentsForDefensiveCopying() {
        final Alignment expected = headerAlignments.get(0);
        emptyTextTableBuilder.setHeaderAlignments(headerAlignments);
        headerAlignments.set(0, null);
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    /*========================================================================
     * TESTS FOR setHeaderAlignments(Alignment...)
     *========================================================================*/

    @Test
    public void testSetHeaderAlignmentsArray() {
        emptyTextTableBuilder.setHeaderAlignments(headerAlignmentsArray);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                headerAlignments,
                emptyTextTableBuilder.getHeaderAlignments()
        );
    }

    @Test
    public void testSetHeaderAlignmentsArrayForNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_LIST);
        populatedTextTableBuilder.setHeaderAlignments((Alignment[]) null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeaderAlignmentsArrayForNullAlignment() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_ALIGNMENT);
        populatedTextTableBuilder.setHeaderAlignments(null, LEFT);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeaderAlignmentsArrayForBadLength() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(BAD_COLUMN_LENGTH_MESSAGE_SUBSTRING);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        populatedTextTableBuilder.setHeaderAlignments();
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testSetHeaderAlignmentsArrayForDefensiveCopying() {
        final Alignment expected = headerAlignmentsArray[0];
        emptyTextTableBuilder.setHeaderAlignments(headerAlignmentsArray);
        headerAlignmentsArray[0] = null;
        assertEquals(
                SETTER_NO_DEFENSIVE_COPY,
                expected,
                emptyTextTableBuilder.getHeaderAlignments().get(0)
        );
    }

    /*========================================================================
     * TESTS FOR clearHeaderAlignments()
     *========================================================================*/

    @Test
    public void testClearHeaderAlignments() {
        populatedTextTableBuilder.clearHeaderAlignments();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getHeaderAlignments().isEmpty()
        );
    }

}
