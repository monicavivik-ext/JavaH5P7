package hari5.penugasan7;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** PENUGASAN VII - TODO 4. Menulis file laporan papan peringkat. */
public class PenulisLaporan {

    /** SUDAH JADI. Salinan daftar, diurutkan skor tertinggi dulu; skor sama -> nama A-Z. */
    public static List<Entri> urutkan(List<Entri> daftar) {
        List<Entri> salinan = new ArrayList<>(daftar);
        salinan.sort(Comparator.comparingInt(Entri::getSkor).reversed().thenComparing(Entri::getNama));
        return salinan;
    }

    /**
     * TODO 4. Tulis laporan dengan PrintWriter di dalam try-with-resources. Format persis:
     *   PAPAN PERINGKAT
     *   1. Citra 12050
     *   2. Indah 11200
     *   ...                                  (paling banyak jumlahTeratas baris; pakai urutkan(...))
     *   Baris valid: 7 | Baris rusak: 4
     *   Baris 6: skor bukan angka: 'sembilan ribu'
     *   ...                                  (semua pesan galat, urut seperti di HasilBaca)
     * Petunjuk: Entri.toString() sudah menghasilkan "Citra 12050".
     */
    public static void tulis(String namaFile, HasilBaca hasil, int jumlahTeratas) throws IOException {
        List<Entri> daftarUrut = urutkan(hasil.getEntri());
        int batas = Math.max(0, Math.min(jumlahTeratas, daftarUrut.size()));
        try (PrintWriter out = new PrintWriter(new FileWriter(namaFile))){
            out.println("PAPAN PERINGKAT");
            for (int i = 0; i < batas; i++){
                out.println((i+1) + ". " + daftarUrut.get(i));
            }
            out.println("Baris valid: " + hasil.getEntri().size() + " | Baris rusak: " + hasil.getGalat().size());
            for (String galat: hasil.getGalat()){
                out.println(galat);
            }
        }
    }
}
