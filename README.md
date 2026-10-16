# PJJ Java Fundamental 2026 — Hari 5 (MP 2, bagian 3): Exception Handling, File dan Stream

Topik: **stack trace, try-catch, hierarki exception, checked vs unchecked, throw, throws, finally,
exception buatan sendiri, membaca dan menulis file teks, try-with-resources, teks vs biner, stream byte**.
Kode kompatibel Java 11 (lihat `pom.xml`). Buka folder ini di NetBeans: *File > Open Project*.
Jalankan satu demo: klik kanan file > **Run File** (Shift+F6).

**Penting:** semua contoh membaca dan menulis di folder `data/` di dalam folder proyek.
NetBeans menjalankan program dari folder proyek, jadi jalur `data/puisi.txt` langsung ditemukan.
File hasil (misalnya `data/puisi-bernomor.txt`) muncul di tab *Files* NetBeans setelah program dijalankan.

| Package | File | Isi |
|---|---|---|
| `hari5.sesi1.exception` | `KalkulatorRapuh` | Program yang sengaja tumbang: ArithmeticException, NumberFormatException, ArrayIndexOutOfBoundsException |
| | `BacaStackTrace` | Membaca stack trace: main -> rataRata -> bagi |
| | `DemoTryCatch` | try-catch, beberapa catch, multi-catch `|` |
| | `PohonException` | Hierarki exception dibuktikan dengan `getSuperclass()`; checked vs unchecked |
| | `DemoChecked` | Checked exception: tangani (try-catch) atau umumkan (`throws`) |
| | `DemoFinally` | Alur exception antar method dan klausa `finally` |
| | `InputUmur` | Meminta input sampai benar; `InputMismatchException` dan `in.next()` |
| `hari5.sesi1.lift` | `Lift`, `KelebihanMuatanException`, `DemoLift` | `throw`, `throws`, exception buatan sendiri |
| `hari5.sesi2.file` | `NomorBaris` | Baca dengan Scanner + FileReader, tulis dengan PrintWriter |
| | `NomorBarisModern` | Program yang sama dengan try-with-resources |
| | `BukuTamu` | Menulis menimpa vs menambah (`FileWriter(nama, true)`) |
| | `PilihFile` | JFileChooser + hitung baris dan kata |
| | `CaraRingkas` | Pengayaan: `java.nio.file.Files` (Java 11) |
| `hari5.sesi2.stream` | `TeksVsBiner` | 12345 sebagai teks (5 byte) vs biner (4 byte: 0 0 48 57) |
| | `SandiCaesar` | Membaca dan menulis byte demi byte; enkripsi Caesar |
| | `SimpanObjek` | Pengayaan: ObjectOutputStream, Serializable |
| `hari5.penugasan7` | `SkorTidakValidException`, `PembacaSkor`, `PenulisLaporan`, `AplikasiPeringkat`, `Entri`, `HasilBaca`, `UjiPeringkat` | **Penugasan VII Papan Peringkat** (I/O and exception handling): lengkapi 5 TODO, penguji 20 tes |
| `hari5.solusi.*` | | (paket pengajar) Kunci Penugasan VII, tantangan `PeringkatGui`, latihan `KalkulatorAman`, `StatistikFile` |

File di `data/`: `puisi.txt` (puisi lama, domain publik), `pesan-rahasia.txt` (untuk SandiCaesar), `skor.txt` (Penugasan VII).

`UjiPeringkat` membuat file uji sendiri di folder sementara sistem, jadi isi `data/` tidak berubah.
