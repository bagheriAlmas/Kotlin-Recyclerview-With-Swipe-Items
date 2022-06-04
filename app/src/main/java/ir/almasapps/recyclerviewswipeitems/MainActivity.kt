package ir.almasapps.recyclerviewswipeitems

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.almasapps.recyclerviewswipeitems.Adapter.MyAdapter
import ir.almasapps.recyclerviewswipeitems.Data.DataService
import ir.almasapps.recyclerviewswipeitems.Helper.MyButton
import ir.almasapps.recyclerviewswipeitems.Helper.MySwipeHelper
import ir.almasapps.recyclerviewswipeitems.Helper.MyButtonClickListener
import ir.almasapps.recyclerviewswipeitems.Model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(this,DataService.items)
        recyclerview.adapter = adapter

        object :MySwipeHelper(this,recyclerview,200){
            override fun instantiateMyButton(viewHolder: RecyclerView.ViewHolder, buffer: MutableList<MyButton>) {


                // DELETE BUTTON
                buffer.add(MyButton(this@MainActivity,"DELETE",30,0, Color.parseColor("#FF3c30"),
                    object : MyButtonClickListener {
                        override fun onClick(pos: Int) {

                            val deleteItem : Item = DataService.items[pos]
                            DataService.items.removeAt(pos)
                            adapter.notifyItemRemoved(pos)

                            Snackbar.make(recyclerview, deleteItem.name.toString(), Snackbar.LENGTH_LONG).setAction("Undo") {
                                DataService.items.add(pos, deleteItem)
                                adapter.notifyItemInserted(pos)
                            }.show()
                        }
                    }))

                // EDIT BUTTON
                buffer.add(MyButton(this@MainActivity,"UPDATE",30,0, Color.parseColor("#FF9502"),
                    object : MyButtonClickListener {
                        override fun onClick(pos: Int) {
                            Toast.makeText(this@MainActivity,"UPDATE ID $pos",Toast.LENGTH_SHORT).show()
                        }
                    }))
            }
        }
    }
}