package com.areeb.boxoffice.ui.main.tabs.home


import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.di.DependencyContainer
import com.areeb.boxoffice.di.fake.FakeHomeRepository
import com.areeb.boxoffice.repo.IHomeRepository
import com.areeb.boxoffice.util.MainCoroutineRule
import com.areeb.boxoffice.util.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var repo: IHomeRepository
    private val dependencyContainer = DependencyContainer()


    @BeforeEach
    fun setUp(){
        dependencyContainer.build()
        viewModel = dependencyContainer.homeViewModel
        repo = dependencyContainer.fakeHomeRepo
    }


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()




    @Test
    fun fetchMoviesByTypeWithMovieTypePopularThenShouldReturnListOfPopularMovies() = mainCoroutineRule.runTest  {



        viewModel.fetchMoviesByType(MovieType.Popular)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.popularMovies.value?.isNotEmpty(),
            Matchers.`is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.popularMovies.value?.size,
            Matchers.`is`(3)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }

    @Test
    fun fetchMoviesByTypeWithMovieTypeNowPlayingThenShouldReturnListOfNowPlayingMovies() = mainCoroutineRule.runTest  {



        viewModel.fetchMoviesByType(MovieType.NowPlaying)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.nowPlayingMovies.value?.isNotEmpty(),
            Matchers.`is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.nowPlayingMovies.value?.size,
            Matchers.`is`(3)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }


    @Test
    fun fetchMoviesByTypeWithMovieTypeUpcomingThenShouldReturnListOfUpcomingMovies() = mainCoroutineRule.runTest  {



        viewModel.fetchMoviesByType(MovieType.Upcoming)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.upcomingMovies.value?.isNotEmpty(),
            Matchers.`is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.upcomingMovies.value?.size,
            Matchers.`is`(3)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }



    @Test
    fun fetchMoviesByTypeWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeHomeRepository).setIsWithError(true)

        viewModel.fetchMoviesByType(MovieType.Upcoming)


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
    fun fetchTopRatedMoviesThenShouldReturnListOfTopRatedMovies() = mainCoroutineRule.runTest  {



        viewModel.fetchTopRatedMovies()


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.topRatedMovies.value?.isNotEmpty(),
            Matchers.`is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.topRatedMovies.value?.size,
            Matchers.`is`(3)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }


    @Test
    fun fetchTopRatedMoviesWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeHomeRepository).setIsWithError(true)

        viewModel.fetchTopRatedMovies()


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