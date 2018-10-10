package samudra.com.newsapigits;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ZAQI on 10/10/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ExampleViewHolder> {
private Context mContext;
private ArrayList<NewsItem> mExampleList;
//private Context mContext;
// private ArrayList<ExampleItem>mExampleList;
private OnItemClickListener mListener;
public  NewsAdapter(Context context,ArrayList<NewsItem>examplelist){
        mContext = context;
        mExampleList = examplelist;
        }
public interface  OnItemClickListener {
    void  onItemClick(int position);
}
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        NewsItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getGambar();
        String Judul = currentItem.getTittle();
        String author = currentItem.getAuthor();
        String publish = currentItem.getPublish();
        holder.judul.setText(Judul);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.author.setText(author);
        holder.publish.setText(publish);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

public class ExampleViewHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;
    public  TextView judul, author , publish;
    public ExampleViewHolder(View itemView) {
        super(itemView);
           mImageView = itemView.findViewById(R.id.news_images);
           judul = itemView.findViewById(R.id.title_news);
           author = itemView.findViewById(R.id.author_news);
           publish = itemView.findViewById(R.id.publish_news);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }}

            }
        });
    }
}
}