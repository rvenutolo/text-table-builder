package org.venutolo.texttablebuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rick Venutolo
 */
public class TextTableBuilder {

    private BoxDrawingCharacters boxDrawingCharacters = BoxDrawingCharacters.LIGHT;

    private List<Alignment> headerAlignments;

    private List<Alignment> rowAlignments;

    private static <T> List<T> defensiveCopy(final List<T> list) {
        return new ArrayList<T>(list);
    }

    public TextTableBuilder setBoxDrawingCharacters(final BoxDrawingCharacters boxDrawingCharacters) {
        this.boxDrawingCharacters = boxDrawingCharacters;
        return this;
    }

    public BoxDrawingCharacters getBoxDrawingCharacters() {
        return boxDrawingCharacters;
    }

    public TextTableBuilder setHeaderAlignments(final List<Alignment> headerAlignments) {
        this.headerAlignments = defensiveCopy(headerAlignments);
        return this;
    }

    public TextTableBuilder setHeaderAlignments(final Alignment... headerAlignments) {
        return setHeaderAlignments(Arrays.asList(headerAlignments));
    }

    public List<Alignment> getHeaderAlignments() {
        return defensiveCopy(headerAlignments);
    }

    public TextTableBuilder setRowAlignments(final List<Alignment> rowAlignments) {
        this.rowAlignments = defensiveCopy(rowAlignments);
        return this;
    }

    public TextTableBuilder setRowAlignments(final Alignment... rowAlignments) {
        return setRowAlignments(Arrays.asList(rowAlignments));
    }

    public List<Alignment> getRowAlignments() {
        return defensiveCopy(rowAlignments);
    }

}
