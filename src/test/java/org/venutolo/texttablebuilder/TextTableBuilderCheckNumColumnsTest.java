package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.venutolo.texttablebuilder.TestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TestStrings.EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH;
import static org.venutolo.texttablebuilder.TestStrings.SHOULD_NOT_REACH_THIS_POINT;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderCheckNumColumnsTest {

    private static final String EXPECTED_IAE_FOR_NULL_COLLECTION =
            "expected IAE for null collection";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TextTableBuilder emptyTextTableBuilder;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
    }

    /*========================================================================
     * TESTS FOR checkNumColumns(Collection<?>)
     *========================================================================*/

    @Test
    public void testCheckNumColumnSetsNumColumns() {
        emptyTextTableBuilder.checkNumColumns(Arrays.asList(null, null));
        // first call sets the number of columns to expect
        assertEquals("expected num columns to be set", 2, emptyTextTableBuilder.getNumColumns());
    }

    @Test
    public void testCheckNumColumnsForDifferentSizes() {
        emptyTextTableBuilder.checkNumColumns(Arrays.asList(null, null));
        // first call sets the number of columns to expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("expected 2 columns");
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_COLUMN_LENGTH);
        emptyTextTableBuilder.checkNumColumns(Arrays.asList(null, null, null));
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    @Test
    public void testCheckNumColumnsWhenNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_COLLECTION);
        emptyTextTableBuilder.checkNumColumns(null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

}
