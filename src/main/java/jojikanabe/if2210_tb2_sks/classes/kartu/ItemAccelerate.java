package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemAccelerate extends Item {
    public ItemAccelerate(String image) {
        super("Accelerate", image);
    }

    @Override
    public void efek() {
        System.out.println("Efek item accelerate");
    }
}
