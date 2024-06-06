package com.areeb.boxoffice.ui.main.tabs.watch_list


import com.areeb.boxoffice.di.DependencyContainer
import com.areeb.boxoffice.di.fake.FakeMovieRepository
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.util.MainCoroutineRule
import com.areeb.boxoffice.util.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WatchListViewModelTest {

    private lateinit var viewModel: WatchListViewModel
    private lateinit var repo: IMovieRepository
    private val dependencyContainer = DependencyContainer()


    @BeforeEach
    fun setUp(){
        dependencyContainer.build()
        viewModel = dependencyContainer.watchListViewModel
        repo = dependencyContainer.fakeMovieRepo
    }


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()




    @Test
    fun getWatchListMoviesThenShouldReturnListOfWatchMovies() = mainCoroutineRule.runTest  {



        viewModel.getWatchListMovies()


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.movies.value?.isNotEmpty(),
            Matchers.`is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.movies.value?.size,
            Matchers.`is`(3)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }


    @Test
    fun getWatchListMoviesWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeMovieRepository).setIsWithError(true)

        viewModel.getWatchListMovies()


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