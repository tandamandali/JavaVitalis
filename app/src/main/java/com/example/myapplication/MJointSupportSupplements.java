package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MJointSupportSupplements extends AppCompatActivity {

    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        // Populate product details
        populateProductList();

        // Create linear layouts for each product
        createProductLayouts();
    }

    private void populateProductList() {
        productList = new ArrayList<>();
        String[] Protien_Powder = {
                "Optimum Nutrition (ON) Gold Standard 100% Whey",
                "MuscleTech NitroTech Whey Gold",
                "Myprotein Impact Whey Protein",
                "Isopure Zero Carb 100% Whey Protein Isolate",
                "Transparent Labs Grass-Fed Whey Protein Isolate",
                "Bulk Nutrients Whey Protein Concentrate",
                "Ascent Native Fuel Whey Protein",
                "RSP Quadrapure Isolate",
                "Legion Athletics Whey+",
                "Kaged Muscle Micropure Whey Protein Isolate"
        };

        String[] Creatine = {
                "Optimum Nutrition (ON) Micronized Creatine Monohydrate",
                "Bulk Supplements Creatine Monohydrate Powder",
                "MusclePharm Creatine Powder",
                "Myprotein Creatine Monohydrate",
                "NOW Sports Creatine Monohydrate Powder",
                "BPI Sports Best Creatine Monohydrate Powder",
                "GNC Creatine Monohydrate Powder",
                "Labrada Creata-Gain",
                "Cellucor Cor-Performance Creatine",
                "Scivation Xtend Creatine"
        };


        String[] fishOil = {
                "Nordic Naturals Ultimate Omega D3",
                "Carlson Labs Fish Oil Gems",
                "Barleans Fine Oils Omega-3 Fish Oil",
                "Nature Made Fish Oil",
                "SmartyPants Omega-3 Fish Oil Gummy Vitamins",
                "Schiff MegaRed Omega-3 Plus EPA & DHA",
                "Twinlab Omega-3 Fish Oil Concentrate",
                "Kirkland Signature Wild Alaskan Fish Oil",
                "Wiley's Finest Wild Alaskan Fish Oil",
                "AOR EPA/DHA Support"
        };

        String[] preWorkoutSupplements = {
                "Cellucor C4 Pre-Workout",
                "Optimum Nutrition (ON) Pre-Workout Powder",
                "Transparent Labs PreSeries Bulk",
                "MusclePharm Assault Pre-Workout Powder",
                "Bucked Up Pre-Workout Powder",
                "Ghost Legend Pre-Workout",
                "Kaged Muscle Pre-Kaged",
                "BPI Sports 1.M.R. Pre-Workout",
                "Primeval Labs Prevail",
                "GAT Nitraflex Pre-Workout"
        };

        String[] probiotics = {
                "Culturelle Daily Probiotic Capsules",
                "Align Probiotic Supplement",
                "Benefiber Prebiotic Chewable Tablets",
                "Nexabiotic Complete Probiotic Capsules",
                "Florastor Live Bacteria Capsules",
                "RepHresh Pro-B Probiotic Supplement",
                "Align Ester-C Probiotic Blend",
                "Garden of Life RAW Probiotics Ultimate Care",
                "Bio-K+ Probiotics Chewable Tablets",
                "Thorne Bifidobacterium GG"
        };

        String[] massGainers = {
                "Optimum Nutrition (ON) Serious Mass Weight Gain Powder",
                "MuscleTech Mass Gainer Powder",
                "Bulk Nutrients Serious Mass Gain Powder",
                "Myprotein Impact Weight Gainer",
                "Kaged Muscle Gain Sweet Potato & Whey",
                "Six Star Elite Whey Gainer Plus",
                "Transparent Labs Bulk Nutrients",
                "RSP TrueGain Weight Gain Powder",
                "Beast Sports Nutrition TrueFit Gainer",
                "Gaspari Nutrition SuperSize Mass Gainer"
        };

        String[] jointSupportSupplements = {
                "glucosamine and chondroitin (various brands)",
                "Osteo Bi-Flex Triple Strength",
                "Nature Made Glucosamine Chondroitin MSM",
                "Schiff Move Free Ultra New Formula",
                "Kirkland Signature Glucosamine Chondroitin MSM with Hyaluronic Acid",
                "Puritan's Pride Glucosamine Chondroitin MSM with Hyaluronic Acid",
                "Doctor's Best Glucosamine Chondroitin MSM with OptiMSM",
                "Hyland's Glucosamine Chondroitin MSM with Turmeric",
                "Now Foods Glucosamine Chondroitin MSM with Boswellia",
                "Solaray Glucosamine Chondroitin MSM with OptiMSM & Hyaluronic Acid"
        };

        String[] fiberSupplements = {
                "Metamucil Fiber Supplement",
                "Citrucel Sugar Free Fiber Powder",
                "Benefiber Prebiotic Fiber Supplement",
                "Konsyl Psyllium Fiber Powder",
                "Miralax Laxative Powder",
                "Fiber Choice Chewable Tablets",
                "Mylanta Fiber Laxative Supplement",
                "Benefiber Advanced Digestive Support",
                "Phillips' Fiber One Softgels",
                "Align Prebiotic Fiber Therapy Chewable Tablets"
        };

        String[] sleepAids = {
                "Melatonin (various brands)",
                "ZzzQuil Nighttime Sleep Aid",
                "Unisom SleepGels",
                "Nature Made Sleep with Melatonin",
                "Simply Sleep by Sleepy zs",
                "Advil PM Pain Reliever with Diphenhydramine",
                "Tylenol PM Pain Reliever with Diphenhydramine",
                "Dramamine Less Drowsy Non-Drowsy Formula",
                "Coricidin HBP Cold & Flu Relief",
                "Sominex Sleep Aid Tablets"
        };


        String[] flavors = {
                "Double Chocolate",
                "Chocolate Hazelnut",
                "French Vanilla",
                "Cookies and cream",
                "Mango",
                "Blueberry Lemonade",
                "Strawberry Banana",
                "Vanilla Bean",
                "Caramel Latte",
                "Cookies 'n Cream"
        };

        double[] ratings = {
                9.6,
                9.2,
                9.0,
                8.8,
                8.5,
                8.4,
                8.9,
                8.7,
                8.3,
                8.0
        };

        double[] prices = {2453, 4398, 1599, 3200, 2377, 4100, 1642, 1899, 4291, 1499};



        for(int i=0;i<10;i++){
            productList.add(new Product(jointSupportSupplements[i], flavors[i], ratings[i], prices[i]));
        }


        // Add more products as needed

    }

    private void createProductLayouts() {
        LinearLayout mainLayout = findViewById(R.id.main_layout);
        ScrollView scrollView = findViewById(R.id.scroll_view);

        // Define margin and padding in pixels
        int marginPx = getResources().getDimensionPixelSize(R.dimen.product_layout_margin);
        int paddingPx = getResources().getDimensionPixelSize(R.dimen.product_layout_padding);
        int textSizePx = getResources().getDimensionPixelSize(R.dimen.product_name_text_size);

        for (Product product : productList) {
            // Create a new linear layout for each product
            LinearLayout productLayout = new LinearLayout(this);
            productLayout.setOrientation(LinearLayout.VERTICAL);

            // Set layout parameters with margin
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, marginPx); // left, top, right, bottom
            productLayout.setLayoutParams(layoutParams);

            // Set padding
            productLayout.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);

            // Create text views for each product detail
            TextView productNameTextView = new TextView(this);
            productNameTextView.setText("" + product.getName());
            productNameTextView.setTextSize(textSizePx);

            TextView categoryTextView = new TextView(this);
            categoryTextView.setText("Category: " +"Joint Support Supplements");

            TextView flavourTextView = new TextView(this);
            flavourTextView.setText("Flavour: " + product.getFlavour());

            TextView ratingTextView = new TextView(this);
            ratingTextView.setText("Rating: " + product.getRating());

            TextView priceTextView = new TextView(this);
            priceTextView.setText("Price: ₹" + product.getPrice());

            LinearLayout.LayoutParams layoutParamss = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamss.setMargins(0, 0, 0, 0); // left, top, right, bottom


            Button mb = new Button(this);
            mb.setId(View.generateViewId());
            mb.setText("Add to Cart");
            mb.setLayoutParams(layoutParamss);

            // Add text views to the product layout
            productLayout.addView(productNameTextView);
            productLayout.addView(categoryTextView);
            productLayout.addView(flavourTextView);
            productLayout.addView(ratingTextView);
            productLayout.addView(priceTextView);
            productLayout.addView(mb);

            // Add product layout to the main layout
            mainLayout.addView(productLayout);
        }
    }

    // Class to represent product details
    private static class Product {
        private String name;
        private String flavour;
        private double rating;
        private double price;

        public Product(String name, String flavour, double rating, double price) {
            this.name = name;
            this.flavour = flavour;
            this.rating = rating;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getFlavour() {
            return flavour;
        }

        public double getRating() {
            return rating;
        }

        public double getPrice() {
            return price;
        }
    }
}



