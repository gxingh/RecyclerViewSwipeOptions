package com.example.kh2315.sliderecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    List<RecyclerEntity> list;
    private final int SHOW_MENU = 1;
    private final int HIDE_MENU = 2;

    public RecyclerAdapter(List<RecyclerEntity> articlesList) {
        this.list = articlesList;
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).isShowMenu()){
            return SHOW_MENU;
        }else{
            return HIDE_MENU;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType==SHOW_MENU){
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_menu, parent, false);
        }else{
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list, parent, false);
        }

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RecyclerEntity entity = list.get(position);
        holder.title.setText(entity.getTitle());
        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showMenu(position);
                return true;
            }
        });
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showMenu(int position) {
        for(int i=0; i<list.size(); i++){
            list.get(i).setShowMenu(false);
        }
        list.get(position).setShowMenu(true);
        notifyDataSetChanged();
    }


    public boolean isMenuShown() {
        for(int i=0; i<list.size(); i++){
            if(list.get(i).isShowMenu()){
                return true;
            }
        }
        return false;
    }

    public void removeItem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    public void closeMenu() {
        for(int i=0; i<list.size(); i++){
            list.get(i).setShowMenu(false);
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        FrameLayout container;
        LinearLayout close;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            container = itemView.findViewById(R.id.container);
            close = itemView.findViewById(R.id.close);
        }
    }
}
