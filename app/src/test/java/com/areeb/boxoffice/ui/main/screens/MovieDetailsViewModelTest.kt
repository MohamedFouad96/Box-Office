package com.areeb.boxoffice.ui.main.screens

import com.areeb.boxoffice.di.DependencyContainer
import com.areeb.boxoffice.di.fake.FakeMovieRepository
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.ui.main.screens.MovieDetailsViewModel
import com.areeb.boxoffice.util.MainCoroutineRule
import com.areeb.boxoffice.util.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var repo: IMovieRepository
    private val dependencyContainer = DependencyContainer()


    @BeforeEach
    fun setUp(){
        dependencyContainer.build()
        viewModel = dependencyContainer.movieDetailsViewModel
        repo = dependencyContainer.fakeMovieRepo
    }


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()




    @Test
    fun getMovieDetailsThenShouldReturnMovieDetails() = mainCoroutineRule.runTest  {


        viewModel.getMovieDetails(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.movieDetails.value,
            notNullValue()
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }

    @Test
    fun getMovieDetailsWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeMovieRepository).setIsWithError(true)

        viewModel.getMovieDetails(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.errorState.value,
            Matchers.`is`("Something Wrong")
        )
        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )



    }



    @Test
    fun fetchMovieReviewsThenShouldReturnListOfReviews() = mainCoroutineRule.runTest  {


        viewModel.fetchMovieReviews(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.movieReviews.value,
            notNullValue()
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }

    @Test
    fun fetchMovieReviewsWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeMovieRepository).setIsWithError(true)

        viewModel.fetchMovieReviews(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.errorState.value,
            Matchers.`is`("Something Wrong")
        )
        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )



    }



    @Test
    fun fetchMovieCastThenShouldReturnListOfCast() = mainCoroutineRule.runTest  {


        viewModel.fetchMovieCast(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.movieCast.value,
            notNullValue()
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }

    @Test
    fun fetchMovieCastWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeMovieRepository).setIsWithError(true)

        viewModel.fetchMovieCast(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.errorState.value,
            Matchers.`is`("Something Wrong")
        )
        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )



    }


    @Test
    fun fetchMovieIsInWatchListThenShouldReturnMovieIsBookmarked() = mainCoroutineRule.runTest  {


        viewModel.fetchMovieIsInWatchList(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.isBookmarked.value,
            `is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }

    @Test
    fun fetchMovieIsInWatchListWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeMovieRepository).setIsWithError(true)

        viewModel.fetchMovieIsInWatchList(1)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.errorState.value,
            Matchers.`is`("Something Wrong")
        )
        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )



    }



}