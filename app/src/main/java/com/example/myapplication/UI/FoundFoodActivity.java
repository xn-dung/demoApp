package com.example.myapplication.UI;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.Adapter.FoundFoodAdapter;
import com.example.myapplication.model.BaiDang;
import com.example.myapplication.model.NguyenLieu;
import com.example.myapplication.model.User;
import com.example.myapplication.R;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;


public class FoundFoodActivity extends AppCompatActivity{
    String ten;
    ArrayList<NguyenLieu> nguyenLieu;
    ArrayList<BaiDang> bd;
    ListView lv;
    FoundFoodAdapter myadapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.found_food);
        String foodName = getIntent().getStringExtra("search");
        lv = findViewById(R.id.outfood);
        bd = new ArrayList<>();
        searchFood(foodName);
        myadapter = new FoundFoodAdapter( R.layout.food_card_home, FoundFoodActivity.this, bd);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            BaiDang selectedBaiDang = bd.get(position);
            Toast.makeText(this, "Bạn đã chọn món " + selectedBaiDang.getTenMon(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FoundFoodActivity.this, FoodPostActivity.class);
            intent.putExtra("baidang", selectedBaiDang);
            startActivity(intent);

        });
    }

    private void searchFood(String foodName) {
        String url = "https://661r3b81-3000.asse.devtunnels.ms/api/baidang/search";
        RequestQueue queue = Volley.newRequestQueue(this);
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("tenMon", foodName);

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.POST,
                    url,
                    null, // Đặt null ở đây, vì ta sẽ override body
                    response -> {
                        try {
                            bd.clear();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);

                                BaiDang baiDang = new BaiDang();
                                baiDang.setId(obj.getString("_id"));
                                baiDang.setTenMon(obj.getString("tenMon"));
                                baiDang.setCachLam(obj.getString("cachLam"));
                                baiDang.setNguyenLieuDinhLuong(obj.optString("nguyenLieuDinhLuong", ""));
                                baiDang.setLinkYtb(obj.optString("linkYtb", ""));
                                baiDang.setLuotThich(obj.optInt("luotThich", 0));
                                baiDang.setImage(obj.optString("image", ""));

                                JSONArray nlArray = obj.getJSONArray("nguyenLieu");
                                nguyenLieu = new ArrayList<>();
                                for(int j = 0; j < nlArray.length(); j++){
                                    nguyenLieu.add(new NguyenLieu(nlArray.getJSONObject(j).getString("_id"), nlArray.getJSONObject(j).getString("ten")));
                                }
                                baiDang.setNguyenLieu(nguyenLieu);

                                bd.add(baiDang);
                            }
                            myadapter.notifyDataSetChanged();
                            Toast.makeText(this, "Tìm thấy " + bd.size() + " món", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(this, "Lỗi xử lý dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Toast.makeText(this, "Lỗi kết nối: " + error.toString(), Toast.LENGTH_SHORT).show()
            ) {
                @Override
                public byte[] getBody() {
                    return jsonBody.toString().getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };

            queue.add(jsonArrayRequest);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi tạo request", Toast.LENGTH_SHORT).show();
        }
    }

}