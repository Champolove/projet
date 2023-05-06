import javafx.scene.control.ListCell;

public class CellChoix extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (!empty && item != null) {
            final String text = String.format("%s",item);
            setText(text);
        }
    }
}
