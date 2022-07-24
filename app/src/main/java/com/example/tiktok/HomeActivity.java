package com.example.tiktok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    //Declaration des variables
    private ImageView sound_disc , like , share  , share_link  ;
    private CircleImageView user ;
    private  Boolean Like_state = false ;
    private  Boolean share_state = false ;
    private TextView nblike , nbshare , nbsharelink ;
    int nb_like=1300;
    int nb_share=2700;
    int nb_share_link=3870;
    String VideoUrlShare ="";
    String message_favoris ="Ajouté aux favoris" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Definir the Bottom Navigation Backrgound Color
        bottomNavigationView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        //Listener pour Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // do something here
                        return true;
                    case R.id.action_group:
                        // do something here
                        return true;
                    case R.id.action_message:
                        // do something here
                        return true;
                    case R.id.action_profil:
                        // do something here
                        return true;
                    default: return true;
                }
            }
        });
        nblike = findViewById(R.id.nblike);
        nblike.setText(""+nb_like);
        nbshare = findViewById(R.id.nbshare);
        nbshare.setText(""+nb_share);
        like = (ImageView) findViewById(R.id.like) ;
        nbsharelink= findViewById(R.id.nbsharelink);
        nbsharelink.setText(""+nb_share_link);
        //Like Button Action
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //like.setBackgroundColor(Color.parseColor("#548456"));
                if(Like_state)
                {
                    nb_like--;
                    nblike.setText(""+nb_like);
                    like.setImageResource(R.drawable.like);
                    Like_state = false ;
                }
                else {
                    nb_like++;
                    nblike.setText(""+nb_like);
                    like.setImageResource(R.drawable.pink_heart);
                    Like_state = true ;
                }
            }
        });
        share = (ImageView) findViewById(R.id.share) ;
        //Share Button action
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(share_state){
                    nb_share--;
                    nbshare.setText(""+nb_share);
                    share.setImageResource(R.drawable.sharebtn);
                    share_state = false ;
                }
                else {
                    nb_share++;
                    nbshare.setText(""+nb_share);
                    share.setImageResource(R.drawable.yellowsharebtn);
                    share_state = true ;

                    Toast toast = Toast.makeText(HomeActivity.this , message_favoris , 1);
                    toast.show();

                }
            }
        });
        share_link = (ImageView) findViewById(R.id.imageView3) ;
        //Share Link of tik Tok
        share_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nb_share_link++;
                nbsharelink.setText(""+nb_share_link);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, VideoUrlShare);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        //Share Button action


        //animated icon
        sound_disc = (ImageView) findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.giphy).into(sound_disc);

        user = (CircleImageView) findViewById(R.id.circleImageView);
        Glide.with(this).load(R.drawable.femaleprofile).into(user);

        //List of video Tik Tok

        final ViewPager2 videosViewPager = findViewById(R.id.viewPagerVideos);
        List<VideoItem> videoItems = new ArrayList<>();

        VideoItem item = new VideoItem();
        item.videoURL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        item.videoUser = "User1";
        item.videoDesc = "Description de Video 1 , all about first vide #tiktok";
        item.videosong = "SOng1 to the first video ";
        videoItems.add(item);
        VideoUrlShare= item.videoURL ;

        VideoItem item2 = new VideoItem();
        item2.videoURL = "http://www.demonuts.com/Demonuts/smallvideo.mp4";
        item2.videoUser = "User2";
        item2.videoDesc = "How Sasha Solomon Became a Software Developer at Twitter";
        item2.videosong = "Chanteur2";
        videoItems.add(item2);
        VideoUrlShare= item2.videoURL ;

        VideoItem item3 = new VideoItem();
        item3.videoURL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
        item3.videoUser = "User3";
        item3.videoDesc = " Depth-First Search Algorithm";
        item3.videosong = "Chanteur3";
        videoItems.add(item3);
        VideoUrlShare= item3.videoURL ;

        VideoItem item4 = new VideoItem();
        item4.videoURL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        item4.videoUser = "User4";
        item4.videoDesc = "Description de Video 4 , all about first video for user of ... #tiktok";
        item4.videosong = "SOng44 to the first video ";
        videoItems.add(item4);
        VideoUrlShare= item4.videoURL ;
        //Gere la liste des videos avec l'adapter
        videosViewPager.setAdapter(new VideosAdapter(videoItems));
    }
}

