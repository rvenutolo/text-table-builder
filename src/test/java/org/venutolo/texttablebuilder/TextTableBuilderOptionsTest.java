package org.venutolo.texttablebuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.ASCII;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.CURVED;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.DOUBLE;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.HEAVY;
import static org.venutolo.texttablebuilder.BoxDrawingCharacters.LIGHT;
import static org.venutolo.texttablebuilder.TestStrings.CANNOT_BE_NULL;
import static org.venutolo.texttablebuilder.TestStrings.GETTER_SETTER_VALUE_NOT_EQUAL;
import static org.venutolo.texttablebuilder.TestStrings.SHOULD_NOT_REACH_THIS_POINT;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderOptionsTest {

    private static final Collection<BoxDrawingCharacters> boxDrawingCharactersCollection =
            Collections.unmodifiableCollection(
                    Arrays.asList(
                            ASCII,
                            CURVED,
                            DOUBLE,
                            HEAVY,
                            LIGHT
                    )
            );

    private static final boolean[] trueFalseArray = {true, false};

    private static final String EXPECTED_IAE_FOR_NULL_BDC =
            "expected IAE for null box drawing characters";

    private static final String EXPECTED_IAE_FOR_BAD_NUM_ROWS =
            "expected IAE for negative number of rows";

    private static final String EMPTY_STRING = "";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TextTableBuilder emptyTextTableBuilder;

    @Before
    public void setUp() {
        emptyTextTableBuilder = new TextTableBuilder();
    }

    /*========================================================================
     * TESTS FOR setBoxDrawingCharacters(BoxDrawingCharacters)
     *========================================================================*/

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
    public void testSetBoxDrawingCharactersForNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CANNOT_BE_NULL);
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_NULL_BDC);
        emptyTextTableBuilder.setBoxDrawingCharacters(null);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    /*========================================================================
     * TESTS FOR setRepeatHeadersEveryXRows(int)
     *========================================================================*/

    @Test
    public void testSetAndGetRepeatHeadersEveryXRows() {
        final int numRows = 10;
        emptyTextTableBuilder.setRepeatHeadersEveryXRows(numRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                numRows,
                emptyTextTableBuilder.getRepeatHeadersEveryXRows()
        );
    }

    @Test
    public void testSetRepeatHeadersEveryXRowsBadValue() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("rows");
        expectedException.reportMissingExceptionWithMessage(EXPECTED_IAE_FOR_BAD_NUM_ROWS);
        emptyTextTableBuilder.setRepeatHeadersEveryXRows(-1);
        fail(SHOULD_NOT_REACH_THIS_POINT);
    }

    /*========================================================================
     * TESTS FOR setRepeatHeadersAtBottom(boolean) and repeatHeadersAtBottom()
     *========================================================================*/

    @Test
    public void testSetAndGetRepeatHeadersAtBottom() {
        for (final boolean repeatHeadersAtBottom : trueFalseArray) {
            emptyTextTableBuilder.setRepeatHeadersAtBottom(repeatHeadersAtBottom);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    repeatHeadersAtBottom,
                    emptyTextTableBuilder.getRepeatHeadersAtBottom()
            );
        }
    }

    @Test
    public void testRepeatHeadersAtBottom() {
        emptyTextTableBuilder.repeatHeadersAtBottom();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getRepeatHeadersAtBottom()
        );
    }

    /*========================================================================
     * TESTS FOR setShowRowNums(boolean) and showRowNums()
     *========================================================================*/

    @Test
    public void testSetAndGetShowRowNums() {
        for (final boolean showRowNums : trueFalseArray) {
            emptyTextTableBuilder.setShowRowNums(showRowNums);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    showRowNums,
                    emptyTextTableBuilder.getShowRowNums()
            );
        }
    }

    @Test
    public void testShowRowNums() {
        emptyTextTableBuilder.showRowNums();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getShowRowNums()
        );
    }

    /*========================================================================
     * TESTS FOR setRowNumHeader(boolean)
     *========================================================================*/

    @Test
    public void testSetAndGetRowNumHeader() {
        final String header = "header";
        emptyTextTableBuilder.setRowNumHeader(header);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                header,
                emptyTextTableBuilder.getRowNumHeader()
        );
    }

    @Test
    public void testSetAndGetRowNumHeaderNull() {
        emptyTextTableBuilder.setRowNumHeader(null);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                EMPTY_STRING,
                emptyTextTableBuilder.getRowNumHeader()
        );
    }

    /*========================================================================
     * TESTS FOR setRowNumFormat(NumberFormat)
     *========================================================================*/

    @Test
    public void testSetAndGetRowNumFormat() {
        final NumberFormat numberFormat = new DecimalFormat();
        emptyTextTableBuilder.setRowNumFormat(numberFormat);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                numberFormat,
                emptyTextTableBuilder.getRowNumFormat()
        );
    }

    @Test
    public void testSetAndGetRowNumFormatNull() {
        emptyTextTableBuilder.setRowNumFormat(null);
        assertNull(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getRowNumFormat()
        );
    }

    /*========================================================================
     * TESTS FOR setLinePrepender(String)
     *========================================================================*/

    @Test
    public void testSetAndGetLinePrepender() {
        final String prepender = "prepender";
        emptyTextTableBuilder.setLinePrepender(prepender);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                prepender,
                emptyTextTableBuilder.getLinePrepender()
        );
    }

    @Test
    public void testSetAndGetLinePrependerNull() {
        emptyTextTableBuilder.setLinePrepender(null);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                EMPTY_STRING,
                emptyTextTableBuilder.getLinePrepender()
        );
    }

    /*========================================================================
     * TESTS FOR setLineAppender(String)
     *========================================================================*/

    @Test
    public void testSetAndGetLineAppender() {
        final String appender = "appender";
        emptyTextTableBuilder.setLineAppender(appender);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                appender,
                emptyTextTableBuilder.getLineAppender()
        );
    }

    @Test
    public void testSetAndGetLineAppenderNull() {
        emptyTextTableBuilder.setLineAppender(null);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                EMPTY_STRING,
                emptyTextTableBuilder.getLineAppender()
        );
    }

    /*========================================================================
     * TESTS FOR setNullColumnReplacement(String)
     *========================================================================*/

    @Test
    public void testSetAndGetNullColumnReplacement() {
        final String replacement = "replacement";
        emptyTextTableBuilder.setNullColumnReplacement(replacement);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                replacement,
                emptyTextTableBuilder.getNullColumnReplacement()
        );
    }

    @Test
    public void testSetAndGetNullColumnReplacementNull() {
        emptyTextTableBuilder.setNullColumnReplacement(null);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                EMPTY_STRING,
                emptyTextTableBuilder.getNullColumnReplacement()
        );
    }

}
