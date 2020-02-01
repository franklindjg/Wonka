package com.keke.franklin.wonka.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.keke.franklin.wonka.R
import com.keke.franklin.wonka.Wonka
import com.keke.franklin.wonka.data.network.Constant
import com.keke.franklin.wonka.data.network.model.Result
import com.keke.franklin.wonka.ui.detail.DetailActivity
import com.keke.franklin.wonka.ui.main.custom.EndlessRecyclerViewScrollListener
import com.keke.franklin.wonka.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_filter -> {
                ViewUtils.showFilterDialog(this, applicationContext, DialogInterface.OnClickListener { _, i ->
                    // TODO: FILTER BY GENDER
                    Log.d("XXX", i.toString())
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        when((applicationContext as Wonka).selectedItemFilter) {
                            0 ->{showMessage("Not implemented")}
                            1 ->{showMessage("Not implemented")}
                            2 ->{showMessage("Not implemented")}
                        }
                    }
                }, (applicationContext as Wonka).selectedItemFilter)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter = MainPresenter(applicationContext, this)
        setRecyclerView()
        presenter.onResume()
    }

    override fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = linearLayoutManager
        adapter = MainAdapter(arrayListOf(), presenter::onItemClicked)

        val mDividerItemDecoration = DividerItemDecoration(
            recycler.context,
            LinearLayoutManager(this).orientation
        )
        val divider = ContextCompat.getDrawable(this, R.drawable.divider)
        divider?.let { mDividerItemDecoration.setDrawable(it) }
        recycler.addItemDecoration(mDividerItemDecoration)

        recycler.adapter = adapter

        // restore recycler state
        recycler.layoutManager?.onRestoreInstanceState((applicationContext as Wonka).recyclerState)
        // reset recycler state
        (applicationContext as Wonka).recyclerState = null

        val scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore (page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (page < 5) presenter.getPage(page)
            }
        }

        val currentPage = (applicationContext as Wonka).currentPage
        if (currentPage != null) {
            scrollListener.setCurrentPage(currentPage)
        }
        recycler.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
    }

    override fun launchActivity(id: Int) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(Constant.ID, id)
        }
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show()
    }

    override fun notifyActivity(list: List<Result>) {
        val currentSize = adapter.itemCount
        adapter.add(list)
        adapter.notifyItemRangeInserted(currentSize, adapter.itemCount - 1)
        Log.d("XXX", adapter.itemCount.toString())
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        recycler.visibility = View.GONE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        recycler.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        // save recycler state
        (applicationContext as Wonka).recyclerState = recycler.layoutManager?.onSaveInstanceState()
    }
}
