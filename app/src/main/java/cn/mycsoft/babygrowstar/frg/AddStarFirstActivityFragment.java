package cn.mycsoft.babygrowstar.frg;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.entity.StarRecord;
import cn.mycsoft.babygrowstar.entity.StarTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddStarFirstActivityFragment extends AbstractFragment implements AbsListView.OnItemClickListener {


    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;
    private TextView emptyView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    public AddStarFirstActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_star_first, container, false);


        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        emptyView = (TextView) view.findViewById(R.id.empty);
//        initListDate();
//        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);


//        refreshTxt = (TextView) view.findViewById(R.id.refresh_txt);


        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof Activity) {
//            Activity activity = (Activity) context;
//
//            ActionBar actionBar = activity.getActionBar();
//            if (actionBar != null) {
//                setHasOptionsMenu(true);
//            }
//        }
//
//        initDate();
//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_add_star_first,menu);
//
//    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDate();
    }

    private void initDate() {
        //如果正在关闭应用或窗口时,调用此方法,可能会出异常.
        if (getActivity() != null) {
//            Cursor c = getController().findInputList();
            Cursor c = getController().findTaskList();
            if (c.getCount() > 0) {
                emptyView.setVisibility(View.GONE);
            }
            mAdapter = new ResourceCursorAdapter(getActivity(), R.layout.task_item_grid_item, c, 1) {
                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView titleTx, numberTx, timeTx;
                    titleTx = (TextView) view.findViewById(R.id.label);
                    numberTx = (TextView) view.findViewById(R.id.star_number);
//                    timeTx = (TextView) view.findViewById(R.id.time);
                    StarTask task = StarTask.parse(cursor);
                    titleTx.setText(task.getName());
                    numberTx.setText(String.valueOf(task.getNumber()));

                    //============================================
                    //类型颜色
//                    int typeColor = getResources().getColor(R.color.task_name);
//                    if (task.getType() == StarTask.Type.everyday) {
//                        typeColor = getResources().getColor(R.color.everyday_task_name);
//                    }
//                    numberTx.setTextColor(typeColor);
//                    titleTx.setTextColor(typeColor);
////                    timeTx.setTextColor(typeColor);
                    //============================================

//                    timeTx.setText(DateUtils.formatTimeFromNow(task.getModifyTime()));

                }

//            private void setText(View view,String column,int id){
//                TextView titleTx;
//                titleTx = (TextView)view.findViewById(R.id.label);
//                String c
//            }


                @Override
                public Object getItem(int position) {
                    return StarRecord.parse((Cursor) super.getItem(position));
                }
            };

            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //根据任务id,添加星星.
        getApp().openAddStarWithTask(getActivity(), 1000, l);
    }
}
