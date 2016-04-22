package cn.mycsoft.babygrowstar.frg;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.entity.StarRecord;
import cn.mycsoft.babygrowstar.entity.StarTask;
import cn.mycsoft.babygrowstar.util.DateUtils;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TaskItemFragment extends AbstractFragment implements AbsListView.OnItemClickListener, AbsListView.OnItemLongClickListener {

//    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    /**
     * 编辑工作栏
     */
    private ViewGroup editbar;

    private ImageButton removeBtn;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskItemFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initDate() {
        //如果正在关闭应用或窗口时,调用此方法,可能会出异常.
        if (getActivity() != null) {
//            Cursor c = getController().findInputList();
            Cursor c = getController().findTaskList();
            mAdapter = new ResourceCursorAdapter(getActivity(), R.layout.task_item_row_item, c, 1) {
                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView titleTx, numberTx, timeTx;
                    titleTx = (TextView) view.findViewById(R.id.label);
                    numberTx = (TextView) view.findViewById(R.id.star_number);
                    timeTx = (TextView) view.findViewById(R.id.time);
                    StarTask task = StarTask.parse(cursor);
                    titleTx.setText(task.getName());
                    numberTx.setText(String.valueOf(task.getNumber()));

                    //============================================
                    //类型颜色
                    int typeColor = getResources().getColor(R.color.task_name);
                    if (task.getType() == StarTask.Type.everyday) {
                        typeColor = getResources().getColor(R.color.everyday_task_name);
                    }
                    numberTx.setTextColor(typeColor);
                    titleTx.setTextColor(typeColor);
//                    timeTx.setTextColor(typeColor);
                    //============================================

                    timeTx.setText(DateUtils.formatTimeFromNow(task.getModifyTime()));

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasklist, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
//        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);


        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
//        mListView.setOnItemLongClickListener(this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initDate();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        editbar = (ViewGroup) view.findViewById(R.id.edit_pane);
//        removeBtn = (ImageButton) view.findViewById(R.id.remove_btn);
//        if (editbar.getVisibility() == View.VISIBLE) {
//            //隐藏工具栏.
//            editbar.setVisibility(View.GONE);
//        } else {
//            if (null != mListener) {
//                // Notify the active callbacks interface (the activity, if the
//                // fragment is attached to one) that an item has been selected.
////                mListener.onStarSelected(mAdapter.getItemId(position));
//                mListener.onStarSelected((StarRecord) mAdapter.getItem(position));
//            }
//        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public void reload() {
        initDate();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //长按删除.
        //显示删除按钮.
        editbar = (ViewGroup) view.findViewById(R.id.edit_pane);
        removeBtn = (ImageButton) view.findViewById(R.id.remove_btn);
        editbar.setVisibility(View.VISIBLE);
        return true;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        /**
         * 选择星星记录时.
         *
         * @param id 星星id.
         * @deprecated
         * @see #onStarSelected(StarRecord)
         */
//        @Deprecated
//        public void onStarSelected(Long id);

        /**
         * 选择星星记录时.
         *
         * @param star 星星信息。.
         */
        void onStarSelected(StarRecord star);
    }

}
