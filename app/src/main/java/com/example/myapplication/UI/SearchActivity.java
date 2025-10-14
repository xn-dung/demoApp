package com.example.myapplication.UI;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.model.NguyenLieu;
import com.example.myapplication.model.BaiDang;
import com.example.myapplication.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private LinearLayout btnSearch;
    Button button;
    Button buttonTru;
    Button buttonBa;
    TableLayout tableLayout;
    ArrayList<BaiDang> bd;
    private User user;
    ArrayList<NguyenLieu> al;
    private TextView fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_food_by_ingredient);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        user = (User) getIntent().getSerializableExtra("user");
        setContentView(R.layout.search_food_by_ingredient);

        tableLayout = findViewById(R.id.tablet);
        button = findViewById(R.id.button2);
        fullName = findViewById(R.id.textFullName);
        fullName.setText( "Hi, "+user.getFullname());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rowCount = tableLayout.getChildCount();
                if (rowCount >= 4) {
                    Toast.makeText(SearchActivity.this, "Chỉ được thêm tối đa 4 dòng!", Toast.LENGTH_SHORT).show();
                    return;
                }
                TableRow newRow = (TableRow) LayoutInflater.from(SearchActivity.this)
                        .inflate(R.layout.table_layout, tableLayout, false);

                EditText tv = newRow.findViewById(R.id.textTen);
                EditText et = newRow.findViewById(R.id.editDinhLuong);

                tv.setText("");
                et.setText("");

                tableLayout.addView(newRow);
            }
        });

        buttonTru = findViewById(R.id.buttonTru);
        buttonTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rowCount = tableLayout.getChildCount();
                if(rowCount > 1) {
                    tableLayout.removeViewAt(rowCount - 1);
                }
                else {
                    Toast.makeText(SearchActivity.this, "Không thể xóa hết các dòng!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        al = new ArrayList<>();
        bd = new ArrayList<>();
        buttonBa = findViewById(R.id.button3);
        buttonBa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int rowCount = tableLayout.getChildCount();
                al.clear();
                TableRow firstOne = (TableRow) tableLayout.getChildAt(0);
                EditText ten = firstOne.findViewById(R.id.editTen1);
                NguyenLieu tmp = new NguyenLieu();
                tmp.setTen(ten.getText().toString());
                al.add(tmp);
                for(int i = 1; i < rowCount; i++){
                    TableRow hehe = (TableRow) tableLayout.getChildAt(i);
                    EditText tv = hehe.findViewById(R.id.textTen);

                    NguyenLieu tmp1 = new NguyenLieu();
                    tmp1.setTen(tv.getText().toString());
                    al.add(tmp1);
                }
                searchFood(al);
            }
        });

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(v -> {
            Intent a = new Intent(SearchActivity.this, SeacrchFoodByNameActivity.class);
            a.putExtra("user", user);
            startActivity(a);
        });

        BottomNavigationView botNav = findViewById(R.id.bottomNavView);
        botNav.setSelectedItemId(R.id.menuHome);

        botNav.setOnItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.menuHome) {
                Intent intent2 = new Intent(SearchActivity.this, HomeActivity.class);
                intent2.putExtra("user", user);
                startActivity(intent2);
                return true;

            } else if (id == R.id.menuProfile){
                Intent intent2 = new Intent(SearchActivity.this, ProfileeActivity.class);
                intent2.putExtra("user", user);
                startActivity(intent2);
                return true;
            } else if (id == R.id.menuSearch){
                Intent intent2 = new Intent(SearchActivity.this, SearchActivity.class);
                intent2.putExtra("user", user);
                startActivity(intent2);
                return true;
            }
            else if(id == R.id.menuAdd){
                Intent intent2 = new Intent(SearchActivity.this, AddFoodPostActivity.class);
                intent2.putExtra("user", user);
                startActivity(intent2);
                return true;
            }
            return false;
        });
    }


    private void searchFood(ArrayList<NguyenLieu> a){
        String url = "https://mobilenodejs.onrender.com/api/baidang/search/ingredient";
        RequestQueue queue = Volley.newRequestQueue(this);
        try{
            JSONArray nguyenLieuArray = new JSONArray();
            for(int i = 0 ; i < a.size(); i++){
                JSONObject nguyenLieuObject = new JSONObject();
                nguyenLieuObject.put("ten", a.get(i).getTen());
                nguyenLieuArray.put(nguyenLieuObject);
            }

            JSONObject requestBody = new JSONObject();
            requestBody.put("nguyenLieu", nguyenLieuArray);

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.POST,
                    url,
                    null,
                    response -> {
                try {
                   bd.clear();
                   for(int i = 0; i < response.length(); i++){
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
                       ArrayList<NguyenLieu> nguyenLieu = new ArrayList<>();
                       for(int j = 0; j < nlArray.length(); j++){
                           nguyenLieu.add(new NguyenLieu(nlArray.getJSONObject(j).getString("_id"), nlArray.getJSONObject(j).getString("ten")));
                       }
                       baiDang.setNguyenLieu(nguyenLieu);

                       bd.add(baiDang);

                   }

                    if (bd.size() > 0) {
                        Intent intent = new Intent(SearchActivity.this, FoundFoodActivity.class);
                        intent.putExtra("type", 1);
                        intent.putExtra("data", bd);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SearchActivity.this, "Không tìm thấy món ăn nào phù hợp!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(this, "Lỗi xử lý dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            },
                    error -> {
                        Toast.makeText(this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    })
            {
                public byte[] getBody() {
                    return requestBody.toString().getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };
            queue.add(jsonArrayRequest);
        }catch (Exception e) {
            Toast.makeText(this, "Lỗi tạo request", Toast.LENGTH_SHORT).show();
        }
    }

}