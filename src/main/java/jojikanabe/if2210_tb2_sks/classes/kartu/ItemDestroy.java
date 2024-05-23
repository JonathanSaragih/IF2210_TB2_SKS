package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemDestroy extends Item {
    public ItemDestroy(String image) {
        super("Destroy", image);
    }

    @Override
    public void efek() {
        System.out.println("Efek item destroy");
    }
}
