package ru.app.globus.globustest.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.LinkedList;
import java.util.List;

import ru.app.globus.globustest.R;
import ru.app.globus.globustest.controller.DataManager;
import ru.app.globus.globustest.controller.event.SimpleDataCallback;
import ru.app.globus.globustest.model.Fail;
import ru.app.globus.globustest.model.TestItem;
import ru.app.globus.globustest.utils.MockData;
import ru.app.globus.globustest.view.adapter.CustomDraggableItemAdapter;
import ru.app.globus.globustest.view.event.SimpleDragOnDropListener;


/**
 * Created by Server on 14.01.2016.
 */

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private CustomDraggableItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerViewDragDropManager recyclerViewDragDropManager = new RecyclerViewDragDropManager();
        recyclerViewDragDropManager.setInitiateOnLongPress(true);
        recyclerViewDragDropManager.setInitiateOnMove(false);
        adapter = new CustomDraggableItemAdapter();
        RecyclerView.Adapter wrappedAdapter = recyclerViewDragDropManager.createWrappedAdapter(adapter);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrappedAdapter);

        recyclerViewDragDropManager.attachRecyclerView(recyclerView);
        recyclerViewDragDropManager.setOnItemDragEventListener(new SimpleDragOnDropListener() {
            @Override
            public void onItemDragFinished(int fromPosition, int toPosition, boolean result) {
                if (fromPosition != toPosition) {
                    List<TestItem> items = new LinkedList<TestItem>();
                    items.addAll(adapter.getData());
                    DataManager.saveTestItem(null, items);
                }
            }
        });
        DataManager.getTestItem(new SimpleDataCallback<List<TestItem>>() {
            @Override
            public void onSuccess(List<TestItem> result) {
                if (result.size() == 0) {
                    adapter.setData(MockData.getMockData());
                } else {
                    adapter.setData(result);
                }
            }

            @Override
            public void onFailure(Fail fail) {
                Log.e(TAG, fail.getMessage());
            }
        });
    }


}
