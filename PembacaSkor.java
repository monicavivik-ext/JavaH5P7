package hari5.penugasan7;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** PENUGASAN VII - TODO 2 dan 3. Mengubah baris teks menjadi Entri, dan membaca satu file skor. */
public class PembacaSkor {
    /**
     * TODO 2. Format baris: nama;skor  (spasi di sekitar nama dan skor diabaikan).
     * Tolak dengan SkorTidakValidException bila:
     *   - tidak tepat dua bagian       -> "format harus nama;skor"
     *   - nama kosong                  -> "nama kosong"
     *   - skor bukan bilangan bulat    -> "skor bukan angka: '<teks>'"   (sertakan NumberFormatException sebagai penyebab)
     *   - skor negatif                 -> "skor negatif: <angka>"
     * Petunjuk: baris.split(";", -1) memecah teks; Integer.parseInt melempar NumberFormatException.
     */
    public static Entri parseBaris(String baris, int nomorBaris) throws SkorTidakValidException {
        String[] bagian = baris.split(";", -1);
        if (bagian.length != 2) {
            throw new SkorTidakValidException(nomorBaris, "format harus nama;skor");
}
        String nama = bagian[0].trim();
        String teksSkor = bagian[1].trim();
        
        if(nama.isEmpty()){
            throw new SkorTidakValidException(nomorBaris, "nama kosong");
        }
        int skor;
        try{
            skor = Integer.parseInt(teksSkor);
        } catch (NumberFormatException e){
            throw new SkorTidakValidException(nomorBaris, "skor bukan angka: '" + teksSkor + "'", e);
        }
        if(skor <0) {
            throw new SkorTidakValidException(nomorBaris, "skor negatif: " + skor);
        }
        return new Entri(nama, skor);
    }
    /**
     * TODO 3. Baca file baris demi baris (nomor baris mulai 1, dihitung untuk SEMUA baris).
     * Lewati baris kosong dan baris komentar yang diawali '#'.
     * Baris valid -> hasil.tambahEntri(...); baris rusak -> hasil.tambahGalat(e.getMessage()) lalu LANJUT.
     * File tidak ada -> biarkan FileNotFoundException keluar (sudah tercakup "throws IOException").
     * Petunjuk: try (BufferedReader in = new BufferedReader(new FileReader(namaFile))) { ... in.readLine() ... }
     */
    public static HasilBaca baca(String namaFile) throws IOException {
        HasilBaca hasil = new HasilBaca();
        
        try (BufferedReader in = new BufferedReader(new FileReader(namaFile))){
            String baris;
            int nomorBaris = 0;
            
            while ((baris = in.readLine()) != null){
                nomorBaris++;
                String teks = baris.trim();
                if (teks.isEmpty() || teks.startsWith("#")){
                    continue;
                }
                
                try{
                    Entri entri = parseBaris(baris, nomorBaris);
                    hasil.tambahEntri(entri);
                } catch (SkorTidakValidException e){
                    hasil.tambahGalat(e.getMessage());
                }
            }
        }
        return hasil;
    }
}
