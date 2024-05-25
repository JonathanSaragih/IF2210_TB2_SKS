package jojikanabe.if2210_tb2_sks.classes;

import javafx.util.Pair;
import jojikanabe.if2210_tb2_sks.classes.kartu.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Kelas GameState merupakan kelas yang menyimpan data permainan.
 * Kelas ini memakai design pattern singleton.
 */
public class GameState implements ConfigController {
    private static volatile GameState instance = null;
    public Integer giliran;
    private Integer turn;
    private Toko toko;
    private List<Pemain> pemain;
    private List<Kartu> dataKartu;

    private GameState() {
        this.loadKartu();
        this.loadToko();
    }

    public static GameState getInstance() {
        if (instance == null) {
            synchronized (GameState.class) {
                if (instance == null) {
                    instance = new GameState();
                }
            }
        }
        return instance;
    }

    public Toko getToko() {
        return toko;
    }

    public List<Pemain> getPemain() {
        return pemain;
    }

    public void NewGame() {
        this.pemain = new ArrayList<>();
        this.pemain.add(new Pemain("Pemain 1", 1000, 40, 0));
        this.pemain.add(new Pemain("Pemain 2", 1000, 40, 0));
        this.giliran = 1;
    }

    public void LoadGame(String folderName) {
        this.pemain = new ArrayList<>();
        this.loadConfig(folderName);
    }

    private void loadToko() {
        this.toko = new Toko();
        for (Kartu kartu : dataKartu) {
            if (kartu instanceof Produk) {
                toko.addStok((Produk) kartu, 0);
            }
        }
    }

    public List<Kartu> getDataKartu() {
        return dataKartu;
    }


    private void loadKartu() {
        this.dataKartu = new ArrayList<>();

        // Hewan
        dataKartu.add(new HewanKarnivora("Hiu Darat", "/assets/kartu/Hewan/hiu_darat.png", 20, 0));
        dataKartu.add(new HewanHerbivora("Sapi", "/assets/kartu/Hewan/sapi.png", 10, 0));
        dataKartu.add(new HewanHerbivora("Domba", "/assets/kartu/Hewan/domba.png", 12, 0));
        dataKartu.add(new HewanHerbivora("Kuda", "/assets/kartu/Hewan/kuda.png", 14, 0));
        dataKartu.add(new HewanOmnivora("Ayam", "/assets/kartu/Hewan/ayam.png", 5, 0));
        dataKartu.add(new HewanOmnivora("Beruang", "/assets/kartu/Hewan/beruang.png", 25, 0));

        // Tanaman
        dataKartu.add(new Tanaman("Biji Jagung", "/assets/kartu/Tanaman/biji_jagung.png", 3, 0));
        dataKartu.add(new Tanaman("Biji Labu", "/assets/kartu/Tanaman/biji_labu.png", 5, 0));
        dataKartu.add(new Tanaman("Biji Stroberi", "/assets/kartu/Tanaman/biji_stroberi.png", 4, 0));

        // Produk
        dataKartu.add(new Produk("Sirip Hiu", "/assets/kartu/Produk/sirip.png", 500, 12));
        dataKartu.add(new Produk("Susu", "/assets/kartu/Produk/susu.png", 100, 4));
        dataKartu.add(new Produk("Daging Domba", "/assets/kartu/Produk/dagingdomba.png", 120, 6));
        dataKartu.add(new Produk("Daging Kuda", "/assets/kartu/Produk/dagingkuda.png", 150, 8));
        dataKartu.add(new Produk("Telur", "/assets/kartu/Produk/telur.png", 50, 2));
        dataKartu.add(new Produk("Daging Beruang", "/assets/kartu/Produk/dagingberuang.png", 500, 12));
        dataKartu.add(new Produk("Jagung", "/assets/kartu/Produk/jagung.png", 150, 3));
        dataKartu.add(new Produk("Labu", "/assets/kartu/Produk/pumpkin.png", 500, 10));
        dataKartu.add(new Produk("Stroberi", "/assets/kartu/Produk/strawberry.png", 350, 5));

        // Item
        dataKartu.add(new ItemAccelerate("/assets/kartu/Item/item_Accelerate.png"));
        dataKartu.add(new ItemDelay("/assets/kartu/Item/delay.png"));
        dataKartu.add(new ItemInstantHarvest("/assets/kartu/Item/instant_harvest.png"));
        dataKartu.add(new ItemDestroy("/assets/kartu/Item/destroy.png"));
        dataKartu.add(new ItemProtect("/assets/kartu/Item/protect.png"));
        dataKartu.add(new ItemTrap("/assets/kartu/Item/trap.png"));

    }

    public void nextTurn() {
        giliran = giliran == 1 ? 2 : 1;
        pemain.get(0).getLadang().nextTurn();
        pemain.get(1).getLadang().nextTurn();
        turn++;
    }

    public String getTurn() {
        return "Turn " + turn;
    }

    public Integer getTurnInt() {return turn;}

    public String getWinner() {
    if (pemain.get(0).getGulden() > pemain.get(1).getGulden()) {
        return "Player 1 wins";
    } else if (pemain.get(0).getGulden() < pemain.get(1).getGulden()) {
        return "Player 2 wins";
    } else {
        return "It's a draw";
    }
}

    public String getGuldenPemain1() {
        return "Player 1: " + pemain.get(0).getGulden();
    }

    public String getGuldenPemain2() {
        return "Player 2: " + pemain.get(1).getGulden();
    }

    public String getDeckStatusPemain1() {
        return pemain.get(0).getDeck().getSize() + "/40";
    }

    public String getDeckStatusPemain2() {
        return pemain.get(1).getDeck().getSize() + "/40";
    }

    public Kartu getKartu(String nama) {
        for (Kartu kartu : dataKartu) {
            if (kartu.getNama().equals(nama)) {
                Kartu kartuBaru = Kartu.getCopy(kartu);
                return kartuBaru;
            }
        }
        return null;
    }

    @Override
    public void loadConfig(String folderName) {
        // Load game state
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + folderName + "/gamestate.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    turn = Integer.parseInt(line);
                    if (turn % 2 == 0) {
                        giliran = 2;
                    } else {
                        giliran = 1;
                    }
                } else if (i == 1) {

                } else {
                    String data[] = line.split(" ");

                    String nama = Helper.convertConfigtoString(data[0]);
                    Integer jumlah = Integer.parseInt(data[1]);
                    for (Kartu kartu : dataKartu) {
                        if (kartu.getNama().equals(nama)) {
                            toko.addStok((Produk) kartu, jumlah);
                        }
                    }
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load pemain
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + folderName + "/player1.txt"))) {
            String line;
            int i = 0;
            int jumlahDeck = 40;
            int jumlahDeckAktif = 0;
            int jumlahLadang = 0;
            int gulden = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    gulden = Integer.parseInt(line);
                } else if (i == 1) {
                    jumlahDeck = Integer.parseInt(line);
                    pemain.add(new Pemain("Pemain 1", gulden, jumlahDeck, jumlahDeckAktif));
                } else if (i == 2) {
                    jumlahDeckAktif = Integer.parseInt(line);
                } else if (i < 3 + jumlahDeckAktif) {
                    String data[] = line.split(" ");
                    int posisi = Helper.convertStringConfigToNumber(data[0]);
                    String nama = Helper.convertConfigtoString(data[1]);
                    pemain.get(0).addKartu(getKartu(nama), posisi);
                } else if (i == 3 + jumlahDeckAktif) {
                    jumlahLadang = Integer.parseInt(line);
                } else {
                    String data[] = line.split(" ");
                    Pair<Integer, Integer> posisi = Ladang.getLadangIndex(data[0]);
                    String nama = Helper.convertConfigtoString(data[1]);
                    Kartu kartu = getKartu(nama);
                    if (kartu instanceof Tanaman) {
                        ((Tanaman) kartu).setUmur(Integer.parseInt(data[2]));
                    } else {
                        ((Hewan) kartu).setBeratBadan(Integer.parseInt(data[2]));
                    }
                    try {
                        pemain.get(0).getLadang().setKartu(posisi.getKey(), posisi.getValue(), kartu);
                    } catch (Exception e) {
                        Kartu temp = pemain.get(0).getLadang().getKartu(posisi.getKey(), posisi.getValue());
                        System.out.println(e.getMessage());
                    }
                    for (int j = 4; j < data.length; j++) {
                        String namaItem = Helper.convertConfigtoString(data[j]);
                        Item item = (Item) getKartu(namaItem);
                        try {
                            pemain.get(0).getLadang().setKartuItems(posisi.getKey(), posisi.getValue(), item);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + folderName + "/player2.txt"))) {
            String line;
            int i = 0;
            int jumlahDeck = 40;
            int jumlahDeckAktif = 0;
            int gulden = 0;
            int jumlahLadang = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    gulden = Integer.parseInt(line);
                } else if (i == 1) {
                    jumlahDeck = Integer.parseInt(line);
                    pemain.add(new Pemain("Pemain 2", gulden, jumlahDeck, jumlahDeckAktif));
                } else if (i == 2) {
                    jumlahDeckAktif = Integer.parseInt(line);
                } else if (i < 3 + jumlahDeckAktif) {
                    String data[] = line.split(" ");
                    int posisi = Helper.convertStringConfigToNumber(data[0]);
                    String nama = Helper.convertConfigtoString(data[1]);
                    pemain.get(1).addKartu(getKartu(nama), posisi);
                } else if (i == 3 + jumlahDeckAktif) {
                    jumlahLadang = Integer.parseInt(line);
                } else {
                    String data[] = line.split(" ");
                    Pair<Integer, Integer> posisi = Ladang.getLadangIndex(data[0]);
                    String nama = Helper.convertConfigtoString(data[1]);
                    Kartu kartu = getKartu(nama);
                    if (kartu instanceof Tanaman) {
                        ((Tanaman) kartu).setUmur(Integer.parseInt(data[2]));
                    } else {
                        ((Hewan) kartu).setBeratBadan(Integer.parseInt(data[2]));
                    }
                    try {
                        pemain.get(1).getLadang().setKartu(posisi.getKey(), posisi.getValue(), kartu);
                    } catch (Exception e) {
                        Kartu temp = pemain.get(1).getLadang().getKartu(posisi.getKey(), posisi.getValue());
                        System.out.println(e.getMessage());
                    }
                    for (int j = 4; j < data.length; j++) {
                        String namaItem = Helper.convertConfigtoString(data[j]);
                        Item item = (Item) getKartu(namaItem);
                        try {
                            pemain.get(1).getLadang().setKartuItems(posisi.getKey(), posisi.getValue(), item);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveConfig(String foldername) {
        // Save game state
        try {
            Files.createDirectories(Paths.get("src/main/resources/" + foldername));

            FileWriter fileWriter = new FileWriter("src/main/resources/" + foldername + "/gamestate.txt");
            fileWriter.write(turn + "\n");
            int i = 0;
            for (Map.Entry<Produk, Integer> entry : toko.getDaftarProduk().entrySet()) {
                if (entry.getValue() > 0)
                    i++;
            }
            fileWriter.write(i + "\n");
            int j = 0;
            for (Map.Entry<Produk, Integer> entry : toko.getDaftarProduk().entrySet()) {
                if (entry.getValue() > 0) {
                    fileWriter.write(Helper.convertStringtoConfig(entry.getKey().getNama()) + " " + entry.getValue());
                    if (j < i - 1) {
                        fileWriter.write("\n");
                        j++;
                    }
                }
            }
            fileWriter.close();

            // Save pemain 1
            fileWriter = new FileWriter("src/main/resources/" + foldername + "/player1.txt");
            fileWriter.write(pemain.get(0).getGulden() + "\n");
            fileWriter.write(pemain.get(0).getDeck().getSize() + "\n");
            fileWriter.write(pemain.get(0).getDeckAktif().size() + "\n");
            for (int k = 0; k < pemain.get(0).getDeckAktif().size(); k++) {
                Kartu kartu = pemain.get(0).getDeckAktif().get(k);
                fileWriter.write(Helper.convertNumberToStringConfig(k) + " " + Helper.convertStringtoConfig(kartu.getNama()) + "\n");
            }
            int jumlahLadang = 0;
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 5; j++) {
                    Kartu kartu = pemain.get(0).getLadang().getKartu(i, j);
                    if (kartu != null) {
                        jumlahLadang++;
                    }
                }
            }
            if (jumlahLadang == 0) {
                fileWriter.write(jumlahLadang);
            } else {
                fileWriter.write(jumlahLadang + "\n");
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 5; j++) {
                        Kartu kartu = pemain.get(0).getLadang().getKartu(i, j);
                        if (kartu != null) {
                            fileWriter.write(Ladang.getLadangPosisi(i, j) + " " + Helper.convertStringtoConfig(kartu.getNama()));
                            if (kartu instanceof Tanaman) {
                                fileWriter.write(" " + ((Tanaman) kartu).getUmur());
                            } else {
                                fileWriter.write(" " + ((Hewan) kartu).getBeratBadan());
                            }
                            List<Kartu> items = pemain.get(0).getLadang().getKartuEffects(i, j);
                            fileWriter.write(" " + items.size());
                            for (int k = 0; k < items.size(); k++) {
                                fileWriter.write(" " + Helper.convertStringtoConfig(items.get(k).getNama()));
                            }
                            if (jumlahLadang > 1) {
                                fileWriter.write("\n");
                            }
                            jumlahLadang--;
                        }
                    }
                }
            }
            fileWriter.close();

            // Save pemain 2
            fileWriter = new FileWriter("src/main/resources/" + foldername + "/player2.txt");
            fileWriter.write(pemain.get(1).getGulden() + "\n");
            fileWriter.write(pemain.get(1).getDeck().getSize() + "\n");
            fileWriter.write(pemain.get(1).getDeckAktif().size() + "\n");
            for (int k = 0; k < pemain.get(1).getDeckAktif().size(); k++) {
                Kartu kartu = pemain.get(1).getDeckAktif().get(k);
                fileWriter.write(Helper.convertNumberToStringConfig(k) + " " + Helper.convertStringtoConfig(kartu.getNama()) + "\n");
            }
            jumlahLadang = 0;
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 5; j++) {
                    Kartu kartu = pemain.get(1).getLadang().getKartu(i, j);
                    if (kartu != null) {
                        jumlahLadang++;
                    }
                }
            }
            if (jumlahLadang == 0) {
                fileWriter.write(jumlahLadang);
            } else {
                fileWriter.write(jumlahLadang + "\n");
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 5; j++) {
                        Kartu kartu = pemain.get(1).getLadang().getKartu(i, j);
                        if (kartu != null) {
                            fileWriter.write(Ladang.getLadangPosisi(i, j) + " " + Helper.convertStringtoConfig(kartu.getNama()));
                            if (kartu instanceof Tanaman) {
                                fileWriter.write(" " + ((Tanaman) kartu).getUmur());
                            } else {
                                fileWriter.write(" " + ((Hewan) kartu).getBeratBadan());
                            }
                            List<Kartu> items = pemain.get(1).getLadang().getKartuEffects(i, j);
                            fileWriter.write(" " + items.size());
                            for (int k = 0; k < items.size(); k++) {
                                fileWriter.write(" " + Helper.convertStringtoConfig(items.get(k).getNama()));
                            }
                            if (jumlahLadang > 1) {
                                fileWriter.write("\n");
                            }
                            jumlahLadang--;
                        }
                    }
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
