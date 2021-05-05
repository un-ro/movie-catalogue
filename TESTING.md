## Skenario Unit Test dan Instrumentation Test | Submission 1

### Unit Test:

Disini saya menggunakan [API tmdb](https://developers.themoviedb.org/3) untuk
datanya.

1. GenreTest:
   - check if genres is same
     - Request ke api dan dibandingkan dengan data manual (Expect: Sukses)
   - check if genres is different
     - Request ke api dan dibandingkan dengan data manual (Expect: Gagal)
2. KoinModuleTest:
   - check modules
     - Melakukan cek bahwa module sudah di inject
3. RepositoryTest:
   - request api and success
     - Request api dan sukses
   - request api and fail
     - Request api dan gagal karena key tidak sesuai
   - request movie
     - Request data movie dan cek bila tidak null
   - request tv show
     - Request data tv show dan cek bila tidak null
   - request tv show but null
     - Request data tv show dan cek bila null

### Instrumentation Test:

1. Splash
   - Splash ke Home Screen:
     - Menunggu 2.5 detik
     - Cek title pada appbar sesuai dengan yang diharapkan
     - Cek kedua isi Tab Layout sesuai dengan yang diharapkan
2. Home
   - RV Movie:
     - Menunggu Data Api untuk di load / fetch
     - Cek ada Text di toolbar sesuai dengan yang diharapkan
     - Cek rv_movie ditampilkan
   - RV TV:
     - Menunggu Data Api untuk di load / fetch
     - Klik "TV Shows" yang ada di Tab Layout
     - Cek text di Tab Layout sesuai dengan yang diharapkan
     - Cek rv_tv ditampilkan
3. Detail
   - Home ke Detail:
     - Menunggu Data Api untuk di load / fetch
     - Cek rv_movie ditampilkan
     - Klik item posisi 0 dari rv_movie
     - Cek title di Toolbar sesuai dengan yang diharapkan
     - Cek iv_poster, tv_title, tv_meta, rtb_rate, cg_genres dan tv_overview
       ditampilkan.
   - Home ke Detail ke Home
     - Menunggu Data Api untuk di load / fetch
     - Cek rv_movie ditampilkan
     - Klik item posisi 0 dari rv_movie
     - Cek tv_title, tv_meta ditampilkan
     - Klik navigasi pada toolbar
     - Memastikan text di appbar sesuai dengan yang diharapkan
     - Memastikan rv_movie ditampilkan
