package com.areeb.boxoffice.di.fake



import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.util.Resource
import com.areeb.boxoffice.repo.IHomeRepository
import kotlinx.coroutines.flow.flow

class FakeHomeRepository: IHomeRepository {


    private var isWithError = false

    fun setIsWithError(isWithError: Boolean){
        this.isWithError = isWithError
    }

    override fun getMoviesByType(firstPage: Boolean, type: MovieType)= flow<Resource<List<Movie>>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = listOf(
            Movie(1,"title", "overview", "poster", "backdrop", "releaseDate",9.0,true),
            Movie(2,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
            Movie(3,"title", "overview", "poster", "backdrop", "releaseDate",9.0,false),
            )

        emit(Resource.success(response))
    }

    override fun getTopRatedMovies() = flow<Resource<List<Movie>>> {
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

}