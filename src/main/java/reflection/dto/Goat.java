package reflection.dto;

import lombok.ToString;

@ToString
public class Goat extends Animal implements Locomotion {

    public Goat(String goat) {
        super(goat);
    }

    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }

    @Override
    public String eats() {
        return "grass";
    }
}
