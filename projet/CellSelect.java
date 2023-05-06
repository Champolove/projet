import javafx.scene.control.ListCell;

public class CellSelect extends ListCell<Personne> {

    @Override
    protected void updateItem(Personne item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (!empty && item != null) {
            final String text = String.format("%s %s", item.nom, item.prenom);
            setText(text);
        }
    }
}