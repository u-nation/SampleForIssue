package com.example.u_nation.sampleforissue

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.u_nation.sampleforissue.databinding.ActivityMainBinding
import com.example.u_nation.sampleforissue.databinding.RecyclerItemBinding

class MainActivity : AppCompatActivity() {

    private val simpleAdapter: SimpleAdapter = SimpleAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setTitle("Lollipop")
        binding.recycler.run {
            adapter = simpleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.refresh.setOnRefreshListener {
            Handler().postDelayed({
                simpleAdapter.notifyDataSetChanged()
                binding.refresh.isRefreshing = false
            }, 500)
        }
    }

    private class SimpleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var inflater: LayoutInflater? = null

        override fun getItemCount(): Int = 1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = inflater ?: LayoutInflater.from(parent.context).also { inflater = it }
            return ViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as ViewHolder).binding

            binding.expandButton.setOnClickListener {
                binding.expandableLayout.run {
                    visibility = if (visibility == View.GONE) View.VISIBLE else View.GONE
                }
            }

            binding.expandableContainer.run {
                removeAllViews()
                for (index in 1..4) {
                    addView(ImageView(context).apply {
                        setImageResource(R.mipmap.ic_launcher)
                    })
                }
            }
        }

        private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val binding: RecyclerItemBinding = DataBindingUtil.bind(itemView)!!
        }
    }
}
