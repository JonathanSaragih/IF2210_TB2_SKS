package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemAccelerate extends Item {
    public ItemAccelerate() {
        super("Accelerate");
    }

    @Override
    public void efek() {
        System.out.println("Efek item accelerate");
    }
}
