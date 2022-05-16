package tech.jour.viewpagerbottomsheet

class TestBuilderFragment : BottomSheetViewPagerFragment() {

    override fun rootView(): Int {
        return R.layout.dialog_vp_test
    }

    override fun viewpagerId(): Int {
        return R.id.viewpager
    }

    override fun tabLayoutId(): Int {
        return R.id.tablayout
    }
}