package org.venutolo.texttablebuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilder {

    private Integer numColumns;

    private BoxDrawingCharacters boxDrawingCharacters = BoxDrawingCharacters.LIGHT;

    private List<Object> headers;

    private List<Alignment> headerAlignments;

    private List<List<Object>> rows;

    private List<Alignment> rowAlignments;

    private int repeatHeaders;

    private boolean repeatHeadersAtBottom;

    private boolean showRowNums;

    private boolean replaceNullWithEmptyString = true;

    private String prepender = "";

    private String appender = "";

    /**************************************************************************
     * CONSTRUCTOR(S)
     **************************************************************************/

    public TextTableBuilder() {
        initRows(null);
    }

    /**************************************************************************
     * STATIC UTILITY METHODS
     **************************************************************************/

    private static <T> List<T> defensiveListCopy(final Collection<T> collection) {
        return (collection == null) ? new ArrayList<T>() : new ArrayList<T>(collection);
    }

    private static List<Object> defensiveObjectListCopy(final Collection<?> collection) {
        return (collection == null) ? new ArrayList<Object>() : new ArrayList<Object>(collection);
    }

    /**************************************************************************
     * INSTANCE UTILITY METHODS
     **************************************************************************/

    private void initRows(final Integer size) {
        this.rows = (size != null)
                    ? new ArrayList<List<Object>>(size)
                    : new ArrayList<List<Object>>();
    }

    private void checkNumColumns(final Collection<?> collection) {
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
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    /**************************************************************************
     * HEADER METHODS
     **************************************************************************/

    public List<Object> getHeaders() {
        return defensiveListCopy(headers);
    }

    public TextTableBuilder setHeadersList(final Collection<?> headers) {
        checkNumColumns(headers);
        this.headers = defensiveObjectListCopy(headers);
        return this;
    }

    public TextTableBuilder setHeaders(final Object... headers) {
        return setHeadersList(Arrays.asList(headers));
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

    public TextTableBuilder setHeaderAlignmentsList(final Collection<Alignment> headerAlignments) {
        checkNumColumns(headerAlignments);
        this.headerAlignments = defensiveListCopy(headerAlignments);
        return this;
    }

    public TextTableBuilder setHeaderAlignments(final Alignment... headerAlignments) {
        return setHeaderAlignmentsList(Arrays.asList(headerAlignments));
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
        for (final Collection<Object> row : this.rows) {
            rows.add(defensiveListCopy(row));
        }
        return rows;
    }

    public TextTableBuilder addRowList(final Collection<?> row) {
        checkNumColumns(row);
        rows.add(defensiveObjectListCopy(row));
        return this;
    }

    public TextTableBuilder addRow(final Object... row) {
        return addRowList(Arrays.asList(row));
    }

    public TextTableBuilder addRowsList(final Collection<? extends Collection<?>> rows) {
        for (final Collection<?> row : rows) {
            addRowList(row);
        }
        return this;
    }

    public TextTableBuilder addRows(final Collection<?>... rows) {
        return addRowsList(Arrays.asList(rows));
    }

    public TextTableBuilder setRowsList(final Collection<? extends Collection<?>> rows) {
        initRows(rows.size());
        return addRowsList(rows);
    }

    public TextTableBuilder setRows(final Collection<?>... rows) {
        return setRowsList(Arrays.asList(rows));
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

    public TextTableBuilder setRowAlignmentsList(final Collection<Alignment> rowAlignments) {
        checkNumColumns(rowAlignments);
        this.rowAlignments = defensiveListCopy(rowAlignments);
        return this;
    }

    public TextTableBuilder setRowAlignments(final Alignment... rowAlignments) {
        return setRowAlignmentsList(Arrays.asList(rowAlignments));
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

    public TextTableBuilder setPrepender(final String prepender) {
        this.prepender = (prepender == null) ? "" : prepender;
        return this;
    }

    public TextTableBuilder clearPrepender() {
        return setPrepender(null);
    }

    public String getAppender() {
        return appender;
    }

    public TextTableBuilder setAppender(final String appender) {
        this.appender = (appender == null) ? "" : appender;
        return this;
    }

    public TextTableBuilder clearAppender() {
        return setAppender(null);
    }

    public boolean getReplaceNullWithEmptyString() {
        return replaceNullWithEmptyString;
    }

    public TextTableBuilder setReplaceNullWithEmptyString(final boolean replaceNullWithEmptyString) {
        this.replaceNullWithEmptyString = replaceNullWithEmptyString;
        return this;
    }

    public TextTableBuilder doNotReplaceNullWithEmptyString() {
        return setReplaceNullWithEmptyString(false);
    }

}
