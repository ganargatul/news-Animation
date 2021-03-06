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

public class ImagesSlider extends RecyclerView.Adapter<ImagesSlider.ExampleViewHolder> {
private Context mContext;
private ArrayList<NewsItem> mExampleList;
//private Context mContext;
// private ArrayList<ExampleItem>mExampleList;
private OnItemClickListener mListener;
public  ImagesSlider(Context context,ArrayList<NewsItem>examplelist){
        mContext = context;
        mExampleList = examplelist;
        }
public interface  OnItemClickListener {
    void  onItemClick(int position);
}
    public void setOnItemClickListener(ImagesSlider.OnItemClickListener listener){
        mListener = listener;
    }
    @NonNull
    @Override
    public ImagesSlider.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.slider_image,parent,false);
        return new ImagesSlider.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesSlider.ExampleViewHolder holder, int position) {
        NewsItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getGambar();

        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

public class ExampleViewHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;

    public ExampleViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.imageslider);
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