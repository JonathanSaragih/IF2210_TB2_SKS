package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemTrap extends Item {
    public ItemTrap(String image) {
        super("Trap", image);
    }

    public ItemTrap(ItemTrap original) {
        super(original);
    }


    @Override
    public void efek(int row, int col, int nomorPemain) throws Exception {

    }
}
