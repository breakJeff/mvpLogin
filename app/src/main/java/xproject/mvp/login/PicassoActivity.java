package xproject.mvp.login;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import xproject.mvp.adapter.RecycleViewDataAdapter;
import xproject.mvp.model.AndroidVersion;
import xproject.mvplogin.R;

public class PicassoActivity extends BaseActivity {
    private final String names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String imageUrls[] = {
            "http://img.blog.csdn.net/20160527205448521",
            "http://img.blog.csdn.net/20160527205516475",
            "http://img.blog.csdn.net/20160527205804527",
            "http://img.blog.csdn.net/20160527205831152",
            "http://img.blog.csdn.net/20160527205912200",
            "http://img.blog.csdn.net/20160527205946793",
            "http://img.blog.csdn.net/20160527210011184",
            "http://img.blog.csdn.net/20160527210026200",
            "http://img.blog.csdn.net/20160527210039778",
            "http://img.blog.csdn.net/20160527210053919"
    };

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> androidVersionsList;
    private RecycleViewDataAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.picasso_main);
        setActionBar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        androidVersionsList = initData();
        adapter = new RecycleViewDataAdapter(getApplicationContext(),androidVersionsList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Picasso.with(getApplicationContext()).resumeTag(adapter.getTag());
                } else {
                    Picasso.with(getApplicationContext()).pauseTag(adapter.getTag());
                }
            }
        });
    }

    private ArrayList<AndroidVersion> initData() {
        ArrayList<AndroidVersion> androidVersions = new ArrayList<>();
        int length = names.length;
        for(int i = 0; i< length; ++i){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.imageUrl = imageUrls[i];
            androidVersion.name = names[i];
            androidVersions.add(androidVersion);
        }

        return androidVersions;

    }
}
