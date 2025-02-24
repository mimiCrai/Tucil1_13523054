# STIMA-Tucil

Program akan menerima sebuah input yang bisa merepresentasikan sebuah kondisi dalam permainan IqPuzzle.

## Persyaratan Instalasi

Untuk menjalankan program ini, Anda perlu menginstal:

- Java Development Kit (JDK) 8 atau lebih tinggi

### Langkah-langkah Instalasi

1. **Install JDK**: Unduh dan instal JDK dari [situs resmi Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) atau gunakan pengelola paket seperti `apt` untuk Ubuntu:
    ```sh
    sudo apt update
    sudo apt install openjdk-11-jdk
    ```

2. **clone Repository**:
    ```sh
    git clone https://github.com/username/STIMA-Tucil1.git
    cd STIMA-Tucil1
    ```

### Cara menjalankan program
    Cara kompilasi:
    -Pastikan path sudah di STIMA-Tucil1
    ```sh
    javac -d bin src/IqPuzzlePro.java
    ```
    
    Menjalankan program:
    ```sh
    java src/IqPuzzlePro.java
    ```
    Di dalam program, akan ditanya nama file input, yang harus sudah ada di dalam folder test dengan ekstensi ".txt".

#### File Input
    input HARUS diberikan sesuai dengan kriteria, yaitu:
    -N adalah banyak baris, M banyak kolom, dan B banyak jenis blok.
    -BOARD bisa berisi satu dari tiga jenis, yaitu DEFAULT/CUSTOM/PYRAMID. Ketika memilih custom, akan diminta untuk memberikan input board, dimana "X" adalah board yang bisa diletakkan sebuah block, dan "." adalah tempat block tidak boleh diletakkan. Kemudian, Apabila memilih PYRAMID, pastikan nilai N dan M sama.
    -Sisa baris berisi bentuk block, gunakan huruf kapital (A-Z) dan jangan gunakan huruf yang sama. Dengan demikian, banyak block maksimum adalah 26. Untuk setiap block, pastikan bahwa tidak ada bagian dari block yang terputus. Gunakan " " untuk bagian kosong dari block.
    Contoh block berbentuk U:
    U U
    U U
    UUU

    -Berikut adalah contoh file input yang benar:
    N M B
    BOARD
    B1
    B2
    ...
    BB


-oleh Aloisius Adrian Stevan Gunawan (13523054)