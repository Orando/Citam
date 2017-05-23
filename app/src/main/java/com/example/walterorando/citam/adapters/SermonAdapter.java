package com.example.walterorando.citam.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.walterorando.citam.R;
import com.example.walterorando.citam.models.SermonItem;

import java.util.ArrayList;

/**
 * Created by Walter Orando on 4/15/2017.
 */

public class SermonAdapter extends RecyclerView.Adapter<SermonAdapter.ViewHolderNews>  {

    private final Context mContext;
    private ArrayList<SermonItem> mListSermons;
    LayoutInflater mInflater;

    public SermonAdapter(Context context, ArrayList<SermonItem> listNews) {
        this.mContext = context;
        this.mListSermons = listNews;
        mInflater = LayoutInflater.from(context);
    }

    public void setNews(ArrayList<SermonItem> listNews) {
        this.mListSermons = listNews;
        notifyDataSetChanged();
    }

    @Override
    public SermonAdapter.ViewHolderNews onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.sermons_item, parent, false);
        ViewHolderNews viewHolder = new ViewHolderNews(view,mContext);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(SermonAdapter.ViewHolderNews holder, int position) {
        SermonItem mSermon = mListSermons.get(position);

        holder.tvSermonTitle.setText(mSermon.getTitle());
    }

    @Override
    public int getItemCount() {
        return mListSermons.size();
    }
    class ViewHolderNews extends RecyclerView.ViewHolder {
        /* ImageView ivNews;
         LinearLayout lytNewsInfo;*/
        TextView tvSermonTitle;
        /* Button btnBack, btnViewMore;*/

        public ViewHolderNews (final View itemView, final Context mContext) {
            super(itemView);
            tvSermonTitle = (TextView) itemView.findViewById(R.id.tvSermonTitle);
            /*lytNewsInfo = (LinearLayout)itemView.findViewById(R.id.lytNewsInfo);
            tvTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
            tvDes = (TextView) itemView.findViewById(R.id.tvNewsDes);

            ivNews.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                public void onClick(View view) {
                    Interpolator interpolator = new FastOutSlowInInterpolator();
                    lytNewsInfo.animate().setInterpolator(interpolator)
                            .translationX(20)
                            .setDuration(1000);
                    btnViewMore.setVisibility(View.GONE);
                }
            });

            btnBack = (Button)itemView.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                public void onClick(View view) {
                    Interpolator interpolator = new FastOutSlowInInterpolator();
                    lytNewsInfo.animate().setInterpolator(interpolator)
                            .translationX(1200)
                            .setDuration(1000);
                    btnViewMore.setVisibility(View.VISIBLE);
                }
            });

            btnViewMore = (Button)itemView.findViewById(R.id.btnViewMore);
            btnViewMore.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                public void onClick(View view) {
                    Interpolator interpolator = new FastOutSlowInInterpolator();
                    lytNewsInfo.animate().setInterpolator(interpolator)
                            .translationX(20)
                            .setDuration(1000);
                    btnViewMore.setVisibility(View.GONE);
                }
            });*/


        }
    }
}
