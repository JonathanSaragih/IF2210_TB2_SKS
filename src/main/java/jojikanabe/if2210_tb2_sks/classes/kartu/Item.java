package jojikanabe.if2210_tb2_sks.classes.kartu;

public abstract class Item extends Kartu {
    public Item(String nama, String image) {
        super(nama, image);
    }

    public abstract void efek();
}
