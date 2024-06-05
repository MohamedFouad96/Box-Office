package com.areeb.boxoffice.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.areeb.boxoffice.data.cache.entity.CategoryEntity
import com.areeb.boxoffice.data.cache.entity.CategoryMovieCrossRef
import com.areeb.boxoffice.data.cache.entity.MovieEntity
import com.areeb.boxoffice.data.cache.entity.MovieType


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategoryMovieCrossRef(categoryMovieCrossRef: CategoryMovieCrossRef)


    @Query("""
        SELECT movieentity.* FROM movieentity
        INNER JOIN categorymoviecrossref ON movieentity.id = categorymoviecrossref.id
        WHERE categorymoviecrossref.type = :type
        ORDER BY addedDate
    """)
    suspend fun getMoviesByType(type: MovieType): List<MovieEntity>

    @Query("SELECT * FROM movieentity WHERE id = :movieId LIMIT 1")
    suspend fun getMoviesById(movieId: Int): MovieEntity?

    @Query("SELECT * FROM movieentity ORDER BY addedDate")
    suspend fun getAllMovies(): List<MovieEntity>


    @Query("""
        SELECT movieentity.* FROM movieentity
        INNER JOIN categorymoviecrossref ON movieentity.id = categorymoviecrossref.id
        WHERE categorymoviecrossref.type = :type
         ORDER BY voteAverage DESC LIMIT 5
    """)
    suspend fun getTopRatedMovies(type: MovieType = MovieType.TopRated): List<MovieEntity>


    @Query("SELECT * FROM movieentity WHERE isBookmarked")
    suspend fun getBookmarkedMovies(): List<MovieEntity>

    @Query("UPDATE movieentity SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun changeMovieBookmark(id: Int,isBookmarked: Boolean)

    @Query("DELETE FROM movieentity")
    suspend fun clearAllData()


    @Query("DELETE FROM CategoryMovieCrossRef WHERE type = :type")
    suspend fun clearMoviesByType(type: MovieType)


    @Transaction
    suspend fun insertCategoryMovies(type: MovieType, movies: List<MovieEntity>) {

        insertCategory(CategoryEntity(type))
        insertMovies(movies)

        movies.forEach { movie ->
            insertCategoryMovieCrossRef(CategoryMovieCrossRef(type,movie.id))
        }
    }
}