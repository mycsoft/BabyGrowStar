package cn.mycsoft.babygrowstar.frg;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.act.AddTaskActivity;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * <p/>
 * interface.
 */
public class TaskListFragment extends AbstractFragment implements AbsListView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SwipeRefreshLayout swipeRefreshLayout;
    //    TextView refreshTxt;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean loading = false;
//    private OnFragmentInteractionListener mListener;

    private TaskItemFragment listFragment;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
//    private ListAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

//    private void initListDate() {
////        AbstractActivity act = ((AbstractActivity) getActivity());
////        if (act == null) {
////            return;
////        }
////        Cursor c = act.getController().findInputList();
////        CursorAdapter adapter = new InputCursorAdapter(c);
////        mAdapter = adapter;
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        initListDate();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasklist, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
//        initListDate();
//        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        //下拉刷新组件
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_light
        );
//        refreshTxt = (TextView) view.findViewById(R.id.refresh_txt);

        listFragment = (TaskItemFragment) getFragmentManager().findFragmentByTag("list");

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
        ActionBar actionBar = activity.getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_task_list, menu);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        getActivity().getMenuInflater().inflate(R.menu.menu_input_list,menu);
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onStarSelected(DummyContent.ITEMS.get(position).id);
//        }
        AddTaskActivity.startForEdit(getActivity(), id, AddTaskActivity.R_CHANGED);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                //打开添加任务画面。
//                Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
                AddTaskActivity.startForAdd(getActivity(), 1000);
                return true;
            case android.R.id.home:
                //返回上一画面。
                this.getActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    /**
//     * The default content for this Fragment has a TextView that is shown when
//     * the list is empty. If you would like to change the text, call this method
//     * to supply the text it should use.
//     */
//    public void setEmptyText(CharSequence emptyText) {
//        View emptyView = mListView.getEmptyView();
//
//        if (emptyView instanceof TextView) {
//            ((TextView) emptyView).setText(emptyText);
//        }
//    }

    /**
     * 刷新画面.
     */
    public void reloadList() {
//        initListDate();
//        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        if (listFragment != null) {
            listFragment.reload();
        }
    }

    @Override
    public void onRefresh() {
        if (loading) {
            return;
        }
        loading = true;
        //下拉刷新列表.
//        Toast.makeText(getActivity(),"刷新列表",Toast.LENGTH_SHORT).show();
//        refreshTxt.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

//                tv.setText("刷新完成");
//                refreshTxt.setVisibility(View.GONE);

                reloadList();
                swipeRefreshLayout.setRefreshing(false);
                loading = false;
            }
        }, 6000);

    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(String id);
//    }
//
//    private class InputCursorAdapter extends ResourceCursorAdapter {
//
//        public InputCursorAdapter(Cursor cursor) {
//            super(getActivity(), R.layout.input_item_row_item, cursor, true);
//        }
//
//        @Override
//        public void bindView(View view, Context context, Cursor cursor) {
////            if (view == null){
////                view = View.inflate(context,R.layout.input_item_row_item,null);
////            }
//
//            setText(view, R.id.label, cursor.getString(4));
//            setText(view, R.id.star_number, String.valueOf(cursor.getInt(1)));
//            Long time = cursor.getLong(2);
//            String df;
//            if (time == null) {
//                df = "未知时间";
//            } else {
//                DateFormat format = SimpleDateFormat.getDateTimeInstance();
//                df = format.format(new Date(time));
//            }
//            setText(view, R.id.time, df);
//
//
//        }
//
//        private void setText(View view, int id, CharSequence txt) {
//            TextView tv = (TextView) view.findViewById(id);
//            tv.setText(txt);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            Cursor c = getCursor();
//            if (c.moveToPosition(position)) {
//                return c.getLong(0);
//            } else {
//                return 0;
//            }
//        }
//    }

}