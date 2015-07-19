package org.venutolo.texttablebuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilder {

    private BoxDrawingCharacters boxDrawingCharacters = BoxDrawingCharacters.LIGHT;

    private Alignment[] headerAlignments;

    private Alignment[] rowAlignments;

    private static <T> T[] copyArray(final T... array) {
        // TODO replace with own implementation if want to be 1.5 compatible
        return Arrays.copyOf(array, array.length);
    }

    private static Alignment[] getAlignmentArray(final List<Alignment> list) {
        return list.toArray(new Alignment[list.size()]);
    }

    public BoxDrawingCharacters getBoxDrawingCharacters() {
        return boxDrawingCharacters;
    }

    public TextTableBuilder setBoxDrawingCharacters(final BoxDrawingCharacters boxDrawingCharacters) {
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    public Alignment[] getHeaderAlignments() {
        return copyArray(headerAlignments);
    }

    public TextTableBuilder setHeaderAlignments(final Alignment... headerAlignments) {
        this.headerAlignments = copyArray(headerAlignments);
        return this;
    }

    public TextTableBuilder setHeaderAlignments(final List<Alignment> headerAlignments) {
        return setHeaderAlignments(getAlignmentArray(headerAlignments));
    }

    public Alignment[] getRowAlignments() {
        return copyArray(rowAlignments);
    }

    public TextTableBuilder setRowAlignments(final Alignment... rowAlignments) {
        this.rowAlignments = copyArray(rowAlignments);
        return this;
    }

    public TextTableBuilder setRowAlignments(final List<Alignment> rowAlignments) {
        return setRowAlignments(getAlignmentArray(rowAlignments));
    }

}
