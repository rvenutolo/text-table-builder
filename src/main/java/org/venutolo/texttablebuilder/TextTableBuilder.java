package org.venutolo.texttablebuilder;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    private int repeatHeadersEveryXRows;

    private boolean repeatHeadersAtBottom;

    private boolean showRowNums;

    private String linePrepender = EMPTY_STRING;

    private String lineAppender = EMPTY_STRING;

    private String nullColumnReplacement = EMPTY_STRING;

    /**************************************************************************
     * CONSTRUCTOR
     **************************************************************************/

    public TextTableBuilder() {
        initRows(null);
    }

    /**************************************************************************
     * STATIC UTILITY METHODS
     **************************************************************************/

    // these next four methods are used to make defensive copies (and convert arrays to collections)
    // of input where null should be preserved for later checking
    private static List<Object> objectListFromInputCollection(final Collection<?> collection) {
        return (collection == null) ? null : new ArrayList<Object>(collection);
    }

    private static List<Alignment> alignmentListFromInputCollection(final Collection<Alignment> collection) {
        return (collection == null) ? null : new ArrayList<Alignment>(collection);
    }

    private static List<Object> objectListFromInputArray(final Object... array) {
        return (array == null) ? null : new ArrayList<Object>(Arrays.asList(array));
    }

    private static List<Alignment> alignmentListFromInputArray(final Alignment... array) {
        return (array == null) ? null : new ArrayList<Alignment>(Arrays.asList(array));
    }

    // this method should return an empty list when the given list is null
    private static <T> List<T> listForOutput(final List<T> list) {
        return (list == null) ? new ArrayList<T>() : new ArrayList<T>(list);
    }

    private static void checkAlignmentsForNull(final Iterable<Alignment> alignments) {
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

    protected void checkNumColumns(final Collection<?> collection) {
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
     * HEADER ALIGNMENT METHODS
     **************************************************************************/

    public List<Alignment> getHeaderAlignments() {
        return listForOutput(headerAlignments);
    }

    private TextTableBuilder setHeaderAlignmentsInternal(@Nonnull final List<Alignment> headerAlignments) {
        checkNotNull(headerAlignments, "header alignments cannot be null");
        checkAlignmentsForNull(headerAlignments);
        checkNumColumns(headerAlignments);
        // param should already be a defensive copy
        this.headerAlignments = headerAlignments;
        return this;
    }

    public TextTableBuilder setHeaderAlignmentsCollection(@Nonnull final Collection<Alignment> headerAlignments) {
        return setHeaderAlignmentsInternal(alignmentListFromInputCollection(headerAlignments));
    }

    public TextTableBuilder setHeaderAlignments(@Nonnull final Alignment... headerAlignments) {
        return setHeaderAlignmentsInternal(alignmentListFromInputArray(headerAlignments));
    }

    public TextTableBuilder clearHeaderAlignments() {
        this.headerAlignments = null;
        return this;
    }

    /**************************************************************************
     * ROW ALIGNMENT METHODS
     **************************************************************************/

    public List<Alignment> getRowAlignments() {
        return listForOutput(rowAlignments);
    }

    private TextTableBuilder setRowAlignmentsInternal(@Nonnull final List<Alignment> rowAlignments) {
        checkNotNull(rowAlignments, "row alignments cannot be null");
        checkAlignmentsForNull(rowAlignments);
        checkNumColumns(rowAlignments);
        // param should already be a defensive copy
        this.rowAlignments = rowAlignments;
        return this;
    }

    public TextTableBuilder setRowAlignmentsCollection(@Nonnull final Collection<Alignment> rowAlignments) {
        return setRowAlignmentsInternal(alignmentListFromInputCollection(rowAlignments));
    }

    public TextTableBuilder setRowAlignments(@Nonnull final Alignment... rowAlignments) {
        return setRowAlignmentsInternal(alignmentListFromInputArray(rowAlignments));
    }

    public TextTableBuilder clearRowAlignments() {
        this.rowAlignments = null;
        return this;
    }

    /**************************************************************************
     * HEADER METHODS
     **************************************************************************/

    public List<Object> getHeaders() {
        return listForOutput(headers);
    }

    private TextTableBuilder setHeadersInternal(@Nonnull final List<Object> headers) {
        checkNotNull(headers, "headers cannot be null");
        checkNumColumns(headers);
        // param should already be a defensive copy
        this.headers = headers;
        return this;
    }

    public TextTableBuilder setHeadersCollection(@Nonnull final Collection<?> headers) {
        return setHeadersInternal(objectListFromInputCollection(headers));
    }

    public TextTableBuilder setHeaders(@Nonnull final Object... headers) {
        return setHeadersCollection(objectListFromInputArray(headers));
    }

    public TextTableBuilder clearHeaders() {
        this.headers = null;
        return this;
    }

    /**************************************************************************
     * ROW METHODS
     **************************************************************************/

    public List<List<Object>> getRows() {
        final List<List<Object>> rows = new ArrayList<List<Object>>(this.rows.size());
        for (final List<Object> row : this.rows) {
            rows.add(listForOutput(row));
        }
        return rows;
    }

    private TextTableBuilder addRowInternal(@Nonnull final List<Object> row) {
        checkNotNull(row, "row cannot be null");
        checkNumColumns(row);
        // param should already be a defensive copy
        rows.add(row);
        return this;
    }

    public TextTableBuilder addRowCollection(@Nonnull final Collection<?> row) {
        return addRowInternal(objectListFromInputCollection(row));
    }

    public TextTableBuilder addRow(@Nonnull final Object... row) {
        return addRowInternal(objectListFromInputArray(row));
    }

    public TextTableBuilder addRowsCollection(@Nonnull final Collection<? extends Collection<?>> rows) {
        checkNotNull(rows, "rows cannot be null");
        for (final Collection<?> row : rows) {
            addRowCollection(row);
        }
        return this;
    }

    public TextTableBuilder addRows(@Nonnull final Collection<?>... rows) {
        checkNotNull(rows, "rows cannot be null");
        for (final Collection<?> row : rows) {
            addRowCollection(row);
        }
        return this;
    }

    public TextTableBuilder setRowsCollection(@Nonnull final Collection<? extends Collection<?>> rows) {
        checkNotNull(rows, "rows cannot be null");
        initRows(rows.size());
        return addRowsCollection(rows);
    }

    public TextTableBuilder setRows(@Nonnull final Collection<?>... rows) {
        checkNotNull(rows, "rows cannot be null");
        initRows(rows.length);
        return addRows(rows);
    }

    public TextTableBuilder clearRows() {
        initRows(null);
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

    public BoxDrawingCharacters getBoxDrawingCharacters() {
        return boxDrawingCharacters;
    }

    public TextTableBuilder setBoxDrawingCharacters(@Nonnull final BoxDrawingCharacters boxDrawingCharacters) {
        checkNotNull(boxDrawingCharacters, "box drawing characters cannot be null");
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    public int getRepeatHeadersEveryXRows() {
        return repeatHeadersEveryXRows;
    }

    public TextTableBuilder setRepeatHeadersEveryXRows(final int repeatHeadersEveryXRows) {
        this.repeatHeadersEveryXRows = repeatHeadersEveryXRows;
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

    public String getLinePrepender() {
        return linePrepender;
    }

    public TextTableBuilder setLinePrepender(final String linePrepender) {
        this.linePrepender = (linePrepender == null) ? EMPTY_STRING : linePrepender;
        return this;
    }

    public String getLineAppender() {
        return lineAppender;
    }

    public TextTableBuilder setLineAppender(final String lineAppender) {
        this.lineAppender = (lineAppender == null) ? EMPTY_STRING : lineAppender;
        return this;
    }

    public String getNullColumnReplacement() {
        return nullColumnReplacement;
    }

    public TextTableBuilder setNullColumnReplacement(final String nullColumnReplacement) {
        this.nullColumnReplacement =
                (nullColumnReplacement == null)
                ? EMPTY_STRING
                : nullColumnReplacement;
        return this;
    }

}
