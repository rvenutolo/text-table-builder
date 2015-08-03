package org.venutolo.texttablebuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilderOptionsTest extends TextTableBuilderTest {

    private static final boolean[] trueFalseArray = {true, false};

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

    @Test
    public void testSetAndGetRepeatHeaders() {
        final int numRows = 10;
        emptyTextTableBuilder.repeatHeaders(numRows);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                numRows,
                emptyTextTableBuilder.getRepeatHeaders()
        );
    }

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

    @Test
    public void testSetAndGetPrepender() {
        final String prepender = "prepender";
        emptyTextTableBuilder.setPrepender(prepender);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                prepender,
                emptyTextTableBuilder.getPrepender()
        );
    }

    @Test
    public void testClearPrepender() {
        populatedTextTableBuilder.clearPrepender();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getPrepender().isEmpty()
        );
    }

    @Test
    public void testSetAndGetAppender() {
        final String appenderString = "appender";
        emptyTextTableBuilder.setAppender(appenderString);
        assertEquals(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                appenderString,
                emptyTextTableBuilder.getAppender()
        );
    }

    @Test
    public void testClearAppender() {
        populatedTextTableBuilder.clearAppender();
        assertTrue(
                NOT_EMPTY_AFTER_CLEAR,
                populatedTextTableBuilder.getAppender().isEmpty()
        );
    }

    @Test
    public void testSetAndGetReplaceNullWithEmptyString() {
        for (final boolean replaceNullWithEmptyString : trueFalseArray) {
            emptyTextTableBuilder.setReplaceNullWithEmptyString(replaceNullWithEmptyString);
            assertEquals(
                    GETTER_SETTER_VALUE_NOT_EQUAL,
                    replaceNullWithEmptyString,
                    emptyTextTableBuilder.getReplaceNullWithEmptyString()
            );
        }
    }

    @Test
    public void testReplaceNullWithEmptyString() {
        emptyTextTableBuilder.replaceNullWithEmptyString();
        assertTrue(
                GETTER_SETTER_VALUE_NOT_EQUAL,
                emptyTextTableBuilder.getReplaceNullWithEmptyString()
        );
    }

}
