package tw.edu.nutc.imac.blockchainfingerprint.ui.items;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.databinding.ActivityItemsItemBinding;
import tw.edu.nutc.imac.blockchainfingerprint.util.ViewHolder;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class ItemsAdapt extends RecyclerView.Adapter<ViewHolder<ActivityItemsItemBinding>> implements ItemsContract.Adapt {
    private ArrayList<ItemModel> mItemList;
    private ItemsContract.Presenter mPresenter;

    @Inject
    public ItemsAdapt(ItemsContract.Presenter presenter) {
        mItemList = new ArrayList<>();
        mPresenter = presenter;
    }

    @Override
    public ViewHolder<ActivityItemsItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityItemsItemBinding itemBinding =
                ActivityItemsItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        itemBinding.setView(this);
        return new ViewHolder<>(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ActivityItemsItemBinding> holder, int position) {
        final ItemModel item = mItemList.get(position);
        item.setIsWhiteBackground(position % 2 == 0);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addItem(final ItemModel item) {
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
    public void onItemClick(ItemModel itemModel) {
        if (itemModel.getIsLock()) {
            mPresenter.onUnlockClickEvent(itemModel);
        } else {
            mPresenter.onChangePointClickEvent(itemModel);
        }
    }
}
