package jojikanabe.if2210_tb2_sks.classes;

import javafx.util.Pair;
import jojikanabe.if2210_tb2_sks.classes.kartu.Item;
import jojikanabe.if2210_tb2_sks.classes.kartu.Kartu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ladang {
    private List<List<Map<Kartu, List<Kartu>>>> ladang;

    public Ladang() {
        this.ladang = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Map<Kartu, List<Kartu>>> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                row.add(new HashMap<>());
            }
            ladang.add(row);
        }
    }

    public static Pair<Integer, Integer> getLadangIndex(String posisi) {
        int row = (int) posisi.charAt(0) - (int) 'A';
        int col = Character.getNumericValue(posisi.charAt(2)) - 1;

        return new Pair<>(row, col);
    }

    public Kartu getKartu(int row, int col) {
        return this.ladang.get(row).get(col).keySet().stream().findFirst().orElse(null);
    }

    public List<Kartu> getKartuEffects(int row, int col) {
        return this.ladang.get(row).get(col).values().stream().findFirst().orElse(new ArrayList<>());
    }

    public void setKartu(int row, int col, Kartu kartu) throws Exception {
        if (this.ladang.get(row).get(col).isEmpty()) {
            this.ladang.get(row).get(col).put(kartu, new ArrayList<>());
        } else {
            throw new Exception("Kartu sudah ada di ladang");
        }
    }

    public void setKartuItems(int row, int col, Kartu item) throws Exception {
        if (!this.ladang.get(row).get(col).isEmpty()) {
            if (item instanceof Item) {
                this.ladang.get(row).get(col).get(getKartu(row, col)).add(item);
            } else {
                throw new Exception("Kartu bukan sebuah item");
            }
        } else {
            throw new Exception("Tidak ada kartu yang bisa diberikan item");
        }
    }

    public void removeKartu(int row, int col) {
        this.ladang.get(row).get(col).clear();
    }

    public void addKartu(int row, int col, Kartu kartu) {
        if (this.ladang.get(row).get(col).isEmpty()) {
            this.ladang.get(row).get(col).put(kartu, new ArrayList<>());
        }
    }
}
