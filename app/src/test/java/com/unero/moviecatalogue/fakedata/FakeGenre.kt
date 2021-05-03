package com.unero.moviecatalogue.fakedata

import com.unero.moviecatalogue.data.remote.response.GenresItem

class FakeGenre {
    fun generateUpdated(): List<GenresItem>{
        return listOf(
                GenresItem("Action",28),
                GenresItem("Adventure",12),
                GenresItem("Animation",16),
                GenresItem("Comedy",35),
                GenresItem("Crime",80),
                GenresItem("Documentary", 99),
                GenresItem("Drama", 18),
                GenresItem("Family", 10751),
                GenresItem("Fantasy", 14),
                GenresItem("History", 36),
                GenresItem("Horror", 27),
                GenresItem("Music", 10402),
                GenresItem("Mystery", 9648),
                GenresItem("Romance", 10749),
                GenresItem("Science Fiction", 878),
                GenresItem("TV Movie", 10770),
                GenresItem("Thriller", 53),
                GenresItem("War", 10752),
                GenresItem("Western",37),
        )
    }

    fun generateDiffer(): List<GenresItem>{
        return listOf(
                GenresItem("Adventure",12),
                GenresItem("Animation",16),
                GenresItem("Comedy",35),
                GenresItem("Crime",80),
                GenresItem("Documentary", 99),
                GenresItem("Drama", 18),
                GenresItem("Family", 10751),
                GenresItem("Fantasy", 14),
                GenresItem("History", 36),
                GenresItem("Horror", 27),
                GenresItem("Music", 10402),
                GenresItem("Mystery", 9648),
                GenresItem("Romance", 10749),
                GenresItem("Science Fiction", 878),
                GenresItem("TV Movie", 10770),
                GenresItem("Thriller", 53),
                GenresItem("War", 10752),
                GenresItem("Western",37),
        )
    }
}
