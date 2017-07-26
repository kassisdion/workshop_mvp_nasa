package com.eldorne.workshop.ui.form

import com.eldorne.workshop.BuildConfig
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*", "org.powermock.*")
@Config(manifest = Config.NONE, constants = BuildConfig::class)

class FormPresenterTest {

    @Mock
    var mFormView: FormView? = null

    var mPresenter: FormPresenter? = null

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)

        mPresenter = FormPresenter()
        mPresenter!!.onAttachView(mFormView!!)
    }

    @After fun tearDown() {
        mPresenter!!.onDetachView()
        mPresenter = null
        mFormView = null
    }

    @Test fun testSuccess() {
        mPresenter!!.onAttemptValidation("toto", "ttt")
        Mockito.verify<FormView>(mFormView, Mockito.times(1)).navigateToMainActivity("toto", "ttt")
    }
}