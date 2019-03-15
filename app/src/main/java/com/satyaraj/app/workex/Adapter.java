package com.satyaraj.app.workex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.satyaraj.app.workex.pojo.Work;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Work> mWorkList = new ArrayList<>(0);

//    private ClickListener mClickListener;
    private MainActivity mMainActivity;

    Adapter(MainActivity mainActivity) {
//        this.mClickListener = mainActivity;
        this.mMainActivity = mainActivity;
    }

    void updateList(List<Work> workList) {
        mWorkList.clear();
        mWorkList.addAll(workList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        viewHolder = new ViewHeader(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Work work = mWorkList.get(position);
        ((ViewHeader) holder).onBind(work);
    }

    @Override
    public int getItemCount() {
        return mWorkList.size();
    }

    private class ViewHeader extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView firstName;
        TextView email;
        ImageView imageView;

        ViewHeader(View view) {
            super(view);
            firstName = view.findViewById(R.id.first_name);
            email = view.findViewById(R.id.email);
            imageView = view.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        void onBind(Work work) {
            firstName.setText(String.valueOf(work.getFirstName() + " " + work.getLastName()));
            email.setText(work.getEmail());
            Picasso.with(mMainActivity).load(work.getAvatar()).transform(new CircleImageTransform()).into(imageView);
        }

        @Override
        public void onClick(View view) {
//            mClickListener.onItemClicked();
        }
    }
}

