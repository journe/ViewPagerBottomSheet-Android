package tech.jour.viewpagerbottomsheet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class BottomSheetBuilder(private val manager: FragmentManager) {
    private var mFragments: List<Fragment> = arrayListOf()
    private var mTitles: List<String> = arrayListOf()
    var mPeekHeight = 1.0
    private lateinit var mSheetViewPagerFragment: BottomSheetViewPagerFragment

    fun setFragments(fragments: List<Fragment>): BottomSheetBuilder {
        mFragments = fragments
        return this
    }

    fun setTitles(titles: List<String>): BottomSheetBuilder {
        mTitles = titles
        return this
    }

    fun setPeekHeight(peek: Double): BottomSheetBuilder {
        mPeekHeight = peek
        return this
    }

    fun build(): BottomSheetBuilder {
        if (mFragments.isEmpty() || mTitles.isEmpty())
            throw Exception("mFragments & mTitles must not be empty")

        mSheetViewPagerFragment =
            BottomSheetViewPagerFragment().apply {
//                peekHeight = mPeekHeight
                fragments = mFragments
                titles = mTitles
            }
        return this
    }

    fun show(tag: String = "") {
        mSheetViewPagerFragment.show(manager, tag)
    }
}