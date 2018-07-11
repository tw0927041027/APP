package tw.edu.nutc.imac.blockchainfingerprint.ui.store.list;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.databinding.FragmentListItemBinding;
import tw.edu.nutc.imac.blockchainfingerprint.util.ViewHolder;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class ListAdapt extends RecyclerView.Adapter<ViewHolder<FragmentListItemBinding>> implements ListContract.Adapt {
    private ArrayList<ListModel> mItemList;
    private ListContract.Presenter mPresenter;

    @Inject
    public ListAdapt(ListContract.Presenter presenter) {
        mItemList = new ArrayList<>();
        mPresenter = presenter;
    }

    @Override
    public ViewHolder<FragmentListItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentListItemBinding itemBinding =
                FragmentListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        itemBinding.setView(this);
        return new ViewHolder<>(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<FragmentListItemBinding> holder, int position) {
        final ListModel item = mItemList.get(position);
        item.setIsWhiteBackground(position % 2 == 0);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addItem(final ListModel item) {
        this.mItemList.add(item);
        this.notifyDataSetChanged();
    }

    @BindingAdapter(value = {"marginTop"}, requireAll = false)
    public static void setBottomMargin(View view, float topMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, Math.round(topMargin),
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void onItemClick(ListModel listModel) {
        mPresenter.onChangePointClickEvent(listModel);
    }
}
