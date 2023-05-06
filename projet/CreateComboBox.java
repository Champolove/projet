import javafx.scene.control.ComboBox;

public class CreateComboBox {
    ComboBox<String> comboBox;
    public CreateComboBox(int x,int y,int indice,String[] listeRecherche){
        comboBox=new ComboBox<>();
        comboBox.setButtonCell(new CellChoix());
        comboBox.setCellFactory(listView -> new CellChoix());
        comboBox.setLayoutX(x);
        comboBox.setLayoutY(y);
        comboBox.valueProperty().addListener(observable -> {
            listeRecherche[indice]=comboBox.getValue();
        });
    }
    
}
