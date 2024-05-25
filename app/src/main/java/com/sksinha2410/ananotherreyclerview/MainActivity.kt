package com.sksinha2410.ananotherreyclerview

import ParentAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val parentRecyclerView = findViewById<RecyclerView>(R.id.parent_recyclerview)
        parentRecyclerView.layoutManager = LinearLayoutManager(this)
        parentRecyclerView.adapter = ParentAdapter(getParentData())
    }

    private fun getParentData(): List<ParentItem> {
        val childItems = List(5) { ChildItem("Child Item $it") }
        return List(10) { ParentItem("Parent Item $it", childItems) }
    }
}