package tech.jour.viewpagerbottomsheet;

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.Nullable
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout


/**
 * create by Jiang HongLi
 * create on 2019/1/9 0009
 * description 用于viewpager+fragment+recyclerview的bottomsheetdialog
 */
class VPDialogFragment : BottomSheetDialogFragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewpager: ViewPager
    var fragments: List<Fragment> = arrayListOf()
    var titles: List<String> = arrayListOf()

    private val activeSize = 16f
    private val normalSize = 14f

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return ViewPagerBottomSheetDialog(requireContext(), R.style.basedialog_anim_style)
    }

    override fun onStart() {
        super.onStart()
        // 设置软键盘不自动弹出
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val dialog = dialog as ViewPagerBottomSheetDialog?
        val bottomSheet =
            dialog!!.delegate.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            val params = bottomSheet.layoutParams
            require(params is CoordinatorLayout.LayoutParams) { "The view is not a child of CoordinatorLayout" }
            val behavior = params.behavior
            require(behavior is ViewPagerBottomSheetBehavior<*>) { "The view is not associated with BottomSheetBehavior" }

//            val behavior = ViewPagerBottomSheetDialog.getBehavior(bottomSheet.layoutParams)
            val height =
                (dialog.context.resources.displayMetrics.heightPixels * 0.5).toInt()
            behavior.peekHeight = height

//            behavior.addBottomSheetCallback(mBottomSheetBehaviorCallback)
//            if (builder != null) {
//                val height =
//                    (dialog.context.resources.displayMetrics.heightPixels * builder!!.peekHeight).toInt()
//                behavior.peekHeight = height
//            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.dialog_vp, container, false)
        tabLayout = mRootView.findViewById(R.id.tablayout)
        viewpager = mRootView.findViewById(R.id.viewpager)
        // 注意要加上这句 让viewpager全部预加载
        viewpager.offscreenPageLimit = fragments.size
        viewpager.adapter = ViewPagerAdapter(
            childFragmentManager, fragments, titles
        )
        tabLayout.setupWithViewPager(viewpager)

        return mRootView
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
}
