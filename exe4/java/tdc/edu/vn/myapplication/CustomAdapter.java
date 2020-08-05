package tdc.edu.vn.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Song> data;
    public CustomAdapter(Context context, int resource, ArrayList<Song> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }
    public int getCount() {
        return data.size();
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        // ánh xạ
        ImageView imgAnh = view.findViewById(R.id.imgAnh);
        TextView tvTenBaiHat = view.findViewById(R.id.tvTenBaiHat);
        // code
        imgAnh.setImageResource(data.get(position).getHinh());
        tvTenBaiHat.setText(data.get(position).getTenbaihat());

        return view;

    }
}
