package tdc.edu.vn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhSach extends AppCompatActivity {
    ListView lvDanhSach;
    ArrayList<Song> data_Song;
    ArrayAdapter adapter_Song;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        setControl();
        setEvent();
    }
    private void setEvent() {
        khoitao();
        adapter_Song = new CustomAdapter(this, R.layout.list_item, data_Song);
        lvDanhSach.setAdapter(adapter_Song);
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhSach.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ma", position);
                bundle.putString("kt", "-1");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
    private void khoitao() {
        data_Song = new ArrayList<>();
        data_Song.add(new Song("Anh chẳng sao mà", R.drawable.khangviet, R.raw.b1));
        data_Song.add(new Song("Anh nhớ nhé", R.drawable.anhnhonhe, R.raw.b2));
        data_Song.add(new Song("Đời là thế thôi", R.drawable.phule, R.raw.b3));
        data_Song.add(new Song("Anh có tài mà", R.drawable.anhcotaima, R.raw.b4));
        data_Song.add(new Song("Ta đi tìm em", R.drawable.taditimem, R.raw.b5));
    }
    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }
}