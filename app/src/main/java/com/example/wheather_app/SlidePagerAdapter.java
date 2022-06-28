package com.example.wheather_app;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheather_app.databinding.FragmentABinding;

import java.util.ArrayList;

public class SlidePagerAdapter extends RecyclerView.Adapter<SlidePagerAdapter.ViewHolder> {
    private ArrayList<FragmentA> fragmentList;

    //Constructor
    public SlidePagerAdapter(ArrayList<FragmentA> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public SlidePagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentABinding fragmentABinding = FragmentABinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(fragmentABinding);
    }


    @Override
    public void onBindViewHolder(@NonNull SlidePagerAdapter.ViewHolder holder, int position) {
        FragmentA fragmentA = fragmentList.get(position);
        holder.fragmentABinding.setFragment(fragmentA); //data binding
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FragmentABinding fragmentABinding;

        public ViewHolder(@NonNull FragmentABinding itemView) {
            super(itemView.getRoot());
            this.fragmentABinding = itemView;

        }
    }

    public void setFragmentList(ArrayList<FragmentA> fragmentList) {
        this.fragmentList = fragmentList;
    }
}
