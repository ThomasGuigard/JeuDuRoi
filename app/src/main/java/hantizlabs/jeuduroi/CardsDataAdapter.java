package hantizlabs.jeuduroi;

import android.content.Context;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import hantizlabs.jeuduroi.Model.Carte;

public class CardsDataAdapter extends ArrayAdapter<Carte> {

    private ViewHolder viewHolder;

    public CardsDataAdapter(Context context) {
        super(context, R.layout.card_content);
    }

    public String description;

    private static class ViewHolder {
        private TextView itemView;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        /*if (contentView == null) {
            contentView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.card_content, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) contentView.findViewById(R.id.ItemView);

            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }
    */
        Carte item = getItem(position);
        /*if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.itemView.setText(String.format("%s %d", item.reason, item.long_val));
        }*/

        Resources resources = getContext().getResources();
        int resourceId = resources.getIdentifier(item.getFilepath(), "drawable",
                getContext().getPackageName());
        Drawable imgCarteDrawable =  resources.getDrawable(resourceId);

        ImageView imgCarte = (ImageView) contentView.findViewById(R.id.imageCarte);
        imgCarte.setImageDrawable(imgCarteDrawable);

        setDescription(item.getDescription());

        return contentView;
    }

    public void setDescription(String text){
        this.description = text;
    }

    public String getDescription(){
        return this.description;
    }
}

