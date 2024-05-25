package jojikanabe.if2210_tb2_sks.classes.kartu;

public abstract class Item extends Kartu {
    public Item(String nama, String image) {
        super(nama, image);
    }

    public Item(Item original) {
        super(original);
    }

    public abstract void efek(int row, int col, int nomorPemain) throws Exception;
}
