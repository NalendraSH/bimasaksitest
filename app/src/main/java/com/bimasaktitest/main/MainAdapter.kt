package com.bimasaktitest.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bimasaktitest.R
import com.bimasaktitest.databinding.ItemMainBinding
import com.bimasaktitest.main.detail.DetailActivity
import com.idepos.customFormat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val listData: MutableList<MutableList<MainModel.Data>> = mutableListOf()
    private val listDate: MutableList<String> = mutableListOf()
    private val listLocalData: MutableList<MainModel.Data?> = mutableListOf()
    private lateinit var viewModel: MainViewModel
    private lateinit var owner: LifecycleOwner
    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding: ItemMainBinding = DataBindingUtil.inflate(inflater, R.layout.item_main, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: MutableMap<String, String> = mutableMapOf()
        val localData: MainModel.Data
//        viewModel.getLocalDataById(position)
        if (listData[position].size > 0) {
            data["label"] = listData[position][0].label.substring(1)
            data["date"] = listDate[position].customFormat("yyyy-MM", "MMMM yyyy")
            data["nb_visits"] = "${listData[position][0].nb_visits} kali dikunjungi"
//            data["status"] = "NOT SAVED"
//            viewModel.localLiveData.observe(owner, Observer {
//                if (it != null) {
//                    data["status"] = "SAVED"
//                } else {
//                    data["status"] = "NOT SAVED"
//                }
//            })

            localData = MainModel.Data(
                position,
                listDate[position],
                listData[position][0].avg_time_generation,
                listData[position][0].avg_time_on_page,
                listData[position][0].bounce_rate,
                listData[position][0].entry_bounce_count,
                listData[position][0].entry_nb_actions,
                listData[position][0].entry_nb_visits,
                listData[position][0].entry_sum_visit_length,
                listData[position][0].exit_nb_visits,
                listData[position][0].exit_rate,
                listData[position][0].label,
                listData[position][0].max_bandwidth,
                listData[position][0].max_time_generation,
                listData[position][0].min_bandwidth,
                listData[position][0].min_time_generation,
                listData[position][0].nb_hits,
                listData[position][0].nb_hits_with_bandwidth,
                listData[position][0].nb_hits_with_time_generation,
                listData[position][0].exit_nb_visits,
                listData[position][0].sum_bandwidth,
                listData[position][0].sum_daily_entry_nb_uniq_visitors,
                listData[position][0].sum_daily_exit_nb_uniq_visitors,
                listData[position][0].sum_daily_nb_uniq_visitors,
                listData[position][0].sum_time_spent
            )
        } else {
            data["label"] = "Data Kosong"
            data["date"] = listDate[position].customFormat("yyyy-MM", "MMMM yyyy")
            data["nb_visits"] = "Data Kosong"
//            data["status"] = "NOT SAVED"
//            viewModel.localLiveData.observe(owner, Observer {
//                if (it != null) {
//                    data["status"] = "SAVED"
//                } else {
//                    data["status"] = "NOT SAVED"
//                }
//            })

            localData = MainModel.Data(
                position,
                listDate[position],
                0.00,
                0,
                "",
                0,
                0,
                0,
                0,
                0,
                "",
                "",
                0,
                "",
                0,
                "",
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
            )
        }
        val isExist = listLocalData.find { it?.id == position }
        if (isExist != null) {
            data["status"] = "SAVED"
        } else {
            data["status"] = "NOT SAVED"
        }
        holder.bindData(data)

        holder.itemView.card_item_main_more.setOnClickListener {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter = moshi.adapter<MainModel.Data>(MainModel.Data::class.java)
            val stringData: String
            if (listData[position].size > 0) {
                stringData = jsonAdapter.toJson(listData[position][0])
            } else {
                stringData = ""
            }
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("detail_json", stringData)
            context.startActivity(intent)
        }
        holder.itemView.card_item_main_save.setOnClickListener {
            listener.onItemClick()
            viewModel.submitLocalData(localData)
        }
    }

    fun submitList(list: MutableMap<String, MutableList<MainModel.Data>>) {
        listData.clear()
        listData.addAll(list.values)
        listDate.clear()
        listDate.addAll(list.keys)
    }

    fun submitLocalData(list: List<MainModel.Data>) {
        listLocalData.clear()
        listLocalData.addAll(list)
        notifyDataSetChanged()
    }

    fun setViewModel(viewModel: MainViewModel, owner: LifecycleOwner) {
        this.viewModel = viewModel
        this.owner = owner
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(private val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: MutableMap<String, String>) {
            binding.itemMain = ItemMainViewModel(data)
            binding.executePendingBindings()
        }
    }

}
