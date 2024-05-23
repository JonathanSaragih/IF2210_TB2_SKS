package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemDestroy extends Item {
    public ItemDestroy() {
        super("Destroy");
    }

    @Override
    public void efek() {
        System.out.println("Efek item destroy");
    }
}
