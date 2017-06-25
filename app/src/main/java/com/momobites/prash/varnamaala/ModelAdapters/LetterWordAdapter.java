package com.momobites.prash.varnamaala.ModelAdapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.momobites.prash.varnamaala.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.AUDIO_SERVICE;
import static android.view.View.GONE;
import static com.momobites.prash.varnamaala.ModelAdapters.AudioPlayback.mCompletionListener;
import static com.momobites.prash.varnamaala.ModelAdapters.AudioPlayback.mOnAudioFocusChangeListener;

/**
 * Created by prash on 6/24/2017.
 */

public class LetterWordAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<LetterWordModel> list = Collections.emptyList();
    Context context;
    private LayoutInflater inflater;

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;
    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;



    public LetterWordAdapter(Context context, ArrayList<LetterWordModel> data) {
        this.context = context;
        this.list = data;
        inflater = LayoutInflater.from(context);

    }

    /*
    * inflates the row layout is and initializes the View Holder. Once the View Holder is
    * initialized it manages the findViewById() methods, finding the views once and recycling
    * them to avoid repeated calls.
    * */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    /*
    * uses the View Holder constructed in the onCreateViewHolder() method to populate the
    * current row of the RecyclerView with data.
    * */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.NepaliWord.setText(list.get(position).getNepaliTranslationId());
        viewHolder.DevgnagariWord.setText(list.get(position).getDevnagariId());
        viewHolder.EnglishWord.setText(list.get(position).getmEnglishId());

        viewHolder.AdditionalDetail.setVisibility(GONE);
        viewHolder.Compounnds.setVisibility(GONE);

        viewHolder.btn_compounds.setVisibility(GONE);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

        /*On ImageView Button Press - for Audio*/
        viewHolder.btn_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                AudioPlayback.releaseMediaPlayer();
                // Get the  object at the given position the user clicked on
                final int  x = list.get(position).getmAudioResourceId();

                // Toast.makeText(context,  x, Toast.LENGTH_SHORT).show();

                // Request audio focus
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.
                    mMediaPlayer = MediaPlayer.create( context, x );
                    // Start the audio file
                    mMediaPlayer.start();
                    // Setup a listener on the media player
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }

        });


        /*On Item Click View*/
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.DevgnagariWord.getTextSize() == 60){

                    viewHolder.DevgnagariWord.setTextSize(200);

                } else {

                    viewHolder.DevgnagariWord.setTextSize(60);
                };

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
