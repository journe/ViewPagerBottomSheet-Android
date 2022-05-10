package tech.jour.viewpagerbottomsheet

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class VP2DialogFragment() : BottomSheetDialogFragment() {

    //    override var builder: BaseBuilder? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewpager: ViewPager2
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
//        if (builder != null) {
//            fragments = (builder as Builder).fragments
//            titles = (builder as Builder).titles
//        }
        val mRootView = inflater.inflate(R.layout.dialog_vp2, container, false)
        tabLayout = mRootView.findViewById(R.id.tablayout)
        viewpager = mRootView.findViewById(R.id.viewpager)
        // 注意要加上这句 让viewpager全部预加载
//        viewpager.offscreenPageLimit = titles.size
        viewpager.adapter = ViewPagerAdapter(childFragmentManager, fragments)
        viewpager.registerOnPageChangeCallback(changeCallback);

        val mediator = TabLayoutMediator(
            tabLayout, viewpager
        ) { tab, position -> //这里可以自定义TabView
            val tabView = TextView(requireContext())
            val states = arrayOfNulls<IntArray>(2)
            states[0] = intArrayOf(android.R.attr.state_selected)
            states[1] = intArrayOf()
            val colors = intArrayOf(Color.BLUE, Color.BLACK)
            val colorStateList = ColorStateList(states, colors)
            tabView.text = titles[position]
            tabView.textSize = normalSize
            tabView.setTextColor(colorStateList)
            tab.customView = tabView
        }
        //要执行这一句才是真正将两者绑定起来
        mediator.attach()
        return mRootView
    }


    private val changeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //可以来设置选中时tab的大小
            val tabCount = tabLayout.tabCount
            for (i in 0 until tabCount) {
                val tab = tabLayout.getTabAt(i)
                val tabView = tab!!.customView as TextView?
                if (tab.position == position) {
                    tabView!!.textSize = activeSize
                    tabView.typeface = Typeface.DEFAULT_BOLD
                } else {
                    tabView!!.textSize = normalSize
                    tabView.typeface = Typeface.DEFAULT
                }
            }
        }
    }


    private inner class ViewPagerAdapter(fm: FragmentManager, val fragments: List<Fragment>) :
        FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }

//    open class Builder(@NonNull activity: AppCompatActivity) : BaseBuilder(activity) {
//
//        var fragments: List<Fragment> = arrayListOf()
//        var titles: List<String> = arrayListOf()
//
//
//        fun setFragments(fragments: List<Fragment>): Builder {
//            this.fragments = fragments
//            return this
//        }
//
//        fun setTitles(titles: List<String>): Builder {
//            this.titles = titles
//            return this
//        }
//
//        override fun create(behavior: ViewPagerBottomSheetBehavior<View>): VPDialogFragment {
//            val customBottomSheetDialogFragment = VPDialogFragment(behavior)
////            customBottomSheetDialogFragment.builder = this
//            return customBottomSheetDialogFragment
//        }
//    }

}