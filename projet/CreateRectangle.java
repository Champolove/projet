import javafx.scene.shape.Rectangle;

class CreateRectangle{
    Rectangle rectangle;
    public CreateRectangle(int x,int y,int width,int height,int angle,String color){
        rectangle=new Rectangle();
        rectangle.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
        rectangle.setFill(javafx.scene.paint.Color.valueOf(color));
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setArcHeight(angle);
        rectangle.setArcWidth(angle);
    }
}
