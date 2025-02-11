package sokoban.model.cell;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;

public abstract class ElementStack {
    protected final MapProperty<Integer, GameObject> elements = new SimpleMapProperty<>(FXCollections.observableHashMap());
    protected ElementStack() {
        elements.put(0, new Ground());
        elements.put(1, null);
        elements.put(2, null);
    }

    public void addElement(GameObject element) {
        if (element instanceof Wall || element instanceof Ground) {
            elements.put(0, element);
            elements.put(1, null);
            elements.put(2, null);
        } else if (element instanceof Player || element instanceof Box) {
            if (elements.get(0) instanceof Wall) {
                elements.put(0, new Ground());
            }
            elements.put(1, element);
            if (element instanceof Box) {
                elements.put(3, element);
            } else {
                elements.put(3, null);
            }
        } else if (element instanceof Target) {
            if (elements.get(0) instanceof Wall) {
                elements.put(0, new Ground());
            }
            elements.put(2, element);
        }
    }

    public void removeElement(GameObject element) {
        if (element instanceof Wall) {
            elements.getValue().put(0, new Ground());
        } else if (element instanceof Player || element instanceof Box) {
            elements.put(1, null);
            elements.put(3, null);
        } else if (element instanceof Target) {
            elements.put(2, null);

        }
    }

    public MapProperty<Integer, GameObject> getElementsProperty() {
        return elements;
    }
}
