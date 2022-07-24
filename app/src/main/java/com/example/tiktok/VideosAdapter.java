package com.example.tiktok;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Gestion des videos
public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder>{
    private List<VideoItem> mVideoItems;
    public ImageView play ;

    public VideosAdapter(List<VideoItem> videoItems) {

        mVideoItems = videoItems;
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_videos_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(mVideoItems.get(position));
    }
    @Override
    public int getItemCount() {

        return mVideoItems.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{
        VideoView mVideoView;
        TextView txtUser,txtDesc,txtSong;
        ProgressBar mProgressBar;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoView = itemView.findViewById(R.id.videoView);
            txtUser = itemView.findViewById(R.id.txtUser);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtSong = itemView.findViewById(R.id.txtSong);
            mProgressBar = itemView.findViewById(R.id.progressBar);
            play = itemView.findViewById(R.id.play);
        }
        void setVideoData(VideoItem videoItem){
            txtUser.setText("@"+videoItem.videoUser);
            txtDesc.setText(videoItem.videoDesc);
            txtSong.setText(videoItem.videosong);
            mVideoView.setVideoPath(videoItem.videoURL);
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mProgressBar.setVisibility(View.GONE);
                    mp.start();
                    RunAnimation();
                    float videoRatio = mp.getVideoWidth() / (float)mp.getVideoHeight();
                    float screenRatio = mVideoView.getWidth() / (float)mVideoView.getHeight();
                    float scale  = videoRatio / screenRatio;
                    if (scale >= 1f){
                        mVideoView.setScaleX(scale);
                    }else {
                        mVideoView.setScaleY(1f / scale);
                    }
                }
            });
            mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
            //play or stop video view
            mVideoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mVideoView.isPlaying())
                    {
                        mVideoView.pause();
                        play.setVisibility(View.VISIBLE);
                    }
                    else {
                        mVideoView.start();
                        play.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
        //animated text View (Scale)
        private void RunAnimation()
        {
            Animation a = AnimationUtils.loadAnimation(this.mVideoView.getContext(), R.anim.scale);
            a.reset();
            txtSong.clearAnimation();
            txtSong.startAnimation(a);
        }

    }

}