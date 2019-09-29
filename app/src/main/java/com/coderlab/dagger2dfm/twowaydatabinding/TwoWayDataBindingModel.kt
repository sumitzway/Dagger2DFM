package com.coderlab.dagger2dfm.twowaydatabinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.coderlab.dagger2dfm.BR


class TwoWayDataBindingModel : BaseObservable() {

    @Bindable
    var userEmail: String? = "dummy@gmail.com"
        set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.userEmail)
            }
        }


}