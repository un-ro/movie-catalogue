## Skenario Unit Test dan Instrumentation Test | Submission 1

### Unit Test:
Disini saya menggunakan [API tmdb](https://developers.themoviedb.org/3) untuk datanya.  
1. KoinModuleTest:
    - check modules
        - Melakukan cek bahwa module sudah di inject
2. RepositoryTest:
    - Get Movie from Network
        - Request ke api, jika sukses cek tidak null pada data.
    - Get TVShow from Network
        - Request ke api, jika sukses cek tidak null pada data.
    - Get Movie Genre and check if equal to Generate
        - Cek apakah data hasil generate dengan data api sama.
    - Get Movie Genre and check if different to Generate
        - Cek apakah data hasil generate dengan data api berbeda.
3. HomeViewModelTest:
    - Top Movies Success
        - Menjalankan fungsi dan menyimpan value ke MutableLiveData
        - Observe menggunakan LiveDataUtil
        - Cek bahwa value LiveData tidak null
   - Top TVShow Success
        - Menjalankan fungsi dan menyimpan value ke MutableLiveData
        - Observe menggunakan LiveDataUtil
        - Cek bahwa value LiveData tidak null
4. DetailViewModelTest:
    - A Get Genres
        - Menjalankan fungsi dan menyimpan value ke MutableLiveData
        - Observe menggunakan LiveDataUtil
        - Cek bahwa value LiveData tidak null
    - B Parse Genres
        - Generate Item Dummy
        - Menyamakan isi list parsing viewmodel dengan dummy
    - C Add Favorite
        - Object Spy milik repo melakukan insert object
        - Verifikasi apakah spy repo tersebut sudah melakukan insert
    - D Search Favorite
        - Object Spy milik repo melakukan pencarian object
        - Verifikasi apakah spy repo tersebut sudah melakukan pencarian
    - E Delete Favorite
        - Object Spy milik repo melakukan insert object
        - Object Spy milik repo melakukan delete object
        - Verifikasi apakah spy repo tersebut sudah melakukan insert
        - Verifikasi apakah spy repo tersebut sudah melakukan delete
5. FavoriteViewModelTest
    - Get PagedList but empty
        - Request ke viewmodel untuk mendapatkan nilai movie berupa PagedList
        - Mengecek nilai jika kosong
    - PagedList but not empty
        - Membuat Dummy List
        - Dummy dibuaat ke PagedList
        - Verifikasi bahwa LocalDataSource telah mengambil data
        
### Instrumentation Test:
1. HomeActivityTest
    - loadHome:
        - Menunggu Data Api untuk di load / fetch
        - Cek ada Text di toolbar sesuai dengan yang diharapkan
        - Cek ada Image yang ditampilkan di toolbar
        - Cek title tab apakah tampil dan sesuai
        - Cek rv_movie ditampilkan
        - Klik "TV Shows" yang ada di Tab Layout
        - Cek title tab apakah tampil dan sesuai
        - Cek rv_tv ditampilkan        
                
    - loadFavorite:
        - Cek ada item di menu dan lakukan klik
        - Cek ada Text di toolbar sesuai dan tampil
        - Cek ada Text di tablayout sesuai dan tampil
        - Cek ada tombol navigasi di toolbar sesuai dan tampil
        
    - loadDetail:
        - Cek rv_movie ditampilkan
        - Klik item ke 0 pada rv_movie
        - Cek bahwa isi dari favorite sudah ada dan ditampilkan
        
    - favoriteAddDelete (Harus dijalankan ketika favorite sedang kosong):
        - Cek rv_movie ditampilkan
        - Klik item ke 0 pada rv_movie
        - Klik favorite
        - Kembali ke home
        - Ke halaman favorite
        - Cek rv_movie pada favorite ditampilkan lalu klik item ke 0
        - Klik tombol favorite untuk menghapus favorite
        - Ke halaman Home lagi
        - Ke halaman favorite lagi lalu cek jika list sudah kosong