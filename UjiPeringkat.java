package hari5.penugasan7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Penguji Penugasan VII. Jalankan file ini (Shift+F6). Target: 20 / 20.
 * Penguji membuat file sementara sendiri, jadi tidak mengubah isi folder data/.
 */
public class UjiPeringkat {

    private static int lulus = 0, total = 0;

    static void cek(String nama, Object harap, Object hasil) {
        total++;
        boolean ok = String.valueOf(harap).equals(String.valueOf(hasil));
        if (ok) {
            lulus++;
        }
        System.out.printf("%s %-42s harap=%-34s hasil=%s%n", ok ? "[LULUS]" : "[GAGAL]", nama, harap, hasil);
    }

    /** Menjalankan parseBaris; mengembalikan entri sebagai teks, atau pesan exception bila ditolak. */
    static String parse(String baris, int nomor) {
        try {
            return "OK " + PembacaSkor.parseBaris(baris, nomor);
        } catch (SkorTidakValidException e) {
            return e.getMessage();
        } catch (RuntimeException e) {
            return "bocor " + e.getClass().getSimpleName();
        }
    }

    static final String CONTOH =
            "# skor uji\n"            // 1
            + "Rani;9800\n"           // 2
            + "\n"                    // 3
            + "Dodi;sembilan\n"       // 4
            + " Citra ; 12050 \n"     // 5
            + "Eka;-5\n"              // 6
            + "Budi;7450\n"           // 7
            + "Hadi 5400\n"           // 8
            + "Joko;7450\n"           // 9
            + "Gita;8800\n";          // 10

    public static void main(String[] args) throws IOException {
        // ---------- TODO 1: SkorTidakValidException ----------
        SkorTidakValidException contoh = new SkorTidakValidException(4, "nama kosong");
        cek("1. pesan diawali nomor baris", "Baris 4: nama kosong", contoh.getMessage());
        cek("2. getNomorBaris", 4, contoh.getNomorBaris());
        cek("3. checked (bukan RuntimeException)", false, (Object) contoh instanceof RuntimeException);
        Throwable sebab = new NumberFormatException("x");
        cek("4. penyebab disimpan", true, new SkorTidakValidException(9, "y", sebab).getCause() == sebab);

        // ---------- TODO 2: parseBaris ----------
        cek("5. baris valid", "OK Rani 9800", parse("Rani;9800", 1));
        cek("6. spasi diabaikan", "OK Citra 12050", parse(" Citra ; 12050 ", 2));
        cek("7. tanpa titik koma", "Baris 3: format harus nama;skor", parse("Hadi 5400", 3));
        cek("8. tiga bagian", "Baris 4: format harus nama;skor", parse("a;b;c", 4));
        cek("9. nama kosong", "Baris 5: nama kosong", parse(";3000", 5));
        cek("10. skor bukan angka", "Baris 6: skor bukan angka: 'sembilan'", parse("Dodi;sembilan", 6));
        String penyebab;
        try {
            PembacaSkor.parseBaris("Dodi;sembilan", 6);
            penyebab = "tidak ditolak";
        } catch (SkorTidakValidException e) {
            penyebab = e.getCause() == null ? "null" : e.getCause().getClass().getSimpleName();
        } catch (RuntimeException e) {
            penyebab = "bocor " + e.getClass().getSimpleName();
        }
        cek("11. penyebab NumberFormatException", "NumberFormatException", penyebab);
        cek("12. skor negatif", "Baris 8: skor negatif: -5", parse("Eka;-5", 8));

        // ---------- TODO 3: baca ----------
        Path folder = Files.createTempDirectory("uji-peringkat");
        Path skor = folder.resolve("skor.txt");
        Files.write(skor, CONTOH.getBytes(StandardCharsets.UTF_8));
        HasilBaca hasil = PembacaSkor.baca(skor.toString());
        cek("13. jumlah entri valid", 5, hasil.getEntri().size());
        cek("14. jumlah baris rusak", 3, hasil.getGalat().size());
        cek("15. galat pertama + nomor baris asli", "Baris 4: skor bukan angka: 'sembilan'",
                hasil.getGalat().isEmpty() ? "-" : hasil.getGalat().get(0));
        String hilang;
        try {
            PembacaSkor.baca(folder.resolve("tidak-ada.txt").toString());
            hilang = "tidak dilempar";
        } catch (IOException e) {
            hilang = e.getClass().getSimpleName();
        }
        cek("16. file hilang -> FileNotFoundException", "FileNotFoundException", hilang);

        // ---------- TODO 4: tulis ----------
        Path laporan = folder.resolve("laporan.txt");
        PenulisLaporan.tulis(laporan.toString(), hasil, 3);
        List<String> isi = Files.exists(laporan) ? Files.readAllLines(laporan) : List.of();
        cek("17. baris judul dan juara", "PAPAN PERINGKAT / 1. Citra 12050",
                isi.size() < 2 ? isi : isi.get(0) + " / " + isi.get(1));
        cek("18. hanya 3 teratas", "3. Gita 8800 / Baris valid: 5 | Baris rusak: 3",
                isi.size() < 5 ? isi : isi.get(3) + " / " + isi.get(4));
        cek("19. galat ikut ditulis", "Baris 8: format harus nama;skor",
                isi.isEmpty() ? "-" : isi.get(isi.size() - 1));

        // ---------- TODO 5: jalankan ----------
        String masukHilang = folder.resolve("skor-kemarin.txt").toString();
        cek("20. file hilang ditangani", "File tidak ditemukan: " + masukHilang,
                AplikasiPeringkat.jalankan(masukHilang, laporan.toString()));

        System.out.println("\nSKOR: " + lulus + " / " + total);
        System.out.println(lulus == total ? "Papan peringkat siap. Jalankan AplikasiPeringkat." : "Masih ada yang GAGAL, periksa TODO terkait.");
    }
}
