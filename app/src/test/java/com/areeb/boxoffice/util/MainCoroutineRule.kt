
package com.areeb.boxoffice.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext


@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineRule(
    private val testDispatcher: TestDispatcher = StandardTestDispatcher(),
    val testScope: TestScope = TestScope(testDispatcher),
) : BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext) {
        Dispatchers.resetMain()
    }

}

@OptIn(ExperimentalCoroutinesApi::class)
fun MainCoroutineRule.runTest(
    block: suspend TestScope.() -> Unit
) = this.testScope.runTest {
    block()
}
