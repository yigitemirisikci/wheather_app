package com.example.wheather_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheather_app.databinding.FragmentABinding;
import com.example.wheather_app.databinding.ViewItemBinding;

import java.util.ArrayList;

public class SlidePagerAdapter extends RecyclerView.Adapter<SlidePagerAdapter.ViewHolder> {
    private ArrayList<ViewItem> fragmentList;

    //Constructor
    public SlidePagerAdapter(ArrayList<ViewItem> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public SlidePagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewItemBinding viewItemBinding = ViewItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(viewItemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewItem viewItem = fragmentList.get(position);
        holder.viewItemBinding.setXMLITEM(viewItem); //data binding
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewItemBinding viewItemBinding;

        public ViewHolder(@NonNull ViewItemBinding itemView) {
            super(itemView.getRoot());
            this.viewItemBinding = itemView;

        }
    }

    public void setFragmentList(ArrayList<ViewItem> fragmentList) {
        this.fragmentList = fragmentList;
    }
}
