package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemTrap extends Item {
    public ItemTrap(String image) {
        super("Trap", image);
    }

    @Override
    public void efek() {
        System.out.println("Efek item trap");
    }
}
