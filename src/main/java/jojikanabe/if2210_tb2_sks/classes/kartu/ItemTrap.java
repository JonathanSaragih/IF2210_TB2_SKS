package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemTrap extends Item {
    public ItemTrap() {
        super("Trap");
    }

    @Override
    public void efek() {
        System.out.println("Efek item trap");
    }
}
