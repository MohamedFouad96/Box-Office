package com.areeb.boxoffice.di.fake



import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.model.MovieCast
import com.areeb.boxoffice.data.model.MovieDetails
import com.areeb.boxoffice.data.model.MovieReview
import com.areeb.boxoffice.data.util.Resource
import com.areeb.boxoffice.repo.IMovieRepository
import kotlinx.coroutines.flow.flow

class FakeMovieRepository: IMovieRepository {


    private var isWithError = false

    fun setIsWithError(isWithError: Boolean){
        this.isWithError = isWithError
    }


    override fun getWatchListMovies() = flow<Resource<List<Movie>>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = listOf(
            Movie(1,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
            Movie(2,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
            Movie(3,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
        )

        emit(Resource.success(response))
    }

    override fun searchForMovies(query: String) =  flow<Resource<List<Movie>>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = listOf(
            Movie(1,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
            Movie(2,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
            Movie(3,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
        )

        emit(Resource.success(response))
    }

    override suspend fun changeMovieBookmark(movieId: Int, isBookmarked: Boolean) {}

    override fun getMovieDetails(movieId: Int) = flow<Resource<MovieDetails>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = MovieDetails(1,"title", "overview", "backdrop", "poster", "releaseDate", "121 minutes", 12.4, "action")

        emit(Resource.success(response))
    }

    override fun getMovieReviews(movieId: Int) = flow<Resource<List<MovieReview>>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = listOf(
           MovieReview("content1","username","avatar", 12),
            MovieReview("content2","username","avatar", 12),
            MovieReview("content3","username","avatar", 12),

            )

        emit(Resource.success(response))
    }

    override fun getMovieCast(movieId: Int) = flow<Resource<List<MovieCast>>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = listOf(
            MovieCast("name1","profile"),
            MovieCast("name2","profile"),
            MovieCast("name3","profile")

        )

        emit(Resource.success(response))
    }

    override fun isMovieBookmarked(movieId: Int)= flow<Resource<Boolean>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }



        emit(Resource.success(true))
    }

}