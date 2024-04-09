package sokoban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.MapProperty;
import sokoban.model.Cell.*;

import java.util.Arrays;

public class Grid {
    private final int line;
    private final int col;

    private final Cell[][] matrix;

    private final LongBinding filledCellsCount;
    private final LongBinding filledPlayerCount;
    private final LongBinding filledBoxsCount;
    private final LongBinding filledTargetsCount;


    public Grid(int line, int col) {
        this.line = line;
        this.col = col;
        matrix = new Cell[col][line];
        for (int i = 0; i < col; ++i) {
            matrix[i] = new Cell[col];
            for (int j = 0; j < col; ++j) {
                matrix[i][j] = new Cell();
            }
        }

        //Compte le nombre de cases remplies et pas le nombre d'éléments

        filledCellsCount = Bindings.createLongBinding(() -> Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .filter(cell -> !cell.isEmpty())
                .count());

        filledPlayerCount = Bindings.createLongBinding(() -> Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getElementsProperty().getValue().get(1) instanceof Player)
                .count());

        filledBoxsCount = Bindings.createLongBinding(() -> Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getElementsProperty().getValue().get(1) instanceof Box)
                .count());

        filledTargetsCount = Bindings.createLongBinding(() -> Arrays
                .stream(matrix)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getElementsProperty().getValue().get(2) instanceof Target)
                .count());

    }

    void play(int line, int col, GameObject value) {
        this.setCell(line, col, value);
        filledCellsCount.invalidate();
        filledPlayerCount.invalidate();
        filledBoxsCount.invalidate();
        filledTargetsCount.invalidate();
    }

    public int getLine() {
        return this.line;
    }
    public int getCol() {
        return this.col;
    }

    MapProperty<Integer, GameObject> valueProperty(int line, int col) {
        return matrix[line][col].getElementsProperty();
    }

    void setCell(int line, int col, GameObject value) {
        if (value instanceof Player) {
            //suppr ancienne pos player
            //suppr player avec removeElement de ElementStack
            //saveposplayer
        }
        matrix[line][col].addElement(value);
    }

    public boolean isEmpty(int line, int col) {
        return matrix[line][col].isEmpty();
    }

    public LongBinding filledCellsCountProperty() {return filledCellsCount;}
    public LongBinding filledPlayerCountProperty() {return filledPlayerCount;}
    public LongBinding filledTargetsCountProperty() {return filledTargetsCount;}
    public LongBinding filledBoxsCountProperty() {return filledBoxsCount;}

}
