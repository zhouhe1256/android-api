package com.android_api.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android_api.R;
import com.zh.android.util.Logger;

import java.util.List;

/**
 * Created by zhouh on 15-12-22.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private Context context;
    private List<String> contents;
    public MyAdapter(Context context,List<String> contents) {
        this.context = context;
        this.contents = contents;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       if(i == TYPE_HEADER){
           View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_header,null);
           return new HeaderViewHolder(view);
       }else if(i == TYPE_NORMAL){
           View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_boom,null);
           return new BoomrViewHolder(view);
       }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof HeaderViewHolder){
            final HeaderViewHolder headerHolder = (HeaderViewHolder) viewHolder;
                headerHolder.headtextView.setText(contents.get(10));
                Logger.i("fffff",contents.get(i));

        }else if(viewHolder instanceof BoomrViewHolder){

            final BoomrViewHolder boomrViewHolder = (BoomrViewHolder) viewHolder;
            boomrViewHolder.textView.setText(contents.get(i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEADER;
        }
            return TYPE_NORMAL;


    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView headtextView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.headtextView = (TextView) itemView.findViewById(R.id.text_head);
        }
    }
    public class BoomrViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public BoomrViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.text_boom);
        }
    }
}
