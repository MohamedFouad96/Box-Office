package com.areeb.boxoffice.data.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.entity.MovieEntity
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.data.util.DataSourceConstants.PhotosConfig.MOVIES_PAGINATION_PAGE_SIZE
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MoviesDaoTest: KoinTest {

    private val dao: MoviesDao by inject()


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @After
    fun tearDown()= runTest {
        dao.clearAllData()
    }

    @Test
    fun getMoviesByTypeWithNowPlayingTypeThenShouldReturnNowPlayingMovies() = runTest {

        val movies = mutableListOf<MovieEntity>()

        for (i in 1..MOVIES_PAGINATION_PAGE_SIZE*3) {
            movies.add(MovieEntity(id = i))
        }
        dao.insertCategoryMovies(type =  MovieType.NowPlaying,movies)


        val retrievedPhotos = dao.getMoviesByType(MovieType.NowPlaying)
        assertThat(retrievedPhotos, notNullValue())
        assertThat(retrievedPhotos.size, `is`(MOVIES_PAGINATION_PAGE_SIZE*3))


    }

    @Test
    fun getMoviesByTypeWithPopularTypeThenShouldReturnPopularMovies() = runTest {

        val movies = mutableListOf<MovieEntity>()

        for (i in 1..MOVIES_PAGINATION_PAGE_SIZE*3) {
            movies.add(MovieEntity(id = i))
        }
        dao.insertCategoryMovies(type =  MovieType.Popular,movies)


        val retrievedPhotos = dao.getMoviesByType(MovieType.Popular)
        assertThat(retrievedPhotos, notNullValue())
        assertThat(retrievedPhotos.size, `is`(MOVIES_PAGINATION_PAGE_SIZE*3))


    }


    @Test
    fun getTopRatedMoviesThenShouldReturnTopRatedMovies() = runTest {

        val movies = mutableListOf<MovieEntity>()

        for (i in 1..MOVIES_PAGINATION_PAGE_SIZE*3) {
            movies.add(MovieEntity(id = i, voteAverage = i*0.2))
        }
        dao.insertCategoryMovies(type =  MovieType.TopRated,movies)


        val retrievedPhotos = dao.getTopRatedMovies()
        assertThat(retrievedPhotos, notNullValue())
        assertThat(retrievedPhotos.size, `is`(5))
        assertThat(retrievedPhotos.first().voteAverage > retrievedPhotos.last().voteAverage, `is`(true))


    }

    @Test()
    fun insertMoviesWithSingleMovieThenReturnInsertedMovie() = runTest {
        val movie = MovieEntity(id = 1)

        dao.insertCategoryMovies(type =  MovieType.NowPlaying,listOf(movie))


        val retrievedPhoto = dao.getMoviesByType(MovieType.NowPlaying).firstOrNull()

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto?.id, `is`(movie.id))

    }

    @Test()
    fun insertMoviesWithMultiplePhotosThenReturnInsertedMovies() = runTest {
        val movie1 = MovieEntity(id = 1)
        val movie2 = MovieEntity(id = 2)
        val movie3 = MovieEntity(id = 3)

        dao.insertCategoryMovies(type =  MovieType.NowPlaying,listOf(movie1, movie2 ,movie3 ))



        val retrievedPhotos = dao.getMoviesByType(MovieType.NowPlaying)
        assertTrue(retrievedPhotos.containsAll(listOf(movie1, movie2,movie3)))

    }

    @Test()
    fun insertMoviesWithEmptyMoviesThenReturnEmpty() = runTest {

        dao.insertMovies(listOf())


        val retrievedPhotos = dao.getAllMovies()

        assertThat(retrievedPhotos, notNullValue())
        assertThat(retrievedPhotos.isEmpty(), `is`(true))

    }



    @Test
    fun clearDataWithInsertedMoviesThenShouldReturnEmptyList() = runTest {

        val movie1 = MovieEntity(id = 1)
        val movie2 = MovieEntity(id = 2)
        val movie3 = MovieEntity(id = 3)

        dao.insertCategoryMovies(type =  MovieType.NowPlaying,listOf(movie1, movie2 ,movie3 ))

        dao.clearAllData()
        val retrievedPhotos = dao.getAllMovies()
        assertTrue(retrievedPhotos.isEmpty())
    }


    @Test
    fun getBookmarkedMoviesThenShouldReturnBookmarkedMovies() = runTest {

        val movies = mutableListOf<MovieEntity>()

        for (i in 1..MOVIES_PAGINATION_PAGE_SIZE*3) {
            movies.add(MovieEntity(id = i, isBookmarked = i % 2 == 0))
        }

        dao.insertCategoryMovies(type =  MovieType.Popular,movies)



        val retrievedPhotos = dao.getBookmarkedMovies()
        assertThat(retrievedPhotos, notNullValue())
        assertThat(retrievedPhotos.size, `is`((MOVIES_PAGINATION_PAGE_SIZE*3)/2))


    }

    @Test
    fun changeMovieBookmarkWithFalseThenShouldRemoveOneMovieFromBookmarkedMovies() = runTest {

        val movies = mutableListOf<MovieEntity>()

        for (i in 1..MOVIES_PAGINATION_PAGE_SIZE*3) {
            movies.add(MovieEntity(id = i, isBookmarked = i % 2 == 0))
        }

        dao.insertCategoryMovies(type =  MovieType.Popular,movies)

        dao.changeMovieBookmark(2, false)

        val retrievedPhotos = dao.getBookmarkedMovies()
        assertThat(retrievedPhotos, notNullValue())
        assertThat(retrievedPhotos.size, `is`(((MOVIES_PAGINATION_PAGE_SIZE*3)/2)-1))


    }

    @Test
    fun changeMovieBookmarkWithTrueThenShouldAddOneMovieFromBookmarkedMovies() = runTest {

        val movies = mutableListOf<MovieEntity>()

        for (i in 1..MOVIES_PAGINATION_PAGE_SIZE*3) {
            movies.add(MovieEntity(id = i, isBookmarked = i % 2 == 0))
        }
        dao.insertCategoryMovies(type =  MovieType.Popular,movies)


        dao.changeMovieBookmark(1, true)


        val retrievedPhotos = dao.getBookmarkedMovies()
        assertThat(retrievedPhotos, notNullValue())
        assertThat(retrievedPhotos.size, `is`(((MOVIES_PAGINATION_PAGE_SIZE*3)/2)+1))


    }


    @Test()
    fun getMoviesByIdWithSingleMovieThenReturnInsertedMovie() = runTest {
        val movie = MovieEntity(id = 1, isBookmarked = true)


        dao.insertCategoryMovies(type =  MovieType.NowPlaying,listOf(movie))



        val retrievedPhoto = dao.getMoviesById(1)

        assertThat(retrievedPhoto, notNullValue())
        assertThat(retrievedPhoto?.id, `is`(movie.id))

    }




}