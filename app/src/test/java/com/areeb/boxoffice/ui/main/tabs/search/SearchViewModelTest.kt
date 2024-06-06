package com.areeb.boxoffice.ui.main.tabs.search


import com.areeb.boxoffice.di.DependencyContainer
import com.areeb.boxoffice.di.fake.FakeMovieRepository
import com.areeb.boxoffice.repo.IMovieRepository
import com.areeb.boxoffice.util.MainCoroutineRule
import com.areeb.boxoffice.util.runTest
import kotlinx.coroutines.isActive
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private lateinit var repo: IMovieRepository
    private val dependencyContainer = DependencyContainer()


    @BeforeEach
    fun setUp(){
        dependencyContainer.build()
        viewModel = dependencyContainer.searchViewModel
        repo = dependencyContainer.fakeMovieRepo
    }


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()




    @Test
    fun searchForMoviesThenShouldReturnListOfMovies() = mainCoroutineRule.runTest  {



        viewModel.searchForMovies("spider")


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
    fun searchForVisitorsWithEmptySearchThenShouldReturnSearchError() = mainCoroutineRule.runTest  {



        viewModel.searchForMovies("")


        assertTrue(testScheduler.isActive)

        testScheduler.advanceUntilIdle()

        MatcherAssert.assertThat(
            viewModel.searchTextIsError.value,
            Matchers.`is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }



    @Test
    fun fetchTopRatedMoviesWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeMovieRepository).setIsWithError(true)

        viewModel.searchForMovies("spider")


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