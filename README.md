# UTS Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: Wendi Rahmawan</li>
  <li>NIM: 23552011208</li>
  <li>Studi Kasus: Manajemen ToDo dengan Login</li>
</ul>

## Judul Studi Kasus
<p>Manajemen ToDo dengan Login</p>

## Penjelasan Studi Kasus
<p>Aplikasi yang dibuat adalah Manajemen ToDo atau Manajemen Daftar Kegiatan dari seseorang, dimana disini User diharuskan untuk login dan setiap user hanya bisa me manage Daftar Kegiatan milik mereka sendiri. Manajemen kegiatan dari aplikasi ini meliputi Tambah Kegiatan, Edit/Update Kegiatan (Ubah nama kegiatan dan ubah status menjadi selesai), Hapus kegiatan. di Menu dashboard menampilkan Daftar kegiatan dengan filter Semua, Belum Selesai, dan Selesai</p>

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Inheritance adalah konsep pewarisan properti dan method dari satu class (superclass) ke class lain (subclass). Dengan inheritance, kita bisa membuat class baru yang mewarisi atribut dan perilaku dari class yang sudah ada, sehingga menghindari penulisan ulang kode (code duplication).Di dalam aplikasi ini, saya membuat kelas CustomUserDetails yang mengimplementasikan interface UserDetails. Dengan pewarisan ini, saya bisa memanfaatkan fitur otentikasi dari Spring tanpa harus menulis semuanya dari awal. User yang digunakan untuk login diambil dari DataBase yang saya buat sendiri dengan tetap menggunakan SpringSecurity sebagai login.</p>

### 2. Encapsulation
<p>Encapsulation adalah konsep pembungkusan data (atribut) dan perilaku (method) dalam satu kesatuan class, sekaligus membatasi akses langsung terhadap data tersebut dari luar class. Implementasi Encapsulation ini diterapkan Untuk menjaga integritas data dan mencegah manipulasi langsung dari luar kelas ada pada model ToDo dan User dimana pada kedua file tersebut ada metode setter dan getter agar data hanya bisa diakses dan dimodifikasi melalui metode resmi.</p>

### 3. Polymorphism
<p>Polimorfisme memungkinkan objek untuk memiliki banyak bentuk (bentuk perilaku yang berbeda) meskipun berada pada satu tipe data yang sama. Dalam aplikasi ini, class CustomUserDetails mengimplementasikan interface UserDetails. Hal ini memungkinkan objek bertipe UserDetails dapat merujuk ke instance CustomUserDetails. Saat Spring Security memanggil method seperti getUsername() atau getAuthorities(), yang dipanggil adalah method milik CustomUserDetails, bukan bawaan dari UserDetails. Ini adalah contoh polimorfisme: satu antarmuka (UserDetails), tetapi perilaku berbeda tergantung pada class implementasinya.</p>

### 4. Abstract
<p>Abstraksi merupakan salah satu prinsip dasar OOP yang berfungsi untuk menyederhanakan kompleksitas sistem dengan menyembunyikan detail implementasi dan hanya menampilkan fitur penting kepada pengguna. Dalam proyek ini, abstraksi dapat dilihat melalui penggunaan interface UserDetailsService. Interface tersebut mendefinisikan method loadUserByUsername(String username) tanpa menjelaskan bagaimana proses otentikasi sebenarnya dilakukan. Implementasinya kemudian ditangani oleh class CustomUserDetailsService, yang berisi logika lengkap untuk mengambil data pengguna dari database.</p>

## Demo Proyek
<ul>
  <li>Github: <a href="https://github.com/wenrahma/UTS_PBO2_TIFK23A_23552011208">Github</a></li>
  <li>Youtube: <a href="">Youtube</a></li>
</ul>
