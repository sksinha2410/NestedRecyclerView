
# Nested RecyclerView Example

This Android app demonstrates the use of nested RecyclerViews using Kotlin and XML. A parent RecyclerView contains child RecyclerViews, each displaying a list of items.

## Project Structure

1. **MainActivity.kt**
2. **ParentAdapter.kt**
3. **ChildAdapter.kt**
4. **ParentItem.kt**
5. **ChildItem.kt**
6. **activity_main.xml**
7. **item_parent.xml**
8. **item_child.xml**

## Files and Code

### 1. MainActivity.kt
```kotlin
package com.sksinha2410.ananotherreyclerview

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
```

### 2. ParentAdapter.kt
```kotlin
package com.sksinha2410.ananotherreyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentAdapter(private val parentList: List<ParentItem>) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parent, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(parentList[position])
    }

    override fun getItemCount(): Int = parentList.size

    class ParentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val childRecyclerView: RecyclerView = view.findViewById(R.id.child_recyclerview)

        fun bind(parentItem: ParentItem) {
            view.findViewById<TextView>(R.id.parent_title).text = parentItem.title
            childRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            childRecyclerView.adapter = ChildAdapter(parentItem.childList)
        }
    }
}
```

### 3. ChildAdapter.kt
```kotlin
package com.sksinha2410.ananotherreyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildAdapter(private val childList: List<ChildItem>) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_child, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(childList[position])
    }

    override fun getItemCount(): Int = childList.size

    class ChildViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(childItem: ChildItem) {
            view.findViewById<TextView>(R.id.child_title).text = childItem.title
        }
    }
}
```

### 4. ParentItem.kt
```kotlin
package com.sksinha2410.ananotherreyclerview

data class ParentItem(
    val title: String,
    val childList: List<ChildItem>
)
```

### 5. ChildItem.kt
```kotlin
package com.example.nestedrecyclerview

data class ChildItem(
    val title: String
)
```

### 6. activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/parent_recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="16dp" />
</LinearLayout>
```

### 7. item_parent.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <TextView
        android:id="@+id/parent_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Parent Title"
        android:textSize="18sp"
        android:layout_margin="8dp" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/child_recyclerview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_marginBottom="16dp" />
</LinearLayout>
```

### 8. item_child.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:layout_margin="8dp">

    <TextView
        android:id="@+id/child_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Child Title"
        android:textSize="16sp"
        android:layout_marginBottom="8dp" />
</LinearLayout>
```

## Explanation

### MainActivity.kt
- Sets up the main RecyclerView (`parentRecyclerView`) with a `ParentAdapter`.
- `getParentData()` creates a list of `ParentItem` objects, each containing a list of `ChildItem` objects.

### ParentAdapter.kt
- Binds each parent item to a view (`item_parent.xml`) that contains a title and another RecyclerView (`childRecyclerView`).
- `ParentViewHolder` sets up the inner RecyclerView with a `ChildAdapter`.

### ChildAdapter.kt
- Binds each child item to a view (`item_child.xml`) that contains a simple title.

### ParentItem.kt
- A data class representing a parent item that includes a title and a list of child items.

### ChildItem.kt
- A data class representing a child item that includes a title.

### activity_main.xml
- Contains a single RecyclerView (`parent_recyclerview`) that fills the screen.

### item_parent.xml
- Defines the layout for each parent item, including a title and an inner RecyclerView.

### item_child.xml
- Defines the layout for each child item, with a simple title.

## How it Works
- **MainActivity** initializes the parent RecyclerView with a list of parent items.
- **ParentAdapter** creates and binds view holders for each parent item, setting up a nested RecyclerView for each.
- **ChildAdapter** binds view holders for each child item within the nested RecyclerView.
- **ParentItem** and **ChildItem** are data classes used to hold the data for parent and child items respectively.

This setup allows each parent item to have its own horizontal list of child items, demonstrating the use of nested RecyclerViews in an Android app.

## Conclusion
This example shows how to use nested RecyclerViews to create complex lists with different types of items. It is a common pattern in many apps, especially those displaying hierarchical data or lists within lists.
