package com.eldorne.workshop.ui.form

import com.eldorne.workshop.BuildConfig
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by eldorne on 11/07/17.
 */
@RunWith(RobolectricTestRunner::class)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*", "org.powermock.*")
@Config(manifest = Config.NONE, constants = BuildConfig::class)

class FormPresenterTest {

    @Mock
    var mFormView: FormView? = null

    var mPresenter: FormPresenter? = null

    @Before
    public fun setUp()
    {
        MockitoAnnotations.initMocks(this)

        mPresenter = FormPresenter()
        mPresenter!!.onAttach(mFormView!!)
    }

    @After
    public fun tearDown()
    {
        mPresenter!!.onDetach()
        mPresenter = null
        mFormView = null
    }

    @Test
    public fun testSuccess()
    {
        mPresenter!!.onAttemptValidation("toto", "ttt")
        Mockito.verify<FormView>(mFormView, Mockito.times(1)).navigateToMainActivity("toto", "ttt")
    }
}