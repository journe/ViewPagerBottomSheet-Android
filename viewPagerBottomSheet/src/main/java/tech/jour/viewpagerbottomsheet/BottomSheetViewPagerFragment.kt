package tech.jour.viewpagerbottomsheet;

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout


open class BottomSheetViewPagerFragment : BottomSheetDialogFragment() {
    open var builder: Builder? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewpager: ViewPager
    var mFragments: List<Fragment> = arrayListOf()
    var mTitles: List<String> = arrayListOf()
    private var mPeekHeight = 1.0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return ViewPagerBottomSheetDialog(requireContext(), R.style.basedialog_anim_style)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as ViewPagerBottomSheetDialog
        // 设置软键盘不自动弹出
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        val bottomSheet =
            dialog.delegate.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            val params = bottomSheet.layoutParams
            require(params is CoordinatorLayout.LayoutParams) { "The view is not a child of CoordinatorLayout" }
            val behavior = params.behavior
            require(behavior is ViewPagerBottomSheetBehavior<*>) { "The view is not associated with BottomSheetBehavior" }
            if (builder != null) {
                mPeekHeight = builder!!.peekHeight
            }
            behavior.peekHeight =
                (dialog.context.resources.displayMetrics.heightPixels * mPeekHeight).toInt()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(rootView(), container, false)
        tabLayout = mRootView.findViewById(tabLayoutId())
        viewpager = mRootView.findViewById(viewpagerId())
        if (builder != null) {
            mFragments = builder!!.fragments
            mTitles = builder!!.titles
        }
        viewpager.offscreenPageLimit = mFragments.size
        viewpager.adapter = ViewPagerAdapter(childFragmentManager, mFragments, mTitles)
        tabLayout.setupWithViewPager(viewpager)

        return mRootView
    }

    open fun rootView(): Int {
        return R.layout.dialog_vp
    }

    open fun viewpagerId(): Int {
        return R.id.viewpager
    }

    open fun tabLayoutId(): Int {
        return R.id.tablayout
    }

    open fun show(fragmentManager: FragmentManager) {
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    private class ViewPagerAdapter(
        fm: FragmentManager,
        val fragments: List<Fragment>,
        val titles: List<String>
    ) : FragmentStatePagerAdapter(fm) {
        override fun getItem(i: Int): Fragment {
            return fragments[i]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }
    }

    open class Builder(@NonNull activity: AppCompatActivity) : BaseBuilder(activity) {

        var fragments: List<Fragment> = arrayListOf()
        var titles: List<String> = arrayListOf()


        fun setFragments(fragments: List<Fragment>): Builder {
            this.fragments = fragments
            return this
        }

        fun setTitles(titles: List<String>): Builder {
            this.titles = titles
            return this
        }

        override fun create(): BottomSheetViewPagerFragment {
            return BottomSheetViewPagerFragment().also { it.builder = this }
        }
    }
}
