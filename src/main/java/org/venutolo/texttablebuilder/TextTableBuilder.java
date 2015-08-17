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
        initRows();
    }

    /**************************************************************************
     * STATIC UTILITY METHODS
     **************************************************************************/

    private static List<Alignment> defensiveAlignmentListCopy(final Collection<Alignment> collection) {
        // use to make a defensive copy of a collection that has already been checked for null
        return new ArrayList<Alignment>(collection);
    }

    private static Collection<Alignment> alignmentArrayToCollection(final Alignment... array) {
        // want to retain null for later null-checking
        return (array == null) ? null : Arrays.asList(array);
    }

    private static void checkAlignmentsForNull(final Iterable<Alignment> alignments) {
        int index = 0;
        for (final Alignment alignment : alignments) {
            checkNotNull(alignment, "alignment at index %s cannot be null", index);
            index++;
        }
    }

    private static List<Object> defensiveObjectListCopy(final Collection<?> collection) {
        // used to make a defensive copy of a collection that has already been checked for null
        return new ArrayList<Object>(collection);
    }

    private static Collection<Object> objectArrayToCollection(final Object... array) {
        // want to retain null for later null-checking
        return (array == null) ? null : Arrays.asList(array);
    }

    private static <T> List<T> listForOutput(final List<T> list) {
        // output is returned to user, so return empty collection instead of null
        return (list == null) ? new ArrayList<T>() : new ArrayList<T>(list);
    }

    /**************************************************************************
     * INSTANCE UTILITY METHODS
     **************************************************************************/

    private void initRows() {
        this.rows = new ArrayList<List<Object>>();
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

    protected TextTableBuilder setHeaderAlignmentsInternal(@Nonnull final Collection<Alignment> headerAlignments) {
        checkNotNull(headerAlignments, "header alignments cannot be null");
        checkAlignmentsForNull(headerAlignments);
        checkNumColumns(headerAlignments);
        this.headerAlignments = defensiveAlignmentListCopy(headerAlignments);
        return this;
    }

    public TextTableBuilder setHeaderAlignments(@Nonnull final Alignment... headerAlignments) {
        return setHeaderAlignmentsInternal(alignmentArrayToCollection(headerAlignments));
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

    protected TextTableBuilder setRowAlignmentsInternal(@Nonnull final Collection<Alignment> rowAlignments) {
        checkNotNull(rowAlignments, "row alignments cannot be null");
        checkAlignmentsForNull(rowAlignments);
        checkNumColumns(rowAlignments);
        this.rowAlignments = defensiveAlignmentListCopy(rowAlignments);
        return this;
    }

    public TextTableBuilder setRowAlignments(@Nonnull final Alignment... rowAlignments) {
        return setRowAlignmentsInternal(alignmentArrayToCollection(rowAlignments));
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

    protected TextTableBuilder setHeadersInternal(@Nonnull final Collection<?> headers) {
        checkNotNull(headers, "headers cannot be null");
        checkNumColumns(headers);
        this.headers = defensiveObjectListCopy(headers);
        return this;
    }

    public TextTableBuilder setHeaders(@Nonnull final Object... headers) {
        return setHeadersInternal(objectArrayToCollection(headers));
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

    protected TextTableBuilder addRowInternal(@Nonnull final Collection<?> row) {
        checkNotNull(row, "row cannot be null");
        checkNumColumns(row);
        rows.add(defensiveObjectListCopy(row));
        return this;
    }

    public TextTableBuilder addRow(@Nonnull final Object... row) {
        return addRowInternal(objectArrayToCollection(row));
    }

    public TextTableBuilder clearRows() {
        initRows();
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
