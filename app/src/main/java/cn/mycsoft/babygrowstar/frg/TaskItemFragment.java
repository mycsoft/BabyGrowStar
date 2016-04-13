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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.entity.StarRecord;

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
            Cursor c = getController().findInputList();
            mAdapter = new ResourceCursorAdapter(getActivity(), R.layout.pay_item_row_item, c, 1) {
                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView titleTx, numberTx, timeTx;
                    titleTx = (TextView) view.findViewById(R.id.label);
                    numberTx = (TextView) view.findViewById(R.id.star_number);
                    timeTx = (TextView) view.findViewById(R.id.time);
                    StarRecord star = StarRecord.parse(cursor);
                    titleTx.setText(star.getDesc());
                    numberTx.setText(String.valueOf(star.getNumber()));

                    //============================================
                    //类型颜色
                    int typeColor = getResources().getColor(R.color.add_star_text);
                    if (star.getType() == StarRecord.Type.use) {
                        typeColor = getResources().getColor(R.color.redeem_text);
                    }
                    numberTx.setTextColor(typeColor);
                    titleTx.setTextColor(typeColor);
                    timeTx.setTextColor(typeColor);
                    //============================================


                    Calendar now = Calendar.getInstance();
                    Calendar time = Calendar.getInstance();
                    time.setTime(star.getTime());
                    String timeString = null;
                    DateFormat format = null;
                    if (now.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
                        //同一年.

                        //与今天的差距(天).
                        int dT = now.get(Calendar.DAY_OF_YEAR) - time.get(Calendar.DAY_OF_YEAR);
                        switch (dT) {
                            case 0: //今天显示时间
                                format = new SimpleDateFormat("HH:mm");
                                timeString = format.format(time.getTime());

                                break;
                            case 1: //昨天
                                timeString = "昨天";
                                break;
                            case 2: //前天
                                timeString = "前天";
                                break;
                            default:    //同年,只显示月日.
                                format = new SimpleDateFormat("MM月dd日");
                                timeString = format.format(time.getTime());
                                break;
                        }

                    } else {
                        //不在同一年,只显示到日期.
                        format = new SimpleDateFormat("yyyy年MM月dd日");
                        timeString = format.format(time.getTime());
                    }

                    timeTx.setText(timeString);

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
        View view = inflater.inflate(R.layout.fragment_payitem, container, false);

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
