package com.example.myfridge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {

    private lateinit var  newRecyclerView : RecyclerView
    private lateinit var  newArrayList : ArrayList<News>
//    lateinit var id : Array <Int>
    lateinit var imageId : Array<Int>
    lateinit var  heading : Array<String>
    lateinit var website : Array<String>
    lateinit var amount : Array<String>
    lateinit var expiration : Array<String>
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j
        )

        heading = arrayOf(
            "Heading 1",
            "Heading 2",
            "Heading 3",
            "Heading 4",
            "Heading 5",
            "Heading 6",
            "Heading 7",
            "Heading 8",
            "Heading 9",
            "Heading 10"
        )
        website = arrayOf(
            "Heading 1",
            "Heading 2",
            "Heading 3",
            "Heading 4",
            "Heading 5",
            "Heading 6",
            "Heading 7",
            "Heading 8",
            "Heading 9",
            "Heading 10"
        )
        amount = arrayOf(
            "QTY: 12",
            "QTY: 12",
            "QTY: 12",
            "QTY: 12",
            "QTY: 12",
            "QTY: 12",
            "QTY: 12",
            "QTY: 12",
            "QTY: 12",
            "QTY: 12"
        )

        expiration = arrayOf(
            "01/03/23",
            "01/03/23",
            "01/03/23",
            "01/03/23",
            "01/03/23",
            "01/03/23",
            "01/03/23",
            "01/03/23",
            "01/03/23",
            "01/03/23"
        )

        newRecyclerView=findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<News>()

        getUserData()

    }

    fun getUserData() {

//        for(i in imageId.indices){
            var db=DataBaseHandler(context)
            var data = db.readData()
        for(i in 0..(data.size-1)){
//            id[i]=data.get(i).id.toString().toInt()
            heading[i]=data.get(i).gName.toString()
            website[i]=data.get(i).website.toString()
            amount[i]=data.get(i).noOfItems.toString()
            expiration[i]=data.get(i).expiryDate.toString()
            val news = News(imageId[i],heading[i],website[i],amount[i],expiration[i])
            newArrayList.add(news)
        }

//        }
        newRecyclerView.adapter = MyAdapter(newArrayList)
    }

}