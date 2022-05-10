package tech.jour.viewpagerbottomsheet

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


open class BaseBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var behavior: BottomSheetBehavior<FrameLayout>

    open var builder: BaseBuilder? = null

    private val mBottomSheetBehaviorCallback: BottomSheetBehavior.BottomSheetCallback =
        object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismissAllowingStateLoss()
                }
                Log.d("SheetBehaviorCallback",newState.toString())
//                if (newState == BottomSheetBehavior.STATE_DRAGGING) {//判断为向下拖动行为时，则强制设定状态为展开
//                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
//                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (context == null) {
            super.onCreateDialog(savedInstanceState)
        } else BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetStyle)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        // 设置软键盘不自动弹出
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val dialog = dialog as BottomSheetDialog?
        val bottomSheet =
            dialog!!.delegate.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.addBottomSheetCallback(mBottomSheetBehaviorCallback)

            if (builder != null) {
                val height =
                    (dialog.context.resources.displayMetrics.heightPixels * builder!!.peekHeight).toInt()
                behavior.peekHeight = height
            }
        }
    }

//    override fun setupDialog(dialog: Dialog, style: Int) {
//        super.setupDialog(dialog, style)
//        val behavior: CoordinatorLayout.Behavior = layoutParams.getBehavior()
//        if (behavior is BottomSheetBehavior) {
//            (behavior as BottomSheetBehavior).setBottomSheetCallback(mBottomSheetBehaviorCallback)
//            if (builder != null && builder!!.peekHeight > 0) {
//                (behavior as BottomSheetBehavior).peekHeight = builder!!.peekHeight.toInt()
//            }
//        }
//        if (builder == null) {
//            dismissAllowingStateLoss()
//            return
//        }
//    }

    open fun show(fragmentManager: FragmentManager) {
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

}