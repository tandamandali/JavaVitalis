package com.example.myapplication;

//public class BrandActivity {
//}


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrandActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BrandAdapter adapter;
    private List<JSONObject> brandList;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        String jsonString = "{\"Beverly International\":{\"Brand\":\"Beverly International\",\"Category\":\"WHEY PROTEIN\",\"Flavor\":\"Graham Cracker\",\"Price\":\"₹3109.05\",\"Rating\":9.5},\"JYM Supplement Science\":{\"Brand\":\"JYM Supplement Science\",\"Category\":\"WHEY PROTEIN\",\"Flavor\":\"Chocolate Mousse\",\"Price\":\"₹2901.72\",\"Rating\":9.2},\"Kaged Muscle\":{\"Brand\":\"Kaged Muscle\",\"Category\":\"WHEY PROTEIN ISOLATE\",\"Flavor\":\"Vanilla Shake\",\"Price\":\"₹2901.72\",\"Rating\":8.9},\"MHP\":{\"Brand\":\"MHP\",\"Category\":\"WHEY PROTEIN ISOLATE\",\"Flavor\":\"Blue Ice\",\"Price\":\"₹2918.31\",\"Rating\":5.3},\"MuscleTech\":{\"Brand\":\"MuscleTech\",\"Category\":\"WHEY PROTEIN ISOLATE\",\"Flavor\":\"French Vanilla Swirl\",\"Price\":\"₹3150.51\",\"Rating\":9.6},\"Pro Supps\":{\"Brand\":\"Pro Supps\",\"Category\":\"WHEY PROTEIN ISOLATE\",\"Flavor\":\"Blueberry Lemonade\",\"Price\":\"₹1865.10\",\"Rating\":8.4},\"Six Star Pro Nutrition\":{\"Brand\":\"Six Star Pro Nutrition\",\"Category\":\"WHEY PROTEIN ISOLATE\",\"Flavor\":\"Triple Chocolate\",\"Price\":\"₹2321.21\",\"Rating\":9.2}}";

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Assuming you have the JSON data stored in a JSONObject called jsonObject
        brandList = new ArrayList<>();


        adapter = new BrandAdapter(brandList);
        recyclerView.setAdapter(adapter);
    }
}
