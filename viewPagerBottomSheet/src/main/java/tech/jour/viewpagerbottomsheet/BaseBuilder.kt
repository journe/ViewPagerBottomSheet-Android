package tech.jour.viewpagerbottomsheet

import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

abstract class BaseBuilder(@NonNull val activity: AppCompatActivity) {

    private var showTitle = true
    var peekHeight = 1.0


    fun setPeekHeight(peek: Double): BaseBuilder {
        peekHeight = peek
        return this
    }

    fun showTitle(showTitle: Boolean): BaseBuilder {
        this.showTitle = showTitle
        return this
    }


    abstract fun create(): BaseBottomSheetFragment

    fun show() {
        create().show(activity.supportFragmentManager)
    }
}