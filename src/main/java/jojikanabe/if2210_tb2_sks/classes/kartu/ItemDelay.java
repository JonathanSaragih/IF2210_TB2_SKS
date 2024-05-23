package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemDelay extends Item {
    public ItemDelay() {
        super("Delay");
    }

    @Override
    public void efek() {
        System.out.println("Efek item delay");
    }
}
