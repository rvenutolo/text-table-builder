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
     * CONSTRUCTOR(S)
     **************************************************************************/

    public TextTableBuilder() {
        initRows(null);
    }

    /**************************************************************************
     * STATIC UTILITY METHODS
     **************************************************************************/

    private static <T> List<T> defensiveListCopy(final Collection<T> list) {
        return (list == null) ? new ArrayList<T>() : new ArrayList<T>(list);
    }

    private static List<Object> defensiveObjectListCopy(final Collection<?> list) {
        return (list == null) ? new ArrayList<Object>() : new ArrayList<Object>(list);
    }

    // if array is null, return null for later checking for null
    private static <T> Collection<T> convertArrayToCollection(final T... array) {
        return (array == null) ? null : Arrays.asList(array);
    }

    protected static void checkAlignmentsForNull(final Iterable<Alignment> alignments) {
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

    protected void checkNumColumns(final Collection<?> list) {
        if (numColumns == null) {
            // if numColumns is null, this is the first check for number of columns, so set it
            numColumns = list.size();
        } else {
            if (numColumns != list.size()) {
                throw new IllegalArgumentException(
                        "Wrong number of columns: " + list.size()
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

    public TextTableBuilder setHeadersCollection(@Nonnull final Collection<?> headers) {
        checkNotNull(headers, "headers cannot be null");
        checkNumColumns(headers);
        this.headers = defensiveObjectListCopy(headers);
        return this;
    }

    public TextTableBuilder setHeaders(@Nonnull final Object... headers) {
        return setHeadersCollection(convertArrayToCollection(headers));
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

    public TextTableBuilder setHeaderAlignmentsCollection(@Nonnull final Collection<Alignment> headerAlignments) {
        checkNotNull(headerAlignments, "header alignments cannot be null");
        checkAlignmentsForNull(headerAlignments);
        checkNumColumns(headerAlignments);
        this.headerAlignments = defensiveListCopy(headerAlignments);
        return this;
    }

    public TextTableBuilder setHeaderAlignments(@Nonnull final Alignment... headerAlignments) {
        return setHeaderAlignmentsCollection(convertArrayToCollection(headerAlignments));
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

    public TextTableBuilder addRowCollection(@Nonnull final Collection<?> row) {
        checkNotNull(row, "row cannot be null");
        checkNumColumns(row);
        rows.add(defensiveObjectListCopy(row));
        return this;
    }

    public TextTableBuilder addRow(@Nonnull final Object... row) {
        return addRowCollection(convertArrayToCollection(row));
    }

    public TextTableBuilder addRowsCollection(@Nonnull final Collection<? extends Collection<?>> rows) {
        checkNotNull(rows, "rows cannot be null");
        for (final Collection<?> row : rows) {
            addRowCollection(row);
        }
        return this;
    }

    public TextTableBuilder addRows(@Nonnull final Collection<?>... rows) {
        return addRowsCollection(convertArrayToCollection(rows));
    }

    public TextTableBuilder setRowsCollection(@Nonnull final Collection<? extends Collection<?>> rows) {
        checkNotNull(rows, "rows cannot be null");
        initRows(rows.size());
        return addRowsCollection(rows);
    }

    public TextTableBuilder setRows(@Nonnull final Collection<?>... rows) {
        return setRowsCollection(convertArrayToCollection(rows));
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

    public TextTableBuilder setRowAlignmentsCollection(@Nonnull final Collection<Alignment> rowAlignments) {
        checkNotNull(rowAlignments, "row alignments cannot be null");
        checkAlignmentsForNull(rowAlignments);
        checkNumColumns(rowAlignments);
        this.rowAlignments = defensiveListCopy(rowAlignments);
        return this;
    }

    public TextTableBuilder setRowAlignments(@Nonnull final Alignment... rowAlignments) {
        return setRowAlignmentsCollection(convertArrayToCollection(rowAlignments));
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
