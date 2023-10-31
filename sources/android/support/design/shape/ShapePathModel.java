package android.support.design.shape;

import android.support.design.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
public class ShapePathModel {
    private static final CornerTreatment DEFAULT_CORNER_TREATMENT;
    private static final EdgeTreatment DEFAULT_EDGE_TREATMENT;
    private EdgeTreatment bottomEdge = DEFAULT_EDGE_TREATMENT;
    private CornerTreatment bottomLeftCorner = DEFAULT_CORNER_TREATMENT;
    private CornerTreatment bottomRightCorner = DEFAULT_CORNER_TREATMENT;
    private EdgeTreatment leftEdge = DEFAULT_EDGE_TREATMENT;
    private EdgeTreatment rightEdge = DEFAULT_EDGE_TREATMENT;
    private EdgeTreatment topEdge = DEFAULT_EDGE_TREATMENT;
    private CornerTreatment topLeftCorner = DEFAULT_CORNER_TREATMENT;
    private CornerTreatment topRightCorner = DEFAULT_CORNER_TREATMENT;

    static {
        CornerTreatment cornerTreatment;
        EdgeTreatment edgeTreatment;
        new CornerTreatment();
        DEFAULT_CORNER_TREATMENT = cornerTreatment;
        new EdgeTreatment();
        DEFAULT_EDGE_TREATMENT = edgeTreatment;
    }

    public ShapePathModel() {
    }

    public void setAllCorners(CornerTreatment cornerTreatment) {
        CornerTreatment cornerTreatment2 = cornerTreatment;
        this.topLeftCorner = cornerTreatment2;
        this.topRightCorner = cornerTreatment2;
        this.bottomRightCorner = cornerTreatment2;
        this.bottomLeftCorner = cornerTreatment2;
    }

    public void setAllEdges(EdgeTreatment edgeTreatment) {
        EdgeTreatment edgeTreatment2 = edgeTreatment;
        this.leftEdge = edgeTreatment2;
        this.topEdge = edgeTreatment2;
        this.rightEdge = edgeTreatment2;
        this.bottomEdge = edgeTreatment2;
    }

    public void setCornerTreatments(CornerTreatment topLeftCorner2, CornerTreatment topRightCorner2, CornerTreatment bottomRightCorner2, CornerTreatment bottomLeftCorner2) {
        this.topLeftCorner = topLeftCorner2;
        this.topRightCorner = topRightCorner2;
        this.bottomRightCorner = bottomRightCorner2;
        this.bottomLeftCorner = bottomLeftCorner2;
    }

    public void setEdgeTreatments(EdgeTreatment leftEdge2, EdgeTreatment topEdge2, EdgeTreatment rightEdge2, EdgeTreatment bottomEdge2) {
        this.leftEdge = leftEdge2;
        this.topEdge = topEdge2;
        this.rightEdge = rightEdge2;
        this.bottomEdge = bottomEdge2;
    }

    public CornerTreatment getTopLeftCorner() {
        return this.topLeftCorner;
    }

    public void setTopLeftCorner(CornerTreatment topLeftCorner2) {
        CornerTreatment cornerTreatment = topLeftCorner2;
        this.topLeftCorner = cornerTreatment;
    }

    public CornerTreatment getTopRightCorner() {
        return this.topRightCorner;
    }

    public void setTopRightCorner(CornerTreatment topRightCorner2) {
        CornerTreatment cornerTreatment = topRightCorner2;
        this.topRightCorner = cornerTreatment;
    }

    public CornerTreatment getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    public void setBottomRightCorner(CornerTreatment bottomRightCorner2) {
        CornerTreatment cornerTreatment = bottomRightCorner2;
        this.bottomRightCorner = cornerTreatment;
    }

    public CornerTreatment getBottomLeftCorner() {
        return this.bottomLeftCorner;
    }

    public void setBottomLeftCorner(CornerTreatment bottomLeftCorner2) {
        CornerTreatment cornerTreatment = bottomLeftCorner2;
        this.bottomLeftCorner = cornerTreatment;
    }

    public EdgeTreatment getTopEdge() {
        return this.topEdge;
    }

    public void setTopEdge(EdgeTreatment topEdge2) {
        EdgeTreatment edgeTreatment = topEdge2;
        this.topEdge = edgeTreatment;
    }

    public EdgeTreatment getRightEdge() {
        return this.rightEdge;
    }

    public void setRightEdge(EdgeTreatment rightEdge2) {
        EdgeTreatment edgeTreatment = rightEdge2;
        this.rightEdge = edgeTreatment;
    }

    public EdgeTreatment getBottomEdge() {
        return this.bottomEdge;
    }

    public void setBottomEdge(EdgeTreatment bottomEdge2) {
        EdgeTreatment edgeTreatment = bottomEdge2;
        this.bottomEdge = edgeTreatment;
    }

    public EdgeTreatment getLeftEdge() {
        return this.leftEdge;
    }

    public void setLeftEdge(EdgeTreatment leftEdge2) {
        EdgeTreatment edgeTreatment = leftEdge2;
        this.leftEdge = edgeTreatment;
    }
}
