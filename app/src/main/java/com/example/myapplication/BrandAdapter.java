package com.example.myapplication;




//public class BrandAdaptor {
//}


import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import org.json.JSONObject;
        import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {
    private List<JSONObject> brandList;

    public BrandAdapter(List<JSONObject> brandList) {
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_brand, parent, false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        JSONObject brand = brandList.get(position);
        try {
            holder.textViewBrandName.setText(brand.getString("Brand"));
            holder.textViewCategory.setText("Category: " + brand.getString("Category"));
            holder.textViewFlavor.setText("Flavor: " + brand.getString("Flavor"));
            holder.textViewPrice.setText("Price: " + brand.getString("Price"));
            holder.textViewRating.setText("Rating: " + brand.getDouble("Rating"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    public static class BrandViewHolder extends RecyclerView.ViewHolder {
        TextView textViewBrandName;
        TextView textViewCategory;
        TextView textViewFlavor;
        TextView textViewPrice;
        TextView textViewRating;

        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBrandName = itemView.findViewById(R.id.textViewBrandName);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewFlavor = itemView.findViewById(R.id.textViewFlavor);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
