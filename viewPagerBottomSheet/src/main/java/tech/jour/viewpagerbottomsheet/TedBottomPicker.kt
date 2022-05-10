package tech.jour.viewpagerbottomsheet

import androidx.appcompat.app.AppCompatActivity


object TedBottomPicker : BottomSheetViewPagerFragment() {
    fun with(activity: AppCompatActivity): Builder {
        return Builder(activity)
    }

    class Builder(activity: AppCompatActivity) :
        BottomSheetViewPagerFragment.Builder(activity) {}
}