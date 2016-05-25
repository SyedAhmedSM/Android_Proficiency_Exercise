package android.example.com.androidproficiencyexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Syed Ahmed on 25-05-2016.
 */
public class CustomAdapter extends BaseAdapter {

    private static ArrayList<ArticleItems> articleItems;
    private static Context context;
    private final LayoutInflater mInflater;
    public CustomAdapter(Context context, ArrayList<ArticleItems> articlesDetails) {
        articleItems = articlesDetails;
        CustomAdapter.context = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return articleItems.size();
    }

    @Override
    public Object getItem(int position) {
        return articleItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txttitle);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.description);
            holder.articleImage = (ImageView) convertView.findViewById(R.id.articleImage);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtTitle.setText(articleItems.get(position).getTitle());
        holder.txtDescription.setText(articleItems.get(position).getDescription());
        if(articleItems.get(position).getHref() != null){
            Glide.with(context).load(articleItems.get(position).getHref()).into(holder.articleImage);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView articleImage;
    }
}
