## Skenario Unit Test dan Instrumentation Test | Submission 1

### Unit Test:
Disini saya menggunakan [API tmdb](https://developers.themoviedb.org/3) untuk datanya.
1. PageViewModelTest:
    - fetch movie and check isSuccessful
        - Request ke api lalu cek response bahwa sukses
    - check one title in movie
        - Request ke api lalu cek nilai objek pertama sama dengan test
    - fetch tvShow and check isSuccessful
        - Request ke api lalu cek response bahwa sukses
    - check one title in movie
        - Request ke api lalu cek nilai objek pertama sama dengan test
        
        
### Instrumentation Test:
1. Splash
    - Splash ke Home Screen:
        - Menunggu 2.5 detik
        - Cek title pada appbar sesuai dengan yang diharapkan
        - Cek kedua isi Tab Layout sesuai dengan yang diharapkan
        
2. Home
    - RV Movie:
        - Menunggu 2.5 detik
        - Cek ada Text di toolbar sesuai dengan yang diharapkan
        - Cek rv_movie ditampilkan
        - Cek isi item ke 0 dari rv_movie sesuai dengan yang diharapkan
        - Cari posisi item terakhir dengan Text yang sesuai dengan yang diharapkan
        - Cari text yang merupakan item terakhir
        
    - RV TV:
        - Menunggu 2.5 detik
        - Klik "TV Shows" yang ada di Tab Layout
        - Cek text di Tab Layout sesuai dengan yang diharapkan
        - Cek rv_tv ditampilkan
        - Cek isi item ke 0 dari rv_tv
        - Cari posisi item terakhir dengan Text yang sesuai dengan yang diharapkan
        - Cari text yang merupakan item terakhir
        
3. Detail
    - Home ke Detail:
        - Menunggu 2.5 detik
        - Cek rv_movie ditampilkan
        - Cek isi item ke 0 dari rv_movie sesuai dengan yang diharapkan
        - Klik item posisi 0 dari rv_movie
        - Cek title di Toolbar sesuai dengan yang diharapkan
        - Cek iv_poster, tv_title, tv_meta, rtb_rate dan tv_overview. Sesuai dengan yang diharapkan
        
    - Home ke Detail ke Home
        - Menunggu 2.5 detik
        - Klik item posisi 0 dari rv_movie
        - Cek tv_title, tv_meta sesuai dengan yang diharapkan
        - Klik navigasi pada toolbar
        - Memastikan text di appbar sesuai dengan yang diharapkan
        - Memastikan rv_movie ditampilkan