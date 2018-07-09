package tw.edu.nutc.imac.blockchainfingerprint.util;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;


/**
 * Created by 依杰 on 2017/8/28.
 */

public class ViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final B mViewDataBinding;

    public ViewHolder(B binding) {
        super(binding.getRoot());
        mViewDataBinding = binding;
    }

    public void bind(final Object object) {
        mViewDataBinding.setVariable(BR.data, object);
        mViewDataBinding.executePendingBindings();
    }

}