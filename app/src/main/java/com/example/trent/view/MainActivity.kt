package com.example.trent.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trent.R
import com.example.trent.model.ProductsItem
import com.example.trent.util.OnItemClickListener
import com.example.trent.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener{
    var productsItem = ArrayList<ProductsItem>()
    lateinit var viewModel: ListViewModel
    private val dummyListAdapter = DummyListAdapter(productsItem,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        dummyList.layoutManager = LinearLayoutManager(this)
        dummyList.adapter = dummyListAdapter


        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }



    fun observeViewModel() {
        viewModel.dummy.observe(this, Observer {dummy ->
            dummy?.let {
                dummyList.visibility = View.VISIBLE
                dummyListAdapter.updateDummy(it.products?: emptyList()) }
        })

        viewModel.dummyLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    dummyList.visibility = View.GONE
                }
            }
        })
    }

    override fun onItemClickListener(position: Int) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("title", productsItem[position].title)
        intent.putExtra("thumbnail", productsItem[position].thumbnail)
        intent.putStringArrayListExtra("images",
            productsItem[position].images as java.util.ArrayList<String>?
        )
        intent.putExtra("price", productsItem[position].price)
        intent.putExtra("description", productsItem[position].description)
        intent.putExtra("category", productsItem[position].category)
        intent.putExtra("stock", productsItem[position].stock)
        intent.putExtra("brand", productsItem[position].brand)
        intent.putExtra("rating", productsItem[position].rating)
        startActivity(intent)
    }

}
