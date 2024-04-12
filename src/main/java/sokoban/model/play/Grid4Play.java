package sokoban.model.play;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.MapProperty;
import sokoban.model.api.Grid;
import sokoban.model.api.cell.GameObject;
import sokoban.model.api.cell.Player;

import java.util.Arrays;

public class Grid4Play extends Grid {
    private final Cell4Play[][] matrix;
    public Grid4Play(int line, int col) {
        this.line = line;
        this.col = col;
        matrix = new Cell4Play[col][line];
        for (int i = 0; i < col; ++i) {
            matrix[i] = new Cell4Play[col];
            for (int j = 0; j < col; ++j) {
                matrix[i][j] = new Cell4Play();
            }
        }

        filledPlayerCount = Bindings.createLongBinding(() -> Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .filter(cell4Design -> cell4Design.getElementsProperty().getValue().get(1) instanceof Player)
                .count());
    }

    protected void setCell(int line, int col, GameObject value) {
        if (value instanceof Player && filledPlayerCount.get() == 1) {
            matrix[getPosPlayerLine()][getPosPlayerCol()].removeElement(value);
            setPosPlayerLine(line);
            setPosPlayerCol(col);
        }
        if (value instanceof Player) {
            setPosPlayerLine(line);
            setPosPlayerCol(col);
        }
        matrix[line][col].addElement(value);
    }

    public Cell4Play getCell(int line, int col) {
        return matrix[line][col];
    }

    void play(int line, int col, GameObject value) {
        this.setCell(line, col, value);
        filledPlayerCount.invalidate();
    }

    public MapProperty<Integer, GameObject> valueProperty(int line, int col) {
        return matrix[line][col].getElementsProperty();
    }

    public boolean isEmpty(int line, int col) {
        return matrix[line][col].isEmpty();
    }

    public LongBinding filledPlayerCountProperty() {return filledPlayerCount;}
}
