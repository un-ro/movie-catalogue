## Skenario Unit Test dan Instrumentation Test | Submission 1

### Unit Test:
Disini saya menggunakan [API tmdb](https://developers.themoviedb.org/3) untuk datanya.  
1. KoinModuleTest:
    - check modules
        - Melakukan cek bahwa module sudah di inject
2. RepositoryTest:
    - Get Movie
        - Request ke api, jika sukses cek tidak null pada data.
        - Jika Gagal, cek tidak null pada pesan eksepsi.
    - Get TV Show
        - Request ke api, jika sukses cek tidak null pada data.
        - Jika Gagal, cek tidak null pada pesan eksepsi.
    - Get Movie Genre and check if equal to Generate
        - Cek apakah data hasil generate dengan data api sama.
    - Get Movie Genre and check if different to Generate
        - Cek apakah data hasil generate dengan data api berbeda.
3. SharedViewModelTest:
    - Setiap Fungsi
        - Menjalankan fungsi dan menyimpan value ke MutableLiveData
        - Observe menggunakan LiveDataUtil
        - Cek bahwa value LiveData tidak null
        
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
        - Cek iv_poster, tv_title, tv_meta, rtb_rate, cg_genres dan tv_overview ditampilkan.
        
    - Home ke Detail ke Home
        - Menunggu Data Api untuk di load / fetch
        - Cek rv_movie ditampilkan
        - Klik item posisi 0 dari rv_movie
        - Cek tv_title, tv_meta ditampilkan
        - Klik navigasi pada toolbar
        - Memastikan text di appbar sesuai dengan yang diharapkan
        - Memastikan rv_movie ditampilkan