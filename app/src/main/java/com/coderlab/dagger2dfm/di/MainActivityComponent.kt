package com.coderlab.dagger2dfm.di

import com.coderlab.dagger2dfm.MainActivity
import com.coderlab.dagger2dfm.core.di.CoreComponent
import com.coderlab.dagger2dfm.core.di.scope.ActivityScope
import dagger.Component


@ActivityScope // add scope, because we cannot use the same scope as the component we depend upon (CoreComponent).
@Component(
    modules = [MainActivityModule::class], // this feature's main/root module, it might contain submodules.
    dependencies = [CoreComponent::class] // so we can use all objects that CoreComponent provides
)
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}