package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemDelay extends Item {
    public ItemDelay(String image) {
        super("Delay", image);
    }

    @Override
    public void efek() {
        System.out.println("Efek item delay");
    }
}
