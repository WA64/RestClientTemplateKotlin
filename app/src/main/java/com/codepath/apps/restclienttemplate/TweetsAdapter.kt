package com.codepath.apps.restclienttemplate

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.models.Tweet

class TweetsAdapter (val tweets: ArrayList<Tweet>) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        //Inflate our item layout
        val view = inflater.inflate(R.layout.item_tweet,parent,false)
        return ViewHolder(view)
    }

    // Populating data into the item through the holder
    override fun onBindViewHolder(holder: TweetsAdapter.ViewHolder, position: Int) {
        //Get the data model based on the position
        val tweet: Tweet = tweets[position]

        Log.i("positionis",""+ position)
        if (position % 2 == 0)
            holder.background.setBackgroundColor(Color.WHITE)
        else
            holder.background.setBackgroundColor(Color.LTGRAY)

        //Set item views based on views and data model
        holder.tvUsername.text = tweet.user?.name

        holder.tvTweetBody.text = tweet.body

        Log.i("timestampped", ""+tweet.timeStamp)
        holder.tvTimeStamp.text = tweet.timeStamp

        Glide.with(holder.itemView).load(tweet.user?.publicImageUrl).into(holder.ivProfileImage)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }
    // Clean all elements of the recycler
    fun clear() {
        tweets.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(tweetList: List<Tweet>) {
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTimeStamp = itemView.findViewById<TextView>(R.id.tvTimeStamp)
        val ivProfileImage = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        val tvUsername = itemView.findViewById<TextView>(R.id.tvUsername)
        val tvTweetBody = itemView.findViewById<TextView>(R.id.tvTweetBody)
        var background = itemView.findViewById<ConstraintLayout>(R.id.tweetWhole)
    }


}