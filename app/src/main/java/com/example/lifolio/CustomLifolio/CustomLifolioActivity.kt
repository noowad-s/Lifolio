package com.example.lifolio.CustomLifolio

import android.content.DialogInterface
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityCustomLifolioBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class CustomLifolioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCustomLifolioBinding
    private lateinit var lineChart1: LineChart
    private lateinit var lineChart2: LineChart
    private lateinit var lineChart3: LineChart

    private var categoryDayList1 : Array<String> = arrayOf(
        "1/1",
        "1/6",
        "1/14",
        "1/19",
        "1/24",
        "1/25",
        "1/28",
        "2/1",
    )
    private var categoryDayList2 : Array<String> = arrayOf(
        "1/1",
        "1/6",
        "1/14",
        "1/19",
        "1/24",
        "1/25",
        "1/28",
        "2/1",
    )
    private var categoryDayList3 : Array<String> = arrayOf(
        "1/1",
        "1/6",
        "1/14",
        "1/19",
        "1/24",
        "1/25",
        "1/28",
        "2/1",
    )

    private var dummyList1: ArrayList<Entry> = arrayListOf<Entry>()
    private var dummyList2: ArrayList<Entry> = arrayListOf<Entry>()
    private var dummyList3: ArrayList<Entry> = arrayListOf<Entry>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomLifolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customlifolioChart1.visibility = View.GONE
        binding.customlifolioChart2.visibility = View.GONE
        binding.customlifolioChart3.visibility = View.GONE

        var category = arrayOf("??????", "??????", "??????") // ????????? ???????????? ????????? ???????????? ??????
        binding.customlifolioCategorySelectConst.setOnClickListener{ // ???????????? ????????? ???????????? ????????? ?????????
            AlertDialog.Builder(this)
                .setTitle("???????????? ??????")
                .setItems(category, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        val currentItem = category[which]
                        binding.customlifolioCategoryTv.text = currentItem
                    }
                })
                .show()
        }

        // ????????? ?????? ?????? ?????? ????????? ?????????
        binding.customlifolioCategoryBtn1.setOnClickListener {
            Log.d("TAG", "onCreate: btn1")
            if(binding.customlifolioChart1.visibility == View.GONE) {
                binding.customlifolioChart1.visibility = View.VISIBLE
            }
            binding.customlifolioChart2.visibility = View.GONE
            binding.customlifolioChart3.visibility = View.GONE
        }

        binding.customlifolioCategoryBtn2.setOnClickListener {
            Log.d("TAG", "onCreate: btn2")
            if(binding.customlifolioChart2.visibility == View.GONE) {
                binding.customlifolioChart2.visibility = View.VISIBLE
            }
            binding.customlifolioChart1.visibility = View.GONE
            binding.customlifolioChart3.visibility = View.GONE
        }

        binding.customlifolioCategoryBtn3.setOnClickListener {
            Log.d("TAG", "onCreate: btn3")
            if(binding.customlifolioChart3.visibility == View.GONE) {
                binding.customlifolioChart3.visibility = View.VISIBLE
            }
            binding.customlifolioChart1.visibility = View.GONE
            binding.customlifolioChart2.visibility = View.GONE
        }

        lineChart1 = binding.customlifolioChart1
        lineChart2 = binding.customlifolioChart2
        lineChart3 = binding.customlifolioChart3

        dummyList1.add(Entry(1f, 50f))
        dummyList1.add(Entry(2f, 70f))
        dummyList1.add(Entry(3f, 40f))
        dummyList1.add(Entry(4f, 50f))
        dummyList1.add(Entry(5f, 30f))
        dummyList1.add(Entry(6f, 100f))
        dummyList1.add(Entry(7f, 20f))
        dummyList1.add(Entry(8f, 50f))

        dummyList2.add(Entry(1f, 20f))
        dummyList2.add(Entry(2f, 30f))
        dummyList2.add(Entry(3f, 60f))
        dummyList2.add(Entry(4f, 30f))
        dummyList2.add(Entry(5f, 50f))
        dummyList2.add(Entry(6f, 20f))
        dummyList2.add(Entry(7f, 40f))
        dummyList2.add(Entry(8f, 50f))

        dummyList3.add(Entry(1f, 10f))
        dummyList3.add(Entry(2f, 20f))
        dummyList3.add(Entry(3f, 50f))
        dummyList3.add(Entry(4f, 20f))
        dummyList3.add(Entry(5f, 40f))
        dummyList3.add(Entry(6f, 70f))
        dummyList3.add(Entry(7f, 8f))
        dummyList3.add(Entry(8f, 60f))

        setGraph(lineChart1, dummyList1, categoryDayList1)
        setGraph(lineChart2, dummyList2, categoryDayList2)
        setGraph(lineChart3, dummyList3, categoryDayList3)
    }

    private fun setGraph(lineChart: LineChart, dummy: ArrayList<Entry>, dayList: Array<String>) {
        val xAxis: XAxis = lineChart.xAxis
        // x??? ??????
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM   //x??? ???????????? ????????? ?????????
            textSize = 14f
            setDrawAxisLine(true)
            setDrawGridLines(false) // ?????? ????????? ?????? ?????? X
            granularity = 1f    // x??? ????????? ?????? ??????
            axisMinimum = 1f    // x??? ????????? ?????? ?????? ???
            isGranularityEnabled = true     // x??? ????????? ???????????? ????????? ??????
//            spaceMin = 0.1f     // Chart ??? ?????? ?????? ?????????
//            spaceMin = 0.1f
        }

        xAxis.valueFormatter = object: ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val day = dayList.get(value.toInt() - 1)
                Log.d("TAG", "getFormattedValue: ${day.substring(day.length - 2)}")
                return day
            }
        }

//        val day = dayList.get(value.toInt() - 1)
//        return day.substring(day.length - 2)

        // ?????? ???????????? ??????
        lineChart.apply {
            description.isEnabled = false

            // axisRight (?????????) ??????
            axisRight.apply {
                isEnabled = false //y?????? ????????? ????????? ?????? ????????????
                setDrawLabels(false) // ?????? ??????
                setDrawAxisLine(false)
                axisMinimum = 0f
                axisMaximum = 100f
            }
            // axisLeft (??????) ??????
            axisLeft.apply {
                textSize = 14f
                gridLineWidth = 1f
                setDrawAxisLine(false)
                axisLineWidth = 2f
                axisMinimum = 0f
                axisMaximum = 100f
            }

            // ?????? ??????
            legend.isEnabled = false
        }

//        var graphCustomMarker = GraphCustomMarker(this, R.layout.item_graph_marker)
//        lineChart.marker = graphCustomMarker
//
//        graphCustomMarker.chartView = lineChart

        // ?????? ??? ??????????????? ??????
        val gradient = LinearGradient(
            800f, 100f, 30f, 0f,
            ContextCompat.getColor(this, R.color.graph_end_color),
            ContextCompat.getColor(this, R.color.graph_start_color),
            Shader.TileMode.CLAMP)
        val paint = lineChart.renderer.paintRender
        paint.setShader(gradient)

        // Entry ArrayList ?????? - Dummy
        var dataList: ArrayList<Entry> = dummy

        xAxis.mAxisMaximum = dataList.size.toFloat()

        // LinearDataSet??? ????????? ArrayList ??????
        var set: LineDataSet = LineDataSet(dataList, "dataset 1")
        set.apply {
            mode = LineDataSet.Mode.CUBIC_BEZIER    // cubic line ??????
            setDrawValues(false)
            setDrawCircleHole(true)
            setDrawCircles(true)
            cubicIntensity = 0.2f   // ?????? ??????
            valueTextSize = 10f
            lineWidth = 5f
            circleRadius = 10f      // ??? ?????? ?????????
            circleHoleRadius = 5f   // ??? ?????? ?????????
//            circleHoleColor = R.color.graph_circle_color  // ?????? ?????? x
            valueTextSize = 0f  // ?????? ?????? ????????????
            fillAlpha = 0   // ?????????
            isHighlightEnabled =false
        }

        // ILineDataSet ????????? ???????????? LinearDataSet ?????? - ?????? ???????????? ????????? ????????? ???????????? ????????? ??????
        var dataSets: ArrayList<ILineDataSet> = arrayListOf()
        dataSets.add(set)

        // ?????????????????? ILineDataSet ????????? ????????? ??????
        var data: LineData = LineData(dataSets)


        // ????????? ?????? ?????? ??? ????????????????????? ?????????
//        set.fillDrawable = ContextCompat.getDrawable(this, R.drawable.item_graph_fill)
//        set.setDrawFilled(true)

        // ????????? ?????? ????????? ??????
        lineChart.data = data


        // ????????? ?????? ??? ???????????? ??????
        lineChart.apply {
            setVisibleXRangeMaximum(5f) // ????????? data ?????? ??? ????????? Scroll ??????
            moveViewToX(data.entryCount.toFloat())
            setPinchZoom(false)
            isDoubleTapToZoomEnabled = false

        }

        // ?????? ????????? - Data ?????? ??? ??????
        lineChart.invalidate()
    }
}