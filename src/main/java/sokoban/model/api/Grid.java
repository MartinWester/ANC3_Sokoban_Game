package sokoban.model.api;

import javafx.beans.binding.LongBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sokoban.model.api.cell.GameObject;
import sokoban.model.api.cell.Player;
import sokoban.model.design.Cell4Design;

public abstract class Grid {
    protected int line;
    protected int col;
    protected final IntegerProperty posPlayerLine = new SimpleIntegerProperty();
    protected final IntegerProperty posPlayerCol = new SimpleIntegerProperty();
    protected LongBinding filledPlayerCount;
    public int getPosPlayerLine() {
        return posPlayerLine.get();
    }

    public IntegerProperty posPlayerLineProperty() {
        return posPlayerLine;
    }

    public int getPosPlayerCol() {
        return posPlayerCol.get();
    }

    public IntegerProperty posPlayerColProperty() {
        return posPlayerCol;
    }

    public void setPosPlayerLine(int posPlayerLine) {
        this.posPlayerLine.set(posPlayerLine);
    }

    public void setPosPlayerCol(int posPlayerCol) {
        this.posPlayerCol.set(posPlayerCol);
    }
    public int getLine() {
        return this.line;
    }
    public int getCol() {
        return this.col;
    }
}
