package GameController;

import java.util.Optional;

public class RotationProperties {
    boolean isRotatable;
    Block axisOfRotation;

    public RotationProperties(boolean isRotatable, Block axisOfRotation){
        this.isRotatable = isRotatable;
        this.axisOfRotation = axisOfRotation;
    }
}
