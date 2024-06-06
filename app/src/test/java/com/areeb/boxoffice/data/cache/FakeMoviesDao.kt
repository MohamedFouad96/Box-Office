package com.areeb.boxoffice.data.cache


import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.entity.CategoryEntity
import com.areeb.boxoffice.data.cache.entity.CategoryMovieCrossRef
import com.areeb.boxoffice.data.cache.entity.MovieEntity
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.data.util.CacheState
import com.areeb.boxoffice.data.remote.generateFakeMovieList
import com.areeb.boxoffice.data.remote.model.toEntity


class FakeMoviesDao(var state: CacheState = CacheState.Empty) : MoviesDao {

    private var moviesList = mutableListOf<MovieEntity>()
    private var categoriesList = mutableListOf<CategoryEntity>()
    private var categoryMovieCrossRefList = mutableListOf<CategoryMovieCrossRef>()
    private var currentPage = 0

    override suspend fun insertMovies(movies: List<MovieEntity>) {
        moviesList.addAll(movies)
    }

    override suspend fun insertCategory(category: CategoryEntity) {
        categoriesList.add(category)
    }

    override suspend fun insertCategoryMovieCrossRef(categoryMovieCrossRef: CategoryMovieCrossRef) {
        categoryMovieCrossRefList.add(categoryMovieCrossRef)
    }

    override suspend fun insertCategoryMovies(type: MovieType, movies: List<MovieEntity>) {
        insertCategory(CategoryEntity(type))
        insertMovies(movies)
        movies.forEach { movie ->
            insertCategoryMovieCrossRef(CategoryMovieCrossRef(type, movie.id))
        }
    }

    override suspend fun getMoviesByType(type: MovieType): List<MovieEntity> {
        initializeData(type)
        val movieIds = categoryMovieCrossRefList.filter { it.type == type }.map { it.id }
        return moviesList.filter { movieIds.contains(it.id) }.sortedBy { it.addedDate }
    }

    override suspend fun getMoviesById(movieId: Int): MovieEntity? {
        return generateFakeMovieList().toEntity().find { it.id == movieId }
    }

    override suspend fun getAllMovies(): List<MovieEntity> {
        return moviesList.sortedBy { it.addedDate }
    }

    override suspend fun getTopRatedMovies(type: MovieType): List<MovieEntity> {
        initializeData(type)
        val movieIds = categoryMovieCrossRefList.filter { it.type == type }.map { it.id }
        return moviesList
            .filter { movieIds.contains(it.id) }
            .sortedByDescending { it.voteAverage }
            .take(5)
    }

    override suspend fun getBookmarkedMovies(): List<MovieEntity> {
        return moviesList.filter { it.isBookmarked }
    }

    override suspend fun changeMovieBookmark(id: Int, isBookmarked: Boolean) {
        moviesList.find { it.id == id }?.isBookmarked = isBookmarked
    }

    override suspend fun clearAllData() {
        moviesList.clear()
        categoriesList.clear()
        categoryMovieCrossRefList.clear()
        currentPage = 0
    }

    override suspend fun clearMoviesByType(type: MovieType) {
        categoryMovieCrossRefList.removeIf { it.type == type }
    }

    private fun initializeData(type: MovieType) {
        when (state) {
            CacheState.WithFullData -> {
                if (currentPage == 0) {
                    moviesList = generateFakeMovieList().toEntity().toMutableList()
                    categoriesList.add(CategoryEntity(type))
                    moviesList.forEach { movie ->
                        categoryMovieCrossRefList.add(CategoryMovieCrossRef(type, movie.id))
                    }
                    currentPage++
                }
            }
            CacheState.WithHalfData -> {
                if (currentPage == 0) {
                    val movies = generateFakeMovieList().toEntity().toMutableList()
                    val firstHalf = movies.take(movies.size / 2)
                    moviesList = firstHalf.toMutableList()
                    categoriesList.add(CategoryEntity(type))
                    moviesList.forEach { movie ->
                        categoryMovieCrossRefList.add(CategoryMovieCrossRef(type, movie.id))
                    }
                    currentPage++
                }
            }
            CacheState.WithOldData -> {
                if (currentPage == 0) {
                    moviesList.add(MovieEntity(234234))
                    categoriesList.add(CategoryEntity(type))
                    categoryMovieCrossRefList.add(CategoryMovieCrossRef(type, 234234))
                    currentPage++
                }
            }
            else -> {}
        }
    }
}
