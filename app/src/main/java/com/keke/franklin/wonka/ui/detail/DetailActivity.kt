package com.keke.franklin.wonka.ui.detail


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.keke.franklin.wonka.R
import com.keke.franklin.wonka.data.network.Constant
import com.keke.franklin.wonka.data.network.model.Result
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_main.*


class DetailActivity : AppCompatActivity(), IDetailView {

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onResume() {
        super.onResume()
        val id = intent.getIntExtra(Constant.ID, 0)
        presenter = DetailPresenter(applicationContext, this, id)
        presenter.onResume()
    }

    override fun notifyActivity(result: Result) {

        val loadingSpinner = CircularProgressDrawable(this)
        loadingSpinner.strokeWidth = 5f
        loadingSpinner.centerRadius = 30f
        loadingSpinner.start()

        Glide.with(this).load(result.image).placeholder(loadingSpinner).into(image)

        name.text = StringBuilder().append(result.firstName).append(" ").append(result.lastName)
        gender.text = StringBuilder().append(applicationContext.getString(R.string.gender)).append(": ").append(result.gender)
        profession.text = StringBuilder().append(applicationContext.getString(R.string.profession)).append(": ").append(result.profession)
        age.text = StringBuilder().append(applicationContext.getString(R.string.age)).append(": ").append(result.age)
        country.text = StringBuilder().append(applicationContext.getString(R.string.country)).append(": ").append(result.country)
        color.text = StringBuilder().append(applicationContext.getString(R.string.color)).append(": ").append(result.favorite?.color)
        food.text = StringBuilder().append(applicationContext.getString(R.string.food)).append(": ").append(result.favorite?.food)
    }

    override fun showProgress() {
        progressDetail.visibility = View.VISIBLE
        // detail.visibility = View.GONE
    }

    override fun hideProgress() {
        progressDetail.visibility = View.GONE
        // detail.visibility = View.VISIBLE
    }

    override fun showMessage(message: String) {
        Snackbar.make(conx, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        } else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
