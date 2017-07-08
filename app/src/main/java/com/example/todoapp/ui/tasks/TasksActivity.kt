package com.example.todoapp.ui.tasks

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.util.Log
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityTasksBinding
import com.example.todoapp.ui.base.BaseActivity

class TasksActivity :BaseActivity() {

    lateinit var binding: ActivityTasksBinding

    lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)
        initViewModel()
        initToolbar()
        initFragment()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem) = when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
    }

    fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
    }

    fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setPadding(0, statusBarHeight, 0, 0)
        Log.d("height", statusBarHeight.toString())
    }

    fun initFragment() {
        supportFragmentManager.findFragmentByTag(TasksFragment.TAG)?: TasksFragment.newInstance().apply {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, this, TasksFragment.TAG)
                    .commit()
        }
    }

}