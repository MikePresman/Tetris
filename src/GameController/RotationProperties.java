package GameController;

public class RotationProperties {
    boolean isRotatable;
    Block axisOfRotation;

    public RotationProperties(boolean isRotatable, Block axisOfRotation){
        this.isRotatable = isRotatable;
        this.axisOfRotation = axisOfRotation;
    }
}
