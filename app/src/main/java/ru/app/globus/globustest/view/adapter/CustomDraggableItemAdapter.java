package ru.app.globus.globustest.view.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import ru.app.globus.globustest.R;
import ru.app.globus.globustest.model.TestItem;
import ru.app.globus.globustest.utils.Utils;


/**
 * Created by Server on 15.01.2016.
 */
public class CustomDraggableItemAdapter extends RecyclerView.Adapter<CustomDraggableItemAdapter.Holder>
        implements DraggableItemAdapter<CustomDraggableItemAdapter.Holder> {
    private static final String TAG = "CustomDraggableAdapter";

    private interface Draggable extends DraggableItemConstants {
    }

    private List<TestItem> data = new ArrayList<>();

    public static class Holder extends AbstractDraggableItemViewHolder {
        public FrameLayout mContainer;
        public View mDragHandle;
        public TextView mTextView;

        public Holder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mDragHandle = v.findViewById(R.id.drag_handle);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
        }
    }

    public CustomDraggableItemAdapter() {
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate((viewType == 0) ? R.layout.draggable_list_item :
                R.layout.draggable_list_item2, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final TestItem data = this.data.get(position);
        holder.mTextView.setText(data.getName());
        final int dragState = holder.getDragStateFlags();
        if (((dragState & Draggable.STATE_FLAG_IS_UPDATED) != 0)) {
            int bgResId;
            if ((dragState & Draggable.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_item_dragging_active_state;
                Utils.clearState(holder.mContainer.getForeground());
            } else if ((dragState & Draggable.STATE_FLAG_DRAGGING) != 0) {
                bgResId = R.drawable.bg_item_dragging_state;
            } else {
                bgResId = R.drawable.bg_item_normal_state;
            }
            holder.mContainer.setBackgroundResource(bgResId);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<TestItem> data) {
        this.data.clear();
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        if (fromPosition != toPosition) {
            final TestItem item = data.remove(fromPosition);
            data.add(toPosition, item);
            notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public boolean onCheckCanStartDrag(Holder holder, int position, int x, int y) {
        final View containerView = holder.mContainer;
        final View dragHandleView = holder.mDragHandle;

        final int offsetX = containerView.getLeft() +
                (int) (ViewCompat.getTranslationX(containerView) + 0.5f);
        final int offsetY = containerView.getTop() +
                (int) (ViewCompat.getTranslationY(containerView) + 0.5f);
        return Utils.hitTest(dragHandleView, x - offsetX, y - offsetY);
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(Holder holder, int position) {
        // no drag-sortable range specified
        return null;
    }

    public List<TestItem> getData() {
        return data;
    }
}
