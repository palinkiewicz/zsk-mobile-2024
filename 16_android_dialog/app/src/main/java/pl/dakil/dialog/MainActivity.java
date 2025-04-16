package pl.dakil.dialog;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int[] drawableResIds = {
                R.drawable.alex_robertson_tld1ui2awjg_unsplash,
                R.drawable.anita_austvika_flpb5nm46ta_unsplash,
                R.drawable.arpit_rastogi_elytldkpkxw_unsplash,
                R.drawable.brian_mcmahon_kf3zlku4npe_unsplash,
                R.drawable.declan_sun_zuog4mkdgbe_unsplash,
                R.drawable.dmitry_voronov_9iooqzrdxag_unsplash,
                R.drawable.ingmar_h_5jmcv5aacp0_unsplash,
                R.drawable.joseph_corl_bmcmuip50qw_unsplash,
                R.drawable.joseph_corl_vnnlb1m78w4_unsplash,
                R.drawable.khanh_ho_ayis5ohztkg_unsplash,
                R.drawable.logan_weaver_lgnwvr_3otma1wbakk_unsplash,
                R.drawable.mos_design_pgrvzgdhfr4_unsplash,
                R.drawable.saeed_ghavam_shahidi_i38wgx6rrn0_unsplash,
                R.drawable.victor_hugo_jeqt4eacd5c_unsplash,
                R.drawable.negley_stockman_ue43ieia4yo_unsplash
        };

        int[] imageViewIds = {
                R.id.imageView1,
                R.id.imageView2,
                R.id.imageView3,
                R.id.imageView4,
                R.id.imageView5,
                R.id.imageView6,
                R.id.imageView7,
                R.id.imageView8,
                R.id.imageView9,
                R.id.imageView10,
                R.id.imageView11,
                R.id.imageView12,
                R.id.imageView13,
                R.id.imageView14,
                R.id.imageView15
        };

        for (int i = 0; i < imageViewIds.length; i++) {
            ImageView imageView = findViewById(imageViewIds[i]);
            imageView.setImageResource(drawableResIds[i]);
            imageView.setTag(drawableResIds[i]);
            imageView.setOnClickListener(this::showImageDialog);
        }
    }

    private void showImageDialog(View view) {
        int imageResId = (int) view.getTag();
        ImageDialogFragment dialogFragment = ImageDialogFragment.newInstance(imageResId);
        dialogFragment.show(getSupportFragmentManager(), "imageDialog");
    }
}