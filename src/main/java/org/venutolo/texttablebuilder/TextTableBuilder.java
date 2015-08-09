package org.venutolo.texttablebuilder;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilder {

    private static final String EMPTY_STRING = "";

    private Integer numColumns;

    private BoxDrawingCharacters boxDrawingCharacters = BoxDrawingCharacters.LIGHT;

    private List<Object> headers;

    private List<Alignment> headerAlignments;

    private List<List<Object>> rows;

    private List<Alignment> rowAlignments;

    private int repeatHeaders;

    private boolean repeatHeadersAtBottom;

    private boolean showRowNums;

    private String nullReplacement = EMPTY_STRING;

    private String prepender = EMPTY_STRING;

    private String appender = EMPTY_STRING;

    /**************************************************************************
     * CONSTRUCTOR(S)
     **************************************************************************/

    public TextTableBuilder() {
        initRows(null);
    }

    /**************************************************************************
     * STATIC UTILITY METHODS
     **************************************************************************/

    private static <T> List<T> defensiveListCopy(final List<T> collection) {
        return (collection == null) ? new ArrayList<T>() : new ArrayList<T>(collection);
    }

    private static List<Object> defensiveObjectListCopy(final List<?> collection) {
        return (collection == null) ? new ArrayList<Object>() : new ArrayList<Object>(collection);
    }

    // if array is null, return null for later checking for null
    private static <T> List<T> convertArrayToList(final T... array) {
        return (array == null) ? null : Arrays.asList(array);
    }

    private static void checkAlignmentsForNull(final List<Alignment> alignments) {
        int index = 0;
        for (final Alignment alignment : alignments) {
            checkNotNull(alignment, "alignment at index %s cannot be null", index);
            index++;
        }
    }

    /**************************************************************************
     * INSTANCE UTILITY METHODS
     **************************************************************************/

    private void initRows(final Integer size) {
        this.rows = (size != null)
                    ? new ArrayList<List<Object>>(size)
                    : new ArrayList<List<Object>>();
    }

    private void checkNumColumns(final List<?> collection) {
        if (numColumns == null) {
            // if numColumns is null, this is the first check for number of columns, so set it
            numColumns = collection.size();
        } else {
            if (numColumns != collection.size()) {
                throw new IllegalArgumentException(
                        "Wrong number of columns: " + collection.size()
                        + "; expected " + numColumns + " columns"
                );
            }
        }
    }

    /**************************************************************************
     * BOX DRAWING CHARACTERS METHODS
     **************************************************************************/

    public BoxDrawingCharacters getBoxDrawingCharacters() {
        return boxDrawingCharacters;
    }

    public TextTableBuilder setBoxDrawingCharacters(final BoxDrawingCharacters boxDrawingCharacters) {
        checkNotNull(boxDrawingCharacters, "box drawing characters cannot be null");
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    /**************************************************************************
     * HEADER METHODS
     **************************************************************************/

    public List<Object> getHeaders() {
        return defensiveListCopy(headers);
    }

    public TextTableBuilder setHeadersList(@Nonnull final List<?> headers) {
        checkNotNull(headers, "headers cannot be null");
        checkNumColumns(headers);
        this.headers = defensiveObjectListCopy(headers);
        return this;
    }

    public TextTableBuilder setHeaders(@Nonnull final Object... headers) {
        return setHeadersList(convertArrayToList(headers));
    }

    public TextTableBuilder clearHeaders() {
        this.headers = null;
        return this;
    }

    /**************************************************************************
     * HEADER ALIGNMENT METHODS
     **************************************************************************/

    public List<Alignment> getHeaderAlignments() {
        return defensiveListCopy(headerAlignments);
    }

    public TextTableBuilder setHeaderAlignmentsList(@Nonnull final List<Alignment> headerAlignments) {
        checkNotNull(headerAlignments, "header alignments cannot be null");
        checkAlignmentsForNull(headerAlignments);
        checkNumColumns(headerAlignments);
        this.headerAlignments = defensiveListCopy(headerAlignments);
        return this;
    }

    public TextTableBuilder setHeaderAlignments(@Nonnull final Alignment... headerAlignments) {
        return setHeaderAlignmentsList(convertArrayToList(headerAlignments));
    }

    public TextTableBuilder clearHeaderAlignments() {
        this.headerAlignments = null;
        return this;
    }

    /**************************************************************************
     * ROW METHODS
     **************************************************************************/

    public List<List<Object>> getRows() {
        final List<List<Object>> rows = new ArrayList<List<Object>>(this.rows.size());
        for (final List<Object> row : this.rows) {
            rows.add(defensiveListCopy(row));
        }
        return rows;
    }

    public TextTableBuilder addRowList(@Nonnull final List<?> row) {
        checkNotNull(row, "row cannot be null");
        checkNumColumns(row);
        rows.add(defensiveObjectListCopy(row));
        return this;
    }

    public TextTableBuilder addRow(@Nonnull final Object... row) {
        return addRowList(convertArrayToList(row));
    }

    public TextTableBuilder addRowsList(@Nonnull final List<? extends List<?>> rows) {
        checkNotNull(rows, "rows cannot be null");
        for (final List<?> row : rows) {
            addRowList(row);
        }
        return this;
    }

    public TextTableBuilder addRows(@Nonnull final List<?>... rows) {
        return addRowsList(convertArrayToList(rows));
    }

    public TextTableBuilder setRowsList(@Nonnull final List<? extends List<?>> rows) {
        checkNotNull(rows, "rows cannot be null");
        initRows(rows.size());
        return addRowsList(rows);
    }

    public TextTableBuilder setRows(@Nonnull final List<?>... rows) {
        return setRowsList(convertArrayToList(rows));
    }

    public TextTableBuilder removeRow(final int rowNum) {
        rows.remove(rowNum);
        return this;
    }

    public TextTableBuilder clearRows() {
        initRows(null);
        return this;
    }

    /**************************************************************************
     * ROW ALIGNMENT METHODS
     **************************************************************************/

    public List<Alignment> getRowAlignments() {
        return defensiveListCopy(rowAlignments);
    }

    public TextTableBuilder setRowAlignmentsList(@Nonnull final List<Alignment> rowAlignments) {
        checkNotNull(rowAlignments, "row alignments cannot be null");
        checkAlignmentsForNull(rowAlignments);
        checkNumColumns(rowAlignments);
        this.rowAlignments = defensiveListCopy(rowAlignments);
        return this;
    }

    public TextTableBuilder setRowAlignments(@Nonnull final Alignment... rowAlignments) {
        return setRowAlignmentsList(convertArrayToList(rowAlignments));
    }

    public TextTableBuilder clearRowAlignments() {
        this.rowAlignments = null;
        return this;
    }

    /**************************************************************************
     * NUMBER OF ROW AND COLUMN GETTERS
     **************************************************************************/

    public int getNumRows() {
        return rows.size();
    }

    public int getNumColumns() {
        return (numColumns == null) ? 0 : numColumns;
    }

    /**************************************************************************
     * MISC OPTION METHODS
     **************************************************************************/

    public boolean getShowRowNums() {
        return showRowNums;
    }

    public TextTableBuilder setShowRowNums(final boolean showRowNums) {
        this.showRowNums = showRowNums;
        return this;
    }

    public TextTableBuilder showRowNums() {
        return setShowRowNums(true);
    }

    public int getRepeatHeaders() {
        return repeatHeaders;
    }

    public TextTableBuilder repeatHeaders(final int numRows) {
        this.repeatHeaders = numRows;
        return this;
    }

    public boolean getRepeatHeadersAtBottom() {
        return repeatHeadersAtBottom;
    }

    public TextTableBuilder setRepeatHeadersAtBottom(final boolean repeatHeadersAtBottom) {
        this.repeatHeadersAtBottom = repeatHeadersAtBottom;
        return this;
    }

    public TextTableBuilder repeatHeadersAtBottom() {
        return setRepeatHeadersAtBottom(true);
    }

    public String getPrepender() {
        return prepender;
    }

    public TextTableBuilder setPrepender(@Nonnull final String prepender) {
        checkNotNull(prepender, "prepender cannot be null");
        this.prepender = prepender;
        return this;
    }

    public TextTableBuilder clearPrepender() {
        return setPrepender(EMPTY_STRING);
    }

    public String getAppender() {
        return appender;
    }

    public TextTableBuilder setAppender(@Nonnull final String appender) {
        checkNotNull(appender, "appender cannot be null");
        this.appender = appender;
        return this;
    }

    public TextTableBuilder clearAppender() {
        return setAppender(EMPTY_STRING);
    }

    public String getNullReplacement() {
        return nullReplacement;
    }

    public TextTableBuilder setNullReplacement(@Nonnull final String nullReplacement) {
        checkNotNull(nullReplacement, "null replacement cannot be null");
        this.nullReplacement = nullReplacement;
        return this;
    }

    public TextTableBuilder clearNullReplacement() {
        return setNullReplacement(EMPTY_STRING);
    }

}
