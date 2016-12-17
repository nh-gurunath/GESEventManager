package com.globaledgesoft.eventmanagerges.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globaledgesoft.eventmanagerges.OrganizerFragment;
import com.globaledgesoft.eventmanagerges.R;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsListFragment extends Fragment {

    private String TAG = getClass().getSimpleName();

    private static Activity context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EventsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsListFragment newInstance(String param1, String param2) {
        EventsListFragment fragment = new EventsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);
        // buildList(view);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.lst_events);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        SimpleStringRecyclerViewAdapter adapter = new SimpleStringRecyclerViewAdapter(getActivity(), getContent());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void buildList(View view){

    }

    List<String> getContent(){
        List<String> lst = new ArrayList<>();
        lst.add("Blood Donations");
        lst.add("Swatch Bharath");
        lst.add("Joy of Giving");
        lst.add("Satsanga");
        lst.add("Youth for seva");
        lst.add("Sports meet");
        lst.add("Bhartnatyam");
        lst.add("Bhajana program");
        lst.add("Preaching from Yogi");
        lst.add("Vanamahotsva");
        lst.add("Hackathon");
        lst.add("Midnight Marathon");
        return lst;
    }
    public  class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {
        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;
        public  class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;
            public final View mView;
            public final ImageView mImageView;
            public final TextView mTextView;
            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }
            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }
        public String getValueAt(int position) {
            return mValues.get(position);
        }
        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mBoundString = mValues.get(position);
            holder.mTextView.setText(mValues.get(position));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*OrganizerFragment of = new OrganizerFragment();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.main_view, of, of.getClass().getSimpleName());
                    // ft.addToBackStack(null);
                    ft.commit();*/
                }
            });
            holder.mImageView.setBackgroundResource(R.drawable.event_logo);
        }
        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
           /* throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
