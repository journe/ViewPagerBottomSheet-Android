package tech.jour.viewpagerbottomsheet

import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

abstract class BaseBuilder(@NonNull val activity: AppCompatActivity) {
    var peekHeight = 1.0
    fun setPeekHeight(peek: Double): BaseBuilder {
        peekHeight = peek
        return this
    }

    protected abstract fun create(): BottomSheetViewPagerFragment

    fun show() {
        create().show(activity.supportFragmentManager)
    }
}